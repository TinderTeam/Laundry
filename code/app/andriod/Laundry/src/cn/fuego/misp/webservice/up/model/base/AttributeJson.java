package cn.fuego.misp.webservice.up.model.base;

import java.io.Serializable;

public class AttributeJson implements Serializable
{
	private String attrKey;
	private String attrValue;
	public String getAttrKey()
	{
		return attrKey;
	}
	public void setAttrKey(String attrKey)
	{
		this.attrKey = attrKey;
	}
	public String getAttrValue()
	{
		return attrValue;
	}
	public void setAttrValue(String attrValue)
	{
		this.attrValue = attrValue;
	}
	
	

}
