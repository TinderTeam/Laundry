package cn.fuego.laundry.cache;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.ui.cart.CartProduct;
import cn.fuego.laundry.webservice.up.model.GetCustomerReq;
import cn.fuego.laundry.webservice.up.model.GetCustomerRsp;
import cn.fuego.laundry.webservice.up.model.base.CustomerJson;
import cn.fuego.laundry.webservice.up.model.base.DeliveryInfoJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.dao.SharedPreUtil;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.webservice.up.model.GetCompanyReq;
import cn.fuego.misp.webservice.up.model.GetCompanyRsp;
import cn.fuego.misp.webservice.up.model.base.CompanyJson;
import cn.fuego.misp.webservice.up.model.base.UserJson;

public class AppCache
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	
	public static String PAY_NOTIFY_URL = MemoryCache.getWebContextUrl() + "/index.php/AlipayNotify/GetNotify";

	private UserJson user;
	private CustomerJson customer;
	private CompanyJson company;
	private static AppCache instance;
	private  int company_id = 1;
 
	
	public static final String USER_CACHE="user";
	public static final String CUSTOMER_CACHE="customer";
	public static final String TOKEN_CACHE="token";
	

	
	
 

	public CompanyJson getCompany()
	{
		return company;
	}

	public int getCompany_id()
	{
		return company_id;
	}
 
 
 

	private AppCache()
	{
		 company_id = 1;
		 
		  
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
		MemoryCache.setToken(null);
		user = null;
		customer = null;
		SharedPreUtil.getInstance().delete(USER_CACHE);
		SharedPreUtil.getInstance().delete(CUSTOMER_CACHE);
		SharedPreUtil.getInstance().delete(TOKEN_CACHE);
				
	}

	public CustomerJson getCustomer()
	{
		return customer;
	}
	public void update(CustomerJson customer)
	{
		this.customer = customer;
		SharedPreUtil.getInstance().put(CUSTOMER_CACHE, customer);

	}
	public void update(String token,UserJson user,CustomerJson customer)
	{
 
		SharedPreUtil.getInstance().put(USER_CACHE, user);
		SharedPreUtil.getInstance().put(CUSTOMER_CACHE, customer);
		SharedPreUtil.getInstance().put(TOKEN_CACHE,token );
		load();

	}

	public void load()
	{
		this.user =  (UserJson) SharedPreUtil.getInstance().get(USER_CACHE);
		this.customer = (CustomerJson) SharedPreUtil.getInstance().get(CUSTOMER_CACHE);
		MemoryCache.setToken((String) SharedPreUtil.getInstance().get(TOKEN_CACHE));
	    CartProduct.getInstance().setDefaultOrderInfo();

		
	}
 
	public UserJson getUser()
	{
		return user;
	}

 
	public void loadCompany()
	{
		GetCompanyReq req = new GetCompanyReq();
		req.setObj(this.company_id);
		WebServiceContext.getInstance().getSystemManageRest(new MispHttpHandler()
		{
			@Override
			public void handle(MispHttpMessage message)
			{
				if(message.isSuccess())
				{
					GetCompanyRsp rsp = (GetCompanyRsp) message.getMessage().obj;
					company = rsp.getObj();
				}
				else
				{
					log.error("can not get the company information");
				}
			}
			
		}).getCompany(req);
	}
	
	
	
	public DeliveryInfoJson getDefuatDelivery()
	{
		DeliveryInfoJson info = new DeliveryInfoJson();
 
		if(null != AppCache.getInstance().getCustomer())
		{
			info.setPhone(AppCache.getInstance().getCustomer().getPhone());
			info.setTake_addr(AppCache.getInstance().getCustomer().getAddr());
			info.setContact_name((AppCache.getInstance().getCustomer().getCustomer_name()));
		}
		else
		{
			log.error("can not get the customer");
		}
		return info;
	}

	

	
	 
}
