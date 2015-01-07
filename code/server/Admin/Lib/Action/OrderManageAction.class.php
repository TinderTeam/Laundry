<?php
// 本类由系统自动生成，仅供测试用途
require_once './Public/PHPInclude/IncludeLaundry.php';

class OrderManageAction extends EasyUITableAction 
{
	protected function GetModel()
	{
		return LaundryDaoContext::Order();
	}
	/*APP下单
	 *  (non-PHPdoc)
	 * @see EasyUITableAction::Create()
	 */
	public function Create()
	{
		$this->LogInfo("Ok");
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

		$result = $orderDao->add($orderData);	//$result获取到的是新创建对象的ID
		if(false == $result)
		{
			$this->LogErr("create order failed.");
			$this->errorCode = MispErrorCode::DB_CREATE_ERROR;
			$this->ReturnJson();
			return;
		}
		//创建订单详情
		$orderDetailDao = LaundryDaoContext::OrderDetail();
		$orderDetailList = $req->orderDetailList;
		for($i=0;$i<count($orderDetailList);$i++)
		{
			$orderDetailList[$i] = $this->objectToArray($orderDetailList[$i]);
			$orderDetailList[$i]['order_id'] = $result;
		}
		$detailResult = $orderDetailDao->addAll($orderDetailList);	//$result获取到的是新创建对象的ID
		if(false == $detailResult)
		{
			$this->LogErr("create order detail failed.");
			$this->errorCode = MispErrorCode::DB_CREATE_ERROR;
		}
		$this->ReturnJson();
	}
	/* (non-PHPdoc)
	 * @see EasyUITableAction::Delete()
	 */
	public function Delete()
	{
		$this->LogInfo("delete order...");
		$Req = $this->GetReqObj();
		$condition['order_id'] = $Req->obj;
		$orderDetailDao = LaundryDaoContext::OrderDetail();
		$result = $orderDetailDao->where($condition)->delete();
		if(false == $result)
		{
			$this->LogErr("delete orderDetail failed.");
			$this->errorCode = MispErrorCode::DB_DELETE_ERROR;
			$this->ReturnJson();
			return; 
		}
		parent::Delete();
	}
}