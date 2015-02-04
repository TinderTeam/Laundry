package cn.fuego.misp.ui.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cn.fuego.laundry.R;
import cn.fuego.misp.ui.base.MispBaseActivtiy;
import cn.fuego.misp.ui.model.ImageDisplayInfo;

public class MispWebViewActivity extends MispBaseActivtiy
{
	public static String JUMP_DATA = "jumpData";

	private WebView descriptionView;
	
	private ImageDisplayInfo imageInfo;

 

	@Override
	public void initRes()
	{
		imageInfo = (ImageDisplayInfo) this.getIntent().getSerializableExtra(JUMP_DATA);

		this.activityRes.setName(imageInfo.getTilteName());
		this.activityRes.setAvtivityView(R.layout.misp_web_display);
 		
	}
	
	public static void jump(Activity activity,ImageDisplayInfo displayInfo)
	{
		Intent intent = new Intent(activity,MispWebViewActivity.class);
		intent.putExtra(JUMP_DATA, displayInfo);

		activity.startActivity(intent);
	}
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		descriptionView = (WebView) findViewById(R.id.misp_web_view);

		//descriptionView.getSettings().setTextSize(WebSettings.TextSize.LARGER);
		descriptionView.getSettings().setJavaScriptEnabled(true);
		//descriptionView.loadData(imageInfo.getUrl(), "text/html; charset=UTF-8", null);
		
		descriptionView.setWebViewClient(new WebViewClient(){
             @Override
             public boolean shouldOverrideUrlLoading(WebView view, String url){
                      
                     return false;
                      
             }
     });
		descriptionView.loadUrl(imageInfo.getUrl());
		
	}
	
	
 


}
