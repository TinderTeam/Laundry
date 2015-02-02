<?php
import("MISP.Model.MispServiceContext");
// 本类由系统自动生成，仅供测试用途
class IndexAction extends BaseAction 
{

    //APP会员注册
    public function Register()
    {
    	$this->LogInfo("Customer register ...");
    	$Req = $this->GetReqObj();
    	//获取用户角色
    	$roleDao = MispDaoContext::SystemRole();
    	$roleCondition['user_type_id'] = UserTypeEnum::CUSTOMER;
    	$roleCondition['company_id'] = $Req->app_id;
    	$customerRole = $roleDao->where($roleCondition)->getField('role_id');
    	$this->LogInfo("Get customer role success,customer role is ".$customerRole);
    	//创建用户信息
    	$user['user_name'] = $Req->user_name;
    	$user['password'] = $Req->password;
    	$user['role_id'] = $customerRole;
    	$user['reg_date'] = date('Y-m-d H:i:s',time());
    	$user['company_id'] = $Req->app_id;
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
    	//创建会员信息
    	$user['user_id'] = $result;
    	$customerInfo['addr'] = $Req->addr;
    	$this->errorCode = MispServiceContext::UserManage()->Register($user,$customerInfo);
    	$this->ReturnJson();
    }
    //WEB/APP登录验证
    public function Login()
    {
    	$Req = $this->GetReqObj();
    	$req = $this->GetCommonData();
    	$reqType = $this->GetReqType();
    	$this->LogInfo("User login, Client type is ".$reqType);
    	$user['user_name'] = $req->user_name;
    	$user['password'] = $req->password;
    	if(($reqType == ClientTypeEnum::IOS)||($reqType == ClientTypeEnum::ANDROID))
    	{
    		//APP客户端登陆验证
    		$data = null;
    		$user['company_id'] = $Req->app_id;
    		$this->LogInfo("Login client type is ".$reqType.",company_id is ".$Req->app_id);
    		//验证用户与登录密码
    		try 
    		{
    			$orignalUser = MispCommonUserService::LoginValidate($user);
    		}
	    	catch(FuegoException $e)
	    	{
	    		$this->errorCode = $e->getCode();
	    		return  $this->ReturnJson();
	    	}
    		//获取用户APP登录权限
    		$privilegeResult = MispCommonDataService::GetRolePrivilege($orignalUser, PrivilegeEnum::ACCESS_TYPE_LOGIN, PrivilegeEnum::ACCESS_VALUE_APP_LOGIN);
    		if(false == $privilegeResult)
    		{
    			//用户不存在APP登录权限
    			$this->LogWarn("Get role privilege failed.The user don't have APP login privilege, login failed.");
    			$this->errorCode = MispErrorCode::USERNAME_OR_PASSWORD_WRONG;
    			return  $this->ReturnJson();
    		}
    		else
    		{
    			//获取登录权限成功
    			$this->LogInfo("Get user login privilege success. The user have APP login privilege.");
    			//判断是否已经在其他设备登录
    			$condition['user_id'] = $orignalUser['user_id'];
    			$tokenDao = MispDaoContext::Token();
    			$tokenCount = $tokenDao->where($condition)->count();
    			$this->LogInfo("Token count is ".$tokenCount);
    			if($tokenCount>0)
    			{
    				//用户已在其他设备登录，删除已有token
    				$this->LogInfo("The user has login in other device, user name is ".$orignalUser['user_name']);
    				try
    				{
    					$result = MispCommonService::Delete($tokenDao, $condition);
    					$this->LogInfo("Delete orginal token success.");
    				}
    				catch(FuegoException $e)
    				{
    					$this->LogWarn("Delete orginal token failed.");
    					$this->LogWarn("Customer APPLogin failed");
    					$this->errorCode = MispErrorCode::ERROR_LOGIN_FAILED;
    					$this->ReturnJson();
    					return;
    				}	
    			}
    			//APP登录成功
    			$data = MispServiceContext::UserManage()->AppLogin($orignalUser);
    		}
    		$this->ReturnJson($data);
    	}
    	if($reqType == ClientTypeEnum::WEB)
    	{
    		//WEB端登陆验证
    		//验证用户与登录密码
    		try
    		{
    			$orignalUser = MispCommonUserService::LoginValidate($user);
    		}
    		catch(FuegoException $e)
    		{
    			$this->errorCode = $e->getCode();
    			return  $this->ReturnJson();
    		}
    		$privilegeResult = MispCommonDataService::GetRolePrivilege($orignalUser, PrivilegeEnum::ACCESS_TYPE_LOGIN, PrivilegeEnum::ACCESS_VALUE_WEB_LOGIN);    		
    		if(false ==  $privilegeResult)
    		{
    			//用户不存在WEB登录权限
    			$this->LogWarn("Get role privilege failed. The user don't have WEB login privilege, login failed.");
    			$this->errorCode = MispErrorCode::USERNAME_OR_PASSWORD_WRONG;
    		}
    		else
    		{
    			//WEB登录成功
    			$this->LogInfo("Get user login privilege success. The user have WEB login privilege.");
    			$this->errorCode = MispServiceContext::UserManage()->WebLogin($orignalUser);
    		}
    		
    	}
    	$this->ReturnJson();   	
    }
    //退出系统
    public function Logout()
    {
    	$reqType = $this->GetReqType();
    	$this->LogInfo("User Logout, Client type is ".$reqType);
    	if(($reqType == ClientTypeEnum::IOS)||($reqType == ClientTypeEnum::ANDROID))
    	{
    		$req = $this->GetCommonData();
    		$user = $this->objectToArray($req);
	    	try
			{
				$this->errorCode = MispServiceContext::UserManage()->AppLogout($user);
			}
			catch(FuegoException $e)
			{
				$this->errorCode = $e->getCode();
			} 
    	}
    	else 
    	{
    		$this->LogInfo("WEB logout...");
    		session_destroy();
    	}
    	$this->LogInfo("User logout success.");
    	$this->ReturnJson();
    }
    //修改密码
    public function ModifyPassword()
    {
    	$req = $this->GetReqObj();
    	$reqType = $this->GetReqType();
    	$this->LogInfo("Modify password,client type is ".$reqType);
    	if(($reqType == ClientTypeEnum::IOS)||($reqType == ClientTypeEnum::ANDROID))
    	{
    		//修改密码
    		$condition['company_id'] = $req->app_id;
    		$condition['user_name'] = $req->user_name;
    		$this->errorCode = MispServiceContext::UserManage()->ModifyPassword($condition,$req);
    	}
    	if($reqType == "WEB")
    	{
    		$condition['user_name'] = $_SESSION['user']['user_name'];
    		$this->errorCode = MispServiceContext::UserManage()->ModifyPassword($condition,$req);
    		if($this->errorCode == MispErrorCode::SUCCESS)
    		{
    			session_destroy();
    		}
    	}
    	$this->ReturnJson();
    }
    //APP会员找回密码
    public function ResetPassword()
    {
    	
    	$Req = $this->GetReqObj();
    	$systemUserDao = MispDaoContext::SystemUser();
    	$condition['user_name'] = $Req->user_name;
    	$condition['company_id'] = $Req->app_id;
    	$this->LogInfo("Customer reset password , customer info is ".json_encode($condition));
    	$userID = $systemUserDao->where($condition)->getField('user_id');
    	if($userID ==''){
    		$this->LogWarn("Finding password failed, user_name is not exist.".$Req->user_name);
    		$this->errorCode = MispErrorCode::ERROR_USER_NOT_EXISTED;
    		$this->ReturnJson();
    		return;
    	}
    	$user['user_id'] = $userID;
    	$user['password'] = $Req->password;
    	try
    	{
    		$result = MispCommonService::Modify($systemUserDao, $user);
    	}
    	catch(FuegoException $e)
    	{
    		$this->errorCode = $e->getCode();
    	}
    	$this->ReturnJson();
    }
    //WEB加载菜单列表
    public function GetMenuTree()
    {
    	$this->LogInfo("get menu tree");
    	//获取用户菜单权限
    	$privilegeDao = MispDaoContext::Privilege();
    	$condition['master_value'] = $_SESSION['user']['role_id'];
    	$condition['master_type'] = PrivilegeEnum::MASTER_TYPE_ROLE;
    	$condition['access_obj_type'] = PrivilegeEnum::ACCESS_TYPE_MENU;
    	$menuIDList = $privilegeDao->where($condition)->getField('access_obj_value',true);
    	//获取菜单列表
    	$menuDao = MispDaoContext::Menu();
    	$map['menu_id']=array('in',$menuIDList);
    	$menuList = $menuDao->where($map)->select();
    	//转换为tree
    	$treeList = array();
    	foreach($menuList as $menu)
    	{
    		$tree['id'] = $menu['menu_id'];
    		$tree['text'] = $menu['value'];
    		$tree['iconCls'] = $menu['icon'];
    		$tree['attributes']['url'] = $menu['url'];
    		array_push($treeList,$tree);
    	}
    	$this->LogInfo("menu tree is ".json_encode($treeList));
    	echo json_encode($treeList);
    	exit;
    }
}