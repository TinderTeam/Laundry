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
import cn.fuego.laundry.constant.SharedPreferenceConst;
import cn.fuego.laundry.ui.base.BaseActivtiy;
import cn.fuego.laundry.ui.base.ExitApplication;
import cn.fuego.laundry.webservice.up.model.LoginReq;
import cn.fuego.laundry.webservice.up.model.LoginRsp;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.constant.ClientTypeEnum;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.MispHttpMessage;

public class LoginActivity extends BaseActivtiy
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	private Button loginBtn;
    private EditText textName,textPwd;
    private String 	userName,password;
    private ProgressDialog proDialog;

    @Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ExitApplication.getInstance().addActivity(this);
		
		textName = (EditText) findViewById(R.id.txt_username);
		textPwd =(EditText) findViewById(R.id.txt_password);
		loginBtn = (Button)findViewById(R.id.login_btn);
		loginBtn.setOnClickListener(loginClick);
       
		
	}
	private OnClickListener loginClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
            
			proDialog =ProgressDialog.show(LoginActivity.this, "请稍等", "登录信息验证中……");
			checkLogin();
			

		}


	};
	private void checkLogin()
	{

		userName = textName.getText().toString().trim();
		// password =textPwd.getText().toString();
		password = textPwd.getText().toString().trim();
		LoginReq req = new LoginReq();
		req.setPassword(password);
		req.setUsername(userName);
		req.setClientType(ClientTypeEnum.ANDRIOD_CLIENT.getStrValue());
		req.setClientVersion(MemoryCache.getVersion());
		req.setDevToken(getDeviceID());

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

			Intent intent = new Intent();
			intent.setClass(LoginActivity.this, MainActivity.class);
			startActivity(intent);
			MemoryCache.setToken(rsp.getToken());
			      
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

}
