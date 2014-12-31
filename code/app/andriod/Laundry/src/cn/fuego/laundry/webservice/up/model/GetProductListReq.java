package cn.fuego.laundry.webservice.up.model;

import cn.fuego.misp.webservice.up.model.base.BaseJsonReq;

public class GetProductListReq extends BaseJsonReq
{
	private String city;			//城市名称
	private int zone_id;				//区域ID
	private int typeRoot;			//typeRoot=0 表示筛选全部分类商品
	private String keyWord;		//关键词模糊搜索
	private boolean search;		//当关键词为空的时候，需要将search设置为false
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public int getTypeRoot() {
		return typeRoot;
	}
	
	public int getZone_id() {
		return zone_id;
	}

	public void setZone_id(int zone_id) {
		this.zone_id = zone_id;
	}

	public void setTypeRoot(int typeRoot) {
		this.typeRoot = typeRoot;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public boolean isSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

	@Override
	public String toString() {
		return "GetProductListReq [city=" + city + ", zone_id=" + zone_id
				+ ", typeRoot=" + typeRoot + ", keyWord=" + keyWord
				+ ", search=" + search + ", token=" + token + "]";
	}

	

}
