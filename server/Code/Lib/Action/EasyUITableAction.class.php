<?php
// 本类由系统自动生成，仅供测试用途
abstract  class EasyUITableAction extends BaseAction 
{
    abstract protected function GetTableName();
    
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
    
    public function Create()
    {

        $tableName = $this->GetTableName();
        $obj = $this->GetObj();
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
        $obj = $this->GetObj();
        
        $tableName = $this->GetTableName();
        
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