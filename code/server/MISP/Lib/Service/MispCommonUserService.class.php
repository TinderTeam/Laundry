<?php
import("MISP.Util.FuegoLog");
import("MISP.Util.FuegoException");
import("MISP.Constant.MispErrorCode");
import("MISP.Constant.UserTypeEnmu");
import("MISP.Service.MispCommonService");
import("MISP.Service.PrivilegeService");
class MispCommonUserService
{
	static function Create($user)
	{
		$errorCode = MispErrorCode::SUCCESS;
		FuegoLog::getLog()->LogInfo("create user...");
		//获取用户类型
		$roleDao = MispDaoContext::SystemRole();
		$roleCondition['role_id'] = $user['role_id'];
		$userType = $roleDao->where($roleCondition)->getField('user_type_id');
		//用户信息查重条件
		$condition['user_name'] = $user['user_name'];
		if(UserTypeEnum::CUSTOMER == $userType)		//customer 需要增加Company_id条件
		{
			$condition['company_id'] = $user['company_id'];
		}
		$systemUserDao = MispDaoContext::SystemUser();
		$userID = $systemUserDao->where($condition)->getField('user_id');
		if($userID!=''){
			//用户已存在
			FuegoLog::getLog()->LogWarn("Create user failed, user_name has exist.".json_encode($condition));
			$errorCode = MispErrorCode::USER_EXISTED;
			throw new FuegoException(null,$errorCode);
		}
		$result = MispCommonService::Create($systemUserDao, $user);
		return $result;
		
	}
	static function LoginValidate($user)
	{
		$errorCode = MispErrorCode::SUCCESS;
		FuegoLog::getLog()->LogInfo("Login validate ...");
		$condition['user_name'] = $user['user_name'];
		if("" != $user['company_id'])
		{
			$condition['company_id'] = $user['company_id'];
		}
		FuegoLog::getLog()->LogInfo("condition is ".json_encode($condition));
		$userDao= MispDaoContext::SystemUser();
		$userCount = $userDao->where($condition)->count();
		if($userCount==0)		//用户不存在
		{
			FuegoLog::getLog()->LogErr("login failed, the user is not exsit. user name is ".$req->user_name);
			$errorCode = MispErrorCode::USERNAME_OR_PASSWORD_WRONG;
			throw new FuegoException(null,$errorCode);
		}
		$orignalUser = $userDao->where($condition)->find();
		if($user['password'] != $orignalUser['password'])		//验证密码
		{
			FuegoLog::getLog()->LogErr("login failed, the password is wrong. user name is ".$req->user_name);
			$errorCode = MispErrorCode::USERNAME_OR_PASSWORD_WRONG;
			throw new FuegoException(null,$errorCode);
		}
		FuegoLog::getLog()->LogInfo("Login validate success.");
		return $orignalUser;
			
	}
	static function Modify($user)
	{
		$errorCode = MispErrorCode::SUCCESS;
		FuegoLog::getLog()->LogInfo("modify user...");
		$systemUserDao = MispDaoContext::SystemUser();
		try
		{
			$result = MispCommonService::Modify($systemUserDao, $user);
		}
		catch(FuegoException $e)
		{
			$errorCode = $e->getCode();
		}
		return $errorCode;
	}
	static function Delete($userID)
	{
		$errorCode = MispErrorCode::SUCCESS;
		FuegoLog::getLog()->LogInfo("delete user...");
		$systemUserDao = MispDaoContext::SystemUser();
		$condition['user_id'] = $userID;
		try
		{
			$result = MispCommonService::Delete($systemUserDao, $condition);
		}
		catch(FuegoException $e)
		{
			$errorCode = $e->getCode();
		}
		return $errorCode;
	}	
}

?>