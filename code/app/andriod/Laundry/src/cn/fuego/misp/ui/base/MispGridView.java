/**   
* @Title: MyGridview.java 
* @Package cn.fuego.eshoping.ui.home 
* @Description: TODO
* @author Aether
* @date 2014-12-18 下午7:22:14 
* @version V1.0   
*/ 
package cn.fuego.misp.ui.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/** 
 * @ClassName: MyGridview 
 * @Description: TODO
 * @author Aether
 * @date 2014-12-18 下午7:22:14 
 *  
 */
public class MispGridView extends GridView { 

    public MispGridView(Context context, AttributeSet attrs) { 
        super(context, attrs); 
    } 

    public MispGridView(Context context) { 
        super(context); 
    } 

    public MispGridView(Context context, AttributeSet attrs, int defStyle) { 
        super(context, attrs, defStyle); 
    } 

    @Override 
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { 

        int expandSpec = MeasureSpec.makeMeasureSpec( 
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST); 
        super.onMeasure(widthMeasureSpec, expandSpec); 
    } 
} 