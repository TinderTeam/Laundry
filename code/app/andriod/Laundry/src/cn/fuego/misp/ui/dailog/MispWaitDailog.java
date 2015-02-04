package cn.fuego.misp.ui.dailog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import cn.fuego.laundry.R;

public class MispWaitDailog extends Dialog
{
	Context context;

	public MispWaitDailog(Context context, int theme)
	{
		super(context, theme);
		this.context = context;
	 
	}

	public MispWaitDailog(Context context)
	{
		super(context,R.style.MispWaitDailog);
		this.context = context;
	 
	}

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.misp_wait_dialog);
    }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		// 按下键盘上返回按钮
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			dismiss();
		}
		return true;
	}

}
