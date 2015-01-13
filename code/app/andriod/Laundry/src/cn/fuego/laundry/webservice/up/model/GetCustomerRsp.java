package cn.fuego.laundry.webservice.up.model;

import cn.fuego.laundry.webservice.up.model.base.CustomerJson;
import cn.fuego.misp.webservice.up.model.base.BaseJsonRsp;


public class GetCustomerRsp extends BaseJsonRsp
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
