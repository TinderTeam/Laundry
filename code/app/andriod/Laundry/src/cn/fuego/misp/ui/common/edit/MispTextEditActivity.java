package cn.fuego.misp.ui.common.edit;

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
import cn.fuego.misp.webservice.up.model.base.AttributeJson;

public class MispTextEditActivity extends BaseActivtiy 
{

	public static String JUMP_DATA = "result";
	private TextView takeAddr;
	
	private AttributeJson result;
 
	
	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initRes()
	{
		//this.activityRes.setName("修改配送信息");
		this.activityRes.setAvtivityView(R.layout.misp_text_edit);
		this.activityRes.getButtonIDList().add(R.id.misp_title_save);
 
		Intent intent = this.getIntent();
		result = (AttributeJson) intent.getSerializableExtra(MispTextEditActivity.JUMP_DATA);
 

	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
 		if(null != result)
		{
			takeAddr = (TextView) findViewById(R.id.misp_text_edit_txt);
			takeAddr.setText(result.getAttrValue());
 
 
		}
		
		
	}
	
	public void activityFinish(AttributeJson result)
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
 				
  
				result.setAttrValue(takeAddr.getText().toString().trim());
                activityFinish(result);
			}
			break;
 
		}

  
	}
	
 
 


	 

}
