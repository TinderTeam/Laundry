package cn.fuego.laundry.webservice.up.model;

import cn.fuego.misp.webservice.up.model.base.BaseJsonReq;

public class GetOrderDetailReq extends BaseJsonReq
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
