package cn.fuego.laundry.ui.order;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.ui.base.BaseActivtiy;
import cn.fuego.laundry.ui.cart.CartProduct;
import cn.fuego.laundry.webservice.up.model.base.OrderJson;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.tool.MispLocation;
import cn.fuego.misp.tool.MispLocationListener;
import cn.fuego.misp.tool.MispLocationService;

public class OrderEditActivity extends BaseActivtiy 
{

	private TextView noteView;
	private TextView payOptionView;
 
	
	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initRes()
	{
		this.activityRes.setName("修改订单信息");
		this.activityRes.setAvtivityView(R.layout.order_info_edit);
		this.activityRes.getButtonIDList().add(R.id.misp_title_save);

 

	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		OrderJson order = CartProduct.getInstance().getOrderInfo().getOrder();
		if(null != order)
		{
			noteView = (TextView) findViewById(R.id.order_note_text);
			noteView.setText(order.getOrder_note());
			payOptionView = (TextView) findViewById(R.id.order_pay_option_text);
			payOptionView.setText(order.getPay_option());

 
 
		}
		
		
	}
	

	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.misp_title_save:
			{
				OrderJson order =   CartProduct.getInstance().getOrderInfo().getOrder();
				order.setPay_option(payOptionView.getText().toString().trim());
				order.setOrder_note(noteView.getText().toString().trim());
 
				this.finish();
			}
			break;
 
		}

  
	}
	
 
 


	 

}
