package cn.fuego.laundry.webservice.up.model.base;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jun
 *
 */
public class DeliveryInfoJson implements Serializable
{
	private int delivery_info_id;
	private int user_id;
	private String delivery_addr;
	private int take_addr;
	private Date delivery_time;
	private Date take_time;
	private String customer_name;   
	private int phone;
	public int getDelivery_info_id()
	{
		return delivery_info_id;
	}
	public void setDelivery_info_id(int delivery_info_id)
	{
		this.delivery_info_id = delivery_info_id;
	}
	public int getUser_id()
	{
		return user_id;
	}
	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}
	public String getDelivery_addr()
	{
		return delivery_addr;
	}
	public void setDelivery_addr(String delivery_addr)
	{
		this.delivery_addr = delivery_addr;
	}
	public int getTake_addr()
	{
		return take_addr;
	}
	public void setTake_addr(int take_addr)
	{
		this.take_addr = take_addr;
	}
	public Date getDelivery_time()
	{
		return delivery_time;
	}
	public void setDelivery_time(Date delivery_time)
	{
		this.delivery_time = delivery_time;
	}
	public Date getTake_time()
	{
		return take_time;
	}
	public void setTake_time(Date take_time)
	{
		this.take_time = take_time;
	}
	public String getCustomer_name()
	{
		return customer_name;
	}
	public void setCustomer_name(String customer_name)
	{
		this.customer_name = customer_name;
	}
	public int getPhone()
	{
		return phone;
	}
	public void setPhone(int phone)
	{
		this.phone = phone;
	}
	
	
 
	
	 

}
