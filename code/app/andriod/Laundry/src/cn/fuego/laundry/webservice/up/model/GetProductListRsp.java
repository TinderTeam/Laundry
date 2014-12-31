package cn.fuego.laundry.webservice.up.model;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.laundry.webservice.up.model.base.ProductJson;
import cn.fuego.misp.webservice.up.model.base.BaseJsonRsp;

public class GetProductListRsp extends BaseJsonRsp
{
	private List<ProductJson> productList = new ArrayList<ProductJson>();

	public List<ProductJson> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductJson> productList) {
		this.productList = productList;
	}

 
	

}
