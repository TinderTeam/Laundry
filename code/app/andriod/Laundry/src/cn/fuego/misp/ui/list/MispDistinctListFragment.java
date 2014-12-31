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
import cn.fuego.misp.ui.model.CommonItemMeta;

public abstract class MispDistinctListFragment extends MispBaseListFragment<CommonItemMeta> implements
     MispListViewInteface,OnItemClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());

 

	protected List<CommonItemMeta> dataList = new ArrayList<CommonItemMeta>();

	private MispListAdapter<CommonItemMeta> adapter;

	protected ListViewResInfo listViewRes = new ListViewResInfo();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{

		this.initRes();
		View rootView = inflater.inflate(this.fragmentRes.getFragmentView(), null);

		ListView listView = (ListView) rootView.findViewById(this.listViewRes.getListView());

		adapter = new MispListAdapter<CommonItemMeta>(this.getActivity(), this,this.listViewRes,this.dataList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		loadSendList();

		return rootView;

	}
 
	

	public abstract void loadSendList();



	public abstract List<CommonItemMeta> loadListRecv(Object obj);
	
	public abstract View getListItemView(LayoutInflater inflater,View convertView,CommonItemMeta item);
	
	@Override
	public int getItemViewType(Object item)
	{
		return ((CommonItemMeta)item).getLayoutType();
	}
 
	@Override
	public View getView(LayoutInflater inflater,View convertView, ViewGroup parent, Object item)
	{
 
		return getListItemView(inflater,convertView, (CommonItemMeta)item);
	}
	
	public void update(List<CommonItemMeta> newList)
	{
		this.dataList.clear();
		if(!ValidatorUtil.isEmpty(newList))
		{
			dataList.addAll(newList);
		}
		this.adapter.notifyDataSetChanged();
	}
 

	@Override
	public void handle(MispHttpMessage message)
	{
		if (message.isSuccess())
		{
			this.dataList.clear();

			List<CommonItemMeta> newData = loadListRecv(message.getMessage().obj);
			if (!ValidatorUtil.isEmpty(newData))
			{
				this.dataList.addAll(newData);
			}

			this.adapter.notifyDataSetChanged();

		} else
		{
			log.error("query product failed");
			this.showMessage(message);
		}
	}

	public void onItemClick(CommonItemMeta item)
	{

		Intent intent = new Intent(this.getActivity(),this.listViewRes.getClickActivityClass());
		intent.putExtra(ListViewResInfo.SELECT_ITEM, (Serializable) item);

		this.startActivity(intent);
	}
	@Override
	final public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		CommonItemMeta item = this.adapter.getItem(position);
		onItemClick(item);
	}
}
