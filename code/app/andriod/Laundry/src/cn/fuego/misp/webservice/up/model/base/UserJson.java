/**   
* @Title: UserJson.java 
* @Package cn.fuego.smart.home.webservice.from.client.model.base 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-1 下午12:13:54 
* @version V1.0   
*/ 
package cn.fuego.misp.webservice.up.model.base;

 /** 
 * @ClassName: UserJson 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-1 下午12:13:54 
 *  
 */
public class UserJson
{
	private int user_id;
	private String password;
	private String user_name;
	private String role;
	public int getUser_id()
	{
		return user_id;
	}
	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
 
	public String getUser_name()
	{
		return user_name;
	}
	public void setUser_name(String user_name)
	{
		this.user_name = user_name;
	}
	public String getRole()
	{
		return role;
	}
	public void setRole(String role)
	{
		this.role = role;
	}
 
 
	
}
