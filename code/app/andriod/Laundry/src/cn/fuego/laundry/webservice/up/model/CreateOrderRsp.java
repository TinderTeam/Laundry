package cn.fuego.laundry.webservice.up.model;

import cn.fuego.laundry.webservice.up.model.base.OrderJson;
import cn.fuego.misp.webservice.up.model.base.BaseJsonRsp;

public class CreateOrderRsp extends BaseJsonRsp
{

	private OrderJson obj;

	public OrderJson getObj()
	{
		return obj;
	}

	public void setObj(OrderJson obj)
	{
		this.obj = obj;
	}
	
	
	
	

}
