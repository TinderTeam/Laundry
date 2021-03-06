package cn.fuego.laundry.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.ui.base.BaseActivtiy;
import cn.fuego.laundry.ui.user.UserRegisterActivity;
import cn.fuego.laundry.webservice.up.model.GetCustomerReq;
import cn.fuego.laundry.webservice.up.model.GetCustomerRsp;
import cn.fuego.laundry.webservice.up.model.base.CustomerJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.webservice.up.model.LoginReq;
import cn.fuego.misp.webservice.up.model.LoginRsp;
import cn.fuego.misp.webservice.up.model.base.UserJson;

public class LoginActivity extends BaseActivtiy implements OnClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
    private EditText textName,textPwd;
    private ProgressDialog proDialog;
	private int[] buttonIDList = new int[]{R.id.user_login_submit,R.id.user_login_find_password,R.id.user_login_to_register};

	public static String JUMP_SOURCE = "jump_source";

	@Override
	public void initRes()
	{
		activityRes.setAvtivityView(R.layout.user_login);
		this.activityRes.setBackBtn(R.id.user_login_back);

		
	}
	
	public static void jump(Activity activity,Class clazz,int code)
	{
		AppCache.getInstance().clear();
		Intent intent = new Intent(activity,LoginActivity.class);
		intent.putExtra(LoginActivity.JUMP_SOURCE, clazz);
		activity.startActivityForResult(intent,code);
	}
	public static void jump(Fragment fragment,int code)
	{
		AppCache.getInstance().clear();

 		Intent intent = new Intent();
 		intent.setClass(fragment.getActivity(), LoginActivity.class);
 		fragment.startActivityForResult(intent,code);
	}

	public static void jump(Activity activity,int code)
	{
		AppCache.getInstance().clear();

 		Intent intent = new Intent();
 		intent.setClass(activity, LoginActivity.class);
  		activity.startActivityForResult(intent,code);

  	}
	
    @Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
 
		//ExitApplication.getInstance().addActivity(this);
		
		textName = (EditText) findViewById(R.id.user_login_name);
		textPwd =(EditText) findViewById(R.id.user_login_password);
		for(int id : buttonIDList)
		{
			Button button = (Button) findViewById(id);
			button.setOnClickListener(this);
		}
		
		
       
		
	}
    
	@Override
	public void onClick(View v)
	{
		
 
		switch(v.getId())
		{
			case R.id.user_login_submit:
			{
				proDialog =ProgressDialog.show(this, "请稍等", "登录信息验证中……");
				String userName = textName.getText().toString().trim();
				// password =textPwd.getText().toString();
				String password = textPwd.getText().toString().trim();
				checkLogin(userName,password);
			}
			break;
			case R.id.user_login_find_password:
			{
				Intent i = new Intent();
				i.putExtra(UserRegisterActivity.OPERTATE_NAME, UserRegisterActivity.FIND_PWD);
				i.setClass(this, UserRegisterActivity.class);
		        this.startActivity(i);

			}
			break;
			case R.id.user_login_to_register:
			{
				Intent i = new Intent();
				i.putExtra(UserRegisterActivity.OPERTATE_NAME, UserRegisterActivity.REGISTER);

				i.setClass(this, UserRegisterActivity.class);
		        this.startActivity(i);
			}
			break;
			
		}

		
	}

 
	private void checkLogin(String userName,String password)
	{


		LoginReq req = new LoginReq();
		req.getObj().setPassword(password);
		req.getObj().setUser_name(userName);
   
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

 	
			if(!ValidatorUtil.isEmpty(rsp.getToken()) && null != rsp.getUser())
			{
				loadCustomer(rsp.getToken(),rsp.getUser());
			}
			else
			{
				this.showMessage(MISPErrorMessageConst.ERROR_LOGIN_FAILED);
			}


		}
		else
		{
			this.showMessage(message);
		}
		
		proDialog.dismiss();

	}
 

	private void loadCustomer(final String token,final UserJson user)
	{
		if(null == user)
		{
			log.error("the user is empty");
			return;
		}
		GetCustomerReq req = new GetCustomerReq();
		req.setToken(token);
		req.setObj(user.getUser_id());
		
		WebServiceContext.getInstance().getCustomerManageRest(new MispHttpHandler()
		{

			@Override
			public void handle(MispHttpMessage message)
			{
				if(message.isSuccess())
				{
					 
					GetCustomerRsp rsp = (GetCustomerRsp) message.getMessage().obj;
					if(null != rsp.getObj())
					{
	 					loginSuccess(token,user,rsp.getObj());

					}
					else
					{
						showMessage(MISPErrorMessageConst.ERROR_LOGIN_FAILED);
					}

				}
				else
				{
					showMessage(message);
					log.error("can not get the customer information");
				}
			}
			
			
		}).getCustomerInfo(req);
	}
	
	private void loginSuccess(String token,UserJson user,CustomerJson customer)
	{
 		AppCache.getInstance().update(token,user, customer);
 		jumpToSource();
 
 
		this.finish();
	}
	private void jumpToSource()
	{
	      
		Class clazz = (Class) this.getIntent().getSerializableExtra(JUMP_SOURCE);
		
		if(null != clazz)
		{
			MainTabbarActivity.jump(this, clazz, 1);
 
		}
		

	}
	
	public String getDeviceID()
	{
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		 return tm.getDeviceId();
	}


	
 
}
