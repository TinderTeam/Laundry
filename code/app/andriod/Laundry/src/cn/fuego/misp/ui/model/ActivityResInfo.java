package cn.fuego.misp.ui.model;

import java.util.ArrayList;
import java.util.List;


public class ActivityResInfo
{
	private String name;
	
	private int avtivityView;
	private int backBtn;
	
	private List<Integer> buttonIDList = new ArrayList<Integer>();

 

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getAvtivityView()
	{
		return avtivityView;
	}

	public void setAvtivityView(int avtivityView)
	{
		this.avtivityView = avtivityView;
	}

	public int getBackBtn()
	{
		return backBtn;
	}

	public void setBackBtn(int backBtn)
	{
		this.backBtn = backBtn;
	}

	public List<Integer> getButtonIDList()
	{
		return buttonIDList;
	}

	public void setButtonIDList(List<Integer> buttonIDList)
	{
		this.buttonIDList = buttonIDList;
	}

 

}
