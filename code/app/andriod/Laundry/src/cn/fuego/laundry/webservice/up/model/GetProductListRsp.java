package cn.fuego.laundry.webservice.up.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.laundry.webservice.up.model.base.ProductJson;
import cn.fuego.misp.webservice.up.model.base.BaseJsonRsp;

public class GetProductListRsp extends BaseJsonRsp
{
	private List<ProductJson> obj = new ArrayList<ProductJson>();

	public List<ProductJson> getObj()
	{
		return obj;
	}

	public void setObj(List<ProductJson> obj)
	{
		this.obj = obj;
	}
 
}
