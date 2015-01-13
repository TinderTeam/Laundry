package cn.fuego.laundry.ui.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.cache.ProductTypeCache;
import cn.fuego.laundry.constant.OrderTypeEnum;
import cn.fuego.laundry.constant.UIDimenConstant;
import cn.fuego.laundry.ui.LoginActivity;
import cn.fuego.laundry.ui.base.BaseFragment;
import cn.fuego.laundry.ui.base.FuegoDailog;
import cn.fuego.laundry.ui.order.OrderActivity;
import cn.fuego.laundry.webservice.up.model.CreateOrderReq;
import cn.fuego.laundry.webservice.up.model.GetADReq;
import cn.fuego.laundry.webservice.up.model.GetADRsp;
import cn.fuego.laundry.webservice.up.model.base.AdvertisementJson;
import cn.fuego.laundry.webservice.up.model.base.ProductJson;
import cn.fuego.laundry.webservice.up.model.base.ProductTypeJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.base.MispGridView;
import cn.fuego.misp.ui.grid.MispCommonGridViewAdapter;
import cn.fuego.misp.ui.model.CommonItemMeta;
import cn.fuego.misp.ui.model.MispGridDataModel;
import cn.fuego.misp.webservice.up.model.base.CompanyJson;

public class HomeFragment extends BaseFragment implements OnClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	
	public static final String SELECT_TYPE = "selectType";
 
	private int[] buttonID = new int[]{R.id.home_btn_join_invest,R.id.home_btn_service_phone,R.id.home_btn_direct_order};
	
	private Map<Integer,Integer> btnTypeMap = new HashMap<Integer, Integer>();
 

	private ViewGroup group;
	private ViewPager viewPager;
	
    //定义数组来存放按钮图片  
    private int mImageViewArray[] = {R.drawable.home_food,R.drawable.home_car,R.drawable.home_photo,R.drawable.home_education,
                                     R.drawable.home_entertainment,R.drawable.home_hotel,R.drawable.home_beauty,R.drawable.home_service};  
          
    //Tab选项卡的文字  
    private int mTextviewArray[] = {R.string.tabbar_home, R.string.tabbar_home,R.string.tabbar_home,R.string.tabbar_home,
    								R.string.tabbar_home,R.string.tabbar_home,R.string.tabbar_home,R.string.tabbar_home}; 
 
	@Override
	public void initRes()
	{ 
		this.fragmentRes.setImage(R.drawable.tab_icon_home);
		this.fragmentRes.setName(R.string.tabbar_home);
		this.fragmentRes.setFragmentView(R.layout.home_fragment);
 
 
 	}
 
	 


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
 
		
		View rootView = inflater.inflate(R.layout.home_fragment, null);
		 group = (ViewGroup)rootView.findViewById(R.id.home_ad_image_group);  
		 viewPager = (ViewPager) rootView.findViewById(R.id.home_ad_image);
		 viewPager.getLayoutParams().height = (int) (this.getScreenWidth()*UIDimenConstant.AD_L_W_RATIO);
		 for(int id : buttonID)
		 {
			Button button =  (Button) rootView.findViewById(id);
			button.setOnClickListener(this);
		 }
		 loadAdd();
		 initGridView(rootView);
		 
		
		return rootView;
	}
	
	private void initGridView(View view)
	{
		List<MispGridDataModel> mList = gridInitData();
		final MispCommonGridViewAdapter gridViewAdapter = new MispCommonGridViewAdapter(getActivity(), mList);
		MispGridView gridView = (MispGridView) view.findViewById(R.id.home_gridview);
		gridView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,	int position, long id)
			{
				//Toast.makeText(parent.getContext(), "position"+position, Toast.LENGTH_LONG);
				Intent intent = new Intent(getActivity(),HomeProductActivity.class);
				
				MispGridDataModel data = (MispGridDataModel) gridViewAdapter.getItem(position);
				ProductTypeJson type = (ProductTypeJson) data.getData();
				intent.putExtra(SELECT_TYPE, type.getType_id());
				startActivity(intent);
			}
			
		});
		gridView.setAdapter(gridViewAdapter);
	}
	
	private List<MispGridDataModel> gridInitData()
	{
		  
		List<MispGridDataModel> mArrayList = new ArrayList<MispGridDataModel>();
		
        for (ProductTypeJson type : ProductTypeCache.getInstance().getTypeList())
		{
         	
        	MispGridDataModel gridData = new MispGridDataModel();
        	gridData.setData(type);
        	gridData.setImage(this.getImageIdByName(type.getType_img()));
        	gridData.setName(type.getType_name());
   
        	mArrayList.add(gridData); 
		}
			
		 return mArrayList;
	}
	
	private void loadAdd()
	{
		GetADReq req = new GetADReq();
		
		WebServiceContext.getInstance().getADManageRest(this).getAll(req);
	}
	
 


	@Override
	public void handle(MispHttpMessage message)
	{
		 if(message.isSuccess())
		 {
			GetADRsp rsp = (GetADRsp) message.getMessage().obj;
			
			List<String> urlList = new ArrayList<String>();
			for(AdvertisementJson json : rsp.getObj())
			{
				urlList.add(MemoryCache.getImageUrl()+json.getAd_img());
			}
			ImagePagerAdapter adapter = new ImagePagerAdapter(this.getActivity(),group,urlList);
		    viewPager.setAdapter(adapter);  
		 
		    viewPager.setCurrentItem(0); 
		    viewPager.setOnPageChangeListener(adapter);
		 }

	}

	@Override
	public void onClick(View v)
	{
 		Intent intent = null;

		if(R.id.home_btn_direct_order == v.getId())
		{
			if(MemoryCache.isLogined())
			{
				CreateOrderReq req = new CreateOrderReq();
				req.getOrder().setOrder_type(OrderTypeEnum.DIRECT_ORDER.getStrValue());
				req.getOrder().setUser_id(AppCache.getInstance().getUser().getUser_id());
				req.getOrder().setUser_name(AppCache.getInstance().getUser().getUser_name());
 
				intent = new Intent(this.getActivity(),OrderActivity.class);
				intent.putExtra(OrderActivity.ORDER_INFO, req);
			}
			else
			{
				intent = new Intent(this.getActivity(),LoginActivity.class);
				intent.putExtra(LoginActivity.JUMP_SOURCE, this.getClass());

				log.warn("have not login when direct order");
			}
			this.startActivity(intent);

		}
		else if(R.id.home_btn_service_phone == v.getId())
		{
			String content = "";
			CompanyJson company = AppCache.getInstance().getCompany();
			if(null != company)
			{
				content = company.getService_phone();
			}
			FuegoDailog.show(this.getActivity(), "客服电话",content );
		}
		else
		{
			intent = new Intent(this.getActivity(),HomeProductActivity.class);
			intent.putExtra(SELECT_TYPE, btnTypeMap.get(v.getId()));
			this.startActivity(intent);

		}


	}

 
 
 


 

}
