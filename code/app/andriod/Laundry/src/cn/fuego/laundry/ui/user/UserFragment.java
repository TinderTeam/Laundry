package cn.fuego.laundry.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.ui.LoginActivity;
import cn.fuego.laundry.ui.base.BaseFragment;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.MispHttpMessage;

public class UserFragment extends BaseFragment implements OnClickListener
{

	private int[] buttonIDList = new int[]{R.id.user_btn_to_login};
	
	private int[] userInfoButtonList = new int[]{R.id.user_to_user_info};

	
	@Override
	public void initRes()
	{
		this.fragmentRes.setImage(R.drawable.tab_icon_user);
		this.fragmentRes.setName(R.string.tabbar_user);
		if(MemoryCache.isLogined())
		{
			this.fragmentRes.setFragmentView(R.layout.user_fragment);
		}
		else
		{
			this.fragmentRes.setFragmentView(R.layout.user_fragment_default);
		}
 	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View rootView = super.onCreateView(inflater, container, savedInstanceState);
		if(MemoryCache.isLogined())
		{
			for(int id : userInfoButtonList)
			{
				Button button = (Button) rootView.findViewById(id);
				button.setOnClickListener(this);
			}
			TextView view = (TextView) rootView.findViewById(R.id.user_user_name);
			view.setText(AppCache.getInstance().getUser().getUser_name());
			
		}
		else
		{
			for(int id : buttonIDList)
			{
				Button button = (Button) rootView.findViewById(id);
				button.setOnClickListener(this);
			}
		}
 
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
		
		switch(v.getId())
		{
		case R.id.user_btn_to_login:
			i.setClass(this.getActivity(), LoginActivity.class);
		 
			break;
		case R.id.user_to_user_info:
			i.setClass(this.getActivity(), UserInfoActivity.class);
			break;
		case R.id.user_to_order:
			break;
		
		}
		this.startActivity(i);

 

        
		
	}

}
