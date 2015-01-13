package cn.fuego.laundry.ui.order;

import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.webservice.up.model.CreateOrderReq;
import cn.fuego.laundry.webservice.up.model.OperateOrderReq;
import cn.fuego.laundry.webservice.up.model.base.OrderDetailJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.ui.list.MispListActivity;

public class OrderDetailActivity extends MispListActivity<OrderDetailJson> implements OnClickListener
{
	private FuegoLog log = FuegoLog.getLog(getClass());
	public static final String ORDER_INFO = "order_info";

	private CreateOrderReq orderReq;
	@Override
	public void initRes()
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadSendList()
	{
		OperateOrderReq req  = new OperateOrderReq();
		WebServiceContext.getInstance().getOrderManageRest(this).show(req);
		
	}

	@Override
	public List<OrderDetailJson> loadListRecv(Object obj)
	{
		CreateOrderReq rsp = new CreateOrderReq();
		return rsp.getOrderDetailList();
	}

	@Override
	public View getListItemView(View view, OrderDetailJson item)
	{
		// TODO Auto-generated method stub
		return null;
	}


 
 
}
