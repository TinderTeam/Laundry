package cn.fuego.laundry.webservice.up.model;

import cn.fuego.misp.webservice.up.model.base.BaseJsonReq;

public class GetProductListReq extends BaseJsonReq
{
 
	private int typeRoot;			//typeRoot=0 表示筛选全部分类商品
	private String keyWord;		//关键词模糊搜索
	public int getTypeRoot()
	{
		return typeRoot;
	}
	public void setTypeRoot(int typeRoot)
	{
		this.typeRoot = typeRoot;
	}
	public String getKeyWord()
	{
		return keyWord;
	}
	public void setKeyWord(String keyWord)
	{
		this.keyWord = keyWord;
	}
 
	 
	

}
