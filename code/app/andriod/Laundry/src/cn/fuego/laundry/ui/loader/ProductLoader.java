package cn.fuego.laundry.ui.loader;

import java.util.List;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.ui.cart.CartProduct;
import cn.fuego.laundry.webservice.up.model.GetProductListReq;
import cn.fuego.laundry.webservice.up.model.GetProductListRsp;
import cn.fuego.laundry.webservice.up.model.base.CustomerJson;
import cn.fuego.laundry.webservice.up.model.base.ProductJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;

public class ProductLoader implements LoaderListener  
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	public void load()
	{
		GetProductListReq req = new GetProductListReq();
		WebServiceContext.getInstance().getProductManageRest(new MispHttpHandler()
		{

			@Override
			public void handle(MispHttpMessage message)
			{
				if(message.isSuccess())
				{
					 
					GetProductListRsp rsp = (GetProductListRsp) message.getMessage().obj;
					if(null != rsp.getObj())
					{
						loadFinish(rsp.getObj());
 					}
					else
					{
						
						loadError(MISPErrorMessageConst.ERROR_LOGIN_FAILED);

 					}

				}
				else
				{
					loadError(message.getErrorCode());

 					log.error("can not get the customer information");
				}
			}
			
		}).getAllProductList(req);

	}

	@Override
	public void loadFinish(Object object)
	{
		if(null != object)
		{
			CartProduct.getInstance().refreshProduct((List<ProductJson>) object);

		}
		
	}

	@Override
	public void loadError(int errorCode)
	{
		// TODO Auto-generated method stub
		
	}

}
