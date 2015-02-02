package cn.fuego.laundry.ui.order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.ui.base.BaseActivtiy;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.tool.MispLocationService;
import cn.fuego.misp.ui.common.edit.MispEditParameter;

public class AddrEditActivity extends BaseActivtiy 
{

	public static String JUMP_DATA = "result";
	private TextView takeAddr;
	
	private MispEditParameter result;
 
	
	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}
	public static void jump(Activity activity,MispEditParameter parameter,int code)
	{
 		Intent intent = new Intent();
 		intent.setClass(activity, AddrEditActivity.class);
 		intent.putExtra(AddrEditActivity.JUMP_DATA, parameter);
 		activity.startActivityForResult(intent,code);

  	}
	@Override
	public void initRes()
	{
		//this.activityRes.setName("修改配送信息");
		this.activityRes.setAvtivityView(R.layout.delivery_info_edit);
		this.activityRes.getButtonIDList().add(R.id.misp_title_save);

		this.activityRes.getButtonIDList().add(R.id.set_location_addr_btn);
		this.activityRes.getButtonIDList().add(R.id.set_default_addr_btn);
		Intent intent = this.getIntent();
		result = (MispEditParameter) intent.getSerializableExtra(AddrEditActivity.JUMP_DATA);
 
		if(null != result)
		{
			this.activityRes.setName(result.getTilteName());
		}

	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
 		if(null != result)
		{
			takeAddr = (TextView) findViewById(R.id.deliver_addr_text);
  			String value = result.getDataValue();
			if(!ValidatorUtil.isEmpty(value))
			{
				takeAddr.setText(value);

			}
			if(!ValidatorUtil.isEmpty(result.getPointOut()))
			{
				takeAddr.setHint(result.getPointOut());
			}
 
		}
 		

		
		
	}
	
	public void activityFinish(MispEditParameter result)
	{
        Intent intent = new Intent();
        intent.putExtra(JUMP_DATA, result);
        /*
         * 调用setResult方法表示我将Intent对象返回给之前的那个Activity，这样就可以在onActivityResult方法中得到Intent对象，
         */
        setResult(1001, intent);

		this.finish();
	}

	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.misp_title_save:
			{
 				
  
				result.setDataValue(takeAddr.getText().toString().trim());
                activityFinish(result);
			}
			break;
			case R.id.set_default_addr_btn:
			{
				takeAddr.setText(AppCache
						.getInstance().getCustomer()
						.getAddr());
			}
			break;
			case R.id.set_location_addr_btn:
			{
				MispLocationService.getInstance().setLocationAddr(this.getApplicationContext(), takeAddr);
			}
			break;
			 
		}

  
	}
	
 
 


	 

}
