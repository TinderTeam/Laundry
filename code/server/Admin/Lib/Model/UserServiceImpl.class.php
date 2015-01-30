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
		FuegoLog::getLog()->LogInfo('Create customer...');
		$customerDao = LaundryDaoContext::Customer();
		$customer['user_id'] = $user['user_id'];
		$customer['user_name'] = $user['user_name'];
		$customer['phone'] = $user['user_name'];
		$customer['addr'] = $customerInfo['addr'];
		$customer['company_id'] = $user['company_id'];
		FuegoLog::getLog()->LogInfo("customer info is ".json_encode($customer));
		try
		{
			$result = MispCommonService::Create($customerDao, $customer);
			FuegoLog::getLog()->LogInfo("Create customer success.");
			FuegoLog::getLog()->LogInfo("Customer register success.");
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
	    $user['password'] = "";
	    $time=30*60;
	    setcookie(session_name(),session_id(),time()+$time,"/");
	    session_start();                            // 初始化session
	    $_SESSION['user'] = $user;
	    FuegoLog::getLog()->LogInfo("User WEBLogin success, the user name is ".$user['user_name']);
	    return MispErrorCode::SUCCESS;
	}

	/* (non-PHPdoc)
	 * @see MispUserService::AppLogin()
	 */
	public function AppLogin($user)
	{
		//创建新的token
		$tokenDao = MispDaoContext::Token();
		$token['token_name'] = DataCreateUtil::GetUUID();
		$token['user_id'] = $user['user_id'];
		$result = $tokenDao->add($token);
		FuegoLog::getLog()->LogInfo("Create token success, token id is ".json_encode($result).". And token is ".$token['token_name']);
		FuegoLog::getLog()->LogInfo("Customer APPLogin success, the user name is ".$user['user_name']);
		//返回数据
		$data['user'] = $user;
		$data['token'] = $token['token_name'];
		return $data;
	}
	
	/* (non-PHPdoc)
	 * @see MispUserService::AppLogout()
	 */
	public function AppLogout($user)
	{
		FuegoLog::getLog()->LogInfo("APP logout...");
		$condition['user_id'] = $user['user_id'];
		$tokenDao = MispDaoContext::Token();
		$result = MispCommonService::Delete($tokenDao, $condition);
		FuegoLog::getLog()->LogInfo("Delete token success.");
		return MispErrorCode::SUCCESS;
	}

	/* (non-PHPdoc)
	 * @see MispUserService::ModifyPassword()
	 */
	public function ModifyPassword($condition, $req)
	{
		FuegoLog::getLog()->LogInfo("Modify password...");
		$errorCode = MispErrorCode::SUCCESS;
		$userDao= MispDaoContext::SystemUser();
    	$orginalUser = $userDao->where($condition)->find();
    	$orginalPwd = $orginalUser['password'];
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
    		FuegoLog::getLog()->LogInfo("Modify password success.");
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