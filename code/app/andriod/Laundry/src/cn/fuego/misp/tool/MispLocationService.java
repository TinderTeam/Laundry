package cn.fuego.misp.tool;

import android.content.Context;
import android.widget.TextView;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.cache.AppCache;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

public class MispLocationService
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	private LocationClient mLocationClient = null;
	
	private static MispLocationService instance;
	public synchronized static MispLocationService getInstance()
	{
		if(null == instance)
		{
			instance = new MispLocationService();
		}
		return instance;
		
	}
 	public void registerOne(Context context,MispLocationListener listener)
 	{
 		mLocationClient = new LocationClient(context);
		mLocationClient.registerLocationListener( listener );
		mLocationClient.start();
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);//设置定位模式
		option.setCoorType("bd09ll");//返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(500);//设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);//返回的定位结果包含地址信息
		option.setNeedDeviceDirect(true);//返回的定位结果包含手机机头的方向
		mLocationClient.setLocOption(option);
		
		int i = mLocationClient.requestLocation();
		log.info("error is "+i);
 	}

 	public void registerCycle(Context context,MispLocationListener listener)
	{
		mLocationClient = new LocationClient(context);
		mLocationClient.registerLocationListener( listener );
		mLocationClient.start();
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);//设置定位模式
		option.setCoorType("bd09ll");//返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(5000);//设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);//返回的定位结果包含地址信息
		option.setNeedDeviceDirect(true);//返回的定位结果包含手机机头的方向
		mLocationClient.setLocOption(option);
		
		int i = mLocationClient.requestLocation();
		log.info("error is "+i);
	}
 	
 	public void requestLocation()
 	{
 		int i = mLocationClient.requestLocation();
		log.info("error is "+i);
 	}
 	
 	public void setLocationAddr(Context context,final TextView view)
 	{
 		
		MispLocationService.getInstance().registerOne(context, new MispLocationListener()
		{

			@Override
			public void receiveLocation(MispLocation location)
			{
				if(null != view)
				{
					view.setText(location.getAddr());
				}
				else
				{
					log.error("the view is null");
				}
				
				
			}
 
			
		});
		requestLocation();
 	}

}
