/**   
* @Title: DateCreateUtil.java 
* @Package cn.fuego.common.util.format 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-1 下午6:02:09 
* @version V1.0   
*/ 
package cn.fuego.common.util.format;

import java.util.UUID;

 /** 
 * @ClassName: DateCreateUtil 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-1 下午6:02:09 
 *  
 */
public class DateCreateUtil
{
	public static String getUUID()
	{
		return UUID.randomUUID().toString();
	}

}
