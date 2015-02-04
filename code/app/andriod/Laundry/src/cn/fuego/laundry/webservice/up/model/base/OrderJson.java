package cn.fuego.laundry.webservice.up.model.base;

import java.io.Serializable;
import java.util.Date;

import cn.fuego.laundry.constant.OrderTypeEnum;
import cn.fuego.laundry.constant.PayOptionEnum;

public class OrderJson implements Serializable
{
	private int order_id;
	private String order_code ="11111111";
	private String order_name ="洗衣";
	private int company_id;
	private int user_id;  //
	private String user_name;  //
	private String create_time;  
	private String confirm_time;
	private String end_time;
	private String pay_option = PayOptionEnum.OFFLINE_PAY.getStrValue();  //
	private String order_status;  
	private int handler_id;
	private String operater_name;
	private String order_type = OrderTypeEnum.NORMAL_ORDER.getStrValue() ; //
	private String order_note; //
	
	private String price_type; //
	private float total_price; //
	private int total_count; //
	
	
 	private String delivery_addr; //
	private String take_addr; //
	private String delivery_time; //
	private String take_time; //
	private String contact_name; //   
	private String phone; //
	
	
	
	public String getPrice_type()
	{
		return price_type;
	}
	public void setPrice_type(String price_type)
	{
		this.price_type = price_type;
	}
	public int getCompany_id()
	{
		return company_id;
	}
	public void setCompany_id(int company_id)
	{
		this.company_id = company_id;
	}
	public String getUser_name()
	{
		return user_name;
	}
	public void setUser_name(String user_name)
	{
		this.user_name = user_name;
	}
	public int getOrder_id()
	{
		return order_id;
	}
	public void setOrder_id(int order_id)
	{
		this.order_id = order_id;
	}
	public String getOrder_code()
	{
		return order_code;
	}
	public void setOrder_code(String order_code)
	{
		this.order_code = order_code;
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
 
	public String getCreate_time()
	{
		return create_time;
	}
	public void setCreate_time(String create_time)
	{
		this.create_time = create_time;
	}
	public String getConfirm_time()
	{
		return confirm_time;
	}
	public void setConfirm_time(String confirm_time)
	{
		this.confirm_time = confirm_time;
	}
	public String getEnd_time()
	{
		return end_time;
	}
	public void setEnd_time(String end_time)
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
	public String getOrder_type()
	{
		return order_type;
	}
	public void setOrder_type(String order_type)
	{
		this.order_type = order_type;
	}
	public String getOrder_note()
	{
		return order_note;
	}
	public void setOrder_note(String order_note)
	{
		this.order_note = order_note;
	}
	public float getTotal_price()
	{
		return total_price;
	}
	public void setTotal_price(float total_price)
	{
		this.total_price = total_price;
	}
	public int getTotal_count()
	{
		return total_count;
	}
	public void setTotal_count(int total_count)
	{
		this.total_count = total_count;
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
