package cn.fuego.laundry.ui.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.webservice.up.model.base.OrderDetailJson;
import cn.fuego.laundry.webservice.up.model.base.ProductJson;

public class CartProduct
{
 	
	private List<OrderDetailJson> orderDetailList = new ArrayList<OrderDetailJson>();
	private static CartProduct instance;
	private CartProduct()
	{
		 
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
		return orderDetailList.size();
	}
	
	public boolean containsSelected(int productID)
	{
		for(OrderDetailJson e : orderDetailList)
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
		for(OrderDetailJson e : orderDetailList)
		{
			if(e.getProduct_id() == productID)
			{
				orderDetailList.remove(e);
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
		detail.setProduct_price(product.getPrice());
		orderDetailList.add(detail);
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
	
	public List<OrderDetailJson> getOrderDetailList()
	{
		 
		return orderDetailList;
	}
	
	public int getTotalCount()
	{
		int totalCnt = 0;
 		for(OrderDetailJson e : this.orderDetailList)
		{
 			totalCnt += e.getQuantity();
		}
		return totalCnt;
	}
	
	public float getTotalPrice()
	{
 		float totalPrice = 0;
		for(OrderDetailJson e : this.orderDetailList)
		{
			totalPrice += e.getProduct_price() * e.getQuantity();
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
