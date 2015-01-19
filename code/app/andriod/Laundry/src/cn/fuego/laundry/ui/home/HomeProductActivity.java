package cn.fuego.laundry.ui.home;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.common.log.FuegoLog;
import cn.fuego.laundry.R;
import cn.fuego.laundry.cache.ProductTypeCache;
import cn.fuego.laundry.ui.MainTabbarActivity;
import cn.fuego.laundry.ui.MainTabbarInfo;
import cn.fuego.laundry.ui.cart.CartProduct;
import cn.fuego.laundry.ui.cart.MyCartFragment;
import cn.fuego.laundry.webservice.up.model.GetProductListReq;
import cn.fuego.laundry.webservice.up.model.GetProductListRsp;
import cn.fuego.laundry.webservice.up.model.base.ProductJson;
import cn.fuego.laundry.webservice.up.model.base.ProductTypeJson;
import cn.fuego.laundry.webservice.up.rest.WebServiceContext;
import cn.fuego.misp.service.MemoryCache;
import cn.fuego.misp.ui.list.MispListActivity;
import cn.fuego.misp.ui.model.ListViewResInfo;
import cn.fuego.misp.ui.pop.MispPopListWindow;
import cn.fuego.misp.ui.pop.MispPopWindowListener;
import cn.fuego.misp.ui.util.LoadImageUtil;

public class HomeProductActivity extends MispListActivity<ProductJson>    
{
	private FuegoLog log = FuegoLog.getLog(HomeProductActivity.class);
 
	private ProductTypeJson selectType;
 	
 
	@Override
	public void initRes()
	{
		
		Intent intent = this.getIntent();
		selectType = (ProductTypeJson) intent.getSerializableExtra(HomeFragment.SELECT_TYPE);
		
		this.activityRes.setAvtivityView(R.layout.home_goods_sel);
		this.activityRes.setName("添加待洗物品•"+selectType.getType_name());
		this.activityRes.getButtonIDList().add(R.id.misp_title_save);
		this.activityRes.getButtonIDList().add(R.id.product_btn_to_cart);

		
		this.listViewRes.setListType(ListViewResInfo.VIEW_TYPE_GRID);
		this.listViewRes.setListView(R.id.product_gridview);
		this.listViewRes.setListItemView(R.layout.home_goods_item);

		
		 
		this.setDataList(CartProduct.getInstance().getProductMap().get(selectType.getType_id()));
 
	} 
	
 
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
 
		updateCount();
	}
	
	public void updateCount()
	{
		this.getTitleView().setText("添加待洗物品•"+selectType.getType_name());
		
		this.getButtonByID(R.id.product_btn_to_cart).setText("洗衣篮（"+CartProduct.getInstance().getOrderInfo().getOrder().getTotal_count()+"）");
	
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
		return CartProduct.getInstance().getProductMap().get(selectType.getType_id());
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
	public void onClick(View v)
	{
 
		switch(v.getId())
		{
			case R.id.misp_title_save:
			{
				setSelectType(v);
			}
			break;
			case R.id.product_btn_to_cart: 
			{
				Intent intent = new Intent(this,MainTabbarActivity.class);
				intent.putExtra(MainTabbarActivity.SELECTED_TAB, MainTabbarInfo.getIndexByClass(MyCartFragment.class));
				this.startActivity(intent);
				 

			}
			break;
		}

	}
	
	private MispPopListWindow popWin = null;
	public void setSelectType(View v)
	{
		MispPopWindowListener listener = new MispPopWindowListener()
		{

			@Override
			public void onConfirmClick(String value)
			{
 				selectType = ProductTypeCache.getInstance().getTypeByName(value);
				refreshList(CartProduct.getInstance().getProductMap().get(selectType.getType_id()));

 			}
			
		};
		
		if(null == popWin)
		{
			popWin = new MispPopListWindow(this,listener,ProductTypeCache.getInstance().getTypeNameList());

		}
		
		popWin.showWindow(v,this.selectType.getType_name());
	}
	
	


}
