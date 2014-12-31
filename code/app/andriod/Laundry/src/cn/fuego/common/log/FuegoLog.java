package cn.fuego.common.log;

import android.util.Log;


public class FuegoLog
{

	private Class clazz;
	
	private FuegoLog(Class clazz)
	{
		this.clazz = clazz;
	}
	public static FuegoLog getLog(Class clazz)
	{
		FuegoLog instance = new FuegoLog(clazz);
		return instance;
	}

	public void info(String message)
	{
		Log.i(clazz.toString(), message);
	}

	public void info(String message,Throwable e)
	{
		Log.i(clazz.toString(), message, e);
	}
	
	public void warn(String message)
	{
		Log.w(clazz.toString(), message);
	}
	
	public void warn(String message,Throwable e)
	{
		Log.w(clazz.toString(), message, e);
	}
	public void error(String message)
	{
		Log.e(clazz.toString(), message);
	}

	public void error(String message,Throwable e)
	{
		Log.e(clazz.toString(), message, e);
	}


	public void trace(String message)
	{
		Log.e(clazz.toString(), message);
	}
	
	public void trace(String message,Throwable e)
	{
		Log.e(clazz.toString(), message, e);
	}

}
