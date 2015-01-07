<?php
// 本类由系统自动生成，仅供测试用途
class FuegoLog
{
	 public static function getLog()
	 {
	 	return new FuegoLog();
	 }
	 public function LogErr($log)
	 {
	   Log::write($log);
	 }
	 public function LogWarn($log)
	 {
	 	Log::write($log,Log::WARN);
	 }
	 public function LogInfo($log)
	 {
	 	Log::write($log,Log::INFO);
	 }
	 
}