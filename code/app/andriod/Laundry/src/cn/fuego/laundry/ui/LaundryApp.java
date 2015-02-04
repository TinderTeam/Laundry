package cn.fuego.laundry.ui;

import java.io.File;

import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.misp.dao.SharedPreUtil;
import cn.fuego.misp.service.MemoryCache;
import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;

public class LaundryApp extends Application
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	@Override
	public void onCreate()
	{
		// TODO Auto-generated method stub
		super.onCreate();
		PackageManager packageManager = getPackageManager();

		try
		{
			SharedPreUtil.initSharedPreference(getApplicationContext());
			
			//initial image cache
			initImageCache();
			//initial baidu 
			try
			{
				SDKInitializer.initialize(getApplicationContext());
			}
			catch(Exception e)
			{
				log.error("baidu sdk initial failed");
			}


			PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
			MemoryCache.setVersionCode(packInfo.versionCode);
			MemoryCache.setVersionNname(packInfo.versionName);
			MemoryCache.setDensity(getResources().getDisplayMetrics().density);

			AppCache.getInstance().loadCompany();
			AppCache.getInstance().load();
			
 

		} catch (NameNotFoundException e)
		{
			log.info("can not get the package information");
		}
	}
	
	private void initImageCache()
	{
		File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "imageloader/Cache");  
		ImageLoaderConfiguration config = new ImageLoaderConfiguration  
			    .Builder(this)  
			    .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽  
			   // .discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null) // Can slow ImageLoader, use it carefully (Better don't use it)/设置缓存的详细信息，最好不要设置这个  
			    .threadPoolSize(30)//线程池内加载的数量  
			    .threadPriority(Thread.NORM_PRIORITY - 2)  
			    .denyCacheImageMultipleSizesInMemory()  
			    .memoryCache(new UsingFreqLimitedMemoryCache(20 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现  
			    .memoryCacheSize(20 * 1024 * 1024)    
			    .discCacheSize(100 * 1024 * 1024)    
			    .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密  
			    .tasksProcessingOrder(QueueProcessingType.LIFO)  
			    .discCacheFileCount(500) //缓存的文件数量  
			    .discCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径  
			    .defaultDisplayImageOptions(DisplayImageOptions.createSimple())  
			    .imageDownloader(new BaseImageDownloader(this, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间  
			    .writeDebugLogs() // Remove for release app  
			    .build();//开始构建  
		ImageLoader.getInstance().init(config);
	}
	
	private void initBaidulocation()
	{
		
	}

}
