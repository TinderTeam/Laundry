package cn.fuego.laundry.cache;

import java.util.ArrayList;
import java.util.List;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.webservice.up.model.GetProductTypeReq;
import cn.fuego.laundry.webservice.up.model.GetProductTypeRsp;
import cn.fuego.laundry.webservice.up.model.base.ProductTypeJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;

public class ProductTypeCache
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	private  List<ProductTypeJson> typeList = new ArrayList<ProductTypeJson>();
	private static ProductTypeCache instance;
	private ProductTypeCache()
	{
		load();
	}
	
	public synchronized static ProductTypeCache getInstance()
	{
		if(null == instance)
		{
			instance = new ProductTypeCache();
		}
		return instance;
		
	}
	
	private ProductTypeJson getType(int fatherID,int typeID,String name)
	{
		ProductTypeJson type = new ProductTypeJson();
		type.setType_id(typeID);
		type.setType_name(name);
		type.setFather_id(fatherID);
		type.setType_img("product_type_"+typeID);
		return type;
	}
	private void initType()
	{
 
		typeList.add(getType(0,1,"上装类"));
		typeList.add(getType(0,2,"下装类"));
		typeList.add(getType(0,3,"毛皮服饰类"));
		typeList.add(getType(0,4,"箱包鞋类"));
		typeList.add(getType(0,5,"奢侈品牌类"));
		typeList.add(getType(0,6,"居家类"));
		typeList.add(getType(0,7,"汽车配饰类"));
		typeList.add(getType(0,8,"染色/改色/救治"));

 
	}
	
	public void load()
	{
		initType();
		GetProductTypeReq req = new GetProductTypeReq();
		req.setTypeRoot(0);
	 
		WebServiceContext.getInstance().getProductManageRest(new MispHttpHandler()
		{
			@Override
			public void handle(MispHttpMessage message)
			{
				if(message.isSuccess())
				{
					GetProductTypeRsp rsp = (GetProductTypeRsp) message.getMessage().obj;
					typeList = rsp.getTypeList();
					
				}
				else
				{
					log.error("can not get type list from server,the error code is "+ message.getErrorCode());
				}
				
			}	
		}).getProductType(req);
	}
	 
	public List<ProductTypeJson> getAllRootType()
	{
		List<ProductTypeJson>  rootTypeList = new ArrayList<ProductTypeJson>();
		for(ProductTypeJson json : typeList)
		{
			if(json.getFather_id() == 0)
			{
				rootTypeList.add(json);

			}
		}
		return rootTypeList;
	}
	
	public List<ProductTypeJson> getSubTypeByTypeID(int typeID)
	{
		List<ProductTypeJson>  subTypeList = new ArrayList<ProductTypeJson>();
		for(ProductTypeJson json : typeList)
		{
			if(json.getFather_id() == typeID)
			{
				subTypeList.add(json);
			}

		}
		return subTypeList;
	}
	 
	public  List<ProductTypeJson> getTypeList()
	{
		return typeList;
	}

	public  void setTypeList(List<ProductTypeJson> typeList)
	{
		this.typeList = typeList;
	}

	public ProductTypeJson getTypeByID(Integer typeID)
	{
		for(ProductTypeJson json : typeList)
		{
			if(json.getType_id() == typeID)
			{
				return json;
			}
		}
		return null;
	}
}
