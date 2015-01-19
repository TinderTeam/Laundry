package cn.fuego.misp.webservice.up.model;

import cn.fuego.misp.webservice.up.model.base.BaseJsonReq;


public class UserRegisterReq extends BaseJsonReq
{
	private String user_name;
	private String password;
	private String verifyCode;
	private String addr = "未设置";
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
	public String getAddr()
	{
		return addr;
	}
	public void setAddr(String addr)
	{
		this.addr = addr;
	}
	public String getVerifyCode()
	{
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode)
	{
		this.verifyCode = verifyCode;
	}
	
	
	

}
