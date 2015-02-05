package cn.fuego.misp.ui.pager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.fuego.laundry.R;
import cn.fuego.misp.ui.util.LoadImageUtil;

public class ImagePagerAdapter extends PagerAdapter implements OnPageChangeListener
{
	 private List<ImageView> imageList = new ArrayList<ImageView>();
 	 private List<String> imageUrlList;
	  List<ImageView> tips = new ArrayList<ImageView>();

	 private Context context;
	 
	 public ImagePagerAdapter(Context context,ViewGroup group,List<String> urlList)
	 {
		 this.imageUrlList = urlList;
		 this.context = context;
		 displayImage(group);
	 }
	 @Override  
     public int getCount() {  
         return imageList.size(); 
     }  

     @Override  
     public boolean isViewFromObject(View arg0, Object arg1) {  
         return arg0 == arg1;  
     }  

     @Override  
     public void destroyItem(View container, int position, Object object) {  
         ((ViewPager)container).removeView(imageList.get(position));  
           
     }  

     /** 
      * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键 
      */  
     @Override  
     public Object instantiateItem(View container, int position) {  
         
    	 if(position>=imageList.size())
    	 {
    		 position = 0;
    	 }
    	 ((ViewPager)container).addView(imageList.get(position), 0);  
         
         return imageList.get(position);  
     }
 	private void displayImage(ViewGroup group)
 	{
 		 
  
          if(null != context)
          {

              for(int i=0; i<imageUrlList.size(); i++)
              {  
                  ImageView imageView = new ImageView(context);  
                  imageView.setLayoutParams(new LayoutParams(4,4));  
                  tips.add(imageView);  
                  if(i == 0){  
                      tips.get(i).setBackgroundResource(R.drawable.image_selected);  
                  }else{  
                 	 tips.get(i).setBackgroundResource(R.drawable.image_unselected);  
                  }  
                    
                  LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,    
                          LayoutParams.WRAP_CONTENT));  
                  layoutParams.leftMargin = 5;  
                //  layoutParams.rightMargin = 5;  
                  group.addView(imageView, layoutParams);  
              }  
       
      
             for(String url : imageUrlList)
             {  
                 ImageView imageView = new ImageView(context);
                 imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                 LoadImageUtil.getInstance().loadImage(imageView, url,LoadImageUtil.getInstance().largeImageSetting());
                 imageList.add(imageView);
              }  
          }

 
   
 	}
 	 @Override  
     public void onPageSelected(int arg0) {  
         setImageBackground(arg0);  
     }  
       
     /** 
      * 设置选中的tip的背景 
      * @param selectItems 
      */  
     private void setImageBackground(int selectItems){  
         for(int i=0; i<tips.size(); i++){  
             if(i == selectItems){  
                 tips.get(i).setBackgroundResource(R.drawable.image_selected);  
             }else{  
            	 tips.get(i).setBackgroundResource(R.drawable.image_unselected);  
             }  
         }  
     }
	@Override
	public void onPageScrollStateChanged(int arg0)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2)
	{
		// TODO Auto-generated method stub
		
	} 

       
}
