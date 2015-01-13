<?php
// 本类由系统自动生成，仅供测试用途
require_once './Public/PHPInclude/IncludeLaundry.php';
class AdminManageAction extends EasyUITableAction 
{
	/* (non-PHPdoc)
	 * @see EasyUITableAction::GetTableName()
	 */
	protected function GetModel()
	{
		return LaundryDaoContext::administrators();	
	}
	
	/* (non-PHPdoc)
	 * @see EasyUITableAction::LoadPage()
	 */
	public function LoadPage()
	{
		//删除自动转义增加的\
		$PostStr = stripslashes($_POST['data']);
		$req = json_decode($PostStr);
		if("" != $req->user_name)
		{
			$keyword = '%'.$req->user_name.'%';
			$searchFilter['user_name'] = array('like',$keyword);
		}
		//$searchFilter['role_id'] = UserRoleEnum::ADMIN;
		if(0 != $_SESSION['user']['company_id'])
		{
			$searchFilter['company_id'] = $_SESSION['user']['company_id'];
		}
		$this->LoadPageTable($this->GetModel(),$searchFilter);
	}
	/* 新增管理员
	 * (non-PHPdoc)
	 * @see EasyUITableAction::Create()
	 */
	public function Create()
	{
		$this->LogInfo("create user...");
        $systemUserDao = MispDaoContext::SystemUser();
        $req = $this->GetCommonData();

        $condition['user_name'] = $req->user_name;
        $userID = $systemUserDao->where($condition)->getField('user_id');
        if($userID!=''){
        	$this->LogWarn("Create user failed, user_name is exist.");
        	$this->errorCode = MispErrorCode::USER_EXISTED;
        	$this->ReturnJson();
        	return;
        }
        $user['user_name'] = $req->user_name;
        $user['password'] = "888888";
        $user['role_id'] = UserRoleEnum::ADMIN;
        $user['reg_date'] = date('Y-m-d H:i:s',time());
        $user['company_id'] = $_SESSION['user']['company_id'];
        try
        {
        	$result = MispCommonService::Create($systemUserDao, $user);
        }
        catch(FuegoException $e)
        {
        	$this->errorCode = $e->getCode();
        	$this->ReturnJson();
        	return;
        }
        $adminDao = $this->GetModel();
        $admin['user_id'] = $result;
        $admin['user_name'] = $req->user_name;
        $admin['email'] = $req->email;
        $admin['company_id'] = $_SESSION['user']['company_id'];
        try
        {
        	$result = MispCommonService::Create($adminDao, $admin);
        }
        catch(FuegoException $e)
        {
        	$this->errorCode = $e->getCode();
        }
        $this->ReturnJson();
	}
	/* (non-PHPdoc)
	 * @see EasyUITableAction::Delete()
	 */
	public function Delete()
	{
		$this->LogInfo("admin delete ...");
		$Req = $this->GetReqObj();
		if($Req->obj == $_SESSION['user']['user_id'])
		{
			$this->LogWarn("Delete admin failed, can not delete yourself.");
			$this->errorCode = MispErrorCode::CANT_DELETE_YOURSELF;
			$this->ReturnJson();
			return;
		}
		//删除会员信息
		$adminDao = $this->GetModel();
		$adminCondition[$adminDao->getPk()] = $Req->obj;
		try
		{
			$result = MispCommonService::Delete($adminDao, $adminCondition);
		}
		catch(FuegoException $e)
		{
			$this->errorCode = $e->getCode();
			$this->ReturnJson();
			return;
		}
		//删除用户信息
		$userDao = MispDaoContext::SystemUser();
		$userCondition[$userDao->getPk()] = $Req->obj;
		try
		{
			$result = MispCommonService::Delete($userDao, $userCondition);
		}
		catch(FuegoException $e)
		{
			$this->errorCode = $e->getCode();
		}
		$this->ReturnJson();
	}

}