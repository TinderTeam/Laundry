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
		$this->LogInfo("Load order page list");
		$clientType = $this->GetReqType();
		$searchFilter = null;
		if((ClientTypeEnum::ANDROID == $clientType)||(ClientTypeEnum::IOS == $clientType))
		{
			//验证APP是否登录
			$this->DoAuth();
			$searchFilter['company_id'] = $this->GetReqObj()->app_id;
			$searchFilter['user_id'] = $this->GetReqObj()->user_id;
			$searchFilter['order_status']  = array('neq',OrderEnum::OrderAbolish);
			$this->LogInfo("ClientType is ".$clientType.",SearchFilter is ".json_encode($searchFilter));
			$order['order_code'] = 'desc';
			$this->LoadPageTable($this->GetModel(),$searchFilter,$order);
		}
		else
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
			if("" != $req->status_sort_id)
			{
				$searchFilter['status_sort_id'] = $req->status_sort_id;
			}
			if(CompanyEnum::GROUP_COMPANY == $_SESSION['user']['company_id'])
			{
				//显示所有订单
				$this->LogInfo("The company id of this user is ".$_SESSION['user']['company_id']);
			}
			else
			{
				$this->LogInfo("The company id of this user is ".$_SESSION['user']['company_id']);
				$searchFilter['company_id'] = $_SESSION['user']['company_id'];
			}
			$this->LogInfo("ClientType is WEB,SearchFilter is ".json_encode($searchFilter));
			//$order['status_sort_id'] = 'asc';
			$order['order_code'] = 'desc';
			$orderViewDao = LaundryDaoContext::ViewOrder();
			$this->LoadPageTable($orderViewDao,$searchFilter,$order);
		}
		
	}
	//加载订单详情中的产品列表
	public function LoadOrderDetailPage()
	{
		$clientType = $this->GetReqType();
		if((ClientTypeEnum::ANDROID == $clientType)||(ClientTypeEnum::IOS == $clientType))
		{
			//验证APP是否登录
			$this->DoAuth();
		}
		$req = $this->GetReqObj();
		$this->LogInfo("Load order detail page, order_id is ".$req->obj);
		$condition['order_id'] = $req->obj;
		$orderDetailDao = LaundryDaoContext::OrderDetail();
		$this->LoadPageTable($orderDetailDao,$condition,$orderDetailDao->getPk());
	}
	/*APP下单
	 *  (non-PHPdoc)
	 * @see EasyUITableAction::Create()
	 */
	public function Create()
	{
		$this->LogInfo("APP create order...");
		//验证APP是否登录
		$this->DoAuth();
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
		if(OrderEnum::OnlinePay == $orderData['pay_option'])
		{
			$orderData['order_status'] = OrderEnum::WaitBuyerPay;
		}
		else 
		{
			$orderData['order_status'] = OrderEnum::OrderSubmit;
		}
		$orderData['create_time'] = date('Y-m-d H:i:s',time());
		$orderData['company_id'] = $req->app_id;
		$this->LogInfo("order info is ".json_encode($orderData));
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
		//正常下单创建订单详情
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
			$this->LogInfo("order type is ".OrderEnum::NormalOrder);
			$this->LogInfo("order detail is ".json_encode($orderDetailList));
			try
			{
				$detailResult = MispCommonService::CreateList($orderDetailDao, $orderDetailList);
			}
			catch(FuegoException $e)
			{
				$this->errorCode = $e->getCode();
				$this->ReturnJson();
				return;
			}
		}
		//直接下单
		if(OrderEnum::DirectOrder == $orderData['order_type'])
		{
			$this->LogInfo("order type is ".OrderEnum::DirectOrder);	
		}
		//发出订单邮件通知
		$adminDao = LaundryDaoContext::administrators();
		$CompanyCondition['company_id'] = $req->app_id;
		$EmailList = $adminDao->where($CompanyCondition)->getField('email',true);
		foreach($EmailList as $Email)
		{
			$this->LogInfo("admin email is ".$Email);
			if (false == SendMail($Email,OrderEnum::EMAIL_TITLE,"会员：".$orderData['user_name']." 已成功提交订单，会员姓名：".$orderData['contact_name']."。订单号为：".$orderData['order_code']."。取衣地址：".$orderData['take_addr']."。请及时处理！"))
			{
				$this->LogErr("Order has create,But send mail failed.");
			}
		}
		//获取订单信息返回
		$orderCondition['order_code'] = $orderData['order_code'];
		try
		{
			$object = MispCommonService::GetUniRecord($orderDao, $orderCondition);
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
	//更新订单状态
	public function UpdateOrder($type)
	{
		$req = $this->GetReqObj();
		$orderIDList = $req->obj;
		$this->LogInfo("Update order status, order list is ".$orderIDList);
		$status = null;
		if("Operate"==$type)
		{
			$status = OrderEnum::OnOperating;
		}
		elseif("Complete"==$type)
		{
			$status = OrderEnum::OrderComplete;
		}
		$orderDao = $this->GetModel();
		try
		{
			$result = MispCommonService::ModifyField($orderDao, $orderIDList, 'order_status', $status);
		}
		catch(FuegoException $e)
		{
			$this->errorCode = $e->getCode();
		}
		$this->ReturnJson();
	}
	//APP取消订单
	public function CancelOrder()
	{
		//验证APP是否登录
		$this->DoAuth();
		$orderID = $this->GetCommonData();
		$orderIDList = array();
		array_push($orderIDList, $orderID);
		$this->LogInfo("APP cancel order, order id is ".$orderID);
		$orderDao = $this->GetModel();
		try
		{
			$result = MispCommonService::ModifyField($orderDao, $orderIDList, 'order_status', OrderEnum::OrderCancel);
		}
		catch(FuegoException $e)
		{
			$this->errorCode = $e->getCode();
		}
		$this->ReturnJson();
	}
	//APP删除订单
	public function AbolishOrder()
	{
		//验证APP是否登录
		$this->DoAuth();
		$orderID = $this->GetCommonData();
		$orderIDList = array();
		array_push($orderIDList, $orderID);
		$this->LogInfo("APP abolish order, order id is ".$orderID);
		$orderDao = $this->GetModel();
		try
		{
			$result = MispCommonService::ModifyField($orderDao, $orderIDList, 'order_status', OrderEnum::OrderAbolish);
		}
		catch(FuegoException $e)
		{
			$this->errorCode = $e->getCode();
		}
		$this->ReturnJson();
	}
	/* 删除订单
	 * (non-PHPdoc)
	 * @see EasyUITableAction::Delete()
	 */
	public function Delete()
	{
		$Req = $this->GetReqObj();
		$this->LogInfo("delete order, order id is ".$Req->obj);
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
	//加载订单状态类型列表
	public function getOrderStatusList()
	{
		$this->LogInfo("Load order status list.");
		$orderStatusDao = LaundryDaoContext::OrderStatus();
		//$condition['parent_id']= ROOTTYPE;
		$orderStatusList = $orderStatusDao->select();
		//$this->LogInfo(json_encode($parentTypeList));
		$comboxDefault = array('status_sort_id'=>'','order_status'=>'请选择...');
		$comboxStatusList = array();
		array_push($comboxStatusList, $comboxDefault);
		foreach($orderStatusList as $orderStatus)
		{
			$combox['status_sort_id'] = $orderStatus['status_sort_id'];
			$combox['order_status'] = $orderStatus['order_status'];
			//$combox['parent_name'] = $productType['type_name'];
			array_push($comboxStatusList,$combox);	
		}
		$this->LogInfo("The order status list is ".json_encode($comboxStatusList));
		echo json_encode($comboxStatusList);
		exit;
	}
}