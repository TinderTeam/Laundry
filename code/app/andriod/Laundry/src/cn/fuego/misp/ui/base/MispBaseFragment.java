package cn.fuego.misp.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
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
	public int getScreenHeight()
	{
		DisplayMetrics metric = new DisplayMetrics();
	    this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
 	    int height = metric.heightPixels;
	    
	    return height;
	}
	
	public int getImageIdByName(String name)
	{
		
		int imgId = getResources().getIdentifier(name, "drawable",getActivity().getPackageName());//图片名字不要加后缀名
		return imgId;
	}

}
