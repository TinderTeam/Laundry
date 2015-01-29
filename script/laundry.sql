# Host: 127.0.0.1  (Version: 5.1.70-community)
# Date: 2015-01-28 10:32:52
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

DROP DATABASE IF EXISTS `laundry`;
CREATE DATABASE `laundry` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `laundry`;

#
# Source for table "administrators"
#

DROP TABLE IF EXISTS `administrators`;
CREATE TABLE `administrators` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

#
# Data for table "administrators"
#

INSERT INTO `administrators` VALUES (98,'kkxd','476171204@qq.com',1),(126,'客服2','dd@dd.com',0),(129,'客服234','345@fue.com',1),(134,'fuego',NULL,11);

#
# Source for table "advertisement"
#

DROP TABLE IF EXISTS `advertisement`;
CREATE TABLE `advertisement` (
  `ad_id` int(11) NOT NULL AUTO_INCREMENT,
  `ad_name` varchar(50) DEFAULT NULL,
  `ad_info` varchar(255) DEFAULT NULL,
  `ad_url` varchar(50) DEFAULT NULL,
  `ad_img` varchar(50) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ad_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "advertisement"
#

INSERT INTO `advertisement` VALUES (1,'广告1',NULL,NULL,'advertisement/advertisement_img1.jpg',1),(2,'广告2',NULL,NULL,'advertisement/advertisement_img2.jpg',1),(3,'广告3',NULL,NULL,'advertisement/advertisement_img3.jpg',1);

#
# Source for table "customer"
#

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `level` varchar(20) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `customer_email` varchar(255) DEFAULT NULL,
  `customer_sex` varchar(20) DEFAULT NULL,
  `customer_img` varchar(255) DEFAULT NULL,
  `card_number` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8;

#
# Data for table "customer"
#

INSERT INTO `customer` VALUES (130,'是昵称','12344555444','slakj;klfj asl','1990-01-04',2,NULL,'昵称是',NULL,'1234445556',1,'123@11.com','男',NULL,'1234455554'),(131,'是拟撑','12312312312312','立刻决定是否看了觉得是返回','1990-01-01',3,NULL,'昵称是',NULL,'12333333333',1,'123！@qq.com','男',NULL,'123123123'),(133,NULL,'18603036649','未设置',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL);

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
  `contact_name` varchar(20) DEFAULT NULL,
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
  `handler_id` int(11) DEFAULT NULL,
  `operater_name` varchar(20) DEFAULT NULL,
  `order_type` varchar(20) DEFAULT NULL,
  `total_price` float(5,2) DEFAULT NULL,
  `total_count` int(11) DEFAULT NULL,
  `order_note` varchar(255) DEFAULT NULL,
  `order_code` varchar(50) DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `delivery_addr` varchar(255) DEFAULT NULL,
  `take_addr` varchar(255) DEFAULT NULL,
  `delivery_time` varchar(10) DEFAULT NULL,
  `take_time` varchar(100) DEFAULT NULL,
  `contact_name` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `price_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=123495 DEFAULT CHARSET=utf8;

#
# Data for table "order"
#

INSERT INTO `order` VALUES (123469,NULL,0,'2014-11-09 00:00:00',NULL,NULL,'送衣付款','已完成',0,NULL,'正常下单',24.00,2,'','93050107122351',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(123470,NULL,0,'2015-01-03 00:00:00',NULL,NULL,'送衣付款','已完成',0,NULL,'正常下单',24.00,2,'','17220107123019',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(123471,NULL,0,'2015-01-11 12:00:00',NULL,NULL,'送衣付款','已完成',0,NULL,'正常下单',24.00,2,'','1501071238183829',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(123472,NULL,0,NULL,NULL,NULL,'送衣付款','已完成',0,NULL,'正常下单',24.00,2,'','1501071241278033',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL),(123473,NULL,0,NULL,NULL,NULL,'送衣付款',NULL,0,NULL,'正常下单',50.00,2,'','1501071243516865',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL),(123474,NULL,0,NULL,NULL,NULL,'送衣付款',NULL,0,NULL,'正常下单',50.00,2,'','1501071837187468',NULL,NULL,NULL,NULL,NULL,NULL,'61',2,NULL),(123475,NULL,0,'2015-01-13 18:35:22',NULL,NULL,'送衣付款','已完成',0,NULL,'正常下单',250.00,11,'','1501131835229783',NULL,NULL,'未设置',NULL,NULL,NULL,'18603036649',1,NULL),(123476,NULL,120,'2015-01-15 11:59:24',NULL,NULL,'送衣付款','已完成',0,NULL,'正常下单',115.00,3,'','1501151159249854','18603036649',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(123477,NULL,120,'2015-01-15 11:59:28',NULL,NULL,'送衣付款','已下单',0,NULL,'正常下单',115.00,3,'','1501151159288721','18603036649',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(123478,NULL,120,'2015-01-15 11:59:51',NULL,NULL,'送衣付款','已下单',0,NULL,'正常下单',115.00,3,'','1501151159513264','18603036649',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(123479,NULL,120,'2015-01-15 12:04:43',NULL,NULL,'送衣付款','已下单',0,NULL,'正常下单',115.00,3,'','1501151204437413','18603036649',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(123480,NULL,120,'2015-01-15 12:04:51',NULL,NULL,'送衣付款','已下单',0,NULL,'正常下单',115.00,3,'','1501151204517612','18603036649',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(123481,NULL,120,'2015-01-15 12:05:32',NULL,NULL,'送衣付款','已下单',0,NULL,'正常下单',115.00,3,'','1501151205326107','18603036649',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(123482,NULL,120,'2015-01-15 12:10:36',NULL,NULL,'送衣付款','已下单',0,NULL,'正常下单',115.00,3,'','1501151210362550','18603036649',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(123483,NULL,120,'2015-01-15 12:15:23',NULL,NULL,'送衣付款','已下单',0,NULL,'正常下单',115.00,3,'','1501151215237984','18603036649',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(123484,NULL,120,'2015-01-15 12:42:43',NULL,NULL,'送衣付款','已下单',0,NULL,'正常下单',293.00,6,'','1501151242432744','18603036649',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(123485,NULL,120,'2015-01-15 12:42:46',NULL,NULL,'送衣付款','已下单',0,NULL,'正常下单',293.00,6,'','1501151242469597','18603036649',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(123486,NULL,120,'2015-01-15 12:42:52',NULL,NULL,'送衣付款','已下单',0,NULL,'正常下单',293.00,6,'','1501151242521686','18603036649',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(123487,NULL,120,'2015-01-15 12:42:53',NULL,NULL,'送衣付款','已下单',0,NULL,'正常下单',293.00,6,'','1501151242532263','18603036649',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(123488,NULL,120,'2015-01-15 12:42:53',NULL,NULL,'送衣付款','已下单',0,NULL,'正常下单',293.00,6,'','1501151242537849','18603036649',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(123489,NULL,120,'2015-01-15 12:42:53',NULL,NULL,'送衣付款','已下单',0,NULL,'正常下单',293.00,6,'','1501151242538833','18603036649',NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),(123490,NULL,120,'2015-01-16 23:34:27',NULL,NULL,'送衣付款','已下单',0,NULL,'正常下单',68.00,6,'','1501162334279321','18603036649',NULL,'未设置急急急计划',NULL,NULL,'刚刚好你刚刚好玩儿','18603036649',1,NULL),(123491,NULL,120,'2015-01-16 23:34:32',NULL,NULL,'送衣付款','已下单',0,NULL,'正常下单',68.00,6,'','1501162334328135','18603036649',NULL,'未设置急急急计划',NULL,NULL,'刚刚好你刚刚好玩儿','18603036649',1,NULL),(123492,NULL,120,'2015-01-16 23:42:02',NULL,NULL,'送衣付款','已下单',0,NULL,'正常下单',350.00,2,'','1501162342024235','18603036649',NULL,'未设置急急急计划',NULL,NULL,'刚刚好你刚刚好玩儿','18603036649',1,NULL),(123493,NULL,120,'2015-01-16 23:42:04',NULL,NULL,'送衣付款','已下单',0,NULL,'正常下单',350.00,2,'','1501162342044436','18603036649',NULL,'未设置急急急计划',NULL,NULL,'刚刚好你刚刚好玩儿','18603036649',1,NULL),(123494,NULL,120,'2015-01-19 23:39:27',NULL,NULL,'送衣付款','已下单',0,NULL,'正常下单',480.00,5,'','1501192339278606','18603036649','未设置急急急计划','未设置急急急计划',NULL,NULL,'刚刚好你刚刚好玩儿','18603036649',1,NULL);

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
  `product_describe` varchar(255) DEFAULT NULL,
  `original_price` float(5,2) DEFAULT NULL,
  `current_price` float(5,2) DEFAULT NULL,
  `product_img` varchar(50) DEFAULT NULL,
  `product_status` varchar(20) DEFAULT NULL,
  `product_update_time` datetime DEFAULT NULL,
  `product_limit_time` datetime DEFAULT NULL,
  `detail_info` varchar(255) DEFAULT NULL,
  `price_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`order_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;

#
# Data for table "order_detail"
#

INSERT INTO `order_detail` VALUES (3,123469,37,1,'西裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,123469,38,1,'休闲裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,123470,37,1,'西裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,123470,38,1,'休闲裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,123471,37,1,'西裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,123471,38,1,'休闲裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,123472,37,1,'西裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,123472,38,1,'休闲裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,123473,30,1,'棉衣/羽绒服（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,123473,31,1,'大衣（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,123474,30,1,'棉衣/羽绒服（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,123474,31,1,'大衣（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,123475,33,1,'连衣裙',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,123475,32,1,'风衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,123475,31,4,'大衣（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,123475,36,4,'夹克',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,123475,35,1,'运动衣/卫衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,123476,76,1,'大衣（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(21,123476,78,1,'礼服',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(22,123476,80,1,'风衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(23,123477,76,1,'大衣（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(24,123477,78,1,'礼服',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(25,123477,80,1,'风衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(26,123478,76,1,'大衣（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(27,123478,78,1,'礼服',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(28,123478,80,1,'风衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(29,123479,76,1,'大衣（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(30,123479,78,1,'礼服',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(31,123479,80,1,'风衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(32,123480,76,1,'大衣（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(33,123480,78,1,'礼服',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(34,123480,80,1,'风衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(35,123481,76,1,'大衣（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(36,123481,78,1,'礼服',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(37,123481,80,1,'风衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(38,123482,76,1,'大衣（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(39,123482,78,1,'礼服',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(40,123482,80,1,'风衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(41,123483,76,1,'大衣（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(42,123483,78,1,'礼服',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(43,123484,76,1,'大衣（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(44,123484,78,1,'礼服',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(45,123484,77,1,'棉衣/羽绒服',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(46,123484,79,1,'大衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(47,123484,81,1,'新大衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(48,123484,80,1,'风衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(49,123485,76,1,'大衣（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(50,123485,78,1,'礼服',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(51,123485,77,1,'棉衣/羽绒服',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(52,123485,79,1,'大衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(53,123485,81,1,'新大衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(54,123485,80,1,'风衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(55,123486,76,1,'大衣（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(56,123486,78,1,'礼服',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(57,123486,77,1,'棉衣/羽绒服',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(58,123486,79,1,'大衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(59,123486,81,1,'新大衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60,123486,80,1,'风衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(61,123487,76,1,'大衣（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(62,123487,78,1,'礼服',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(63,123487,77,1,'棉衣/羽绒服',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(64,123487,79,1,'大衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(65,123487,81,1,'新大衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(66,123487,80,1,'风衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(67,123488,76,1,'大衣（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(68,123488,78,1,'礼服',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(69,123488,77,1,'棉衣/羽绒服',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70,123488,79,1,'大衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(71,123488,81,1,'新大衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(72,123488,80,1,'风衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(73,123489,76,1,'大衣（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(74,123489,78,1,'礼服',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(75,123489,77,1,'棉衣/羽绒服',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(76,123489,79,1,'大衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(77,123489,81,1,'新大衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(78,123489,80,1,'风衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(79,123490,130,3,'休闲裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80,123490,132,1,'中裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(81,123490,131,1,'西裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(82,123490,134,1,'沙滩裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(83,123491,130,3,'休闲裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(84,123491,132,1,'中裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(85,123491,131,1,'西裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(86,123491,134,1,'沙滩裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(87,123492,100,1,'皮草（兔毛/短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(88,123492,101,1,'皮草（兔毛/长）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(89,123493,100,1,'皮草（兔毛/短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90,123493,101,1,'皮草（兔毛/长）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(91,123494,139,1,'运动鞋',NULL,NULL,0.00,20.00,NULL,NULL,NULL,NULL,NULL,NULL),(92,123494,143,1,'卡包',NULL,NULL,0.00,20.00,NULL,NULL,NULL,NULL,NULL,NULL),(93,123494,145,1,'双肩包',NULL,NULL,0.00,40.00,NULL,NULL,NULL,NULL,NULL,NULL),(94,123494,151,1,'汽车坐垫（平毛垫）',NULL,NULL,0.00,240.00,NULL,NULL,NULL,NULL,NULL,NULL),(95,123494,153,1,'汽车坐垫（养生坐垫）',NULL,NULL,0.00,160.00,NULL,NULL,NULL,NULL,NULL,NULL);

#
# Source for table "product"
#

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `describe` varchar(255) DEFAULT NULL,
  `original_price` float(5,2) DEFAULT NULL,
  `price` float(5,2) DEFAULT NULL,
  `img` varchar(50) DEFAULT NULL,
  `product_status` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `detail_info` varchar(2000) DEFAULT NULL,
  `price_type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8;

#
# Data for table "product"
#

INSERT INTO `product` VALUES (82,'床单（薄）',6,1,'',NULL,0.01,'54b8db9d8c5f8.png',NULL,NULL,NULL,NULL,'固定'),(83,'床单（厚）',6,1,'',NULL,0.01,'54b8dbac4244f.png',NULL,NULL,NULL,NULL,'固定'),(84,'床罩（薄）',6,1,'',NULL,10.00,'54b8dbc5cda54.png',NULL,NULL,NULL,NULL,'固定'),(85,'床罩（厚）',6,1,'',NULL,20.00,'54b8dbd2147aa.png',NULL,NULL,NULL,NULL,'固定'),(86,'挂毯/平方',6,1,'',NULL,10.00,'54b8ad3327dbb.png',NULL,NULL,NULL,NULL,'固定'),(87,'窗纱/平方',6,1,'',NULL,10.00,'54b8ad4588d0a.png',NULL,NULL,NULL,NULL,'固定'),(88,'靠垫（中）',6,1,'',NULL,15.00,'54b8ad64d08ce.png',NULL,NULL,NULL,NULL,'固定'),(89,'靠垫（大）',6,1,'',NULL,20.00,'54b8ad74f0aed.png',NULL,NULL,NULL,NULL,'固定'),(90,'毛毯（大）',6,1,'',NULL,80.00,'54b8ad8f85554.png',NULL,NULL,NULL,NULL,'固定'),(91,'羊毛毯',6,1,'',NULL,80.00,'54b8ad9d40298.png',NULL,NULL,NULL,NULL,'固定'),(92,'毛绒玩具',6,1,'',NULL,30.00,'54b8adb26c906.png',NULL,NULL,NULL,NULL,'固定'),(93,'毛绒玩具',6,1,'',NULL,50.00,'54b8adc40fa48.png',NULL,NULL,NULL,NULL,'固定'),(94,'帽子',6,1,'',NULL,10.00,'54b8add68fef0.png',NULL,NULL,NULL,NULL,'固定'),(95,'披肩',6,1,'',NULL,10.00,'54b8ade1e0d16.png',NULL,NULL,NULL,NULL,'固定'),(96,'黑皮大衣（短）',3,1,'',NULL,100.00,'54b910f9a6fef.png',NULL,NULL,NULL,NULL,'固定'),(97,'黑皮大衣（中）',3,1,'',NULL,150.00,'54b91115b1888.png',NULL,NULL,NULL,NULL,'固定'),(98,'彩皮大衣（中）',3,1,'',NULL,150.00,'54b91131bef04.png',NULL,NULL,NULL,NULL,'固定'),(99,'彩皮大衣（长）',3,1,'',NULL,200.00,'54b91144e996c.png',NULL,NULL,NULL,NULL,'固定'),(100,'皮草（兔毛/短）',3,1,'',NULL,150.00,'54b911634a519.png',NULL,NULL,NULL,NULL,'固定'),(101,'皮草（兔毛/长）',3,1,'',NULL,200.00,'54b9118186ae3.png',NULL,NULL,NULL,NULL,'固定'),(102,'黑色皮裤',3,1,'',NULL,80.00,'54b911c148d81.png',NULL,NULL,NULL,NULL,'固定'),(103,'彩色皮裤',3,1,'',NULL,80.00,'54b9120f57c05.png',NULL,NULL,NULL,NULL,'固定'),(104,'皮上衣',3,1,'',NULL,100.00,'54b912308a8d1.png',NULL,NULL,NULL,NULL,'固定'),(105,'PU装',3,1,'',NULL,30.00,'54b9123e8c0c0.png',NULL,NULL,NULL,NULL,'固定'),(106,'黑皮大衣（长）',3,1,'',NULL,200.00,'54b912650ee30.png',NULL,NULL,NULL,NULL,'固定'),(107,'彩皮大衣（短）',3,1,'',NULL,100.00,'54b91278d2ebf.png',NULL,NULL,NULL,NULL,'固定'),(108,'皮风衣',3,1,'',NULL,150.00,'54b9129662f04.png',NULL,NULL,NULL,NULL,'固定'),(109,'皮夹克',3,1,'',NULL,100.00,'54b912ab89a6b.png',NULL,NULL,NULL,NULL,'固定'),(110,'披马甲',3,1,'',NULL,80.00,'54b912c6c18d5.png',NULL,NULL,NULL,NULL,'固定'),(111,'皮毛领',3,1,'',NULL,20.00,'54b912e6cbc5d.png',NULL,NULL,NULL,NULL,'固定'),(112,'皮手套',3,1,'',NULL,20.00,'54b912fecb8fe.png',NULL,NULL,NULL,NULL,'固定'),(113,'棉衣/羽绒服（短）',1,1,'',NULL,25.00,'54b91b0d03d22.png',NULL,NULL,NULL,NULL,'固定'),(114,'棉衣/羽绒服（长）',1,1,'',NULL,30.00,'54b91b20cff1d.png',NULL,NULL,NULL,NULL,'固定'),(115,'大衣（短）',1,1,'',NULL,25.00,'54b91b3d8610f.png',NULL,NULL,NULL,NULL,'固定'),(116,'大衣（长）',1,1,'',NULL,30.00,'54b91b4c618b3.png',NULL,NULL,NULL,NULL,'固定'),(117,'牛仔衣',1,1,'',NULL,15.00,'54b91b716a558.png',NULL,NULL,NULL,NULL,'固定'),(118,'冲锋衣',1,1,'',NULL,25.00,'54b91b7fc3a58.png',NULL,NULL,NULL,NULL,'固定'),(119,'风衣',1,1,'',NULL,30.00,'54b91b952630c.png',NULL,NULL,NULL,NULL,'固定'),(120,'西装',1,1,'',NULL,18.00,'54b91ba38d43c.png',NULL,NULL,NULL,NULL,'固定'),(121,'夹克',1,1,'',NULL,20.00,'54b91bbc11d67.png',NULL,NULL,NULL,NULL,'固定'),(122,'开衫',1,1,'',NULL,15.00,'54b91bc753042.png',NULL,NULL,NULL,NULL,'固定'),(123,'羊绒衫',1,1,'',NULL,20.00,'54b91be1aa3fd.png',NULL,NULL,NULL,NULL,'固定'),(124,'羊毛衫',1,1,'',NULL,15.00,'54b91beece666.png',NULL,NULL,NULL,NULL,'固定'),(125,'运动衣/卫衣',1,1,'',NULL,20.00,'54b91c144236f.png',NULL,NULL,NULL,NULL,'固定'),(126,'T恤',1,1,'',NULL,10.00,'54b91c3f4e9f3.png',NULL,NULL,NULL,NULL,'固定'),(127,'短裙（薄）',2,1,'',NULL,8.00,'54b91e98b4415.png',NULL,NULL,NULL,NULL,'固定'),(128,'短裙（厚）',2,1,'',NULL,10.00,'54b91eb5e1e96.png',NULL,NULL,NULL,NULL,'固定'),(129,'热裤',2,1,'',NULL,8.00,'54b91ec78241b.png',NULL,NULL,NULL,NULL,'固定'),(130,'休闲裤',2,1,'',NULL,12.00,'54b91ed7237fa.png',NULL,NULL,NULL,NULL,'固定'),(131,'西裤',2,1,'',NULL,12.00,'54b91eecc88e2.png',NULL,NULL,NULL,NULL,'固定'),(132,'中裤',2,1,'',NULL,10.00,'54b91efae7efe.png',NULL,NULL,NULL,NULL,'固定'),(133,'牛仔裤',2,1,'',NULL,10.00,'54b91f0ea0f7b.png',NULL,NULL,NULL,NULL,'固定'),(134,'沙滩裤',2,1,'',NULL,10.00,'54b91f1b72a8d.png',NULL,NULL,NULL,NULL,'固定'),(135,'睡裤',2,1,'',NULL,12.00,'54b91f30bb643.png',NULL,NULL,NULL,NULL,'固定'),(136,'运动裤',2,1,'',NULL,12.00,'54b91f3d47687.png',NULL,NULL,NULL,NULL,'固定'),(137,'铅笔裤',2,1,'',NULL,12.00,'54b91f4fee8e0.png',NULL,NULL,NULL,NULL,'固定'),(138,'打底裤',2,1,'',NULL,10.00,'54b91f63bdcf4.png',NULL,NULL,NULL,NULL,'固定'),(139,'运动鞋',4,1,'',NULL,20.00,'54b922c3f06f8.png',NULL,NULL,NULL,NULL,'固定'),(140,'雪地鞋',4,1,'',NULL,30.00,'54b922d5b8b4a.png',NULL,NULL,NULL,NULL,'固定'),(141,'户外鞋',4,1,'',NULL,20.00,'54b922ece3bf2.png',NULL,NULL,NULL,NULL,'固定'),(142,'单肩包',4,1,'',NULL,30.00,'54b922ff09dd7.png',NULL,NULL,NULL,NULL,'固定'),(143,'卡包',4,1,'',NULL,20.00,'54b9231a64b46.png',NULL,NULL,NULL,NULL,'固定'),(144,'皮鞋',4,1,'',NULL,50.00,'54b923282c3a4.png',NULL,NULL,NULL,NULL,'固定'),(145,'双肩包',4,1,'',NULL,40.00,'54b9233da7948.png',NULL,NULL,NULL,NULL,'固定'),(146,'钱包',4,1,'',NULL,30.00,'54b9234a57ecc.png',NULL,NULL,NULL,NULL,'固定'),(147,'手提包',4,1,'',NULL,40.00,'54b9235f51905.png',NULL,NULL,NULL,NULL,'固定'),(148,'拉杆箱',4,1,'',NULL,60.00,'54b9237571562.png',NULL,NULL,NULL,NULL,'固定'),(149,'汽车坐垫（珠子垫）',7,1,'',NULL,360.00,'54b92454f031d.png',NULL,NULL,NULL,NULL,'固定'),(150,'汽车坐垫（长毛垫）',7,1,'',NULL,280.00,'54b9248bed521.png',NULL,NULL,NULL,NULL,'固定'),(151,'汽车坐垫（平毛垫）',7,1,'',NULL,240.00,'54b924b7c4b68.png',NULL,NULL,NULL,NULL,'固定'),(152,'汽车坐垫（短毛垫）',7,1,'',NULL,260.00,'54b924cad219c.png',NULL,NULL,NULL,NULL,'固定'),(153,'汽车坐垫（养生坐垫）',7,1,'',NULL,160.00,'54b924ec24f6a.png',NULL,NULL,NULL,NULL,'固定'),(154,'汽车坐垫（冰丝、布套）',7,1,'',NULL,100.00,'54b9253bc6621.png',NULL,NULL,NULL,NULL,'固定'),(155,'测试产品',2,NULL,'123123',NULL,0.00,'54c76bbaea197.png',NULL,NULL,NULL,NULL,'面议');

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
  `parent_type_id` int(11) DEFAULT NULL,
  `type_img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

#
# Data for table "product_type"
#

INSERT INTO `product_type` VALUES (1,'上装类',0,NULL),(2,'下装类',0,NULL),(3,'毛皮服饰类',0,NULL),(4,'箱包鞋类',0,NULL),(5,'奢侈品牌类',0,NULL),(6,'居家类',0,NULL),(7,'汽车配饰类',0,NULL),(8,'染色、救治',0,NULL);

#
# Source for view "view_administrators"
#

DROP VIEW IF EXISTS `view_administrators`;
CREATE VIEW `view_administrators` AS 
  select `laundry`.`administrators`.`user_id` AS `user_id`,`laundry`.`administrators`.`user_name` AS `user_name`,`laundry`.`administrators`.`email` AS `email`,`laundry`.`administrators`.`company_id` AS `company_id`,`misp`.`misp_system_user`.`role_id` AS `role_id`,`misp`.`misp_system_role`.`role_name` AS `role_name` from ((`laundry`.`administrators` join `misp`.`misp_system_user` on((`misp`.`misp_system_user`.`user_id` = `laundry`.`administrators`.`user_id`))) join `misp`.`misp_system_role` on((`misp`.`misp_system_role`.`role_id` = `misp`.`misp_system_user`.`role_id`)));

#
# Source for view "view_customer"
#

DROP VIEW IF EXISTS `view_customer`;
CREATE VIEW `view_customer` AS 
  select `laundry`.`customer`.`user_id` AS `user_id`,`laundry`.`customer`.`customer_name` AS `customer_name`,`laundry`.`customer`.`phone` AS `phone`,`laundry`.`customer`.`addr` AS `addr`,`laundry`.`customer`.`birthday` AS `birthday`,`laundry`.`customer`.`score` AS `score`,`laundry`.`customer`.`level` AS `level`,`laundry`.`customer`.`nickname` AS `nickname`,`laundry`.`customer`.`status` AS `status`,`laundry`.`customer`.`user_name` AS `user_name`,`laundry`.`customer`.`company_id` AS `company_id`,`laundry`.`customer`.`customer_email` AS `customer_email`,`laundry`.`customer`.`customer_sex` AS `customer_sex`,`laundry`.`customer`.`customer_img` AS `customer_img`,`laundry`.`customer`.`card_number` AS `card_number`,`misp`.`misp_system_user`.`role_id` AS `role_id`,`misp`.`misp_system_role`.`role_name` AS `role_name` from ((`laundry`.`customer` join `misp`.`misp_system_user` on((`misp`.`misp_system_user`.`user_id` = `laundry`.`customer`.`user_id`))) join `misp`.`misp_system_role` on((`misp`.`misp_system_role`.`role_id` = `misp`.`misp_system_user`.`role_id`)));

#
# Source for view "view_product"
#

DROP VIEW IF EXISTS `view_product`;
CREATE VIEW `view_product` AS 
  select `laundry`.`product`.`product_id` AS `product_id`,`laundry`.`product`.`product_name` AS `product_name`,`laundry`.`product`.`type_id` AS `type_id`,`laundry`.`product`.`company_id` AS `company_id`,`laundry`.`product`.`describe` AS `describe`,`laundry`.`product`.`original_price` AS `original_price`,`laundry`.`product`.`price` AS `price`,`laundry`.`product`.`img` AS `img`,`laundry`.`product`.`product_status` AS `product_status`,`laundry`.`product`.`update_time` AS `update_time`,`laundry`.`product`.`end_time` AS `end_time`,`laundry`.`product`.`detail_info` AS `detail_info`,`laundry`.`product`.`price_type` AS `price_type`,`laundry`.`product_type`.`type_name` AS `type_name`,`laundry`.`product_type`.`parent_type_id` AS `parent_type_id` from (`laundry`.`product` join `laundry`.`product_type` on((`laundry`.`product`.`type_id` = `laundry`.`product_type`.`type_id`)));

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
