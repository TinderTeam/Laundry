/**   
* @Title: OperateTypeEnum.java 
* @Package cn.fuego.smart.home.constant 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-5 下午11:34:59 
* @version V1.0   
*/ 
package cn.fuego.misp.constant;

 /** 
 * @ClassName: OperateTypeEnum 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-5 下午11:34:59 
 *  
 */
public enum ResourceOperateEnum
{
	READ(0,"READ"),
	MODIFY(1,"MODIFY"),
	DELETE(2,"DELETE");
	private int intValue;
	private String strValue;
	/**
	 * @param intValue
	 * @param strValue
	 */
	private ResourceOperateEnum(int intValue, String strValue)
	{
		this.intValue = intValue;
		this.strValue = strValue;
	}
	public int getIntValue()
	{
		return intValue;
	}
	public String getStrValue()
	{
		return strValue;
	}
}
