package cn.fuego.laundry.webservice.up.model.base;

import java.io.Serializable;

public class AdvertisementJson implements Serializable
{
	private int company_id;
	private int ad_id;

	private String ad_name;
	private String ad_info;
	private String ad_url;
	private String ad_img;
	public int getAd_id()
	{
		return ad_id;
	}
	public void setAd_id(int ad_id)
	{
		this.ad_id = ad_id;
	}
	public String getAd_name()
	{
		return ad_name;
	}
	public void setAd_name(String ad_name)
	{
		this.ad_name = ad_name;
	}
	public String getAd_info()
	{
		return ad_info;
	}
	public void setAd_info(String ad_info)
	{
		this.ad_info = ad_info;
	}
	public String getAd_url()
	{
		return ad_url;
	}
	public void setAd_url(String ad_url)
	{
		this.ad_url = ad_url;
	}
	public String getAd_img()
	{
		return ad_img;
	}
	public void setAd_img(String ad_img)
	{
		this.ad_img = ad_img;
	}
	public int getCompany_id()
	{
		return company_id;
	}
	public void setCompany_id(int company_id)
	{
		this.company_id = company_id;
	}
	
	

}
