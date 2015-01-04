package cn.fuego.laundry.ui.cart;

import java.io.Serializable;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import cn.fuego.laundry.R;
import cn.fuego.laundry.ui.home.HomeProductActivity;
import cn.fuego.laundry.ui.order.OrderActivity;
import cn.fuego.laundry.webservice.up.model.base.OrderDetailJson;
import cn.fuego.laundry.webservice.up.model.base.OrderJson;
import cn.fuego.misp.ui.list.MispListFragment;

public class MyCartFragment extends MispListFragment<OrderDetailJson> implements OnClickListener
{
	private OrderJson order = new OrderJson();
 
	@Override
	public void initRes()
	{
		this.fragmentRes.setImage(R.drawable.tab_icon_cart);
		this.fragmentRes.setName(R.string.tabbar_cart);
		this.fragmentRes.setFragmentView(R.layout.chart_fragment);
 
		listViewRes.setListView(R.id.chart_list);
		listViewRes.setListItemView(R.layout.chart_list_item);
		listViewRes.setClickActivityClass(HomeProductActivity.class);
		OrderDetailJson json = new OrderDetailJson();
		json.setProduct_name("裤子");
		json.setProduct_price((float)1.1);
		json.setQuantity(1);
		this.dataList.add(json);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View rootView = super.onCreateView(inflater, container, savedInstanceState);
		Button submitButton = (Button) rootView.findViewById(R.id.chart_submit);
		submitButton.setOnClickListener(this);
		super.adapterForScrollView();
		
		return rootView;
	}




	@Override
	public void loadSendList()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public View getListItemView(View view, OrderDetailJson item)
	{
		TextView nameView = (TextView) view.findViewById(R.id.chart_list_item_name);
		nameView.setText(item.getProduct_name());
		
		TextView priceView = (TextView) view.findViewById(R.id.chart_list_item_price);
		priceView.setText(String.valueOf(item.getProduct_price()));
		
		TextView amountView = (TextView) view.findViewById(R.id.chart_list_item_quantity);
		amountView.setText(String.valueOf(item.getQuantity()));
		return view;
	}

	@Override
	public List<OrderDetailJson> loadListRecv(Object obj)
	{
		return null;
	}


	@Override
	public void onClick(View v)
	{
		Intent intent = new Intent(this.getActivity(),OrderActivity.class);
 
		this.startActivity(intent);

	}


}
