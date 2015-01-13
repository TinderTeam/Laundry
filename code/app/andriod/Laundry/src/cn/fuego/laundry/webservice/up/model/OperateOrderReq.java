package cn.fuego.laundry.webservice.up.model;

import java.util.List;

import cn.fuego.laundry.webservice.up.model.base.DeliveryInfoJson;
import cn.fuego.laundry.webservice.up.model.base.OrderDetailJson;
import cn.fuego.laundry.webservice.up.model.base.OrderJson;
import cn.fuego.misp.webservice.up.model.base.BaseJsonReq;

public class OperateOrderReq extends BaseJsonReq
{
	private String order_id;

	public String getOrder_id()
	{
		return order_id;
	}

	public void setOrder_id(String order_id)
	{
		this.order_id = order_id;
	}
	
	

}
