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

    //APP会员注册
    public function Register()
    {
    	$this->LogInfo("customer register ...");
    	$Req = $this->GetReqObj();
    	
    	$systemUserDao = MispDaoContext::SystemUser();
    	$condition['user_name'] = $Req->user_name;
    	$condition['company_id'] = $Req->app_id;
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
    	$user['company_id'] = $Req->app_id;
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
    	$user['user_id'] = $result;
    	$customerInfo['addr'] = $Req->addr;
    	$this->errorCode = MispServiceContext::UserManage()->Register($user,$customerInfo);
    	$this->ReturnJson();
    }
    //WEB/APP登录验证
    public function Login()
    {
    	$Req = $this->GetReqObj();
    	$reqType = $Req->clientType;
    	$req = $Req->obj;
    	$userDao= MispDaoContext::SystemUser();
    	$condition['user_name']=$req->user_name;
    	if(($reqType == ClientTypeEnum::IOS)||($reqType == ClientTypeEnum::ANDROID))
    	{
    		$condition['company_id'] = $Req->app_id;
    	}
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
    	if(($reqType == ClientTypeEnum::IOS)||($reqType == ClientTypeEnum::ANDROID))
    	{
    		$condition['company_id'] = $req->app_id;
    		$condition['user_name'] = $req->user_name;
    		$this->errorCode = MispServiceContext::UserManage()->ModifyPassword($condition,$req);
    	}
    	if($reqType == "WEB")
    	{
    		if($_SESSION['user']['user_name'] == "")
    		{
    			$this->LogWarn("Modify password failed,no user login.");
    			$this->errorCode = MispErrorCode::ERROR_SESSION_INVALID;
    			$this->ReturnJson();
    			return;
    		}
    		$condition['user_name'] = $_SESSION['user']['user_name'];
    		$this->errorCode = MispServiceContext::UserManage()->ModifyPassword($condition,$req);
    		if($this->errorCode == MispErrorCode::SUCCESS)
    		{
    			session_destroy();
    		}
    	}
    	$this->ReturnJson();
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
    		$tree['iconCls'] = $menu['icon'];
    		$tree['attributes']['url'] = $menu['url'];
    		array_push($treeList,$tree);
    	}
    	$this->LogInfo(json_encode($treeList));
    	echo json_encode($treeList);
    	exit;
    }
}