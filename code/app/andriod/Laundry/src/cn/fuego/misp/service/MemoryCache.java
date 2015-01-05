package cn.fuego.misp.service;

import cn.fuego.common.util.validate.ValidatorUtil;



public class MemoryCache
{
	private static String token;

 	
	private static String version = "0.1";
	private static String serverIp =  "120.24.217.173";
	private static String serverPort= "9000";

	//private static String serverIp = "192.168.0.105";//"120.24.217.173";
	//private static String serverPort= "7000";
 

	public static String getToken()
	{
		return token;
	}
	
	public static boolean isLogined()
	{
		if(ValidatorUtil.isEmpty(token))
		{
			return false;
		}
		return true;
	}

	public static void setToken(String token)
	{
		MemoryCache.token = token;
	}

	public static String getVersion()
	{
		return version;
	}

 

	public static String getServerIp()
	{
		return serverIp;
	}

 

	public static String getServerPort()
	{
		return serverPort;
	}
	
	public static String getHostUrl()
	{
		return "http://"+MemoryCache.getServerIp()+":"+MemoryCache.getServerPort();
	}
 
	
 

}
