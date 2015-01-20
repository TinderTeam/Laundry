package cn.fuego.laundry.ui.upgrade;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.R;
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
	private ProgressBar progressView;                       //进度条
	private TextView progress_txt;							//进度条显示
    private int progressCount = 0;                               //下载进度  
    private final int DOWNLOAD_ING = 1;                     //标记正在下载  
    private final int DOWNLOAD_OVER = 2;                    //标记下载完成 
    private final int DOWNLOAD_CANCEL = 3;                    //标记下载完成 

    private final int DOWNLOAD_ERROR = 0;                   //标记下载失败
    private boolean interceptFlag = false;  //是否取消下载 
    private AlertDialog downloadDialog;    //下载弹出框  
    
    /* 保存解析的XML信息 */  
    HashMap<String, String> mHashMap;  
    /* 下载保存路径 */  
    private String mSavePath; 
	public void initRes()
	{
		this.activityRes.setAvtivityView(R.layout.update_page);
		this.activityRes.setName("版本名称");
		this.activityRes.getButtonIDList().add(R.id.update_page_check_btn);
		Intent intent = getIntent();
		newVerInfo = (ClientVersionJson) intent.getSerializableExtra(CLIENT_INFO);
		
	}
 
 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
 
		super.onCreate(savedInstanceState);
 		
        String appName= getResources().getString(R.string.app_name);
		String versionName = AppCache.getInstance().getVersionNname();
		StringBuffer sb = new StringBuffer(); 
		sb.append(appName);
		sb.append(" ");
		sb.append(versionName);
		TextView version_desp = (TextView) findViewById(R.id.upgrade_version_txt);
		version_desp.setText(sb);
 
	}
	@Override
	public void onClick(View v)
	{
	     switch (v.getId()) 
	     {

	        case R.id.update_page_check_btn:
	        	checkVersion();
	            break;	
	        default:
	            break;
	     }
		
	}
	private void checkVersion()
	{
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
								dialog.dismiss();
	                        }
	                    }).create();//创建
	    // 显示对话框        
	    dialog.show();
	}
	//更新UI的handler  
	private Handler mhandler = new Handler() 
	{                          
		  
        @Override  
        public void handleMessage(Message msg) {  
            super.handleMessage(msg); 
           
            switch (msg.what) {  
            case DOWNLOAD_ING:  
                // 更新进度条  
            	progressView.setProgress(progressCount);  
            	progress_txt.setText("下载进度："+String.valueOf(progressCount)+"%");
                break;  
            case DOWNLOAD_OVER:    //安装   
				downloadDialog.cancel();

                down();           
                break; 
            case DOWNLOAD_ERROR:
				downloadDialog.cancel();

            	showMessage((Integer)MISPErrorMessageConst.ERROR_UPDATE_VERSION_FAILED);
            	break;
            case DOWNLOAD_CANCEL:
				downloadDialog.cancel();

            	showMessage((Integer)MISPErrorMessageConst.ERROR_UPDATE_VERSION_FAILED);
            	break;
            default:  
                break;  
            }  
            
        }  
  
    } ;
	void downFile(final String path)
	{
		android.app.AlertDialog.Builder builder = new AlertDialog.Builder(UpgradeActivity.this); 
		builder.setTitle("正在下载更新……");
		LayoutInflater inflater = LayoutInflater.from(UpgradeActivity.this); 
		View view = inflater.inflate(R.layout.upgrade_apk, null);
		progress_txt = (TextView) view.findViewById(R.id.upgrade_count_txt);
		progress_txt.setText("下载进度：0%");
		progressView = (ProgressBar) view.findViewById(R.id.uprade_progress_count);
		builder.setView(view);
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
                dialog.dismiss();  
                interceptFlag = true; 
				
			}
		});
		 downloadDialog = builder.create();  
	     downloadDialog.show(); 

		// pBar.show();
		new Thread()
		{
			public void run()
			{
				
                // 创建连接
             
				try
				{
					
				     //判断SD卡是否存在
				     if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
				     {
				    	 String sdpath = Environment.getExternalStorageDirectory() + "/";
				    	// mSavePath = sdpath + getResources().getString(R.string.app_name);//创建文件名 
				    	 mSavePath = sdpath ;
					        URL url = new URL(path);
					        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					        conn.connect();
				    	 File file = new File(mSavePath);  
			             // 判断文件目录是否存在  
			             if (!file.exists())  
			             {  
			                 file.mkdir();  
			             } 
			            File apkFile = new File(mSavePath, newVerInfo.getApk_name());  
		                FileOutputStream fileOutputStream = new FileOutputStream(apkFile); 
		                

						long length = conn.getContentLength();
						InputStream is = conn.getInputStream();
/*						FileOutputStream fileOutputStream = null;
						File file = new File(
								Environment.getExternalStorageDirectory(),
								"/"+newVerInfo.getApkName());
						fileOutputStream = new FileOutputStream(file);*/
						if (is != null)
						{
							byte[] buf = new byte[1024];
							int ch = 0;
							int count = 0;
							while (((ch = is.read(buf)) != -1))
							{
								if(interceptFlag)
								{
									mhandler.sendEmptyMessage(DOWNLOAD_CANCEL);
									return;

								}

								count += ch;
								progressCount = (int) (((float)count/length)*100);
								mhandler.sendEmptyMessage(DOWNLOAD_ING); 

								fileOutputStream.write(buf, 0, ch);
							}
							

						}
						fileOutputStream.flush();
						if (fileOutputStream != null)
						{
							fileOutputStream.close();
						}
						//down();
						mhandler.sendEmptyMessage(DOWNLOAD_OVER);
					     
				     }
				     else
				     {
				    	 log.info("SD not existed!");
				    	 Toast.makeText(UpgradeActivity.this, "SD卡不存在!", Toast.LENGTH_LONG).show();
				    	 
				     }

			 

				} catch (Exception e)
				{
					log.error("update failed",e);
					//Message message = new Message();
					//message.obj = MISPErrorMessageConst.ERROR_UPDATE_VERSION_FAILED;
					//mhandler.sendMessage(message);
					//showMessage(MISPErrorMessageConst.ERROR_VERSION_FAILED);
					mhandler.sendEmptyMessage(DOWNLOAD_ERROR);
				}  
			}

		}.start();

	}

	void down()
	{
		mhandler.post(new Runnable()
		{
			public void run()
			{
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
