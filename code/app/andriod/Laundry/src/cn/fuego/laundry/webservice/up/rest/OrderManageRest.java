package cn.fuego.laundry.webservice.up.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.laundry.webservice.up.model.CreateOrderReq;
import cn.fuego.laundry.webservice.up.model.CreateOrderRsp;
import cn.fuego.laundry.webservice.up.model.GetOrderListReq;
import cn.fuego.laundry.webservice.up.model.GetOrderListRsp;
import cn.fuego.laundry.webservice.up.model.OperateOrderReq;

@Path("/laundry.php/OrderManage")
@Produces("application/json")  
@Consumes("application/json")  
public interface OrderManageRest
{
	@POST
	@Path("/LoadPage")
	GetOrderListRsp getAll(GetOrderListReq req);
	@POST
	@Path("/Create")
	CreateOrderRsp create(CreateOrderReq req);
	
	@POST
	@Path("/OrderDetail")
	CreateOrderReq show(OperateOrderReq req);
	
	@POST
	@Path("/Cancel")
	CreateOrderRsp cancel(OperateOrderReq req);
	
	@POST
	@Path("/Delete")
	CreateOrderRsp delete(OperateOrderReq req);
}
