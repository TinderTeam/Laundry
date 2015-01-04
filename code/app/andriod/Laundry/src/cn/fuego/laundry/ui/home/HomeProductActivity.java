package cn.fuego.laundry.ui.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import cn.fuego.laundry.R;
import cn.fuego.laundry.ui.cart.CartProduct;
import cn.fuego.laundry.webservice.up.model.GetProductListReq;
import cn.fuego.laundry.webservice.up.model.GetProductListRsp;
import cn.fuego.laundry.webservice.up.model.base.ProductJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.ui.list.MispListActivity;

public class HomeProductActivity extends MispListActivity<ProductJson> implements  OnCheckedChangeListener
{
 
	private int selectType = 1;
	private Map<Integer,Integer> btnTypeMap = new HashMap<Integer, Integer>();
	
	private Button cartButton;

	@Override
	public void initRes()
	{
		this.activityRes.setAvtivityView(R.layout.home_goods_sel);
		this.activityRes.setBackBtn(R.id.product_back);
		this.listViewRes.setListView(R.id.product_list);
		this.listViewRes.setListItemView(R.layout.home_goods_item);
		
		Intent intent = this.getIntent();
		selectType = intent.getIntExtra(HomeFragment.SELECT_TYPE,selectType);
		
		btnTypeMap.put(R.id.product_radio1, 1);
		btnTypeMap.put(R.id.product_radio2, 2);
		btnTypeMap.put(R.id.product_radio3, 3);
		btnTypeMap.put(R.id.product_radio4, 4);
		btnTypeMap.put(R.id.product_radio5, 5);
		btnTypeMap.put(R.id.product_radio6, 6);
		btnTypeMap.put(R.id.product_radio7, 7);
		
		ProductJson json = new ProductJson();
		json.setProduct_name("上衣");
		json.setPrice((float)1.1);
		dataList.clear();
		dataList.add(json);
		
		ProductJson json1 = new ProductJson();
		json1.setProduct_id(1);
		json.setProduct_name("上衣");
		json.setPrice((float)1.1);
		dataList.add(json1);
		
		ProductJson json2 = new ProductJson();
		json2.setProduct_id(2);
		json.setProduct_name("上衣");
		json.setPrice((float)1.1);
		dataList.add(json2);
		
		ProductJson json3 = new ProductJson();
		json3.setProduct_id(4);
		json.setProduct_name("上衣");
		json.setPrice((float)1.1);
		dataList.add(json3);

		
	} 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		RadioGroup radioGroup =  (RadioGroup) findViewById(R.id.product_radio_group);
		radioGroup.setOnCheckedChangeListener(this);
		cartButton = (Button) findViewById(R.id.product_btn_to_cart);

		updateCount();
	}
	
	public void updateCount()
	{
		cartButton.setText("洗衣篮（"+CartProduct.getInstance().getSelectProductList().size()+")");
	
	}


	@Override
	public void loadSendList()
	{
		if(CartProduct.getInstance().getProductMap().isEmpty())
		{
			GetProductListReq req = new GetProductListReq();
			WebServiceContext.getInstance().getProductManageRest(this).getAllProductList(req);
		}
		
	}

	@Override
	public List<ProductJson> loadListRecv(Object obj)
	{
		GetProductListRsp rsp = (GetProductListRsp) obj;
		CartProduct.getInstance().refreshProduct(rsp.getObj());
		return CartProduct.getInstance().getProductMap().get(selectType);
	}
	
 	@Override
	public View getListItemView(View view, ProductJson item)
	{
		TextView nameView = (TextView) view.findViewById(R.id.product_list_item_name);
		nameView.setText(item.getProduct_name());
		
		TextView priceView = (TextView) view.findViewById(R.id.product_list_item_curPrice);
		priceView.setText(String.valueOf(item.getPrice()));
		final CheckBox check = (CheckBox) view.findViewById(R.id.product_list_item_check_btn);
		final int nowProductID = item.getProduct_id();
		check.setOnClickListener(new OnClickListener()
		{     
            @Override  
            public void onClick(View v) 
            {  
                if(CartProduct.getInstance().getSelectProductList().contains(new Integer(nowProductID)))
                {
                	CartProduct.getInstance().getSelectProductList().remove(new Integer(nowProductID));
                    check.setChecked(false);  
                }  
                else
                {  
                	CartProduct.getInstance().getSelectProductList().add(nowProductID);
                    check.setChecked(true);  
                }  
                updateCount();
            }     
        });   
 
		return view;
	}
 
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId)
	{
		int radioButtonId = group.getCheckedRadioButtonId();
		this.selectType = this.btnTypeMap.get(radioButtonId);
		refreshList(CartProduct.getInstance().getProductMap().get(this.selectType));
		
	}

 
	


}
