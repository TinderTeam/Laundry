<?php

interface MispUserService 
{
	 function WebLogin($user);
	 function AppLogin($user);
	 function Register($user,$customerInfo);
}

?>