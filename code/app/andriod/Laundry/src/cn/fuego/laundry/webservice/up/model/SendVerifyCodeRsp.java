package cn.fuego.laundry.webservice.up.model;

import cn.fuego.misp.webservice.up.model.base.BaseJsonRsp;

public class SendVerifyCodeRsp extends BaseJsonRsp
{
	private String verifyCode;

	public String getVerifyCode()
	{
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode)
	{
		this.verifyCode = verifyCode;
	}
	
	

}
