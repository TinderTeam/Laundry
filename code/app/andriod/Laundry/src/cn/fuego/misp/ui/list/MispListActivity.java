package cn.fuego.misp.ui.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.base.MispGridView;

public abstract class MispListActivity<E> extends MispBaseListActivity<E> 

{
	 
 
 
	@Override
	public int getItemViewType(Object item)
	{
		// TODO Auto-generated method stub
		return 0;
	}
	public int getItemTypeCount()
	{
		return 1;
	}
	


 	
	public abstract View getListItemView(View view, E item);

	
	@Override
	final public View getView(LayoutInflater inflater,View convertView, ViewGroup parent, Object item)
	{
		View view = inflater.inflate(this.listViewRes.getListItemView(), null);
		return getListItemView(view,(E)item);
	}

 
}
