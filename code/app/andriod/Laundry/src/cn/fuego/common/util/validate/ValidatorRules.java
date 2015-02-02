package cn.fuego.common.util.validate;

public class ValidatorRules
{
	public static String isPhone()
	{
		return "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";
	}
	
	public static String isEmpty()
	{
		return "";
	}
	
	public static String isLenght(int minLenght,int maxLength)
	{
		String rule = "^[\\u4e00-\\u9fa5]{"+minLenght+","+maxLength+"}$|^[\\dA-Za-z_]{"+minLenght+","+maxLength+"}$";
		return rule;
	}

}
