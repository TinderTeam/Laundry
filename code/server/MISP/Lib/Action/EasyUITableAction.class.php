<?php
// 本类由系统自动生成，仅供测试用途
import("MISP.Action.BaseAction");
abstract class EasyUITableAction extends BaseAction 
{
    abstract protected function GetModel();
    
    public function Validator($obj)
    {
        $this->LogWarn("the validator is empty ");
        return SUCCESS;
    }
    private function  GetObj()
    {
        $Req = $this->GetReqObj();
        $obj = $Req->obj;
        return $obj;
    }
    private function  GetObjArray()
    {
    	$Req = $this->GetReqObj();
    	$obj = $Req->obj;
    	return $this->objectToArray($obj);
    }
    protected function GetTableCondition()
    {
    	if(0 != $_SESSION['user']['company_id'])
    	{
    		$condition['company_id'] = $_SESSION['user']['company_id'];
    	}
    	return $condition;
    }
    private function GetPage()
    {
        if(null == $_POST['page'])
        {
            $page['currentPage'] = 0;
            $this->LogWarn("the crrent page is empty");
        }
        else
        {
            $page['currentPage'] = $_POST['page']-1;
        } 
        if(null == $_POST['rows'])
        {
            $page['pageSize'] = 10;
            $this->LogWarn("the page size is empty");
        }
        else
        {
            $page['pageSize'] = $_POST['rows'];
        }
        $this->LogInfo("the current page is ".$page['currentPage'].",the page size is ".$page['pageSize']);
        return $page;
    }
    public function LoadPage()
    {
        $db =  $this->GetModel();
        $this->LoadPageTable($db,$this->GetTableCondition(),$db->getPk());
    }
    
    public  function LoadAll()
    {
    	$this->LogInfo("LoadALL");
    	$Req = $this->GetReqObj();
    	$condition['company_id'] = $Req->app_id;
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
    public function Show()
    {
        $db = $this->GetModel();
        $this->ShowModel($db);
    }
    public function ShowModel($model)
    {
    	$this->LogInfo("show");
    	$objID = $this->GetObj();
    	$condition[$model->getPk()] = $objID;
    	try
    	{
    		$object = MispCommonService::GetUniRecord($model, $condition);
    	}
    	catch(FuegoException $e)
    	{
    		$this->errorCode = $e->getCode();
    		$this->ReturnJson();
    		return;
    	}
    	$data['obj'] = $object;
    	$this->ReturnJson($data);
    }
    public function Create()
    {
    	$db = $this->GetModel();
    	$this->CreateModel($db);
    }
    public function CreateModel($model)
    {
        $this->LogInfo("create");
        $obj = $this->GetObj();
        $result = $this->Validator($obj);
        if(SUCCESS != $result)
        {
            $this->errorCode = $result;
            $this->LogErr("validator failed when create ".$model—>tableName);
            $this->LogErr("the error is ".$result);
            $this->ReturnJson();
            return;
        }
        $object = $this->objectToArray($obj);
        try
        {
        	$result = MispCommonService::Create($model, $object);
        }
        catch(FuegoException $e)
        {
        	$this->errorCode = $e->getCode();
        }
        $this->ReturnJson();  
    }
    public function Modify()
    {
    	$db = $this->GetModel();
    	$this->ModifyModel($db);
    }
    public function ModifyModel($model)
    {
        $this->LogInfo("modify");
        $obj = $this->GetObj();
        $result = $this->Validator($obj);
        if(SUCCESS != $result)
        {
            $this->errorCode = $result;
            $this->LogErr("validator failed when create ".$model->tableName);
            $this->LogErr("the error is ".$result);
            $this->ReturnJson();
            return;
        }    
        $object = $this->objectToArray($obj);
        try
        {
        	$result = MispCommonService::Modify($model, $object);
        }
        catch(FuegoException $e)
        {
        	$this->errorCode = $e->getCode();
        }
        $this->ReturnJson();
    }
    public function Delete()
    {
    	$db = $this->GetModel();
    	$this->DeleteModel($db);
    }
    public function DeleteModel($model)
    {
        $this->LogInfo("delete");
        $condition[$model->getPk()] = $this->GetObj();
        try
        {
        	$result = MispCommonService::Delete($model, $condition);
        }
        catch(FuegoException $e)
        {
        	$this->errorCode = $e->getCode();
        }
        $this->ReturnJson();
    }
    
    public function LoadPageTable($model,$condition,$orderby=null)
    {
    	
    	$ReqType = $this->GetReqType();
    	$this->LogInfo("LoadPageTable,ReqType is ".$ReqType);
    	if(($ReqType == ClientTypeEnum::IOS)||($ReqType == ClientTypeEnum::ANDROID))
    	{
    		$productList = $model->where($condition)->order($orderby.' desc')->select();
    		$data['obj'] = $productList;
    		$this->ReturnJson($data);
    	}
    	else 
    	{
    		$count = $model->where($condition)->count();
    		$page = $this->GetPage();
    		$index = $page['currentPage']*$page['pageSize'];
    		$rows = $model->where($condition)->order($orderby.' desc')->limit($index,$page['pageSize'])->select();
    		$this->LogInfo("query the table ".$model->tableName." count is ".$count);
    		$data['total'] = $count;
    		$data['rows'] = $rows;
    		if(false === $rows)
    		{
    			$this->errorCode = MispErrorCode::DB_GET_ERROR;
    		}
    		$this->ReturnJson($data);
    	}
    }
    
}