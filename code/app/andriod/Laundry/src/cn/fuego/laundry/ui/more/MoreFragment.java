package cn.fuego.laundry.ui.more;

import java.io.Serializable;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import cn.fuego.laundry.R;
import cn.fuego.laundry.ui.upgrade.UpgradeActivity;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.list.ListViewResInfo;
import cn.fuego.misp.ui.list.MispListFragment;
import cn.fuego.misp.webservice.up.model.GetClientVersionReq;
import cn.fuego.misp.webservice.up.model.GetClientVersionRsp;
import cn.fuego.misp.webservice.up.model.base.AttributeJson;

public class MoreFragment extends MispListFragment<AttributeJson> 
{

	
	@Override
	public void initRes()
	{
		this.fragmentRes.setImage(R.drawable.tab_icon_more);
		this.fragmentRes.setName(R.string.tabbar_more);
		this.fragmentRes.setFragmentView(R.layout.more_fragment);
		this.listViewRes.setListView(R.id.more_list);
		this.listViewRes.setListItemView(R.layout.misp_list_item_btntype);
		this.listViewRes.setClickActivityClass(UpgradeActivity.class);
		
		initData();
		

 	}
	private void initData()
	{
		this.getDataList().clear();
		AttributeJson json1 = new AttributeJson();
		json1.setAttrKey("快客介绍");
		json1.setAttrValue("点击");
		this.getDataList().add(json1);
		AttributeJson json2= new AttributeJson();
		json2.setAttrKey("加入我们");
		json2.setAttrValue("点击");
		this.getDataList().add(json2);
		AttributeJson json3 = new AttributeJson();
		json3.setAttrKey("版本更新");
		json3.setAttrValue("点击");
		this.getDataList().add(json3);
 	}

	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}



	@Override
	public void loadSendList()
	{
		// TODO Auto-generated method stub
		
	}



	@Override
	public View getListItemView(View view, AttributeJson item)
	{
		TextView name = (TextView) view.findViewById(R.id.item_btntype_name);
		name.setText(item.getAttrKey());
 
		return view;
	}



	@Override
	public List<AttributeJson> loadListRecv(Object obj)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onItemListClick(AdapterView<?> parent, View view, long id,
			AttributeJson item)
	{
		updateVersion();
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
