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
public enum PayOptionEnum
{	
	ONLINE_PAY("在线支付",1),  
	OFFLINE_PAY("送衣付款",2);
	private String strValue;
	private int intValue;
	
	private PayOptionEnum(String strValue, int intValue)
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


	public static PayOptionEnum getEnumByInt(int intValue)
	{
		for (PayOptionEnum c : PayOptionEnum.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}
	public static PayOptionEnum getEnumByStr(String strValue)
	{
		for (PayOptionEnum c : PayOptionEnum.values())
		{
			if (strValue.equals(c.strValue) )
			{
				return c;
			}
		}
		return null;
	}	 

}
