package cn.fuego.misp.ui.model;

import java.util.ArrayList;
import java.util.List;




public class FragmentResInfo
{
	private int image;
	private int name;
	private int titleView;
	private int fragmentView;
	
	private List<Integer> buttonIDList = new ArrayList<Integer>();

	
	public List<Integer> getButtonIDList()
	{
		return buttonIDList;
	}

	public void setButtonIDList(List<Integer> buttonIDList)
	{
		this.buttonIDList = buttonIDList;
	}

	public int getImage()
	{
		return image;
	}

	public void setImage(int image)
	{
		this.image = image;
	}

	public int getName()
	{
		return name;
	}

	public void setName(int name)
	{
		this.name = name;
	}

	public int getFragmentView()
	{
		return fragmentView;
	}

	public void setFragmentView(int fragmentView)
	{
		this.fragmentView = fragmentView;
	}

	public int getTitleView()
	{
		return titleView;
	}

	public void setTitleView(int titleView)
	{
		this.titleView = titleView;
	}
 
  
 
 }
	
 