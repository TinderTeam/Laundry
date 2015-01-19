package cn.fuego.misp.ui.model;

import java.io.Serializable;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.laundry.R;

public class CommonEditItemMeta implements Serializable
{
	public static final int TEXT_EDIT= 0 ; // 默认btn，无文字内容显示
	public static final int LARGE_TEXT_ITEM= 1 ; // btn带文字信息
	public static final int SELECT_ITEM= 2 ; // btn带文字信息
	public static final int DATE_ITEM= 3 ; // btn带文字信息
 	public static final int DIVIDER_ITEM= 4 ; //无内容，作为分割区域定义
 	public static final int SUBMIT_BUTTON = 5;

	
	public static final int ITEM_TYPE_COUNT = 6;
	
 
	private String title;
	private String value;
	private int layoutType;
 	private Object content;
	

	

}
