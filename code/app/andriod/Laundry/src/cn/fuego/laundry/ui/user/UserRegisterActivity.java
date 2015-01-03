package cn.fuego.laundry.ui.user;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import cn.fuego.laundry.R;
import cn.fuego.laundry.webservice.up.model.SendVerifyCodeReq;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.base.MispHttpActivtiy;

public class UserRegisterActivity extends MispHttpActivtiy implements OnClickListener
{

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_register);
	}

	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v)
	{
		int id = v.getId();
		switch(id)
		{
			case R.id.user_register_btn_verifyCode:
			{
				SendVerifyCodeReq req = new SendVerifyCodeReq();
				WebServiceContext.getInstance().getUserManageRest(this).sendVerifyCode(req);
			}
			break;
			case R.id.user_register_btn_submit:
			{
				SendVerifyCodeReq req = new SendVerifyCodeReq();
				WebServiceContext.getInstance().getUserManageRest(this).sendVerifyCode(req);
			}
			break;
			default:
				break;
		
		}
		
	}

}
