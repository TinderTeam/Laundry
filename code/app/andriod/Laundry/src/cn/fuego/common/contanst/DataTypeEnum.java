/**   
* @Title: DataTypeEnum.java 
* @Package cn.fuego.misp.contanst 
* @Description: TODO
* @author Tang Jun   
* @date 2014-9-24 下午04:35:12 
* @version V1.0   
*/ 
package cn.fuego.common.contanst;

/** 
 * @ClassName: DataTypeEnum 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-9-24 下午04:35:12 
 *  
 */

public enum DataTypeEnum
{
	FLOAT("float"),
	INTEGER("integer"),
	DATE("date");
	
    private String type;  
    private DataTypeEnum(String type)
    {  
        this.type = type;  
    }
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}  
    
	
}
