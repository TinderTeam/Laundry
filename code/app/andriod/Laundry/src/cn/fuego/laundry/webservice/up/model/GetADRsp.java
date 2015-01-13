package cn.fuego.laundry.webservice.up.model;

import java.util.List;

import cn.fuego.laundry.webservice.up.model.base.AdvertisementJson;
import cn.fuego.misp.webservice.up.model.base.BaseJsonRsp;

public class GetADRsp extends BaseJsonRsp
{
	private List<AdvertisementJson> obj;

	public List<AdvertisementJson> getObj()
	{
		return obj;
	}

	public void setObj(List<AdvertisementJson> obj)
	{
		this.obj = obj;
	}
	
}
