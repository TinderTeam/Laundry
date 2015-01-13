/**   
* @Title: UserTypeEnumj.java 
* @Package cn.fuego.remote.medical.constant 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-27 下午07:00:09 
* @version V1.0   
*/ 
package cn.fuego.laundry.constant;

/** 
* @ClassName: UserTypeEnum 
* @Description: TODO
* @author Aether
* @date 2014-11-4 上午11:32:38 
*  
*/ 
public enum OrderTypeEnum
{	
	NORMAL_ORDER("正常下单",1),  
	DIRECT_ORDER("直接下单",2);
	private String strValue;
	private int intValue;
	
	private OrderTypeEnum(String strValue, int intValue)
	{
		this.strValue = strValue;
		this.intValue = intValue;
	}
 
	
	public String getStrValue()
	{
		return strValue;
	}


	public void setStrValue(String strValue)
	{
		this.strValue = strValue;
	}


	public int getIntValue()
	{
		return intValue;
	}


	public void setIntValue(int intValue)
	{
		this.intValue = intValue;
	}


	public static OrderTypeEnum getEnumByInt(int intValue)
	{
		for (OrderTypeEnum c : OrderTypeEnum.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}
	public static OrderTypeEnum getEnumByStr(String strValue)
	{
		for (OrderTypeEnum c : OrderTypeEnum.values())
		{
			if (strValue.equals(c.strValue) )
			{
				return c;
			}
		}
		return null;
	}	 

}
