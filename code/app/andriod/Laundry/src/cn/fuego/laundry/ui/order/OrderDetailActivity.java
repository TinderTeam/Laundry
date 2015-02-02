package cn.fuego.laundry.ui.order;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.constant.OrderStatusEnum;
import cn.fuego.laundry.ui.LoginActivity;
import cn.fuego.laundry.ui.cart.CartProduct;
import cn.fuego.laundry.ui.user.UserFragment;
import cn.fuego.laundry.webservice.up.model.GetOrderDetailReq;
import cn.fuego.laundry.webservice.up.model.GetOrderDetailRsp;
import cn.fuego.laundry.webservice.up.model.OperateOrderReq;
import cn.fuego.laundry.webservice.up.model.base.OrderDetailJson;
import cn.fuego.laundry.webservice.up.model.base.OrderJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.common.alipay.MispPayActivity;
import cn.fuego.misp.ui.common.alipay.MispPayParameter;
import cn.fuego.misp.ui.info.MispInfoListActivity;
import cn.fuego.misp.ui.model.CommonItemMeta;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.misp.webservice.up.model.base.CompanyJson;

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
		this.activityRes.setSaveBtnName(getOperateByStatus(order.getOrder_status()));
		getOrderDetail();
 		
	}
	private String getOperateByStatus(String status)
	{
		if(OrderStatusEnum.OrderSubmit.getStrValue().equals(status))
		{
			return "取消";
		}
		else if(OrderStatusEnum.PaySuccess.getStrValue().equals(status))
		{
			return "";
		}
		else if(OrderStatusEnum.WaitBuyerPay.getStrValue().equals(status))
		{
			return "付款";
		}
		else if(OrderStatusEnum.PayFinished.getStrValue().equals(status))
		{
			return "";
		}
		else if(OrderStatusEnum.OrderComplete.getStrValue().equals(status))
		{
			return "删除";
		}
		else if(OrderStatusEnum.OrderCancel.getStrValue().equals(status))
		{
			return "删除";
		}
		else if(OrderStatusEnum.OrderAbolish.getStrValue().equals(status))
		{
			return "";
		}
		else if(OrderStatusEnum.OnOperating.getStrValue().equals(status))
		{
			return "";
		}
		return "";
	}
	private List<CommonItemMeta> getBtnData(OrderJson order)
	{
		 
		List<CommonItemMeta> list = new ArrayList<CommonItemMeta>();
		
		if(null != order)
		{
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "订单号", order.getOrder_code(),String.valueOf(R.drawable.icon_order_num)));

			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "订单状态", order.getOrder_status(),String.valueOf(R.drawable.icon_order_status)));

			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "下单时间", order.getCreate_time(),String.valueOf(R.drawable.icon_order_time)));
			
			list.add(new CommonItemMeta(CommonItemMeta.DIVIDER_ITEM, null, null));

			
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "取衣地址", order.getTake_addr(),String.valueOf(R.drawable.icon_addr_out)));

			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "送回地址", order.getDelivery_addr(),String.valueOf(R.drawable.icon_addr_home)));
			
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "联系人", order.getContact_name(),String.valueOf(R.drawable.icon_contact_name)));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "联系电话", order.getPhone(),String.valueOf(R.drawable.icon_contact_phone)));
			
			
			list.add(new CommonItemMeta(CommonItemMeta.DIVIDER_ITEM, null, null));

	 
			
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "总价", CartProduct.getInstance().getDispPrice(order.getPrice_type(), order.getTotal_price()),String.valueOf(R.drawable.icon_order_sum)));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "付款方式", order.getPay_option(),String.valueOf(R.drawable.icon_pay_way)));
			list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "您的要求", order.getOrder_note(),String.valueOf(R.drawable.icon_customer_note)));
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
				list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, detail.getProduct_name(), detail.getQuantity() +"*" +CartProduct.getInstance().getDispPrice(detail.getPrice_type(),detail.getCurrent_price()),MemoryCache.getImageUrl()+detail.getProduct_img()));
			}
		}
		list.add(new CommonItemMeta(CommonItemMeta.DIVIDER_ITEM, null, null));


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
					 if(message.getErrorCode() == MISPErrorMessageConst.ERROR_LOGIN_INVALID)
					 {
						 LoginActivity.jump(OrderDetailActivity.this, 1);
						 finish();
					 }
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
	@Override
	public void saveOnClick(View v)
	{
	
		String status = order.getOrder_status();
		
		OperateOrderReq req = new  OperateOrderReq();
		req.setObj(order.getOrder_id());
		if(OrderStatusEnum.OrderSubmit.getStrValue().equals(status))
		{
			  //"取消";
 
			WebServiceContext.getInstance().getOrderManageRest(this).cancel(req);
		}
		else if(OrderStatusEnum.WaitBuyerPay.getStrValue().equals(status))
		{
			 //"付款";
			MispPayParameter parameter = new MispPayParameter();
			parameter.setOrder_code(order.getOrder_code());
			parameter.setOrder_name(order.getOrder_name());
			parameter.setOrder_desc(order.getOrder_note());
			parameter.setOrder_price(String.valueOf(order.getTotal_price()));
			parameter.setNotify_url(AppCache.PAY_NOTIFY_URL);
			CompanyJson company = AppCache.getInstance().getCompany();
			if(null != company)
			{
				parameter.setSeller(company.getAlipay_seller());
				parameter.setPartner(company.getAlipay_partner());
				parameter.setRsa_private(company.getAlipay_private_key());
				MispPayActivity.jump(this, parameter);

			}
			else
			{
				showMessage("订单提交成功，支付异常");
			}
		}
		else if(OrderStatusEnum.OrderComplete.getStrValue().equals(status))
		{

			WebServiceContext.getInstance().getOrderManageRest(this).delete(req);
			// "删除";
		}
		else if(OrderStatusEnum.OrderCancel.getStrValue().equals(status))
		{
 
			WebServiceContext.getInstance().getOrderManageRest(this).delete(req);
			// "删除";
		}

		
	}
	@Override
	public void handle(MispHttpMessage message)
	{
		if(message.isSuccess())
		{
			this.finish();
		}
		showMessage(message);
		 if(message.getErrorCode() == MISPErrorMessageConst.ERROR_LOGIN_INVALID)
		 {
			 LoginActivity.jump(this, 1);
		 }
	}
 

 
 
}
