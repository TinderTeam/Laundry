<?php
import("MISP.Util.FuegoLog");
import("Admin.Model.UserServiceImpl");
import("Admin.Model.AlipayNotifyServiceImpl");
class MispServiceContext
{
	static function UserManage()
	{
		FuegoLog::getLog()->LogInfo("Get user manage implement");
		return new UserServiceImpl();
	}
	static function AlipayNotify()
	{
		FuegoLog::getLog()->LogInfo("Get Alipay implement");
		return new AlipayNotifyServiceImpl();
	}
}

?>