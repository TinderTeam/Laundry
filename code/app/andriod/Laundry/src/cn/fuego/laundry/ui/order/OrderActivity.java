package cn.fuego.laundry.ui.order;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorRules;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.constant.PayOptionEnum;
import cn.fuego.laundry.constant.PriceTypeEnum;
import cn.fuego.laundry.ui.LoginActivity;
import cn.fuego.laundry.ui.MainTabbarActivity;
import cn.fuego.laundry.ui.MainTabbarInfo;
import cn.fuego.laundry.ui.cart.CartProduct;
import cn.fuego.laundry.ui.home.HomeFragment;
import cn.fuego.laundry.webservice.up.model.CreateOrderRsp;
import cn.fuego.laundry.webservice.up.model.base.OrderJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.constant.MispCommonIDName;
import cn.fuego.misp.service.MISPException;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.common.alipay.MispPayActivity;
import cn.fuego.misp.ui.common.alipay.MispPayParameter;
import cn.fuego.misp.ui.common.edit.MispEditParameter;
import cn.fuego.misp.ui.common.edit.MispTextEditActivity;
import cn.fuego.misp.ui.info.MispInfoListActivity;
import cn.fuego.misp.ui.model.CommonItemMeta;
import cn.fuego.misp.ui.pop.MispPopListWindow;
import cn.fuego.misp.ui.pop.MispPopWindowListener;
import cn.fuego.misp.webservice.up.model.base.AttributeJson;
import cn.fuego.misp.webservice.up.model.base.CompanyJson;

public class OrderActivity extends MispInfoListActivity
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	public static final String TAKE_ADDR = "取衣地址";
	public static final String SEND_ADDR = "送回地址";
	public static final String CONTACT_NAME = "联系人";

	public static final String CONTACT_PHONE = "联系电话";

	public static final String PAY_OPTION = "付款方式";

	
	public static final String NOTE = "您的要求";

	
	private TextView tatolPriceView;

	
	
	@Override
	public void initRes()
	{
 
		super.initRes();
		
		this.activityRes.setAvtivityView(R.layout.order_confirm);
 		this.activityRes.setName("提交订单");
 		this.activityRes.getButtonIDList().add(R.id.misp_btn_submit);
		this.getDataList().clear();
		this.getDataList().addAll(getBtnData());
	}
 
	private List<CommonItemMeta> getBtnData()
	{
		OrderJson order = CartProduct.getInstance().getOrderInfo().getOrder();
			List<CommonItemMeta> list = new ArrayList<CommonItemMeta>();
			
			if(null != order)
			{
 
				list.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, TAKE_ADDR, order.getTake_addr()));

				list.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, SEND_ADDR, order.getDelivery_addr()));
				
				list.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, CONTACT_NAME, order.getContact_name()));
				list.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, CONTACT_PHONE, order.getPhone()));
				
				
				//list.add(new CommonItemMeta(CommonItemMeta.DIVIDER_ITEM, null, null));

		 
 
				//list.add(new CommonItemMeta(CommonItemMeta.TEXT_CONTENT, "总价", order.getTotal_price()));
				list.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, PAY_OPTION, order.getPay_option()));
				list.add(new CommonItemMeta(CommonItemMeta.BUTTON_TO_EDIT_ITEM, NOTE, order.getOrder_note()));
 

			}

		
		return list;
	}
 
	
	
	 
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		tatolPriceView = (TextView) findViewById(R.id.order_tatal_price_txt);
		
		String priceStr = "数量："+ CartProduct.getInstance().getOrderInfo().getOrder().getTotal_count() + ",";
		
		String priceType = CartProduct.getInstance().getOrderInfo().getOrder().getPrice_type();
		float price = CartProduct.getInstance().getOrderInfo().getOrder().getTotal_price();
		
		priceStr += "总价：" + CartProduct.getInstance().getDispPrice(priceType,price);

		tatolPriceView.setText(priceStr);
	}

	@Override
	public void onItemListClick(AdapterView<?> parent, View view, long id,
			CommonItemMeta item)
	{
		if(CommonItemMeta.BUTTON_TO_EDIT_ITEM == item.getLayoutType() || CommonItemMeta.LARGE_TEXT  == item.getLayoutType())
		{
			String title =   item.getTitle();
 			MispEditParameter data = new MispEditParameter();
			data.setTilteName(title);
			data.setDataKey(title);
			if(null != item.getContent())
			{
				data.setDataValue(String.valueOf(item.getContent()));
			}
			
			if(TAKE_ADDR.equals(title))
			{
				data.setDataRule(ValidatorRules.isLenght(0, 50));
				data.setErrorMsg("长度不能大于50");
				data.setPointOut("您可以使用LBS定位获取地址或手动填写地址！");
				AddrEditActivity.jump(this, data, 1);

			}
			else if(SEND_ADDR.equals(title))
			{
				data.setDataRule(ValidatorRules.isLenght(0, 50));
				data.setErrorMsg("长度不能大于50");
				data.setPointOut("您可以使用LBS定位获取地址或手动填写地址！");

				AddrEditActivity.jump(this, data, 1);

			}
			else if(CONTACT_NAME.equals(title))
			{
				data.setDataRule(ValidatorRules.isLenght(0, 10));
				data.setErrorMsg("长度不能大于10");
				data.setPointOut("请填写您的真实姓名，方便我们更好的服务于您！");

				MispTextEditActivity.jump(this, data, 1);
 
			}
			else if(CONTACT_PHONE.equals(title))
			{
				data.setDataRule(ValidatorRules.isPhone());
				data.setErrorMsg("电话格式不正确");
				data.setPointOut("请填写您的联系电话！\n手机格式:1**********\n座机格式:0***-******");


				MispTextEditActivity.jump(this, data, 1);

			}
			else if(NOTE.equals(title))
			{
				data.setDataRule(ValidatorRules.isLenght(0, 200));
				data.setErrorMsg("长度不能大于200");
				data.setPointOut("您可填写详细地址及其他要求,或静候工作人员与您取得联系,敬请保持手机畅通！");

				MispTextEditActivity.jump(this, data, 1);

			}
			else if(PAY_OPTION.equals(title))
			{
				if(PriceTypeEnum.FLOAT_PRICE.getStrValue().equals(CartProduct.getInstance().getOrderPriceType()))
				{
					showMessage("总价面议，只能使用送衣付款");
				}
				else
				{
					setPayOption(view);	
				}
				
			}
 
		}
 
	}
	
	private MispPopListWindow popWin = null;
	public void setPayOption(View v)
	{
		
		MispPopWindowListener listener = new MispPopWindowListener()
		{
			@Override
			public void onConfirmClick(String value)
			{
				CartProduct.getInstance().getOrderInfo().getOrder().setPay_option(value);
				refreshList(getBtnData());
 			}
			
		};
		
		if(null == popWin)
		{
			popWin = new MispPopListWindow(this,listener,PayOptionEnum.getAllStr());
			//popWin.setWidth((int) (300*MemoryCache.getDensity()));
			popWin.setTitle(PAY_OPTION);
			popWin.setLocation(MispPopListWindow.SHOW_CENTER);
		}
		popWin.showWindow(v,CartProduct.getInstance().getOrderInfo().getOrder().getPay_option());
	}
	
	
	
	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		if(ValidatorUtil.isEmpty(CartProduct.getInstance().getOrderInfo().getOrder().getPhone()))
		{
			showMessage("联系电话不能为空");
			return;
		}
		if(!ValidatorUtil.isPhone(CartProduct.getInstance().getOrderInfo().getOrder().getPhone()))
		{
			showMessage("电话号码无效");
			return;
		}
		submitOrder();
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
						 submitOrderSuccess(rsp.getObj());
					 }
				 }
				 else
				 {
					 
					 if(message.getErrorCode() == MISPErrorMessageConst.ERROR_LOGIN_INVALID)
					 {
						 LoginActivity.jump(OrderActivity.this, 1);
					 }
					 log.error("create order failed");

				 }
				 
				 showMessage(message);

				 
			}
			
		}).create(CartProduct.getInstance().getOrderInfo());
	}
	
	
 
 
	@Override
	public View getListItemView(LayoutInflater inflater, CommonItemMeta item)
	{
 		View view =null;
		int type= item.getLayoutType();
		String title= "";
		if(!ValidatorUtil.isEmpty(item.getTitle()))
		{
			title = item.getTitle();
		}
		String content = "";
		if(null != item.getContent())
		{
			content = String.valueOf(item.getContent());
		}
				
		
		switch(type)
		{
 
		case CommonItemMeta.BUTTON_TO_EDIT_ITEM:
			{
				view = inflater.inflate(R.layout.order_confirm_item, null);
				TextView title_view = (TextView) view.findViewById(R.id.item_btntype_name);
				if(null != title_view)
				{
					title_view.setText(title);
				}
				else
				{
					log.warn("can not find text view by id, the is item_btntype_name " + MispCommonIDName.item_btntype_name);
				}
				
				TextView valueView  = (TextView) view.findViewById(MispCommonIDName.item_btntype_value);
				if(null != valueView)
				{
					valueView.setText(content);	
				}
				else
				{
					log.warn("can not find text view by id, the is item_btntype_value " + MispCommonIDName.item_btntype_name);
				}
				
				
			}
			break;
		case CommonItemMeta.LARGE_TEXT:
		{
			view = inflater.inflate(R.layout.order_confirm_largetext_item, null);
			TextView title_view = (TextView) view.findViewById(R.id.item_btntype_name);
			if(null != title_view)
			{
				title_view.setText(title);
			}
			else
			{
				log.warn("can not find text view by id, the is item_btntype_name " + MispCommonIDName.item_btntype_name);
			}
			
			TextView valueView  = (TextView) view.findViewById(MispCommonIDName.item_btntype_value);
			if(null != valueView)
			{
				valueView.setText(content);	
			}
			else
			{
				log.warn("can not find text view by id, the is item_btntype_value " + MispCommonIDName.item_btntype_name);
			}
			
			
		}
		break;
	    default:
	    	throw new MISPException("the item layout can not be empty");
	    	 
 
		}
		 
		return view;
	}

	private void submitOrderSuccess(OrderJson   order)
	{

 		if(PayOptionEnum.ONLINE_PAY.getStrValue().equals(order.getPay_option()))
		{
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
		else
		{
	 		Intent intent = new Intent();
	 		intent.setClass(this, MainTabbarActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra(MainTabbarActivity.SELECTED_TAB, MainTabbarInfo.getIndexByClass(HomeFragment.class));
	        startActivity(intent);
		}
        CartProduct.getInstance().clearCart();
        this.finish();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if(null != data)
		{
			MispEditParameter result_value = (MispEditParameter) data
					.getSerializableExtra(AddrEditActivity.JUMP_DATA);

			if (null != result_value)
			{
				if (TAKE_ADDR.equals(result_value.getDataKey()))
				{
					CartProduct.getInstance().getOrderInfo().getOrder()
							.setTake_addr(result_value.getDataValue());
				}
				else if (SEND_ADDR.equals(result_value.getDataKey()))
				{
					CartProduct.getInstance().getOrderInfo().getOrder()
							.setDelivery_addr(result_value.getDataValue());

				}
				else if (CONTACT_NAME.equals(result_value.getDataKey()))
				{
					CartProduct.getInstance().getOrderInfo().getOrder()
							.setContact_name(result_value.getDataValue());

				}
				else if (CONTACT_PHONE.equals(result_value.getDataKey()))
				{
					CartProduct.getInstance().getOrderInfo().getOrder()
							.setPhone(result_value.getDataValue());

				}
				else if (NOTE.equals(result_value.getDataKey()))
				{
					CartProduct.getInstance().getOrderInfo().getOrder()
							.setOrder_note(result_value.getDataValue());

				}
			}
		}


		this.refreshList(this.getBtnData());

	}


	 

}
