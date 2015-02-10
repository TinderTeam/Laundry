<?php
// 本类由系统自动生成，仅供测试用途
require_once './Public/PHPInclude/IncludeMisp.php';
class BaseAction extends Action 
{
     protected $errorCode = MispErrorCode::SUCCESS ;
	 protected $errorMsg;
	 
	 private  $reqLogFlag = true;
	 
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
	 //Session统一验证
	 public function _initialize(){
	 	$indexURL = "Index/index";
	 	$loginURL = "Index/Login";
	 	$logoutURL = "Index/Logout";
	 	$redirectURL = "Index/redirectPage";
	 	$ADManageURL = "ADManage";
	 	
	 	$reqURL = $_SERVER["REQUEST_URI"];
	 	if((ClientTypeEnum::WEB == $this->GetReqType())||(""== $this->GetReqType()))
	 	{
	 		$this->LogInfo("Session validator...".$this->GetReqType());
	 		if(strpos($reqURL, $ADManageURL))
	 		{
	 			$this->LogInfo("APP WEB View dispaly.");
				return;
	 		}
	 		if(strpos($reqURL, $redirectURL)||strpos($reqURL, $indexURL)||strpos($reqURL, $loginURL)||strpos($reqURL, $logoutURL))
	 		{
	 			$this->LogInfo("Session validator login Page");
	 			return;
	 		}
	 		if($_SESSION['user']['user_name'] == null)
	 		{
	 			$this->LogInfo("Session is out ...,Jump to login page");
	 			session_destroy();
	 			header("location: http://".$_SERVER['HTTP_HOST']."/Laundry/index.php/Index/redirectPage.html");
	 		}
	 		else 
	 		{
	 			$time=30*60;
	 			setcookie(session_name(),session_id(),time()+$time,"/");
	 		}
	 	}
	 }
	 
	 private function GetReqJson()
	 {
	   $req = file_get_contents("php://input");
	   if($this->reqLogFlag)
	   {
	   	$this->LogInfo('the url is '.$_SERVER["REQUEST_URI"] );
	   	$this->LogInfo('request is '.$req);
	   	$this->reqLogFlag=false;
	   	 
	   }
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
	   $this->LogInfo("User login validator.DoAuth...");
	   $req =  $this->GetReqObj();
	   $condition['token_name'] = $req->token;
	   $tokenDao = MispDaoContext::Token();
	   $tokenCount = $tokenDao->where($condition)->count();
	   if(0 == $tokenCount)
	   {
	   	    $this->LogWarn("DoAuth failed, user login invalid.");
	   		$this->errorCode = MispErrorCode::ERROR_LOGIN_INVALID;
	   		$this->ReturnJson();
	   }
	   $this->LogInfo("DoAuth success.");
	 }
	 
 
	 public  function  ReturnJson($data=null)
	 {
	     if($data!=null)
	     {
	        $returnArray = $data; 
	     }
 	     $returnArray['errorCode'] = $this->errorCode;
 	     $clientType = $this->GetReqType();
 	     if((ClientTypeEnum::ANDROID == $clientType)||(ClientTypeEnum::IOS == $clientType))
 	     {
 	     	$returnArray['errorMsg'] = null;
 	     }
 	     else 
 	     {
 	     	if(!ValidatorUtil::IsEmpty($data['PrivateErrorMsg']))
 	     	{
 	     		$returnArray['errorMsg'] = $data['PrivateErrorMsg'];
 	     	}
 	     	else 
 	     	{
 	     		$returnArray['errorMsg'] = $this->GetErrorMsg();
 	     	}
 	     }
	     $json = json_encode($returnArray);
	     
	     $this->LogInfo('the url is '.$_SERVER["REQUEST_URI"] );
	     $this->LogInfo('response is '.$json);
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