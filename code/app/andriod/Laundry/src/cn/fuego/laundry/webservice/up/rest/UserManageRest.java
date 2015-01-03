package cn.fuego.laundry.webservice.up.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.laundry.webservice.up.model.LoginReq;
import cn.fuego.laundry.webservice.up.model.LoginRsp;
import cn.fuego.laundry.webservice.up.model.SendVerifyCodeReq;
import cn.fuego.laundry.webservice.up.model.SendVerifyCodeRsp;
import cn.fuego.laundry.webservice.up.model.UserRegisterReq;
import cn.fuego.laundry.webservice.up.model.UserRegisterRsp;
import cn.fuego.misp.webservice.up.model.base.ModifyPwdReq;
import cn.fuego.misp.webservice.up.model.base.ModifyPwdRsp;


/**
 * 
* @ClassName: UserManageService 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:53:45 
*
 */

@Path("/Index")
@Produces("application/json")  
@Consumes("application/json")  
public interface UserManageRest
{
	//APP登录验证
	@POST
	@Path("/login_rest")
	LoginRsp login(LoginReq req);
	
	//APP退出
	@POST
	@Path("/logout_rest")
	LoginRsp logout(LoginReq req);
	
	//APP修改密码
	@POST
	@Path("/modifyPswd_rest")
    ModifyPwdRsp modifyPassword(ModifyPwdReq req);
	
	@POST
	@Path("/modifyPswd_rest")
	SendVerifyCodeRsp sendVerifyCode(SendVerifyCodeReq req);
	
	@POST
	@Path("/modifyPswd_rest")
	UserRegisterRsp register(UserRegisterReq req);
 
 
}
