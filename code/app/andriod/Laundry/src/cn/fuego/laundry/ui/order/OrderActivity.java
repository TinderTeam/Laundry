package cn.fuego.laundry.ui.order;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.constant.ListItemTypeConst;
import cn.fuego.laundry.webservice.up.model.CreateOrderReq;
import cn.fuego.laundry.webservice.up.model.base.DeliveryInfoJson;
import cn.fuego.laundry.webservice.up.model.base.OrderDetailJson;
import cn.fuego.laundry.webservice.up.model.base.OrderJson;
import cn.fuego.misp.ui.list.MispDistinctListActivity;
import cn.fuego.misp.ui.model.CommonItemMeta;

public class OrderActivity extends MispDistinctListActivity
{
	
	private CreateOrderReq orderReq;
 
	@Override
	public void initRes()
	{
		this.activityRes.setAvtivityView(R.layout.order_info);
		this.activityRes.setBackBtn(R.id.order_info_back);
		this.listViewRes.setListView(R.id.order_info_list);
		Intent intent = this.getIntent();
		orderReq = (CreateOrderReq) intent.getSerializableExtra("order_info");
 		
	}
 
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		this.dataList.clear();
		this.dataList.addAll(getBtnData());
	}



	private List<CommonItemMeta> getBtnData()
	{
		// 生成数据源
		List<CommonItemMeta> list = new ArrayList<CommonItemMeta>();
		// 每个Map结构为一条数据，key与Adapter中定义的String数组中定义的一一对应。
	 
		
		CommonItemMeta meta2 = new CommonItemMeta();
		meta2.setTitle("取衣地址");
		meta2.setLayoutType(ListItemTypeConst.TEXT_CONTENT);
		meta2.setContent(orderReq.getDeliveryInfo().getTake_addr());
		list.add(meta2);
		
		CommonItemMeta meta3 = new CommonItemMeta();
		meta3.setTitle("取衣时间");
		meta3.setLayoutType(ListItemTypeConst.TEXT_CONTENT);
		meta3.setContent("");
		list.add(meta3);
		
		CommonItemMeta meta4 = new CommonItemMeta();
		meta4.setTitle("联系人");
		meta4.setLayoutType(ListItemTypeConst.TEXT_CONTENT);
		meta4.setContent(orderReq.getDeliveryInfo().getCustomer_name());
		list.add(meta4);
		
		CommonItemMeta meta5 = new CommonItemMeta();
		meta5.setTitle("联系电话");
		meta5.setLayoutType(ListItemTypeConst.TEXT_CONTENT);
		meta5.setContent(orderReq.getDeliveryInfo().getPhone());
		list.add(meta5);
  
		CommonItemMeta meta6 = new CommonItemMeta();
		meta6.setTitle("总价");
		meta6.setLayoutType(ListItemTypeConst.TEXT_CONTENT);
		meta6.setContent(orderReq.getOrder().getTotal_price());
		list.add(meta6);
		
		CommonItemMeta meta7 = new CommonItemMeta();
		meta7.setTitle("付款方式");
		meta7.setLayoutType(ListItemTypeConst.TEXT_CONTENT);
		meta7.setContent(orderReq.getOrder().getPay_option());
		list.add(meta7);
		
		CommonItemMeta meta8 = new CommonItemMeta();
		meta8.setTitle("备注");
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
	public int getListItemType(CommonItemMeta item)
	{
		// TODO Auto-generated method stub
		return item.getLayoutType();
	}



	 

}
