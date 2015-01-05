package cn.fuego.misp.webservice.up.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.laundry.webservice.up.model.LoginReq;
import cn.fuego.laundry.webservice.up.model.LoginRsp;
import cn.fuego.misp.webservice.up.model.ModifyPwdReq;
import cn.fuego.misp.webservice.up.model.ModifyPwdRsp;
import cn.fuego.misp.webservice.up.model.SendVerifyCodeReq;
import cn.fuego.misp.webservice.up.model.SendVerifyCodeRsp;
import cn.fuego.misp.webservice.up.model.UserRegisterReq;
import cn.fuego.misp.webservice.up.model.UserRegisterRsp;


/**
 * 
* @ClassName: UserManageService 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:53:45 
*
 */

@Path("/index.php/UserManage")
@Produces("application/json")  
@Consumes("application/json")  
public interface MispUserManageRest
{
	//APP登录验证
	@POST
	@Path("/Login")
	LoginRsp login(LoginReq req);
	
	//APP退出
	@POST
	@Path("/Logout")
	LoginRsp logout(LoginReq req);
	
	//APP修改密码
	@POST
	@Path("/ModifyPassword")
    ModifyPwdRsp modifyPassword(ModifyPwdReq req);
	
	@POST
	@Path("/Register")
	UserRegisterRsp register(UserRegisterReq req);
 
	@POST
	@Path("/SendVerifyCode")
	SendVerifyCodeRsp sendVerifyCode(SendVerifyCodeReq req);
	

 
}
