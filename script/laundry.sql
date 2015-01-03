# Host: 127.0.0.1  (Version: 5.1.70-community)
# Date: 2015-01-03 21:30:04
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
# Source for table "customer"
#

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `birthday` varchar(20) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `level` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

#
# Data for table "customer"
#

INSERT INTO `customer` VALUES (31,'李思廉','1231234455','广州市深圳区','1998-06-01',1,'1');

#
# Source for table "delivery_info"
#

DROP TABLE IF EXISTS `delivery_info`;
CREATE TABLE `delivery_info` (
  `delivery_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `delivery_addr` varchar(255) DEFAULT NULL,
  `take_addr` varchar(255) DEFAULT NULL,
  `delivery_time` varchar(50) DEFAULT NULL,
  `take_time` varchar(50) DEFAULT NULL,
  `customer_name` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`delivery_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "delivery_info"
#


#
# Source for table "order"
#

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_name` varchar(20) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `confirm_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `pay_option` varchar(20) DEFAULT NULL,
  `order_status` varchar(20) DEFAULT NULL,
  `delivery_info_id` int(11) DEFAULT NULL,
  `handler_id` int(11) DEFAULT NULL,
  `operater_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "order"
#


#
# Source for table "order_detail"
#

DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `order_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `product_name` varchar(50) DEFAULT NULL,
  `product_type` varchar(50) DEFAULT NULL,
  `describe` varchar(255) DEFAULT NULL,
  `original_price` float(5,2) DEFAULT NULL,
  `price` float(5,2) DEFAULT NULL,
  `img` varchar(50) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `limit_time` datetime DEFAULT NULL,
  `detail_info` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "order_detail"
#


#
# Source for table "product"
#

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `seller_id` int(11) DEFAULT NULL,
  `describe` varchar(255) DEFAULT NULL,
  `original_price` float(5,2) DEFAULT NULL,
  `price` float(5,2) DEFAULT NULL,
  `img` varchar(50) DEFAULT NULL,
  `product_status` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `detail_info` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

#
# Data for table "product"
#

INSERT INTO `product` VALUES (23,'产品',1001,NULL,'123',NULL,123.00,'54a22415cd685.png',NULL,NULL,NULL,NULL),(24,'12',1002,NULL,'',NULL,213.00,'54a28be8e129b.png',NULL,NULL,NULL,NULL),(25,'1',2001,NULL,'1234',NULL,123.00,'54a28c1444d27.png',NULL,NULL,NULL,NULL),(26,'1111',2001,NULL,'123123213',NULL,999.99,'54a28d2d37b47.png',NULL,NULL,NULL,NULL),(27,'123123',3001,NULL,'123123123',NULL,999.99,'54a28da23d15f.png',NULL,NULL,NULL,NULL),(28,'123',4001,NULL,'123123',NULL,999.99,'54a28ff0aa2f8.png',NULL,NULL,NULL,NULL);

#
# Source for table "product_cart"
#

DROP TABLE IF EXISTS `product_cart`;
CREATE TABLE `product_cart` (
  `cart_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`cart_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "product_cart"
#


#
# Source for table "product_type"
#

DROP TABLE IF EXISTS `product_type`;
CREATE TABLE `product_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5002 DEFAULT CHARSET=utf8;

#
# Data for table "product_type"
#

INSERT INTO `product_type` VALUES (1,'衣物类',0),(2,'居家用品类',0),(3,'箱包鞋类',0),(4,'奢侈品及其他类',0),(5,'汽车配饰类',0),(1001,'上装类',1),(1002,'下装类',1),(1003,'毛皮服饰类',1),(2001,'居家用品类',2),(3001,'箱包鞋类',3),(4001,'奢侈品类',4),(4002,'其他类',4),(5001,'汽车配饰类',5);

#
# Source for view "view_customer"
#

DROP VIEW IF EXISTS `view_customer`;
CREATE VIEW `view_customer` AS 
  select `laundry`.`customer`.`user_id` AS `user_id`,`laundry`.`customer`.`customer_name` AS `customer_name`,`laundry`.`customer`.`phone` AS `phone`,`laundry`.`customer`.`addr` AS `addr`,`laundry`.`customer`.`birthday` AS `birthday`,`laundry`.`customer`.`score` AS `score`,`laundry`.`customer`.`level` AS `level`,`misp`.`misp_system_user`.`user_name` AS `user_name`,`misp`.`misp_system_user`.`nickname` AS `nickname`,`misp`.`misp_system_user`.`password` AS `password`,`misp`.`misp_system_user`.`role_id` AS `role_id`,`misp`.`misp_system_user`.`status` AS `status`,`misp`.`misp_system_user`.`reg_date` AS `reg_date` from (`laundry`.`customer` join `misp`.`misp_system_user` on((`laundry`.`customer`.`user_id` = `misp`.`misp_system_user`.`user_id`)));

#
# Source for view "view_product"
#

DROP VIEW IF EXISTS `view_product`;
CREATE VIEW `view_product` AS 
  select `laundry`.`product`.`product_id` AS `product_id`,`laundry`.`product`.`product_name` AS `product_name`,`laundry`.`product`.`type_id` AS `type_id`,`laundry`.`product`.`describe` AS `describe`,`laundry`.`product`.`original_price` AS `original_price`,`laundry`.`product`.`price` AS `price`,`laundry`.`product`.`img` AS `img`,`laundry`.`product`.`product_status` AS `product_status`,`laundry`.`product`.`update_time` AS `update_time`,`laundry`.`product`.`end_time` AS `end_time`,`laundry`.`product`.`detail_info` AS `detail_info`,`laundry`.`product_type`.`type_name` AS `type_name`,`laundry`.`product_type`.`parent_id` AS `parent_id` from (`laundry`.`product` join `laundry`.`product_type` on((`laundry`.`product`.`type_id` = `laundry`.`product_type`.`type_id`)));

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
