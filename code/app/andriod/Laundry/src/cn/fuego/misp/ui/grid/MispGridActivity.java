package cn.fuego.misp.ui.grid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.base.MispHttpActivtiy;
import cn.fuego.misp.ui.base.MispGridView;
import cn.fuego.misp.ui.list.ListViewResInfo;
import cn.fuego.misp.ui.list.MispListAdapter;
import cn.fuego.misp.ui.list.MispListViewInteface;

public abstract class MispGridActivity<E> extends MispHttpActivtiy implements MispListViewInteface, OnItemClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());

 

	private List<E> dataList = new ArrayList<E>();

	private MispListAdapter<E> adapter;

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
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
 
		//ListView user_info_list = (ListView) findViewById(R.id.user_info_list);
		//ArrayList<HashMap<String,Object>> datasource = new ArrayList<HashMap<String,Object>>();
		//String[] data={"昵称","user1",};
		
		//adapter = new MispGridViewAdapter(this, this,this.listViewRes,this.dataList);
		MispGridView productView = (MispGridView) findViewById(this.listViewRes.getListView());
;
		productView.setAdapter(adapter);
		productView.setOnItemClickListener(this);
		loadSendList();

	}
	

	public abstract void loadSendList();
	
 
	public void repaint()
	{
		this.adapter.notifyDataSetChanged();
	}
 

	public abstract List<E> loadListRecv(Object obj);
	
	public abstract View getListItemView(View view, E item);

 

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
		
		Intent intent = new Intent(this,this.listViewRes.getClickActivityClass());
		intent.putExtra(ListViewResInfo.SELECT_ITEM, (Serializable) item);

		this.startActivity(intent);
	}
}
