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
		return type;
	}
	private void initType()
	{
 
		typeList.add(getType(0,1,"衣物类"));
		typeList.add(getType(0,2,"居家用品"));
		typeList.add(getType(0,3,"鞋类清洗"));
		typeList.add(getType(0,4,"奢侈品洗护"));
		
		typeList.add(getType(1,5,"上装"));
		typeList.add(getType(1,6,"裤类"));
		typeList.add(getType(1,7,"裙类及配饰"));
		
		typeList.add(getType(2,8,"床上用品"));
		typeList.add(getType(2,9,"沙发套"));
		typeList.add(getType(2,10,"窗帘清洗"));
		typeList.add(getType(2,11,"地毯垫类"));
		
		typeList.add(getType(3,12,"低于脚踝以下尺寸"));
		typeList.add(getType(3,13,"脚踝至膝盖之间"));
		typeList.add(getType(3,14,"过膝盖以上尺寸"));
		
		typeList.add(getType(4,15,"钱包"));
		typeList.add(getType(4,16,"卡包"));
		typeList.add(getType(4,17,"男女包"));
		typeList.add(getType(4,18,"旅行包"));
 
 
 
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
