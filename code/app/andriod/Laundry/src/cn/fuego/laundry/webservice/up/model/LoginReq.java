package cn.fuego.laundry.webservice.up.model;

import cn.fuego.misp.webservice.up.model.base.BaseJsonReq;


/**
 * 
* @ClassName: LoginReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:59:19 
*
 */
public class LoginReq extends BaseJsonReq
{
	private String user_name;		//用户名
	private String password;		//密码
	public String getUser_name()
	{
		return user_name;
	}
	public void setUser_name(String user_name)
	{
		this.user_name = user_name;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	
 
}
