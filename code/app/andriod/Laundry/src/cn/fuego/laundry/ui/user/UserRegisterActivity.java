package cn.fuego.laundry.ui.user;

import java.util.Timer;
import java.util.TimerTask;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.R;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.base.MispHttpActivtiy;
import cn.fuego.misp.ui.dailog.MispWaitDailog;
import cn.fuego.misp.webservice.up.model.SendVerifyCodeReq;
import cn.fuego.misp.webservice.up.model.SendVerifyCodeRsp;
import cn.fuego.misp.webservice.up.model.UserRegisterReq;

public class UserRegisterActivity extends MispHttpActivtiy  
{

	private FuegoLog log = FuegoLog.getLog(getClass());
 
	private String verifyCode;// 验证码
	private Timer timer;// 计时器
	private Button verifyCodeBtn;
	private TextView verifyCodeView;
	private TextView phoneNumView;
	private TextView passwordView;
	
	public static final int SEND_CYCLE_TIME = 60;
	
	//public static final int VALID
	
	private int validTime = SEND_CYCLE_TIME;
	
	public static final String OPERTATE_NAME = "operate";
	public static final int REGISTER = 0;
	public static final int FIND_PWD = 1;
	private  int operate = 0;
	
	private long lastCodeTime = 0;
	private String sendPhoneNum;

	private MispWaitDailog proDialog;
	@Override
	public void initRes()
	{
		operate = this.getIntent().getIntExtra(OPERTATE_NAME, 0);
		if(operate == REGISTER)
		{
			this.activityRes.setName("用户注册");
		}
		else
		{
			this.activityRes.setName("重置密码");
		}
		this.activityRes.setAvtivityView(R.layout.user_register);
		this.activityRes.getButtonIDList().add(R.id.user_register_btn_verifyCode);
		this.activityRes.getButtonIDList().add(R.id.user_register_btn_submit);
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
 
		verifyCodeBtn = (Button) findViewById(R.id.user_register_btn_verifyCode);
		phoneNumView = (TextView) findViewById(R.id.user_register_txt_phoneNum);
		//默认聚焦
		phoneNumView.requestFocus();
		phoneNumView.requestFocusFromTouch();
		verifyCodeView = (TextView) findViewById(R.id.user_register_txt_verify_code);
		passwordView = (TextView) findViewById(R.id.user_register_txt_password);

		
 
	}

	@Override
	public void handle(MispHttpMessage message)
	{
		this.showMessage(message);

		if (message.isSuccess())
		{
			this.finish();
 
		}  

	}

	@Override
	public void onClick(View v)
	{
		int id = v.getId();
		switch (id)
		{
		case R.id.user_register_btn_verifyCode:
		{
			 getVerifyCode();
		}
			break;
		case R.id.user_register_btn_submit:
		{
			String phoneNum = phoneNumView.getText().toString().trim();
			if(!ValidatorUtil.isMobile(phoneNum))
			{
				this.showMessage(MISPErrorMessageConst.ERROR_PHONE_INVALID);
				return;
			}

			String code = verifyCodeView.getText().toString().trim();
			if(!isVerifyCodeValid(code))
			{
				this.showMessage(MISPErrorMessageConst.ERROR_VERIFY_CODE_INVALID);
				return;
			}
			
			if(!phoneNum.equals(this.sendPhoneNum))
			{
				this.showMessage(MISPErrorMessageConst.ERROR_VERIFY_CODE_INVALID);
				return;
			}
			
			String password = passwordView.getText().toString().trim();
			if(ValidatorUtil.isEmpty(password))
			{
				this.showMessage(MISPErrorMessageConst.ERROR_PASSWORD_IS_EMPTY);
				return;
			}
			if(password.length()<6 || password.length()>20)
			{
				this.showMessage("请输入6-20位新密码");
				return;
			}
			UserRegisterReq req = new UserRegisterReq();
			req.setUser_name(phoneNum);
			req.setPassword(password);
			if(REGISTER == operate)
			{
				WebServiceContext.getInstance().getUserManageRest(this).register(req);
			}
			else
			{
				WebServiceContext.getInstance().getUserManageRest(this).resetPassword(req);
			}
		}
			break;
		default:
			break;

		}

	}
	
	private void getVerifyCode()
	{
		String phoneNum = this.phoneNumView.getText().toString().trim();
		if(!ValidatorUtil.isMobile(phoneNum))
		{
			this.showMessage(MISPErrorMessageConst.ERROR_PHONE_INVALID);
			return;
		}
		SendVerifyCodeReq req = new SendVerifyCodeReq();
		req.setPhone_num(phoneNum);
		this.sendPhoneNum = phoneNum;
		proDialog  = new MispWaitDailog(this);
		proDialog.show();

		WebServiceContext.getInstance().getUserManageRest(new MispHttpHandler()
		{
			@Override
			public void handle(MispHttpMessage message)
			{
				proDialog.dismiss();
				if (message.isSuccess())
				{
					SendVerifyCodeRsp rsp  = (SendVerifyCodeRsp) message.getMessage().obj;
					verifyCode = rsp.getObj();
					lastCodeTime = System.currentTimeMillis();
					startVerifyTimer();

				} else
				{
					log.error("get verify code failed");
					showMessage(message);
				}
				
			}
		}
		).sendVerifyCode(req);

	}
	
	private void startVerifyTimer()
	{
		validTime = SEND_CYCLE_TIME;
		verifyCodeBtn.setEnabled(false);
		timer = new Timer();
		timer.schedule(new TimerTask()
		{

			@Override
			public void run()
			{
				handler.sendEmptyMessage(validTime--);
			}
		}, 0, 1000);
	}

	private boolean isVerifyCodeValid(String code)
	{
		if(ValidatorUtil.isEmpty(code))
		{
			return false;
			
		}
		if(!code.equals(this.verifyCode))
		{
			return false;
		}
		long nowTime = System.currentTimeMillis();
		if((nowTime - lastCodeTime)>10*60*1000)
		{
			return false;
		}
		return true;
	}
	private Handler handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			if (msg.what == 0)
			{
				verifyCodeBtn.setEnabled(true);
				verifyCodeBtn.setText("获取验证码");
				//verifyCode = null;
				timer.cancel();
			} else
			{
 				verifyCodeBtn.setText(msg.what+"秒");
			}
		};
	};

	@Override
	public void onDestroy()
	{
		if (timer != null)
			timer.cancel();
		super.onDestroy();
	}


}
