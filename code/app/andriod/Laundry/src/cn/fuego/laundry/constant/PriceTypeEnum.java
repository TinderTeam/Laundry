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
public enum PriceTypeEnum
{	
	FIX_PRICE("固定价格",1),  
	FLOAT_PRICE("浮动价格",2);
	private String strValue;
	private int intValue;
	
	private PriceTypeEnum(String strValue, int intValue)
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


	public static PriceTypeEnum getEnumByInt(int intValue)
	{
		for (PriceTypeEnum c : PriceTypeEnum.values())
		{
			if (intValue == c.intValue)
			{
				return c;
			}
		}
		return null;
	}
	public static PriceTypeEnum getEnumByStr(String strValue)
	{
		for (PriceTypeEnum c : PriceTypeEnum.values())
		{
			if (strValue.equals(c.strValue) )
			{
				return c;
			}
		}
		return null;
	}	 

}
