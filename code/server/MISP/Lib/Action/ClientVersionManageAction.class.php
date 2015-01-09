<?php
// 本类由系统自动生成，仅供测试用途

class ClientVersionManageAction extends EasyUITableAction 
{
	protected function GetModel()
	{
		return MispDaoContext::ClientVersion();
	}
	/*
	 * APP获取版本信息
	 */
	public function GetLatestVersion()
	{
		$req = $this->GetReqObj();
		$condition['client_type'] = $req->clientType;
		$condition['company_id'] = $req->app_id;
		$condition['version_status'] = VersionStatusEnum::LATEST_VERSION;
		$ClientVersionDao = $this->GetModel();
		$result = $ClientVersionDao->where($condition)->find();
		if(false == $result)
		{
			$this->LogErr("GetLatestVersion failed.");
			$this->errorCode = MispErrorCode::DB_GET_ERROR;
		}
		$data['obj'] = $result;
    	$this->ReturnJson($data);
	} 
}