package cn.fuego.misp.ui.model;

import java.io.Serializable;
import java.util.List;

public class ImageDisplayInfo implements Serializable
{
	private String tilteName;
	private String url;
	private List<String> imageList;
	

	
	
	public List<String> getImageList()
	{
		return imageList;
	}
	public void setImageList(List<String> imageList)
	{
		this.imageList = imageList;
	}
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
