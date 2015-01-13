package cn.fuego.laundry.webservice.up.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.laundry.webservice.up.model.GetCustomerReq;
import cn.fuego.laundry.webservice.up.model.GetCustomerRsp;
import cn.fuego.misp.webservice.up.model.LoginReq;
import cn.fuego.misp.webservice.up.model.LoginRsp;


/**
 * 
* @ClassName: UserManageService 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:53:45 
*
 */

@Path("/laundry.php/CustomerManage")
@Produces("application/json")  
@Consumes("application/json")  
public interface CustomerManageRest
{
	//APP登录验证
	@POST
	@Path("/Login")
	LoginRsp login(LoginReq req);
	
	@POST
	@Path("/Modify")
	GetCustomerRsp modify(GetCustomerReq req);
 
	
	@POST
	@Path("/Show")
	GetCustomerRsp getCustomerInfo(GetCustomerReq req);
 
 
}
