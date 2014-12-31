/**   
 * @Title: MispClientProxy.java 
 * @Package cn.fuego.smart.home 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2014-11-13 上午12:38:16 
 * @version V1.0   
 */
package cn.fuego.misp.service.http;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;

import android.os.Handler;

/**
 * @ClassName: MispClientProxy
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-13 上午12:38:16
 * 
 */
public class MispHttpClientProxy implements InvocationHandler
{
	private Map<Method, MispHttpClientInvoker> methodMap = new HashMap<Method,MispHttpClientInvoker>();
	private Class<?> clazz;

	public MispHttpClientProxy(String base, Class<?> clazz, HttpClient httpClient, HttpListener handler)
	{

		this.clazz = clazz;
		for (Method method : clazz.getMethods())
		{
			MispHttpClientInvoker invoker = new MispHttpClientInvoker(createUri(base),
					clazz, method, httpClient,handler);
			methodMap.put(method, invoker);
		}
 
	}

	public static URI createUri(String base)
	{
		try
		{
			return new URI(base);
		} catch (URISyntaxException e)
		{
			throw new RuntimeException(e);
		}
	}

	public Class<?> getClazz()
	{
		return clazz;
	}

	public void setClazz(Class<?> clazz)
	{
		this.clazz = clazz;
	}

	public Object invoke(Object o, Method method, Object[] args)
			throws Throwable
	{
		// equals and hashCode were added for cases where the proxy is added to
		// collections. The Spring transaction management, for example, adds
		// transactional Resources to a Collection, and it calls equals and
		// hashCode.

		MispHttpClientInvoker clientInvoker = methodMap.get(method);
		if (clientInvoker == null)
		{
			if (method.getName().equals("equals"))
			{
				return this.equals(o);
			} else if (method.getName().equals("hashCode"))
			{
				return this.hashCode();
			} else if (method.getName().equals("toString")
					&& (args == null || args.length == 0))
			{
				return this.toString();
			} else if (method.getName().equals("getResteasyClientInvokers"))
			{
				return methodMap.values();
			}

		}

		if (clientInvoker == null)
		{
			throw new RuntimeException("Could not find a method for: " + method);
		}

		clientInvoker.invoke(args).start();
		return null;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || !(obj instanceof MispHttpClientProxy))
			return false;
		MispHttpClientProxy other = (MispHttpClientProxy) obj;
		if (other == this)
			return true;
		if (other.clazz != this.clazz)
			return false;
		return super.equals(obj);
	}

	@Override
	public int hashCode()
	{
		return clazz.hashCode();
	}

	public String toString()
	{
		return "Client Proxy for :" + clazz.getName();
	}
}
