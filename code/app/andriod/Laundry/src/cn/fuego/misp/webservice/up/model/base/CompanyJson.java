package cn.fuego.misp.webservice.up.model.base;

public class CompanyJson
{
	private int company_id;
	private String company_name;
	private String company_web_site;
	private String company_addr;
	private String company_desp;
	private String service_phone;
 	private int ios_version;
	private int andriod_version;
	
	
	public int getCompany_id()
	{
		return company_id;
	}
	public void setCompany_id(int company_id)
	{
		this.company_id = company_id;
	}
	public String getCompany_name()
	{
		return company_name;
	}
	public void setCompany_name(String company_name)
	{
		this.company_name = company_name;
	}
	public String getCompany_web_site()
	{
		return company_web_site;
	}
	public void setCompany_web_site(String company_web_site)
	{
		this.company_web_site = company_web_site;
	}
	public String getCompany_desp()
	{
		return company_desp;
	}
	public void setCompany_desp(String company_desp)
	{
		this.company_desp = company_desp;
	}
	public String getService_phone()
	{
		return service_phone;
	}
	public void setService_phone(String service_phone)
	{
		this.service_phone = service_phone;
	}
 
	public String getCompany_addr()
	{
		return company_addr;
	}
	public void setCompany_addr(String company_addr)
	{
		this.company_addr = company_addr;
	}
	public int getIos_version()
	{
		return ios_version;
	}
	public void setIos_version(int ios_version)
	{
		this.ios_version = ios_version;
	}
	public int getAndriod_version()
	{
		return andriod_version;
	}
	public void setAndriod_version(int andriod_version)
	{
		this.andriod_version = andriod_version;
	}
	
	

}
