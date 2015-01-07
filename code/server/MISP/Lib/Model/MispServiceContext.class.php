<?php
import("MISP.Util.FuegoLog");
import("Admin.Model.UserServiceImpl");
class MispServiceContext
{
	static function UserManage()
	{
		FuegoLog::getLog()->LogErr("get user manage implement");
		return new UserServiceImpl();
	}
}

?>