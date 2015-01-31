package cn.fuego.misp.ui.pop;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;


public class MispDataSelector
{
 	
	private static MispDataSelector instance;
	public synchronized static MispDataSelector getInstance()
	{
		if(null == instance)
		{
			instance = new MispDataSelector();
		}
		return instance;
		
	}
	public void selectItem(Activity context,MispPopWindowListener listener,List<String> dataSource)
	{
//		MispPopListWindow popWin = new MispPopListWindow(context,listener,dataSource);
//		popWin.setTitle("分类");
//		popWin.setLocation(MispPopListWindow.SHOW_CENTER);
//		popWin.showWindow(v,this.selectType.getType_name());

	}
	public void selectItem(String tilte,Activity context,List<String> dataSource,final TextView view)
	{
		MispPopWindowListener listener = new MispPopWindowListener()
		{
			
			@Override
			public void onConfirmClick(String value)
			{
				if(null != view)
				{
					view.setText(value);
				}
				
			}
		};
		MispPopListWindow popWin = new MispPopListWindow(context,listener,dataSource);
		popWin.setTitle(tilte);
		popWin.setLocation(MispPopListWindow.SHOW_CENTER);
		popWin.showWindow(view,view.getText().toString());
	}

}
