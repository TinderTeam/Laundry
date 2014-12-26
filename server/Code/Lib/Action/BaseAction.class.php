<?php
// 本类由系统自动生成，仅供测试用途
require 'ErrorCode.class.php';
 

class BaseAction extends Action 
{
     protected $errorCode = 0;
	 protected $errorMsg = '22';
	 
	 public function LogErr($log)
	 {
	   Log::write($log);
	 }
	 public function LogWarn($log)
	 {
	 	Log::write($log,Log::WARN);
	 }
	 public function LogInfo($log)
	 {
	 	Log::write($log,Log::INFO);
	 }
	 public function GetReqJson()
	 {
	   $req = file_get_contents("php://input");
	   $this->LogErr('the url is '.$_SERVER["REQUEST_URI"] );
	   $this->LogErr('request is '.$req);

	   return $req;
	 }
	 
	 public function GetReqObj()
	 {
	   
	   $json = $this->GetReqJson();
	   $obj = json_decode($json);
 
	   return $obj;
	 }
	 
	 
	 public function DoAuth()
	 {
	   $req =  $this->GetReqObj();
	   $token = $req->token;
	   if(true)
	   {
	       //$this->returnJson(ERROR_TOKEN_INVALID,null);
	   }
	 }
	 
 
	 public  function  ReturnJson($data=null)
	 {
	     if($data!=null)
	     {
	        $returnArray = $data; 
	     }
 	     $returnArray['errorCode'] = $this->errorCode;
 	     $returnArray['errorMsg'] = $this->GetErrorMsg();;
	     
	     $json = json_encode($returnArray);
	     
	     $this->LogErr('the url is '.$_SERVER["REQUEST_URI"] );
	     $this->LogErr('response is '.$json);
	     header('Content-Type:application/json;charset=utf-8');
	     echo $json;
	     exit;
	 }
	 
	 public  function  GetErrorMsg()
	 {
	     return $this->$errorCode;
	 }

	 
   function uuid($prefix = '')  
   {  
    $chars = md5(uniqid(mt_rand(), true));  
    $uuid  = substr($chars,0,8) . '-';  
    $uuid .= substr($chars,8,4) . '-';  
    $uuid .= substr($chars,12,4) . '-';  
    $uuid .= substr($chars,16,4) . '-';  
    $uuid .= substr($chars,20,12);  
    return $prefix . $uuid;  
  }  


	
}