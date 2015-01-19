package cn.fuego.misp.ui.base;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.R;
import cn.fuego.misp.constant.MISPErrorMessageConst;
import cn.fuego.misp.service.http.MispHttpMessage;
import cn.fuego.misp.ui.model.ActivityResInfo;

public abstract class MispBaseActivtiy extends Activity implements OnClickListener
{
	private Map<Integer,Button> buttonViewList = new HashMap<Integer,Button>();
	private TextView titleView;
	public void backOnClick()
	{
		this.finish();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		initRes();
		// TODO Auto-generated method stub
 		super.onCreate(savedInstanceState);
 	 
 		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
 		if(0 != activityRes.getAvtivityView())
 		{
 			setContentView(activityRes.getAvtivityView());

 		}
		View button = findViewById(activityRes.getBackBtn());
		if(null == button)
		{
			 button = findViewById(R.id.misp_title_back);
		}
		if(null != button)
		{
			
			OnClickListener l = new OnClickListener()
			{
				
				@Override
				public void onClick(View v)
				{
					backOnClick();
					
				}
			};
			button.setOnClickListener(l);
		}
 
		
		titleView = (TextView) findViewById(R.id.misp_title_name);
		if(null != titleView)
		{
			titleView.setText(this.activityRes.getName());
		}
		if(!ValidatorUtil.isEmpty(this.activityRes.getButtonIDList()))
		{
			for(Integer id :this.activityRes.getButtonIDList() )
			{
				Button btn =   (Button) findViewById(id);
				if(null != btn)
				{
					btn.setOnClickListener(this);
					buttonViewList.put(id, btn);
				}
			}
		}

	}
	
	
	
	public TextView getTitleView()
	{
		return titleView;
	}
	public Button getButtonByID(int id)
	{
		return this.buttonViewList.get(id);
	}

	public ActivityResInfo activityRes = new ActivityResInfo();

	public abstract void initRes();
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
	public int getScreenWidth()
	{
		DisplayMetrics metric = new DisplayMetrics();
	    getWindowManager().getDefaultDisplay().getMetrics(metric);
	    int width = metric.widthPixels;     // 屏幕宽度（像素）
 	    
	    return width;
	}
	public int getActivityHeight()
	{
		DisplayMetrics metric = new DisplayMetrics();
	    this.getWindowManager().getDefaultDisplay().getMetrics(metric);
 	    int height = metric.heightPixels;
 	    height = height-getStatusHeight(this);
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
	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		
	}
	

}
