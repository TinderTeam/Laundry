<?php
// 本类由系统自动生成，仅供测试用途
class UserManageAction extends EasyUITableAction 
{
	/* (non-PHPdoc)
	 * @see EasyUITableAction::GetTableName()
	 */
	protected function GetModel()
	{
		return MispDaoContext::SystemUser();	
	}
	
	/* (non-PHPdoc)
	 * @see EasyUITableAction::LoadPage()
	 */
	public function LoadPage()
	{
		//删除自动转义增加的\
		$PostStr = stripslashes($_POST['data']);
		$req = json_decode($PostStr);
		$searchFilter = null;
		if("" != $req->user_name)
		{
			$keyword = '%'.$req->user_name.'%';
			$searchFilter['user_name'] = array('like',$keyword);
		}
		$condition['company_id'] = CompanyEnum::GROUP_COMPANY;
		$condition['user_type_id'] = UserTypeEnum::SUPER_ADMIN;
		$roleList = MispCommonDataService::GetRoleID($condition);
		$searchFilter['role_id']=array('in',$roleList);
		$this->LogInfo("LoadPage, searchFilter is ".json_encode($searchFilter));
		$this->LoadPageTable($this->GetModel(),$searchFilter,$this->GetModel()->getPk());
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
		
        $user['user_name'] = $req->user_name;
        $user['password'] = $req->password;
        $user['role_id'] = $req->role_id;
        $user['reg_date'] = date('Y-m-d H:i:s',time());
        $user['company_id'] = CompanyEnum::GROUP_COMPANY;
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
        $this->ReturnJson();
	}

	/**
	 * 发送注册验证码
	 */
	public function SendVerifyCode(){
		$this->LogInfo("Send verify code...");
		$obj = $this->GetReqObj();
		//判断公司是否冻结
		$companyCondition['company_id'] = $obj->app_id;
		$companyDao = MispDaoContext::Company();
		$companyStatus = $companyDao->where($companyCondition)->getField('company_status');
		if(CompanyEnum::STATUS_FREEZE == $companyStatus)
		{
			$this->LogWarn("Send verify code failed. The company is freezed, company id is ".$obj->app_id);
			$this->errorCode = MispErrorCode::COMPANY_SERVICE_STOP;
			$this->ReturnJson();
			return;
		}
		//发送验证码
		$phoneNum = $obj->phone_num;
		$content = ShortMessage::getRandNum(4);
		$message = $content."【快客洗涤】";
		$result = ShortMessage::SendMessage($phoneNum,$message);
		$xmlObj = simplexml_load_string($result);
		if($xmlObj->RetCode == "Sucess")
		{
			$this->LogInfo("Send verify code success, verify code is ".$content);
			$data['obj'] = $content;
			$this->ReturnJson($data);
			return;
		}
		else
		{
			$this->LogInfo("Send verify code failed.");
			$this->errorCode = SEND_MESSAGE_FAILED;
			$this->ReturnJson();
			return;
		}
	}
}
?>