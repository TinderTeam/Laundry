package cn.fuego.misp.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import cn.fuego.misp.ui.model.CommonItemMeta;

public abstract class MispDistinctListFragment extends MispBaseListFragment<CommonItemMeta> implements
     MispListViewInteface,OnItemClickListener
{
 
	
	
 
	
	public abstract View getListItemView(LayoutInflater inflater,View convertView,CommonItemMeta item);
	
	@Override
	final public int getItemViewType(Object item)
	{
		return ((CommonItemMeta)item).getLayoutType();
	}
 
	@Override
	public View getView(LayoutInflater inflater,View convertView, ViewGroup parent, Object item)
	{
 
		return getListItemView(inflater,convertView, (CommonItemMeta)item);
	}
	
 	public int getListItemType(CommonItemMeta item)
	{
		// TODO Auto-generated method stub
		return item.getLayoutType();
	}
 
 
}
