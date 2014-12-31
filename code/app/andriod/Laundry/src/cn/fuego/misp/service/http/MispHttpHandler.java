package cn.fuego.misp.service.http;

import android.os.Handler;
import android.os.Message;

public class MispHttpHandler implements HttpListener
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
	@Override
	public void handle(MispHttpMessage message)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMessage(MispHttpMessage message)
	{
		handler.sendMessage(message.getMessage());
		
	}
 

}
