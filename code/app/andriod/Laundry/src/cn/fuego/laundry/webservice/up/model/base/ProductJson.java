package cn.fuego.laundry.webservice.up.model.base;

import java.io.Serializable;
import java.util.Date;

public class ProductJson implements Serializable
{
	
	private int product_id;
	private int company_id;
	private String product_name;
	private int type_id;
	private int seller_id;
	private String describe;
	private String price_type;

	private float price;
	private float original_price;
	private String img;
	private String product_status;
	private Date update_time;
	private Date end_time;
	private String detail_info;
	private String type_name;
	private int parent_type_id;

	
	public int getCompany_id()
	{
		return company_id;
	}
	public void setCompany_id(int company_id)
	{
		this.company_id = company_id;
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
	public int getType_id()
	{
		return type_id;
	}
	public void setType_id(int type_id)
	{
		this.type_id = type_id;
	}
	public int getSeller_id()
	{
		return seller_id;
	}
	public void setSeller_id(int seller_id)
	{
		this.seller_id = seller_id;
	}
	public String getDescribe()
	{
		return describe;
	}
	public void setDescribe(String describe)
	{
		this.describe = describe;
	}
	public float getPrice()
	{
		return price;
	}
	public void setPrice(float price)
	{
		this.price = price;
	}
	public float getOriginal_price()
	{
		return original_price;
	}
	public void setOriginal_price(float original_price)
	{
		this.original_price = original_price;
	}
	public String getImg()
	{
		return img;
	}
	public void setImg(String img)
	{
		this.img = img;
	}
	public String getProduct_status()
	{
		return product_status;
	}
	public void setProduct_status(String product_status)
	{
		this.product_status = product_status;
	}
	public Date getUpdate_time()
	{
		return update_time;
	}
	public void setUpdate_time(Date update_time)
	{
		this.update_time = update_time;
	}
	public Date getEnd_time()
	{
		return end_time;
	}
	public void setEnd_time(Date end_time)
	{
		this.end_time = end_time;
	}
	public String getDetail_info()
	{
		return detail_info;
	}
	public void setDetail_info(String detail_info)
	{
		this.detail_info = detail_info;
	}
	public String getType_name()
	{
		return type_name;
	}
	public void setType_name(String type_name)
	{
		this.type_name = type_name;
	}
	public int getParent_type_id()
	{
		return parent_type_id;
	}
	public void setParent_type_id(int parent_type_id)
	{
		this.parent_type_id = parent_type_id;
	}
	public String getPrice_type()
	{
		return price_type;
	}
	public void setPrice_type(String price_type)
	{
		this.price_type = price_type;
	}
 
	


}
