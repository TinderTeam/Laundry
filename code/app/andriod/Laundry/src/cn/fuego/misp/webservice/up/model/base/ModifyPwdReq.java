/**   
* @Title: ModifyPwdReq.java 
* @Package cn.fuego.smart.home.webservice.from.client.model 
* @Description: TODO
* @author Tang Jun   
* @date 2014-11-4 下午11:07:00 
* @version V1.0   
*/ 
package cn.fuego.misp.webservice.up.model.base;


 /** 
 * @ClassName: ModifyPwdReq 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-11-4 下午11:07:00 
 *  
 */
public class ModifyPwdReq extends BaseJsonReq
{
	 
	private String user_name;		//用户名
	private String pwdOld;			//原密码
	private String pwdNew;			//新密码
	public String getUser_name()
	{
		return user_name;
	}
	public void setUser_name(String user_name)
	{
		this.user_name = user_name;
	}
	public String getPwdOld()
	{
		return pwdOld;
	}
	public void setPwdOld(String pwdOld)
	{
		this.pwdOld = pwdOld;
	}
	public String getPwdNew()
	{
		return pwdNew;
	}
	public void setPwdNew(String pwdNew)
	{
		this.pwdNew = pwdNew;
	}
	 
	

}
