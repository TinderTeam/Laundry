package cn.fuego.misp.ui.common;

import android.view.View;
import android.widget.TextView;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.ui.base.BaseActivtiy;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.webservice.up.model.ModifyPwdReq;

public class MispModifyPwdActivity extends BaseActivtiy 
{

	@Override
	public void handle(MispHttpMessage message)
	{
		super.showMessage(message);
		if(message.isSuccess())
		{
			this.finish();
		}
		
		
	}

	@Override
	public void initRes()
	{
		this.activityRes.setName("修改密码");
		this.activityRes.setAvtivityView(R.layout.misp_modify_password);
		this.activityRes.getButtonIDList().add(R.id.misp_title_save);
		
	}

	@Override
	public void onClick(View v)
	{
		ModifyPwdReq req = new ModifyPwdReq();
		TextView oldText = (TextView) findViewById(R.id.misp_old_password_text);
		TextView newText = (TextView) findViewById(R.id.misp_new_password_text);
		TextView confirmText = (TextView) findViewById(R.id.misp_confirm_password_text);
		String oldPwd = oldText.getText().toString().trim();
		String newPwd = newText.getText().toString().trim();
		String confirm = confirmText.getText().toString().trim();
		
		
		
		if(ValidatorUtil.isEmpty(oldPwd) || ValidatorUtil.isEmpty(newPwd) || ValidatorUtil.isEmpty(confirm))
		{
		    super.showMessage(MISPErrorMessageConst.ERROR_PASSWORD_IS_EMPTY);
		    return ;
		}
		if(!newPwd.equals(confirm))
		{		    
			super.showMessage(MISPErrorMessageConst.ERROR_PASSWORD_NOT_SAME);

			return;
		}
		req.setUser_name(AppCache.getInstance().getUser().getUser_name());
		req.setPwdOld(oldPwd);
		req.setPwdNew(newPwd);
		
		WebServiceContext.getInstance().getUserManageRest(this).modifyPassword(req);
	}
	
	
	
	
	
	
 


}
