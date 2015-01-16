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
		this.activityRes.setBackBtn(R.id.order_info_back);
		this.activityRes.getButtonIDList().add(R.id.order_info_btn_submit);
		
		this.listViewRes.setListView(R.id.order_info_list);
		Intent intent = this.getIntent();
		orderReq = (CreateOrderReq) intent.getSerializableExtra(OrderActivity.ORDER_INFO);
		
		orderReq.getOrder().setPhone(AppCache.getInstance().getDefuatDelivery().getPhone());
		orderReq.getOrder().setCustomer_name(AppCache.getInstance().getDefuatDelivery().getCustomer_name());
		orderReq.getOrder().setTake_addr(AppCache.getInstance().getDefuatDelivery().getTake_addr());
		orderReq.getOrder().setTake_time(AppCache.getInstance().getDefuatDelivery().getTake_time());
		orderReq.getOrder().setDelivery_addr(AppCache.getInstance().getDefuatDelivery().getDelivery_addr());
		orderReq.getOrder().setDelivery_time(AppCache.getInstance().getDefuatDelivery().getDelivery_time());
		
		this.getDataList().clear();
		this.getDataList().addAll(getBtnData());
	}
 
	private List<CommonItemMeta> getBtnData()
	{
		// 生成数据源
		List<CommonItemMeta> list = new ArrayList<CommonItemMeta>();
		// 每个Map结构为一条数据，key与Adapter中定义的String数组中定义的一一对应。
	 
		
		CommonItemMeta meta2 = new CommonItemMeta();
		meta2.setTitle("取衣地址");
		meta2.setLayoutType(ListItemTypeConst.EDIT_TEXT);
		meta2.setContent(orderReq.getOrder().getTake_addr());
		list.add(meta2);
		
		CommonItemMeta meta3 = new CommonItemMeta();
		meta3.setTitle("送回地址");
		meta3.setLayoutType(ListItemTypeConst.EDIT_TEXT);
		meta3.setContent("");
		list.add(meta3);
		
		CommonItemMeta meta4 = new CommonItemMeta();
		meta4.setTitle("联系人");
		meta4.setLayoutType(ListItemTypeConst.EDIT_TEXT);
		meta4.setContent(orderReq.getOrder().getCustomer_name());
		list.add(meta4);
		
		CommonItemMeta meta5 = new CommonItemMeta();
		meta5.setTitle("联系电话");
		meta5.setLayoutType(ListItemTypeConst.EDIT_TEXT);
		meta5.setContent(orderReq.getOrder().getPhone());
		list.add(meta5);
  
		CommonItemMeta meta6 = new CommonItemMeta();
		meta6.setTitle("总价");
		meta6.setLayoutType(ListItemTypeConst.TEXT_CONTENT);
		meta6.setContent(orderReq.getOrder().getTotal_price());
		list.add(meta6);
		
		CommonItemMeta meta7 = new CommonItemMeta();
		meta7.setTitle("付款方式");
		meta7.setLayoutType(ListItemTypeConst.EDIT_TEXT);
		meta7.setContent(orderReq.getOrder().getPay_option());
		list.add(meta7);
		
		CommonItemMeta meta8 = new CommonItemMeta();
		meta8.setTitle("您的要求");
		meta8.setLayoutType(ListItemTypeConst.TEXT_CONTENT);
		meta8.setContent(orderReq.getOrder().getOrder_note());
		list.add(meta8);

		
		return list;
	}
	
	@Override
	public int getItemTypeCount()
	{
		// TODO Auto-generated method stub
		return 4;
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
				view = inflater.inflate(R.layout.list_item_imgtype, null);
				TextView title_view = (TextView) view.findViewById(R.id.item_imgtype_name);
				ImageView img = (ImageView) view.findViewById(R.id.item_imgtype_img);
				title_view.setText(title);
			}
			
			break;
		case ListItemTypeConst.TEXT_CONTENT:
			{
				view = inflater.inflate(R.layout.list_item_texttype, null);
				TextView title_view = (TextView) view.findViewById(R.id.item_texttype_name);
				TextView content_view = (TextView) view.findViewById(R.id.item_texttype_text);
				title_view.setText(title);
				content_view.setText( String.valueOf(item.getContent()));
			}
			
			break;
		case ListItemTypeConst.EDIT_TEXT:
			{
				view = inflater.inflate(R.layout.list_item_texttype, null);
				TextView title_view = (TextView) view.findViewById(R.id.item_texttype_name);
				TextView content_view = (TextView) view.findViewById(R.id.item_texttype_text);
				title_view.setText(title);
				content_view.setText( String.valueOf(item.getContent()));
			}
			
		break;	
		case ListItemTypeConst.DEFAULT_CONTENT:
			{
				view = inflater.inflate(R.layout.list_item_btntype, null);
				TextView title_view = (TextView) view.findViewById(R.id.item_btntype_name);
				title_view.setText(title);
			}
			
			break;
		case ListItemTypeConst.NULL_CONTENT:
			{
				view = inflater.inflate(R.layout.list_item_divider, null);
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
