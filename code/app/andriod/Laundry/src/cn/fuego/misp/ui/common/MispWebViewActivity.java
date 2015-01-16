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

public class MispWebViewActivity extends BaseActivtiy
{

	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initRes()
	{
		this.activityRes.setName("修改密码");
		this.activityRes.setAvtivityView(R.layout.user_info_edit);
		
	}
 


}
