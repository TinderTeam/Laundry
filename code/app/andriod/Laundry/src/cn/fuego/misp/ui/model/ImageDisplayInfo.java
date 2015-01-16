package cn.fuego.misp.ui.model;

import java.io.Serializable;

public class ImageDisplayInfo implements Serializable
{
	private String tilteName;
	private String url;
	public String getTilteName()
	{
		return tilteName;
	}
	public void setTilteName(String tilteName)
	{
		this.tilteName = tilteName;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	
	

}
