/**   
* @Title: GroupAdapter.java 
* @Package cn.fuego.eshoping.ui.home 
* @Description: TODO
* @author Aether
* @date 2014-12-23 下午9:22:17 
* @version V1.0   
*/ 
package cn.fuego.laundry.ui.home;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.fuego.laundry.R;


/** 
 * @ClassName: GroupAdapter 
 * @Description: TODO
 * @author Aether
 * @date 2014-12-23 下午9:22:17 
 *  
 */
public class GroupAdapter extends BaseAdapter
{
	private Context context;

	private List<String> list;
	
	 private int selectedPosition = -1;// 选中的位置  

	public GroupAdapter(Context context, List<String> list)
	{

		this.context = context;
		this.list = list;

	}

	@Override
	public int getCount()
	{
		return list.size();
	}

	@Override
	public Object getItem(int position)
	{

		return list.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	public void setSelectedPosition(int position) {  
        this.selectedPosition = position;  
    }  
	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup)
	{
        View view ;
		ViewHolder holder;
		if (convertView == null)
		{
			convertView = LayoutInflater.from(context).inflate(
					R.layout.search_condition_item, null);
			holder = new ViewHolder();
			
			view= convertView;
			
			convertView.setTag(holder);

			holder.groupItem = (TextView) convertView
					.findViewById(R.id.search_item_text);

		} else
		{
			holder = (ViewHolder) convertView.getTag();
			view = convertView;
		}
		
		if(selectedPosition == position)
		{
			view.setBackgroundColor(Color.rgb(231,230, 227));
			holder.groupItem.setSelected(true);
		}
		else
		{
			view.setBackgroundColor(Color.TRANSPARENT);
			holder.groupItem.setSelected(false);
			
		}
		//holder.groupItem.setTextColor(Color.BLACK);
		holder.groupItem.setText(list.get(position));

		view =  LayoutInflater.from(context).inflate(
				R.layout.search_condition_item, null);
		return view;
	}

	static class ViewHolder
	{
		TextView groupItem;
	} 
}
