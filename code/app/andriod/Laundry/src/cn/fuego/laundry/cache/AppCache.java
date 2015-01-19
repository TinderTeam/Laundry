package cn.fuego.laundry.cache;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.ui.cart.CartProduct;
import cn.fuego.laundry.webservice.up.model.GetCustomerReq;
import cn.fuego.laundry.webservice.up.model.GetCustomerRsp;
import cn.fuego.laundry.webservice.up.model.base.CustomerJson;
import cn.fuego.laundry.webservice.up.model.base.DeliveryInfoJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.webservice.up.model.GetCompanyReq;
import cn.fuego.misp.webservice.up.model.GetCompanyRsp;
import cn.fuego.misp.webservice.up.model.base.CompanyJson;
import cn.fuego.misp.webservice.up.model.base.UserJson;

public class AppCache
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	private UserJson user;
	private CustomerJson customer;
	private CompanyJson company;
	private static AppCache instance;
	private  int company_id = 1;
	
	private String versionNname;
	private int versionCode;
	

	
	
 

	public CompanyJson getCompany()
	{
		return company;
	}

	public int getCompany_id()
	{
		return company_id;
	}
 
	public String getVersionNname()
	{
		return versionNname;
	}

	public int getVersionCode()
	{
		return versionCode;
	}
	
	

	public void setVersionNname(String versionNname)
	{
		this.versionNname = versionNname;
	}

	public void setVersionCode(int versionCode)
	{
		this.versionCode = versionCode;
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
		user = null;
		customer = null;
				
	}

	public CustomerJson getCustomer()
	{
		return customer;
	}
	public void loadLoginInfo(CustomerJson customer)
	{
		this.customer = customer;
	}
	public void loadLoginInfo(UserJson user,CustomerJson customer)
	{
		this.user = user;
		this.customer = customer;
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
