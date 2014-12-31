package cn.fuego.misp.ui.base;

import android.os.Handler;
import android.os.Message;
import cn.fuego.misp.service.http.HttpListener;
import cn.fuego.misp.service.http.MispHttpMessage;

public abstract class MispHttpFragment extends MispBaseFragment implements HttpListener
{
	private Handler handler = new Handler()
	{
		@Override
  		public void handleMessage(Message msg) 
		{
			 super.handleMessage(msg);
			 MispHttpMessage mispMessage = new MispHttpMessage();
			 mispMessage.setMessage(msg);
			 handle(mispMessage);
		}
	
	};
	
	public void sendMessage(MispHttpMessage message)
	{
		handler.sendMessage(message.getMessage());
	}

	
	
 
}
