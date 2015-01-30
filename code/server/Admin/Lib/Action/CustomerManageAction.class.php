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
		$this->LogInfo("Load customer page list");
		//删除自动转义增加的\
		$PostStr = stripslashes($_POST['data']);
		$req = json_decode($PostStr);
		$searchFilter = null;
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
		if(CompanyEnum::GROUP_COMPANY == $_SESSION['user']['company_id'])
		{
			//显示所有会员
			$this->LogInfo("The company id of this user is ".$_SESSION['user']['company_id']);
		}
		else 
		{
			$this->LogInfo("The company id of this user is ".$_SESSION['user']['company_id']);
			$searchFilter['company_id'] = $_SESSION['user']['company_id'];
		}
		$this->LogInfo("SearchFilter is".json_encode($searchFilter));
		$this->LoadPageTable($this->GetModel(),$searchFilter,$this->GetModel()->getPk());
	}
	//会员新增
	/* (non-PHPdoc)
	 * @see EasyUITableAction::Create()
	 */
	public function Create()
	{
		$this->LogInfo("customer create ...");
		$obj = $this->GetCommonData();
		if("" == $obj->role_id)
		{
			$this->errorCode = MispErrorCode::ERROR_ROLE_IS_EMPTY;
			$this->ReturnJson();
			return; 
		}
		//创建用户
		$user['user_name'] = $obj->user_name;
		$user['password'] = '888888';
		$user['role_id'] = $obj->role_id;
		$user['reg_date'] = date('Y-m-d H:i:s',time());
		$user['company_id'] = $_SESSION['user']['company_id'];
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
		//创建会员
    	$customerDao = $this->GetModel();
        $customer['user_id'] = $result;
        $customer['user_name'] = $obj->user_name;
        $customer['customer_name'] = $obj->customer_name;
        $customer['nickname'] = $obj->nickname;
        $customer['phone'] = $obj->phone;
        $customer['birthday'] = $obj->birthday;
        $customer['addr'] = $obj->addr;
        $customer['score'] = $obj->score;
        $customer['card_number'] = $obj->card_number;
        $customer['customer_sex'] = $obj->customer_sex;
        $customer['customer_email'] = $obj->customer_email;
        $customer['company_id'] = $_SESSION['user']['company_id'];
        $this->LogInfo("Create customer ".json_encode($customer));
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
	 * @see EasyUITableAction::Show()
	 */
	public function Show()
	{
		$reqType = $this->GetReqType();
		if(($reqType == ClientTypeEnum::IOS)||($reqType == ClientTypeEnum::ANDROID))
		{
			//验证APP是否登录
			$this->DoAuth();
		}
		$objID = $this->GetCommonData();
    	$this->LogInfo("show customer, user_id is ".$objID);
    	$viewCustomerDao = LaundryDaoContext::ViewCustomer();
    	$condition['user_id'] = $objID;
    	try
    	{
    		$object = MispCommonService::GetUniRecord($viewCustomerDao, $condition);
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
		$this->LogInfo("Modify customer ...");
		$customer = $this->objectToArray($this->GetCommonData());
		if("" == $customer['role_id'])
		{
			$this->errorCode = MispErrorCode::ERROR_ROLE_IS_EMPTY;
			$this->ReturnJson();
			return;
		}
		//修改用户角色
		$user['user_id'] = $customer['user_id'];
		$user['role_id'] = $customer['role_id'];
		$this->errorCode = MispCommonUserService::Modify($user);
		if(MispErrorCode::SUCCESS != $this->errorCode)
		{
			$this->ReturnJson();
			return;
		}
		//修改会员信息
		unset($customer['role_id']);				//删除$customer中的role_id字段
		try
        {
        	$result = MispCommonService::Modify($this->GetModel(), $customer);
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
		$this->LogInfo("customer delete, customer_id is ".$Req->obj);
		//删除会员信息
		$customerDao = $this->GetModel();
		$customerCondition[$customerDao->getPk()] = $Req->obj;
		try
		{
			$result = MispCommonService::Delete($customerDao, $customerCondition);
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