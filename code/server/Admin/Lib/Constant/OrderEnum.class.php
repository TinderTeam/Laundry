<?php
class OrderEnum
{
	const OrderSubmit = "已下单";
	const PaySuccess = "付款成功";
	const WaitBuyerPay = "待付款";
	const PayFinished = "交易完成";
	const OnOperating = "处理中";
	const OrderComplete = "已完成";
	const OrderCancel = "已取消";
	const OrderAbolish = "已作废";
	
	const NormalOrder = "正常下单";
	const DirectOrder = "直接下单";
	
	const OnlinePay = "在线支付";
	const OfflinePay = "送衣付款";
	
	const EMAIL_TITLE = "快客洗涤下单提醒";
	
}
?>