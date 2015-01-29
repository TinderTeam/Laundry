<?php
require_once './Public/PHPInclude/IncludeLaundry.php';
class AlipayNotifyServiceImpl implements MispAlipayNotifyService
{
	/* (non-PHPdoc)
	 * @see MispAlipayNotifyService::OrderPaySuccess()
	 */
	public function UpdateOrderStatus($status,$orderCode)
	{
		// TODO Auto-generated method stub
		FuegoLog::getLog()->LogInfo('update order status');
		$orderDao = LaundryDaoContext::Order();
		$condition['order_code'] = $orderCode;
		if($status == "TRADE_SUCCESS")
		{
			FuegoLog::getLog()->LogInfo('order pay success');
			$result = $orderDao->where($condition)->setField('order_status', OrderEnum::PaySuccess);
		}
		elseif($status == "TRADE_FINISHED")
		{
			FuegoLog::getLog()->LogInfo('order pay finished');
			$result = $orderDao->where($condition)->setField('order_status', OrderEnum::PayFinished);
		}
		else 
		{
			FuegoLog::getLog()->LogInfo('order pay failed, wait buyer pay.');
			$result = $orderDao->where($condition)->setField('order_status', OrderEnum::PayFailed);
		}
		
		
		if($result)
		{
			FuegoLog::getLog()->LogInfo('update order status success');
		}
		else 
		{
			FuegoLog::getLog()->LogErr('update order status failed');
		}
	}
 
}

?>