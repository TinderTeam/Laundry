package cn.fuego.laundry.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.ui.LoginActivity;
import cn.fuego.laundry.ui.MainTabbarActivity;
import cn.fuego.laundry.ui.MainTabbarInfo;
import cn.fuego.laundry.ui.base.BaseFragment;
import cn.fuego.laundry.ui.loader.CustomerLoader;
import cn.fuego.laundry.ui.order.OrderDetailActivity;
import cn.fuego.laundry.ui.order.OrderListActivity;
import cn.fuego.laundry.webservice.up.model.base.CustomerJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.webservice.up.model.LoginReq;
import cn.fuego.misp.webservice.up.model.base.UserJson;

public class UserFragment extends BaseFragment implements OnClickListener
{

	private int[] buttonIDList = new int[]{R.id.user_btn_to_login};
	
	private int[] userInfoButtonList = new int[]{R.id.user_to_user_info,R.id.user_to_order,R.id.user_btn_logout};

	
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
			TextView nickName = (TextView) rootView.findViewById(R.id.user_nickname_txt);
			TextView phoneView = (TextView) rootView.findViewById(R.id.user_phone_txt);
			TextView sexView = (TextView) rootView.findViewById(R.id.user_sex_txt);

			CustomerJson customer = AppCache.getInstance().getCustomer();

			if(null != customer)
			{
				nickName.setText(customer.getCustomer_name());
				phoneView.setText(customer.getPhone());
				sexView.setText(customer.getCustomer_sex());
			}


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
		
		switch(v.getId())
		{
		case R.id.user_btn_to_login:
		{
			Intent intent = new Intent();

			intent.setClass(this.getActivity(), LoginActivity.class);
			intent.putExtra(LoginActivity.JUMP_SOURCE, this.getClass());
			startActivity(intent);

		}
			break;
		case R.id.user_to_user_info:
		{
			new CustomerLoader()
			{

				@Override
				public void loadFinish(Object object)
				{
					super.loadFinish(object);

					Intent intent = new Intent();

					intent.setClass(getActivity(), UserInfoActivity.class);
					startActivity(intent);

				}

				@Override
				public void loadError(int errorCode)
				{
					// TODO Auto-generated method stub
					showMessage(errorCode);
					 if(errorCode == MISPErrorMessageConst.ERROR_LOGIN_INVALID)
					 {
						 LoginActivity.jump(UserFragment.this.getActivity(), 1);
					 }
				}
				
			}.loadCustomer(MemoryCache.getToken(), AppCache.getInstance().getUser());
		}
			break;
		case R.id.user_to_order:
		{
			Intent intent = new Intent();

			intent.setClass(this.getActivity(), OrderListActivity.class);
			startActivity(intent);

		}
			break;
		case R.id.user_btn_logout:
			{	
				Intent intent = new Intent();

				LoginReq req = new LoginReq();
				req.setObj(AppCache.getInstance().getUser());
				WebServiceContext.getInstance().getUserManageRest(this).logout(req);
	
				AppCache.getInstance().clear();
				intent.setClass(this.getActivity(), MainTabbarActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	
				intent.putExtra(MainTabbarActivity.SELECTED_TAB, MainTabbarInfo.getIndexByClass(UserFragment.class));
				startActivity(intent);

			}
		}

 

        
		
	}

}
