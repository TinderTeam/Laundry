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
	private List<Integer> selectProductList = new ArrayList<Integer>();
	
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

	public List<Integer> getSelectProductList()
	{
		return selectProductList;
	}

	public void setSelectProductList(List<Integer> selectProductList)
	{
		this.selectProductList = selectProductList;
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
		List<OrderDetailJson> orderDetailList = new ArrayList<OrderDetailJson>();
		for(Integer e :selectProductList)
		{
			OrderDetailJson detail = new OrderDetailJson();
			ProductJson product = getProductByID(e);
			detail.setProduct_id(e);
			detail.setProduct_name(product.getProduct_name());
			detail.setProduct_price(product.getPrice());
			orderDetailList.add(detail);
		}
		return orderDetailList;
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
