package cn.fuego.laundry.ui.user;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.ui.base.BaseActivtiy;
import cn.fuego.laundry.webservice.up.model.ModifyCustomerReq;
import cn.fuego.laundry.webservice.up.model.base.CustomerJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.constant.MispCommonDataSource;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.tool.MispLocationService;
import cn.fuego.misp.ui.pop.MispDataSelector;
import cn.fuego.misp.ui.pop.MispDatePicker;
import cn.fuego.misp.ui.pop.MispPopWindowListener;

public class UserEditActivity extends BaseActivtiy    
{
	private CustomerJson customer;
	private TextView userName;
	private TextView sex;

	private TextView birthday;
	private TextView phone;
	private TextView email;
	private TextView addr;
	

	@Override
	public void handle(MispHttpMessage message)
	{
		super.showMessage(message);
		if(message.isSuccess())
		{
			AppCache.getInstance().update(customer);
			this.finish();
			
		}
		
		
	}

	@Override
	public void initRes()
	{
		this.activityRes.setName("用户信息修改");
		this.activityRes.setAvtivityView(R.layout.user_info_edit);
		this.activityRes.getButtonIDList().add(R.id.misp_title_save);
		this.activityRes.getButtonIDList().add(R.id.user_set_location_addr_btn);

		
		
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		CustomerJson customer = AppCache.getInstance().getCustomer();
		if(null != customer)
		{
			userName = (TextView) findViewById(R.id.user_user_name_text);
			userName.setText(customer.getCustomer_name());
		    sex = (TextView) findViewById(R.id.user_user_sex_text);
			sex.setText(customer.getCustomer_sex());
			sex.setClickable(true);
			sex.setFocusable(false);
			sex.setOnClickListener(this);

			birthday = (TextView) findViewById(R.id.user_user_birthday_text);
			birthday.setText(customer.getBirthday());
			birthday.setClickable(true);
			birthday.setFocusable(false);
			birthday.setOnClickListener(this);

			phone = (TextView) findViewById(R.id.user_user_phone_text);
			phone.setText(customer.getPhone());

			email = (TextView) findViewById(R.id.user_user_email_text);
			email.setText(customer.getCustomer_email());

			addr = (TextView) findViewById(R.id.user_user_addr_text);
			addr.setText(customer.getAddr());
		}
		
		
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.misp_title_save:
			{
				ModifyCustomerReq req = new ModifyCustomerReq();
				customer = AppCache.getInstance().getCustomer().clone();
				
				customer.setCustomer_sex(sex.getText().toString().trim());
				customer.setCustomer_name(userName.getText().toString().trim());
				customer.setCustomer_email(email.getText().toString().trim());
				customer.setPhone(phone.getText().toString().trim());
				customer.setBirthday(birthday.getText().toString().trim());
				customer.setAddr(addr.getText().toString().trim());
				req.setObj(customer);
				
			    WebServiceContext.getInstance().getCustomerManageRest(this).modify(req);
			}
			
			break;
			case R.id.user_set_location_addr_btn:
			{
 				MispLocationService.getInstance().setLocationAddr(getApplicationContext(), addr);
			}
			break;
			case R.id.user_user_birthday_text:
			{
				showDatePickerDialog(this.birthday);
			}
			break;
			case R.id.user_user_sex_text:
			{
				
				MispDataSelector.getInstance().selectItem("性别", this, MispCommonDataSource.getSexDataSource(), this.sex);
			}
				
			break;
		default:
			break;
		}

	}
	
	public void showDatePickerDialog(final TextView view)
	{  
		MispDatePicker datePicker = new MispDatePicker(new MispPopWindowListener()
		{
			
			@Override
			public void onConfirmClick(String value)
			{
				view.setText(value);
				
			}
		});  
	    datePicker.show(getFragmentManager(), "datePicker");
  	}
 


}
