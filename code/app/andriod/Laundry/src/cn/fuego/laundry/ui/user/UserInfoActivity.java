package cn.fuego.laundry.ui.user;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.webservice.up.model.base.CustomerJson;
import cn.fuego.misp.ui.common.MispModifyPwdActivity;
import cn.fuego.misp.ui.info.MispInfoListActivity;
import cn.fuego.misp.ui.model.CommonItemMeta;

public class UserInfoActivity extends MispInfoListActivity  
{

	private static String MODIFY_INFO = "修改信息";
	private static String MODIFY_PASSWORD = "修改密码";

	@Override
	public void initRes()
	{
		super.initRes();
		this.activityRes.setName("我的资料");
		this.getDataList().clear();
		this.getDataList().addAll(getBtnData());

	}
	
 
	private List<CommonItemMeta> getBtnData()
	{
		CustomerJson customer = AppCache.getInstance().getCustomer();
		List<CommonItemMeta> list = new ArrayList<CommonItemMeta>();

		if(null != customer)
		{
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "用户名", customer.getUser_name()));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "会员卡号", customer.getCard_number()));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "积分", customer.getScore()));
			
			list.add(new CommonItemMeta(CommonItemMeta.DIVIDER_ITEM, null, null));

			list.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, MODIFY_INFO, MODIFY_INFO));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "真实姓名", customer.getCustomer_name()));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "性别", customer.getCustomer_sex()));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "出生年月", customer.getBirthday()));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "手机号码", customer.getPhone()));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "邮箱", customer.getCustomer_email()));

			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "常用地址", customer.getAddr()));
			
			
			list.add(new CommonItemMeta(CommonItemMeta.DIVIDER_ITEM, null, null));
			list.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, MODIFY_PASSWORD, MODIFY_PASSWORD));
			list.add(new CommonItemMeta(CommonItemMeta.DIVIDER_ITEM, null, null));
			list.add(new CommonItemMeta(CommonItemMeta.DIVIDER_ITEM, null, null));

			
		}
	 
		
		return list;
	}

 

	
 	@Override
	public void onItemListClick(AdapterView<?> parent, View view, long id,
			CommonItemMeta item)
	{
		if(CommonItemMeta.BUTTON_TO_EDIT_ITEM == item.getLayoutType())
		{
			String content = (String) item.getContent();
			Intent intent = new Intent();
			if(MODIFY_INFO.equals(content))
			{
				intent.setClass(this, UserEditActivity.class);
			}
			else if(MODIFY_PASSWORD.equals(content))
			{
				intent.setClass(this, MispModifyPwdActivity.class);
			}
			this.startActivityForResult(intent,1);
		}
	}

 

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		this.refreshList(this.getBtnData());
		 
	}
	
	 
 


}
