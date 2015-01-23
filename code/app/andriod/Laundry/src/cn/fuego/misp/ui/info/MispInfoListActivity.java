package cn.fuego.misp.ui.info;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import cn.fuego.misp.constant.MispCommonIDName;
import cn.fuego.misp.ui.list.MispDistinctListActivity;
import cn.fuego.misp.ui.model.CommonItemMeta;

public class MispInfoListActivity extends MispDistinctListActivity 
{
 

	@Override
	public void initRes()
	{
		this.activityRes.setAvtivityView(MispCommonIDName.layout_misp_info_display);
		this.listViewRes.setListView(MispCommonIDName.misp_info_list);
		 
	 

	}
	
	@Override
	public void loadSendList()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CommonItemMeta> loadListRecv(Object obj)
	{
		// TODO Auto-generated method stub
		return null;
	}

 
	@Override
	public int getItemTypeCount()
	{
		// TODO Auto-generated method stub
		return CommonItemMeta.ITEM_TYPE_COUNT;
	}

 
 
	@Override
	public View getListItemView(LayoutInflater inflater, CommonItemMeta item)
	{
 		 return item.getListItemView(inflater);
	}

	@Override
	public void onItemListClick(AdapterView<?> parent, View view, long id,
			CommonItemMeta item)
	{

	}

 
	 
 


}
