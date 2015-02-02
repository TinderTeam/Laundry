package cn.fuego.laundry.cache;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.webservice.up.model.base.AdvertisementJson;

public class AdDataCache
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	private  List<AdvertisementJson> dataList = new ArrayList<AdvertisementJson>();
	private static AdDataCache instance;
	private AdDataCache()
	{
		 
	}
	
	public synchronized static AdDataCache getInstance()
	{
		if(null == instance)
		{
			instance = new AdDataCache();
		}
		return instance;
		
	}
	
	public boolean isEmpty()
	{
		if(ValidatorUtil.isEmpty(dataList))
		{
			return true;
		}
		return false;
	}
	public void init(List<AdvertisementJson> newData)
	{
		dataList.clear();
		if(!ValidatorUtil.isEmpty(newData))
		{
			dataList.addAll(newData);
		}
	}

	public List<AdvertisementJson> getDataList()
	{
		return dataList;
	}
	
	
	
}
