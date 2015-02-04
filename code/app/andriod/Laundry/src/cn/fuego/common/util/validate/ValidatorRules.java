package cn.fuego.common.util.validate;

import java.util.regex.Pattern;

public class ValidatorRules
{
	private static String FUEGO_RULE_LENGTH = "fuego_length:";
	public static String isPhone()
	{
		return "^(13|15|18|17)\\d{9}$|(^((\\(\\d{2,3}\\))|(\\d{3}\\-))?(\\(0\\d{2,3}\\)|0\\d{2,3}-)?[1-9]\\d{6,7}(\\-\\d{1,4})?$)";
	}
	
	public static String isEmpty()
	{
		String rule =FUEGO_RULE_LENGTH+1+","+Integer.MAX_VALUE;
		return rule;
	}
	
	public static String isLength(int minLenght,int maxLength)
	{
		String rule =FUEGO_RULE_LENGTH+minLenght+","+maxLength;
		return rule;
	}
	public static boolean isValid(String dataRule,String value)
	{
		if(ValidatorUtil.isEmpty(dataRule))
		{
			return true;
		}
		if(dataRule.startsWith(FUEGO_RULE_LENGTH))
		{
			return lengthRuleValidator(dataRule, value);
		}
		return Pattern.matches(dataRule,value);  
 	}

	private static boolean lengthRuleValidator(String dataRule, String value)
	{
		String pStr = dataRule.replaceAll(FUEGO_RULE_LENGTH, "");
		String[] pAry = pStr.split(",");
		if(pAry.length==2)
		{
			int length = length(value);
			if(length>Integer.valueOf(pAry[1]) || length<Integer.valueOf(pAry[0]))
			{
				return false;
			}
			return true;
		}
		return false;
	}
	
	public static int length(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
        	if(i<value.length()-2)
        	{
                String temp = value.substring(i, i + 2);
                /* 判断是否为中文字符 */
                if (temp.matches(chinese)) {
                    /* 中文字符长度为2 */
                    valueLength += 1;
                    i++;
                    i++;
                } else {
                    /* 其他字符长度为1 */
                    valueLength += 1;
                }
        	}
        	else
        	{
        		 valueLength += 1;
        	}

        }
        return valueLength;
    }

	public static String isMobile()
	{
		// TODO Auto-generated method stub
		return "^(13|15|18|17)\\d{9}$";
	}

}
