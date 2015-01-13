<?php
// 本类由系统自动生成，仅供测试用途
require_once './Public/PHPInclude/IncludeLaundry.php';

class OrderManageAction extends EasyUITableAction 
{
	protected function GetModel()
	{
		return LaundryDaoContext::Order();
	}
	/* (non-PHPdoc)
	 * @see EasyUITableAction::LoadPage()
	 */
	public function LoadPage()
	{
		//删除自动转义增加的\
		$PostStr = stripslashes($_POST['data']);
		$req = json_decode($PostStr);
		if("" != $req->order_code)
		{
			$keyID = '%'.$req->order_code.'%';
			$searchFilter['order_code'] = array('like',$keyID);
		}
		if("" != $req->user_name)
		{
			$keyUser = '%'.$req->user_name.'%';
			$searchFilter['user_name'] = array('like',$keyUser);
		}
		if("" != $req->start_time)
		{
			$date= date("Y-m-d",strtotime($req->start_time));
			$searchFilter['create_time']=array('EGT',$date);	//判断截至时间大于当前时间的条件
		}
		if("" != $req->end_time)
		{
			$date= date("Y-m-d",strtotime("$req->end_time +1 day"));
			$condition['create_time']=array('ELT',$date);
			$condition['_logic'] = "AND";
			$searchFilter['_complex']=$condition;
		}
		if((ClientTypeEnum::ANDROID == $this->GetReqObj()->clientType)||(ClientTypeEnum::IOS == $this->GetReqObj()->clientType))
		{
			$searchFilter['company_id'] = $this->GetReqObj()->app_id;
			$searchFilter['user_id'] = $this->GetReqObj()->user_id;	
		}
		else
		{
			if(0 == $_SESSION['user']['company_id'])
			{
			}
			else
			{
				$searchFilter['company_id'] = $_SESSION['user']['company_id'];
			}
		}
	
		$this->LogInfo("Search filter is ".json_encode($searchFilter));
		$this->LoadPageTable($this->GetModel(),$searchFilter);
	}
	//加载订单详情中的产品列表
	public function LoadOrderDetailPage()
	{
		$req = $this->GetReqObj();
		$condition['order_id'] = $req->obj;
		$orderDetailDao = LaundryDaoContext::OrderDetail();
		$this->LoadPageTable($orderDetailDao,$condition);
	}
	//更新订单状态
	public function UpdateOrder()
	{
		$req = $this->GetReqObj();
		$orderIDList = $req->obj;
		$this->LogInfo($orderIDList);
		$orderDao = $this->GetModel();
		try
		{
			$result = MispCommonService::ModifyField($orderDao, $orderIDList, 'order_status', OrderEnum::OrderComplete);
		}
		catch(FuegoException $e)
		{
			$this->errorCode = $e->getCode();
		}
		$this->ReturnJson();
	}
	/*APP下单
	 *  (non-PHPdoc)
	 * @see EasyUITableAction::Create()
	 */
	public function Create()
	{
		$req = $this->GetReqObj();
		//创建订单
		$order = $req->order;
		$orderData = $this->objectToArray($order);
		
		$orderDao = $this->GetModel();
		do
		{
			$orderData['order_code'] = date('ymdHis',time()).rand(1000,9999);
		}
		while($orderDao->where('order_code='.$orderData['order_code'])->count());
		$orderData['create_time'] = date('Y-m-d H:i:s',time());
		$orderData['order_status'] = OrderEnum::OrderSubmit;
		$orderData['company_id'] = $req->app_id;
		try
		{
			$result = MispCommonService::Create($orderDao, $orderData);
		}
		catch(FuegoException $e)
		{
			$this->errorCode = $e->getCode();
			$this->ReturnJson();
			return;
		}
		if($orderData['order_type'] == OrderEnum::NormalOrder)
		{
			//创建订单详情
			$orderDetailDao = LaundryDaoContext::OrderDetail();
			$orderDetailList = $req->orderDetailList;
			for($i=0;$i<count($orderDetailList);$i++)
			{
				$orderDetailList[$i] = $this->objectToArray($orderDetailList[$i]);
				$orderDetailList[$i]['order_id'] = $result;
			}
			try
			{
				$detailResult = MispCommonService::CreateList($orderDetailDao, $orderDetailList);
			}
			catch(FuegoException $e)
			{
				$this->errorCode = $e->getCode();
			}
		}
		SendMail('market@fuego.cn',"下单提示","Laundry下单提醒");
		$this->ReturnJson();
	}
	/* 删除订单
	 * (non-PHPdoc)
	 * @see EasyUITableAction::Delete()
	 */
	public function Delete()
	{
		$this->LogInfo("delete order...");
		$Req = $this->GetReqObj();
		$condition['order_id'] = $Req->obj;
		$orderDetailDao = LaundryDaoContext::OrderDetail();
		try
		{
			$result = MispCommonService::Delete($orderDetailDao, $condition);
		}
		catch(FuegoException $e)
		{
			$this->errorCode = $e->getCode();
			$this->ReturnJson();
			return;
		}
		parent::Delete();
	}
}