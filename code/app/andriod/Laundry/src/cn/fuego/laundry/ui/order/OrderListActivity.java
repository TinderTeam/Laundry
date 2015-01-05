package cn.fuego.laundry.ui.order;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.laundry.R;
import cn.fuego.laundry.constant.ListItemTypeConst;
import cn.fuego.laundry.ui.cart.MyCartFragment;
import cn.fuego.laundry.webservice.up.model.CreateOrderReq;
import cn.fuego.laundry.webservice.up.model.base.OrderJson;
import cn.fuego.misp.ui.list.MispDistinctListActivity;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.misp.ui.model.CommonItemMeta;

public class OrderListActivity extends MispListActivity<OrderJson>
{

	@Override
	public void initRes()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadSendList()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrderJson> loadListRecv(Object obj)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View getListItemView(View view, OrderJson item)
	{
		// TODO Auto-generated method stub
		return null;
	}

	
 
 

	 

}
