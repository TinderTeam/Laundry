<?php
// 本类由系统自动生成，仅供测试用途
abstract  class EasyUITableAction extends BaseAction 
{
    abstract protected function GetTableName();
    
    public function Validator($obj)
    {
        $this->LogWarn("the validator is empty ");
        return SUCCESS;
    }
    private function  GetObj()
    {
        $Req = $this->GetReqObj();
        $obj = $Req['obj'];
        return $obj;
    }
    protected function GetTableCondition()
    {
        return null;
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
        $tableName =  $this->GetTableName();
        return $this->LoadPageTable($tableName,$this->GetTableCondition());
    }
    
    public  function LoadAll()
    {
        
    }
    public function Show()
    {
        $this->LogInfo("show");
        
        $obj = $this->GetObj();
        
        $tableName = $this->GetTableName();
        $db = M($tableName);
 
        $result = $db->find($obj);
        if(false == $result)
        {
            $this->LogErr("delete data failed.the table is " + $tableName);
        	$this->errorCode = DB_GET_ERROR;
        }
        return $this->ReturnJson($result);
    }
    public function Create()
    {
        $this->LogInfo("create");
        
        $tableName = $this->GetTableName();
        
        $obj = $this->GetObj();
        $result = $this->Validator($obj);
        if(SUCCESS != $result)
        {
            $this->errorCode = $result;
            $this->LogErr("validator failed when create ".$tableName.",the error is "+$result);
            return $this->ReturnJson();
        }    
        
        $db = M($tableName);
        $result = $db->add($obj);
	        if(false == $result)
        {
            $this->LogErr("create data failed.the table is " + $tableName);
            $this->errorCode = DB_CREATE_ERROR;
        }
        return $this->ReturnJson();
        
    }
    
    public function Modify()
    {
        $this->LogInfo("modify");
        
        $obj = $this->GetObj();
        $tableName = $this->GetTableName();
        $result = $this->Validator($obj);
        if(SUCCESS != $result)
        {
            $this->errorCode = $result;
            $this->LogErr("validator failed when create ".$tableName.",the error is "+$result);
            return $this->ReturnJson();
        }    
        
        $db = M($tableName);
        $result = $db->save($obj);
        if(false == $result)
        {
            $this->LogErr("modify data failed.the table is " + $tableName);
        	$this->errorCode = DB_MODIFY_ERROR;
        }
        return $this->ReturnJson();
    }
    
    public function Delete()
    {
        $this->LogInfo("delete");
        $obj = $this->GetObj();
        
        $tableName = $this->GetTableName();
        $db = M($tableName);
 
        $result = $db->delete($obj);
        if(false == $result)
        {
            $this->LogErr("delete data failed.the table is " + $tableName);
        	$this->errorCode = DB_DELETE_ERROR;
        }
        return $this->ReturnJson();
    }
    
    public function LoadPageTable($tableName,$condition)
    {
        $db = M($tableName);
        $count = $db->where($condition)->count();
        $page = $this->GetPage();
        $rows = $db->where($condition)->limit($page['currentPage'],$page['pageSize'])->select();
        $this->LogInfo("query the table ".$tableName." count is ".$count);
        $data['total'] = $count;
        $data['rows'] = $rows;
        if(false == $rows)
        {
            $this->errorCode = DB_GET_ERROR;
        }
        return $this->ReturnJson($data);
    }
    
}