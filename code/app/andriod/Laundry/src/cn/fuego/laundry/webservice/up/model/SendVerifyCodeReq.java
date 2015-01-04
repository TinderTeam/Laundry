package cn.fuego.laundry.webservice.up.model;

import cn.fuego.misp.webservice.up.model.base.BaseJsonReq;

public class SendVerifyCodeReq extends BaseJsonReq
{
	private String phone_num;

	public String getPhone_num()
	{
		return phone_num;
	}

	public void setPhone_num(String phone_num)
	{
		this.phone_num = phone_num;
	}
	
	
	

}
