<?php
import("MISP.Model.MispServiceContext");
// 本类由系统自动生成，仅供测试用途
class CommonDataAction extends BaseAction 
{
	//获取角色列表
    public function GetRoleList($RoleGroupID)
    {
		$this->LogInfo("Load role list.");
		$roleDao = MispDaoContext::SystemRole();
		$roleCondition['company_id'] = $_SESSION['user']['company_id'];
		$roleCondition['user_type_id'] = $RoleGroupID;
		$this->LogInfo("get role conditon is ".json_encode($roleCondition));
		$roleList = $roleDao->where($roleCondition)->select();
		$comboxRoleList = array();
		foreach($roleList as $role)
		{
			$combox['role_id'] = $role['role_id'];
			$combox['role_name'] = $role['role_name'];
			array_push($comboxRoleList,$combox);
		}
		$this->LogInfo("The role list is ".json_encode($comboxRoleList));
		echo json_encode($comboxRoleList);
		exit;
    }

}