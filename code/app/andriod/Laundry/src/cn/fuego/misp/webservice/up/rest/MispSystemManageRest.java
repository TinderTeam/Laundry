package cn.fuego.misp.webservice.up.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.misp.webservice.up.model.GetClientVersionReq;
import cn.fuego.misp.webservice.up.model.GetClientVersionRsp;
import cn.fuego.misp.webservice.up.model.GetCompanyReq;
import cn.fuego.misp.webservice.up.model.GetCompanyRsp;


@Path("/index.php")
@Produces("application/json")  
@Consumes("application/json")  
public interface MispSystemManageRest
{
	@POST
	@Path("/CompanyManage/Show")
	GetCompanyRsp getCompany(GetCompanyReq req);
	
	@POST
	@Path("/ClientVersionManage/GetLatestVersion")
	GetClientVersionRsp getAppVersion(GetClientVersionReq req);
}
