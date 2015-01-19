package cn.fuego.misp.ui.base;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import cn.fuego.laundry.R;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.http.HttpListener;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.model.FragmentResInfo;

public abstract class MispBaseFragment extends Fragment implements HttpListener 
{ 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		this.initRes();
		

		View rootView = inflater.inflate(this.fragmentRes.getFragmentView(), null);
		TextView titleView = (TextView) rootView.findViewById(R.id.misp_title_name);
		if(null != titleView)
		{
			titleView.setText(this.fragmentRes.getName());
		}
		return rootView;
	}
	
	public void showMessage(int errorCode)
	{
		showMessage(MISPErrorMessageConst.getMessageByErrorCode(errorCode));
	}
	public void showMessage(String message)
	{
		Toast toast;
		toast = Toast.makeText(this.getActivity(), message , Toast.LENGTH_LONG);
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

}
