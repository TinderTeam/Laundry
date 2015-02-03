package cn.fuego.misp.ui.common.edit;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cn.fuego.common.util.validate.ValidatorRules;
import cn.fuego.common.util.validate.ValidatorUtil;

public class MispEditParameter implements Serializable
{
	private String tilteName;
	private String pointOut;
	private String dataKey;
	private String dataValue;
	private String dataType;
	
	private Map<String,String> ruleMap = new HashMap<String,String>();
 	private String errorMsg;
	
	
	public Map<String, String> getRuleMap()
	{
		return ruleMap;
	}
	public void setRuleMap(Map<String, String> ruleMap)
	{
		this.ruleMap = ruleMap;
	}
	public boolean isValid(String value)
	{
		if(null == ruleMap || ruleMap.isEmpty())
		{
			return true;
		}
		
		for(String key:ruleMap.keySet())
		{
			if(!ValidatorUtil.isEmpty(key))
			{
				if(!ValidatorRules.isValid(key, value))
				{
					this.errorMsg = ruleMap.get(key);
					return false;
				}
 			}
		}

		return true;
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
 
	public String getErrorMsg()
	{
		return errorMsg;
	}
 
	

}
