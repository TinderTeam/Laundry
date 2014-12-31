package cn.fuego.laundry.ui.util;

import cn.fuego.misp.service.MemoryCache;

public class DataConvertUtil
{
	public static String getAbsUrl(String url)
	{
		return  MemoryCache.getHostUrl() + "/copydp/Public/uploads/img/" +url;
	}
}
