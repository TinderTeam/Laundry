package cn.fuego.laundry.webservice.up.model.base;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jun
 *
 */
public class CustomerJson implements Serializable,Cloneable
{
	
	private int user_id;
	private int company_id;
	private String user_name;
	private String customer_name;
	private String customer_email;
	private String card_number;
	private String customer_sex;
	private String customer_img;
	
	private String phone;
	private String addr;
	private String birthday;
	private int score;  //车牌号
	private String level;
	private String nickname;
	private String status;

	
	public int getCompany_id()
	{
		return company_id;
	}
	public void setCompany_id(int company_id)
	{
		this.company_id = company_id;
	}
	public int getUser_id()
	{
		return user_id;
	}
	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}
	public String getCustomer_name()
	{
		return customer_name;
	}
	public void setCustomer_name(String customer_name)
	{
		this.customer_name = customer_name;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getAddr()
	{
		return addr;
	}
	public void setAddr(String addr)
	{
		this.addr = addr;
	}
	public String getBirthday()
	{
		return birthday;
	}
	public void setBirthday(String birthday)
	{
		this.birthday = birthday;
	}
	public int getScore()
	{
		return score;
	}
	public void setScore(int score)
	{
		this.score = score;
	}
	public String getLevel()
	{
		return level;
	}
	public void setLevel(String level)
	{
		this.level = level;
	}
	public String getUser_name()
	{
		return user_name;
	}
	public void setUser_name(String user_name)
	{
		this.user_name = user_name;
	}
	public String getNickname()
	{
		return nickname;
	}
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public String getCustomer_email()
	{
		return customer_email;
	}
	public void setCustomer_email(String customer_email)
	{
		this.customer_email = customer_email;
	}
	public String getCard_number()
	{
		return card_number;
	}
	public void setCard_number(String card_number)
	{
		this.card_number = card_number;
	}
	public String getCustomer_sex()
	{
		return customer_sex;
	}
	public void setCustomer_sex(String customer_sex)
	{
		this.customer_sex = customer_sex;
	}
	public String getCustomer_img()
	{
		return customer_img;
	}
	public void setCustomer_img(String customer_img)
	{
		this.customer_img = customer_img;
	}
	@Override
	public CustomerJson clone()
	{
		CustomerJson p = null;
		// TODO Auto-generated method stub
		try
		{
			p =(CustomerJson) super.clone();
		} catch (CloneNotSupportedException e)
		{
			// TODO Auto-generated catch block
			p = new CustomerJson();
			e.printStackTrace();
		}
		return p;
	}
	
	

}
