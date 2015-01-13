package cn.fuego.laundry.webservice.up.model;

import cn.fuego.misp.webservice.up.model.base.BaseJsonReq;

public class GetCustomerReq extends BaseJsonReq
{
 
	private int obj; //customer id;

	public int getObj()
	{
		return obj;
	}

	public void setObj(int obj)
	{
		this.obj = obj;
	}
 
	

}
