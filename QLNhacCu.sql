CREATE DATABASE  IF NOT EXISTS `QLNhacCu` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `QLNhacCu`;
-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: 127.0.0.1    Database: QLNhacCu
-- ------------------------------------------------------
-- Server version	5.7.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Categories`
--

DROP TABLE IF EXISTS `Categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Categories` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Categories`
--

LOCK TABLES `Categories` WRITE;
/*!40000 ALTER TABLE `Categories` DISABLE KEYS */;
INSERT INTO `Categories` VALUES (1,'Electric Guitar'),(2,'Acoustic Guitar'),(3,'Piano'),(4,'Electric Piano'),(5,'Keyboard/Synthesizer'),(6,'Effect'),(7,'Accessory'),(8,'Drum'),(9,'Bass');
/*!40000 ALTER TABLE `Categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CollectionDetails`
--

DROP TABLE IF EXISTS `CollectionDetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CollectionDetails` (
  `item_id` int(11) NOT NULL,
  `collection_id` int(11) NOT NULL,
  PRIMARY KEY (`item_id`,`collection_id`),
  KEY `fk_CollectionDetails_Collections1_idx` (`collection_id`),
  KEY `fk_CollectionDetails_Items1_idx` (`item_id`),
  CONSTRAINT `fk_CollectionDetails_Collections1` FOREIGN KEY (`collection_id`) REFERENCES `Collections` (`collection_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_CollectionDetails_Items1` FOREIGN KEY (`item_id`) REFERENCES `Items` (`item_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CollectionDetails`
--

LOCK TABLES `CollectionDetails` WRITE;
/*!40000 ALTER TABLE `CollectionDetails` DISABLE KEYS */;
INSERT INTO `CollectionDetails` VALUES (1,1),(4,1),(2,2),(7,2),(3,3),(1,4),(3,4),(4,4),(5,4),(6,4),(7,4),(2,5),(3,5),(4,5),(5,5),(11,5);
/*!40000 ALTER TABLE `CollectionDetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Collections`
--

DROP TABLE IF EXISTS `Collections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Collections` (
  `collection_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`collection_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Collections`
--

LOCK TABLES `Collections` WRITE;
/*!40000 ALTER TABLE `Collections` DISABLE KEYS */;
INSERT INTO `Collections` VALUES (1,'Used box'),(2,'Brand new'),(3,'Outlet'),(4,'Special Price'),(5,'Hot item');
/*!40000 ALTER TABLE `Collections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Items`
--

DROP TABLE IF EXISTS `Items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Items` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `brandname` varchar(30) DEFAULT NULL,
  `color` varchar(10) DEFAULT NULL,
  `shape` varchar(30) DEFAULT NULL,
  `images` varchar(45) DEFAULT NULL,
  `info` text,
  `price` decimal(15,2) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  `visible` tinyint(1) NOT NULL,
  PRIMARY KEY (`item_id`),
  KEY `fk_Items_Categories1_idx` (`category_id`),
  KEY `fk_Items_Brand_idx` (`brandname`),
  KEY `fk_Items_Shape_idx` (`shape`),
  KEY `fk_Items_Color_idx` (`color`),
  CONSTRAINT `fk_Items_Categories1` FOREIGN KEY (`category_id`) REFERENCES `Categories` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Items`
--

LOCK TABLES `Items` WRITE;
/*!40000 ALTER TABLE `Items` DISABLE KEYS */;
INSERT INTO `Items` VALUES (1,'Gibson Les Paul Standard USA','Gibson','Red','Les Paul','1','Gibson Lespaul Standard',20000000.00,0,1,1),(2,'Fender Stratocaster Custom USA','Fender','Sunburt','Stratocaster','2','Fender Stratocaster Custom',30000000.00,2,1,1),(3,'Ibanez Prestige S6570','Ibanez','Green','Super Strat','3','Ibanez Prestige S6570',34250000.00,11,1,1),(4,'Gibson Acoustic Limited Country','Gibson','Nature','D Style','4','Gibson Acoustic Limited Country',24000000.00,3,2,1),(5,'Fender USA Pro Jazz Bass','Fender','Sunburt','Stratocaster','5','Fender USA Pro Jazz Bass',50000000.00,12,9,1),(6,'Kawaii MP-5 Stage Studio','Roland','Black','Other','6','Kawaii MP-5 Stage Studio',30000000.00,2,4,1),(7,'Zoom G5N Multieffect','Zoom','Black','Other','7','Zoom G5N Multieffect',6000000.00,4,6,1),(8,'NYXL Bass Regular Light','D’Addario','Other','Other','8','D’Addario NYXL45130 NYXL Bass Regular Light 5-String 45-130',1200000.00,100,7,1),(9,'YAMAHA  / KX5 REMOTE KEYBOARD','Yamaha','Black','Other','9','YAMAHA  / KX5 REMOTE KEYBOARD',10000000.00,1,5,1),(10,'Gretsch / GB-4167BBR Brooklyn Series ','Gretsch','Nature','Other','10','Gretsch / GB-4167BBR Brooklyn Series ',24000000.00,3,8,1),(11,'Ibanez / J-Custom RG9670Z Dark Misty Night','Ibanez','Gray','Super Strat','11','Ibanez / J-Custom RG9670Z Dark Misty Night',98000000.00,1,1,1);
/*!40000 ALTER TABLE `Items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderDetails`
--

DROP TABLE IF EXISTS `OrderDetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderDetails` (
  `order_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT '0',
  PRIMARY KEY (`order_id`,`item_id`),
  KEY `FK_OrderDetails_ItemId_idx` (`item_id`),
  CONSTRAINT `FK_OrderDetails_ItemId` FOREIGN KEY (`item_id`) REFERENCES `Items` (`item_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_OrderDetails_OrderId` FOREIGN KEY (`order_id`) REFERENCES `Orders` (`order_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderDetails`
--

LOCK TABLES `OrderDetails` WRITE;
/*!40000 ALTER TABLE `OrderDetails` DISABLE KEYS */;
INSERT INTO `OrderDetails` VALUES (3,1,1),(3,3,1),(3,5,1),(4,1,1),(4,3,3),(5,1,1),(5,3,3),(6,1,1),(6,3,3),(7,1,1),(8,1,1),(8,3,1),(9,1,1),(9,2,2),(9,3,1),(10,1,1),(10,2,3),(10,3,1),(11,2,2),(11,4,1),(11,6,1),(12,1,1),(12,2,1),(12,3,1),(12,4,1),(12,5,1),(13,4,1),(14,1,1),(14,2,1),(15,1,6),(15,4,1);
/*!40000 ALTER TABLE `OrderDetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER tg_updatenumber
BEFORE INSERT
ON OrderDetails 
FOR EACH ROW
BEGIN
	DECLARE itemnumber INT(4);
	SELECT number INTO itemnumber From  QLNhacCu.Items WHERE item_id = NEW.item_id;
	IF NEW.quantity > itemnumber THEN
		signal sqlstate '45000' set message_text = "Not enough item for decreasing";
	ELSE
		Update Items 
		SET number = number -  NEW.quantity 
		WHERE items.item_id = NEW.item_id;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `OrderStatus`
--

DROP TABLE IF EXISTS `OrderStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderStatus` (
  `status_id` int(1) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderStatus`
--

LOCK TABLES `OrderStatus` WRITE;
/*!40000 ALTER TABLE `OrderStatus` DISABLE KEYS */;
INSERT INTO `OrderStatus` VALUES (1,'Processing'),(2,'Payment waiting'),(3,'Payment checked'),(4,'Shipped'),(5,'Cancelled'),(6,'Refunded');
/*!40000 ALTER TABLE `OrderStatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_datetime` datetime(2) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `receiver_name` varchar(32) NOT NULL,
  `postal_code` varchar(7) DEFAULT NULL,
  `delivery_address` varchar(100) NOT NULL,
  `payment_method` varchar(20) DEFAULT NULL,
  `receiver_phone` varchar(16) NOT NULL,
  `status_id` int(1) DEFAULT NULL,
  `restocked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`order_id`),
  KEY `fk_Orders_Users1_idx` (`user_id`),
  KEY `fk_Payment_Methods_idx` (`payment_method`),
  KEY `fk_Order_Status_idx` (`status_id`),
  CONSTRAINT `fk_Order_Status` FOREIGN KEY (`status_id`) REFERENCES `OrderStatus` (`status_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Orders_Users1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Payment_Methods` FOREIGN KEY (`payment_method`) REFERENCES `PaymentMethods` (`payment_method`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
INSERT INTO `Orders` VALUES (2,'2017-06-21 02:14:31.00',2,'Thieu Anh','9100003','Linh Dam','COD','941064395',NULL,0),(3,'2017-06-21 02:27:05.00',2,'Thieu Anh','9100003','Linh Dam','COD','941064395',5,1),(4,'2017-06-21 03:02:54.00',2,'Thieu Anh','9100003','Linh Dam','COD','941064395',5,1),(5,'2017-06-21 03:02:54.00',2,'Thieu Anh','9100003','Linh Dam','COD','941064395',5,1),(6,'2017-06-21 03:09:24.00',2,'Thieu Anh','9100003','Linh Dam','COD','941064395',2,0),(7,'2017-06-21 08:10:17.00',8,'Tuan Tran','100000','Hanoi, Vietnam','COD','123459678',1,0),(8,'2017-06-21 08:11:56.00',8,'ATTTTT','100000','HCM, Vietnam','COD','123459678',1,0),(9,'2017-06-21 12:49:13.00',2,'Thieu Anh','9100003','Linh Dam','COD','941064395',4,0),(10,'2017-06-21 17:22:13.00',2,'Thieu Anh','9100003','Linh Dam','COD','941064395',1,0),(11,'2017-06-23 04:16:11.00',2,'Thieu Anh','9100003','Linh Dam','COD','941064395',4,0),(12,'2017-06-23 04:16:40.00',2,'Thieu Anh','9100003','Linh Dam','COD','941064395',1,0),(13,'2017-06-26 06:07:05.00',2,'Thieu Anh','9100003','Linh Dam','COD','941064395',5,0),(14,'2017-06-26 23:58:44.00',2,'Thieu Anh','9100003','Linh Dam','COD','941064395',5,0),(15,'2017-06-29 07:38:28.00',2,'Thieu Anh','9100003','Linh Dam','COD','941064395',NULL,0);
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `QLNhacCu`.`Orders_AFTER_UPDATE` AFTER UPDATE ON `Orders` FOR EACH ROW
BEGIN
		DECLARE n INT DEFAULT 0;
		DECLARE i INT DEFAULT 0;
        DECLARE increaseNum INT DEFAULT 0;
		DECLARE iid INT DEFAULT 0;
		IF New.status_id = 	(SELECT status_id FROM OrderStatus WHERE name = 'Cancelled' ) AND  OLD.restocked =0 THEN
                SELECT COUNT(*) INTO n FROM OrderDetails od WHERE od.order_id = New.order_id;
                SET i=0;
                WHILE i<n DO 
						SET iid=0;
                        SET  increaseNum=0;
                        SELECT item_id,quantity into iid, increaseNum FROM OrderDetails od WHERE od.order_id = New.order_id LIMIT i,1;
						UPDATE Items 
                        SET number = number +increaseNum
                        WHERE item_id = iid;
						SET i = i + 1;
				END WHILE;
        END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `PaymentMethods`
--

DROP TABLE IF EXISTS `PaymentMethods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PaymentMethods` (
  `payment_method` varchar(30) NOT NULL,
  PRIMARY KEY (`payment_method`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PaymentMethods`
--

LOCK TABLES `PaymentMethods` WRITE;
/*!40000 ALTER TABLE `PaymentMethods` DISABLE KEYS */;
INSERT INTO `PaymentMethods` VALUES ('BANK'),('COD');
/*!40000 ALTER TABLE `PaymentMethods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Roles`
--

DROP TABLE IF EXISTS `Roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Roles`
--

LOCK TABLES `Roles` WRITE;
/*!40000 ALTER TABLE `Roles` DISABLE KEYS */;
INSERT INTO `Roles` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');
/*!40000 ALTER TABLE `Roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User_Role`
--

DROP TABLE IF EXISTS `User_Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_Role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `fk_User_Role_Users1_idx` (`user_id`),
  KEY `fk_User_Role_Role1_idx` (`role_id`),
  CONSTRAINT `fk_User_Role_Role1` FOREIGN KEY (`role_id`) REFERENCES `Roles` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Role_Users1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_Role`
--

LOCK TABLES `User_Role` WRITE;
/*!40000 ALTER TABLE `User_Role` DISABLE KEYS */;
INSERT INTO `User_Role` VALUES (1,1),(2,1),(2,2),(3,1),(3,2),(4,1),(5,1),(6,1),(7,1),(8,1),(8,2),(9,1);
/*!40000 ALTER TABLE `User_Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phonenumber` int(11) DEFAULT NULL,
  `postalcode` int(11) DEFAULT NULL,
  `address` text,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'thieuanh1995hn','$2a$11$Iwc8/CyI3W4/mfJa/mVKhegQvH/TiFW8AjGsf33Q8/bppkwmOjgdS','Thieu','Anh','thieuanh1995hn@gmail.com',941064395,100000,'To 3 Sai Dong'),(2,'thieuanh1995','$2a$11$Jb6cQy.h8QIA/grRfGkhNeU6N9ASJnNjM1isKoW0HiC3Jt/BKeTaS','Thieu','Anh','thieuanh1995hn@gmail.com',941064395,9100003,'Linh Dam'),(3,'thieuanh','$2a$11$2XUlOTtjoLGWrD4O4N0YaO3QNP/M10B.gIvS1K8b2nLTksJBaAKoW','Thieu','Anh','thieuanh1995hn@gmail.com',941064395,910003,'Linh Dam'),(4,'tathao1995','$2a$10$cD92KPiY8vZGlJiFU81BLunSNzoQH2idDQmA/dTmuKXPKuY7MrSRq','Ta','Thao','thieuanh1995hn@gmail.com',941064395,9100003,'Sai Gong'),(5,'lungthilinh','$2a$10$vie554kIuhn2difKGJwjSui0RT2B0qYkrX6OPc8WarcZYGzcCnsNW','Linh','Lung','thieuanh1995hn@gmail.com',941064395,100000,'Nguyen Khac Nhu - 53'),(6,'thieuanhhn1995','$2a$10$Mvmi3vmHW53UcO7Egm1TCudZf9k2CmIjNvL4pmdB.XxPOA.O8thFu','Thieu','Anh','thieuanh1995hn@gmail.com',941064395,910003,'GP'),(7,'tathao223','$2a$10$uxNEhaFQyW1z.6/44optg.qSC1oMc91bFZy6mSqEtX3n8TRdnu6Ji','Thieu','Anh','thieuanh1995hn@gmail.com',941064395,910003,'To 3 Sai Dong'),(8,'tuantran1995','$2a$10$Ii/XEcYafhZ4.6.7KZqRy.bGNXPpZA2KxJ2e84lfUtYucI/v13fOy','Tuan','Tran','tuantran.nuce@gmail.com',123459678,100000,'Ng? G&#7889;c &#272;&#7873;'),(9,'hoanghonghu1995','$2a$10$PnxlHPlTgld8/yFVYUOi7OkJktcD8B28SE4AvReG4X0PAvbqFCmCa','Hoang','Hong Hu','hoanghonghu@gmail.com',941558558,100000,'Nguyen Khac Nhu - 53');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'QLNhacCu'
--

--
-- Dumping routines for database 'QLNhacCu'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-02 12:22:25
