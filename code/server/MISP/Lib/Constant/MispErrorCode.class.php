<?php

class MispErrorCode
{
	const SUCCESS = 0;                   	//操作成功
	const ERROR_MSG_WRONG =1;            	//消息错误
	const OPERATE_FAILED =2;             	//操作失败
	const ERROR_USER_NOT_EXISTED =3;     	//用户不存在
	const ERROR_LOGIN_FAILED =4;         	//登录失败
	const ERROR_OLD_PASSWORD_WORD = 5;    	//原密码错误
	const CLIENT_VERSION_LOW = 6;         	//版本过低
	const USER_EXISTED = 7;               	//用户已存在
	const RESULT_NULL = 8;                	//搜索结果为空
	const USERNAME_OR_PASSWORD_WRONG = 9; 	//用户名或密码错误
	const ERROR_LOGIN_INVALID = 10; 		//用户登录失效
	const DB_ERROR = 11; 					//数据库操作失败
	const DB_CREATE_ERROR = 12; 			//数据库创建失败
	const DB_DELETE_ERROR = 13; 			//数据库删除失败
	const DB_MODIFY_ERROR = 14; 			//数据库更新失败
	const DB_GET_ERROR = 15; 				//数据库查询失败
	const UPLOAD_IMG_FAILED = 16;			//上传图片失败
	const ERROR_NET_FAIL = 17 ; 			// 网络连接异常
	const ERROR_INPUT_NULL = 18 ; 			// 输入为空
	const ERROR_NO_RIGHT_OPERATE = 19;		//无权操作
	const ERROR_PHONE_INVALID = 20; 		//无效手机号码
	const ERROR_VERIFY_CODE_INVALID = 21; 	//验收码无效
	const ERROR_PASSWORD_IS_EMPTY = 22;		//密码不能为空
	const ERROR_PASSWORD_NOT_SAME = 23;		//确认密码不一致
	const ERROR_UPDATE_VERSION_FAILED = 24;	//更新版本失败 
	const PRIVILEGE_NOT_EXIST = 25;			//权限不存在
	const ERROR_ROLE_IS_EMPTY = 26;			//角色不能为空

	const USER_UNCHECKED = 10000;           //用户未审核
	const USER_APPROVAL_REFUSED = 10001;    //用户审核未通过
	const CODE_IS_INVALID = 10002;          //激活码已失效
	const SEND_MESSAGE_FAILED = 10003;		//发送验证码失败
	const COMPANY_EXISTED = 10004;			//公司已存在
	const CANT_DELETE_YOURSELF = 10005;		//不能删除自己
	const COMPANY_SERVICE_STOP = 10006;		//公司服务暂停
	
}

?>