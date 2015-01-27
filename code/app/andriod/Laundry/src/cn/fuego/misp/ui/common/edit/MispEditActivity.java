package cn.fuego.misp.ui.common.edit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import cn.fuego.laundry.R;
import cn.fuego.laundry.ui.base.BaseActivtiy;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.common.alipay.MispPayActivity;
import cn.fuego.misp.ui.common.alipay.MispPayParameter;
import cn.fuego.misp.ui.model.ImageDisplayInfo;
import cn.fuego.misp.ui.util.LoadImageUtil;

public class MispEditActivity extends BaseActivtiy
{

	public static final String PAY_PARAMETER = "pay_parameter";
	public static final String data;
 	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initRes()
	{
		data = (String) this.getIntent().getSerializableExtra(PAY_PARAMETER);
		this.activityRes.setName(imageInfo.getTilteName());
		this.activityRes.setAvtivityView(R.layout.misp_image_display);
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
 
	}
	
	public static void jump(Activity activity,MispPayParameter parameter)
	{
 		Intent i = new Intent();
		i.setClass(activity, MispPayActivity.class);
		i.putExtra(PAY_PARAMETER, parameter);
		activity.startActivity(i);

  	}
 


}
