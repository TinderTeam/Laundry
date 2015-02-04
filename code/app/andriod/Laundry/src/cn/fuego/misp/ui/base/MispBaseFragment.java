package cn.fuego.misp.ui.base;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.constant.MispCommonIDName;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.model.FragmentResInfo;

public abstract class MispBaseFragment extends Fragment implements OnClickListener 
{ 
	private FuegoLog log = FuegoLog.getLog(MispBaseFragment.class);

	private Map<Integer,Button> buttonViewList = new HashMap<Integer,Button>();
	
	private TextView titleView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		this.initRes();
		View rootView = inflater.inflate(this.fragmentRes.getFragmentView(), null);
		
		//初始化标题
		initFragmentTitle(rootView);
		
				if(!ValidatorUtil.isEmpty(this.fragmentRes.getButtonIDList()))
		{
			for(Integer id :this.fragmentRes.getButtonIDList() )
			{
				Button btn =   (Button) rootView.findViewById(id);
				if(null != btn)
				{
					btn.setOnClickListener(this);
					buttonViewList.put(id, btn);
				}
				else
				{
					log.warn("the button id is not exist in the view, the id is "+id);
				}
			}
		}
		return rootView;
	}
	public Button getButtonByID(int id)
	{
		return this.buttonViewList.get(id);
	}
	private void initFragmentTitle(View rootView)
	{
		titleView = (TextView) rootView.findViewById(this.fragmentRes.getTitleView());	
		if(titleView!=null)
		{
			titleView.setText(this.fragmentRes.getName());
		}
		else
		{
			 titleView = (TextView) rootView.findViewById(MispCommonIDName.misp_title_name);
			if(null != titleView)
			{
				titleView.setText(this.fragmentRes.getName());
			}
		}
	}

	public void showMessage(int errorCode)
	{
		showMessage(MISPErrorMessageConst.getMessageByErrorCode(errorCode));
	}
	public void showMessage(String message)
	{
		Toast toast;
		toast = Toast.makeText(this.getActivity(), message , Toast.LENGTH_SHORT);
		toast.show();
	}
	public void showMessage(MispHttpMessage message)
	{
		showMessage(message.getErrorCode());	
	}
	public FragmentResInfo fragmentRes = new FragmentResInfo();
	public abstract void initRes();
	public int getScreenWidth()
	{
		DisplayMetrics metric = new DisplayMetrics();
	    this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
	    int width = metric.widthPixels;     // 屏幕宽度（像素）
 	    
	    return width;
	}
	public int getActivityHeight()
	{
		DisplayMetrics metric = new DisplayMetrics();
	    this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
 	    int height = metric.heightPixels;
 	    height = height-getStatusHeight(this.getActivity());
	    return height;
	}
	public int getStatusHeight(Activity activity){
        int statusHeight = 0;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight){
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = activity.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }  
        }
        return statusHeight;
    }
	public int getImageIdByName(String name)
	{
		
		int imgId = getResources().getIdentifier(name, "drawable",getActivity().getPackageName());//图片名字不要加后缀名
		return imgId;
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		
	}
}
