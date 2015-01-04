package cn.fuego.laundry.ui.cart;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.OnWheelChangedListener;
import antistatic.spinnerwheel.adapters.NumericWheelAdapter;
import cn.fuego.laundry.R;
import cn.fuego.laundry.ui.home.HomeProductActivity;
import cn.fuego.laundry.ui.order.OrderActivity;
import cn.fuego.laundry.webservice.up.model.base.OrderDetailJson;
import cn.fuego.laundry.webservice.up.model.base.OrderJson;
import cn.fuego.misp.ui.list.MispListFragment;

public class MyCartFragment extends MispListFragment<OrderDetailJson> implements OnClickListener
{
	private OrderJson order = new OrderJson();
	private PopupWindow popupWindow=null;  
	private View view;  
	private EditText amountView;
	private  String curNum="1";
	@Override
	public void initRes()
	{
		this.fragmentRes.setImage(R.drawable.tab_icon_cart);
		this.fragmentRes.setName(R.string.tabbar_cart);
		this.fragmentRes.setFragmentView(R.layout.chart_fragment);
 
		listViewRes.setListView(R.id.chart_list);
		listViewRes.setListItemView(R.layout.chart_list_item);
		listViewRes.setClickActivityClass(HomeProductActivity.class);
		OrderDetailJson json = new OrderDetailJson();
		json.setProduct_name("裤子");
		json.setProduct_price((float)1.1);
		json.setQuantity(1);
		this.dataList.add(json);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View rootView = super.onCreateView(inflater, container, savedInstanceState);
		Button submitButton = (Button) rootView.findViewById(R.id.chart_submit);
		submitButton.setOnClickListener(this);
		super.adapterForScrollView();
		return rootView;
	}




	@Override
	public void loadSendList()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public View getListItemView(View view, OrderDetailJson item)
	{
		final TextView nameView = (TextView) view.findViewById(R.id.chart_list_item_name);
		nameView.setText(item.getProduct_name());
		
		TextView priceView = (TextView) view.findViewById(R.id.chart_list_item_price);
		priceView.setText(String.valueOf(item.getProduct_price()));
		
		amountView = (EditText) view.findViewById(R.id.chart_list_item_quantity);
		//强制关闭软键盘
		InputMethodManager imm = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(amountView.getWindowToken(), 0);
		amountView.setInputType(InputType.TYPE_NULL);
		amountView.setText(String.valueOf(item.getQuantity()));
		amountView.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{

				showWindow(v,amountView,nameView.getText().toString());

			}
		});

		return view;
	}

	@Override
	public List<OrderDetailJson> loadListRecv(Object obj)
	{
		return null;
	}


	@Override
	public void onClick(View v)
	{
	 
		Intent intent = new Intent(this.getActivity(),OrderActivity.class);
 
		this.startActivity(intent);

		
	}
	private void showWindow(View parent,final EditText txt_view,String defTitle) 
	{  

		Button sure_btn =null;

        if (popupWindow == null)
        {  
            LayoutInflater layoutInflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
  
            view = layoutInflater.inflate(R.layout.pop_window_num, null);  

            TextView  title= (TextView) view.findViewById(R.id.pop_window_title);
            title.setText(defTitle);
            sure_btn = (Button) view.findViewById(R.id.pop_window_sure_btn);
    	   //   day.setViewAdapter(dayAdapter);
    	    //    day.setCurrentItem(dayAdapter.getToday());
    		// 创建一个PopuWidow对象
			 popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        }  
  
        // 使其聚集  
        popupWindow.setFocusable(true);  
        // 设置允许在外点击消失  
        popupWindow.setOutsideTouchable(true);  

        //实例化一个ColorDrawable颜色为半透明 
        ColorDrawable dw = new ColorDrawable(0xb0000000);  
        popupWindow.setBackgroundDrawable(dw);
/*        //点击底部页面消失pop
        View bottomview = view.findViewById(R.id.pop_window_bottom);
        bottomview.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				popupWindow.dismiss(); 
				
			}
		});
       */
		final AbstractWheel num = (AbstractWheel)view.findViewById(R.id.chart_list_item_num);
		NumericWheelAdapter numAdapter = new NumericWheelAdapter(this.getActivity(), 1, 20);
		num.setViewAdapter(numAdapter);
		num.setCurrentItem(0);
		num.setVisibleItems(5);
		num.addChangingListener(new OnWheelChangedListener()
		{
			
			@Override
			public void onChanged(AbstractWheel wheel, int oldValue, int newValue)
			{
				//Toast.makeText(ctx, "newValue:"+String.valueOf(newValue), Toast.LENGTH_SHORT).show();
				curNum =String.valueOf(newValue+1);
				
			}
		});
		sure_btn.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				//amountView.setText(curNum);
				txt_view.setText(curNum);
				popupWindow.dismiss();
				
			}
		});

        WindowManager windowManager = (WindowManager) this.getActivity().getSystemService(Context.WINDOW_SERVICE);  

        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);//在屏幕居中，无偏移
        //监听popwindow消失事件，并对radioGroup清零
       popupWindow.setOnDismissListener(new OnDismissListener()
		{		
			@Override
			public void onDismiss()
			{
				popupWindow=null;
			}
		});

    } 





}
