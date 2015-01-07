<?php
// 本类由系统自动生成，仅供测试用途
//import("MISP.Model.MispDaoContext");
//import("MISP.Constant.MispErrorCode");
//import("MISP.Constant.UserRoleEnum");
//import("MISP.Constant.ClientTypeEnum");
//import("MISP.Model.MispServiceContext");
require_once './Public/PHPInclude/IncludeMisp.php';
class BaseAction extends Action 
{
     protected $errorCode = MispErrorCode::SUCCESS ;
	 protected $errorMsg;
	 
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
	 private function GetReqJson()
	 {
	   $req = file_get_contents("php://input");
	   $this->LogInfo('the url is '.$_SERVER["REQUEST_URI"] );
	   $this->LogInfo('request is '.$req);

	   return $req;
	 }
	 
	 public function GetReqObj()
	 {
	   
	   $json = $this->GetReqJson();
	   $obj = json_decode($json);
	   return $obj;
	 }
	 public function GetReqType()
	 {
	 
	 	$json = $this->GetReqJson();
	 	$obj = json_decode($json);
	 
	 	return $obj->clientType;
	 }
	 public function GetCommonData()
	 {
	 
	 	$json = $this->GetReqJson();

	 	$obj = json_decode($json);
 	 
	 	return $obj->obj;
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
 	     $clientType = $this->GetReqType();
 	     if($clientType == ClientTypeEnum::WEB)
 	     {
 	     	$returnArray['errorMsg'] = $this->GetErrorMsg();
 	     }
 	     else 
 	     {
 	     	$returnArray['errorMsg'] = null;
 	     }
	     $json = json_encode($returnArray);
	     
	     $this->LogErr('the url is '.$_SERVER["REQUEST_URI"] );
	     $this->LogErr('response is '.$json);
	     header('Content-Type:application/json;charset=utf-8');
	     echo $json;
	     exit;
	 }
	 
	 public  function  GetErrorMsg()
	 {
	 	$ini_array = parse_ini_file("./MISP/Lang/MispErrorMessage.ini");
	    return $ini_array[$this->errorCode];
	 }
	 
	 function objectToArray($array) {
	 	if(is_object($array)) {
	 		$array = (array)$array;
	 	} if(is_array($array)) {
	 		foreach($array as $key=>$value) {
	 			$array[$key] = $this->objectToArray($value);
	 		}
	 	}
	 	return $array;
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