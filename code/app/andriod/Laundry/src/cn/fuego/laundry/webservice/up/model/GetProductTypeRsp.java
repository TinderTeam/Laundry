package cn.fuego.laundry.webservice.up.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.laundry.webservice.up.model.base.ProductTypeJson;
import cn.fuego.misp.webservice.up.model.base.BaseJsonRsp;

public class GetProductTypeRsp extends BaseJsonRsp
{
	private List<ProductTypeJson> typeList = new ArrayList<ProductTypeJson>();

	public List<ProductTypeJson> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<ProductTypeJson> typeList) {
		this.typeList = typeList;
	}
 

}
