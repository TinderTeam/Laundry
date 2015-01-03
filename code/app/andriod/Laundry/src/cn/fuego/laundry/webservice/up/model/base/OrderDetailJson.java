package cn.fuego.laundry.webservice.up.model.base;

import java.io.Serializable;

public class OrderDetailJson implements Serializable
{
 	
	private int order_detail_id;
	private String order_id;
	private int product_id;
 
 

	private String product_name;
	private float product_price
;
	private int quantity
;
	public int getOrder_detail_id()
	{
		return order_detail_id;
	}
	public void setOrder_detail_id(int order_detail_id)
	{
		this.order_detail_id = order_detail_id;
	}
	public String getOrder_id()
	{
		return order_id;
	}
	public void setOrder_id(String order_id)
	{
		this.order_id = order_id;
	}
	public int getProduct_id()
	{
		return product_id;
	}
	public void setProduct_id(int product_id)
	{
		this.product_id = product_id;
	}
	public String getProduct_name()
	{
		return product_name;
	}
	public void setProduct_name(String product_name)
	{
		this.product_name = product_name;
	}
	public float getProduct_price()
	{
		return product_price;
	}
	public void setProduct_price(float product_price)
	{
		this.product_price = product_price;
	}
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
 
	
	

}
