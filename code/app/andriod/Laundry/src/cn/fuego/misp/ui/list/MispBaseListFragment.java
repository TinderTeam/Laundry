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
import android.widget.ListAdapter;
import android.widget.ListView;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.base.MispHttpFragment;
import cn.fuego.misp.ui.model.ListViewResInfo;

public abstract class MispBaseListFragment<E> extends MispHttpFragment implements OnItemClickListener,MispListViewInteface
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	public static final String SELECT_ITEM = "SELECT_ITEM";
 

	private List<E> dataList = new ArrayList<E>();

	private MispListAdapter<E> adapter;

	private ListView listView;
	protected ListViewResInfo listViewRes = new ListViewResInfo();
	
	public List<E> getDataList()
	{
		return dataList;
	}

	public void setDataList(List<E> dataList)
	{
		if(null != dataList)
		{
			this.dataList = dataList;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{

 		View rootView = super.onCreateView(inflater, container, savedInstanceState);
 				

		listView = (ListView) rootView.findViewById(this.listViewRes.getListView());
		
		if(null != listView)
		{
			adapter = new MispListAdapter<E>(this.getActivity(), this,this.listViewRes,this.dataList);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(this);
			loadSendList();
		}
 
		return rootView;

	}
	
	public void repaint()
	{
		this.adapter.notifyDataSetChanged();
	}
	
	public void refreshList(List<E> newDataList)
	{
		this.dataList.clear();
		if(!ValidatorUtil.isEmpty(newDataList))
		{
			this.dataList.addAll(newDataList);
		}
		repaint();
	}
	
	public void adapterForScrollView()
	{
    	ListAdapter listAdapter = listView.getAdapter();   
        if (listAdapter == null) {   
            return;   
        }   
   
        int totalHeight = 0;   
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {   
            // listAdapter.getCount()返回数据项的数目   
            View listItem = listAdapter.getView(i, null, listView);   
            // 计算子项View 的宽高   
            listItem.measure(0, 0);    
            // 统计所有子项的总高度   
            totalHeight += listItem.getMeasuredHeight();    
        }   
   
        ViewGroup.LayoutParams params = listView.getLayoutParams();   
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));   
        // listView.getDividerHeight()获取子项间分隔符占用的高度   
        // params.height最后得到整个ListView完整显示需要的高度   
        listView.setLayoutParams(params);  
	}

	public abstract void loadSendList();

 

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
	final public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{

		E item = this.adapter.getItem(position);
		onItemListClick(parent,view,id,item);

	}
	
	public void onItemListClick(AdapterView<?> parent, View view,long id, E item)
	{
		if(null != this.listViewRes.getClickActivityClass())
		{
			Intent intent = new Intent(this.getActivity(),this.listViewRes.getClickActivityClass());
			intent.putExtra(ListViewResInfo.SELECT_ITEM, (Serializable) item);

			this.startActivity(intent);
		}

	}
}
