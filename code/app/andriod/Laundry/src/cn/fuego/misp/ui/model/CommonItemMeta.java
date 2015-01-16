package cn.fuego.misp.ui.model;

import java.io.Serializable;

public class CommonItemMeta implements Serializable
{
	private String title;
	private int layoutType;
	private Integer titleIamge = null; //if no title image the value is empty
	private Object content;
	
	public CommonItemMeta()
	{
		
	}
	public CommonItemMeta(int type,String title,Object content)
	{
		this.layoutType = type;
		this.title = title;
		this.content = content;
	}
	public CommonItemMeta(int type,String title,Object content,Integer titleImage)
	{
		this.layoutType = type;
		this.title = title;
		this.content = content;
		this.titleIamge =titleIamge;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public int getLayoutType()
	{
		return layoutType;
	}
	public void setLayoutType(int layoutType)
	{
		this.layoutType = layoutType;
	}
	public Integer getTitleIamge()
	{
		return titleIamge;
	}
	public void setTitleIamge(Integer titleIamge)
	{
		this.titleIamge = titleIamge;
	}
	public Object getContent()
	{
		return content;
	}
	public void setContent(Object content)
	{
		this.content = content;
	}
	
	
	

}
