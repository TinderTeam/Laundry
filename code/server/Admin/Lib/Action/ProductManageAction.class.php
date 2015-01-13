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
		if(0 != $_SESSION['user']['company_id'])
		{
			$searchFilter['company_id'] = $_SESSION['user']['company_id'];
		}
		$viewProductDao = LaundryDaoContext::ViewProduct();
		$this->LoadPageTable($viewProductDao,$searchFilter);
	}

	/* (non-PHPdoc)
	 * @see EasyUITableAction::Create()
	 */
	public function Create()
	{
		//导入图片上传类  
        import("ORG.Net.UploadFile");
        //实例化上传类  
        $upload = new UploadFile();  
        $upload->maxSize = 4145728;  
        //设置文件上传类型  
        $upload->allowExts = array('jpg','gif','png','jpeg');  
        //设置文件上传位置  
        $upload->savePath = "./Public/Fuego/Uploads/";	//根目录下的Public文件夹  
        //设置文件上传名(按照时间)  
        //$upload->saveRule = "time";  
        if (!$upload->upload()){
        	$this->errorCode = NO_IMG;
            return $this->ReturnJson();  
        }else{ 
            //上传成功，获取上传信息  
           	$info = $upload->getUploadFileInfo();
        }
        
		$productDao = $this->GetModel();
		$data['product_name'] = $_POST['product_name'];
		$data['price'] = $_POST['price'];
		$data['describe'] = $_POST['describe'];
		$data['type_id'] = $_POST['type_id'];
		$data['company_id'] = $_SESSION['user']['company_id'];
 		$data['img'] = $info[0]['savename'];
 		try
 		{
 			$result = MispCommonService::Create($productDao, $data);
 		}
 		catch(FuegoException $e)
 		{
 			$this->errorCode = $e->getCode();
 		}
		return $this->ReturnJson();
	}
	
	/* (non-PHPdoc)
	 * @see EasyUITableAction::Modify()
	 */
	public function Modify()
	{
		//导入图片上传类
		import("ORG.Net.UploadFile");
		//实例化上传类
		$upload = new UploadFile();
		$upload->maxSize = 4145728;
		//设置文件上传类型
		$upload->allowExts = array('jpg','gif','png','jpeg');
		//设置文件上传位置
		$upload->savePath = "./Public/Fuego/Uploads/";	//根目录下的Public文件夹
		//设置文件上传名(按照时间)
		//$upload->saveRule = "time";
		if (!$upload->upload()){
			//$this->error($upload->getErrorMsg());
		}else{
			//上传成功，获取上传信息
			$info = $upload->getUploadFileInfo();
		}
		$productDao = $this->GetModel();
		$condition['product_id']=$_POST['product_id'];
		$data['product_name'] = $_POST['product_name'];
		$data['price'] = $_POST['price'];
		$data['describe'] = $_POST['describe'];
		$data['type_id'] = $_POST['type_id'];
		if($info[0]['savename']!=""){
			$data['img'] = $info[0]['savename'];
		}
		try
		{
			$result = MispCommonService::Modify($productDao, $condition);
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
		$this->LogInfo(json_encode($comboxTypeList));
		echo json_encode($comboxTypeList);
		exit;
	}

}