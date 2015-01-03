package cn.fuego.laundry.webservice.up.model.base;

import java.io.Serializable;

public class OrderDetailJson implements Serializable
{
	private String product_name;
	private float product_price;
	private int amount;
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
	public int getAmount()
	{
		return amount;
	}
	public void setAmount(int amount)
	{
		this.amount = amount;
	}
	
	

}
