# Host: 127.0.0.1  (Version: 5.1.70-community)
# Date: 2015-01-09 23:00:21
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
  `apk_url` varchar(50) DEFAULT NULL,
  `qr_code` varchar(50) DEFAULT NULL,
  `version_status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`version_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "misp_client_version"
#

INSERT INTO `misp_client_version` VALUES (1,1,'KKXD','Laundry','V1.0',2,'ANDROID','www.kkxd.com.cn',NULL,'新');

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
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "misp_company"
#

INSERT INTO `misp_company` VALUES (1,'快客洗涤','www.kkxd.com.cn',NULL,NULL,'0755-01234567');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Data for table "misp_menu"
#

INSERT INTO `misp_menu` VALUES (1,'UserManage','用户管理',NULL,'icon-user',NULL,'index.php/UserManage/userManage.html',NULL),(2,'CustomerManage','会员管理',NULL,'icon-group',NULL,'laundry.php/CustomerManage/customerManage.html',NULL),(3,'ProductManage','产品管理',NULL,'icon-product',NULL,'laundry.php/ProductManage/productManage.html',NULL),(4,'OrderManage','订单管理',NULL,'icon-order',NULL,'laundry.php/OrderManage/orderManage.html',NULL);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "misp_privilege"
#


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
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "misp_system_role"
#

INSERT INTO `misp_system_role` VALUES (1,'ADMIN'),(2,'CUSTOMER');

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
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;

#
# Data for table "misp_system_user"
#

INSERT INTO `misp_system_user` VALUES (1,'admin','123456',1,NULL,0),(50,'nidni','888888',2,'2015-01-05 11:20:32',NULL),(51,'123445646','888888',2,'2015-01-05 15:38:12',NULL),(62,'1234566666','888888',2,'2015-01-07 18:43:38',NULL),(63,'18603036649','123456',2,'2015-01-07 19:34:47',NULL),(64,'admin1','123456',1,'2015-01-08 15:01:03',1),(65,'user1','123456',1,'2015-01-08 15:02:27',1),(66,'admin2','123456',1,'2015-01-08 15:09:55',2),(67,'user101','123456',1,'2015-01-09 10:09:24',1),(68,'user102','123456',1,'2015-01-09 10:09:33',1),(69,'user103','123456',1,'2015-01-09 10:09:42',1),(70,'user104','123456',1,'2015-01-09 10:09:53',1),(71,'user105','123456',1,'2015-01-09 10:10:02',1),(72,'user106','123456',1,'2015-01-09 10:10:10',1),(73,'user107','123456',1,'2015-01-09 10:10:27',1),(74,'user108','123456',1,'2015-01-09 10:10:38',1),(75,'user109','123456',1,'2015-01-09 10:10:47',1),(76,'user110','123456',1,'2015-01-09 10:10:57',1),(77,'user111','123456',1,'2015-01-09 10:11:08',1),(79,'13322887744','888888',2,'2015-01-09 18:13:47',NULL),(80,'13366623344','888888',2,'2015-01-09 18:30:18',1),(81,'18988889999','888888',2,'2015-01-09 18:40:35',1);

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
