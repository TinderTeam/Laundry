package cn.fuego.laundry.webservice.up.model.base;

import java.io.Serializable;
import java.util.Date;

public class OrderJson implements Serializable
{
	private String order_id;
	private String order_name;
	private int user_id;
	private Date create_time;
	private Date confirm_time;
	private Date end_time;
	private String pay_option;
	private String order_status;
	private int delivery_info_id;
	private int handler_id;
	private String operater_name;
	private float totalPrice;
	private float totalCount;
	public String getOrder_id()
	{
		return order_id;
	}
	public void setOrder_id(String order_id)
	{
		this.order_id = order_id;
	}
	public String getOrder_name()
	{
		return order_name;
	}
	public void setOrder_name(String order_name)
	{
		this.order_name = order_name;
	}
	public int getUser_id()
	{
		return user_id;
	}
	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}
	public Date getCreate_time()
	{
		return create_time;
	}
	public void setCreate_time(Date create_time)
	{
		this.create_time = create_time;
	}
	public Date getConfirm_time()
	{
		return confirm_time;
	}
	public void setConfirm_time(Date confirm_time)
	{
		this.confirm_time = confirm_time;
	}
	public Date getEnd_time()
	{
		return end_time;
	}
	public void setEnd_time(Date end_time)
	{
		this.end_time = end_time;
	}
	public String getPay_option()
	{
		return pay_option;
	}
	public void setPay_option(String pay_option)
	{
		this.pay_option = pay_option;
	}
	public String getOrder_status()
	{
		return order_status;
	}
	public void setOrder_status(String order_status)
	{
		this.order_status = order_status;
	}
	public int getDelivery_info_id()
	{
		return delivery_info_id;
	}
	public void setDelivery_info_id(int delivery_info_id)
	{
		this.delivery_info_id = delivery_info_id;
	}
	public int getHandler_id()
	{
		return handler_id;
	}
	public void setHandler_id(int handler_id)
	{
		this.handler_id = handler_id;
	}
	public String getOperater_name()
	{
		return operater_name;
	}
	public void setOperater_name(String operater_name)
	{
		this.operater_name = operater_name;
	}
	public float getTotalPrice()
	{
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice)
	{
		this.totalPrice = totalPrice;
	}
	public float getTotalCount()
	{
		return totalCount;
	}
	public void setTotalCount(float totalCount)
	{
		this.totalCount = totalCount;
	}
 




}
