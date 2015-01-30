<?php
// 本类由系统自动生成，仅供测试用途
class ValidatorUtil
{
	/**
	 * 生成唯一ID
	 * @param 
	 */
	static function IsEmpty($str)
	{
		if (null == $str || count(trim($str)) == 0)
            {
                return true;
            }
        return false;
	}
	 
}