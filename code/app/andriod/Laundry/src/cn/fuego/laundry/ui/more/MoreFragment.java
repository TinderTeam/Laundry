package cn.fuego.laundry.ui.more;

import cn.fuego.laundry.R;
import cn.fuego.laundry.ui.base.BaseFragment;
import cn.fuego.misp.service.http.MispHttpMessage;

public class MoreFragment extends BaseFragment 
{

	
	@Override
	public void initRes()
	{
		this.fragmentRes.setImage(R.drawable.tab_icon_more);
		this.fragmentRes.setName(R.string.tabbar_more);
		this.fragmentRes.setFragmentView(R.layout.more_fragment);
		

 	}



	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}



}
