package cn.fuego.misp.tool;

import cn.fuego.misp.constant.MISPErrorMessageConst;

public class MispLocation
{
	private int errorCode = MISPErrorMessageConst.SUCCESS;
	
	
	public int getErrorCode()
	{
		return errorCode;
	}
	public void setErrorCode(int errorCode)
	{
		this.errorCode = errorCode;
	}
	private double latitude;
	private double lontitude;
	private String addr;
	private String province;
	private String city;
	public double getLatitude()
	{
		return latitude;
	}
	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}
	public double getLontitude()
	{
		return lontitude;
	}
	public void setLontitude(double lontitude)
	{
		this.lontitude = lontitude;
	}
	public String getAddr()
	{
		return addr;
	}
	public void setAddr(String addr)
	{
		this.addr = addr;
	}
	public String getProvince()
	{
		return province;
	}
	public void setProvince(String province)
	{
		this.province = province;
	}
	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}
	@Override
	public String toString()
	{
		return "MispLocation [latitude=" + latitude + ", lontitude="
				+ lontitude + ", addr=" + addr + ", province=" + province
				+ ", city=" + city + "]";
	}
	
	
	

}
