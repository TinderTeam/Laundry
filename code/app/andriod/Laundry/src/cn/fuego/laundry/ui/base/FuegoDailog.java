package cn.fuego.laundry.ui.base;

import android.app.AlertDialog;
import android.content.Context;

public class FuegoDailog
{
	public static void show(Context context,String title,String content)
	{
		new AlertDialog.Builder(context)    
	            .setTitle(title).setMessage(content) 
		                .setPositiveButton("确定", null) 
		                .show();  
	}

}
