package cn.fuego.common.util;

import java.io.UnsupportedEncodingException;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.file.PropertyReader;

public class SystemConfigInfo
{
    private static final FuegoLog log = FuegoLog.getLog(SystemConfigInfo.class);

	public static String getSystemRootPath()
	{
		String path = SystemConfigInfo.class.getResource("/").getPath();
		try
		{
			path = java.net.URLDecoder.decode(path,"utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			log.error("decode failed",e);
		}  
		return path;
	}
	
	public static String getConfigItem(String itemName)
	{
		return  PropertyReader.getInstance().getPropertyByName(itemName);
	}

	public static String getProductName()
	{
		return PropertyReader.getInstance().getPropertyByName(SystemConfigNameConst.PRODUCT_NAME);
	}
	
	public static String getDefaultPassword()
	{
		return PropertyReader.getInstance().getPropertyByName(SystemConfigNameConst.USER_DEFAULT_PASSWOARD);
	}
	public static String getTemplatePath()
	{
		return  getSystemRootPath() + PropertyReader.getInstance().getPropertyByName(SystemConfigNameConst.TEMPLATE_PATH);
		
	}
	public static String getDevicePort()
	{
		return PropertyReader.getInstance().getPropertyByName(SystemConfigNameConst.DEVICE_PORT);
	}

 
}
