package cn.fuego.misp.ui.common.edit;

import java.io.Serializable;
import java.util.regex.Pattern;

import cn.fuego.common.util.validate.ValidatorUtil;

public class MispEditParameter implements Serializable
{
	private String tilteName;
	private String pointOut;
	private String dataKey;
	private String dataValue;
	private String dataType;
	private String dataRule;
	private String errorMsg;
	
	public boolean isValid(String value)
	{
		if(ValidatorUtil.isEmpty(dataRule))
		{
			return true;
		}
		return Pattern.matches(dataRule,value);  
 	}
	public String getTilteName()
	{
		return tilteName;
	}
	public void setTilteName(String tilteName)
	{
		this.tilteName = tilteName;
	}
	public String getPointOut()
	{
		return pointOut;
	}
	public void setPointOut(String pointOut)
	{
		this.pointOut = pointOut;
	}
	public String getDataKey()
	{
		return dataKey;
	}
	public void setDataKey(String dataKey)
	{
		this.dataKey = dataKey;
	}
	public String getDataValue()
	{
		return dataValue;
	}
	public void setDataValue(String dataValue)
	{
		this.dataValue = dataValue;
	}
	public String getDataType()
	{
		return dataType;
	}
	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}
	public String getDataRule()
	{
		return dataRule;
	}
	public void setDataRule(String dataRule)
	{
		this.dataRule = dataRule;
	}
	public String getErrorMsg()
	{
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg)
	{
		this.errorMsg = errorMsg;
	}
	

}
