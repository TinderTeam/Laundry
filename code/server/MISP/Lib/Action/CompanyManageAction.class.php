<?php
// 本类由系统自动生成，仅供测试用途
import("MISP.Model.MispServiceContext");
class CompanyManageAction extends EasyUITableAction 
{
	protected function GetModel()
	{
		return MispDaoContext::Company();
	}
	/* (non-PHPdoc)
	 * @see EasyUITableAction::LoadPage()
	 */
	public function LoadPage()
	{
		$this->LogInfo("Load company page list");
		//删除自动转义增加的\
		$PostStr = stripslashes($_POST['data']);
		$req = json_decode($PostStr);
		$searchFilter = null;
		if("" != $req->company_name)
		{
			$keyword = '%'.$req->company_name.'%';
			$searchFilter['company_name'] = array('like',$keyword);
		}
		$this->LogInfo("SearchFilter is ".json_encode($searchFilter));
		$this->LoadPageTable($this->GetModel(),$searchFilter,$this->GetModel()->getPk());
	}
	/* (non-PHPdoc)
	 * @see EasyUITableAction::Create()
	 */
	public function Create()
	{
		$this->LogInfo("company create ...");
		$obj = $this->GetCommonData();
		//创建公司
		$companyDao = $this->GetModel();
        $condition['company_name'] = $obj->company_name;
        $companyID = $companyDao->where($condition)->getField('company_id');
        if($companyID!=''){
        	$this->LogWarn("Create company failed, company has exist. Company_name is ".$obj->company_name);
            $this->errorCode = MispErrorCode::COMPANY_EXISTED;
            $this->ReturnJson();
            return;
        }
        $object = $this->objectToArray($obj);
        $object['company_status'] = CompanyEnum::STATUS_NORMAL; 
        try
        {
        	$companyID = MispCommonService::Create($companyDao, $object);
        }
        catch(FuegoException $e)
        {
        	$this->errorCode = $e->getCode();
        	$this->ReturnJson();
        	return;
        }
        //创建公司基本角色
        try
        {
        	$roleID = $this->CreateBasicRole($companyID);
        }
        catch(FuegoException $e)
        {
        	$this->errorCode = $e->getCode();
        	$this->ReturnJson();
        	return;
        }
        //创建System user
        $systemUserDao = MispDaoContext::SystemUser();
        $user['user_name'] = $obj->user_name;
        $user['password'] = "888888";
        $user['role_id'] = $roleID;				//增加公司时，同时增加了一个企业管理员账户
        $user['reg_date'] = date('Y-m-d H:i:s',time());
        $user['company_id'] = $companyID;
        $this->LogInfo("Create first admin of company, user info is ".json_encode($user));
        try
        {
        	$userID = MispCommonUserService::Create($user);
        }
        catch(FuegoException $e)
        {
        	$this->errorCode = $e->getCode();
        	$this->ReturnJson();
        	return;
        }
        //创建admin
        $admin['user_id'] = $userID;
        $admin['user_name'] = $obj->user_name;
        $admin['company_id'] = $companyID;
        $this->LogInfo("Create first admin of company, admin info is ".json_encode($admin));
        $this->errorCode = MispServiceContext::UserManage()->CreateAdmin($admin);
        $this->ReturnJson();
	}
	//创建公司基本角色，供创建公司函数调用
	public function CreateBasicRole($companyID)
	{
		//创建公司基本角色
		$roleDao = MispDaoContext::SystemRole();
		//创建管理员角色
		$role['role_name'] = "管理员";
		$role['user_type_id'] = UserTypeEnum::ADMIN;
		$role['user_type_name'] = "ADMIN";
		$role['company_id'] = $companyID;
		$adminRoleID = MispCommonService::Create($roleDao, $role);
		//创建管理员权限
		$privilegeDao = MispDaoContext::Privilege();
		$privilegeList = array();
		for($i=1;$i<5;$i++)
		{
			$privilege['master_type'] = PrivilegeEnum::MASTER_TYPE_ROLE;
			$privilege['master_value'] = $adminRoleID;
			$privilege['access_obj_type'] = PrivilegeEnum::ACCESS_TYPE_MENU;
			$privilege['access_obj_value'] = $i;
			array_push($privilegeList,$privilege);
		}
		$loginPrivilege['master_type'] = PrivilegeEnum::MASTER_TYPE_ROLE;
		$loginPrivilege['master_value'] = $adminRoleID;
		$loginPrivilege['access_obj_type'] = PrivilegeEnum::ACCESS_TYPE_LOGIN;
		$loginPrivilege['access_obj_value'] = PrivilegeEnum::ACCESS_VALUE_WEB_LOGIN;
		array_push($privilegeList,$loginPrivilege);
		$result = MispCommonService::CreateList($privilegeDao, $privilegeList);
		//创建消费者角色
		$role['role_name'] = "消费者";
		$role['user_type_id'] = UserTypeEnum::CUSTOMER;
		$role['user_type_name'] = "CUSTOMER";
		$role['company_id'] = $companyID;
		$sellerRoleID = MispCommonService::Create($roleDao, $role);
		//创建消费者权限
		$loginPrivilege['master_type'] = PrivilegeEnum::MASTER_TYPE_ROLE;
		$loginPrivilege['master_value'] = $sellerRoleID;
		$loginPrivilege['access_obj_type'] = PrivilegeEnum::ACCESS_TYPE_LOGIN;
		$loginPrivilege['access_obj_value'] = PrivilegeEnum::ACCESS_VALUE_APP_LOGIN;
		$result = MispCommonService::Create($privilegeDao, $loginPrivilege);
		
		return $adminRoleID;
	}
	//更新公司状态
	public function UpdateStatus($status)
	{
		$companyIDList = $this->GetCommonData();
		$this->LogInfo("Update company status, company id list is ".$companyIDList);
		if("freeze" == $status)
		{
			$this->LogInfo("Company freeze..");
			$company_status = CompanyEnum::STATUS_FREEZE;
		}
		if("unfreeze" == $status)
		{
			$this->LogInfo("Company unfreeze..");
			$company_status = CompanyEnum::STATUS_NORMAL;
		}
		$companyDao = $this->GetModel();
		try
		{
			$result = MispCommonService::ModifyField($companyDao, $companyIDList, 'company_status', $company_status);
		}
		catch(FuegoException $e)
		{
			$this->errorCode = $e->getCode();
		}
		$this->ReturnJson();
	}
}