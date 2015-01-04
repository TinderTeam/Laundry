package cn.fuego.laundry.webservice.up.model;

import cn.fuego.laundry.webservice.up.model.base.CustomerJson;
import cn.fuego.misp.webservice.up.model.base.BaseJsonReq;

public class ModifyCustomerReq extends BaseJsonReq
{
 
	private CustomerJson obj;

	public CustomerJson getObj()
	{
		return obj;
	}

	public void setObj(CustomerJson obj)
	{
		this.obj = obj;
	}
	
	

}
