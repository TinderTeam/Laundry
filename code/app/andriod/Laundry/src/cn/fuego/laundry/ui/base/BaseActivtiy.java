package cn.fuego.laundry.ui.base;

import java.security.MessageDigest;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import cn.fuego.misp.service.http.HttpListener;
import cn.fuego.misp.ui.base.MispHttpActivtiy;

public abstract class BaseActivtiy extends MispHttpActivtiy implements HttpListener
{

	private Context contextDialog ;

 




	public void exitDialog(Context context) { 
		contextDialog = context;
        AlertDialog.Builder builder = new Builder(contextDialog);   
        builder.setMessage("确定要退出吗?");   
        builder.setTitle("提示");   
        builder.setPositiveButton("确认",  new android.content.DialogInterface.OnClickListener() {   
            @Override   
            public void onClick(DialogInterface dialog, int which) {   
                dialog.dismiss();   
                //android.os.Process.killProcess(android.os.Process.myPid());   
                ExitApplication.getInstance().exit(contextDialog);
            }   
        });   
        builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {   
            @Override   
            public void onClick(DialogInterface dialog, int which) {   
                dialog.dismiss();   
            }   
        });   
        builder.create().show(); 
        ExitApplication.getInstance().addActivity(this);
    }
	
	// MD5加密，32位
    public  String MD5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
 
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];
 
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
 
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
    
    public String getTrimText(EditText text)
    {
		String txt =text.getText().toString().trim();
		
    	return txt;
    	
    }
    //用于scrollview 中自适应listview
    public static void setListViewHeightBasedOnChildren(ListView listView)
    {   
        // 获取ListView对应的Adapter   
    	ListAdapter listAdapter = listView.getAdapter();   
        if (listAdapter == null) {   
            return;   
        }   
   
        int totalHeight = 0;   
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {   
            // listAdapter.getCount()返回数据项的数目   
            View listItem = listAdapter.getView(i, null, listView);   
            // 计算子项View 的宽高   
            listItem.measure(0, 0);    
            // 统计所有子项的总高度   
            totalHeight += listItem.getMeasuredHeight();    
        }   
   
        ViewGroup.LayoutParams params = listView.getLayoutParams();   
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));   
        // listView.getDividerHeight()获取子项间分隔符占用的高度   
        // params.height最后得到整个ListView完整显示需要的高度   
        listView.setLayoutParams(params);   
    } 


}
