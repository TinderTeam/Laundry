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
	private String take_addr;
	private String delivery_time;
	private String take_time;
	private String contact_name;   
	private String phone;
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
	public String getTake_addr()
	{
		return take_addr;
	}
	public void setTake_addr(String take_addr)
	{
		this.take_addr = take_addr;
	}
	public String getDelivery_time()
	{
		return delivery_time;
	}
	public void setDelivery_time(String delivery_time)
	{
		this.delivery_time = delivery_time;
	}
	public String getTake_time()
	{
		return take_time;
	}
	public void setTake_time(String take_time)
	{
		this.take_time = take_time;
	}
 
	public String getContact_name()
	{
		return contact_name;
	}
	public void setContact_name(String contact_name)
	{
		this.contact_name = contact_name;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
 

}
