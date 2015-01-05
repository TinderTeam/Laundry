package cn.fuego.laundry.cache;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.webservice.up.model.base.CustomerJson;
import cn.fuego.misp.webservice.up.model.base.UserJson;

public class AppCache
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	private UserJson user;
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
	
	public void clear()
	{
		user = null;
		customer = null;
				
	}

	public CustomerJson getCustomer()
	{
		return customer;
	}

	public void setCustomer(CustomerJson customer)
	{
		this.customer = customer;
	}

	public UserJson getUser()
	{
		return user;
	}

	public void setUser(UserJson user)
	{
		this.user = user;
	}
	
	
	 
}
