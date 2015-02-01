package cn.fuego.laundry.ui.loader;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.cache.AppCache;
import cn.fuego.laundry.webservice.up.model.GetCustomerReq;
import cn.fuego.laundry.webservice.up.model.GetCustomerRsp;
import cn.fuego.laundry.webservice.up.model.base.CustomerJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.http.MispHttpHandler;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.webservice.up.model.base.UserJson;

public class CustomerLoader implements LoaderListener  
{
	private FuegoLog log = FuegoLog.getLog(getClass());

	public void loadCustomer(final String token,final UserJson user)
	{
		if(null == user)
		{
			log.error("the user is empty");
			return;
		}
		GetCustomerReq req = new GetCustomerReq();
		req.setToken(token);
		req.setObj(user.getUser_id());
		
		WebServiceContext.getInstance().getCustomerManageRest(new MispHttpHandler()
		{

			@Override
			public void handle(MispHttpMessage message)
			{
				if(message.isSuccess())
				{
					 
					GetCustomerRsp rsp = (GetCustomerRsp) message.getMessage().obj;
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
			
			
		}).getCustomerInfo(req);
	}

	@Override
	public void loadFinish(Object object)
	{
		AppCache.getInstance().update((CustomerJson) object);
		
	}

	@Override
	public void loadError(int errorCode)
	{
		// TODO Auto-generated method stub
		
	}

}
