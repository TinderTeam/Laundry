package cn.fuego.laundry.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.R;
import cn.fuego.misp.ui.base.MispBaseActivtiy;

public class StartActivity extends MispBaseActivtiy 
{
	private FuegoLog log = FuegoLog.getLog(StartActivity.class);
	
	public static boolean isForeground = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);
		setContentView(R.layout.main_welcome);
 
		
 		new CountDownTimer(2000, 1000)
		{

			@Override
			public void onTick(long millisUntilFinished)
			{
			}

			@Override
			public void onFinish()
			{
				  Intent intent = new Intent();
					 intent.setClass(StartActivity.this, MainTabbarActivity.class);
					 startActivity(intent);
				 

				@SuppressWarnings("deprecation")
				int VERSION = Integer.parseInt(android.os.Build.VERSION.SDK);
				if (VERSION >= 5)
				{
					StartActivity.this.overridePendingTransition(
							R.anim.alpha_in, R.anim.alpha_out);
				}
				finish();
			}
		}.start();
		
	}

	@Override
	public void initRes()
	{
		// TODO Auto-generated method stub
		
	}
	

	 

		
}
