<?php
require_once './Public/PHPInclude/IncludeLaundry.php';
class UserServiceImpl implements MispUserService
{
	/* (non-PHPdoc)
	 * @see MispUserService::Register()
	 */
	public function Register($user,$customerInfo)
	{
		$errorCode = MispErrorCode::SUCCESS;
		FuegoLog::getLog()->LogInfo('register customer');
		$customerDao = LaundryDaoContext::Customer();
		$customer['user_id'] = $user['user_id'];
		$customer['phone'] = $user['user_name'];
		$customer['addr'] = $customerInfo['addr'];
		$customer['company_id'] = $user['company_id'];
		try
		{
			$result = MispCommonService::Create($customerDao, $customer);
		}
		catch(FuegoException $e)
		{
			$errorCode = $e->getCode();
		}
		return $errorCode;
	}
	/* (non-PHPdoc)
	 * @see MispUserService::WebLogin()
	 */
	public function WebLogin($user)
	{
		FuegoLog::getLog()->LogInfo("WEB login");
		if($user['role_id'] != UserRoleEnum::ADMIN)
	    {
	    	FuegoLog::getLog()->LogWarn("Web login failed");
	    	return MispErrorCode::USERNAME_OR_PASSWORD_WRONG;
	    }
	    $user['password'] = "";
	    session_start();
	    $time=30*60;
	    setcookie(session_name(),session_id(),time()+$time,"/");
	    $_SESSION['user'] = $user;
	    FuegoLog::getLog()->LogInfo("Web login success, the user name is ".$user['user_name']);
	    return MispErrorCode::SUCCESS;
	}

	/* (non-PHPdoc)
	 * @see MispUserService::AppLogin()
	 */
	public function AppLogin($user)
	{
		FuegoLog::getLog()->LogInfo("APP login");
		if($user['role_id'] != UserRoleEnum::CUSTOMER)
	    {
	    	FuegoLog::getLog()->LogWarn("APP login failed");
	    	return MispErrorCode::USERNAME_OR_PASSWORD_WRONG;
	    }
	    FuegoLog::getLog()->LogInfo("customer login success, the user name is ".$user['user_name']);
		return MispErrorCode::SUCCESS;
	}
	/* (non-PHPdoc)
	 * @see MispUserService::ModifyPassword()
	 */
	public function ModifyPassword($condition, $req)
	{
		$errorCode = MispErrorCode::SUCCESS;
		$userDao= MispDaoContext::SystemUser();
    	$orginalUser = $userDao->where($condition)->find();
    	$orginalPwd = $orginalUser['password'];
    	FuegoLog::getLog()->LogWarn("OK".$orginalPwd);
    	FuegoLog::getLog()->LogWarn("OK".$req->pwdOld);
    	if($orginalPwd != $req->pwdOld)
    	{
    		FuegoLog::getLog()->LogWarn("Modify password failed,pwdOld is wrong.");
    		$errorCode = MispErrorCode::ERROR_OLD_PASSWORD_WORD;
    		return $errorCode;
    	}
    	$orginalUser['password'] = $req->pwdNew;
    	try
    	{
    		$result = MispCommonService::Modify($userDao, $orginalUser);
    	}
    	catch(FuegoException $e)
    	{
    		$errorCode = $e->getCode();
    	}
    	return $errorCode;
		
	}
	/* (non-PHPdoc)
	 * @see MispUserService::CreatAdmin()
	 */
	public function CreateAdmin($admin)
	{
		$errorCode = MispErrorCode::SUCCESS;
		FuegoLog::getLog()->LogInfo("Create admin");
		$adminDao = LaundryDaoContext::administrators();
		try
        {
        	$result = MispCommonService::Create($adminDao, $admin);
        }
        catch(FuegoException $e)
        {
        	$errorCode = $e->getCode();
        }
        return $errorCode;
	} 
}

?>