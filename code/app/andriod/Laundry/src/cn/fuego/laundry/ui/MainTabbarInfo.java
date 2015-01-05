package cn.fuego.laundry.ui;

import cn.fuego.laundry.ui.cart.MyCartFragment;
import cn.fuego.laundry.ui.home.HomeFragment;
import cn.fuego.laundry.ui.more.MoreFragment;
import cn.fuego.laundry.ui.user.UserFragment;

public class MainTabbarInfo
{
    private static Class fragments[] = {HomeFragment.class,MyCartFragment.class,UserFragment.class,MoreFragment.class};  
    
    public static int getIndexByClass(Class clazz)
    {
    	for(int i=0;i<fragments.length;i++)
    	{
    		if(clazz == fragments[i])
    		{
    			return i;
    		}
    	}
    	return 0;
    }

	public static Class[] getFragments()
	{
		return fragments;
	}
    
    
    
}
