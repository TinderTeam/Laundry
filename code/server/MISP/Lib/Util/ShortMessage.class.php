<?php
// 本类由系统自动生成，仅供测试用途
class ShortMessage
{
	/**
	 * 短信接口
	 * @param unknown $phoneNum
	 * @param unknown $content
	 */
	static function SendMessage($phoneNum,$content) {
		$url = "http://www.mxtong.net.cn/GateWay/Services.asmx/DirectSend?";
		$url = $url."UserID="."966239";			//短信接口信息
		$url = $url."&Account="."admin";
		$url = $url."&Password="."EWSMFX";
		$options = array(
				'http' => array(
						'method' =>'GET',
						'timeout' =>15 * 60 // 超时时间（单位:s）
				)
		);
		$url = $url."&Phones=".$phoneNum;
		$url = $url."&Content=".$content;
		$url = $url."&SendTime=";
		$url = $url."&SendType=1";
		$url = $url."&PostFixNumber=";
		$context = stream_context_create($options);
		$result = file_get_contents($url, false, $context);
		return $result;
	}
	/**
	 * 生成固定位数的随机数字
	 * @param unknown $length
	 * @return string
	 */
	static function getRandNum($length) {
		$str = '0123456789';
		$randNum = '';
		$len = strlen($str)-1;
		for($i = 0;$i < $length;$i ++){
			$num = mt_rand(0, $len);
			$randNum .= $str[$num];
		}
		return $randNum ;
	}
	 
}