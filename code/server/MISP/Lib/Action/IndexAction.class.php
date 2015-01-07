<?php
import("MISP.Model.MispServiceContext");
// 本类由系统自动生成，仅供测试用途
class IndexAction extends BaseAction 
{
	//展示登录首页
    public function index()
    {
		$this->display();
    }
    //展示登录首页
    public function SessionValidate()
    {
    	if($_SESSION['user_name'] == "")
    	{
    		$this->LogWarn("no user login.");
    		$this->errorCode = MispErrorCode::ERROR_SESSION_INVALID;
    		$this->ReturnJson();
    		return;
    	}
    	$this->ReturnJson();
    }
    //APP会员注册
    public function Register()
    {
    	$this->LogInfo("customer register ...");
    	$Req = $this->GetReqObj();
    	
    	$systemUserDao = MispDaoContext::SystemUser();
    	$condition['user_name'] = $Req->user_name;
    	$userID = $systemUserDao->where($condition)->getField('user_id');
    	if($userID!=''){
    		$this->LogWarn("Create customer failed, user_name is exist.");
    		$this->errorCode = MispErrorCode::USER_EXISTED;
    		$this->ReturnJson();
    		return;
    	}
    	$user['user_name'] = $Req->user_name;
    	$user['password'] = $Req->password;
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
    	$user['user_id'] = $result;
    	$customerInfo['addr'] = $Req->addr;
    	$this->errorCode = MispServiceContext::UserManage()->Register($user,$customerInfo);
    	$this->ReturnJson();
    }
    //WEB/APP登录验证
    public function Login()
    {
    	$req = $this->GetCommonData();
    	$reqType = $this->GetReqType();
    	$userDao= MispDaoContext::SystemUser();
    	$condition['user_name']=$req->user_name;
    	$userCount = $userDao->where($condition)->count();
    	if($userCount==0)		//用户不存在
    	{
    		$this->LogErr("login failed, the user is not exsit. user name is ".$req->user_name);
    		$this->errorCode = MispErrorCode::USERNAME_OR_PASSWORD_WRONG;
    		return  $this->ReturnJson();
    	}
    	$orignalUser = $userDao->where($condition)->find();
    	if($req->password != $orignalUser['password'])		//验证密码
    	{
    		$this->LogErr("login failed, the password is wrong. user name is ".$req->user_name);
    		$this->errorCode = MispErrorCode::USERNAME_OR_PASSWORD_WRONG;
    		$this->ReturnJson();
    		return;
    	}
    	$data = null;
    	if($reqType == ClientTypeEnum::WEB)
    	{
    		$this->errorCode = MispServiceContext::UserManage()->WebLogin($orignalUser);
    	}
    	else 
    	{
    		$this->errorCode = MispServiceContext::UserManage()->AppLogin($orignalUser);
    		if($this->errorCode == MispErrorCode::SUCCESS)
    		{
    			$data['user'] = $orignalUser;
	    		$data['token'] = DataCreateUtil::GetUUID();
    		}	
    	}
    	$this->ReturnJson($data);

    	   	
    }
    //退出系统
    public function Logout()
    {
    	session_destroy();
    	$this->ReturnJson();
    }
    //修改密码
    public function ModifyPassword()
    {
    	$req = $this->GetReqObj();
    	$reqType = $req->clientType;
    	if($reqType == "WEB")
    	{
    		$this->LogErr($_SESSION['user_name']);
    		if($_SESSION['user_name'] == "")
    		{
    			$this->LogWarn("Modify password failed,no user login.");
    			$this->errorCode = MispErrorCode::ERROR_SESSION_INVALID;
    			$this->ReturnJson();
    			return;
    		}
    		$condition['user_name'] = $_SESSION['user_name'];
    		$userDao= MispDaoContext::SystemUser();
    		$orginalPwd = $userDao->where($condition)->getField('password');
    		if($orginalPwd != $req->pwdOld)
    		{
    			$this->LogWarn("Modify password failed,pwdOld is wrong.");
    			$this->errorCode = MispErrorCode::ERROR_OLD_PASSWORD_WORD;
    			$this->ReturnJson();
    			return;
    		}
    		$data['password'] = $req->pwdNew;
    		$result=$userDao->where($condition)->save($data);
    		if(false == $result)
    		{
    			$this->LogErr("Modify password failed,database wrong");
    			$this->errorCode = MispErrorCode::DB_MODIFY_ERROR;
    			$this->ReturnJson();
    			return;
    		}
    		session_destroy();
    		$this->ReturnJson();
    	}
    }
    //WEB加载菜单列表
    public function GetMenuTree()
    {
    	$menuDao = MispDaoContext::Menu();
    	$menuList = $menuDao->select();
    	$this->LogWarn($menuList);
    	$treeList = array();
    	foreach($menuList as $menu)
    	{
    		$tree['id'] = $menu['menu_id'];
    		$tree['text'] = $menu['value'];
    		$tree['attributes']['url'] = $menu['url'];
    		array_push($treeList,$tree);
    	}
    	$this->LogInfo(json_encode($treeList));
    	echo json_encode($treeList);
    	exit;
    }
}