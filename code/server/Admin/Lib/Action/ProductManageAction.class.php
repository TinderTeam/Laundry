<?php
// 本类由系统自动生成，仅供测试用途
require 'IncludeLaundry.php';

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
		$db = LaundryDaoContext::ViewProduct();		
        $this->LoadPageTable($db,$this->GetTableCondition());
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
		$data['img'] = $info[0]['savename'];
		$result = $productDao->add($data);
		if(false == $result)
		{
			$this->LogErr("create data failed.the table is product");
			$this->errorCode = DB_CREATE_ERROR;
		}
		return $this->ReturnJson();
	}
	
	/* (non-PHPdoc)
	 * @see EasyUITableAction::Modify()
	 */
	public function Modify()
	{
		$this->LogInfo("OK");
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
		$result = $productDao->where($condition)->save($data);
		if(false == $result)
		{
			$this->LogErr("Modify data failed.the table is product");
			$this->errorCode = DB_MODIFY_ERROR;
		}
		return $this->ReturnJson();
	}

	//加载产品类型列表
	public function getProductTypeList()
	{
		$productTypeDao = LaundryDaoContext::ProductType();
		$condition['parent_id']= ROOTTYPE;
		$parentTypeList = $productTypeDao->where($condition)->select();
		$this->LogInfo(json_encode($parentTypeList));
		$comboxTypeList = array();
		foreach($parentTypeList as $parentType)
		{
			$IDcondition['parent_id']=$parentType['type_id'];
			$typeList = $productTypeDao->where($IDcondition)->select();
			foreach($typeList as $type)
			{
				$combox['type_id'] = $type['type_id'];
				$combox['type_name'] = $type['type_name'];
				$combox['parent_name'] = $parentType['type_name'];
				array_push($comboxTypeList,$combox);	
			}
		}
		$this->LogInfo(json_encode($comboxTypeList));
		echo json_encode($comboxTypeList);
		exit;
	}

}