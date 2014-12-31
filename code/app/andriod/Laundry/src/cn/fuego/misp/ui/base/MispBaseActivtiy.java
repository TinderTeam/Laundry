package cn.fuego.misp.ui.base;

import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.http.MispHttpMessage;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class MispBaseActivtiy extends Activity 
{

	public void showMessage(MispHttpMessage message)
	{
		showMessage(message.getErrorCode());	
	}
	
	public void showMessage(int errorCode)
	{
		showMessage(MISPErrorMessageConst.getMessageByErrorCode(errorCode));
	}
	
	public void showMessage(String message)
	{
		Toast toast;
		toast = Toast.makeText(getApplicationContext(), message , Toast.LENGTH_LONG);
		toast.show();
	}
	

}
