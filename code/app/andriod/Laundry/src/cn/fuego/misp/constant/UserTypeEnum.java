/**   
* @Title: UserTypeEnumj.java 
* @Package cn.fuego.remote.medical.constant 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-27 下午07:00:09 
* @version V1.0   
*/ 
package cn.fuego.misp.constant;

/** 
* @ClassName: UserTypeEnum 
* @Description: TODO
* @author Aether
* @date 2014-11-4 上午11:32:38 
*  
*/ 
public enum UserTypeEnum
{	
	TERMINAL("终端用户",1),  
	UNION("联防用户",2),
	REGION_ADMIN("区域管理员",3),
	LOW_ADMIN("普通管理员",88),
	ADMIN("超级管理员",99); 
	private String type;
	private int typeValue;
	
	private UserTypeEnum(String type, int typeValue)
	{
		this.type = type;
		this.typeValue = typeValue;
	}
	public String getType()
	{
		return type;
	}
	public int getTypeValue()
	{
		return typeValue;
	}

	
	public static UserTypeEnum getEnumByInt(int typeInt)
	{
		for (UserTypeEnum c : UserTypeEnum.values())
		{
			if (typeInt == c.typeValue)
			{
				return c;
			}
		}
		return null;
	}
	public static UserTypeEnum getEnumByStr(String typeStr)
	{
		for (UserTypeEnum c : UserTypeEnum.values())
		{
			if (typeStr.equals(c.type) )
			{
				return c;
			}
		}
		return null;
	}	 

}
