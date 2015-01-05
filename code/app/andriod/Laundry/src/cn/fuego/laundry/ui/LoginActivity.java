package cn.fuego.laundry.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.constant.SharedPreferenceConst;
import cn.fuego.laundry.ui.base.BaseActivtiy;
import cn.fuego.laundry.ui.base.ExitApplication;
import cn.fuego.laundry.ui.cart.MyCartFragment;
import cn.fuego.laundry.ui.user.UserFragment;
import cn.fuego.laundry.ui.user.UserRegisterActivity;
import cn.fuego.laundry.webservice.up.model.LoginReq;
import cn.fuego.laundry.webservice.up.model.LoginRsp;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.constant.ClientTypeEnum;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.MispHttpMessage;

public class LoginActivity extends BaseActivtiy implements OnClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	private Button loginBtn;
    private EditText textName,textPwd;
    private String 	userName,password;
    private ProgressDialog proDialog;
	private int[] buttonIDList = new int[]{R.id.user_login_submit,R.id.user_login_find_password,R.id.user_login_to_register};


	@Override
	public void initRes()
	{
		activityRes.setAvtivityView(R.layout.user_login);
		this.activityRes.setBackBtn(R.id.user_login_back);

		
	}
	
    @Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
 
		ExitApplication.getInstance().addActivity(this);
		
		textName = (EditText) findViewById(R.id.user_login_name);
		textPwd =(EditText) findViewById(R.id.user_login_password);
		for(int id : buttonIDList)
		{
			Button button = (Button) findViewById(id);
			button.setOnClickListener(this);
		}
		
       
		
	}
 
	private void checkLogin()
	{

		userName = textName.getText().toString().trim();
		// password =textPwd.getText().toString();
		password = textPwd.getText().toString().trim();
		LoginReq req = new LoginReq();
		req.getObj().setPassword(password);
		req.getObj().setUser_name(userName);
		req.setClientType(ClientTypeEnum.ANDRIOD_CLIENT.getStrValue());
		req.setClientVersion(MemoryCache.getVersion());
 
		try
		{
			WebServiceContext.getInstance().getUserManageRest(this).login(req);
 
		}
		catch(Exception e)
		{
			Toast toast = Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT);
			toast.show();
		}

	}

	@Override
	public void handle(MispHttpMessage message)
	{
		if (message.isSuccess())
		{
			LoginRsp rsp = (LoginRsp) message.getMessage().obj;

			// 存放个人信息cookie
			SharedPreferences userInfo = getSharedPreferences(SharedPreferenceConst.UESR_INFO, Context.MODE_PRIVATE);
			userInfo.edit().putString(SharedPreferenceConst.NAME, userName).commit();
			userInfo.edit().putString(SharedPreferenceConst.PASSWORD, password).commit();
			MemoryCache.setToken(rsp.getToken());
			AppCache.getInstance().setUser(rsp.getUser());
		      
			Intent intent = new Intent(this,MainTabbarActivity.class);
			intent.putExtra(MainTabbarActivity.SELECTED_TAB, MainTabbarInfo.getIndexByClass(UserFragment.class));
			this.startActivity(intent);

			LoginActivity.this.finish();

		}
		else
		{
			this.showMessage(message);
		}
		
		proDialog.dismiss();

	}
 
	public String getDeviceID()
	{
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		 return tm.getDeviceId();
	}

	@Override
	public void onClick(View v)
	{
		
		if(v.getId() == R.id.user_btn_to_login)
		{
			
		}

		switch(v.getId())
		{
			case R.id.user_login_submit:
			{
				proDialog =ProgressDialog.show(LoginActivity.this, "请稍等", "登录信息验证中……");
				checkLogin();
			}
			break;
			case R.id.user_login_find_password:
			{
				Intent i = new Intent();
				i.setClass(this, UserRegisterActivity.class);
		        this.startActivity(i);

			}
			break;
			case R.id.user_login_to_register:
			{
				Intent i = new Intent();
				i.setClass(this, UserRegisterActivity.class);
		        this.startActivity(i);
			}
			break;
			
		}

		
	}

	
 
}
