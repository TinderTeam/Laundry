# Host: 127.0.0.1  (Version: 5.1.70-community)
# Date: 2015-02-09 23:47:09
# Generator: MySQL-Front 5.3  (Build 2.42)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

DROP DATABASE IF EXISTS `misp`;
CREATE DATABASE `misp` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `misp`;

#
# Source for table "misp_client_version"
#

DROP TABLE IF EXISTS `misp_client_version`;
CREATE TABLE `misp_client_version` (
  `version_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) DEFAULT NULL,
  `app_name` varchar(50) DEFAULT NULL,
  `apk_name` varchar(50) DEFAULT NULL,
  `version_name` varchar(50) DEFAULT NULL,
  `version_code` int(11) DEFAULT NULL,
  `client_type` varchar(20) DEFAULT NULL,
  `apk_url` varchar(255) DEFAULT NULL,
  `qr_code` varchar(50) DEFAULT NULL,
  `version_status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`version_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "misp_client_version"
#

INSERT INTO `misp_client_version` VALUES (1,1,'KKXD','Laundry.apk','V1.0',2,'ANDROID','/Public/App/Android/Laundry.apk',NULL,'新');

#
# Source for table "misp_company"
#

DROP TABLE IF EXISTS `misp_company`;
CREATE TABLE `misp_company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(50) DEFAULT NULL,
  `company_web_site` varchar(50) DEFAULT NULL,
  `company_addr` varchar(50) DEFAULT NULL,
  `company_desp` varchar(255) DEFAULT NULL,
  `service_phone` varchar(20) DEFAULT NULL,
  `alipay_seller` varchar(50) DEFAULT NULL,
  `alipay_partner` varchar(50) DEFAULT NULL,
  `alipay_private_key` varchar(2000) DEFAULT NULL,
  `company_status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "misp_company"
#

INSERT INTO `misp_company` VALUES (1,'快客洗涤','www.kkxd.com.cn',NULL,NULL,'0859-3383000','996825888@qq.com','2088811061975475','MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOVjx6iMMZgkgIndiKHoerD18LWnpjzIHEA6oyWAd0UaN5iWAoDeg71PW7h+k5ZPIJ8XCzHLj9PSBKrEKg8gCjKJxH7jR1hDaodL4Vm4HgOmf6XdhFxZOM0cy3NyD4sorFqMca5r1X5EkenDYkFjXmrwKaRifDOWRfGAf6RrWzxbAgMBAAECgYEA35m6xp4Zvc9fCIRMql5eMl8aS0hnb/o0J5vA6k5mdJKQvQkE2a+NRRy1MIsZvDvXdZxVyi0+PuEKsZbT1LiLlk0xZl+EmiXDRSYR6Wsz8LpT7edRhrAJj9IusPV1TDgghVIXmkEYT5dpRLke9l1sYQFWQb4rr6vjrnPxaajbhdECQQD57+1hSYmVX6GZQuZLhnlQt/SlV7Tz45zVBmcd4ZlZ877a19WsKHMWHDQbU7QNONtJ6CiAWaLycRUUPMDmlXlzAkEA6vRBjMiD59vMrLQyWUiBEKexXeCsypvh5OIFQ2hkiBKw6gIl4QK4uEfC1LxcIg1xfc8N7bn7gUlz6tdajyaXeQJAYFhTijAdwB34HitCuRRiSXJP9TilAWrZNujb8RHY2mryREv1CwMgsgI3N92BR6OGLKw4iJmFDa33sTBmL7yo7wJBALVIPQNo+w18dBGU/3wQCzVUje+HGQtC9ypokfMOqvKqqUIE4kEYnnnhNJx7sQK9KKIPjgmshDee+wdpnf/xoNECQDg8O7/Vm23PAUaiN70t/XU2GT334yb0n6VWcdow9LaRLsajL5c8/MbsGBa8gWw0smrcIyHbS0jLe5A1Gsr0jRg=','正常');

#
# Source for table "misp_menu"
#

DROP TABLE IF EXISTS `misp_menu`;
CREATE TABLE `misp_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `value` varchar(20) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `external` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

#
# Data for table "misp_menu"
#

INSERT INTO `misp_menu` VALUES (1,'AdminManage','员工管理',NULL,'icon-user',NULL,'laundry.php/AdminManage/adminManage.html',NULL),(2,'CustomerManage','会员管理',NULL,'icon-group',NULL,'laundry.php/CustomerManage/customerManage.html',NULL),(3,'ProductManage','产品管理',NULL,'icon-product',NULL,'laundry.php/ProductManage/productManage.html',NULL),(4,'OrderManage','订单管理',NULL,'icon-order',NULL,'laundry.php/OrderManage/orderManage.html',NULL),(5,'CompanyManage','公司管理',NULL,'icon-house',NULL,'index.php/CompanyManage/companyManage.html',NULL),(6,'UserManage','超级管理员',NULL,'icon-admin',NULL,'index.php/UserManage/userManage.html',NULL);

#
# Source for table "misp_operate_log"
#

DROP TABLE IF EXISTS `misp_operate_log`;
CREATE TABLE `misp_operate_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `log_name` varchar(20) DEFAULT NULL,
  `obj_name` varchar(20) DEFAULT NULL,
  `result` varchar(20) DEFAULT NULL,
  `oper_desp` varchar(50) DEFAULT NULL,
  `oper_time` datetime DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "misp_operate_log"
#


#
# Source for table "misp_privilege"
#

DROP TABLE IF EXISTS `misp_privilege`;
CREATE TABLE `misp_privilege` (
  `privilege_id` int(11) NOT NULL AUTO_INCREMENT,
  `master_type` varchar(20) DEFAULT NULL,
  `master_value` varchar(50) DEFAULT NULL,
  `access_obj_type` varchar(20) DEFAULT NULL,
  `access_obj_value` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`privilege_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

#
# Data for table "misp_privilege"
#

INSERT INTO `misp_privilege` VALUES (1,'ROLE','1','LOGIN','1'),(2,'ROLE','2','LOGIN','1'),(3,'ROLE','3','LOGIN','1'),(4,'ROLE','1','MENU','1'),(5,'ROLE','1','MENU','2'),(6,'ROLE','1','MENU','3'),(7,'ROLE','1','MENU','4'),(8,'ROLE','1','MENU','5'),(9,'ROLE','1','MENU','6'),(10,'ROLE','2','MENU','1'),(11,'ROLE','2','MENU','2'),(12,'ROLE','2','MENU','3'),(13,'ROLE','2','MENU','4'),(14,'ROLE','3','MENU','2'),(15,'ROLE','3','MENU','4'),(16,'ROLE','4','LOGIN','2'),(17,'ROLE','5','MENU','1'),(18,'ROLE','5','MENU','2'),(19,'ROLE','5','MENU','3'),(20,'ROLE','5','MENU','4'),(21,'ROLE','5','LOGIN','1'),(22,'ROLE','6','LOGIN','2');

#
# Source for table "misp_system_id_type"
#

DROP TABLE IF EXISTS `misp_system_id_type`;
CREATE TABLE `misp_system_id_type` (
  `name` varchar(10) NOT NULL DEFAULT '',
  `step` int(11) DEFAULT '1',
  `inc_mode` int(11) DEFAULT '1',
  `length` int(11) DEFAULT '10',
  `current_id` int(11) DEFAULT '1',
  `prefix` varchar(10) DEFAULT NULL,
  `suffix` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "misp_system_id_type"
#


#
# Source for table "misp_system_role"
#

DROP TABLE IF EXISTS `misp_system_role`;
CREATE TABLE `misp_system_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  `user_type_id` int(11) DEFAULT NULL,
  `user_type_name` varchar(50) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Data for table "misp_system_role"
#

INSERT INTO `misp_system_role` VALUES (1,'系统管理员',1,'SUPER_ADMIN',0),(2,'管理员',2,'ADMIN',1),(3,'客服',2,'ADMIN',1),(4,'消费者',3,'CUSTOMER',1);

#
# Source for table "misp_system_user"
#

DROP TABLE IF EXISTS `misp_system_user`;
CREATE TABLE `misp_system_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `reg_date` datetime DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8;

#
# Data for table "misp_system_user"
#

INSERT INTO `misp_system_user` VALUES (1,'admin','111111',1,NULL,0),(98,'kkxd','123456',2,'2015-01-13 16:41:58',1),(133,'18603036649','123456',4,'2015-01-27 19:17:58',1),(138,'13926005532','123456',4,'2015-01-29 16:27:35',1),(140,'123123dlk','888888',4,'2015-01-30 00:03:47',1),(141,'客服人员1','888888',3,'2015-01-30 21:27:09',1);

#
# Source for table "misp_token"
#

DROP TABLE IF EXISTS `misp_token`;
CREATE TABLE `misp_token` (
  `token_id` int(11) NOT NULL AUTO_INCREMENT,
  `token_name` varchar(50) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`token_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

#
# Data for table "misp_token"
#

INSERT INTO `misp_token` VALUES (40,'e3c3e3e3-2d0a-976f-dbb6-b72551c0916f',138),(41,'655677ee-6031-d83d-4943-4e2cc96dcf5d',133);

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
