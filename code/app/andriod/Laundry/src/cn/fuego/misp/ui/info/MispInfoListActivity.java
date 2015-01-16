package cn.fuego.misp.ui.info;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.constant.ListItemTypeConst;
import cn.fuego.laundry.webservice.up.model.base.CustomerJson;
import cn.fuego.misp.ui.list.MispDistinctListActivity;
import cn.fuego.misp.ui.model.CommonItemMeta;

public class MispInfoListActivity extends MispDistinctListActivity 
{
 

	@Override
	public void initRes()
	{
		this.activityRes.setAvtivityView(R.layout.misp_info_display);
		this.listViewRes.setListView(R.id.misp_info_list);
		 
	 

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
		return 6;
	}

 
 
	@Override
	public View getListItemView(LayoutInflater inflater, CommonItemMeta item)
	{
 		View view =null;
		int type= item.getLayoutType();
		String title= item.getTitle();
		
		switch(type)
		{
		case ListItemTypeConst.IMG_CONTENT:
			{
				view = inflater.inflate(R.layout.list_item_imgtype, null);
				TextView title_view = (TextView) view.findViewById(R.id.item_imgtype_name);
				ImageView img = (ImageView) view.findViewById(R.id.item_imgtype_img);
				title_view.setText(title);
			}
			
			break;
		case ListItemTypeConst.TEXT_CONTENT:
			{
				view = inflater.inflate(R.layout.list_item_texttype, null);
				TextView title_view = (TextView) view.findViewById(R.id.item_texttype_name);
				TextView content_view = (TextView) view.findViewById(R.id.item_texttype_text);
				title_view.setText(title);
				content_view.setText( String.valueOf(item.getContent()));
			}
			
			break;
		case ListItemTypeConst.DEFAULT_CONTENT:
			{
				view = inflater.inflate(R.layout.list_item_btntype, null);
				TextView title_view = (TextView) view.findViewById(R.id.item_btntype_name);
				title_view.setText(title);
			}
			
			break;
		case ListItemTypeConst.BUTTON_ITEM:
			{
				view = inflater.inflate(R.layout.list_item_btntype, null);
				TextView title_view = (TextView) view.findViewById(R.id.item_btntype_name);
				title_view.setText(title);
			}
			break;
		case ListItemTypeConst.NULL_CONTENT:
		   {
			view = inflater.inflate(R.layout.list_item_divider, null);
		   }
		   break;
	    default:
	    	break;
			
			
		}
		 
		return view;
	}

 
	 
 


}
