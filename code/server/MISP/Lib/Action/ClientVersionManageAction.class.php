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
		$this->LogInfo("Get latest client version.");
		$req = $this->GetReqObj();
		$condition['client_type'] = $req->clientType;
		$condition['company_id'] = $req->app_id;
		$condition['version_status'] = VersionStatusEnum::LATEST_VERSION;
		$ClientVersionDao = $this->GetModel();
		try
		{
			$object = MispCommonService::GetUniRecord($ClientVersionDao, $condition);
		}
		catch(FuegoException $e)
		{
			$this->errorCode = $e->getCode();
			$this->ReturnJson();
			return;
		}
		$this->LogInfo("Latest client version info ".json_encode($object));
		$data['obj'] = $object;
    	$this->ReturnJson($data);
	} 
}