package cn.fuego.laundry.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.misp.ui.base.MispBaseActivtiy;
import cn.fuego.misp.ui.pager.ImagePagerAdapter;

public class StartActivity extends MispBaseActivtiy 
{
	private FuegoLog log = FuegoLog.getLog(StartActivity.class);
	
	public static boolean isForeground = false;
	
	private ViewGroup group;
	private ViewPager viewPager;
	private ImageView imageView;
	private View helpView;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);
 
		imageView = (ImageView) findViewById(R.id.welcome_start_image);
		helpView = findViewById(R.id.welcome_help_layout);
		 group = (ViewGroup)findViewById(R.id.home_ad_image_group);  
		 viewPager = (ViewPager) findViewById(R.id.home_ad_image);
 		new CountDownTimer(2000, 1000)
		{

			@Override
			public void onTick(long millisUntilFinished)
			{
			}

			@Override
			public void onFinish()
			{
				if(AppCache.getInstance().isFirstStarted())
				{
				   	AppCache.getInstance().setFirstStarted(false);
				   	List<String> imageList = new ArrayList<String>();
				   	imageList.add(String.valueOf(R.drawable.help_1));
				   	imageList.add(String.valueOf(R.drawable.help_2));
				   	imageList.add(String.valueOf(R.drawable.help_3));
				   	imageList.add(String.valueOf(R.drawable.help_4));
				   	imageList.add(String.valueOf(R.drawable.help_5));
				   //	imageList.add(String.valueOf(0));

				   	initAdView(imageList);

				}
				else
				{
 
					startMain();
				}
				  
			}


		}.start();

		
	}
 
	private void startMain()
	{
		MainTabbarActivity.jump(this, MainTabbarActivity.class, 1);
 

		@SuppressWarnings("deprecation")
		int VERSION = Integer.parseInt(android.os.Build.VERSION.SDK);
		if (VERSION >= 5)
		{
			StartActivity.this.overridePendingTransition(
					R.anim.alpha_in, R.anim.alpha_out);
		}
		finish();
	}
	
	private  int curPosition = 0;
	private int status = 0;
	private void initAdView(final List<String> urlList)
	{
 
		imageView.setVisibility(View.GONE);
		helpView.setVisibility(View.VISIBLE);
		ImagePagerAdapter adapter = new ImagePagerAdapter(this,group,urlList)
		{

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2)
			{
				// TODO Auto-generated method stub
				super.onPageScrolled(arg0, arg1, arg2);
				if(arg0== urlList.size()-1)
				{
					
				}
				
				 if (curPosition == urlList.size()-1 && status ==1)
	             {
	                 //已经在最后一页还想往右划
	                  if (arg2 == 0)
	                  {
	                	 startMain();
	                   }
	             }
			}

			@Override
			public void onPageSelected(int arg0)
			{
				// TODO Auto-generated method stub
				super.onPageSelected(arg0);
				curPosition = arg0;
			}

			@Override
			public void onPageScrollStateChanged(int arg0)
			{
				// TODO Auto-generated method stub
				super.onPageScrollStateChanged(arg0);
				status = arg0;
			}
			

			 
			
		};
	    viewPager.setAdapter(adapter);  
	   
	 
	    viewPager.setCurrentItem(0); 
	    viewPager.setOnPageChangeListener(adapter);
 	}
	@Override
	public void initRes()
	{
		this.activityRes.getButtonIDList().add(R.id.welcome_go_home);
		this.activityRes.setAvtivityView(R.layout.main_welcome);
		
	}


	@Override
	public void onClick(View v)
	{
		startMain();
	}
	
	

	 

		
}
