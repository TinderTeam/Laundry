# Host: 127.0.0.1  (Version: 5.1.70-community)
# Date: 2015-01-03 21:30:15
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

INSERT INTO `misp_menu` VALUES (1,'UserManage','用户管理',NULL,NULL,NULL,'index.php/UserManage/userManage.html',NULL),(2,'ProductManage','产品管理',NULL,NULL,NULL,'laundry.php/ProductManage/productManage.html',NULL),(3,'CustomerManage','会员管理',NULL,NULL,NULL,'laundry.php/CustomerManage/customerManage.html',NULL),(4,'OrderManage','订单管理',NULL,NULL,NULL,'laundry.php/ProductManage/productManage.html',NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "misp_system_role"
#

INSERT INTO `misp_system_role` VALUES (1,'ADMIN');

#
# Source for table "misp_system_user"
#

DROP TABLE IF EXISTS `misp_system_user`;
CREATE TABLE `misp_system_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `reg_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

#
# Data for table "misp_system_user"
#

INSERT INTO `misp_system_user` VALUES (1,'admin',NULL,'1234',NULL,NULL,NULL),(3,'user1',NULL,'123456',NULL,NULL,NULL),(8,'user1',NULL,'1234',NULL,NULL,NULL),(11,'user1',NULL,'1234',NULL,NULL,NULL),(17,'user2',NULL,'1234',NULL,NULL,NULL),(19,'user1',NULL,'1234',NULL,NULL,NULL),(20,'user1',NULL,'1234',NULL,NULL,NULL),(28,'user11',NULL,'1234',NULL,NULL,NULL),(30,'测试用户',NULL,'1',NULL,NULL,NULL),(31,'customer',NULL,'1234',1,NULL,NULL);

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
