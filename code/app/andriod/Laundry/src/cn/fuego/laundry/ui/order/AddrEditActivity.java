package cn.fuego.laundry.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.ui.base.BaseActivtiy;
import cn.fuego.laundry.ui.cart.CartProduct;
import cn.fuego.laundry.ui.home.HomeFragment;
import cn.fuego.laundry.webservice.up.model.base.OrderJson;
import cn.fuego.laundry.webservice.up.model.base.ProductTypeJson;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.tool.MispLocationService;
import cn.fuego.misp.ui.common.edit.MispEditParameter;
import cn.fuego.misp.webservice.up.model.base.AttributeJson;

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
			takeAddr.setText(result.getDataValue());
 
 
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
