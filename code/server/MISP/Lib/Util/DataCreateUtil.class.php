<?php
// 本类由系统自动生成，仅供测试用途
class DataCreateUtil
{
	/**
	 * 生成唯一ID
	 * @param 
	 */
	static function GetUUID($prefix = '')
	{
		$chars = md5(uniqid(mt_rand(), true));
		$uuid  = substr($chars,0,8) . '-';
		$uuid .= substr($chars,8,4) . '-';
		$uuid .= substr($chars,12,4) . '-';
		$uuid .= substr($chars,16,4) . '-';
		$uuid .= substr($chars,20,12);
		return $prefix . $uuid;
	}
	 
}