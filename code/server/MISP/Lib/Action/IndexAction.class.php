<?php

// 本类由系统自动生成，仅供测试用途
class IndexAction extends BaseAction 
{
	//展示登录首页
    public function index()
    {
		$this->display();
    }
    //登录验证
    public function login()
    {
    	
    	$req = $this->GetCommonData();
    	session_start();
    	$time=30*60;
    	setcookie(session_name(),session_id(),time()+$time,"/");
    	$userDao= MispDaoContext::SystemUser();
    	$condition['user_name']=$req->user_name;
    	$userCount = $userDao->where($condition)->count();
    	if($userCount==0)		//用户不存在
    	{
    		$this->LogErr("login failed, the user is not exsit. user name is ".$req->user_name);
    		$this->errorCode = MispErrorCode::USERNAME_OR_PASSWORD_WRONG;
    		return  $this->ReturnJson();
    	}
    	$password = $userDao->where($condition)->getField('password');
    	if($req->password != $password)		//密码不正确
    	{
    		$this->LogErr("login failed, the password is wrong. user name is ".$req->user_name);
    		$this->errorCode = MispErrorCode::USERNAME_OR_PASSWORD_WRONG;
    		return  $this->ReturnJson();
    	}
    	$_SESSION['login_user']= $req->user_name;
		$this->LogInfo("login success, the user name is ".$req->user_name);
    	return $this->ReturnJson();
    }
    //加载菜单列表
    public function getMenuTree()
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