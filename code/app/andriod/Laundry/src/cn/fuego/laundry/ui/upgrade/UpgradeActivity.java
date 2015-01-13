package cn.fuego.laundry.ui.upgrade;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.ui.base.MispBaseActivtiy;
import cn.fuego.misp.webservice.up.model.base.ClientVersionJson;

public class UpgradeActivity extends MispBaseActivtiy
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	public static final String CLIENT_INFO = "client_info";
	private ClientVersionJson newVerInfo;
	private ProgressDialog 	 pBar ;
 
	
	 
	public void initRes()
	{
 
		
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		 initRes();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
		newVerInfo = (ClientVersionJson) intent.getSerializableExtra(CLIENT_INFO);
		  
        int vercode = AppCache.getInstance().getVersionCode();
        if (null != newVerInfo && newVerInfo.getVersion_code() > vercode) {  
            doNewVersionUpdate(); // 更新新版本  
        } else {  
            notNewVersionShow(); // 提示当前为最新版本  
        }  
	}


	private void notNewVersionShow() {  
	    int verCode = AppCache.getInstance().getVersionCode();
	    String verName = AppCache.getInstance().getVersionNname();
	    StringBuffer sb = new StringBuffer();  
	    sb.append("当前版本:");  
	    sb.append(verName);  
	    sb.append(" Code:");  
	    sb.append(verCode);  	
	    sb.append(",\n已是最新版,无需更新!");  
	    Dialog dialog = new AlertDialog.Builder(this).setTitle("软件更新")  
	            .setMessage(sb.toString())// 设置内容  
	            .setPositiveButton("确定",// 设置确定按钮  
	                    new DialogInterface.OnClickListener() {  
	                        @Override  
	                        public void onClick(DialogInterface dialog,  
	                                int which) {  
	                            finish();  
	                        }  
	                    }).create();// 创建  
	    // 显示对话框  
	    dialog.show();  
	}  
	private void doNewVersionUpdate() 
	{
	    int verCode = AppCache.getInstance().getVersionCode();
	    String verName = AppCache.getInstance().getVersionNname();
	    StringBuffer sb = new StringBuffer();
	    sb.append("当前版本:");
	    sb.append(verName);
	    sb.append(" Code:");
	    sb.append(verCode);
	    sb.append(", 发现新版本");
	    sb.append(newVerInfo.getVersion_name());
	    sb.append(" Code:");
	    sb.append(newVerInfo.getVersion_code());
	    sb.append(",是否更新?");
	    Dialog dialog = new AlertDialog.Builder(UpgradeActivity.this)
	            .setTitle("软件更新")
	            .setMessage(sb.toString())
	            // 设置内容
	            .setPositiveButton("更新",// 设置确定按钮
	                    new DialogInterface.OnClickListener() { 
	 
	                        @Override
	                        public void onClick(DialogInterface dialog,
	                                int which) {

	                            downFile(MemoryCache.getWebContextUrl()+newVerInfo.getApk_url());
	                        } 
	 
	                    })
	            .setNegativeButton("暂不更新",
	                    new DialogInterface.OnClickListener() { 
	                        public void onClick(DialogInterface dialog,
	                                int whichButton) {
	                            // 点击"取消"按钮之后退出程序
	                            finish();
	                        }
	                    }).create();//创建
	    // 显示对话框        
	    dialog.show();
	}
 
	private Handler handler = new Handler()
	{

		@Override
		public void handleMessage(Message msg)
		{
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			showMessage((Integer)msg.obj);
		}
		
	};
	void downFile(final String path)
	{
		pBar = new ProgressDialog(UpgradeActivity.this);
		pBar.setTitle("正在下载");
		pBar.setMessage("请稍候...");
		pBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);

		// pBar.show();
		new Thread()
		{
			public void run()
			{
				
                // 创建连接
             
				try
				{
					URL url = new URL(path);
					 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					 conn.connect();
			 
					long length = conn.getContentLength();
					InputStream is = conn.getInputStream();
					FileOutputStream fileOutputStream = null;
					if (is != null)
					{

						File file = new File(
								Environment.getExternalStorageDirectory(),
								newVerInfo.getApk_name());
						fileOutputStream = new FileOutputStream(file);

						byte[] buf = new byte[1024];
						int ch = -1;
						int count = 0;
						while ((ch = is.read(buf)) != -1)
						{
							fileOutputStream.write(buf, 0, ch);
							count += ch;
							if (length > 0)
							{
							}
						}

					}
					fileOutputStream.flush();
					if (fileOutputStream != null)
					{
						fileOutputStream.close();
					}
					down();
				} catch (Exception e)
				{
					log.error("update failed",e);
					Message message = new Message();
					message.obj = MISPErrorMessageConst.ERROR_VERSION_FAILED;
					handler.sendMessage(message);
					//showMessage(MISPErrorMessageConst.ERROR_VERSION_FAILED);
				}  
			}

		}.start();

	}

	void down()
	{
		handler.post(new Runnable()
		{
			public void run()
			{
				pBar.cancel();
				update();
			}
		});

	}

	void update()
	{

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(Environment
				.getExternalStorageDirectory(), newVerInfo.getApk_name())),
				"application/vnd.android.package-archive");
		startActivity(intent);
	}

}
