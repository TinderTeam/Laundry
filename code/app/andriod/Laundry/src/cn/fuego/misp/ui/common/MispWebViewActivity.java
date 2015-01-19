package cn.fuego.misp.ui.common;

import cn.fuego.laundry.R;
import cn.fuego.laundry.ui.base.BaseActivtiy;
import cn.fuego.misp.service.http.MispHttpMessage;

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
