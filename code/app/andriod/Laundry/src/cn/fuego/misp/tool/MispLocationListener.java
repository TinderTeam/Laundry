package cn.fuego.misp.tool;

import cn.fuego.common.log.FuegoLog;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

public abstract class MispLocationListener implements BDLocationListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());

 	public abstract void receiveLocation(MispLocation location);
 	
 	public void errorLocation()
 	{
 		log.error("location error");
 	}
	
	@Override
	public void onReceiveLocation(BDLocation location)
	{
	 
	 
		if(null != location)
		{
			MispLocation mispLocation = new MispLocation();
			mispLocation.setLatitude(location.getLatitude());
			mispLocation.setLontitude(location.getLatitude());
			mispLocation.setAddr(location.getAddrStr());
			mispLocation.setProvince(location.getProvince());
			mispLocation.setCity(location.getCity());
			log.info(mispLocation.toString());

			receiveLocation(mispLocation);
		
		}
		else
		{
			errorLocation();
		}
 
	}

	

}
