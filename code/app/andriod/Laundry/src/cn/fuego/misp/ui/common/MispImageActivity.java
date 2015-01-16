package cn.fuego.misp.ui.common;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.constant.ListItemTypeConst;
import cn.fuego.laundry.ui.base.BaseActivtiy;
import cn.fuego.laundry.webservice.up.model.base.CustomerJson;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.list.MispDistinctListActivity;
import cn.fuego.misp.ui.model.CommonItemMeta;
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
		
		LoadImageUtil.getInstance().loadImage(image, imageInfo.getUrl());
	}
	
	
 


}
