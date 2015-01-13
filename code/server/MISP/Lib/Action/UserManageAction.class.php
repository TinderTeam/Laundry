<?php
// 本类由系统自动生成，仅供测试用途
class UserManageAction extends EasyUITableAction 
{
	/* (non-PHPdoc)
	 * @see EasyUITableAction::GetTableName()
	 */
	protected function GetModel()
	{
		return LaundryDaoContext::administrator();	
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
        $user['password'] = $req->password;
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
        $admin['company_id'] = $_SESSION['user']['company_id'];
        try
        {
        	$result = MispCommonService::Create($adminDao, $user);
        }
        catch(FuegoException $e)
        {
        	$this->errorCode = $e->getCode();
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