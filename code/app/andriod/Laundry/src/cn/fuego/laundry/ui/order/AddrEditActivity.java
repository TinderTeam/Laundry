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

public class AddrEditActivity extends BaseActivtiy 
{

	private TextView takeAddr;
	private TextView deliveryAddr;
	private TextView contactName;
	private TextView contactPhone;
	
	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initRes()
	{
		this.activityRes.setName("修改配送信息");
		this.activityRes.setAvtivityView(R.layout.delivery_info_edit);
		this.activityRes.getButtonIDList().add(R.id.misp_title_save);

		this.activityRes.getButtonIDList().add(R.id.delivery_set_default_take_addr_btn);
		this.activityRes.getButtonIDList().add(R.id.delivery_set_location_take_addr_btn);
		this.activityRes.getButtonIDList().add(R.id.delivery_set_default_delivery_addr_btn);
		this.activityRes.getButtonIDList().add(R.id.delivery_set_location_delivery_addr_btn);

	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		OrderJson order = CartProduct.getInstance().getOrderInfo().getOrder();
		if(null != order)
		{
			takeAddr = (TextView) findViewById(R.id.deliver_take_addr_text);
			takeAddr.setText(order.getTake_addr());
			deliveryAddr = (TextView) findViewById(R.id.deliver_delivery_addr_text);
			deliveryAddr.setText(order.getDelivery_addr());

			contactName = (TextView) findViewById(R.id.deliver_contact_text);
			contactName.setText(order.getContact_name());

			contactPhone = (TextView) findViewById(R.id.deliver_contact_phone_text);
			contactPhone.setText(order.getPhone());
 
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
				
				order.setTake_addr(takeAddr.getText().toString().trim());
				order.setDelivery_addr(deliveryAddr.getText().toString().trim());
				order.setContact_name(contactName.getText().toString().trim());
				order.setPhone(contactPhone.getText().toString().trim());
				this.finish();
			}
			break;
			case R.id.delivery_set_default_take_addr_btn:
			{
				takeAddr.setText(AppCache
						.getInstance().getCustomer()
						.getAddr());
			}
			break;
			case R.id.delivery_set_location_take_addr_btn:
			{
				MispLocationService.getInstance().setLocationAddr(this.getApplicationContext(), takeAddr);
			}
			break;
			case R.id.delivery_set_default_delivery_addr_btn:
			{
				deliveryAddr.setText(AppCache
						.getInstance().getCustomer()
						.getAddr());
			}
			break;
			case R.id.delivery_set_location_delivery_addr_btn:
			{
				MispLocationService.getInstance().setLocationAddr(this.getApplicationContext(), deliveryAddr);
			}
			break;
		}

  
	}
	
 
 


	 

}
