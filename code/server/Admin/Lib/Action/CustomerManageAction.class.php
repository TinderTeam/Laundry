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

	//会员新增
	/* (non-PHPdoc)
	 * @see EasyUITableAction::Create()
	 */
	public function Create()
	{
		$this->LogInfo("customer create ...");
		$Req = $this->GetReqObj();
		$obj = $Req->obj;
		
		$systemUserDao = MispDaoContext::SystemUser();
		$customerDao = $this->GetModel();
        $condition['user_name'] = $obj->user_name;
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
        $result = $systemUserDao->add($user);	//$result获取到的是新创建对象的ID
        if(false == $result)
        {
        	$this->LogErr("create systemUser failed");
        	$this->errorCode = MispErrorCode::DB_CREATE_ERROR;
        	$this->ReturnJson();
        	return;
        }
        $customer['user_id'] = $result;
        $customer['user_name'] = $obj->user_name;
        $customer['customer_name'] = $obj->customer_name;
        $customer['nickname'] = $obj->nickname;
        $customer['phone'] = $obj->user_name;
        $customer['birthday'] = $obj->birthday;
        $customer['addr'] = $obj->addr;
        $customer['score'] = 0;
        $customerResult = $customerDao->add($customer);	//$result获取到的是新创建对象的ID
        if(false == $customerResult)
        {
        	$this->LogErr("create customer failed");
        	$this->errorCode = MispErrorCode::DB_CREATE_ERROR;
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
		$obj = $Req->obj;
		
		$customerDao = $this->GetModel();
        $result = $customerDao->delete($obj);
        if(false == $result)
        {
            $this->LogErr("delete data failed.the table is customer");
        	$this->errorCode = DB_DELETE_ERROR;
        }
        $userDao = MispDaoContext::SystemUser();
        $result = $userDao->delete($obj);
        if(false == $result)
        {
        	$this->LogErr("delete data failed.the table is system_user");
        	$this->errorCode = DB_DELETE_ERROR;
        }
        $this->ReturnJson();
	}

}