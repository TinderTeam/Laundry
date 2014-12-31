/**   
* @Title: ValidatorUtil.java 
* @Package cn.tinder.fuego.util 
* @Description: TODO
* @author Tang Jun   
* @date 2013-12-4 下午06:01:41 
* @version V1.0   
*/ 
package cn.fuego.common.util.validate;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * @ClassName: ValidatorUtil 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-12-4 下午06:01:41 
 *  
 */

public class ValidatorUtil
{
	public static boolean isEmpty(List list)
	{
		if(null == list)
		{
			return true;
		}
		if(list.isEmpty())
		{
			return true;
		}
		return false;
	}
	public static boolean isEmpty(Set list)
	{
		if(null == list)
		{
			return true;
		}
		if(list.isEmpty())
		{
			return true;
		}
		return false;
	}
	public static boolean isEmpty(String str)
	{
		if(null == str)
		{
			return true;
		}
		if(str.trim().isEmpty())
		{
			return true;
		}
		return false;
	}
	public static boolean isEmpty(String[] str)
	{
		if(null == str)
		{
			return true;
		}
		if(0 == str.length)
		{
			return true;
		}
		return false;
	}
    public static boolean isChinese(String str) //检验输入为中文方法1，繁琐！
    {

    	
        for(char c : str.toCharArray())
        {
            if(!validChinese(c)) return false; //包含非中文字符
        }
        return true;
    }
	public static boolean validChinese(char c)//校验输入为中文字符
	{


        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if(ub==Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS ||
           ub==Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS ||
           ub==Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A)
        {
            return true; 
        }
        else
        	{
        	return false;
        	}
		
	}
	public static boolean isChinese2(String str) //检验输入为中文方法2，简单！
	{
		String regEx="^[\u4e00-\u9fa5]+$";// "+"为不止一个
		Pattern pat=Pattern.compile(regEx);
		Matcher mat=pat.matcher(str);
		return mat.matches();
	}
	public static boolean isEnglish(String str) //校验输入只为英文字母
	{
		String regEx="^[a-zA-Z]+$";
		Pattern pat=Pattern.compile(regEx);
		Matcher mat=pat.matcher(str);
		return mat.matches();
	}

    /** 
     * 验证Email 
     * @param email email地址，格式：zhangsan@sina.com，zhangsan@xxx.com.cn，xxx代表邮件服务商 
     * @return 验证成功返回true，验证失败返回false 
     */  
    public static boolean isEmail(String email)  //校验邮箱地址合法性
    {  
        String regEx = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";  //?表示0或1次 
        return Pattern.matches(regEx, email);     //这种方法更简洁
    } 
    /** 
     * 验证身份证号码 
     * @param idCard 居民身份证号码15位或18位，最后一位可能是数字或字母 
     * @return 验证成功返回true，验证失败返回false 
     */  
    public static boolean isIDCard(String idCard) 
    {  
        String regEx = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";  
        return Pattern.matches(regEx,idCard);  
    } 
    /** 
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港）） 
     * @param mobile 移动、联通、电信运营商的号码段 
     *<p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡） 
     *、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p> 
     *<p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p> 
     *<p>电信的号段：133、153、180（未启用）、189</p> 
     * @return 验证成功返回true，验证失败返回false 
     */  
    public static boolean isPhone(String phone)
    {  
        String regEx = "(\\+\\d+)?1[3458]\\d{9}$";  
        return Pattern.matches(regEx,phone);  
    }  
    /** 
     * 验证日期（年月日） 
     * @param 日期，格式：1992-09-03， 
     * @return 验证成功返回true，验证失败返回false 
     */  
    public static boolean isDate(String date)
    {  
        String regEx = "[1-9]{4}(-)\\d{1,2}\\1\\d{1,2}";  //"\1"是一个反向引用，引用的正是捕获的第一组(-)
        return Pattern.matches(regEx,date);  
    }
    /** 
     * 验证URL地址 
     * @param url 格式：http://blog.csdn.net:80/xyang81/article/details/7705960? 或 http://www.csdn.net:80 
     * @return 验证成功返回true，验证失败返回false 
     */  
    public static boolean isURL(String url) {  
        String regEx = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";  
        return Pattern.matches(regEx, url);  
    }  
      
    /** 
     * 匹配中国邮政编码 
     * @param postcode 邮政编码 例如广州某国企pc=511434
     * @return 验证成功返回true，验证失败返回false 
     */  
    public static boolean isPc(String pc) {  
        String regEx = "[1-9]\\d{5}";  
        return Pattern.matches(regEx, pc);  
    }  
      
    /** 
     * 匹配IP地址(简单匹配，格式，如：192.168.1.1; 127.0.0.1，没有匹配IP段的大小) 
     * @param ip IPv4标准地址 
     * @return 验证成功返回true，验证失败返回false 
     */  
    public static boolean isIp(String ip) {  
        String regEx = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";  
        return Pattern.matches(regEx, ip);  
    }  
}
