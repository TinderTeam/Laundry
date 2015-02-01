package cn.fuego.laundry.ui.more;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.R;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.constant.MispCommonIDName;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.common.MispImageActivity;
import cn.fuego.misp.ui.common.upgrade.UpgradeActivity;
import cn.fuego.misp.ui.info.MispInfoListFragment;
import cn.fuego.misp.ui.model.CommonItemMeta;
import cn.fuego.misp.ui.model.ImageDisplayInfo;
import cn.fuego.misp.ui.util.LoadImageUtil;
import cn.fuego.misp.webservice.up.model.GetClientVersionReq;
import cn.fuego.misp.webservice.up.model.GetClientVersionRsp;

public class MoreFragment extends MispInfoListFragment
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	private static String JOIN_US= "加入我们";
	private static String UPDATE_VERSION= "版本更新";
	private static String APP_INFO= "快客介绍";
	private static String PROTOCOL="洗涤协议";
	private static String ATTENTION="注意事项";
	private static String HELP="新手指引";

	
	@Override
	public void initRes()
	{
		super.initRes();
 		this.fragmentRes.setImage(R.drawable.tab_icon_more);
		this.fragmentRes.setName(R.string.tabbar_more);
		
		this.fragmentRes.setFragmentView(R.layout.more);
		this.listViewRes.setListView(R.id.misp_info_list);
		this.listViewRes.setListItemView(R.layout.more_list_item);
		
		this.getDataList().clear();
		this.getDataList().addAll(getMetaData());
 
 	}
	
	
	private List<CommonItemMeta> getMetaData()
	{
		List<CommonItemMeta> metaList = new ArrayList<CommonItemMeta>();
		this.getDataList().clear();
		
		metaList.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, APP_INFO ,null));
		metaList.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, JOIN_US ,null));
		metaList.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, PROTOCOL ,null));
		metaList.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, ATTENTION ,null));
		metaList.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, HELP ,null));

		metaList.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, UPDATE_VERSION,null));
 

		return metaList;
 	}

	public View getListItemView(LayoutInflater inflater,View convertView,CommonItemMeta item)
	{
 		View view =null;
		int type= item.getLayoutType();
		String title= "";
		if(!ValidatorUtil.isEmpty(item.getTitle()))
		{
			title = item.getTitle();
		}
		String content = "";
		if(null != item.getContent())
		{
			content = String.valueOf(item.getContent());
		}
				
		
		switch(type)
		{
 
		case CommonItemMeta.BUTTON_TO_EDIT_ITEM:
			{
				view = inflater.inflate(R.layout.more_list_item, null);
				TextView title_view = (TextView) view.findViewById(R.id.item_btntype_name);
				if(null != title_view)
				{
					title_view.setText(title);
				}
				else
				{
					log.warn("can not find text view by id, the is item_btntype_name " + MispCommonIDName.item_btntype_name);
				}
				
				TextView valueView  = (TextView) view.findViewById(MispCommonIDName.item_btntype_value);
				if(null != valueView)
				{
					valueView.setText(content);	
				}
				else
				{
					log.warn("can not find text view by id, the is item_btntype_value " + MispCommonIDName.item_btntype_name);
				}
				
				
			}
			break;
	    default:
	    	throw new MISPException("the item layout can not be empty");
	    	 
 
		}
		 
		return view;
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
