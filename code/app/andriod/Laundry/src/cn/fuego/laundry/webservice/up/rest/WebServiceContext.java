package cn.fuego.laundry.webservice.up.rest;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.HttpListener;
import cn.fuego.misp.service.http.MispProxyFactory;
import cn.fuego.misp.webservice.up.rest.MispUserManageRest;

public class WebServiceContext
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	private static WebServiceContext instance;

	public static String hostURL = MemoryCache.getHostUrl()+"/Laundry";
	
	private WebServiceContext()
	{
		log.info("the host and base url is "+hostURL);

	}

	public static synchronized WebServiceContext getInstance()
	{
		if (null == instance)
		{
			instance = new WebServiceContext();
		}
		return instance;
	}
 
	private HttpClient getHttpClient()
	{
		HttpClient httpClient = new DefaultHttpClient();
	 
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
 
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
		return httpClient;

	}

	public MispUserManageRest getUserManageRest(HttpListener handler)
	{
 
		MispUserManageRest rest = MispProxyFactory.create( hostURL,MispUserManageRest.class, getHttpClient(),handler);

		return rest;
	}	
	
	public CustomerManageRest getCustomerManageRest(HttpListener handler)
	{
 
		CustomerManageRest rest = MispProxyFactory.create( hostURL,CustomerManageRest.class, getHttpClient(),handler);

		return rest;
	}	
 
	public ProductManageRest getProductManageRest(HttpListener handler)
	{
 
		ProductManageRest rest = MispProxyFactory.create( hostURL,ProductManageRest.class, getHttpClient(),handler);

		return rest;
	}
	
	 
		public OrderManageRest getOrderManageRest(HttpListener handler)
		{
	 
			OrderManageRest rest = MispProxyFactory.create( hostURL,OrderManageRest.class, getHttpClient(),handler);

			return rest;
		}
 

}
