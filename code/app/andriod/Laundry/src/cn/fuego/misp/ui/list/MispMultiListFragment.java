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
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.base.MispHttpFragment;
import cn.fuego.misp.ui.model.FragmentResInfo;

public abstract class MispMultiListFragment<E> extends MispBaseListFragment<E> implements
OnItemClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	public static final String SELECT_ITEM = "SELECT_ITEM";
 

	private List<List<Object>> tableDataList = new ArrayList<List<Object>>();

	private List<MispListAdapter<Object>> adapterList = new ArrayList<MispListAdapter<Object>>();

	protected List<ListViewResInfo> listViewRes = new ArrayList<ListViewResInfo>();
	
	private List<ListView> listViewList = new ArrayList<ListView>();

	
	private int currentIndex = 0;
 
	
	public abstract void initRes();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{

		initRes();
		if(ValidatorUtil.isEmpty(listViewRes))
		{
			log.info("the list view resource can not be empty");
			throw new MISPException("init failed");
			
		}
		View rootView = inflater.inflate(this.fragmentRes.getFragmentView(), null);

		for(int i=0;i<listViewRes.size();i++)
		{
			tableDataList.add(new ArrayList<Object>());
			MispListAdapter<Object> adapter = new MispListAdapter<Object>(this.getActivity(), this,this.listViewRes.get(i),this.tableDataList.get(i));
			adapterList.add(adapter);
			ListView listView = (ListView) rootView.findViewById(this.listViewRes.get(i).getListView());
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(this);
			//listView.setVisibility(View.GONE);
			listViewList.add(listView);
		}
		

		displayList(currentIndex);

		return rootView;

	}
	
	public void displayList(int index)
	{
		int oldIndex = currentIndex;
		currentIndex = index;
		log.info("display list the index is " + currentIndex);
		loadSendList(currentIndex);
		listViewList.get(oldIndex).setVisibility(View.GONE);
		listViewList.get(currentIndex).setVisibility(View.VISIBLE);
  
	}

	public abstract void loadSendList(int index);

	public abstract View getListItemView(int index,View view, E item);

	public abstract List<E> loadListRecv(int index,Object obj);
	public int getItemTypeCount()
	{
		return 1;
	}
	
	@Override
	public View getView(LayoutInflater inflater,View convertView, ViewGroup parent, Object item)
	{
		View view = inflater.inflate(this.listViewRes.get(currentIndex).getListItemView(), null);
		return getListItemView(currentIndex,view,(E)item);
	}
	
	final public View getListItemView(View view, E item)
	{
		return getListItemView(currentIndex,view,item);
	}
	@Override
	public void handle(MispHttpMessage message)
	{
		if (message.isSuccess())
		{
			this.tableDataList.get(currentIndex).clear();

			List<E> newData = loadListRecv(currentIndex,message.getMessage().obj);
			if (!ValidatorUtil.isEmpty(newData))
			{
				this.tableDataList.get(currentIndex).addAll(newData);
			}

			this.adapterList.get(currentIndex).notifyDataSetChanged();

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

		Object item = this.adapterList.get(currentIndex).getItem(position);
		Intent intent = new Intent(this.getActivity(), listViewRes.get(currentIndex).getClickActivityClass());
		intent.putExtra(SELECT_ITEM, (Serializable) item);

		this.startActivity(intent);

	}
}
