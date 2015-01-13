package cn.fuego.misp.webservice.up.model;

import cn.fuego.misp.webservice.up.model.base.ClientVersionJson;
import cn.fuego.misp.webservice.up.model.base.BaseJsonRsp;

public class GetClientVersionRsp extends BaseJsonRsp
{
	private ClientVersionJson obj;

	public ClientVersionJson getObj()
	{
		return obj;
	}

	public void setObj(ClientVersionJson obj)
	{
		this.obj = obj;
	}
	

}
