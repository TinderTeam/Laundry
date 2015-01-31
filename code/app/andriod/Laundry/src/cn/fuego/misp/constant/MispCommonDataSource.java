package cn.fuego.misp.constant;

import java.util.ArrayList;
import java.util.List;

public class MispCommonDataSource
{
	public static List<String> getSexDataSource()
	{
		List<String> sexList = new ArrayList<String>();
		sexList.add("男");
		sexList.add("女");
		return sexList;
	}

}
