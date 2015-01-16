package cn.fuego.laundry.ui.order;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.constant.ListItemTypeConst;
import cn.fuego.laundry.ui.cart.MyCartFragment;
import cn.fuego.laundry.webservice.up.model.CreateOrderReq;
import cn.fuego.laundry.webservice.up.model.base.DeliveryInfoJson;
import cn.fuego.laundry.webservice.up.model.base.OrderJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.list.MispDistinctListActivity;
import cn.fuego.misp.ui.model.CommonItemMeta;

public class OrderActivity extends MispDistinctListActivity implements OnClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	public static final String ORDER_INFO = "order_info";

	private CreateOrderReq orderReq;
 

	
	
	@Override
	public void initRes()
	{
		this.activityRes.setAvtivityView(R.layout.order_info);
		this.activityRes.setName("提交订单");
		this.activityRes.getButtonIDList().add(R.id.order_info_btn_submit);
		
		this.listViewRes.setListView(R.id.order_info_list);
		Intent intent = this.getIntent();
		orderReq = (CreateOrderReq) intent.getSerializableExtra(OrderActivity.ORDER_INFO);
		
		orderReq.getOrder().setPhone(AppCache.getInstance().getDefuatDelivery().getPhone());
		orderReq.getOrder().setContact_name(AppCache.getInstance().getDefuatDelivery().getContact_name());
		orderReq.getOrder().setTake_addr(AppCache.getInstance().getDefuatDelivery().getTake_addr());
		orderReq.getOrder().setTake_time(AppCache.getInstance().getDefuatDelivery().getTake_time());
		orderReq.getOrder().setDelivery_addr(AppCache.getInstance().getDefuatDelivery().getDelivery_addr());
		orderReq.getOrder().setDelivery_time(AppCache.getInstance().getDefuatDelivery().getDelivery_time());
		
		this.getDataList().clear();
		this.getDataList().addAll(getBtnData(orderReq.getOrder()));
	}
 
	private List<CommonItemMeta> getBtnData(OrderJson order)
	{
		 
			List<CommonItemMeta> list = new ArrayList<CommonItemMeta>();
			
			if(null != order)
			{
				list.add(new CommonItemMeta(ListItemTypeConst.TEXT_CONTENT, "取衣地址", order.getTake_addr()));

				list.add(new CommonItemMeta(ListItemTypeConst.TEXT_CONTENT, "送回地址", order.getDelivery_addr()));
				
				list.add(new CommonItemMeta(ListItemTypeConst.TEXT_CONTENT, "联系人", order.getContact_name()));
				list.add(new CommonItemMeta(ListItemTypeConst.TEXT_CONTENT, "联系电话", order.getPhone()));
				
				
				list.add(new CommonItemMeta(ListItemTypeConst.NULL_CONTENT, null, null));

		 
				
				list.add(new CommonItemMeta(ListItemTypeConst.TEXT_CONTENT, "总价", order.getTotal_price()));
				list.add(new CommonItemMeta(ListItemTypeConst.TEXT_CONTENT, "付款方式", order.getPay_option()));
				list.add(new CommonItemMeta(ListItemTypeConst.TEXT_CONTENT, "您的要求", order.getOrder_note()));
			}

		
		return list;
	}
	
	@Override
	public int getItemTypeCount()
	{
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public void loadSendList()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CommonItemMeta> loadListRecv(Object obj)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View getListItemView(LayoutInflater inflater, CommonItemMeta item)
	{
 		View view =null;
		int type= item.getLayoutType();
		String title= item.getTitle();
		switch(type)
		{
		case ListItemTypeConst.IMG_CONTENT:
			{
				view = inflater.inflate(R.layout.misp_list_item_imgtype, null);
				TextView title_view = (TextView) view.findViewById(R.id.item_imgtype_name);
				ImageView img = (ImageView) view.findViewById(R.id.item_imgtype_img);
				title_view.setText(title);
			}
			
			break;
		case ListItemTypeConst.TEXT_CONTENT:
			{
				view = inflater.inflate(R.layout.misp_list_item_texttype, null);
				TextView title_view = (TextView) view.findViewById(R.id.item_texttype_name);
				TextView content_view = (TextView) view.findViewById(R.id.item_texttype_text);
				title_view.setText(title);
				content_view.setText( String.valueOf(item.getContent()));
			}
			
			break;
		case ListItemTypeConst.EDIT_TEXT:
			{
				view = inflater.inflate(R.layout.misp_list_item_texttype, null);
				TextView title_view = (TextView) view.findViewById(R.id.item_texttype_name);
				TextView content_view = (TextView) view.findViewById(R.id.item_texttype_text);
				title_view.setText(title);
				content_view.setText( String.valueOf(item.getContent()));
			}
			
		break;	
		case ListItemTypeConst.DEFAULT_CONTENT:
			{
				view = inflater.inflate(R.layout.misp_list_item_btntype, null);
				TextView title_view = (TextView) view.findViewById(R.id.item_btntype_name);
				title_view.setText(title);
			}
			
			break;
		case ListItemTypeConst.NULL_CONTENT:
			{
				view = inflater.inflate(R.layout.misp_list_item_divider, null);
			}
			
		}
		return view;
	}

	
	
	
	 
	@Override
	public void onItemListClick(AdapterView<?> parent, View view, long id,
			CommonItemMeta item)
	{
		 switch(item.getLayoutType())
		 {
		 case ListItemTypeConst.EDIT_TEXT:
			 {
				 showAddr();
			 }
			 break;
		
	     default:
	         break;
		 
		 }
	}
 

	public void showAddr()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("请输入").setIcon(
			     android.R.drawable.ic_dialog_info);
		builder.setView(new EditText(this));
		builder.setPositiveButton("确定", null);
		builder.setNegativeButton("取消", null);
		builder.show();
	}

	@Override
	public void onClick(View v)
	{
		
		WebServiceContext.getInstance().getOrderManageRest(new MispHttpHandler()
		{

			@Override
			public void handle(MispHttpMessage message)
			{
				 if(message.isSuccess())
				 {
					 jumpToOrderList();
				 }
				 else
				 {
					 log.error("create order failed");

				 }
				 showMessage(message);
			}
			
		}).create(orderReq);
	}
	
	private void jumpToOrderList()
	{
		Intent i = new Intent();
		i.setClass(this, OrderListActivity.class);
        startActivity(i);
	}



	 

}
