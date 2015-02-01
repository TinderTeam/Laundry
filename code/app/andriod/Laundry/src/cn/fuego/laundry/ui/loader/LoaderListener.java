package cn.fuego.laundry.ui.loader;

import cn.fuego.common.log.FuegoLog;

public interface LoaderListener
{

	public void loadFinish(Object object);
	public void loadError(int error);
}
