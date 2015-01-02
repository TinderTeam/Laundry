package cn.fuego.laundry.ui.cart;

import java.util.List;

import android.view.View;
import cn.fuego.laundry.R;
import cn.fuego.laundry.ui.home.HomeProductActivity;
import cn.fuego.laundry.webservice.up.model.base.ProductJson;
import cn.fuego.misp.ui.list.MispListFragment;

public class MyCartFragment extends MispListFragment<ProductJson>
{

	@Override
	public void initRes()
	{
		this.fragmentRes.setImage(R.drawable.tab_icon_cart);
		this.fragmentRes.setName(R.string.tabbar_cart);
		this.fragmentRes.setFragmentView(R.layout.news_fragment);
 
		listViewRes.setListView(R.id.news_list);
		listViewRes.setListItemView(R.layout.news_list_item);
		listViewRes.setClickActivityClass(HomeProductActivity.class);
	}
	

	@Override
	public void loadSendList()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public View getListItemView(View view, ProductJson item)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductJson> loadListRecv(Object obj)
	{
		// TODO Auto-generated method stub
		return null;
	}


}
