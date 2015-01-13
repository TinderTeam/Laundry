package cn.fuego.misp.webservice.up.model;

import cn.fuego.misp.webservice.up.model.base.BaseJsonRsp;
import cn.fuego.misp.webservice.up.model.base.CompanyJson;

public class GetCompanyRsp extends BaseJsonRsp
{
	private CompanyJson obj;

	public CompanyJson getObj()
	{
		return obj;
	}

	public void setObj(CompanyJson obj)
	{
		this.obj = obj;
	}
	
	
	

}
