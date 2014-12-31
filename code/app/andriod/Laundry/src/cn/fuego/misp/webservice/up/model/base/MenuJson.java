/**   
* @Title: MenuJson.java 
* @Package cn.fuego.smart.home.webservice.from.client.model.base 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-1 下午5:46:27 
* @version V1.0   
*/ 
package cn.fuego.misp.webservice.up.model.base;


 /** 
 * @ClassName: MenuJson 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-1 下午5:46:27 
 *  
 */
public class MenuJson
{
	private int id;
	private String name;
	
 
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	@Override
	public String toString()
	{
		return "MenuJson [id=" + id + ", name=" + name + "]";
	}
	

}
