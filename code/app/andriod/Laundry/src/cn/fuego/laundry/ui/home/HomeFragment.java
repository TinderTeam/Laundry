package cn.fuego.laundry.ui.home;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.ProductTypeCache;
import cn.fuego.laundry.ui.util.DataConvertUtil;
import cn.fuego.laundry.webservice.up.model.GetProductListRsp;
import cn.fuego.laundry.webservice.up.model.base.ProductJson;
import cn.fuego.laundry.webservice.up.model.base.ProductTypeJson;
import cn.fuego.misp.ui.list.ListViewResInfo;
import cn.fuego.misp.ui.list.MispDistinctListFragment;
import cn.fuego.misp.ui.model.CommonItemMeta;
import cn.fuego.misp.ui.util.LoadImageUtil;

public class HomeFragment extends MispDistinctListFragment implements OnItemClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
    //定义数组来存放按钮图片  
    private int mImageViewArray[] = {R.drawable.home_food,R.drawable.home_car,R.drawable.home_photo,R.drawable.home_education,
                                     R.drawable.home_entertainment,R.drawable.home_hotel,R.drawable.home_beauty,R.drawable.home_service};  
          
    //Tab选项卡的文字  
    private int mTextviewArray[] = {R.string.home_food, R.string.home_car,R.string.home_photo,R.string.home_education,
    								R.string.home_entertainment,R.string.home_hotel,R.string.home_beauty,R.string.home_service}; 
 
    private static final int ITEM_TYPE_GRID = 1; 
    
    private static final int ITEM_TYPE_TAB = 2; 
    private static final int ITEM_TYPE_PRODUCT_TYPE = 3; 

    private static final int ITEM_TYPE_PRODUCT = 4; 
    
    private static final int ITEM_TYPE_IMAGE = 5; 
    
    private int tabID = 0;

 
    
	private LoadImageUtil loadImageUtil = LoadImageUtil.getInstance();
	
 
	private List<CommonItemMeta> newProductData;
	private List<CommonItemMeta> typeProductData;
	private List<CommonItemMeta> allProductData;
 

	@Override
	public void initRes()
	{ 
		this.fragmentRes.setImage(R.drawable.tabbar_home_icon);
		this.fragmentRes.setName(R.string.tabbar_home);
		this.fragmentRes.setFragmentView(R.layout.home_fragment);
 
		listViewRes.setListView(R.id.home_main_list);
		//listViewRes.setListItemView(R.layout.home_list_item);
		listViewRes.setClickActivityClass(HomeProductActivity.class);
 
		List<CommonItemMeta> itemList = new ArrayList<CommonItemMeta>();
		
		CommonItemMeta imageItem = new CommonItemMeta();
		imageItem.setLayoutType(ITEM_TYPE_IMAGE);
		imageItem.setContent(gridInitData());
		itemList.add(imageItem);
		
		CommonItemMeta mainType = new CommonItemMeta();
		mainType.setLayoutType(ITEM_TYPE_TAB);
		mainType.setContent(ProductTypeCache.getInstance().getAllRootType());
		itemList.add(mainType);
		
		CommonItemMeta subType = new CommonItemMeta();
		subType.setLayoutType(ITEM_TYPE_TAB);
		subType.setContent(ProductTypeCache.getInstance().getSubTypeByTypeID(1));
		itemList.add(subType);
		
//		CommonItemMeta gridItem = new CommonItemMeta();
//		gridItem.setLayoutType(ITEM_TYPE_GRID);
//		gridItem.setContent(gridInitData());
//		itemList.add(gridItem);
//		
//		CommonItemMeta tabItem = new CommonItemMeta();
//		tabItem.setLayoutType(ITEM_TYPE_TAB);
//		itemList.add(tabItem); 
		
		CommonItemMeta proItem = new CommonItemMeta();
		proItem.setLayoutType(ITEM_TYPE_PRODUCT);
		ProductJson json = new ProductJson();
		json.setPrice((float)2.2);
		json.setDscr("aaaaa");
		proItem.setContent(json);
		itemList.add(proItem); 
		itemList.add(proItem); 

		itemList.add(proItem); 
		itemList.add(proItem); 

		this.dataList = itemList;
 	}
 
	 


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		tabID = 0;
		
		
		View view = super.onCreateView(inflater, container, savedInstanceState);
		

		
		return view;
	}




	@Override
	public void loadSendList()
	{
		
//		GetProductListReq req = new GetProductListReq();
//		req.setCity(MemoryCache.getCurCity());
// 
//		switch(tabID)
//		{
//		case 0:
//			WebServiceContext.getInstance().getProductManageRest(this).getNewProductList(req);
//			break;
//			
//		case 1:
//			WebServiceContext.getInstance().getProductManageRest(this).getTypeRecProductList(req);
//			break;
//		case 2:
//			WebServiceContext.getInstance().getProductManageRest(this).getAllProductList(req);
//			break;
//		default:
//			break;
//		}
	}
	
	@Override
	public List<CommonItemMeta> loadListRecv(Object obj)
	{
		GetProductListRsp rsp = (GetProductListRsp) obj;
		
		List<CommonItemMeta> itemList = new ArrayList<CommonItemMeta>();
		
		CommonItemMeta gridItem = new CommonItemMeta();
		gridItem.setLayoutType(ITEM_TYPE_GRID);
		gridItem.setContent(gridInitData());
		itemList.add(gridItem);
		
		CommonItemMeta tabItem = new CommonItemMeta();
		tabItem.setLayoutType(ITEM_TYPE_TAB);
		itemList.add(tabItem); 
		if(tabID == 1)
		{
			Map<Integer,List<ProductJson>> productGroup = divideGroup(rsp.getProductList());
			
			for(Integer typeID : productGroup.keySet())
			{
				
				List<ProductJson> productList = productGroup.get(typeID);
				if(!ValidatorUtil.isEmpty(productList))
				{
					CommonItemMeta groupItem = new CommonItemMeta();
					groupItem.setLayoutType(ITEM_TYPE_PRODUCT_TYPE);
					ProductTypeJson type = ProductTypeCache.getInstance().getTypeByID(typeID);
					if(null != type)
					{
						groupItem.setContent(type.getType_name());
					}
					else
					{
						groupItem.setContent("未知类型");
					}
					itemList.add(groupItem);
					for(ProductJson product : productList)
					{
						CommonItemMeta item = new CommonItemMeta();
						item.setLayoutType(ITEM_TYPE_PRODUCT);
						item.setContent(product);
						itemList.add(item);
					}	
				}
			}
		}
		else
		{
			for(ProductJson product : rsp.getProductList())
			{
				CommonItemMeta item = new CommonItemMeta();
				item.setLayoutType(ITEM_TYPE_PRODUCT);
				item.setContent(product);
				itemList.add(item);
			}	
		}
		switch (tabID)
		{
		case 0:
			this.newProductData = itemList;
			break;
		case 1:
			this.typeProductData = itemList;
			break;
		case 2:
			this.allProductData = itemList;
			break;
		default:
			this.newProductData = itemList;
			break;
		}
		
		return itemList;
	}
	private List<Map<String,Object>> gridInitData()
	{
		List<Map<String,Object>> mArrayList = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < mImageViewArray.length; j++)
		{
        	HashMap<String, Object> map = new HashMap<String, Object>();  
        	map.put("ItemImage", mImageViewArray[j]);//添加图像资源的ID  
        	map.put("ItemText", "test");//按序号做ItemText  
        	mArrayList.add(map); 
		}
			
		 return mArrayList;
	}
	
	public Map<Integer,List<ProductJson>> divideGroup(List<ProductJson> productList)
	{
		Map<Integer,List<ProductJson>> map = new HashMap<Integer,List<ProductJson>>();
		for(ProductJson json : productList)
		{
			List<ProductJson> jsonList = map.get(json.getType_id());
			if(null == jsonList)
			{
				jsonList = new ArrayList<ProductJson>();
				map.put(json.getType_id(), jsonList);
			}
			jsonList.add(json);
		}
		
		return map;
	}
	
	@Override
	public int getItemTypeCount()
	{
		// TODO Auto-generated method stub
		return 6;
	}


 

	@Override
	public View getListItemView(LayoutInflater inflater,View convertView,CommonItemMeta item)
	{
		View view = null;
		
		switch(item.getLayoutType())
		{
		case ITEM_TYPE_IMAGE:
		{
			//view = inflater.inflate(R.layout.list_item_divider, null);
			view = inflater.inflate(R.layout.home_list_item_image, null);
		
			ViewGroup group = (ViewGroup)view.findViewById(R.id.home_ad_image_group);  
			ViewPager viewPager = (ViewPager) view.findViewById(R.id.home_ad_image);
			List<String> urlList = new ArrayList<String>();
			urlList.add(DataConvertUtil.getAbsUrl("54856a7f2a8a7.png"));
			urlList.add(DataConvertUtil.getAbsUrl("54856b34222d9.png"));
			urlList.add(DataConvertUtil.getAbsUrl("54856b34222d9.png"));

 
 
			ImagePagerAdapter adapter = new ImagePagerAdapter(this.getActivity(),group,urlList);
	          //设置Adapter  
	          viewPager.setAdapter(adapter);  
	          //设置监听，主要是设置点点的背景  
	          //viewPager.setOnPageChangeListener(this);  
	          //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动  
	          viewPager.setCurrentItem(0); 
	          viewPager.setOnPageChangeListener(adapter);
 			
			break;
		}
		case ITEM_TYPE_GRID:
			if(null == convertView)
			{
				convertView = inflater.inflate(R.layout.home_list_item_gridview, null);
				view = convertView;
				convertView.setTag(view);
			}
			else
			{
				view = (View) convertView.getTag();
			}
 			
			List<Map<String, Object>> arrayListForEveryGridView = (List<Map<String, Object>>) item.getContent();
			GridViewAdapter gridViewAdapter = new GridViewAdapter(this.getActivity(),arrayListForEveryGridView);
			GridView gridView = (GridView) view.findViewById(R.id.home_gridview);
			gridView.setOnItemClickListener(new OnItemClickListener()
			{

				@Override
				public void onItemClick(AdapterView<?> parent, View view,	int position, long id)
				{
					//Toast.makeText(parent.getContext(), "position"+position, Toast.LENGTH_LONG);
					Intent i= new Intent();
 					parent.getContext().startActivity(i);
				}
				
			});
			gridView.setAdapter(gridViewAdapter);
			break;
		case ITEM_TYPE_TAB:
		{
			List<ProductTypeJson> typeList = (List<ProductTypeJson>) item.getContent();
			log.info("typeList"+typeList);
			if(null == convertView)
			{
				convertView  = inflater.inflate(R.layout.home_list_item_radio, null);
				view = convertView;
				 int flag=0;
				RadioGroup group = (RadioGroup) view.findViewById(R.id.home_radio_group);
				//group.setOnCheckedChangeListener(this);
				for(ProductTypeJson type : typeList)
				{
					RadioButton  typeRadio =new RadioButton(this.getActivity());
					LayoutParams params = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT,1);
					typeRadio.setText(type.getType_name());
					typeRadio.setTextColor(getResources().getColorStateList(R.drawable.nav_text_color));
					typeRadio.setTextSize(getResources().getDimension(R.dimen.content_font_size_sm));
					typeRadio.setBackgroundResource(R.drawable.nav_btn);
					typeRadio.setButtonDrawable(getResources().getDrawable(android.R.color.transparent));//   android:button="@null"
					typeRadio.setId(type.getType_id());
					typeRadio.setLayoutParams(params);
					if(flag==0)
					{
						typeRadio.setChecked(true);
					}
					group.addView(typeRadio);
					flag++;
				}

				convertView.setTag(view);

			}
			else
			{
				view = (View) convertView.getTag();
			}
	

		    break;
		}
		case ITEM_TYPE_PRODUCT_TYPE:
		    
			if(null == convertView)
			{
				convertView = inflater.inflate(R.layout.list_item_divider, null);
		        view = convertView;
		        convertView.setTag(view);
			}
			else
			{
				view = (View) convertView.getTag();
			}

			TextView text = (TextView) view.findViewById(R.id.list_divider_label);
			text.setText((String)item.getContent());
			break;
		case ITEM_TYPE_PRODUCT:
			if(null == convertView)
			{
				convertView = inflater.inflate(R.layout.home_list_item, null);
	

		        view = convertView;
		        convertView.setTag(view);
			}
			else
			{
				view = (View) convertView.getTag();
			}
			
			ProductJson product = (ProductJson) item.getContent();
		    TextView titleView = (TextView) convertView.findViewById(R.id.home_list_item_title);
	        titleView.setText(product.getName());
	        
	        TextView curPrice = (TextView) convertView.findViewById(R.id.home_list_item_curPrice);
	        curPrice.setText(String.valueOf(product.getPrice()));
	        TextView oldPrice = (TextView) convertView.findViewById(R.id.home_list_item_oldPrice);
	        oldPrice.setText(String.valueOf(product.getOriginal_price()));

	        TextView desp = (TextView) convertView.findViewById(R.id.home_list_item_desp);
	        desp.setText(String.valueOf(product.getDscr()));

	        ImageView imageView = (ImageView) convertView.findViewById(R.id.home_list_item_img);
	 
	        loadImageUtil.loadImage(imageView, DataConvertUtil.getAbsUrl(product.getImgsrc()));
	        
	        break;
		
		}
 

        return view;
	}

	public void onItemClick(CommonItemMeta item)
	{
		if(ITEM_TYPE_PRODUCT == item.getLayoutType())
		{
			Intent intent = new Intent(this.getActivity(),this.listViewRes.getClickActivityClass());
			intent.putExtra(ListViewResInfo.SELECT_ITEM, (Serializable) item.getContent());

			this.startActivity(intent);
		}
		

	}



 

}
