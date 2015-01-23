package cn.fuego.misp.ui.model;

import java.io.Serializable;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.constant.MispCommonIDName;

public class CommonItemMeta implements Serializable
{
	public static final int DEFAULT_CONTENT= 0 ; // 默认btn，无文字内容显示
	public static final int TEXT_CONTENT= 1 ; // btn带文字信息
	public static final int EDIT_TEXT= 2 ; // btn带文字信息
	public static final int BUTTON_TO_EDIT_ITEM= 3 ; // btn带文字信息
	public static final int IMG_CONTENT= 4 ; // btn带图片信息
	public static final int DIVIDER_ITEM= 5 ; //无内容，作为分割区域定义
	public static final int LARGE_TEXT = 6;
	public static final int SUBMIT_BUTTON = 7;

	
	public static final int ITEM_TYPE_COUNT = 8;
	
	
	 

	
	private String title;
	private int layoutType;
	private Integer titleIamge = null; //if no title image the value is empty
	private Object content;
	
	public CommonItemMeta()
	{
		
	}
	public CommonItemMeta(int type,String title,Object content)
	{
		this.layoutType = type;
		this.title = title;
		this.content = content;
	}
	public CommonItemMeta(int type,String title,Object content,Integer titleImage)
	{
		this.layoutType = type;
		this.title = title;
		this.content = content;
		this.titleIamge =titleImage;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public int getLayoutType()
	{
		return layoutType;
	}
	public void setLayoutType(int layoutType)
	{
		this.layoutType = layoutType;
	}
	public Integer getTitleIamge()
	{
		return titleIamge;
	}
	public void setTitleIamge(Integer titleIamge)
	{
		this.titleIamge = titleIamge;
	}
	public Object getContent()
	{
		return content;
	}
	public void setContent(Object content)
	{
		this.content = content;
	}
	public View getListItemView(LayoutInflater inflater)
	{
 		View view =null;
		int type= this.getLayoutType();
		String title= "";
		if(!ValidatorUtil.isEmpty(this.getTitle()))
		{
			title = this.getTitle();
		}
		String content = "";
		if(null != this.getContent())
		{
			content = String.valueOf(this.getContent());
		}
				
		
		switch(type)
		{
		case IMG_CONTENT:
			{
				view = inflater.inflate(MispCommonIDName.layout_misp_list_item_imgtype, null);
				TextView title_view = (TextView) view.findViewById(MispCommonIDName.item_imgtype_name);
				ImageView img = (ImageView) view.findViewById(MispCommonIDName.item_imgtype_img);
				title_view.setText(title);
			}
			
			break;
		case TEXT_CONTENT:
			{
				view = inflater.inflate(MispCommonIDName.layout_misp_list_item_texttype, null);
				TextView title_view = (TextView) view.findViewById(MispCommonIDName.item_texttype_name);
				TextView content_view = (TextView) view.findViewById(MispCommonIDName.item_texttype_text);
				title_view.setText(title);
				content_view.setText(content);
			}
			
			break;
		case DEFAULT_CONTENT:
			{
				view = inflater.inflate(MispCommonIDName.layout_misp_list_item_btntype, null);
				TextView title_view = (TextView) view.findViewById(MispCommonIDName.item_btntype_name);
				title_view.setText(title);
			}
			
			break;
		case BUTTON_TO_EDIT_ITEM:
			{
				view = inflater.inflate(MispCommonIDName.layout_misp_list_item_btntype, null);
				TextView title_view = (TextView) view.findViewById(MispCommonIDName.item_btntype_name);
				title_view.setText(title);
				TextView valueView  = (TextView) view.findViewById(MispCommonIDName.item_btntype_value);
				if(null != this.getContent())
				{
					valueView.setText(String.valueOf(this.getContent()));
				}
			}
			break;
		case DIVIDER_ITEM:
		   {
			view = inflater.inflate(MispCommonIDName.layout_misp_list_item_divider, null);
		   }
		   break;
		case LARGE_TEXT:
		   {
			view = inflater.inflate(MispCommonIDName.layout_misp_list_item_large_text, null);
			TextView titleView = (TextView) view.findViewById(MispCommonIDName.misp_list_item_title_text);
			titleView.setText(title);
			TextView contentView = (TextView) view.findViewById(MispCommonIDName.misp_list_item_content_text);
			contentView.setText(content);
		   }
		   break;
		case SUBMIT_BUTTON:
		   {
			view = inflater.inflate(MispCommonIDName.layout_misp_list_item_submit_btn, null);
			TextView titleView = (TextView) view.findViewById(MispCommonIDName.misp_list_item_submit_txt);
			if(!ValidatorUtil.isEmpty(title))
			{
				titleView.setText(title);
			}
 		   }
		   break;
	    default:
	    	break;
			
			
		}
		 
		return view;
	}
	
	

}
