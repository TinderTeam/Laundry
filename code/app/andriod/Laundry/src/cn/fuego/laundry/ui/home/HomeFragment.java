package cn.fuego.laundry.ui.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import cn.fuego.laundry.cache.AdDataCache;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.cache.ProductTypeCache;
import cn.fuego.laundry.constant.OrderTypeEnum;
import cn.fuego.laundry.constant.UIDimenConstant;
import cn.fuego.laundry.ui.LoginActivity;
import cn.fuego.laundry.ui.base.BaseFragment;
import cn.fuego.laundry.ui.cart.CartProduct;
import cn.fuego.laundry.ui.order.OrderActivity;
import cn.fuego.laundry.webservice.up.model.CreateOrderReq;
import cn.fuego.laundry.webservice.up.model.GetADReq;
import cn.fuego.laundry.webservice.up.model.GetADRsp;
import cn.fuego.laundry.webservice.up.model.base.AdvertisementJson;
import cn.fuego.laundry.webservice.up.model.base.ProductTypeJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.tool.MispLocationListener;
import cn.fuego.misp.ui.base.MispGridView;
import cn.fuego.misp.ui.common.MispImageActivity;
import cn.fuego.misp.ui.grid.MispGridViewAdapter;
import cn.fuego.misp.ui.model.ImageDisplayInfo;
import cn.fuego.misp.ui.model.MispGridDataModel;
import cn.fuego.misp.ui.util.LoadImageUtil;
import cn.fuego.misp.webservice.up.model.base.CompanyJson;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

public class HomeFragment extends BaseFragment implements OnClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	
	public static final String SELECT_TYPE = "selectType";
 
	private int[] buttonID = new int[]{R.id.home_btn_join_invest,R.id.home_btn_service_phone,R.id.home_btn_direct_order};
	
  

	private ViewGroup group;
	private ViewPager viewPager;
	
 


	
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
			button.getLayoutParams().height = (int)(this.getScreenWidth()*UIDimenConstant.HOME_BTN_L_W_RATIO);
			button.setOnClickListener(this);
		 }
		 loadAdd();
		 initGridView(rootView);
 
		 View gridViewLayout = rootView.findViewById(R.id.home_gridview_layout);
		 
		 int gridHeight = (int)(this.getActivityHeight() - getResources().getDisplayMetrics().density*(UIDimenConstant.TITLE_HEIGHT_DP+UIDimenConstant.TAB_BAR_HEIGHT_DP) - (this.getScreenWidth()*(UIDimenConstant.AD_L_W_RATIO+3*UIDimenConstant.HOME_BTN_L_W_RATIO)));
		 if(gridHeight <getResources().getDisplayMetrics().density*140)
		 {
			 gridViewLayout.getLayoutParams().height = (int)(getResources().getDisplayMetrics().density*140);
		 }
		 else
		 {
			 gridViewLayout.getLayoutParams().height = gridHeight;//
		 }
		 
		
		return rootView;
	}
	
	private void initGridView(View view)
	{
	 
		List<MispGridDataModel> mList = gridInitData();
		final MispGridViewAdapter gridViewAdapter = new MispGridViewAdapter(getActivity(), mList);
		MispGridView gridView = (MispGridView) view.findViewById(R.id.home_gridview);
		//gridView.getLayoutParams().height = (int) (this.getScreenWidth()-50-50 - this.getScreenWidth()*(UIDimenConstant.HOME_BTN_L_W_RATIO+UIDimenConstant.AD_L_W_RATIO));
		gridView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,	int position, long id)
			{
				//Toast.makeText(parent.getContext(), "position"+position, Toast.LENGTH_LONG);
				Intent intent = new Intent(getActivity(),HomeProductActivity.class);
				
				MispGridDataModel data = (MispGridDataModel) gridViewAdapter.getItem(position);
				ProductTypeJson type = (ProductTypeJson) data.getData();
				intent.putExtra(SELECT_TYPE, type);
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
		if(AdDataCache.getInstance().isEmpty())
		{
			GetADReq req = new GetADReq();
			
			WebServiceContext.getInstance().getADManageRest(this).getAll(req);
		}

	}
	
 


	@Override
	public void handle(MispHttpMessage message)
	{
		 if(message.isSuccess())
		 {
			GetADRsp rsp = (GetADRsp) message.getMessage().obj;
			
			List<String> urlList = new ArrayList<String>();
			AdDataCache.getInstance().init(rsp.getObj());
			for(AdvertisementJson json : rsp.getObj())
			{
				urlList.add(MemoryCache.getImageUrl()+json.getAd_img());
			}
			ImagePagerAdapter adapter = new ImagePagerAdapter(this.getActivity(),group,urlList);
		    viewPager.setAdapter(adapter);  
		 
		    viewPager.setCurrentItem(0); 
		    viewPager.setOnPageChangeListener(adapter);
		    startCarouselTimer();
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
 
				CartProduct.getInstance().getOrderInfo().getOrder().setOrder_type(OrderTypeEnum.DIRECT_ORDER.getStrValue());
				intent = new Intent(this.getActivity(),OrderActivity.class);
				//intent.putExtra(OrderActivity.ORDER_INFO, req);
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
			servicePhone();
		}
		else
		{
			intent = new Intent(this.getActivity(),MispImageActivity.class);
			ImageDisplayInfo imageInfo = new ImageDisplayInfo();
			imageInfo.setTilteName("加入我们");
			imageInfo.setUrl(LoadImageUtil.getInstance().getLocalUrl(R.drawable.home_join_info));
			intent.putExtra(MispImageActivity.JUMP_DATA, imageInfo);
			this.startActivity(intent);

		}


	}
	
	private void servicePhone()
	{
		CompanyJson companyJson =  AppCache.getInstance().getCompany();
		if(null == companyJson)
		{
			AppCache.getInstance().loadCompany();
			showMessage(MISPErrorMessageConst.ERROR_NET_FAIL);
			return;
		}
		
		final String content = companyJson.getService_phone();
		 
 		new AlertDialog.Builder(this.getActivity())    
        .setTitle("客服电话").setMessage(content) 
                .setPositiveButton("拨打", new DialogInterface.OnClickListener()
				{

					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						 
		                //用intent启动拨打电话  
		                Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+content));  
		                startActivity(intent); 
						
					}
					
					 
				})
                .setNegativeButton("取消", null)
                .show();  
	}

	  
	private Handler adHandler = new Handler()
	{

		@Override
		public void handleMessage(Message msg)
		{
			// TODO Auto-generated method stub
			viewPager.setCurrentItem(msg.what);
		}
		
	};

	private void startCarouselTimer()
	{
		Timer timer = new Timer();
		timer.schedule(new TimerTask()
		{

			@Override
			public void run()
			{
				int index = viewPager.getCurrentItem()+1;
				if(index>=3)
				{
					index = 0;
				}
				adHandler.sendEmptyMessage(index);
			}
		}, 3000, 3000);
	}
 
 


 

}
