package cn.fuego.laundry.ui.order;

import java.io.Serializable;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.cache.ProductCache;
import cn.fuego.laundry.constant.PriceTypeEnum;
import cn.fuego.laundry.ui.LoginActivity;
import cn.fuego.laundry.ui.MainTabbarActivity;
import cn.fuego.laundry.ui.user.UserFragment;
import cn.fuego.laundry.webservice.up.model.GetOrderListReq;
import cn.fuego.laundry.webservice.up.model.GetOrderListRsp;
import cn.fuego.laundry.webservice.up.model.base.OrderJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.dailog.MispWaitDailog;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.misp.ui.util.LoadImageUtil;

public class OrderListActivity extends MispListActivity<OrderJson>
{
	
 	@Override
	public void backOnClick()
	{
		// TODO Auto-generated method stub
		super.backOnClick();
		MainTabbarActivity.jump(this, UserFragment.class, 1);
		
	}

	public static void jump(Activity activity,int code)
	{
 		Intent intent = new Intent();
 		intent.setClass(activity, OrderListActivity.class);
  		activity.startActivityForResult(intent,code);
  	}

	@Override
	public void initRes()
	{
		this.activityRes.setAvtivityView(R.layout.my_order);
		this.activityRes.setName("我的订单");
 		this.listViewRes.setListView(R.id.order_list);
		this.listViewRes.setListItemView(R.layout.order_list_item);
		this.listViewRes.setClickActivityClass(OrderDetailActivity.class);
		
		waitDailog = new MispWaitDailog(this);  
		this.waitDailog.show();

		
	}

	@Override
	public void loadSendList()
	{
		GetOrderListReq req = new GetOrderListReq();
		req.setUser_id(AppCache.getInstance().getUser().getUser_id());

		WebServiceContext.getInstance().getOrderManageRest(this).getAll(req);
		
		
	}
	
	

	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		waitDailog.dismiss();
		super.handle(message);
		 if(message.getErrorCode() == MISPErrorMessageConst.ERROR_LOGIN_INVALID)
		 {
			 LoginActivity.jump(this, 1);
			 this.finish();
		 }
	}



	@Override
	public List<OrderJson> loadListRecv(Object obj)
	{
		GetOrderListRsp rsp = (GetOrderListRsp) obj;
		
		return rsp.getObj();
	}
	
	
	

	@Override
	public View getListItemView(View view, OrderJson item)
	{
		TextView idView = (TextView) view.findViewById(R.id.order_list_item_id);
		idView.setText(item.getOrder_code());
		TextView priceView = (TextView) view.findViewById(R.id.order_list_item_price);
		
		String price = ProductCache.getInstance().getDispPrice(item.getPrice_type(), item.getTotal_price());
		if(PriceTypeEnum.FLOAT_PRICE.getStrValue().equals(price))
		{
			priceView.setText(price);
		}
		else
		{
			priceView.setText("￥"+price);
		}
		
		TextView statusView = (TextView)view.findViewById(R.id.order_list_item_status);
		statusView.setText(item.getOrder_status());
		TextView timeView = (TextView) view.findViewById(R.id.order_list_item_time);
		timeView.setText(item.getCreate_time());
 

		return view;
	}

	@Override
	public void onItemListClick(AdapterView<?> parent, View view, long id,
			OrderJson item)
	{
		// TODO Auto-generated method stub
		Intent intent = new Intent(this,this.listViewRes.getClickActivityClass());
		intent.putExtra(ListViewResInfo.SELECT_ITEM, (Serializable) item);

		this.startActivityForResult(intent, 2);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(requestCode == 2)
		{
			loadSendList();
		}
		
	}


	
 
 

	 

}
