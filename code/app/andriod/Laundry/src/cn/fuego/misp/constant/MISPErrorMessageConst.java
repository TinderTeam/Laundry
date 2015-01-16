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
	
	public static final int CLIENT_VERSION_LOW = 6 ; //版本过低 

	public static final int USER_EXISTED = 7;//用户已存在

	public static final int RESULT_NULL = 8;//搜索结果为空
	
	public static final int USERNAME_OR_PASSWORD_WRONG = 9; //用户名或密码错误
	public static final int ERROR_LOGIN_INVALID = 10; //登录失效
	public static final int DB_ERROR = 11;        //数据库操作失败
	public static final int DB_CREATE_ERROR = 12; //创建失败
	public static final int DB_DELETE_ERROR = 13; //删除失败
	public static final int DB_MODIFY_ERROR = 14; //修改失败
	public static final int DB_GET_ERROR = 15;   //查询失败
	public static final int ERROR_NO_IMG = 16;  //上传图片为空
 
	
	public static final int ERROR_NET_FAIL = 17 ; // 网络连接异常
	
	public static final int ERROR_INPUT_NULL = 18 ; // 输入为空
 
	public static final int ERROR_NO_RIGHT_OPERATE = 19; //无权操作
 	
	public static final int ERROR_PHONE_INVALID = 20; //无效手机号码
	public static final int ERROR_VERIFY_CODE_INVALID = 21; //验收码无效
	
	public static final int ERROR_PASSWORD_IS_EMPTY = 22;//密码不能为空
	
	public static final int ERROR_PASSWORD_NOT_SAME = 23;//确认密码不一致
	
	public static final int ERROR_UPDATE_VERSION_FAILED = 24;//更新版本失败

 	
	
	public static String getMessageByErrorCode(int errorCode)
	{
		return MispMessageReader.getInstance().getPropertyByName(String.valueOf(errorCode));
	}
	
}
