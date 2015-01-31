/**   
* @Title: ErrorMessageConst.java 
* @Package cn.fuego.smart.home.constant 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-24 下午10:42:34 
* @version V1.0   
*/ 
package cn.fuego.laundry.constant;

import cn.fuego.misp.constant.MISPErrorMessageConst;

 /** 
 * @ClassName: ErrorMessageConst 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-24 下午10:42:34 
 *  
 */
public class ErrorMessageConst extends MISPErrorMessageConst
{

	public static final int OPREATE_DEVICE_FAiLED= 10000 ; // 操作设备失败 
	public static final int USER_APPROVAL_REFUSED = 10001;    //用户审核未通过
	public static final int CODE_IS_INVALID = 10002;          //激活码已失效
	public static final int SEND_MESSAGE_FAILED = 10003;	 //发送验证码失败
}
