CREATE DATABASE  IF NOT EXISTS `busbookingdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `busbookingdb`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: busbookingdb
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `state` int DEFAULT NULL,
  `moneyPaid` double DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `bill_type_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,'2023-09-02 15:29:17',NULL,NULL,1,NULL),(2,'2023-09-02 15:56:02',NULL,NULL,1,NULL),(3,'2023-09-03 18:49:03',NULL,NULL,5,NULL);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus`
--

DROP TABLE IF EXISTS `bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus` (
  `id` int NOT NULL AUTO_INCREMENT,
  `plate` varchar(12) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `number_of_seats` int DEFAULT NULL,
  `bus_type_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bus_type_id` (`bus_type_id`),
  CONSTRAINT `bus_ibfk_1` FOREIGN KEY (`bus_type_id`) REFERENCES `bus_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus`
--

LOCK TABLES `bus` WRITE;
/*!40000 ALTER TABLE `bus` DISABLE KEYS */;
INSERT INTO `bus` VALUES (1,'93C1-12475',16,1),(2,'47B1-14752',16,1);
/*!40000 ALTER TABLE `bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bus_type`
--

DROP TABLE IF EXISTS `bus_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `type_cost` float DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus_type`
--

LOCK TABLES `bus_type` WRITE;
/*!40000 ALTER TABLE `bus_type` DISABLE KEYS */;
INSERT INTO `bus_type` VALUES (1,'Xe giường nằm',1.12);
/*!40000 ALTER TABLE `bus_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text COLLATE utf8mb4_unicode_520_ci,
  `stars` int NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `trip_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `feedback_ibfk_1_idx` (`user_id`),
  KEY `feedback_ibfk_3_idx` (`trip_id`),
  CONSTRAINT `feedback_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `feedback_ibfk_3` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,'This is the best trip I\'ve gone',5,'2023-05-04 15:22:30',NULL,1,4),(2,'Hay nha',4,'2023-09-04 20:11:34',NULL,5,4);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offline_bill`
--

DROP TABLE IF EXISTS `offline_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offline_bill` (
  `bill_id` int NOT NULL,
  `client_name` varchar(50) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `client_phone_number` char(10) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  UNIQUE KEY `bill_id` (`bill_id`),
  CONSTRAINT `offline_bill_ibfk_1` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offline_bill`
--

LOCK TABLES `offline_bill` WRITE;
/*!40000 ALTER TABLE `offline_bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `offline_bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(30) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_Admin'),(2,'ROLE_Staff'),(3,'ROLE_Driver'),(4,'ROLE_Client');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `route` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `origin` int DEFAULT NULL,
  `destination` int DEFAULT NULL,
  `duration_days` int DEFAULT NULL,
  `basic_price` float NOT NULL,
  `duration_time` varchar(12) COLLATE utf8mb4_unicode_520_ci DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `destination` (`destination`),
  KEY `origin` (`origin`),
  CONSTRAINT `route_ibfk_1` FOREIGN KEY (`destination`) REFERENCES `station` (`id`),
  CONSTRAINT `route_ibfk_2` FOREIGN KEY (`origin`) REFERENCES `station` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (1,'',1,2,2,300000,'20:30:00'),(2,'',3,4,0,250000,'06:00:00'),(3,'',4,1,0,100000,'02:00:00'),(4,'',1,3,0,200000,'06:00:00'),(5,'',1,4,0,100000,'02:00:00');
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `seat_row_pos` int DEFAULT NULL,
  `seat_col_pos` int DEFAULT NULL,
  `bus_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bus_id` (`bus_id`),
  CONSTRAINT `seat_ibfk_1` FOREIGN KEY (`bus_id`) REFERENCES `bus` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (1,1,1,1),(2,2,1,1),(3,1,2,1),(4,2,2,1),(5,1,1,2),(6,2,1,2),(7,3,1,2),(8,1,2,2),(9,2,2,2),(10,3,2,2),(11,1,3,2),(12,2,3,2),(13,3,3,2),(14,1,4,2),(15,2,4,2),(16,3,4,2),(17,1,5,2),(18,2,5,2),(19,3,5,2);
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station` (
  `id` int NOT NULL AUTO_INCREMENT,
  `location` varchar(250) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `province` varchar(50) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `picture` varchar(200) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES (1,'District 3, Hai Bà Trưng Street','Hồ Chí Minh City','https://res.cloudinary.com/ddivten1j/image/upload/v1692845955/v41tpvvjn5alvaoaghqk.jpg'),(2,'Hồ Gươm Lake','Hà Nội','https://res.cloudinary.com/ddivten1j/image/upload/v1693093510/qolmwaempzlrtyfsrpxq.jpg'),(3,'Quảng Trường, Đà Lạt','Lâm Đồng','https://res.cloudinary.com/ddivten1j/image/upload/v1693095320/r2ikzkfqyzspt6gozzsx.jpg'),(4,'Đồng Xoài City','Bình Phước','https://res.cloudinary.com/ddivten1j/image/upload/v1693095967/auzhwneoizkqp3letmaq.jpg');
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_available` tinyint(1) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `trip_id` int DEFAULT NULL,
  `seat_id` int DEFAULT NULL,
  `bill_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `trip_id` (`trip_id`),
  KEY `seat_id` (`seat_id`),
  KEY `bill_id` (`bill_id`),
  CONSTRAINT `ticket_ibfk_1` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`),
  CONSTRAINT `ticket_ibfk_2` FOREIGN KEY (`seat_id`) REFERENCES `seat` (`id`),
  CONSTRAINT `ticket_ibfk_3` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (1,0,2000,3,1,2),(2,1,3000,3,2,NULL),(3,0,4000,3,3,NULL),(4,1,5000,3,4,NULL),(5,0,6000,4,1,1),(6,0,7000,4,2,1),(7,1,8000,4,3,NULL),(8,0,9000,4,4,1),(9,1,10000,5,5,NULL),(10,1,11000,5,6,NULL),(11,1,12000,5,7,NULL),(12,1,13000,5,8,NULL),(13,1,12000,5,9,NULL),(14,1,11000,5,10,NULL),(15,1,11000,5,11,NULL),(16,1,11000,5,12,NULL),(17,1,11000,5,13,NULL),(18,1,11000,5,14,NULL),(19,1,11000,5,15,NULL),(20,1,11000,5,16,NULL),(21,1,11000,5,17,NULL),(22,1,11000,5,18,NULL),(23,1,11000,5,19,NULL),(24,0,132000,6,1,3),(25,1,132000,6,2,NULL),(26,1,132000,6,3,NULL),(27,1,132000,6,4,NULL),(28,1,284000,7,5,NULL),(29,1,284000,7,6,NULL),(30,1,284000,7,7,NULL),(31,1,284000,7,8,NULL),(32,1,284000,7,9,NULL),(33,1,284000,7,10,NULL),(34,1,284000,7,11,NULL),(35,1,284000,7,12,NULL),(36,1,284000,7,13,NULL),(37,1,284000,7,14,NULL),(38,1,284000,7,15,NULL),(39,1,284000,7,16,NULL),(40,1,284000,7,17,NULL),(41,1,284000,7,18,NULL),(42,1,284000,7,19,NULL);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_printed`
--

DROP TABLE IF EXISTS `ticket_printed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket_printed` (
  `ticket_id` int DEFAULT NULL,
  `printed_time` datetime DEFAULT NULL,
  `staff_id` int DEFAULT NULL,
  UNIQUE KEY `ticket_id` (`ticket_id`),
  KEY `staff_id` (`staff_id`),
  CONSTRAINT `ticket_printed_ibfk_1` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`),
  CONSTRAINT `ticket_printed_ibfk_2` FOREIGN KEY (`staff_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_printed`
--

LOCK TABLES `ticket_printed` WRITE;
/*!40000 ALTER TABLE `ticket_printed` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_printed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip`
--

DROP TABLE IF EXISTS `trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip` (
  `id` int NOT NULL AUTO_INCREMENT,
  `set_off_day` date DEFAULT NULL,
  `holiday_cost` float DEFAULT '1',
  `state` int DEFAULT NULL,
  `driver_id` int DEFAULT NULL,
  `bus_id` int DEFAULT NULL,
  `route_id` int DEFAULT NULL,
  `set_off_time` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `driver_id` (`driver_id`),
  KEY `route_id` (`route_id`),
  KEY `bus_id` (`bus_id`),
  CONSTRAINT `trip_ibfk_1` FOREIGN KEY (`driver_id`) REFERENCES `user` (`id`),
  CONSTRAINT `trip_ibfk_2` FOREIGN KEY (`route_id`) REFERENCES `route` (`id`),
  CONSTRAINT `trip_ibfk_3` FOREIGN KEY (`bus_id`) REFERENCES `bus` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip`
--

LOCK TABLES `trip` WRITE;
/*!40000 ALTER TABLE `trip` DISABLE KEYS */;
INSERT INTO `trip` VALUES (2,'2023-09-10',1.2,NULL,NULL,1,1,'14:20:00'),(3,'2023-09-02',1.2,NULL,NULL,1,1,'14:27:00'),(4,'2023-09-21',1.2,NULL,NULL,1,1,'15:50:00'),(5,'2023-09-19',1.2,NULL,NULL,2,2,'20:10:00'),(6,'2023-09-06',1.2,NULL,NULL,1,3,'07:01:00'),(7,'2023-09-10',1.3,NULL,NULL,2,4,'09:30:00');
/*!40000 ALTER TABLE `trip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `last_name` varchar(50) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `phone_number` char(10) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `address` varchar(200) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `active` tinyint(1) DEFAULT '1',
  `password` varchar(100) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `avatar` varchar(100) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Alvin','Cao','0123456789','BP','hihi@gmail.xyz',1,'$2a$10$5X9k5N1sTc1/CjVH5XJoje3QMYijH3ETpgkox00R0MdPaJPPrf7wO','https://res.cloudinary.com/ddivten1j/image/upload/v1692864166/ojwnerjirtoay80dahtv.png',1),(2,'Wendy','Grigges','0123456789','HN','yeye@gmail.xyz',1,'$2a$10$5X9k5N1sTc1/CjVH5XJoje3QMYijH3ETpgkox00R0MdPaJPPrf7wO','https://res.cloudinary.com/ddivten1j/image/upload/v1692864373/xckho0xcvanp5kxfozaa.png',2),(3,'Mable','Pines','0123456789','DL','abc@xyz.hehe',1,'$2a$10$5X9k5N1sTc1/CjVH5XJoje3QMYijH3ETpgkox00R0MdPaJPPrf7wO','https://res.cloudinary.com/ddivten1j/image/upload/v1692860768/nutns1mhfmxesaoe8d8p.png',3),(4,'Dipper','Pines','0123456789','SG','xyz@abc.hehe',1,'$2a$10$5X9k5N1sTc1/CjVH5XJoje3QMYijH3ETpgkox00R0MdPaJPPrf7wO','https://res.cloudinary.com/ddivten1j/image/upload/v1692863873/ycdnkdtkukh7hdlysbsr.jpg',1),(5,'Alvin',NULL,NULL,NULL,'zedmontage69@gmail.com',1,'$2a$10$KxJThgN6Q.1Zunr3EPs5I.Bz2F1Ve2slecUqw1DecbmVlG1JZXB0i','https://lh3.googleusercontent.com/a/AAcHTtcALWkVyE-20m6Vll-Y0o9bgxrO6pqP2MiOVEgCVIpYzBg=s96-c',4);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-04 20:48:22
