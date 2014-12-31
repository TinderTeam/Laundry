package cn.fuego.misp.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface MispListViewInteface
{
	 public View getView(LayoutInflater inflater,View convertView, ViewGroup parent,Object item);
	 
	public int getItemViewType(Object item);
	
	public int getItemTypeCount();

}
