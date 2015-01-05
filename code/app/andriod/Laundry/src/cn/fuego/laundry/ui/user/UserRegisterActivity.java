package cn.fuego.laundry.ui.user;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
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
import cn.fuego.misp.webservice.up.model.SendVerifyCodeReq;
import cn.fuego.misp.webservice.up.model.SendVerifyCodeRsp;
import cn.fuego.misp.webservice.up.model.UserRegisterReq;

public class UserRegisterActivity extends MispHttpActivtiy implements
		OnClickListener
{

	private FuegoLog log = FuegoLog.getLog(getClass());
	private int[] buttonIDList = new int[]
	{ R.id.user_register_btn_submit, R.id.user_register_btn_verifyCode };

	private String verifyCode;// 验证码
	private Timer timer;// 计时器
	private Button verifyCodeBtn;
	private TextView verifyCodeView;
	private TextView phoneNumView;
	private TextView passwordView;
	private int validTime = 60;

	@Override
	public void initRes()
	{
		this.activityRes.setAvtivityView(R.layout.user_register);
		this.activityRes.setBackBtn(R.id.user_register_back);
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
 
		verifyCodeBtn = (Button) findViewById(R.id.user_register_btn_verifyCode);
		phoneNumView = (TextView) findViewById(R.id.user_register_txt_phoneNum);
		verifyCodeView = (TextView) findViewById(R.id.user_register_txt_verify_code);
		passwordView = (TextView) findViewById(R.id.user_register_txt_password);

		
		for (int id : buttonIDList)
		{
			Button button = (Button) findViewById(id);
			button.setOnClickListener(this);
		}
	}

	@Override
	public void handle(MispHttpMessage message)
	{
		if (message.isSuccess())
		{
			this.showMessage("注册成功");
 
		} else
		{
			log.error("query product failed");
			this.showMessage(message);
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
			if(!ValidatorUtil.isPhone(phoneNum))
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
			String password = passwordView.getText().toString().trim();
			if(ValidatorUtil.isEmpty(password))
			{
				this.showMessage(MISPErrorMessageConst.ERROR_PASSWORD_IS_EMPTY);
				return;
			}
			UserRegisterReq req = new UserRegisterReq();
			req.setUser_name(phoneNum);
			req.setPassword(password);
			WebServiceContext.getInstance().getUserManageRest(this).register(req);
		}
			break;
		default:
			break;

		}

	}
	
	private void getVerifyCode()
	{
		String phoneNum = this.phoneNumView.getText().toString().trim();
		if(!ValidatorUtil.isPhone(phoneNum))
		{
			this.showMessage(MISPErrorMessageConst.ERROR_PHONE_INVALID);
			return;
		}
		SendVerifyCodeReq req = new SendVerifyCodeReq();
		req.setPhone_num(phoneNum);
		WebServiceContext.getInstance().getUserManageRest(new MispHttpHandler()
		{
			@Override
			public void handle(MispHttpMessage message)
			{
				if (message.isSuccess())
				{
					SendVerifyCodeRsp rsp  = (SendVerifyCodeRsp) message.getMessage().obj;
					verifyCode = rsp.getObj();
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
		validTime = 60;
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
				verifyCode = null;
				timer.cancel();
			} else
			{
				verifyCodeBtn.setText(msg.what + "秒");
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
