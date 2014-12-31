package cn.fuego.laundry.webservice.up.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.laundry.webservice.up.model.GetProductListReq;
import cn.fuego.laundry.webservice.up.model.GetProductListRsp;
import cn.fuego.laundry.webservice.up.model.GetProductTypeReq;
import cn.fuego.laundry.webservice.up.model.GetProductTypeRsp;


@Path("/Buy")
@Produces("application/json")  
@Consumes("application/json")  
public interface ProductManageRest
{
	//APP获取推荐产品列表
	@POST
	@Path("/RecommendProduct_rest")
	GetProductListRsp getRecommendProductList(GetProductListReq req);
	
	//APP获取最新特惠产品列表
	@POST
	@Path("/NewProduct_rest")
	GetProductListRsp getNewProductList(GetProductListReq req);
	
	//APP获取分类推荐产品列表
	@POST
	@Path("/TypeRecProduct_rest")
	GetProductListRsp getTypeRecProductList(GetProductListReq req);
	
	//APP获取所有产品列表
	@POST
	@Path("/AllProduct_rest")
	GetProductListRsp getAllProductList(GetProductListReq req);
	
	//APP获取积分产品列表
	@POST
	@Path("/ScoreProduct_rest")
	GetProductListRsp getScoreProductList(GetProductListReq req);
	
	//APP获取产品类型列表
	@POST
	@Path("/GetProdutType_rest")
	GetProductTypeRsp getProductType(GetProductTypeReq req);
 
 
}
