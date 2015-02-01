package cn.fuego.laundry.webservice.up.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.fuego.laundry.webservice.up.model.CreateOrderReq;
import cn.fuego.laundry.webservice.up.model.CreateOrderRsp;
import cn.fuego.laundry.webservice.up.model.GetOrderDetailReq;
import cn.fuego.laundry.webservice.up.model.GetOrderDetailRsp;
import cn.fuego.laundry.webservice.up.model.GetOrderListReq;
import cn.fuego.laundry.webservice.up.model.GetOrderListRsp;
import cn.fuego.laundry.webservice.up.model.OperateOrderReq;
import cn.fuego.laundry.webservice.up.model.OperateOrderRsp;

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
	
	@Path("/Modify")
	CreateOrderRsp modify(CreateOrderReq req);
	
	
	@POST
	@Path("/LoadOrderDetailPage")
	GetOrderDetailRsp getOrderDetailList(GetOrderDetailReq req);
	
	@POST
	@Path("/CancelOrder")
	OperateOrderRsp cancel(OperateOrderReq req);
	
	@POST
	@Path("/AbolishOrder")
	OperateOrderRsp delete(OperateOrderReq req);
}
