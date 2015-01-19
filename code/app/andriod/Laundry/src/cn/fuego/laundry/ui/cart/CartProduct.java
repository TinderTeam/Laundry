package cn.fuego.laundry.ui.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.webservice.up.model.CreateOrderReq;
import cn.fuego.laundry.webservice.up.model.base.OrderDetailJson;
import cn.fuego.laundry.webservice.up.model.base.OrderJson;
import cn.fuego.laundry.webservice.up.model.base.ProductJson;

public class CartProduct
{
 	
 	private static CartProduct instance;
	
	private CreateOrderReq orderInfo = new CreateOrderReq();
	
	
	public CreateOrderReq getOrderInfo()
	{
		orderInfo.getOrder().setTotal_count(getTotalCount());
		orderInfo.getOrder().setTotal_price(getTotalPrice());
		return orderInfo;
	}

 
	private CartProduct()
	{
		 
	}
	
	public void setDefaultOrderInfo()
	{
		OrderJson order =  this.orderInfo.getOrder();
		order.setUser_id(AppCache.getInstance().getUser().getUser_id());
		order.setUser_name(AppCache.getInstance().getUser().getUser_name());
		order.setTake_addr(AppCache.getInstance().getCustomer().getAddr());
		order.setDelivery_addr(AppCache.getInstance().getCustomer().getAddr());
		order.setContact_name(AppCache.getInstance().getCustomer().getCustomer_name());
		order.setPhone(AppCache.getInstance().getCustomer().getPhone());
	}
	
	public synchronized static CartProduct getInstance()
	{
		if(null == instance)
		{
			instance = new CartProduct();
		}
		return instance;
		
	}

 
	public int getSelectProduct()
	{
		return orderInfo.getOrderDetailList().size();
	}
	
	public boolean containsSelected(int productID)
	{
		for(OrderDetailJson e : orderInfo.getOrderDetailList())
		{
			if(e.getProduct_id() == productID)
			{
				return true;
			}
		}
		return false;
	}
	
	public void removeSelected(int productID)
	{
		for(OrderDetailJson e : orderInfo.getOrderDetailList())
		{
			if(e.getProduct_id() == productID)
			{
				orderInfo.getOrderDetailList().remove(e);
				return;
			}
		}
	}
	
	public void addSelected(int productID)
	{
		OrderDetailJson detail = new OrderDetailJson();
		ProductJson product = getProductByID(productID);
		detail.setProduct_id(productID);
		detail.setProduct_name(product.getProduct_name());
		detail.setCurrent_price(product.getPrice());
		orderInfo.getOrderDetailList().add(detail);
	}

 
	
	private Map<Integer,List<ProductJson>>  productMap = new HashMap<Integer, List<ProductJson>>();
	
	public Map<Integer, List<ProductJson>> getProductMap()
	{
		return productMap;
	}

	public void setProductMap(Map<Integer, List<ProductJson>> productMap)
	{
		this.productMap = productMap;
	}
	public void refreshProduct(List<ProductJson> productList)
	{
		productMap.clear();
		for(ProductJson json : productList)
		{	
			List<ProductJson> tempList = this.productMap.get(json.getType_id());
			if(null == tempList)
			{
				tempList = new ArrayList<ProductJson>();
				productMap.put(json.getType_id(), tempList);
			}
			tempList.add(json);
		}
	}
	
 
	
	private int getTotalCount()
	{
		int totalCnt = 0;
 		for(OrderDetailJson e : orderInfo.getOrderDetailList())
		{
 			totalCnt += e.getQuantity();
		}
		return totalCnt;
	}
	
	private float getTotalPrice()
	{
 		float totalPrice = 0;
		for(OrderDetailJson e : orderInfo.getOrderDetailList())
		{
			totalPrice += e.getCurrent_price() * e.getQuantity();
 		}
		return totalPrice;
	}
	
	private ProductJson getProductByID(int id)
	{
		for(Integer type :productMap.keySet())
		{
			List<ProductJson> productList = productMap.get(type);
			if(!ValidatorUtil.isEmpty(productList))
			{
				for(ProductJson product :productList)
				{
					if(product.getProduct_id() == id)
					{
						return product;
					}
				}
			}
		}
		return null;
	}
 
	

}
