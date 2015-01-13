package cn.fuego.misp.webservice.up.model;

import cn.fuego.misp.webservice.up.model.base.BaseJsonReq;
import cn.fuego.misp.webservice.up.model.base.UserJson;


/**
 * 
* @ClassName: LoginReq 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:59:19 
*
 */
public class LoginReq extends BaseJsonReq
{
	private UserJson obj = new UserJson();

	public UserJson getObj()
	{
		return obj;
	}

	public void setObj(UserJson obj)
	{
		this.obj = obj;
	}
 
	
	
 
}
