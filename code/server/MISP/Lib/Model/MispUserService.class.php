<?php

interface MispUserService 
{
	 function WebLogin($user);
	 function AppLogin($user);
	 function AppLogout($user);
	 function Register($user,$customerInfo);
	 function ModifyPassword($condition,$req);
	 function CreateAdmin($admin);
}
?>