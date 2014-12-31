/**   
 * @Title: MispClientInvoker.java 
 * @Package cn.fuego.smart.home 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2014-11-13 上午9:19:59 
 * @version V1.0   
 */
package cn.fuego.misp.service.http;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.Set;

import javax.ws.rs.Path;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.specimpl.UriBuilderImpl;
import org.jboss.resteasy.util.IsHttpMethod;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.misp.constant.MISPErrorMessageConst;

/**
 * @ClassName: MispClientInvoker
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-13 上午9:19:59
 * 
 */

public class MispHttpClientInvoker extends Thread
{
	
	private FuegoLog log = FuegoLog.getLog(getClass());

	private static final String GET_METHOD = "GET";
	private static final String POST_METHOD = "POST";
	
	private static final String CODE_WITH_UTF_8 = "UTF-8";
 	protected UriBuilderImpl uri;
	protected Method method;
	protected Object[] argObjects;
	protected HttpClient httpClient;
	protected HttpListener handler;
 
	public MispHttpClientInvoker(URI baseUri,Class<?> calzz,Method method,HttpClient httpClient, HttpListener handler)
	{
		this.uri = new UriBuilderImpl();
		this.handler = handler;
		uri.uri(baseUri);
		if (calzz.isAnnotationPresent(Path.class)) 
		{
			uri.path(calzz);
		}
		if (method.isAnnotationPresent(Path.class))
		{
			uri.path(method);
		}
		 
		this.method = method;
		this.httpClient = httpClient;
	}
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		MispHttpMessage msg = new MispHttpMessage();

		Object rspObj = null;
		try
		{
			
		   HttpUriRequest httpMethod = getHttpMethod(argObjects[0]);
			
			
			
 			HttpResponse response = httpClient.execute(httpMethod); // 发起GET请求
			
			
			String content = EntityUtils.toString(response.getEntity(), CODE_WITH_UTF_8);
			
			log.info("the response is " + content);
			
			ObjectMapper mapper = new ObjectMapper();
			
			rspObj = mapper.readValue(content,method.getReturnType());
			msg.getMessage().obj = rspObj;
			
			 
		} catch (Exception e)
		{
			log.error("call http failed.",e);
			msg.getMessage().what = MISPErrorMessageConst.NET_FAIL;
		}
		handler.sendMessage(msg);
		
	}



	public MispHttpClientInvoker invoke(Object[] args)
	{
		this.argObjects = args;
		return this;
//		
//		Object rspObj = null;
//		try
//		{
//			
//		   HttpUriRequest httpMethod = getHttpMethod(args[0]);
//			
//			
//			
// 			HttpResponse response = httpClient.execute(httpMethod); // 发起GET请求
//			
//			
//			String content = EntityUtils.toString(response.getEntity(), CODE_WITH_UTF_8);
//			
//			ObjectMapper mapper = new ObjectMapper();
//			
//			rspObj = mapper.readValue(content,method.getReturnType());
//			 
//		} catch (Exception e)
//		{
//			throw new MISPException("call http failed",e);
//		}
//
//		
//		return rspObj;
	}
	
	private HttpUriRequest  getHttpMethod(Object args)
	{
		Set<String> httpMethods = IsHttpMethod.getHttpMethods(method);
	    if (httpMethods == null || httpMethods.size() != 1)
	    {
	         throw new RuntimeException("You must use at least one, but no more than one http method annotation on: " + method.toString());
	    }
	    
	    String absPath = this.getAbsUrlPath();

		if(GET_METHOD.equals(httpMethods.iterator().next()))
		{
 
			HttpGet getMethod = new HttpGet(absPath);
			return getMethod;
		}
		else if(POST_METHOD.equals(httpMethods.iterator().next()))
		{
			ObjectMapper mapper = new ObjectMapper();
			
			ByteArrayEntity se = null;
			try
			{
				//se = new StringEntity(mapper.writeValueAsString(args));
				String json = mapper.writeValueAsString(args);
				
				 se = new ByteArrayEntity(json.getBytes()); 
				//se = new StringEntity(json,CODE_WITH_UTF_8);
	            //se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                se.setContentType("application/json");
      
                se.setContentEncoding(CODE_WITH_UTF_8);
			} catch (Exception e)
			{
				log.error("covert object to json failed.object is " + args,e);
			} 
        
			HttpPost postMethod = new HttpPost(absPath);

			postMethod.setHeader("Content-Type", "application/json;charset=utf-8");
			postMethod.setEntity(se);
			return postMethod;
		}
		
		return null;
		
	}
	private String getAbsUrlPath()
	{
		String absPath = uri.getScheme() + "://" + uri.getHost() +":"+ uri.getPort() + uri.getPath();
		return absPath;
	}

}
