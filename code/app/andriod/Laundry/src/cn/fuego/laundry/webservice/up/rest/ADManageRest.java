package cn.fuego.laundry.webservice.up.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.laundry.webservice.up.model.GetADReq;
import cn.fuego.laundry.webservice.up.model.GetADRsp;


/**
 * 
* @ClassName: UserManageService 
* @Description: TODO
* @author Tang Jun
* @date 2014-10-20 上午10:53:45 
*
 */

@Path("/laundry.php/ADManage")
@Produces("application/json")  
@Consumes("application/json")  
public interface ADManageRest
{
	//APP登录验证
	@POST
	@Path("/LoadAll")
	GetADRsp getAll(GetADReq req);
 
	@POST
	@Path("/LoadHelp")
	GetADRsp getHelp(GetADReq req);
 
}
