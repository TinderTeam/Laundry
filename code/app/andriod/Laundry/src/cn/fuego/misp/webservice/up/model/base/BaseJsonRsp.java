package cn.fuego.misp.webservice.up.model.base;

import java.io.Serializable;

import cn.fuego.misp.constant.MISPErrorMessageConst;


/**
 * 
* @ClassName: BaseJsonRsp 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:57:45 
*
 */
public class BaseJsonRsp implements Serializable
{
	protected int errorCode = MISPErrorMessageConst.SUCCESS;
	protected String errorMsg;
	public int getErrorCode()
	{
		return errorCode;
	}
	public void setErrorCode(int errorCode)
	{
		this.errorCode = errorCode;
	}
	public String getErrorMsg()
	{
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg)
	{
		this.errorMsg = errorMsg;
	}

	 
	
}
