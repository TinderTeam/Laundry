package cn.fuego.misp.ui.pop;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.ui.home.GroupAdapter;
import cn.fuego.misp.constant.MispCommonIDName;
import cn.fuego.misp.service.MemoryCache;

public class MispPopListWindow
{
	private PopupWindow popupWindow=null;  
	private ListView  listView = null;
	private TextView titleView;
	private GroupAdapter  fatherAdapter;
	private Activity contex;
	private List<String> dataList = new ArrayList<String>();
	private MispPopWindowListener listener;
	
	//size
	private int width=(int) (300*MemoryCache.getDensity());
	private int height=ViewGroup.LayoutParams.WRAP_CONTENT;
	private int location=SHOW_CENTER;
	private boolean showTitleFlag = true;
	
	private String title = "";
	public static final int SHOW_AS_DROP_DOWN = 1;
	public static final int SHOW_CENTER=0;
	
	
	public MispPopListWindow(Activity context,MispPopWindowListener listener,List<String> dataList)
	{
		this.contex = context;
 		if(!ValidatorUtil.isEmpty(dataList))
		{
			this.dataList = dataList;

		}
		this.listener = listener;
	}
	
	
	
	public void setSize(int height,int width){
		this.setWidth(width);
		this.setHeight(height);
	}
	
	private int getPosition(String type)
	{
		for(int i=0;i<dataList.size();i++)
		{
			if(dataList.get(i).equals(type))
			{
				return i;
			}
		}
		return 0;
	}
	
	public void showWindow(View parent,String selectItem) 
	{  
        if (popupWindow == null)
        {  
            LayoutInflater layoutInflater = (LayoutInflater) this.contex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
        	View view = layoutInflater.inflate(MispCommonIDName.layout_misp_pop_list_window, null);  
        	listView  = (ListView) view.findViewById(MispCommonIDName.misp_pop_list);
        	
        	titleView = (TextView) view.findViewById(MispCommonIDName.misp_pop_title);
        	titleView.setText(title);
        	
        	if(!showTitleFlag){
        		titleView.setVisibility(View.GONE);
        	}
        	
            fatherAdapter = new GroupAdapter(contex, dataList);  
            listView.setAdapter(fatherAdapter);  
            popupWindow = new PopupWindow(view, width, height);
            // 使其聚集  
            popupWindow.setFocusable(true);  
            //点击外部消失
            popupWindow.setOutsideTouchable(true);  
            ColorDrawable dw = new ColorDrawable(0x90000000);  
            popupWindow.setBackgroundDrawable(dw);
        } 
        
        //设置背景变暗
        WindowManager.LayoutParams lp=this.contex.getWindow().getAttributes();
        lp.alpha = 0.4f;
        this.contex.getWindow().setAttributes(lp); 
        
        fatherAdapter.setSelectedPosition(this.getPosition(selectItem));
        fatherAdapter.notifyDataSetChanged();
  
		switch(location)
		{
			case SHOW_CENTER: 
				popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);	//在屏幕居中，无偏移
			break;
			
			case SHOW_AS_DROP_DOWN: 
				popupWindow.showAsDropDown(parent, 0, 0);	//在屏幕居中，无偏移
			break;
		}
		
		// 监听popwindow消失事件，并对radioGroup清零
		popupWindow.setOnDismissListener(new OnDismissListener()
		{
			@Override
			public void onDismiss()
			{
				WindowManager.LayoutParams lp = contex.getWindow()
						.getAttributes();
				lp.alpha = 1f;
				contex.getWindow().setAttributes(lp);
				popupWindow = null;
			}
		});

        listView.setOnItemClickListener(new OnItemClickListener()
        {  
  
            @Override  
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
            	view.getBackground().setAlpha(40);
                fatherAdapter.setSelectedPosition(position);
                fatherAdapter.notifyDataSetInvalidated();
            	listener.onConfirmClick((String)fatherAdapter.getItem(position));
            	popupWindow.dismiss(); 
            }  
        });


    }

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}



	/**
	 * @return the location
	 */
	public int getLocation()
	{
		return location;
	}



	/**
	 * @param location the location to set
	 */
	public void setLocation(int location)
	{
		this.location = location;
	}



	public boolean isShowTitleFlag()
	{
		return showTitleFlag;
	}



	public void setShowTitleFlag(boolean showTitleFlag)
	{
		this.showTitleFlag = showTitleFlag;
	}



	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}



	/**
	 * @param title the title to set
	 */
	public void setTitle(String title)
	{
		this.title = title;
	} 

}
