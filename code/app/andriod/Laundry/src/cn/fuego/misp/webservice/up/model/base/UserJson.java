/**   
* @Title: UserJson.java 
* @Package cn.fuego.smart.home.webservice.from.client.model.base 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-1 下午12:13:54 
* @version V1.0   
*/ 
package cn.fuego.misp.webservice.up.model.base;

import java.util.Date;

 /** 
 * @ClassName: UserJson 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-1 下午12:13:54 
 *  
 */
public class UserJson
{
	private int user_id;
	private String password;
	private String user_name;
	private int role_id;
	private int company_id;
	private String reg_date;
	public int getUser_id()
	{
		return user_id;
	}
	
	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}
	public String getPassword()
	{
		return password;
	}
	
	public int getCompany_id()
	{
		return company_id;
	}

	public void setCompany_id(int company_id)
	{
		this.company_id = company_id;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getUser_name()
	{
		return user_name;
	}
	public void setUser_name(String user_name)
	{
		this.user_name = user_name;
	}
	public int getRole_id()
	{
		return role_id;
	}
	public void setRole_id(int role_id)
	{
		this.role_id = role_id;
	}
	public String getReg_date()
	{
		return reg_date;
	}
	public void setReg_date(String reg_date)
	{
		this.reg_date = reg_date;
	}
 
 
	
}
