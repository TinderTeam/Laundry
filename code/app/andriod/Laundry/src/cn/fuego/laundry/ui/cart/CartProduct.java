package cn.fuego.laundry.ui.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.constant.OrderTypeEnum;
import cn.fuego.laundry.constant.PriceTypeEnum;
import cn.fuego.laundry.webservice.up.model.CreateOrderReq;
import cn.fuego.laundry.webservice.up.model.base.CustomerJson;
import cn.fuego.laundry.webservice.up.model.base.OrderDetailJson;
import cn.fuego.laundry.webservice.up.model.base.OrderJson;
import cn.fuego.laundry.webservice.up.model.base.ProductJson;
import cn.fuego.misp.service.MemoryCache;

public class CartProduct
{
 	
	
 	private static CartProduct instance;
	
	private CreateOrderReq orderInfo = new CreateOrderReq();
	
	
	public CreateOrderReq getOrderInfo()
	{
		orderInfo.getOrder().setTotal_count(getTotalCount());
		orderInfo.getOrder().setTotal_price(getTotalPrice());
		orderInfo.getOrder().setPrice_type(getOrderPriceType());
		return orderInfo;
	}

 
	private CartProduct()
	{
		 
	}
	
	public String getDispPrice(String priceType,float price)
	{
		String totalPrice = String.valueOf(price);
		if(PriceTypeEnum.FLOAT_PRICE.getStrValue().equals(priceType))
		{
			totalPrice = PriceTypeEnum.FLOAT_PRICE.getStrValue();
		}
		return totalPrice;
	}

	
	public void clearCart()
	{
		this.orderInfo = new CreateOrderReq();
		setDefaultOrderInfo();
	}
	
	public boolean isEmpty()
	{
		return orderInfo.getOrderDetailList().isEmpty();
	}
	
	public void setDefaultOrderInfo()
	{
		this.orderInfo.setToken(MemoryCache.getToken());
		OrderJson order =  this.orderInfo.getOrder();
		CustomerJson customer = AppCache.getInstance().getCustomer();
		if(null != customer)
		{
			order.setUser_id(customer.getUser_id());
			order.setUser_name(customer.getUser_name());
			order.setTake_addr(customer.getAddr());
			order.setDelivery_addr(customer.getAddr());
			order.setContact_name(customer.getCustomer_name());
			order.setPhone(customer.getPhone());
		}

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
		detail.setPrice_type(product.getPrice_type());
		detail.setProduct_type(product.getProduct_name());
		detail.setProduct_describe(product.getDescribe());
		detail.setProduct_img(product.getImg());
		detail.setCurrent_price(product.getPrice());
		detail.setOriginal_price(product.getOriginal_price());
		
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
 
	public String getOrderPriceType()
	{
 		for(OrderDetailJson e : orderInfo.getOrderDetailList())
		{
			if(PriceTypeEnum.FLOAT_PRICE.getStrValue().equals(e.getPrice_type()))
			{
				return PriceTypeEnum.FLOAT_PRICE.getStrValue();
			}
 		}
 		if(OrderTypeEnum.DIRECT_ORDER.getStrValue().equals(orderInfo.getOrder().getOrder_type()))
 		{
 			return PriceTypeEnum.FLOAT_PRICE.getStrValue();
 		}
		return PriceTypeEnum.FIX_PRICE.getStrValue();
	}
	

}
