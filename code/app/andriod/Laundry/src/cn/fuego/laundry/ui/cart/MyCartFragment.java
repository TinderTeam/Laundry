package cn.fuego.laundry.ui.cart;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.R;
import cn.fuego.laundry.constant.PriceTypeEnum;
import cn.fuego.laundry.ui.LoginActivity;
import cn.fuego.laundry.ui.home.HomeFragment;
import cn.fuego.laundry.ui.home.HomeProductActivity;
import cn.fuego.laundry.ui.order.OrderActivity;
import cn.fuego.laundry.webservice.up.model.base.OrderDetailJson;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.ui.list.MispListFragment;

public class MyCartFragment extends MispListFragment<OrderDetailJson>  
{
	private FuegoLog log = FuegoLog.getLog(MyCartFragment.class);
	
	private PopupWindow popupWindow=null;  
 	private TextView totalPriceView;
	private TextView totalCountView;
	private View view;  
	private  int  curNum= 1;
 	@Override
	public void initRes()
	{
		this.fragmentRes.setImage(R.drawable.tab_icon_cart);
		this.fragmentRes.setName(R.string.tabbar_cart);
		
		if(CartProduct.getInstance().isEmpty())
		{
			this.fragmentRes.setFragmentView(R.layout.cart_fragment_default);
			this.fragmentRes.getButtonIDList().add(R.id.cart_submit);

		}
		else
		{
			this.fragmentRes.setFragmentView(R.layout.cart_fragment);
			 
			listViewRes.setListView(R.id.chart_list);
			listViewRes.setListItemView(R.layout.cart_list_item);
			listViewRes.setClickActivityClass(HomeProductActivity.class);
			
			this.fragmentRes.getButtonIDList().add(R.id.cart_submit);
			this.fragmentRes.getButtonIDList().add(R.id.cart_to_product);

			this.setDataList(CartProduct.getInstance().getOrderInfo().getOrderDetailList());  
		}

  
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View rootView = super.onCreateView(inflater, container, savedInstanceState);
		
		if(!CartProduct.getInstance().isEmpty())
		{
			totalPriceView = (TextView) rootView.findViewById(R.id.chart_total_price);
			
			totalCountView = (TextView) rootView.findViewById(R.id.chart_total_count);
	 
			refreshView();
		}

 
		return rootView;
	}

 


	@Override
	public void loadSendList()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public View getListItemView(View view, final OrderDetailJson item)
	{
		final TextView nameView = (TextView) view.findViewById(R.id.chart_list_item_name);
		nameView.setText(item.getProduct_name());
		
		TextView priceView = (TextView) view.findViewById(R.id.chart_list_item_price);
		priceView.setText(String.valueOf(item.getCurrent_price()));
		
		 EditText  amountView = (EditText) view.findViewById(R.id.chart_list_item_quantity);
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

				showWindow(v,item);

			}
		});
		
		 Button  deleteBtn = (Button) view.findViewById(R.id.char_list_item_btn_delete);

		 deleteBtn.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View v)
				{

					CartProduct.getInstance().removeSelected(item.getProduct_id());
					refreshView();

				}
			});
		return view;
	}
	
	private void refreshView()
	{
 
		if(CartProduct.getInstance().isEmpty())
		{
 
			totalPriceView.setVisibility(View.INVISIBLE);
			totalCountView.setVisibility(View.INVISIBLE);
			this.getActivity().findViewById(R.id.cart_text).setVisibility(View.INVISIBLE);
			this.getButtonByID(R.id.cart_submit).setVisibility(View.INVISIBLE);
			repaint();

		}
		else
		{
			this.totalCountView.setText(String.valueOf(CartProduct.getInstance().getOrderInfo().getOrder().getTotal_count()));

			this.totalPriceView.setText(CartProduct.getInstance().getOrderDispPrice());
			repaint();
		}
	}

	@Override
	public List<OrderDetailJson> loadListRecv(Object obj)
	{
		return null;
	}


	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.cart_submit:
			{
				Intent intent;
				if(MemoryCache.isLogined())
				{
		 
					//set default delivery information
		 
					intent = new Intent(this.getActivity(),OrderActivity.class);
				}
				else
				{
					intent = new Intent(this.getActivity(),LoginActivity.class);
					intent.putExtra(LoginActivity.JUMP_SOURCE, this.getClass());
	
					log.warn("have not login when create order");
				}
				this.startActivity(intent);
			}
				break;
			case R.id.cart_to_product:
			{
                Intent intent = new Intent(getActivity(),HomeProductActivity.class);
				
  				startActivity(intent);
			}
			break;

		default:
			break;
		}



		
	}
	private void showWindow(View parent,final OrderDetailJson orderDetail) 
	{  

		Button sure_btn =null;

        if (popupWindow == null)
        {  
            LayoutInflater layoutInflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
  
            view = layoutInflater.inflate(R.layout.pop_window_num, null);  

            TextView  title= (TextView) view.findViewById(R.id.pop_window_title);
            title.setText(orderDetail.getProduct_name());
            sure_btn = (Button) view.findViewById(R.id.pop_window_sure_btn);
            popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            // 使其聚集  
            popupWindow.setFocusable(true);  
            //点击外部消失
            popupWindow.setOutsideTouchable(true);  
            ColorDrawable dw = new ColorDrawable(0x90000000);  
            popupWindow.setBackgroundDrawable(dw);


        } 
        //设置背景变暗
        WindowManager.LayoutParams lp= getActivity().getWindow().getAttributes();
        lp.alpha = 0.4f;
        this.getActivity().getWindow().setAttributes(lp); 
        
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
				curNum = newValue+1;
				
			}
		});
		sure_btn.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				orderDetail.setQuantity(curNum);
	 
				refreshView();
				popupWindow.dismiss();
				
			}
		});

        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);//在屏幕居中，无偏移
        class  DismissListener implements OnDismissListener
        {

			@Override
			public void onDismiss()
			{
				WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
			    lp.alpha = 1f;
			    getActivity().getWindow().setAttributes(lp);
			    popupWindow=null;
			}
        	
        };
        DismissListener disListener = new DismissListener();
		//监听popwindow消失事件，并对radioGroup清零
        popupWindow.setOnDismissListener(disListener);


    } 





}
