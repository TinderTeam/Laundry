package cn.fuego.laundry.cache;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.webservice.up.model.base.CustomerJson;

public class AppCache
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	private  CustomerJson customer;
	private static AppCache instance;
	
	private AppCache()
	{
		 
	}
	
	public synchronized static AppCache getInstance()
	{
		if(null == instance)
		{
			instance = new AppCache();
		}
		return instance;
		
	}

	public CustomerJson getCustomer()
	{
		return customer;
	}

	public void setCustomer(CustomerJson customer)
	{
		this.customer = customer;
	}
	
	 
}
