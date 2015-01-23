/**   
* @Title: GridViewAdapter.java 
* @Package cn.fuego.eshoping.ui.home 
* @Description: TODO
* @author Aether
* @date 2014-12-22 下午2:30:46 
* @version V1.0   
*/ 
package cn.fuego.misp.ui.grid;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.fuego.misp.constant.MispCommonIDName;
import cn.fuego.misp.ui.model.MispGridDataModel;

/** 
 * @ClassName: GridViewAdapter 
 * @Description: TODO
 * @author Aether
 * @date 2014-12-22 下午2:30:46 
 *  
 */
public class MispGridViewAdapter extends BaseAdapter
{

	private Context mContext;
	private List<MispGridDataModel> mList;

	public MispGridViewAdapter(Context mContext,List<MispGridDataModel> mList)
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
		MispGridDataModel gridData = mList.get(position);
 
		if (convertView == null)
		{

			convertView = LayoutInflater.from(this.mContext).inflate(MispCommonIDName.layout_misp_grid_item, null, false);
			//holder.button = (Button) convertView.findViewById(R.id.gridview_item_button);
			TextView title_view = (TextView) convertView.findViewById(MispCommonIDName.misp_grid_item_title);
			ImageView img = (ImageView) convertView.findViewById(MispCommonIDName.misp_grid_item_img);
			title_view.setText(gridData.getName());
			//String title=mylist.get(position).get("data");
			img.setImageResource(gridData.getImage());

		} 


		return convertView;
	}

}
