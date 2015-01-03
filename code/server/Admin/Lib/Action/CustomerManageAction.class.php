<?php
// 本类由系统自动生成，仅供测试用途
require 'IncludeLaundry.php';
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
		$db = LaundryDaoContext::ViewCustomer();
		$this->LoadPageTable($db,$this->GetTableCondition());
	}
	
	public function CustomerRegister()
	{
		$this->LogInfo("customer register ...");
		$systemUserDao = MispDaoContext::SystemUser();
        $customerDao = $this->GetModel();
        $obj = $this->GetObj();
        $condition['user_name'] = $obj->user_name;
        $userID = $systemUserDao->where($condition)->getField('user_id');
        if($userID!=''){
        	$this->errorCode = USER_EXISTED;
        	$this->ReturnJson();
            return;
        }
        $userData['user_name'] = $obj->user_name;
        $userData['password'] = $obj->password;
        $userData['nickname'] = $obj->nickname;
        $userData['role_id'] = CUSTOMER;
        $userData['status'] = TOAPPROVE;
        $userData['reg_date'] = date('Y-m-d H:i:s',time());
        $result = $systemUserDao->add($userData);	//$result获取到的是新创建对象的ID
        if(false == $result)
        {
        	$this->LogErr("create systemUser failed");
        	$this->errorCode = DB_CREATE_ERROR;
        	return;
        }
        $customerID = $systemUserDao->where($condition)->getField('user_id');
        $customerData['user_id'] = $customerID;
        $customerData['customer_name'] = $obj->customer_name;
        $customerData['phone'] = $obj->phone;
        $customerData['addr'] = $obj->addr;
        $customerData['birthday'] = $obj->birthday;
        $customerData['score'] = 0;
        $customerResult = $customerDao->add($customerData);	//$result获取到的是新创建对象的ID
        if(false == $customerResult)
        {
        	$this->LogErr("create customer failed");
        	$this->errorCode = DB_CREATE_ERROR;
        }       
        return;
	}

}