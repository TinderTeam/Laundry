package cn.fuego.misp.ui.common;

import android.os.Bundle;
import android.widget.ImageView;
import cn.fuego.laundry.R;
import cn.fuego.laundry.ui.base.BaseActivtiy;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.model.ImageDisplayInfo;
import cn.fuego.misp.ui.util.LoadImageUtil;

public class MispImageActivity extends BaseActivtiy
{

	public static String JUMP_DATA = "jumpData";
	private ImageDisplayInfo imageInfo;
	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initRes()
	{
		imageInfo = (ImageDisplayInfo) this.getIntent().getSerializableExtra(JUMP_DATA);
		this.activityRes.setName(imageInfo.getTilteName());
		this.activityRes.setAvtivityView(R.layout.misp_image_display);
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ImageView image = (ImageView)findViewById(R.id.misp_common_image);
		//image.setBackgroundResource(R.drawable.home_join_info);
		LoadImageUtil.getInstance().loadImage(image, imageInfo.getUrl());
	}
	
	
 


}
