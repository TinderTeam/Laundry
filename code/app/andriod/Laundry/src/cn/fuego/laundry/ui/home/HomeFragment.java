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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
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
import cn.fuego.laundry.ui.base.BaseFragment;
import cn.fuego.laundry.ui.util.DataConvertUtil;
import cn.fuego.laundry.webservice.up.model.GetProductListRsp;
import cn.fuego.laundry.webservice.up.model.base.ProductJson;
import cn.fuego.laundry.webservice.up.model.base.ProductTypeJson;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.list.ListViewResInfo;
import cn.fuego.misp.ui.model.CommonItemMeta;
import cn.fuego.misp.ui.util.LoadImageUtil;

public class HomeFragment extends BaseFragment implements OnClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	
	public static final String SELECT_TYPE = "selectType";
 
 
    
	private LoadImageUtil loadImageUtil = LoadImageUtil.getInstance();
	
	private int[] buttonID = new int[]{R.id.Button01,R.id.Button02,R.id.Button03,R.id.Button04,R.id.Button05,R.id.Button06,R.id.Button07,R.id.Button08};
	
	private Map<Integer,Integer> btnTypeMap = new HashMap<Integer, Integer>();
 

	@Override
	public void initRes()
	{ 
		this.fragmentRes.setImage(R.drawable.tab_icon_home);
		this.fragmentRes.setName(R.string.tabbar_home);
		this.fragmentRes.setFragmentView(R.layout.home_fragment);
		
		btnTypeMap.put(R.id.Button01, 1);
		btnTypeMap.put(R.id.Button02, 2);
		btnTypeMap.put(R.id.Button03, 3);
		btnTypeMap.put(R.id.Button04, 4);
		btnTypeMap.put(R.id.Button05, 5);
		btnTypeMap.put(R.id.Button06, 6);
		btnTypeMap.put(R.id.Button06, 7);

 	}
 
	 


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
 
		
		View rootView = inflater.inflate(R.layout.home_fragment, null);

		 setAdView(rootView);
		 for(int id : buttonID)
		 {
			Button button =  (Button) rootView.findViewById(id);
			button.setOnClickListener(this);
		 }

		 
		
		return rootView;
	}
	
	private void  setAdView(View rootView)
	{
 
		ViewGroup group = (ViewGroup)rootView.findViewById(R.id.home_ad_image_group);  
		ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.home_ad_image);
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
	}




	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}




	@Override
	public void onClick(View v)
	{
 		Intent intent = new Intent(this.getActivity(),HomeProductActivity.class);
		intent.putExtra(SELECT_TYPE, btnTypeMap.get(v.getId()));
		this.startActivity(intent);
		
	}

 
 
 


 

}
