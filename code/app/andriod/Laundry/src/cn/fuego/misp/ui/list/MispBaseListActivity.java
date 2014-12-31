package cn.fuego.misp.ui.list;

import cn.fuego.misp.ui.base.MispHttpActivtiy;
import cn.fuego.misp.ui.model.ActivityResInfo;

public abstract class MispBaseListActivity<E> extends MispHttpActivtiy
{
 
	public ActivityResInfo activityRes = new ActivityResInfo();

	public abstract void initRes();
}
