package cn.fuego.misp.ui.list;

import java.util.List;

import cn.fuego.misp.ui.model.ListViewResInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MispListAdapter<E> extends ArrayAdapter<E>
{

	private MispListViewInteface mispList;
	private Context context;
 
	public MispListAdapter(Context context,MispListViewInteface mispList,ListViewResInfo resInfo,List<E> dataList)
	{
		
        super(context, resInfo.getListView(),dataList);
	    this.mispList = mispList;
	    this.context = context;

	}
	
	  @Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		  LayoutInflater inflater = LayoutInflater.from(context);
	        E item = getItem(position);

		  return mispList.getView(inflater, convertView,parent,item);
	}
	  @Override
	public int getItemViewType(int position)
	{
		   E item = getItem(position);
		 return mispList.getItemViewType(item);
	}

	@Override
	public int getViewTypeCount()
	{
		// TODO Auto-generated method stub
		return mispList.getItemTypeCount();
	}
 

}
