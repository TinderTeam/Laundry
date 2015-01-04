package cn.fuego.laundry.webservice.up.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.laundry.webservice.up.model.GetProductListReq;
import cn.fuego.laundry.webservice.up.model.GetProductListRsp;
import cn.fuego.laundry.webservice.up.model.GetProductTypeReq;
import cn.fuego.laundry.webservice.up.model.GetProductTypeRsp;


@Path("/laundry.php/ProductManage")
@Produces("application/json")  
@Consumes("application/json")  
public interface ProductManageRest
{
 
	//APP获取所有产品列表
	@POST
	@Path("/LoadPage")
	GetProductListRsp getAllProductList(GetProductListReq req);
	
 
	//APP获取产品类型列表
	@POST
	@Path("/GetProdutType_rest")
	GetProductTypeRsp getProductType(GetProductTypeReq req);
 
 
}
