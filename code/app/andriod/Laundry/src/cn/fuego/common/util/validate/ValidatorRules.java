package cn.fuego.common.util.validate;

public class ValidatorRules
{
	public static String isPhone()
	{
		return "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";
	}

}
