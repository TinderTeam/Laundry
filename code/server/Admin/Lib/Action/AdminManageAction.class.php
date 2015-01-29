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
		$this->LogInfo("Load admin page list");
		//删除自动转义增加的\
		$PostStr = stripslashes($_POST['data']);
		$req = json_decode($PostStr);
		if("" != $req->user_name)
		{
			$keyword = '%'.$req->user_name.'%';
			$searchFilter['user_name'] = array('like',$keyword);
		}
		if(CompanyEnum::GROUP_COMPANY == $_SESSION['user']['company_id'])
		{
			//显示所有管理员
			$this->LogInfo("The company id of this user is ".$_SESSION['user']['company_id']);
		}
		else
		{
			$this->LogInfo("The company id of this user is ".$_SESSION['user']['company_id']);
			$searchFilter['company_id'] = $_SESSION['user']['company_id'];
		}
		$viewAdminDao = LaundryDaoContext::ViewAdmin();
		$this->LogInfo("SearchFilter is".json_encode($searchFilter));
		$this->LoadPageTable($viewAdminDao,$searchFilter,$this->GetModel()->getPK());
	}
	/* 新增管理员
	 * (non-PHPdoc)
	 * @see EasyUITableAction::Create()
	 */
	public function Create()
	{
		$this->LogInfo("create admin...");
        $systemUserDao = MispDaoContext::SystemUser();
        $req = $this->GetCommonData();
        if("" == $obj->role_id)
        {
        	$this->errorCode = MispErrorCode::ERROR_ROLE_IS_EMPTY;
        	$this->ReturnJson();
        	return;
        }
		//创建User信息
        $user['user_name'] = $req->user_name;
        $user['password'] = "888888";
        $user['role_id'] = $req->role_id;
        $user['reg_date'] = date('Y-m-d H:i:s',time());
        $user['company_id'] = $_SESSION['user']['company_id'];
        $this->LogInfo("Create user info ".json_encode($user));
        try
        {
        	$result = MispCommonUserService::Create($user);
        }
        catch(FuegoException $e)
        {
        	$this->errorCode = $e->getCode();
        	$this->ReturnJson();
        	return;
        }
        //创建管理员信息
        $adminDao = $this->GetModel();
        $admin['user_id'] = $result;
        $admin['user_name'] = $req->user_name;
        $admin['email'] = $req->email;
        $admin['company_id'] = $_SESSION['user']['company_id'];
        $this->LogInfo("Create admin info ".json_encode($admin));
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
	 * @see EasyUITableAction::Show()
	 */
	public function Show()
	{
    	$objID = $this->GetCommonData();
    	$this->LogInfo("show admin, user_id is ".$objID);
    	$viewAdminDao = LaundryDaoContext::ViewAdmin();
    	$condition['user_id'] = $objID;
    	try
    	{
    		$object = MispCommonService::GetUniRecord($viewAdminDao, $condition);
    	}
    	catch(FuegoException $e)
    	{
    		$this->errorCode = $e->getCode();
    		$this->ReturnJson();
    		return;
    	}
    	$data['obj'] = $object;
    	$this->ReturnJson($data);
	}
	
	/* (non-PHPdoc)
	 * @see EasyUITableAction::Modify()
	 */
	public function Modify()
	{
		$this->LogInfo("Modify admin ...");
		$admin = $this->objectToArray($this->GetCommonData());
		if("" == $admin['role_id'])
		{
			$this->errorCode = MispErrorCode::ERROR_ROLE_IS_EMPTY;
			$this->ReturnJson();
			return;
		}
		//修改用户角色
		$user['user_id'] = $admin['user_id'];
		$user['role_id'] = $admin['role_id'];
		$this->errorCode = MispCommonUserService::Modify($user);
		if(MispErrorCode::SUCCESS != $this->errorCode)
		{
			$this->ReturnJson();
			return; 
		}
		//修改管理员信息
		unset($admin['role_id']);		//删除admin中的role_id字段
		try
        {
        	$result = MispCommonService::Modify($this->GetModel(), $admin);
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
		$Req = $this->GetReqObj();
		
		$this->LogInfo("admin delete, user_id is ".$Req->obj);
		if($Req->obj == $_SESSION['user']['user_id'])
		{
			$this->LogWarn("Delete admin failed, can not delete yourself.");
			$this->errorCode = MispErrorCode::CANT_DELETE_YOURSELF;
			$this->ReturnJson();
			return;
		}
		//删除管理员信息
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
		$this->errorCode = MispCommonUserService::Delete($Req->obj);
		$this->ReturnJson();
	}

}