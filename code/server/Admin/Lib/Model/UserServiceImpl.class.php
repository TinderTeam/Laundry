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
		$customerResult = $customerDao->add($customer);	//$result获取到的是新创建对象的ID
		if(false == $customerResult)
		{
			FuegoLog::getLog()->LogInfo("create customer failed");
			$errorCode = MispErrorCode::DB_CREATE_ERROR;
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


	 
}

?>