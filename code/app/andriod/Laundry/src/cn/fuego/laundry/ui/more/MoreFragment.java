package cn.fuego.laundry.ui.more;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import cn.fuego.laundry.R;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.common.MispImageActivity;
import cn.fuego.misp.ui.common.upgrage.UpgradeActivity;
import cn.fuego.misp.ui.info.MispInfoListFragment;
import cn.fuego.misp.ui.model.CommonItemMeta;
import cn.fuego.misp.ui.model.ImageDisplayInfo;
import cn.fuego.misp.ui.util.LoadImageUtil;
import cn.fuego.misp.webservice.up.model.GetClientVersionReq;
import cn.fuego.misp.webservice.up.model.GetClientVersionRsp;

public class MoreFragment extends MispInfoListFragment
{

	private static String JOIN_US= "加入我们";
	private static String UPDATE_VERSION= "版本更新";
	private static String APP_INFO= "快客介绍";

	
	@Override
	public void initRes()
	{
		super.initRes();
 		this.fragmentRes.setImage(R.drawable.tab_icon_more);
		this.fragmentRes.setName(R.string.tabbar_more);
		
		this.getDataList().clear();
		this.getDataList().addAll(getMetaData());
 
 	}
	
	
	private List<CommonItemMeta> getMetaData()
	{
		List<CommonItemMeta> metaList = new ArrayList<CommonItemMeta>();
		this.getDataList().clear();
		
		metaList.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, APP_INFO ,"点击"));
		metaList.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, JOIN_US ,"点击"));
		metaList.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, UPDATE_VERSION,"点击"));
 

		return metaList;
 	}

 
	
	@Override
	public void onItemListClick(AdapterView<?> parent, View view, long id,
			CommonItemMeta item)
	{
		if(UPDATE_VERSION.equals(item.getTitle()))
		{
			updateVersion();

		}
		else if(JOIN_US.equals(item.getTitle()))
		{ 		
			Intent intent =  new Intent(this.getActivity(),MispImageActivity.class);
			ImageDisplayInfo imageInfo = new ImageDisplayInfo();
			imageInfo.setTilteName(item.getTitle());
			imageInfo.setUrl(LoadImageUtil.getInstance().getLocalUrl(R.drawable.home_join_info));
			intent.putExtra(MispImageActivity.JUMP_DATA, imageInfo);
			this.startActivity(intent);
		}
		else
		{
			Intent intent =  new Intent(this.getActivity(),MispImageActivity.class);
			ImageDisplayInfo imageInfo = new ImageDisplayInfo();
			imageInfo.setTilteName(item.getTitle());
			imageInfo.setUrl(LoadImageUtil.getInstance().getLocalUrl(R.drawable.home_join_info));
			intent.putExtra(MispImageActivity.JUMP_DATA, imageInfo);
			this.startActivity(intent);
		}
	}
	private void updateVersion()
	{
		GetClientVersionReq req = new GetClientVersionReq();
		WebServiceContext.getInstance().getSystemManageRest(new MispHttpHandler()
		{

			@Override
			public void handle(MispHttpMessage message)
			{
				if(message.isSuccess())
				{
					GetClientVersionRsp rsp = (GetClientVersionRsp) message.getMessage().obj;
					Intent intent = new Intent(getActivity(),UpgradeActivity.class);
					intent.putExtra(UpgradeActivity.CLIENT_INFO, (Serializable) rsp.getObj());

					startActivity(intent);
				}
				else
				{
					showMessage(message);
				}
				
			}
			
			
		}).getAppVersion(req);

	}

	


}
