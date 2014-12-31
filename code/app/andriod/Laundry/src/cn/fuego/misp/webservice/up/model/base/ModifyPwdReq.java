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
	 
	private String username;		//用户名
	private String oldPwd;			//原密码
	private String pwdNew;			//新密码
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOldPwd()
	{
		return oldPwd;
	}
	public void setOldPwd(String oldPwd)
	{
		this.oldPwd = oldPwd;
	}
 
	public String getPwdNew()
	{
		return pwdNew;
	}
	public void setPwdNew(String pwdNew)
	{
		this.pwdNew = pwdNew;
	}
	@Override
	public String toString() {
		return "ModifyPwdReq [username=" + username + ", oldPwd=" + oldPwd
				+ ", pwdNew=" + pwdNew + "]";
	}
	
 
	

}
