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
		$obj = $this->GetReqObj();
		$phoneNum = $obj->phone_num;
		$content = ShortMessage::getRandNum(4);
		$message = $content."【快客洗涤】";
		$result = ShortMessage::SendMessage($phoneNum,$message);
		$this->LogInfo("OK".$result);
		$xmlObj = simplexml_load_string($result);
		$this->LogInfo($xmlObj->RetCode);
		if($xmlObj->RetCode == "Sucess")
		{
			$data['obj'] = $content;
			$this->ReturnJson($data);
			return;
		}
		else
		{
			$this->errorCode = SEND_MESSAGE_FAILED;
			$this->ReturnJson();
			return;
		}
	}
}
?>