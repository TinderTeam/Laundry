package cn.fuego.misp.webservice.up.model.base;

import java.io.Serializable;

import cn.fuego.laundry.cache.AppCache;
import cn.fuego.misp.constant.ClientTypeEnum;
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
 	protected int app_id = AppCache.getInstance().getCompany_id();
	protected String clientType = ClientTypeEnum.ANDRIOD_CLIENT.getStrValue();		//
	protected int clientVersion = AppCache.getInstance().getVersionCode();	//
	public String getToken()
	{
		return token;
	}
	public void setToken(String token)
	{
		this.token = token;
	}
	public int getApp_id()
	{
		return app_id;
	}
	public void setApp_id(int app_id)
	{
		this.app_id = app_id;
	}
 
 
	public String getClientType()
	{
		return clientType;
	}
	public void setClientType(String clientType)
	{
		this.clientType = clientType;
	}
	public int getClientVersion()
	{
		return clientVersion;
	}
	public void setClientVersion(int clientVersion)
	{
		this.clientVersion = clientVersion;
	}
 
 

}
