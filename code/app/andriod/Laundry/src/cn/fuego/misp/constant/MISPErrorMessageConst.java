/**   
* @Title: ErrorMessageConst.java 
* @Package cn.fuego.smart.home.constant 
* @Description: TODO
* @author Tang Jun   
* @date 2014-10-24 下午10:42:34 
* @version V1.0   
*/ 
package cn.fuego.misp.constant;

import cn.fuego.misp.dao.file.MispMessageReader;

 /** 
 * @ClassName: ErrorMessageConst 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-10-24 下午10:42:34 
 *  
 */
public class MISPErrorMessageConst
{
	public static final int SUCCESS = 0;  //成功
 
	public static final int ERROR_MSG_WRONG = 1; //消息错误
	
	public static final int OPERATE_FAILED = 2 ; // 

	public static final int ERROR_USER_NOT_EXISTED = 3; //用户不存在
	public static final int ERROR_LOGIN_FAILED = 4;  //登录失败
	public static final int ERROR_OLD_PASSWORD_WORD = 5; //原始密码错误
	
	public static final int CLIENT_VERSION_LOW = 6 ; // 

	public static final int USER_EXISTED = 7;//用户已存在

	public static final int RESULT_NULL = 8;//搜索结果为空
	
	
	public static final int NET_FAIL = 9 ; // 网络连接异常
	
	public static final int INPUT_NULL = 10 ; // 输入为空
	
	public static final int TARGET_NOT_EXISTED = 11; // 操作对象已经不存在
	public static final int ADMIN_NOT_DELETED = 12; //超级管理员不能被删除
	public static final int OPERATE_PROHIBITED = 13; //无权操作
	
	public static final int ERROR_QUREY_FAILED = 14;
	public static String getMessageByErrorCode(int errorCode)
	{
		return MispMessageReader.getInstance().getPropertyByName(String.valueOf(errorCode));
	}
	
}
