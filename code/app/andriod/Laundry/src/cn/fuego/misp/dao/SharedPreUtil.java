/**   
* @Title: SharedPreUtil.java 
* @Package cn.fuego.smart.home.ui.base 
* @Description: TODO
* @author Aether
* @date 2015-1-25 下午4:03:32 
* @version V1.0   
*/ 
package cn.fuego.misp.dao;

import java.io.IOException;
import java.io.Serializable;
import java.io.StreamCorruptedException;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/** 
 * @ClassName: SharedPreUtil 
 * @Description: TODO
 * @author Aether
 * @date 2015-1-25 下午4:03:32 
 *  
 */
public class SharedPreUtil
{
 
     
    private static SharedPreUtil s_SharedPreUtil;
     
  
    private SharedPreferences msp;
     
    // 初始化，一般在应用启动之后就要初始化
    public static synchronized void initSharedPreference(Context context)
    {
        if (s_SharedPreUtil == null)
        {
            s_SharedPreUtil = new SharedPreUtil(context);
        }
    }
     
    /**
     * 获取唯一的instance
     *
     * @return
     */
    public static synchronized SharedPreUtil getInstance()
    {
        return s_SharedPreUtil;
    }
     
    public SharedPreUtil(Context context)
    {
        msp = context.getSharedPreferences("SharedPreUtil",
                Context.MODE_PRIVATE | Context.MODE_APPEND);
    }
     
    public SharedPreferences getSharedPref()
    {
        return msp;
    }
 
     
    public synchronized void put(String key,Serializable user)
    {
         
        Editor editor = msp.edit();
         
        String str="";
        try {
            str = SerializableUtil.obj2Str(user);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        editor.putString(key,str);
        editor.commit();
 
    }
     
    public synchronized Serializable get(String key)
    {
         
        
    	Serializable s_User = null;
     
             
            //获取序列化的数据
            String str = msp.getString(key, "");
             
            try {
                Object obj = SerializableUtil.str2Obj(str);
                if(obj != null){
                    s_User = (Serializable)obj;
                }
                 
            } catch (StreamCorruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
 
         
        return s_User;
    }
     
    public synchronized void delete(String key)
    {
        Editor editor = msp.edit();
        editor.putString(key,"");
     
        editor.commit();
 
    }

}
