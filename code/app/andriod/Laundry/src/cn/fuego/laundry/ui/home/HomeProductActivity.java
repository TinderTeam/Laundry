package cn.fuego.laundry.ui.home;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.R;
import cn.fuego.laundry.ui.base.BaseActivtiy;
import cn.fuego.laundry.ui.util.DataConvertUtil;
import cn.fuego.laundry.webservice.up.model.base.ProductJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.list.ListViewResInfo;
import cn.fuego.misp.ui.util.LoadImageUtil;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;

public class HomeProductActivity extends BaseActivtiy 
{
	private FuegoLog log = FuegoLog.getLog(HomeProductActivity.class);

	private MapView mMapView = null;

	private ViewPager viewPager;
	private ImageView[] tips;    

	private List<ImageView> mImageViews = new ArrayList<ImageView>();  
    
	  
 	private ProductJson product;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// 在使用SDK各组件之前初始化context信息，传入ApplicationContext
		// 注意该方法要再setContentView方法之前实现
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.home_product);
		// 获取地图控件引用
		mMapView = (MapView) findViewById(R.id.bmapView);
		
		Intent intent = this.getIntent();
		
		product = (ProductJson) intent.getSerializableExtra(ListViewResInfo.SELECT_ITEM);
		TextView priceView = (TextView) findViewById(R.id.home_product_price);
		
	 
		
		priceView.setText(String.valueOf(product.getPrice()));
		List<String> urlList = new ArrayList<String>();
		urlList.add(DataConvertUtil.getAbsUrl("54856a7f2a8a7.png"));
		urlList.add(DataConvertUtil.getAbsUrl("54856b34222d9.png"));
		displayImage(urlList);
		
	}

	public static List<String> getImgStr(String htmlStr)
	{
		String img = "";
		Pattern p_image;
		Matcher m_image;
		List<String> pics = new ArrayList<String>();
		String regEx_img = "]*?>";
		p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(htmlStr);
		while (m_image.find())
		{
			img = img + "," + m_image.group();
			Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)")
					.matcher(img);
			while (m.find())
			{
				pics.add(m.group(1));
			}
		}
		return pics;
	}
	
	
	private void displayImage(List<String> imageUrlList)
	{
		 
		ViewGroup group = (ViewGroup)findViewById(R.id.home_product_image_view_group);  
        viewPager = (ViewPager) findViewById(R.id.home_product_image);  
          
 
          
        //将点点加入到ViewGroup中  
        tips = new ImageView[imageUrlList.size()];  
        for(int i=0; i<tips.length; i++)
        {  
            ImageView imageView = new ImageView(this);  
            imageView.setLayoutParams(new LayoutParams(2,2));  
            tips[i] = imageView;  
            if(i == 0){  
                tips[i].setBackgroundResource(R.drawable.image_selected);  
            }else{  
                tips[i].setBackgroundResource(R.drawable.image_unselected);  
            }  
              
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,    
                    LayoutParams.WRAP_CONTENT));  
            layoutParams.leftMargin = 5;  
            layoutParams.rightMargin = 5;  
            group.addView(imageView, layoutParams);  
        }  
          
  
 
        for(String url : imageUrlList)
        {  
            ImageView imageView = new ImageView(this);
            LoadImageUtil.getInstance().loadImage(imageView, url);
            mImageViews.add(imageView);
         }  
          
        //设置Adapter  
        viewPager.setAdapter(new MyAdapter());  
        //设置监听，主要是设置点点的背景  
        //viewPager.setOnPageChangeListener(this);  
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动  
        viewPager.setCurrentItem(0);  
	}
			
	 public class MyAdapter extends PagerAdapter{  
		  
	        @Override  
	        public int getCount() {  
	            return mImageViews.size(); 
	        }  
	  
	        @Override  
	        public boolean isViewFromObject(View arg0, Object arg1) {  
	            return arg0 == arg1;  
	        }  
	  
	        @Override  
	        public void destroyItem(View container, int position, Object object) {  
	            ((ViewPager)container).removeView(mImageViews.get(position));  
	              
	        }  
	  
	        /** 
	         * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键 
	         */  
	        @Override  
	        public Object instantiateItem(View container, int position) {  
	            ((ViewPager)container).addView(mImageViews.get(position), 0);  
	            return mImageViews.get(position);  
	        }
 
	          
	          
	          
	    }  
 


	@Override
	public void handle(MispHttpMessage message)
	{
 

	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		// 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		mMapView.onDestroy();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		mMapView.onResume();
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mMapView.onPause();
	}

}
