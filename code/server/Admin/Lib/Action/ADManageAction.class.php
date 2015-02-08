<?php
// 本类由系统自动生成，仅供测试用途
require_once './Public/PHPInclude/IncludeLaundry.php';

class ADManageAction extends EasyUITableAction 
{
	protected function GetModel()
	{
		return LaundryDaoContext::advertisement();
	}
	
	/* (non-PHPdoc)
	 * @see EasyUITableAction::LoadAll()
	 */
	public function LoadAll()
	{
		$this->LogInfo("Load home ad...");
		$Req = $this->GetReqObj();
		$condition['company_id'] = $Req->app_id;
		$condition['ad_name'] = ADEnum::HomeAD;
		$db = $this->GetModel();
		try
		{
			$objectList = MispCommonService::GetAll($db,$condition);
		}
		catch(FuegoException $e)
		{
			$this->errorCode = $e->getCode();
			$this->ReturnJson();
			return;
		}
		$data['obj'] = $objectList;
		$this->ReturnJson($data);
		
	}

	public function  LoadHelp()
	{
		$this->LogInfo("Load help ad...");
		$Req = $this->GetReqObj();
		$condition['company_id'] = $Req->app_id;
		$condition['ad_name'] = ADEnum::HelpAD;
		$db = $this->GetModel();
		try
		{
			$objectList = MispCommonService::GetAll($db,$condition);
		}
		catch(FuegoException $e)
		{
			$this->errorCode = $e->getCode();
			$this->ReturnJson();
			return;
		}
		$data['obj'] = $objectList;
		$this->ReturnJson($data);
	}
}