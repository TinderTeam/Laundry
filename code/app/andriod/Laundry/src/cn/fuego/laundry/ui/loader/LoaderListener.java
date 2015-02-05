package cn.fuego.laundry.ui.loader;


public interface LoaderListener
{

	public void loadFinish(Object object);
	public void loadError(int error);
}
