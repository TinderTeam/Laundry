package cn.fuego.laundry.ui;

import android.content.Intent;
import android.os.Bundle;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.R;
import cn.fuego.laundry.ui.base.BaseActivtiy;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.tool.MispLocationListener;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;

public class MainActivity extends BaseActivtiy 
{
	private FuegoLog log = FuegoLog.getLog(MainActivity.class);
	
	public static boolean isForeground = false;

	@Override
	public void initRes()
	{
		this.activityRes.setAvtivityView(R.layout.main_welcome);
		
	}
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);
		
		//init baidu map

		
		Intent intent = new Intent();
		//SharedPreferences userInfo = getSharedPreferences(SharedPreferenceConst.UESR_INFO, 0);
		 
        intent.setClass(MainActivity.this, MainTabbarActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
        startActivity(intent);
        this.finish();
 
	}

	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}
	
 

		
}
