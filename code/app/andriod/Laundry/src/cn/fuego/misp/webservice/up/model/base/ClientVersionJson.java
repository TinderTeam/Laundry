package cn.fuego.misp.webservice.up.model.base;

import java.io.Serializable;

public class ClientVersionJson implements Serializable
{
	private int version_id;
	private int company_id;
	private String app_name;
	private String apk_name;
	private String version_name;
	private int version_code;
	private String client_type;
	private String apk_url;
	private String qr_code;
	private String version_status; // new, old
	public int getVersion_id()
	{
		return version_id;
	}
	public void setVersion_id(int version_id)
	{
		this.version_id = version_id;
	}
	public int getCompany_id()
	{
		return company_id;
	}
	public void setCompany_id(int company_id)
	{
		this.company_id = company_id;
	}
	public String getApp_name()
	{
		return app_name;
	}
	public void setApp_name(String app_name)
	{
		this.app_name = app_name;
	}
	public String getApk_name()
	{
		return apk_name;
	}
	public void setApk_name(String apk_name)
	{
		this.apk_name = apk_name;
	}
 
	public String getVersion_name()
	{
		return version_name;
	}
	public void setVersion_name(String version_name)
	{
		this.version_name = version_name;
	}
	public int getVersion_code()
	{
		return version_code;
	}
	public void setVersion_code(int version_code)
	{
		this.version_code = version_code;
	}
	public String getClient_type()
	{
		return client_type;
	}
	public void setClient_type(String client_type)
	{
		this.client_type = client_type;
	}
	public String getApk_url()
	{
		return apk_url;
	}
	public void setApk_url(String apk_url)
	{
		this.apk_url = apk_url;
	}
	public String getQr_code()
	{
		return qr_code;
	}
	public void setQr_code(String qr_code)
	{
		this.qr_code = qr_code;
	}
	public String getVersion_status()
	{
		return version_status;
	}
	public void setVersion_status(String version_status)
	{
		this.version_status = version_status;
	}
 
	
  
	
 }
