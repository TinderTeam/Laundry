package cn.fuego.laundry.ui;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.cache.AppCache;
import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

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
			PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
			AppCache.getInstance().setVersionCode(packInfo.versionCode);
			AppCache.getInstance().setVersionNname(packInfo.versionName);
			AppCache.getInstance().loadCompany();

		} catch (NameNotFoundException e)
		{
			log.info("can not get the package information");
		}
	}

}
