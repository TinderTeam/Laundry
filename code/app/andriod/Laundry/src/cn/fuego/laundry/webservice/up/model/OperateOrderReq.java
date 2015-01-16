package cn.fuego.laundry.webservice.up.model;

import java.util.List;

import cn.fuego.laundry.webservice.up.model.base.DeliveryInfoJson;
import cn.fuego.laundry.webservice.up.model.base.OrderDetailJson;
import cn.fuego.laundry.webservice.up.model.base.OrderJson;
import cn.fuego.misp.webservice.up.model.base.BaseJsonReq;

public class OperateOrderReq extends BaseJsonReq
{
	private int obj; //order id

	public int getObj()
	{
		return obj;
	}

	public void setObj(int obj)
	{
		this.obj = obj;
	}

 

 
	

}
