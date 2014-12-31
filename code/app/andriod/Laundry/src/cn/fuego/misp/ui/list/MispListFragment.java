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
import cn.fuego.misp.ui.base.MispHttpFragment;
import cn.fuego.misp.ui.model.FragmentResInfo;

public abstract class MispListFragment<E> extends MispBaseListFragment<E> implements
		OnItemClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	public static final String SELECT_ITEM = "SELECT_ITEM";
 

	private List<E> dataList = new ArrayList<E>();

	private MispListAdapter<E> adapter;

	protected ListViewResInfo listViewRes = new ListViewResInfo();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{

		this.initRes();
		View rootView = inflater.inflate(this.fragmentRes.getFragmentView(), null);

		ListView productView = (ListView) rootView.findViewById(this.listViewRes.getListView());

		adapter = new MispListAdapter<E>(this.getActivity(), this,this.listViewRes,this.dataList);
		productView.setAdapter(adapter);
		productView.setOnItemClickListener(this);
		loadSendList();

		return rootView;

	}

	public abstract void loadSendList();

	public abstract View getListItemView(View view, E item);


	public abstract List<E> loadListRecv(Object obj);
	
	
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
	
	@Override
	public View getView(LayoutInflater inflater,View convertView, ViewGroup parent, Object item)
	{
		View view = inflater.inflate(this.listViewRes.getListItemView(), null);
		return getListItemView(view,(E)item);
	}
	@Override
	public void handle(MispHttpMessage message)
	{
		if (message.isSuccess())
		{
			this.dataList.clear();

			List<E> newData = loadListRecv(message.getMessage().obj);
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{

		E item = this.adapter.getItem(position);
		Intent intent = new Intent(this.getActivity(),this.listViewRes.getClickActivityClass());
		intent.putExtra(SELECT_ITEM, (Serializable) item);

		this.startActivity(intent);

	}
}
