package cn.fuego.laundry.ui.order;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.webservice.up.model.GetOrderDetailReq;
import cn.fuego.laundry.webservice.up.model.GetOrderDetailRsp;
import cn.fuego.laundry.webservice.up.model.base.OrderDetailJson;
import cn.fuego.laundry.webservice.up.model.base.OrderJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.info.MispInfoListActivity;
import cn.fuego.misp.ui.model.CommonItemMeta;
import cn.fuego.misp.ui.model.ListViewResInfo;

public class OrderDetailActivity extends MispInfoListActivity
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	public static final String ORDER_INFO = "order_info";

	private OrderJson order;
	@Override
	public void initRes()
	{
		Intent intent = this.getIntent();
		order = (OrderJson) intent.getSerializableExtra(ListViewResInfo.SELECT_ITEM);
	 
		super.initRes();
		this.activityRes.setName("订单详情");
		getOrderDetail();
 		
	}
	private List<CommonItemMeta> getBtnData(OrderJson order)
	{
		 
		List<CommonItemMeta> list = new ArrayList<CommonItemMeta>();
		
		if(null != order)
		{
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "取衣地址", order.getTake_addr()));

			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "送回地址", order.getDelivery_addr()));
			
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "联系人", order.getContact_name()));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "联系电话", order.getPhone()));
			
			
			list.add(new CommonItemMeta(CommonItemMeta.DIVIDER_ITEM, null, null));

	 
			
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "总价", order.getTotal_price()));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "付款方式", order.getPay_option()));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "您的要求", order.getOrder_note()));
		}

		return list;
	}
	
	private List<CommonItemMeta> getOrderDetailData(List<OrderDetailJson> orderDetailList)
	{
		List<CommonItemMeta> list = new ArrayList<CommonItemMeta>();
		if(!ValidatorUtil.isEmpty(orderDetailList))
		{
			for(OrderDetailJson detail : orderDetailList)
			{
				list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, detail.getProduct_name(), detail.getCurrent_price()));
			}
		}

		return list;

	}
	
	public void getOrderDetail()
	{
		GetOrderDetailReq  req = new GetOrderDetailReq();
		req.setObj(order.getOrder_id());
		WebServiceContext.getInstance().getOrderManageRest(new MispHttpHandler()
		{

			@Override
			public void handle(MispHttpMessage message)
			{
				 if(message.isSuccess())
				 {
					 GetOrderDetailRsp rsp = (GetOrderDetailRsp) message.getMessage().obj;
					 
					 reloadData(order,rsp.getObj());
					 repaint();

				 }
				 else
				 {
					 log.error("can not get order detali by id"+order.getOrder_id());
					 showMessage(message);
				 }
			}
			
		}
		).getOrderDetailList(req);
	}
	
	public void reloadData(OrderJson order,List<OrderDetailJson> detaliList)
	{
		this.getDataList().clear();
		this.getDataList().addAll(getBtnData(order));
		this.getDataList().add(new CommonItemMeta(CommonItemMeta.DIVIDER_ITEM, null, null));
		
		this.getDataList().addAll(getOrderDetailData(detaliList));

	}
 

 
 
}
