<?php
// 本类由系统自动生成，仅供测试用途
require_once './Public/PHPInclude/IncludeLaundry.php';

class ADManageAction extends EasyUITableAction 
{
	protected function GetModel()
	{
		return LaundryDaoContext::advertisement();
	}
}