package cn.fuego.laundry.webservice.up.model.base;

import java.util.Date;

/**
 * 
 * @author jun
 *
 */
public class CustomerJson
{
	private int user_id;
	private String customer_name;
	private String phone;
	private String addr;
	private String birthday;
	private int score;  //车牌号
	private String level;
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
	
	

}
