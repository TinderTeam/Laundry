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
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.PopupWindow.OnDismissListener;
import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.OnWheelChangedListener;
import antistatic.spinnerwheel.adapters.NumericWheelAdapter;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.R;
import cn.fuego.laundry.ui.home.GroupAdapter;
import cn.fuego.laundry.webservice.up.model.base.OrderDetailJson;
import cn.fuego.misp.service.MemoryCache;

public class MispPopListWindow
{
	private PopupWindow popupWindow=null;  
	private  ListView  listView = null;
	private  GroupAdapter  fatherAdapter;
	private Activity contex;
	private List<String> dataList = new ArrayList<String>();
	private MispPopWindowListener listener;
	
	public MispPopListWindow(Activity context,MispPopWindowListener listener,List<String> dataList)
	{
		this.contex = context;
 		if(!ValidatorUtil.isEmpty(dataList))
		{
			this.dataList = dataList;

		}
		this.listener = listener;
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
  
        	 View view = layoutInflater.inflate(R.layout.misp_pop_list_window, null);  

           
         
            listView  = (ListView) view.findViewById(R.id.misp_pop_list);
            

            fatherAdapter = new GroupAdapter(contex, dataList);  
            listView.setAdapter(fatherAdapter);  

            int width =  (int) (300*MemoryCache.getDensity());
            popupWindow = new PopupWindow(view, width, ViewGroup.LayoutParams.WRAP_CONTENT);
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
  
		popupWindow.showAsDropDown(parent, 0, 0);// 在屏幕居中，无偏移

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
                fatherAdapter.setSelectedPosition(position);
                fatherAdapter.notifyDataSetInvalidated();
            	listener.onConfirmClick((String)fatherAdapter.getItem(position));
            	popupWindow.dismiss(); 
            }  
        });


    } 

}
