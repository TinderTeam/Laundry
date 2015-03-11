package cn.fuego.laundry.ui.more;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.R;
import cn.fuego.laundry.webservice.up.model.GetADReq;
import cn.fuego.laundry.webservice.up.model.GetADRsp;
import cn.fuego.laundry.webservice.up.model.base.AdvertisementJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.constant.MispCommonIDName;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.common.MispWebViewActivity;
import cn.fuego.misp.ui.common.display.MispImageActivity;
import cn.fuego.misp.ui.common.upgrade.UpgradeActivity;
import cn.fuego.misp.ui.dailog.MispWaitDailog;
import cn.fuego.misp.ui.info.MispInfoListFragment;
import cn.fuego.misp.ui.model.CommonItemMeta;
import cn.fuego.misp.ui.model.ImageDisplayInfo;
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
	private static String AREA="服务范围";
	
	public static String baseUrl = "/Laundry.php/ADManage/";

	
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
		
		metaList.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, APP_INFO ,MemoryCache.getWebContextUrl()+baseUrl+"introduct.html"));
		metaList.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, JOIN_US ,MemoryCache.getWebContextUrl()+baseUrl+"joinUS.html"));
 		metaList.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, ATTENTION ,MemoryCache.getWebContextUrl()+baseUrl+"attention.html"));
		metaList.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, HELP ,MemoryCache.getWebContextUrl()+baseUrl+"help.html"));
		metaList.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, AREA ,MemoryCache.getWebContextUrl()+baseUrl+"area.html"));

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
		ImageDisplayInfo displayInfo = new ImageDisplayInfo();
		displayInfo.setTilteName(item.getTitle());
		if(UPDATE_VERSION.equals(item.getTitle()))
		{
			updateVersion();

		}
		else if(HELP.equals(item.getTitle()))
		{ 		

			 loadLocalHelp();
			

		}
		else
		{
 			displayInfo.setUrl(item.getContent().toString());
			MispWebViewActivity.jump(this.getActivity(), displayInfo);
		}
	}
	
	private void loadLocalArea()
	{
		ImageDisplayInfo displayInfo = new ImageDisplayInfo();
		displayInfo.setTilteName(HELP);
 	 
 	   	List<String> imageList = new ArrayList<String>();
	   	imageList.add(String.valueOf(R.drawable.help_1));
	   	imageList.add(String.valueOf(R.drawable.help_2));
	   	imageList.add(String.valueOf(R.drawable.help_3));
	   	imageList.add(String.valueOf(R.drawable.help_4));
	   	imageList.add(String.valueOf(R.drawable.help_5));
	   	
		displayInfo.setImageList(imageList);
		MispImageActivity.jump(MoreFragment.this.getActivity(), displayInfo);
	}
	
	private void loadLocalHelp()
	{
		ImageDisplayInfo displayInfo = new ImageDisplayInfo();
		displayInfo.setTilteName(HELP);
 	 
 	   	List<String> imageList = new ArrayList<String>();
	   	imageList.add(String.valueOf(R.drawable.help_1));
	   	imageList.add(String.valueOf(R.drawable.help_2));
	   	imageList.add(String.valueOf(R.drawable.help_3));
	   	imageList.add(String.valueOf(R.drawable.help_4));
	   	imageList.add(String.valueOf(R.drawable.help_5));
	   	
		displayInfo.setImageList(imageList);
		MispImageActivity.jump(MoreFragment.this.getActivity(), displayInfo);
	}
	
	private void loadHelp()
	{
		
		final MispWaitDailog dailog = new MispWaitDailog(this.getActivity());
		dailog.show();
		GetADReq req = new GetADReq();
		
		WebServiceContext.getInstance().getADManageRest(new MispHttpHandler()
		{

			@Override
			public void handle(MispHttpMessage message)
			{
				dailog.dismiss();
				 if(message.isSuccess())
				 {
					ImageDisplayInfo displayInfo = new ImageDisplayInfo();
					displayInfo.setTilteName(HELP);
					GetADRsp rsp = (GetADRsp) message.getMessage().obj;
				 
					List<String> urlList = new ArrayList<String>();
					for(AdvertisementJson json : rsp.getObj())
					{
						urlList.add(MemoryCache.getImageUrl()+json.getAd_img()); 
					}
					displayInfo.setImageList(urlList);
 					MispImageActivity.jump(MoreFragment.this.getActivity(), displayInfo);
					//initAdView(rsp.getObj());
				 }
				 else
				 {
					 showMessage(message);
				 }
			}
				
		 }).getHelp(req);
		 
	}
	private void updateVersion()
	{
		final MispWaitDailog dailog = new MispWaitDailog(this.getActivity());
		dailog.show();
		GetClientVersionReq req = new GetClientVersionReq();
		WebServiceContext.getInstance().getSystemManageRest(new MispHttpHandler()
		{

			@Override
			public void handle(MispHttpMessage message)
			{
				dailog.dismiss();
				if(message.isSuccess())
				{
					GetClientVersionRsp rsp = (GetClientVersionRsp) message.getMessage().obj;
					UpgradeActivity.jump(MoreFragment.this.getActivity(), rsp.getObj());
 
				}
				else
				{
					showMessage(message);
				}
				
			}
			
			
		}).getAppVersion(req);

	}

	


}
