/**   
* @Title: GridViewAdapter.java 
* @Package cn.fuego.eshoping.ui.home 
* @Description: TODO
* @author Aether
* @date 2014-12-22 下午2:30:46 
* @version V1.0   
*/ 
package cn.fuego.laundry.ui.home;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.laundry.R;

/** 
 * @ClassName: GridViewAdapter 
 * @Description: TODO
 * @author Aether
 * @date 2014-12-22 下午2:30:46 
 *  
 */
public class GridViewAdapter extends BaseAdapter
{

	private Context mContext;
	private List<Map<String, Object>> mList;

	public GridViewAdapter(Context mContext,List<Map<String, Object>> mList)
	{
		super();
		this.mContext = mContext;
		this.mList = mList;
	}

	@Override
	public int getCount()
	{
		if (mList == null)
		{
			return 0;
		} else
		{
			return this.mList.size();
		}
	}

	@Override
	public Object getItem(int position)
	{
		if (mList == null)
		{
			return null;
		} else
		{
			return this.mList.get(position);
		}
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		String title= mList.get(position).get("ItemText").toString();
		String imgID= mList.get(position).get("ItemImage").toString();
		if (convertView == null)
		{

			convertView = LayoutInflater.from(this.mContext).inflate(R.layout.home_grid_item, null, false);
			//holder.button = (Button) convertView.findViewById(R.id.gridview_item_button);
			TextView title_view = (TextView) convertView.findViewById(R.id.grid_item_title);
			ImageView img = (ImageView) convertView.findViewById(R.id.grid_item_img);
			title_view.setText(title);
			//String title=mylist.get(position).get("data");
			img.setImageResource(Integer.valueOf(imgID));

		} 


		return convertView;
	}

}
