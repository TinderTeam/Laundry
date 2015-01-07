<?php

class MispDaoContext
{
	const MISPDB = 'mysql://root:root@localhost:3306/misp';
	const PREFIX = 'misp_';
	static function SystemUser()
	{
		return M('system_user',MispDaoContext::PREFIX,MispDaoContext::MISPDB);
	}
	static function Menu()
	{
		return M('menu',MispDaoContext::PREFIX,MispDaoContext::MISPDB);
	}
}

?>