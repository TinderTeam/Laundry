<?php

class MispErrorCode
{
	const SUCCESS = 0;                   	//操作成功
	const ERROR_MSG_WRONG =1;            	//消息错误
	const OPERATE_FAILED =2;             	//操作失败
	const ERROR_USER_NOT_EXISTED =3;     	//用户不存在
	const ERROR_LOGIN_FAILED =4;         	//登录失败
	const ERROR_OLD_PASSWORD_WORD = 5;    	//原密码错误
	const CLIENT_VERSION_LOW = 6;         	//用户等级过低
	const USER_EXISTED = 7;               	//用户已存在
	const RESULT_NULL = 8;                	//搜索结果为空
	const USERNAME_OR_PASSWORD_WRONG = 9; 	//用户名或密码错误
	const ERROR_TOKEN_INVALID = 10; 		//token 失效
	const DB_ERROR = 11; 					//数据库操作失败
	const DB_CREATE_ERROR = 12; 			//数据库创建失败
	const DB_DELETE_ERROR = 13; 			//数据库删除失败
	const DB_MODIFY_ERROR = 14; 			//数据库更新失败
	const DB_GET_ERROR = 15; 				//数据库获取失败
	const NO_IMG = 16;						//上传图片为空
	const ERROR_SESSION_INVALID = 17;		//session失效

	const USER_UNCHECKED = 10000;           //用户未审核
	const USER_APPROVAL_REFUSED = 10001;    //用户审核未通过
	const CODE_IS_INVALID = 10002;          //激活码已失效
	const SEND_MESSAGE_FAILED = 10003;		//发送验证码失败
	
}

?>