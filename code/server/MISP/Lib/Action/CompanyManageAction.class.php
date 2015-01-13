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
		//删除自动转义增加的\
		$PostStr = stripslashes($_POST['data']);
		$req = json_decode($PostStr);
		if("" != $req->company_name)
		{
			$keyword = '%'.$req->company_name.'%';
			$searchFilter['company_name'] = array('like',$keyword);
		}
		$this->LoadPageTable($this->GetModel(),$searchFilter);
	}
	/* (non-PHPdoc)
	 * @see EasyUITableAction::Create()
	 */
	public function Create()
	{
		$this->LogInfo("company create ...");
		$Req = $this->GetReqObj();
		$obj = $Req->obj;
		//创建用户信息
		$companyDao = $this->GetModel();
        $condition['company_name'] = $obj->company_name;
        $companyID = $companyDao->where($condition)->getField('company_id');
        if($companyID!=''){
        	$this->LogWarn("Create company failed, company_name is exist.");
            $this->errorCode = MispErrorCode::COMPANY_EXISTED;
            $this->ReturnJson();
            return;
        }
        $object = $this->objectToArray($obj);
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
        //创建System user
        $systemUserDao = MispDaoContext::SystemUser();
        $user['user_name'] = $obj->user_name;
        $user['password'] = "888888";
        $user['role_id'] = UserRoleEnum::ADMIN;
        $user['reg_date'] = date('Y-m-d H:i:s',time());
        $user['company_id'] = $companyID;
        try
        {
        	$userID = MispCommonService::Create($systemUserDao, $user);
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
        $this->LogInfo("OOOOK");
        $this->errorCode = MispServiceContext::UserManage()->CreateAdmin($admin);
        $this->ReturnJson();
	}
}