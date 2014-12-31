package cn.fuego.misp.ui.list;

public class ListViewResInfo
{
	public static final String SELECT_ITEM = "SELECT_ITEM";

	private int listView;
	private int listItemView;
	private Class clickActivityClass;
	public int getListView()
	{
		return listView;
	}
	public void setListView(int listView)
	{
		this.listView = listView;
	}
	public int getListItemView()
	{
		return listItemView;
	}
	public void setListItemView(int listItemView)
	{
		this.listItemView = listItemView;
	}
	public Class getClickActivityClass()
	{
		return clickActivityClass;
	}
	public void setClickActivityClass(Class clickActivityClass)
	{
		this.clickActivityClass = clickActivityClass;
	}
	
	
}
