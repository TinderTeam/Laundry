package cn.fuego.misp.ui.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.constant.MispCommonIDName;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class LoadImageUtil
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	private final Map<String, Drawable> cache = new HashMap<String, Drawable>();
 
	private static LoadImageUtil instance;
	
	private boolean isAllCache = true;
	
	private boolean isLoad = true;
	
	private boolean isLocalCache = true;
	
	private  LoadImageUtil()
	{
		
	}
	public String getLocalUrl(int id)
	{
		String imageUri = "drawable://" + id;
		return imageUri;
	}
	public DisplayImageOptions getImageSetting(int loadingImage,int faileImage)
	{
		DisplayImageOptions options;  
		options = new DisplayImageOptions.Builder()  
		// .showImageOnLoading(loadingImage) //设置图片在下载期间显示的图片  
		// .showImageForEmptyUri(faileImage)//设置图片Uri为空或是错误的时候显示的图片  
		//.showImageOnFail(faileImage)  //设置图片加载/解码过程中错误时候显示的图片
		.cacheInMemory(true)//设置下载的图片是否缓存在内存中  
		.cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中  
		.considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
		.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示  
		.bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//  
		//.decodingOptions(android.graphics.BitmapFactory.Options decodingOptions)//设置图片的解码配置  
		//.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
		//设置图片加入缓存前，对bitmap进行设置  
		//.preProcessor(BitmapProcessor preProcessor)  
		.resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位  
		.displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少  
		.displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间  
		.build();//构建完成 
		
		return options;
	}
	public DisplayImageOptions noCacheSetting()
	{
		DisplayImageOptions options;  
		options = new DisplayImageOptions.Builder()  
		// .showImageOnLoading(MispCommonIDName.drawable_loading_small_image) //设置图片在下载期间显示的图片  
		// .showImageForEmptyUri(MispCommonIDName.drawable_load_small_image_fail)//设置图片Uri为空或是错误的时候显示的图片  
		//.showImageOnFail(MispCommonIDName.drawable_ic_launcher)  //设置图片加载/解码过程中错误时候显示的图片
		.cacheInMemory(false)//设置下载的图片是否缓存在内存中  
		.cacheOnDisc(false)//设置下载的图片是否缓存在SD卡中  
		.considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
		.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示  
		.bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//  
		//.decodingOptions(android.graphics.BitmapFactory.Options decodingOptions)//设置图片的解码配置  
		//.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
		//设置图片加入缓存前，对bitmap进行设置  
		//.preProcessor(BitmapProcessor preProcessor)  
		.resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位  
		.displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少  
		.displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间  
		.build();//构建完成 
		
		return options;
	}
	public DisplayImageOptions smallImageSetting()
	{
		DisplayImageOptions options;  
		options = new DisplayImageOptions.Builder()  
		// .showImageOnLoading(MispCommonIDName.drawable_loading_small_image) //设置图片在下载期间显示的图片  
		// .showImageForEmptyUri(MispCommonIDName.drawable_load_small_image_fail)//设置图片Uri为空或是错误的时候显示的图片  
		//.showImageOnFail(MispCommonIDName.drawable_ic_launcher)  //设置图片加载/解码过程中错误时候显示的图片
		.cacheInMemory(true)//设置下载的图片是否缓存在内存中  
		.cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中  
		.considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
		.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示  
		.bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//  
		//.decodingOptions(android.graphics.BitmapFactory.Options decodingOptions)//设置图片的解码配置  
		//.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
		//设置图片加入缓存前，对bitmap进行设置  
		//.preProcessor(BitmapProcessor preProcessor)  
		.resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位  
		.displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少  
		.displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间  
		.build();//构建完成 
		
		return options;
	}
	
	public DisplayImageOptions largeImageSetting()
	{
		DisplayImageOptions options;  
		options = new DisplayImageOptions.Builder()  
		// .showImageOnLoading(MispCommonIDName.drawable_loading_large_image) //设置图片在下载期间显示的图片  
		// .showImageForEmptyUri(MispCommonIDName.drawable_load_large_image_failed)//设置图片Uri为空或是错误的时候显示的图片  
		//.showImageOnFail(MispCommonIDName.drawable_ic_launcher)  //设置图片加载/解码过程中错误时候显示的图片
		.cacheInMemory(true)//设置下载的图片是否缓存在内存中  
		.cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中  
		.considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
		//.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示  
		.bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//  
		//.decodingOptions(android.graphics.BitmapFactory.Options decodingOptions)//设置图片的解码配置  
		//.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
		//设置图片加入缓存前，对bitmap进行设置  
		//.preProcessor(BitmapProcessor preProcessor)  
		.resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位  
		.displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少  
		.displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间  
		.build();//构建完成 
		
		return options;
	}
	
	public synchronized static LoadImageUtil getInstance()
	{
		if(null == instance)
		{
			instance = new LoadImageUtil();
		}
		return instance;
	}
	
	
	public void loadImage(final ImageView imageView, final String urlString)
	{
		if(isLocalCache)
		{
			if(ValidatorUtil.isInt(urlString))
			{
				imageView.setImageResource(Integer.valueOf(urlString));

			}
			else
			{
				ImageLoader.getInstance().displayImage(urlString, imageView,smallImageSetting());
			}

		}
		else
		{
			loadWithMemoryCache(imageView, urlString);
		}
		
	}
	
	public void loadImage(final ImageView imageView, final String urlString,DisplayImageOptions options)
	{
		if(isLocalCache)
		{
			if(ValidatorUtil.isInt(urlString))
			{
				loadImageWithoutCache(imageView,Integer.valueOf(urlString));

			}
			else
			{
				ImageLoader.getInstance().displayImage(urlString, imageView,options);

			}
		}
		else
		{
			loadWithMemoryCache(imageView, urlString);
		}
		
	}
	
	public void loadImageWithoutCache(final ImageView imageView, final int localImageId)
	{
 	ImageLoader.getInstance().displayImage("drawable://"+localImageId, imageView,smallImageSetting());

	}
	
	public void loadImage(final ImageView imageView, final int localImageId)
	{
		 
		if(isLocalCache)
		{
			ImageLoader.getInstance().displayImage("drawable://"+localImageId, imageView,noCacheSetting());
		}
		else
		{
			loadWithMemoryCache(imageView, "drawable://"+localImageId);
		}
	}
	
	private void loadWithMemoryCache(final ImageView imageView, final String urlString)
	{
		if(!isLoad)
		{
			imageView.setImageResource(MispCommonIDName.drawable_load_large_image_failed);
			return;
		}
		if (isAllCache && cache.containsKey(urlString))
		{
			imageView.setImageDrawable(cache.get(urlString));
			return;
		}

		// Show a "Loading" image here
		imageView.setImageResource(MispCommonIDName.drawable_loading_large_image);

		log.info("Image url:" + urlString);

		final Handler handler = new Handler()
		{
			@Override
			public void handleMessage(Message message)
			{
				imageView.setImageDrawable((Drawable) message.obj);
			}
		};

		Runnable runnable = new Runnable()
		{
			public void run()
			{
				Drawable drawable = null;
				try
				{
					InputStream is = download(urlString);
					drawable = Drawable.createFromStream(is, "src");

					if (drawable != null)
					{
						cache.put(urlString, drawable);
					}
				} catch (Exception e)
				{
					Log.e(this.getClass().getSimpleName(),
							"Image download failed", e);
					// Show a "download fail" image
					drawable = imageView.getResources().getDrawable(
							MispCommonIDName.drawable_load_large_image_failed);
				}

				// Notify UI thread to show this image using Handler
				Message msg = handler.obtainMessage(1, drawable);
				handler.sendMessage(msg);
			}
		};
		new Thread(runnable).start();
	}
	
 

	private InputStream download(String urlString)
			throws MalformedURLException, IOException
	{
		InputStream inputStream = (InputStream) new URL(urlString).getContent();
		return inputStream;
	}
}
