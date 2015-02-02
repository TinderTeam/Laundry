<?php
// 本类由系统自动生成，仅供测试用途
require_once './Public/PHPInclude/IncludeLaundry.php';

class ProductManageAction extends EasyUITableAction 
{
	protected function GetModel()
	{
		return LaundryDaoContext::Product();
	}
	/* (non-PHPdoc)
	 * @see EasyUITableAction::LoadPage()
	 */
	public function LoadPage()
	{
		$this->LogInfo("Load product page list");
		$clientType = $this->GetReqObj()->clientType;
		$searchFilter = null;
		if((ClientTypeEnum::ANDROID == $clientType)||(ClientTypeEnum::IOS == $clientType))
		{
			$searchFilter['company_id'] = $this->GetReqObj()->app_id;
			$this->LogInfo("ClientType is ".$clientType.",SearchFilter is ".json_encode($searchFilter));
		}
		else
		{
			//删除自动转义增加的\
			$PostStr = stripslashes($_POST['data']);
			$req = json_decode($PostStr);
			if("" != $req->product_id)
			{
				$keyID = '%'.$req->product_id.'%';
				$searchFilter['product_id'] = array('like',$keyID);
			}
			if("" != $req->product_name)
			{
				$keyName = '%'.$req->product_name.'%';
				$searchFilter['product_name'] = array('like',$keyName);
			}
			if("" != $req->type_id)
			{
				$searchFilter['type_id'] = $req->type_id;
			}
			if(CompanyEnum::GROUP_COMPANY == $_SESSION['user']['company_id'])
			{
				//显示所有产品
				$this->LogInfo("The company id of this user is ".$_SESSION['user']['company_id']);
			}
			else
			{
				$this->LogInfo("The company id of this user is ".$_SESSION['user']['company_id']);
				$searchFilter['company_id'] = $_SESSION['user']['company_id'];
			}
			$this->LogInfo("ClientType is WEB,SearchFilter is ".json_encode($searchFilter));
		}
		$viewProductDao = LaundryDaoContext::ViewProduct();
		$order['sort_id'] = 'asc'; 
		$this->LoadPageTable($viewProductDao,$searchFilter,$order);
	}

	/* (non-PHPdoc)
	 * @see EasyUITableAction::Create()
	 */
	public function Create()
	{
		$this->LogInfo("Create product...");
		//导入图片上传类  
        import("ORG.Net.UploadFile");
        //实例化上传类  
        $upload = new UploadFile();
        $upload->maxSize = 20976;		//138px*152px
        //$upload->maxSize = 4145728;  
        //设置文件上传类型  
        $upload->allowExts = array('jpg','gif','png','jpeg');  
        //设置文件上传位置  
        $upload->savePath = "./Public/Fuego/Uploads/";	//根目录下的Public文件夹  
        //设置文件上传名(按照时间)  
        //$upload->saveRule = "time";  
        if (!$upload->upload()){
        	//上传图片错误
        	$this->LogWarn("Upload image failed.".$upload->getErrorMsg());
        	$this->LogWarn("Modify product failed.");
        	$this->errorCode =MispErrorCode::UPLOAD_IMG_FAILED;
        	$data['PrivateErrorMsg'] = $upload->getErrorMsg();
        	$this->ReturnJson($data);
        	return; 
        }else{ 
            //上传成功，获取上传信息  
            $this->LogInfo("Upload image success.");
           	$info = $upload->getUploadFileInfo();
        }
        
		$productDao = $this->GetModel();
		$data['product_name'] = $_POST['product_name'];
		$data['price_type'] = $_POST['price_type'];
		$data['price'] = $_POST['price'];
		$data['describe'] = $_POST['describe'];
		$data['type_id'] = $_POST['type_id'];
		$data['company_id'] = $_SESSION['user']['company_id'];
 		$data['img'] = $info[0]['savename'];
 		$this->LogInfo("Create product info ".json_encode($data));
 		try
 		{
 			$result = MispCommonService::Create($productDao, $data);
 			$this->LogInfo("Create product success.");
 		}
 		catch(FuegoException $e)
 		{
 			$this->errorCode = $e->getCode();
 		}
 		$this->ReturnJson();
	}
	
	/* (non-PHPdoc)
	 * @see EasyUITableAction::Modify()
	 */
	public function Modify()
	{
		$this->LogInfo("Modify product...");
		//导入图片上传类
		import("ORG.Net.UploadFile");
		//实例化上传类
		$upload = new UploadFile();
		$upload->maxSize = 20976;
		//设置文件上传类型
		$upload->allowExts = array('jpg','gif','png','jpeg');
		//设置文件上传位置
		$upload->savePath = "./Public/Fuego/Uploads/";	//根目录下的Public文件夹
		//设置文件上传名(按照时间)
		//$upload->saveRule = "time";
		if (!$upload->upload()){
			if(MispErrorCode::UPLOAD_IMG_FAILED == $upload->getErrorMsg())
			{
				//图片未更新
				$this->LogInfo("No image update.");
			}
			else 
			{
				//上传图片错误
				$this->LogWarn("Image update failed.".$upload->getErrorMsg());
				$this->LogWarn("Modify product failed.");
				$this->errorCode =MispErrorCode::UPLOAD_IMG_FAILED;
				$data['PrivateErrorMsg'] = $upload->getErrorMsg();
				$this->ReturnJson($data);
				return;
			}
		}else{
			$this->LogInfo("Image update success.");
			//上传成功，获取上传信息
			$info = $upload->getUploadFileInfo();
		}
		$productDao = $this->GetModel();
		$data['product_id']=$_POST['product_id'];
		$data['product_name'] = $_POST['product_name'];
		$data['price_type'] = $_POST['price_type'];
		$data['price'] = $_POST['price'];
		$data['describe'] = $_POST['describe'];
		$data['type_id'] = $_POST['type_id'];
		if($info[0]['savename']!=""){
			$data['img'] = $info[0]['savename'];
		}
		$this->LogInfo("Modify product info ".json_encode($data));
		try
		{
			$result = MispCommonService::Modify($productDao, $data);
			$this->LogInfo("Modify product success.");
		}
		catch(FuegoException $e)
		{
			$this->errorCode = $e->getCode();
		}
		return $this->ReturnJson();
	}

	//加载产品类型列表
	public function getProductTypeList()
	{
		$this->LogInfo("Load product type list.");
		$productTypeDao = LaundryDaoContext::ProductType();
		//$condition['parent_id']= ROOTTYPE;
		$productTypeList = $productTypeDao->select();
		//$this->LogInfo(json_encode($parentTypeList));
		$comboxDefault = array('type_id'=>'','type_name'=>'请选择...');
		$comboxTypeList = array();
		array_push($comboxTypeList, $comboxDefault);
		foreach($productTypeList as $productType)
		{
			$combox['type_id'] = $productType['type_id'];
			$combox['type_name'] = $productType['type_name'];
			//$combox['parent_name'] = $productType['type_name'];
			array_push($comboxTypeList,$combox);	
		}
		$this->LogInfo("The product type list is ".json_encode($comboxTypeList));
		echo json_encode($comboxTypeList);
		exit;
	}

}