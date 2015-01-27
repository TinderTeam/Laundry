package cn.fuego.laundry.ui.order;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.constant.PayOptionEnum;
import cn.fuego.laundry.ui.MainTabbarActivity;
import cn.fuego.laundry.ui.MainTabbarInfo;
import cn.fuego.laundry.ui.cart.CartProduct;
import cn.fuego.laundry.ui.home.HomeFragment;
import cn.fuego.laundry.webservice.up.model.CreateOrderRsp;
import cn.fuego.laundry.webservice.up.model.base.OrderJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.common.alipay.MispPayActivity;
import cn.fuego.misp.ui.common.alipay.MispPayParameter;
import cn.fuego.misp.ui.info.MispInfoListActivity;
import cn.fuego.misp.ui.model.CommonItemMeta;

public class OrderActivity extends MispInfoListActivity
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	public static final String ORDER_INFO = "订单信息";
  
	private static final String EDIT_DELIVERY = "配送信息";
	
	private static final String BTN_VALUE ="点击修改";

	
	
	@Override
	public void initRes()
	{
 
		super.initRes();
 		this.activityRes.setName("提交订单");

		this.getDataList().clear();
		this.getDataList().addAll(getBtnData());
	}
 
	private List<CommonItemMeta> getBtnData()
	{
		OrderJson order = CartProduct.getInstance().getOrderInfo().getOrder();
			List<CommonItemMeta> list = new ArrayList<CommonItemMeta>();
			
			if(null != order)
			{
				list.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, EDIT_DELIVERY, BTN_VALUE));

				list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "取衣地址", order.getTake_addr()));

				list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "送回地址", order.getDelivery_addr()));
				
				list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "联系人", order.getContact_name()));
				list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "联系电话", order.getPhone()));
				
				
				list.add(new CommonItemMeta(CommonItemMeta.DIVIDER_ITEM, null, null));

		 
				list.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, ORDER_INFO, BTN_VALUE));

				list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "总价", order.getTotal_price()));
				list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "付款方式", order.getPay_option()));
				list.add(new CommonItemMeta(CommonItemMeta.LARGE_TEXT, "您的要求", order.getOrder_note()));
				
				list.add(new CommonItemMeta(CommonItemMeta.DIVIDER_ITEM, null, null));

				
				list.add(new CommonItemMeta(CommonItemMeta.SUBMIT_BUTTON, null, null));

			}

		
		return list;
	}
 
	
	 
	@Override
	public void onItemListClick(AdapterView<?> parent, View view, long id,
			CommonItemMeta item)
	{
		if(CommonItemMeta.BUTTON_TO_EDIT_ITEM == item.getLayoutType())
		{
			String content =   item.getTitle();
			Intent intent = new Intent();
			if(EDIT_DELIVERY.equals(content))
			{
				intent.setClass(this, AddrEditActivity.class);
				this.startActivityForResult(intent,1);
			}
			else if(ORDER_INFO.equals(content))
			{
				intent.setClass(this,  OrderEditActivity.class);
				this.startActivityForResult(intent,1);
			}
		}
		else if(CommonItemMeta.SUBMIT_BUTTON == item.getLayoutType())
		{

			submitOrder();
		}
	}
	
	private void submitOrder()
	{
	     final ProgressDialog proDialog =ProgressDialog.show(this, "请稍等", "订单正在提交......");
		WebServiceContext.getInstance().getOrderManageRest(new MispHttpHandler()
		{

			@Override
			public void handle(MispHttpMessage message)
			{
				 proDialog.dismiss();
				 if(message.isSuccess())
				 {
					 CreateOrderRsp rsp = (CreateOrderRsp) message.getMessage().obj;
					 if(null != rsp.getObj())
					 {
						 jumpToOrderList(rsp.getObj());
					 }
				 }
				 else
				 {
					 log.error("create order failed");

				 }
				 showMessage(message);
			}
			
		}).create(CartProduct.getInstance().getOrderInfo());
	}
 
 
	private void jumpToOrderList(OrderJson   order)
	{
 		if(PayOptionEnum.ONLINE_PAY.getStrValue().equals(order.getPay_option()))
		{
			MispPayParameter parameter = new MispPayParameter();
			parameter.setOrder_code(order.getOrder_code());
			parameter.setOrder_name(order.getOrder_name());
			parameter.setOrder_desc("描述");
			parameter.setOrder_price(String.valueOf(order.getTotal_price()));
			parameter.setNotify_url(AppCache.PAY_NOTIFY_URL);
			
			MispPayActivity.jump(this, parameter);
		}
		else
		{
	 		Intent i = new Intent();
			i.setClass(this, MainTabbarActivity.class);
			i.putExtra(MainTabbarActivity.SELECTED_TAB, MainTabbarInfo.getIndexByClass(HomeFragment.class));
	        startActivity(i);
		}
        CartProduct.getInstance().clearCart();
        this.finish();

	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		this.refreshList(this.getBtnData());
		 
	}


	 

}
