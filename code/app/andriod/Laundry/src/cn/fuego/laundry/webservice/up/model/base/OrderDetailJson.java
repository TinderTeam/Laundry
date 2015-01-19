package cn.fuego.laundry.webservice.up.model.base;

import java.io.Serializable;

public class OrderDetailJson implements Serializable
{
 	
	private int order_detail_id;
	private int order_id;
	private int product_id;
 
	
 
    private int quantity = 1;
	private String product_name;
	private String product_type;
	private String product_describe;
	
	private float current_price;
 	private float original_price;
	private String product_img;
	private String product_status;
	private String product_update_time;
	private String product_limit_time;
	
	private String detail_info;
	
	
	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	public int getOrder_detail_id()
	{
		return order_detail_id;
	}
	public void setOrder_detail_id(int order_detail_id)
	{
		this.order_detail_id = order_detail_id;
	}
	public int getOrder_id()
	{
		return order_id;
	}
	public void setOrder_id(int order_id)
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
	public String getProduct_type()
	{
		return product_type;
	}
	public void setProduct_type(String product_type)
	{
		this.product_type = product_type;
	}
	public String getProduct_describe()
	{
		return product_describe;
	}
	public void setProduct_describe(String product_describe)
	{
		this.product_describe = product_describe;
	}
	public float getCurrent_price()
	{
		return current_price;
	}
	public void setCurrent_price(float current_price)
	{
		this.current_price = current_price;
	}
	public float getOriginal_price()
	{
		return original_price;
	}
	public void setOriginal_price(float original_price)
	{
		this.original_price = original_price;
	}
	public String getProduct_img()
	{
		return product_img;
	}
	public void setProduct_img(String product_img)
	{
		this.product_img = product_img;
	}
	public String getProduct_status()
	{
		return product_status;
	}
	public void setProduct_status(String product_status)
	{
		this.product_status = product_status;
	}
	public String getProduct_update_time()
	{
		return product_update_time;
	}
	public void setProduct_update_time(String product_update_time)
	{
		this.product_update_time = product_update_time;
	}
	public String getProduct_limit_time()
	{
		return product_limit_time;
	}
	public void setProduct_limit_time(String product_limit_time)
	{
		this.product_limit_time = product_limit_time;
	}
	public String getDetail_info()
	{
		return detail_info;
	}
	public void setDetail_info(String detail_info)
	{
		this.detail_info = detail_info;
	}
 
	

}
