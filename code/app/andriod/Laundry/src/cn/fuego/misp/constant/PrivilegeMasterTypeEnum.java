/**   
* @Title: PrivilegeMasterNameEnum.java 
* @Package cn.fuego.misp.constant 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-21 上午10:02:44 
* @version V1.0   
*/ 
package cn.fuego.misp.constant;

 /** 
 * @ClassName: PrivilegeMasterNameEnum 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-21 上午10:02:44 
 *  
 */
public enum PrivilegeMasterTypeEnum
{
	USER("USER"),
	ROLE("ROLE"),
	DEPTARTMENT("DEPP");
	private String masterType;
	private PrivilegeMasterTypeEnum(String masterType)
	{
		this.masterType = masterType;
	}
	public String getMasterType()
	{
		return masterType;
	}

}
