# Host: 127.0.0.1  (Version: 5.1.70-community)
# Date: 2015-01-13 21:11:35
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
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;

#
# Data for table "administrators"
#

INSERT INTO `administrators` VALUES (98,'user1','123@123.com',1),(99,'user2','seru@123.com',2),(108,'fuego','market@fuego.cn',10);

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
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

#
# Data for table "customer"
#

INSERT INTO `customer` VALUES (61,'测试用户','18603036649','深圳市龙华新区工业路192号','1990-08-23',0,NULL,'昵称',NULL,'18603036649',1),(63,NULL,'18603036649','未设置','1988-08-08',NULL,NULL,NULL,NULL,NULL,1),(79,'士大夫','13322887744','士大夫士大夫是的复古宫廷','2015-01-12',0,NULL,'ask大家',NULL,'13322887744',NULL),(80,'与人品','13366623344','士大夫岁的法国','2015-01-12',0,NULL,'失联客机',NULL,'13366623344',1),(81,'斯蒂芬','18988889999','稍等帮您弄好','1990-05-24',0,NULL,'卢卡斯吉多',NULL,'18988889999',1),(90,'','sdfsdf11','','1990-01-01',0,NULL,'',NULL,'sdfsdf11',0);

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
  `customer_name` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=123476 DEFAULT CHARSET=utf8;

#
# Data for table "order"
#

INSERT INTO `order` VALUES (123469,NULL,0,'2014-11-09 00:00:00',NULL,NULL,'送衣付款','已完成',0,NULL,'正常下单',24.00,2,'','93050107122351',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(123470,NULL,0,'2015-01-03 00:00:00',NULL,NULL,'送衣付款','已完成',0,NULL,'正常下单',24.00,2,'','17220107123019',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(123471,NULL,0,'2015-01-11 12:00:00',NULL,NULL,'送衣付款','已完成',0,NULL,'正常下单',24.00,2,'','1501071238183829',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(123472,NULL,0,NULL,NULL,NULL,'送衣付款','已完成',0,NULL,'正常下单',24.00,2,'','1501071241278033',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2),(123473,NULL,0,NULL,NULL,NULL,'送衣付款',NULL,0,NULL,'正常下单',50.00,2,'','1501071243516865',NULL,NULL,NULL,NULL,NULL,NULL,NULL,2),(123474,NULL,0,NULL,NULL,NULL,'送衣付款',NULL,0,NULL,'正常下单',50.00,2,'','1501071837187468',NULL,NULL,NULL,NULL,NULL,NULL,'61',2),(123475,NULL,0,'2015-01-13 18:35:22',NULL,NULL,'送衣付款','已下单',0,NULL,'正常下单',250.00,11,'','1501131835229783',NULL,NULL,'未设置',NULL,NULL,NULL,'18603036649',1);

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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

#
# Data for table "order_detail"
#

INSERT INTO `order_detail` VALUES (3,123469,37,1,'西裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,123469,38,1,'休闲裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,123470,37,1,'西裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,123470,38,1,'休闲裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,123471,37,1,'西裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,123471,38,1,'休闲裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,123472,37,1,'西裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,123472,38,1,'休闲裤',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,123473,30,1,'棉衣/羽绒服（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,123473,31,1,'大衣（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,123474,30,1,'棉衣/羽绒服（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,123474,31,1,'大衣（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,123475,33,1,'连衣裙',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,123475,32,1,'风衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,123475,31,4,'大衣（短）',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,123475,36,4,'夹克',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,123475,35,1,'运动衣/卫衣',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;

#
# Data for table "product"
#

INSERT INTO `product` VALUES (30,'棉衣/羽绒服（短）',1,1,'',NULL,25.00,'54a90d904b50d.png',NULL,NULL,NULL,NULL,NULL),(31,'大衣（短）',1,1,'',NULL,25.00,'54a90dcff2f19.png',NULL,NULL,NULL,NULL,NULL),(32,'风衣',1,1,'',NULL,30.00,'54a90dee1307c.png',NULL,NULL,NULL,NULL,NULL),(33,'连衣裙',1,1,'',NULL,20.00,'54a90e0839edc.png',NULL,NULL,NULL,NULL,NULL),(34,'冲风衣',1,1,'',NULL,25.00,'54a90e6143fae.png',NULL,NULL,NULL,NULL,NULL),(35,'运动衣/卫衣',1,1,'',NULL,20.00,'54a90e8593aca.png',NULL,NULL,NULL,NULL,NULL),(36,'夹克',1,1,'',NULL,20.00,'54a90ea14f332.png',NULL,NULL,NULL,NULL,NULL),(37,'西裤',2,1,'',NULL,12.00,'54a90edc18cbc.png',NULL,NULL,NULL,NULL,NULL),(38,'休闲裤',2,1,'',NULL,12.00,'54a90eeee4685.png',NULL,NULL,NULL,NULL,NULL),(39,'牛仔裤',2,1,'',NULL,10.00,'54a90f02de82a.png',NULL,NULL,NULL,NULL,NULL),(40,'中裤',2,1,'',NULL,10.00,'54a90f1551f2a.png',NULL,NULL,NULL,NULL,NULL),(41,'睡裤',2,1,'',NULL,12.00,'54a90f3f673f6.png',NULL,NULL,NULL,NULL,NULL),(42,'沙滩裤',2,1,'',NULL,10.00,'54a90f8b6308c.png',NULL,NULL,NULL,NULL,NULL),(43,'铅笔裤',2,1,'',NULL,12.00,'54a90fa3c4cb1.png',NULL,NULL,NULL,NULL,NULL),(44,'床罩（薄）',3,1,'',NULL,10.00,'54a91003022cd.png',NULL,NULL,NULL,NULL,NULL),(45,'床罩（厚）',3,1,'',NULL,20.00,'54a91019c4a66.png',NULL,NULL,NULL,NULL,NULL),(46,'床单（薄）',3,1,'',NULL,10.00,'54a9103461011.png',NULL,NULL,NULL,NULL,NULL),(47,'床单（厚）',3,1,'',NULL,20.00,'54a9104424196.png',NULL,NULL,NULL,NULL,NULL),(48,'枕套（一对）',3,1,'',NULL,8.00,'54a9108472a27.png',NULL,NULL,NULL,NULL,NULL),(49,'地毯/平方',3,1,'',NULL,30.00,'54a9109b14a2a.png',NULL,NULL,NULL,NULL,NULL),(50,'挂毯/平方',3,1,'',NULL,28.00,'54a910b6be833.png',NULL,NULL,NULL,NULL,NULL),(51,'黑色皮大衣（短）',4,1,'',NULL,100.00,'54a910f556e59.png',NULL,NULL,NULL,NULL,NULL),(52,'黑色皮大衣（中）',4,1,'',NULL,150.00,'54a9110b0896b.png',NULL,NULL,NULL,NULL,NULL),(53,'黑色皮大衣（长）',4,1,'',NULL,200.00,'54a9111cdbab3.png',NULL,NULL,NULL,NULL,NULL),(54,'彩色皮大衣（短）',4,1,'',NULL,100.00,'54a9114004ab3.png',NULL,NULL,NULL,NULL,NULL),(55,'彩色皮大衣（中）',4,1,'',NULL,150.00,'54a911534f1c6.png',NULL,NULL,NULL,NULL,NULL),(56,'彩色皮大衣（长）',4,1,'',NULL,200.00,'54a9116297c2a.png',NULL,NULL,NULL,NULL,NULL),(57,'皮风衣',4,1,'',NULL,150.00,'54a9117508947.png',NULL,NULL,NULL,NULL,NULL),(58,'汽车坐垫（羊毛垫/长毛）',5,1,'',NULL,280.00,'54a911bcc1686.png',NULL,NULL,NULL,NULL,NULL),(59,'汽车坐垫（养生坐垫）',5,1,'',NULL,160.00,'54a911d43efd3.png',NULL,NULL,NULL,NULL,NULL),(60,'汽车坐垫（羊毛垫/平毛）',5,1,'',NULL,240.00,'54a911fc1e1f4.png',NULL,NULL,NULL,NULL,NULL),(61,'汽车坐垫（羊毛垫/短毛）',5,1,'',NULL,260.00,'54a9122e06f9c.png',NULL,NULL,NULL,NULL,NULL),(62,'汽车坐垫（珠子垫）',5,1,'',NULL,360.00,'54a9125487533.png',NULL,NULL,NULL,NULL,NULL),(63,'汽车坐垫（冰丝/亚麻）',5,1,'',NULL,100.00,'54a91276a7a53.png',NULL,NULL,NULL,NULL,NULL),(64,'运动鞋',6,1,'',NULL,20.00,'54a9129f4f3dc.png',NULL,NULL,NULL,NULL,NULL),(65,'雪地鞋',6,1,'',NULL,30.00,'54a912c09448f.png',NULL,NULL,NULL,NULL,NULL),(66,'户外鞋',6,1,'',NULL,20.00,'54a912cfbbdaa.png',NULL,NULL,NULL,NULL,NULL),(67,'单肩包',6,1,'',NULL,30.00,'54a912e2c427a.png',NULL,NULL,NULL,NULL,NULL),(68,'双肩包',6,1,'',NULL,40.00,'54a912f98ca01.png',NULL,NULL,NULL,NULL,NULL),(69,'钱包',6,1,'',NULL,30.00,'54a9133636ee0.png',NULL,NULL,NULL,NULL,NULL),(70,'卡包',6,1,'',NULL,20.00,'54a91345e358c.png',NULL,NULL,NULL,NULL,NULL),(71,'奢侈品护理',7,1,'',NULL,0.00,'54a9159a6c3eb.png',NULL,NULL,NULL,NULL,NULL),(72,'衣服染色',7,1,'',NULL,0.00,'54a915b147447.png',NULL,NULL,NULL,NULL,NULL),(73,'皮衣改色',7,1,'',NULL,0.00,'54a915c1f2831.png',NULL,NULL,NULL,NULL,NULL),(74,'皮衣救治',7,1,'',NULL,0.00,'54a915d4d7102.png',NULL,NULL,NULL,NULL,NULL),(75,'新测试产品',3,NULL,'',NULL,122.22,'54af85497963b.png',NULL,NULL,NULL,NULL,NULL);

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

INSERT INTO `product_type` VALUES (1,'上装类',0,NULL),(2,'下装类',0,NULL),(3,'毛皮服饰类',0,NULL),(4,'箱包鞋类',0,NULL),(5,'奢侈品牌类',0,NULL),(6,'居家类',0,NULL),(7,'汽车配饰类',0,NULL),(8,'染色、改色、救治',0,NULL);

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
