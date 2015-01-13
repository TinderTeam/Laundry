package cn.fuego.misp.webservice.up.model;

import cn.fuego.misp.webservice.up.model.base.BaseJsonReq;
import cn.fuego.misp.webservice.up.model.base.CompanyJson;

public class GetCompanyReq extends BaseJsonReq
{
 
	private int obj;  //公司ID

	public int getObj()
	{
		return obj;
	}

	public void setObj(int obj)
	{
		this.obj = obj;
	}
 
	

}
