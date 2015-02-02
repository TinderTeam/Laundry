package cn.fuego.misp.ui.common.edit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.ui.base.BaseActivtiy;
import cn.fuego.laundry.ui.cart.CartProduct;
import cn.fuego.laundry.ui.home.HomeFragment;
import cn.fuego.laundry.webservice.up.model.base.OrderJson;
import cn.fuego.laundry.webservice.up.model.base.ProductTypeJson;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.tool.MispLocationService;
import cn.fuego.misp.ui.common.alipay.MispPayActivity;
import cn.fuego.misp.ui.common.alipay.MispPayParameter;
import cn.fuego.misp.webservice.up.model.base.AttributeJson;

public class MispTextEditActivity extends BaseActivtiy 
{

	public static String JUMP_DATA = "result";
	
	public static int REQUEST_CODE = 1;
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
 		intent.setClass(activity, MispTextEditActivity.class);
 		intent.putExtra(MispTextEditActivity.JUMP_DATA, parameter);
 		activity.startActivityForResult(intent,code);

  	}
	@Override
	public void initRes()
	{
		//this.activityRes.setName("修改配送信息");
		this.activityRes.setAvtivityView(R.layout.misp_text_edit);
		this.activityRes.getButtonIDList().add(R.id.misp_title_save);
 
		Intent intent = this.getIntent();
		result = (MispEditParameter) intent.getSerializableExtra(MispTextEditActivity.JUMP_DATA);
 
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
			takeAddr = (TextView) findViewById(R.id.misp_text_edit_txt);
			String value = result.getDataValue();
			if(ValidatorUtil.isEmpty(value))
			{
				value = "";
			}
			takeAddr.setText(value);
 
 
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
 				
  
				String data = takeAddr.getText().toString().trim();
				if(!result.isValid(data))
				{
					showMessage(result.getErrorMsg());
					return;
				}
				result.setDataValue(data);

                activityFinish(result);
			}
			break;
 
		}

  
	}
	
 
 


	 

}
