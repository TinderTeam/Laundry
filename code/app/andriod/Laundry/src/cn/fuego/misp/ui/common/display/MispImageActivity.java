package cn.fuego.misp.ui.common.display;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.R;
import cn.fuego.laundry.constant.UIDimenConstant;
import cn.fuego.laundry.ui.base.BaseActivtiy;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.model.ImageDisplayInfo;
import cn.fuego.misp.ui.pager.ImagePagerAdapter;
import cn.fuego.misp.ui.util.LoadImageUtil;

public class MispImageActivity extends BaseActivtiy
{

	public static String JUMP_DATA = "jumpData";
	private ImageDisplayInfo imageInfo;
	
	private ViewGroup group;
	private ViewPager viewPager;
	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}
	
	public static void jump(Activity activity,ImageDisplayInfo imageInfo)
	{
		Intent intent = new Intent(activity,MispImageActivity.class);
 
		intent.putExtra(MispImageActivity.JUMP_DATA, imageInfo);
		activity.startActivity(intent);
	}

	@Override
	public void initRes()
	{
		imageInfo = (ImageDisplayInfo) this.getIntent().getSerializableExtra(JUMP_DATA);
		
		this.activityRes.setName(imageInfo.getTilteName());
		this.activityRes.setAvtivityView(R.layout.misp_image_display);
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//ImageView image = (ImageView)findViewById(R.id.misp_common_image);
		//image.setBackgroundResource(R.drawable.home_join_info);
		//LoadImageUtil.getInstance().loadImage(image, imageInfo.getUrl());
		
		 group = (ViewGroup)findViewById(R.id.home_ad_image_group);  
		 viewPager = (ViewPager) findViewById(R.id.home_ad_image);
		 //viewPager.getLayoutParams().height = (int) (this.getScreenWidth()*1.777);
			
		 if(null != imageInfo && !ValidatorUtil.isEmpty(imageInfo.getImageList()))
		 {
			 initAdView(imageInfo.getImageList());
		 }
		
		 
		
	}
	
	private void initAdView(List<String> urlList)
	{
 
		ImagePagerAdapter adapter = new ImagePagerAdapter(this,group,urlList);
	    viewPager.setAdapter(adapter);  
	   
	 
	    viewPager.setCurrentItem(0); 
	    viewPager.setOnPageChangeListener(adapter);
 	}
 
}
