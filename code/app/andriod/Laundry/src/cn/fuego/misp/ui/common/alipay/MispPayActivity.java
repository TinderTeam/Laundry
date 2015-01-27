package cn.fuego.misp.ui.common.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import cn.fuego.laundry.R;
import cn.fuego.laundry.ui.MainTabbarActivity;
import cn.fuego.laundry.ui.MainTabbarInfo;
import cn.fuego.laundry.ui.cart.CartProduct;
import cn.fuego.laundry.ui.home.HomeFragment;
import cn.fuego.misp.ui.base.MispBaseActivtiy;

import com.alipay.sdk.app.PayTask;

public class MispPayActivity extends MispBaseActivtiy
{

	public static final String PARTNER = "2088811061975475";
	public static final String SELLER = "996825888@qq.com";
	public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOVjx6iMMZgkgIndiKHoerD18LWnpjzIHEA6oyWAd0UaN5iWAoDeg71PW7h+k5ZPIJ8XCzHLj9PSBKrEKg8gCjKJxH7jR1hDaodL4Vm4HgOmf6XdhFxZOM0cy3NyD4sorFqMca5r1X5EkenDYkFjXmrwKaRifDOWRfGAf6RrWzxbAgMBAAECgYEA35m6xp4Zvc9fCIRMql5eMl8aS0hnb/o0J5vA6k5mdJKQvQkE2a+NRRy1MIsZvDvXdZxVyi0+PuEKsZbT1LiLlk0xZl+EmiXDRSYR6Wsz8LpT7edRhrAJj9IusPV1TDgghVIXmkEYT5dpRLke9l1sYQFWQb4rr6vjrnPxaajbhdECQQD57+1hSYmVX6GZQuZLhnlQt/SlV7Tz45zVBmcd4ZlZ877a19WsKHMWHDQbU7QNONtJ6CiAWaLycRUUPMDmlXlzAkEA6vRBjMiD59vMrLQyWUiBEKexXeCsypvh5OIFQ2hkiBKw6gIl4QK4uEfC1LxcIg1xfc8N7bn7gUlz6tdajyaXeQJAYFhTijAdwB34HitCuRRiSXJP9TilAWrZNujb8RHY2mryREv1CwMgsgI3N92BR6OGLKw4iJmFDa33sTBmL7yo7wJBALVIPQNo+w18dBGU/3wQCzVUje+HGQtC9ypokfMOqvKqqUIE4kEYnnnhNJx7sQK9KKIPjgmshDee+wdpnf/xoNECQDg8O7/Vm23PAUaiN70t/XU2GT334yb0n6VWcdow9LaRLsajL5c8/MbsGBa8gWw0smrcIyHbS0jLe5A1Gsr0jRg=";
	public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDlY8eojDGYJICJ3Yih6Hqw9fC1p6Y8yBxAOqMlgHdFGjeYlgKA3oO9T1u4fpOWTyCfFwsxy4/T0gSqxCoPIAoyicR+40dYQ2qHS+FZuB4Dpn+l3YRcWTjNHMtzcg+LKKxajHGua9V+RJHpw2JBY15q8CmkYnwzlkXxgH+ka1s8WwIDAQAB";

	private static final int SDK_PAY_FLAG = 1;

	private static final int SDK_CHECK_FLAG = 2;
	
	public static final String PAY_PARAMETER = "pay_parameter";
	
	private MispPayParameter payParamerter;
	private TextView nameView;
	private TextView despView;
	private TextView priceView;

	public static void jump(Activity activity,MispPayParameter parameter)
	{
 		Intent i = new Intent();
		i.setClass(activity, MispPayActivity.class);
		i.putExtra(PAY_PARAMETER, parameter);
		activity.startActivity(i);

  	}
	
 
	@Override
	public void initRes()
	{
		this.activityRes.setName("在线支付");
		this.activityRes.setAvtivityView(R.layout.misp_pay_page);
		this.activityRes.getButtonIDList().add(R.id.misp_pay_submit_btn);
		payParamerter = (MispPayParameter) this.getIntent().getSerializableExtra(PAY_PARAMETER);

		
	}
	
	@Override
	public void onClick(View v)
	{
		pay();
	}



	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		nameView = (TextView) findViewById(R.id.misp_pay_order_name);
		
		nameView.setText(payParamerter.getOrder_name());
		
		despView = (TextView) findViewById(R.id.misp_pay_order_desp);
		despView.setText(payParamerter.getOrder_desc());
		priceView = (TextView) findViewById(R.id.misp_pay_order_price);
		priceView.setText(payParamerter.getOrder_price());

  
	}
	private Handler mHandler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
			case SDK_PAY_FLAG:
			{
				Result resultObj = new Result((String) msg.obj);
				String resultStatus = resultObj.resultStatus;

				// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
				if (TextUtils.equals(resultStatus, "9000"))
				{
					Toast.makeText(MispPayActivity.this, "支付成功",
							Toast.LENGTH_SHORT).show();
				} else
				{
					// 判断resultStatus 为非“9000”则代表可能支付失败
					// “8000”
					// 代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
					if (TextUtils.equals(resultStatus, "8000"))
					{
						Toast.makeText(MispPayActivity.this, "支付结果确认中",
								Toast.LENGTH_SHORT).show();

					} else
					{
						Toast.makeText(MispPayActivity.this, "支付失败",
								Toast.LENGTH_SHORT).show();

					}
				}
				break;
			}
			case SDK_CHECK_FLAG:
			{
				Toast.makeText(MispPayActivity.this, "检查结果为：" + msg.obj,
						Toast.LENGTH_SHORT).show();
				break;
			}
			default:
				break;
			}
		};
	};



	/**
	 * call alipay sdk pay. 调用SDK支付
	 * 
	 */
	public void pay()
	{
		
		String orderInfo = getOrderInfo(payParamerter);
		String sign = sign(orderInfo,payParamerter.getRsa_private());
		try
		{
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
				+ getSignType();

		Runnable payRunnable = new Runnable()
		{

			@Override
			public void run()
			{
				// 构造PayTask 对象
				PayTask alipay = new PayTask(MispPayActivity.this);
				// 调用支付接口
				String result = alipay.pay(payInfo);

				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

	/**
	 * check whether the device has authentication alipay account.
	 * 查询终端设备是否存在支付宝认证账户
	 * 
	 */
	public void check(View v)
	{
		Runnable checkRunnable = new Runnable()
		{

			@Override
			public void run()
			{
				PayTask payTask = new PayTask(MispPayActivity.this);
				boolean isExist = payTask.checkAccountIfExist();

				Message msg = new Message();
				msg.what = SDK_CHECK_FLAG;
				msg.obj = isExist;
				mHandler.sendMessage(msg);
			}
		};

		Thread checkThread = new Thread(checkRunnable);
		checkThread.start();

	}

	/**
	 * get the sdk version. 获取SDK版本号
	 * 
	 */
	public void getSDKVersion()
	{
		PayTask payTask = new PayTask(this);
		String version = payTask.getVersion();
		Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
	}

	/**
	 * create the order info. 创建订单信息
	 * 
	 */
	public String getOrderInfo(MispPayParameter payParamerter)
	{
		// 合作者身份ID
		String orderInfo = "partner=" + "\"" + PARTNER + "\"";

		// 卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + payParamerter.getOrder_code() + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + payParamerter.getOrder_name() + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + payParamerter.getOrder_desc() + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + payParamerter.getOrder_price() + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + payParamerter.getNotify_url()
				+ "\"";

		// 接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}

 

	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param content
	 *            待签名订单信息
	 */
	public String sign(String content,String rsa_private)
	{
		return SignUtils.sign(content, rsa_private);
	}

	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 */
	public String getSignType()
	{
		return "sign_type=\"RSA\"";
	}


}
