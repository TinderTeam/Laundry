package cn.fuego.laundry.webservice.up.model;

import cn.fuego.misp.webservice.up.model.base.BaseJsonReq;

public class GetProductTypeReq extends BaseJsonReq
{
	private int typeRoot;		//typeRoot=0 表示全部分类，其他1-42对应数据库中type_id对应的产品分类

	public int getTypeRoot() {
		return typeRoot;
	}

	public void setTypeRoot(int typeRoot) {
		this.typeRoot = typeRoot;
	}

	@Override
	public String toString() {
		return "GetProductTypeReq [typeRoot=" + typeRoot + ", token=" + token
				+ "]";
	}

	

}
