package cn.fuego.laundry.ui.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.R;
import cn.fuego.laundry.ui.MainTabbarActivity;
import cn.fuego.laundry.ui.MainTabbarInfo;
import cn.fuego.laundry.ui.cart.CartProduct;
import cn.fuego.laundry.ui.cart.MyCartFragment;
import cn.fuego.laundry.webservice.up.model.GetProductListReq;
import cn.fuego.laundry.webservice.up.model.GetProductListRsp;
import cn.fuego.laundry.webservice.up.model.base.ProductJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.ui.list.ListViewResInfo;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.misp.ui.util.LoadImageUtil;

public class HomeProductActivity extends MispListActivity<ProductJson> implements  OnCheckedChangeListener, OnClickListener
{
	private FuegoLog log = FuegoLog.getLog(HomeProductActivity.class);
 
	private int selectType = 1;
	private Map<Integer,Integer> btnTypeMap = new HashMap<Integer, Integer>();
	
	private Button cartButton;

	@Override
	public void initRes()
	{
		this.activityRes.setAvtivityView(R.layout.home_goods_sel);
		this.activityRes.setBackBtn(R.id.product_back);
		
		this.listViewRes.setListType(ListViewResInfo.VIEW_TYPE_GRID);
		this.listViewRes.setListView(R.id.product_gridview);
		this.listViewRes.setListItemView(R.layout.home_goods_item);
		
		Intent intent = this.getIntent();
		selectType = intent.getIntExtra(HomeFragment.SELECT_TYPE,selectType);
 
	} 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
 

		cartButton = (Button) findViewById(R.id.product_btn_to_cart);
 		cartButton.setOnClickListener(this);
		updateCount();
	}
	
	public void updateCount()
	{
		cartButton.setText("洗衣篮（"+CartProduct.getInstance().getTotalCount()+"）");
	
	}

	@Override
	public void loadSendList()
	{
		if(CartProduct.getInstance().getProductMap().isEmpty())
		{
			GetProductListReq req = new GetProductListReq();
			WebServiceContext.getInstance().getProductManageRest(this).getAllProductList(req);
		}
		else
		{
			List<ProductJson> productList = CartProduct.getInstance().getProductMap().get(selectType);
			if(null != productList)
			{
				this.getDataList().addAll(productList );
			}
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
        ImageView imageView = (ImageView) view.findViewById(R.id.product_list_item_img);
   	 
        LoadImageUtil.getInstance().loadImage(imageView,MemoryCache.getImageUrl()+item.getImg());
        
		TextView priceView = (TextView) view.findViewById(R.id.product_list_item_curPrice);

		TextView nameView = (TextView) view.findViewById(R.id.product_list_item_name);
		nameView.setText(item.getProduct_name());
		
		priceView.setText(String.valueOf(item.getPrice()));
		ImageView check = (ImageView) view.findViewById(R.id.product_list_item_check_img);
		final int nowProductID = item.getProduct_id();
        if(CartProduct.getInstance().containsSelected(nowProductID))
        {
        	check.setImageResource(R.drawable.checkbox_on);
        }
		 
 
		return view;
	}
 	
 	
 
	@Override
	public void onItemListClick(AdapterView<?> parent, View view, long id,
			ProductJson item)
	{
		ImageView check = (ImageView) view.findViewById(R.id.product_list_item_check_img);
		// TODO Auto-generated method stub
		//super.onItemListClick(parent, view, id, item);
        if(CartProduct.getInstance().containsSelected(item.getProduct_id()))
        {
        	CartProduct.getInstance().removeSelected(item.getProduct_id());
        	check.setImageResource(R.drawable.checkbox_off);
        }  
        else
        {  
        	CartProduct.getInstance().addSelected(item.getProduct_id());
        	check.setImageResource(R.drawable.checkbox_on);
        }  
        updateCount();
	}


	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId)
	{
		int radioButtonId = group.getCheckedRadioButtonId();
		this.selectType = this.btnTypeMap.get(radioButtonId);
		refreshList(CartProduct.getInstance().getProductMap().get(this.selectType));
		
	}

	@Override
	public void onClick(View v)
	{
 
		Intent intent = new Intent(this,MainTabbarActivity.class);
		intent.putExtra(MainTabbarActivity.SELECTED_TAB, MainTabbarInfo.getIndexByClass(MyCartFragment.class));
		this.startActivity(intent);

		
	}
	


}
