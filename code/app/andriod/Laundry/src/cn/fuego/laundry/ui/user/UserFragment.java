package cn.fuego.laundry.ui.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.ui.LoginActivity;
import cn.fuego.laundry.ui.MainTabbarActivity;
import cn.fuego.laundry.ui.base.BaseFragment;
import cn.fuego.laundry.ui.loader.CustomerLoader;
import cn.fuego.laundry.ui.order.OrderListActivity;
import cn.fuego.laundry.webservice.up.model.base.CustomerJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.dailog.MispWaitDailog;
import cn.fuego.misp.webservice.up.model.LoginReq;

public class UserFragment extends BaseFragment implements OnClickListener
{

	private int[] buttonIDList = new int[]{R.id.user_btn_to_login};
	
	private int[] userInfoButtonList = new int[]{R.id.user_to_user_info,R.id.user_to_order,R.id.user_btn_logout};

	private View rootView;
	
	@Override
	public void initRes()
	{
		this.fragmentRes.setImage(R.drawable.tab_icon_user);
		this.fragmentRes.setName(R.string.tabbar_user);
		this.fragmentRes.getButtonIDList().add(R.id.user_btn_to_login);
		this.fragmentRes.getButtonIDList().add(R.id.user_to_user_info);
		this.fragmentRes.getButtonIDList().add(R.id.user_to_order);
		this.fragmentRes.getButtonIDList().add(R.id.user_btn_logout);
        this.fragmentRes.setFragmentView(R.layout.user_fragment);
 
 	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		rootView = super.onCreateView(inflater, container, savedInstanceState);
		
		 initView(rootView);
 
		return rootView;
	}

	private void initView(View rootView)
	{
		
			if(null != rootView)
			{
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
				View infoLayout = rootView.findViewById(R.id.user_info_layout);
				View defaultLayout = rootView.findViewById(R.id.user_default_layout);
				
				if(MemoryCache.isLogined())
				{
					infoLayout.setVisibility(View.VISIBLE);
					defaultLayout.setVisibility(View.GONE);
				}
				else
				{
					defaultLayout.setVisibility(View.VISIBLE);
					infoLayout.setVisibility(View.GONE);
				}
				 
			}



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
			final MispWaitDailog proDialog = new MispWaitDailog(this.getActivity());
			proDialog.show();
			new CustomerLoader()
			{

				@Override
				public void loadFinish(Object object)
				{
					proDialog.dismiss();
					super.loadFinish(object);

					Intent intent = new Intent();

					intent.setClass(getActivity(), UserInfoActivity.class);
					startActivityForResult(intent, 1);
					

				}

				@Override
				public void loadError(int errorCode)
				{
					proDialog.dismiss();
					// TODO Auto-generated method stub
					showMessage(errorCode);
					 if(errorCode == MISPErrorMessageConst.ERROR_LOGIN_INVALID)
					 {
						 LoginActivity.jump(UserFragment.this, 1);
					 }
				}
				
			}.loadCustomer(MemoryCache.getToken(), AppCache.getInstance().getUser());
		}
			break;
		case R.id.user_to_order:
		{
			Intent intent = new Intent();

			intent.setClass(this.getActivity(), OrderListActivity.class);
			startActivityForResult(intent, 1);

		}
			break;
		case R.id.user_btn_logout:
			{	

				LoginReq req = new LoginReq();
				req.setObj(AppCache.getInstance().getUser());
				WebServiceContext.getInstance().getUserManageRest(this).logout(req);
	
				AppCache.getInstance().clear();
				
				MainTabbarActivity.jump(this.getActivity(),UserFragment.class,1);
 

			}
		}
 
		
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		 
		
		 initView(rootView);
	}
 


}
