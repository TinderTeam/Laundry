<?php
// 本类由系统自动生成，仅供测试用途
require 'IncludeMisp.php';
class IndexAction extends EasyUITableAction 
{
    public function index()
    {
		$this->display();
    }
    public function saveData()
    {
    	$this->display();
    }
	/* (non-PHPdoc)
	 * @see EasyUITableAction::GetTableName()
	 */
	protected function GetModel()
	{
		return "user";
	}

}