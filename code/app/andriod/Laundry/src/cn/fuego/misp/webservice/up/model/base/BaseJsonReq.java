package cn.fuego.misp.webservice.up.model.base;

import java.io.Serializable;

import cn.fuego.misp.service.MemoryCache;


/**
 * 
* @ClassName: BaseJsonReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:57:41 
*
 */
public class BaseJsonReq implements Serializable
{
 	protected String token = MemoryCache.getToken();
	protected String clientType;		//
	protected String clientVersion;	//
	
	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public String getClientType()
	{
		return clientType;
	}

	public void setClientType(String clientType)
	{
		this.clientType = clientType;
	}

	public String getClientVersion()
	{
		return clientVersion;
	}

	public void setClientVersion(String clientVersion)
	{
		this.clientVersion = clientVersion;
	}

 

}
