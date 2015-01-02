package cn.fuego.laundry.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import cn.fuego.laundry.R;
import cn.fuego.laundry.ui.base.BaseFragment;
import cn.fuego.misp.service.http.MispHttpMessage;

public class UserFragment extends BaseFragment implements OnClickListener
{

	
	@Override
	public void initRes()
	{
		this.fragmentRes.setImage(R.drawable.tab_icon_user);
		this.fragmentRes.setName(R.string.tabbar_user);
		this.fragmentRes.setFragmentView(R.layout.news_fragment);
 
 
		
 	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.user_fragment, null);
		ImageButton user_info_btn = (ImageButton) rootView.findViewById(R.id.user_head_btn);
		user_info_btn.setOnClickListener(this);
				
		return rootView;
	}

	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v)
	{
		Intent i = new Intent();
		i.setClass(this.getActivity(), UserInfoActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
        this.startActivity(i);
		
	}

}
