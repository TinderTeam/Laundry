<?php
// 本类由系统自动生成，仅供测试用途
require_once './Public/PHPInclude/IncludeLaundry.php';
class CustomerManageAction extends EasyUITableAction 
{
	/* (non-PHPdoc)
	 * @see EasyUITableAction::GetTableName()
	 */
	protected function GetModel()
	{
		return LaundryDaoContext::Customer();
	}
	/* (non-PHPdoc)
	 * @see EasyUITableAction::LoadPage()
	 */
	public function LoadPage()
	{
		//删除自动转义增加的\
		$PostStr = stripslashes($_POST['data']);
		$req = json_decode($PostStr);
		if("" != $req->user_id)
		{
			$keyID = '%'.$req->user_id.'%';
			$searchFilter['user_id'] = array('like',$keyID);
		}
		if("" != $req->user_name)
		{
			$keyUser = '%'.$req->user_name.'%';
			$searchFilter['user_name'] = array('like',$keyUser);
		}
		if("" != $req->customer_name)
		{
			$keyName = '%'.$req->customer_name.'%';
			$searchFilter['customer_name'] = array('like',$keyName);
		}
		if(0 != $_SESSION['user']['company_id'])
		{
			$searchFilter['company_id'] = $_SESSION['user']['company_id'];
		}
		$this->LoadPageTable($this->GetModel(),$searchFilter);
	}
	//会员新增
	/* (non-PHPdoc)
	 * @see EasyUITableAction::Create()
	 */
	public function Create()
	{
		$this->LogInfo("customer create ...");
		$Req = $this->GetReqObj();
		$obj = $Req->obj;
		//创建用户信息
		$systemUserDao = MispDaoContext::SystemUser();
        $condition['user_name'] = $obj->user_name;
        $condition['company_id'] = $_SESSION['user']['company_id'];
        $userID = $systemUserDao->where($condition)->getField('user_id');
        if($userID!=''){
        	$this->LogWarn("Create customer failed, user_name is exist.");
            $this->errorCode = MispErrorCode::USER_EXISTED;
            $this->ReturnJson();
            return;
        }
        $user['user_name'] = $obj->user_name;
        $user['password'] = '888888';
        $user['role_id'] = UserRoleEnum::CUSTOMER;
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
		//创建会员信息
    	$customerDao = $this->GetModel();
        $customer['user_id'] = $result;
        $customer['user_name'] = $obj->user_name;
        $customer['customer_name'] = $obj->customer_name;
        $customer['nickname'] = $obj->nickname;
        $customer['phone'] = $obj->user_name;
        $customer['birthday'] = $obj->birthday;
        $customer['addr'] = $obj->addr;
        $customer['score'] = 0;
        $customer['company_id'] = $_SESSION['user']['company_id'];
        try
        {
        	$result = MispCommonService::Create($customerDao, $customer);
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
		$this->LogInfo("customer delete ...");
		$Req = $this->GetReqObj();
		$objID = $Req->obj;
		//删除会员信息
		$customerDao = $this->GetModel();
		try
		{
			$result = MispCommonService::Delete($customerDao, $objID);
		}
		catch(FuegoException $e)
		{
			$this->errorCode = $e->getCode();
			$this->ReturnJson();
			return;
		}
		//删除用户信息
        $userDao = MispDaoContext::SystemUser();
        try
        {
        	$result = MispCommonService::Delete($userDao, $objID);
        }
        catch(FuegoException $e)
        {
        	$this->errorCode = $e->getCode();
        }
        $this->ReturnJson();
	}

}