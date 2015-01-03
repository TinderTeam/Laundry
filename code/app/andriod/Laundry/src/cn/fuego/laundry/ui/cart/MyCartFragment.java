package cn.fuego.laundry.ui.cart;

import java.util.List;

import android.view.View;
import android.widget.TextView;
import cn.fuego.laundry.R;
import cn.fuego.laundry.ui.home.HomeProductActivity;
import cn.fuego.laundry.webservice.up.model.base.OrderDetailJson;
import cn.fuego.laundry.webservice.up.model.base.ProductJson;
import cn.fuego.misp.ui.list.MispListFragment;

public class MyCartFragment extends MispListFragment<OrderDetailJson>
{
	private int totalCnt;
	private float sum;

	@Override
	public void initRes()
	{
		this.fragmentRes.setImage(R.drawable.tab_icon_cart);
		this.fragmentRes.setName(R.string.tabbar_cart);
		this.fragmentRes.setFragmentView(R.layout.chart_fragment);
 
		listViewRes.setListView(R.id.char_list);
		listViewRes.setListItemView(R.layout.chart_list_item);
		listViewRes.setClickActivityClass(HomeProductActivity.class);
		OrderDetailJson json = new OrderDetailJson();
		json.setProduct_name("裤子");
		json.setProduct_price((float)1.1);
		json.setAmount(1);
		this.dataList.add(json);
	}
	

	@Override
	public void loadSendList()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public View getListItemView(View view, OrderDetailJson item)
	{
		TextView nameView = (TextView) view.findViewById(R.id.chart_product_name);
		nameView.setText(item.getProduct_name());
		
		TextView priceView = (TextView) view.findViewById(R.id.chart_product_price);
		priceView.setText(String.valueOf(item.getProduct_price()));
		
		TextView amountView = (TextView) view.findViewById(R.id.chart_product_amount);
		amountView.setText(String.valueOf(item.getAmount()));
		return view;
	}

	@Override
	public List<OrderDetailJson> loadListRecv(Object obj)
	{
		return null;
	}


}
