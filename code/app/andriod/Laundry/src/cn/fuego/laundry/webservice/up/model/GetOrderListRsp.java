package cn.fuego.laundry.webservice.up.model;

import java.util.List;

import cn.fuego.laundry.webservice.up.model.base.OrderJson;
import cn.fuego.misp.webservice.up.model.base.BaseJsonReq;

public class GetOrderListRsp extends BaseJsonReq
{
	private List<OrderJson> obj;

	public List<OrderJson> getObj()
	{
		return obj;
	}

	public void setObj(List<OrderJson> obj)
	{
		this.obj = obj;
	}
	

}
