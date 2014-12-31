package cn.fuego.laundry.webservice.up.rest;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.HttpListener;
import cn.fuego.misp.service.http.MispProxyFactory;

public class WebServiceContext
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	private static WebServiceContext instance;

	public static String hostURL = MemoryCache.getHostUrl()+"/copydp/index.php";
	
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

	public UserManageRest getUserManageRest(HttpListener handler)
	{
 
		UserManageRest rest = MispProxyFactory.create( hostURL,UserManageRest.class, getHttpClient(),handler);

		return rest;
	}	
 
	public ProductManageRest getProductManageRest(HttpListener handler)
	{
 
		ProductManageRest rest = MispProxyFactory.create( hostURL,ProductManageRest.class, getHttpClient(),handler);

		return rest;
	}
 

}
