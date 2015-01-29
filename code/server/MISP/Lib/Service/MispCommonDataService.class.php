<?php
import("MISP.Util.FuegoLog");
import("MISP.Constant.PrivilegeEnum");
import("MISP.Constant.CompanyEnum");
import("MISP.Model.MispDaoContext");
class MispCommonDataService
{
    //获取角色权限
    static function GetRolePrivilege($user,$obj_type,$obj_value)
    {
    	FuegoLog::getLog()->LogInfo("Get role privilege...");
    	$privilegeResult = true;
    	$companyCondition['company_id'] = $user['company_id'];
    	$companyDao = MispDaoContext::Company();
    	$companyStatus = $companyDao->where($companyCondition)->getField('company_status');
    	if(CompanyEnum::STATUS_FREEZE == $companyStatus)
    	{
    		FuegoLog::getLog()->LogInfo("Get role privilege failed. The company is freezed, company id is ".$user['company_id']);
    		return false;
    	}
    	$loginCondition['master_value'] = $user['role_id'];
    	$loginCondition['master_type'] = PrivilegeEnum::MASTER_TYPE_ROLE;
    	$loginCondition['access_obj_type'] = $obj_type;
    	$loginCondition['access_obj_value'] = $obj_value;
    	
    	$privilegeDao = MispDaoContext::Privilege();
    	$Privilege = $privilegeDao->where($loginCondition)->count();
    	if($Privilege<1)
    	{
    		$privilegeResult = false;
    	}
    	return $privilegeResult;
    }
    //获取用户角色idList
    static function GetRoleID($condition)
    {
    	FuegoLog::getLog()->LogInfo("Get role id. condition is ".$condition);
    	$roleDao = MispDaoContext::SystemRole();
    	$roleList = $roleDao->where($condition)->getField('role_id',true);
    	FuegoLog::getLog()->LogInfo("Role id list is ".json_encode($roleList));
    	return  $roleList;
    }
	
}

?>