package cn.fuego.laundry.ui.home;

import java.util.ArrayList;
import java.util.List;
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
import cn.fuego.laundry.cache.ProductCache;
import cn.fuego.laundry.cache.ProductTypeCache;
import cn.fuego.laundry.constant.OrderTypeEnum;
import cn.fuego.laundry.constant.UIDimenConstant;
import cn.fuego.laundry.ui.LoginActivity;
import cn.fuego.laundry.ui.base.BaseFragment;
import cn.fuego.laundry.ui.loader.ProductLoader;
import cn.fuego.laundry.ui.more.MoreFragment;
import cn.fuego.laundry.ui.order.OrderActivity;
import cn.fuego.laundry.webservice.up.model.CreateOrderReq;
import cn.fuego.laundry.webservice.up.model.GetADReq;
import cn.fuego.laundry.webservice.up.model.GetADRsp;
import cn.fuego.laundry.webservice.up.model.base.AdvertisementJson;
import cn.fuego.laundry.webservice.up.model.base.ProductTypeJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.base.MispGridView;
import cn.fuego.misp.ui.common.MispWebViewActivity;
import cn.fuego.misp.ui.common.display.MispImageActivity;
import cn.fuego.misp.ui.common.upgrade.UpgradeActivity;
import cn.fuego.misp.ui.grid.MispGridViewAdapter;
import cn.fuego.misp.ui.model.ImageDisplayInfo;
import cn.fuego.misp.ui.model.MispGridDataModel;
import cn.fuego.misp.ui.pager.ImagePagerAdapter;
import cn.fuego.misp.ui.util.LoadImageUtil;
import cn.fuego.misp.webservice.up.model.GetClientVersionReq;
import cn.fuego.misp.webservice.up.model.GetClientVersionRsp;
import cn.fuego.misp.webservice.up.model.base.ClientVersionJson;
import cn.fuego.misp.webservice.up.model.base.CompanyJson;

public class HomeFragment extends BaseFragment implements OnClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	
	public static final String SELECT_TYPE = "selectType";
 
	private int[] buttonID = new int[]{R.id.home_btn_join_invest,R.id.home_btn_service_phone,R.id.home_btn_direct_order};
	
  

	private ViewGroup group;
	private ViewPager viewPager;
	
	private ImagePagerAdapter adapter;//new ImagePagerAdapter(this.getActivity(),group,urlList);
 
	private Timer timer;

	
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
		 
		 //check version
		 if(!AppCache.getInstance().isStarted())
		 {
			 AppCache.getInstance().setStarted(true);
			 updateVersion();	 
		 }
		 
		 new ProductLoader().load();
		 
		 
		
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
		else
		{
			initAdView(AdDataCache.getInstance().getDataList());
		}

	}

	@Override
	public void handle(MispHttpMessage message)
	{
		 if(message.isSuccess())
		 {
			GetADRsp rsp = (GetADRsp) message.getMessage().obj;
			AdDataCache.getInstance().init(rsp.getObj());

			initAdView(rsp.getObj());
		 }
		 else
		 {
			 showMessage(message);
		 }

	}
 
	private void initAdView(List<AdvertisementJson> adList)
	{
 		
		List<String> urlList = new ArrayList<String>();
		for(AdvertisementJson json : adList)
		{
			urlList.add(MemoryCache.getImageUrl()+json.getAd_img());
		}
		ImagePagerAdapter adapter = new ImagePagerAdapter(this.getActivity(),group,urlList);
	    viewPager.setAdapter(adapter);  
	   
	 
	    viewPager.setCurrentItem(0); 
	    viewPager.setOnPageChangeListener(adapter);
	    startCarouselTimer(adHandler);
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
 
				ProductCache.getInstance().getOrderInfo().getOrder().setOrder_type(OrderTypeEnum.DIRECT_ORDER.getStrValue());
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
			ImageDisplayInfo imageInfo = new ImageDisplayInfo();
			imageInfo.setTilteName("服务范围");
			imageInfo.setUrl(MemoryCache.getWebContextUrl()+MoreFragment.baseUrl+"area.html");
			//MispImageActivity.jump(this.getActivity(), imageInfo);
			MispWebViewActivity.jump(this.getActivity(), imageInfo);
  
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
	
	private void updateVersion()
	{
		GetClientVersionReq req = new GetClientVersionReq();
		WebServiceContext.getInstance().getSystemManageRest(new MispHttpHandler()
		{

			@Override
			public void handle(MispHttpMessage message)
			{
				if(message.isSuccess())
				{
					GetClientVersionRsp rsp = (GetClientVersionRsp) message.getMessage().obj;
					checkVersion(rsp.getObj());

 
				}
				else
				{
					showMessage(message);
				}
				
			}
			
			
		}).getAppVersion(req);

	}
	
	private void checkVersion(final ClientVersionJson version)
	{
		if(UpgradeActivity.isNewVision(version))
		{
	 		new AlertDialog.Builder(this.getActivity())    
	        .setTitle("版本更新").setMessage("发现新版本，是否要更新") 
	                .setPositiveButton("确定", new DialogInterface.OnClickListener()
					{

						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							 
							UpgradeActivity.jump(HomeFragment.this.getActivity(), version);
							
						}
						
						 
					})
	                .setNegativeButton("取消", null)
	                .show();
		}

	}

	  


	private void startCarouselTimer(final Handler handler)
	{
 		timer = new Timer();
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
				handler.sendEmptyMessage(index);
			}
		}, 3000, 3000);
	}
 
	
	@Override
	public void onDestroyView()
	{
		if (timer != null)
 			timer.cancel();
		super.onDestroyView();
	}




	@Override
	public void onDestroy()
	{
		if (timer != null)
			timer.cancel();

 		super.onDestroy();
	}
 


 

}
