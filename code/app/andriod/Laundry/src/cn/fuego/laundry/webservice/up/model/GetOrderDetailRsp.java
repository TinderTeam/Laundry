package cn.fuego.laundry.webservice.up.model;

import java.util.List;

import cn.fuego.laundry.webservice.up.model.base.OrderDetailJson;
import cn.fuego.laundry.webservice.up.model.base.OrderJson;
import cn.fuego.misp.webservice.up.model.base.BaseJsonRsp;

public class GetOrderDetailRsp extends BaseJsonRsp
{
	private List<OrderDetailJson> obj;

	public List<OrderDetailJson> getObj()
	{
		return obj;
	}

	public void setObj(List<OrderDetailJson> obj)
	{
		this.obj = obj;
	}

 

}
