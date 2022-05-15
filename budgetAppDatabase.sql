CREATE DATABASE  IF NOT EXISTS `javabudgetapp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `javabudgetapp`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: javabudgetapp
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `accountsummary`
--

DROP TABLE IF EXISTS `accountsummary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accountsummary` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_balance` double NOT NULL,
  `month` varchar(20) NOT NULL,
  `year` varchar(20) NOT NULL,
  `total_income` double NOT NULL,
  `total_expenses` double NOT NULL,
  `total_savings` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountsummary`
--

LOCK TABLES `accountsummary` WRITE;
/*!40000 ALTER TABLE `accountsummary` DISABLE KEYS */;
/*!40000 ALTER TABLE `accountsummary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `custom_goals`
--

DROP TABLE IF EXISTS `custom_goals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `custom_goals` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NOT NULL,
  `saved_location` varchar(100) NOT NULL,
  `amount` double NOT NULL,
  `saved_date` date NOT NULL,
  `total_desired` double NOT NULL,
  `final_date` date NOT NULL,
  `notes` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `custom_goals`
--

LOCK TABLES `custom_goals` WRITE;
/*!40000 ALTER TABLE `custom_goals` DISABLE KEYS */;
INSERT INTO `custom_goals` VALUES (1,'new TV','piggy bank',400,'2022-01-30',3000,'2022-05-01',NULL),(2,'new TV','piggy bank',300,'2022-01-31',3000,'2022-05-01',NULL),(3,'camping trip','savings account',500,'2022-01-31',10000,'2022-12-20',NULL),(4,'camping trip','savings account',500,'2022-01-31',10000,'2022-12-20',NULL),(5,'new TV','piggy bank',400,'2022-02-26',3000,'2022-05-01',NULL),(6,'new TV','piggy bank',400,'2022-02-26',3000,'2022-05-01',NULL),(7,'camping trip','savings account',550,'2022-02-28',10000,'2022-12-20',NULL),(8,'camping trip','savings account',500,'2022-03-01',10000,'2022-12-20',NULL),(9,'new TV','piggy bank',390,'2022-03-25',3000,'2022-05-01',NULL),(10,'new TV','piggy bank',400,'2022-03-26',3000,'2022-05-01',NULL),(11,'camping trip','savings account',600,'2022-03-27',10000,'2022-12-20',NULL),(12,'camping trip','savings account',500,'2022-03-28',10000,'2022-12-20',NULL);
/*!40000 ALTER TABLE `custom_goals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `debt_payments`
--

DROP TABLE IF EXISTS `debt_payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `debt_payments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_category` varchar(100) NOT NULL,
  `paid_to` varchar(100) NOT NULL,
  `amount` double NOT NULL,
  `payment_date` date NOT NULL,
  `end_date` date NOT NULL,
  `total_owed` double NOT NULL,
  `interest` double NOT NULL,
  `notes` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `debt_payments`
--

LOCK TABLES `debt_payments` WRITE;
/*!40000 ALTER TABLE `debt_payments` DISABLE KEYS */;
INSERT INTO `debt_payments` VALUES (1,'morgage','property24',4500,'2022-01-26','2028-05-26',680000,10,NULL),(2,'morgage','property24',4500,'2022-01-26','2028-05-26',680000,10,NULL),(3,'vehicle payment','bmw',3500,'2022-01-26','2025-01-26',65000,12,NULL),(4,'vehicle payment','audi',4000,'2022-01-26','2024-09-26',89000,14,NULL),(5,'account','woolworths',780,'2022-01-26','2022-10-26',4500,18,'clothing account'),(6,'account','edgars',500,'2022-01-27','2023-01-26',5100,14,'clothing account'),(7,'contract','vodacom',400,'2022-01-29','2022-09-29',3000,9,'cellphone contract'),(8,'contract','mtn',250,'2022-01-30','2023-03-30',3500,15,'cellphone contract'),(9,'contract','takealot',750,'2022-01-30','2024-01-30',6000,14,'laptop contract'),(10,'contract','incredible connetction',450,'2022-01-31','2023-07-31',5500,13,'table contract'),(11,'morgage','property24',4500,'2022-02-26','2028-05-26',671000,10,NULL),(12,'morgage','property24',4500,'2022-02-26','2028-05-26',671000,10,NULL),(13,'vehicle payment','bmw',3500,'2022-02-26','2025-01-26',64000,12,NULL),(14,'vehicle payment','audi',4000,'2022-02-26','2024-09-26',86000,14,NULL),(15,'account','woolworths',780,'2022-02-26','2022-10-26',3850,18,'clothing account'),(16,'account','edgars',500,'2022-02-27','2023-01-26',4800,14,'clothing account'),(17,'contract','vodacom',400,'2022-02-28','2022-09-29',2500,9,'cellphone contract'),(18,'contract ','mtn',250,'2022-02-28','2023-03-30',3100,15,'cellphone contract'),(19,'contract','takealot',750,'2022-02-28','2024-01-30',6300,14,'laptop contract'),(20,'contract','incredible connection',450,'2022-02-28','2023-07-31',5200,13,'tablet contract'),(22,'morgage','property24',4500,'2022-03-26','2028-05-26',662000,10,NULL),(23,'morgage','property24',4500,'2022-03-26','2028-05-26',662000,10,NULL),(24,'vehicle payment','bmw',3500,'2022-03-26','2025-01-26',60500,12,NULL),(25,'vehicle payment','audi',4000,'2022-03-26','2024-09-26',82500,14,NULL),(27,'account','woolworths',780,'2022-03-26','2022-10-26',3050,18,'clothing account'),(28,'account','edgars',500,'2022-03-27','2023-01-27',4350,14,'clothing account'),(29,'contract','vodacom',400,'2022-03-29','2022-09-29',2100,9,'cellphon contract'),(30,'contract','mtn',250,'2022-03-30','2022-03-30',2850,15,'cellphone contract'),(31,'contract','takealot',750,'2022-03-30','2024-01-30',5150,14,'laptop contract'),(32,'contract','incredible connection',450,'2022-03-31','2023-07-31',4800,13,'tablet contract');
/*!40000 ALTER TABLE `debt_payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expenses`
--

DROP TABLE IF EXISTS `expenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expenses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_category` varchar(100) NOT NULL,
  `paid_to` varchar(100) NOT NULL,
  `amount` double NOT NULL,
  `payment_date` date NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `notes` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expenses`
--

LOCK TABLES `expenses` WRITE;
/*!40000 ALTER TABLE `expenses` DISABLE KEYS */;
INSERT INTO `expenses` VALUES (1,'water_electricity','property24',2000,'2022-01-26',NULL,NULL),(2,'water_electricity','property24',1000,'2022-01-26',NULL,NULL),(3,'debit_order','virgin active',550,'2022-01-26','gym membership',NULL),(4,'debit_order','netflix',300,'2022-01-26','netflix membership',NULL),(5,'debit_order','telkom',700,'2022-01-26','wifi paymemt',NULL),(6,'petrol','sasol',600,'2022-01-26',NULL,NULL),(7,'groceries_food','checkers',780,'2022-01-26',NULL,NULL),(8,'insurance','outsurance',1200,'2022-01-27','car insurance',NULL),(9,'insurance','outsurance',900,'2022-01-27','car insurance',NULL),(10,'petrol','bp',300,'2022-01-29',NULL,NULL),(11,'medical_costs','dischem',190,'2022-01-29','flu medicine',NULL),(12,'eating out','spur',450,'2022-01-29',NULL,NULL),(13,'repairs_upgrades_maintenance','builders',340,'2022-01-30','new shelves',NULL),(14,'smoking_alcohol','tops',290,'2022-01-31','braai drinks',NULL),(15,'petrol','caltex',680,'2022-01-31',NULL,NULL),(16,'custom','incredible connection',500,'2022-02-01','headphones',NULL),(17,'petrol','bp',400,'2022-02-01',NULL,NULL),(18,'study_expense','udemy',290,'2022-02-02','calculus course',NULL),(19,'groceries_food','woolworths',100,'2022-02-02','snacks',NULL),(20,'groceries_food','pick n pay',350,'2022-02-03',NULL,NULL),(21,'eating_out','macdonalds',50,'2022-02-04','desert',NULL),(22,'petrol','sasol',270,'2022-02-06',NULL,NULL),(23,'study_expense','pna',135,'2022-02-07','notebook',NULL),(24,'medical_costs','clicks',80,'2022-02-07','bandages',NULL),(25,'groceries_food','pick n pay',250,'2022-02-07',NULL,NULL),(26,'custom','takealot',199,'2022-02-08','converter cable',NULL),(27,'smoking_alcohol','liquorcity',150,'2022-02-08','drinks for dinner ',NULL),(28,'petrol','bp',150,'2022-02-08',NULL,NULL),(29,'petrol','sasol',300,'2022-02-09',NULL,NULL),(30,'groceries_food','checkers',560,'2022-02-09',NULL,NULL),(31,'groceries_food','checkers',478,'2022-02-09',NULL,NULL),(32,'travel_expenses','hikingmountain',250,'2022-02-10','hiking day',NULL),(33,'travel_expenses','hikingmountain',250,'2022-02-10','hiking day',NULL),(34,'eating_out','burgercity',300,'2022-02-10','lunch',NULL),(35,'petrol','engen',300,'2022-02-10',NULL,NULL),(36,'repairs_upgrades_maintenance','pick n pay',120,'2022-02-11','light bulbs',NULL),(37,'emergency','friend account',400,'2022-02-11','private',NULL),(38,'entertainment','numetro',350,'2022-02-12','movie night',NULL),(39,'eating_out','kfc',160,'2022-02-12','dinner',NULL),(40,'study_expense','pna',35,'2022-02-13','new pen',NULL),(41,'study_expense','pna',70,'2022-02-13','pen and book',NULL),(42,'petrol','bp',120,'2022-02-13',NULL,NULL),(43,'medical_costs','clicks',55,'2022-02-13','masks',NULL),(44,'groceries_food','woolworths',175,'2022-02-15','snacks',NULL),(45,'groceries_food','pick n pay',400,'2022-02-15',NULL,NULL),(46,'petrol','sasol',350,'2022-02-15',NULL,NULL),(47,'debit_orders','codingnomads',190,'2022-02-17','coding subscription',NULL),(48,'medical_costs','dischem',85,'2022-02-17','first aid kit topup',NULL),(49,'custom','sheet street',250,'2022-02-18','2 x pillows',NULL),(50,'repairs_maintenance_upgrades','hardware',390,'2022-02-18','paint for wall',NULL),(51,'petrol','bp',120,'2022-02-19',NULL,NULL),(52,'eating_out','pizzapalace',90,'2022-02-20','dinner',NULL),(53,'entertainment','mr dvd',60,'2022-02-20','dvd night',NULL),(54,'entertainment','put-put',80,'2022-02-21','golf day',NULL),(55,'eating_out','chip and dip',75,'2022-02-21','lunch',NULL),(56,'petrol','sasol',290,'2022-02-21',NULL,NULL),(57,'groceries_food','spar',350,'2022-02-22',NULL,NULL),(58,'medical_costs','dischem',150,'2022-02-22','lotions and creams',NULL),(59,'medical_costs','physio',400,'2022-02-23','monthly stretching',NULL),(60,'petrol','bp',900,'2022-02-25',NULL,NULL),(61,'petrol','sasol',860,'2022-02-25',NULL,NULL),(62,'groceries_food','pick n pay',700,'2022-02-25',NULL,NULL),(63,'study_expense','cna',130,'2022-02-25','diary',NULL),(64,'water_electricty','property24',1900,'2022-02-26',NULL,NULL),(65,'water_electricity','property24',1400,'2022-02-26',NULL,NULL),(66,'debit_order','virgin active',550,'2022-02-26','gym membership',NULL),(67,'debit_order','netflix',300,'2022-02-26','netflix membership',NULL),(68,'debit_order','telkom',700,'2022-02-26','wifi payment',NULL),(69,'eating_out','doppio zero',450,'2022-02-27','dinner',NULL),(70,'emergency','plumber',300,'2022-02-28','burst pipe',NULL),(71,'custom','incredible connection',1200,'2022-02-28','new monitor',NULL),(72,'repairs_upgrades_maintenance','hardware',290,'2022-03-01','shelves, screws, paint',NULL),(73,'groceries_food','spar',190,'2022-03-01',NULL,NULL),(74,'smoking_alcohol','tops',100,'2022-03-01','drinks',NULL),(75,'petrol','sasol',200,'2022-03-04',NULL,NULL),(76,'petrol','bp',150,'2022-03-06',NULL,NULL),(77,'custom','bp',120,'2022-03-06','car wash',NULL),(78,'custom','bp',120,'2022-03-06','car wash',NULL),(79,'repairs_upgrades_maintenance','hardware',40,'2022-03-06','washers',NULL),(80,'eating_out','rocomamas',138,'2022-03-06','desert',NULL),(81,'groceries_food','spar',210,'2022-03-07',NULL,NULL),(82,'medical_costs','clicks',45,'2022-03-07','cough syrup',NULL),(83,'custom','pet store',300,'2022-03-08','gold fish setup',NULL),(84,'smoking_alcohol','tops',140,'2022-03-08','drinks for party',NULL),(85,'groceries_food','spar',300,'2022-03-08','meat for braai',NULL),(86,'petrol','bp',130,'2022-03-09',NULL,NULL),(87,'entertainment','numetro',390,'2022-03-09','movie night',NULL),(88,'study_expense','textbooksforall',290,'2022-03-11','research book',NULL),(89,'medical_costs','dischem',55,'2022-03-11','masks',NULL),(90,'eating_out','steers',189,'2022-03-11','dinner',NULL),(91,'groceries_food','pick n pay',560,'2022-03-12',NULL,NULL),(92,'petrol','caltex',400,'2022-03-12',NULL,NULL),(93,'repairs_upgrades_maintenance','hardware',290,'2022-03-15','paint and varnish',NULL),(94,'petrol','bp',350,'2022-03-15',NULL,NULL),(95,'debit_orders','codingnomads',180,'2022-03-17','coding subscription',NULL),(96,'entertainment','dvdking',90,'2022-03-17','dvd night',NULL),(97,'groceries_food','woolworths',130,'2022-03-18','snacks',NULL),(98,'groceries_food','spar',200,'2022-03-18',NULL,NULL),(99,'custom','matrix',80,'2022-03-18','mouse',NULL),(100,'repairs_upgrades_maintenance','hardware',100,'2022-03-18','rocks for pond',NULL),(101,'travel_expenses','viewpoint',120,'2022-03-20','bird watching',NULL),(102,'travel_expenses','viewpoint',120,'2022-03-20','bird watching',NULL),(103,'eating_out','pizzapalace',190,'2022-03-20','lunch',NULL),(104,'petrol','bp',300,'2022-03-21',NULL,NULL),(105,'study_expense','pna',120,'2022-03-21','pens and book',NULL),(106,'custom','nurserygalore',170,'2022-03-22','plants for garden',NULL),(107,'groceries_food','checkers',390,'2022-03-23',NULL,NULL),(108,'groceries_food','pick  n pay',170,'2022-03-23',NULL,NULL),(109,'eating_out','kfc',140,'2022-03-23','lunch',NULL),(110,'petrol','sasol',200,'2022-03-24',NULL,NULL),(111,'water_electricity','property24',2200,'2022-03-26',NULL,NULL),(112,'water_electricity','property24',1600,'2022-03-26',NULL,NULL),(113,'debit_order','virgin active',550,'2022-03-26','gym membership',NULL),(114,'debit_order','netflix',300,'2022-03-26','netflix membership',NULL),(115,'debit_order','telkom',700,'2022-03-26','wifi payment',NULL),(116,'groceries_food','spar',450,'2022-03-26',NULL,NULL),(117,'eating_out','grill and seafood',290,'2022-03-27','dinner',NULL),(118,'entertainment','sterkinekpr',300,'2022-03-27','movies',NULL),(119,'entertainment','goldencircle',400,'2022-03-27','gambling',NULL),(120,'petrol','engen',390,'2022-03-28',NULL,NULL),(121,'groceries_food','pick n pay',170,'2022-03-28',NULL,NULL),(122,'medical_costs','clicks',180,'2022-03-29','flu medicine',NULL);
/*!40000 ALTER TABLE `expenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `income`
--

DROP TABLE IF EXISTS `income`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `income` (
  `id` int NOT NULL AUTO_INCREMENT,
  `source_name` varchar(100) NOT NULL,
  `amount` double NOT NULL,
  `payment_date` date NOT NULL,
  `payment_interval` varchar(100) NOT NULL,
  `notes` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `income`
--

LOCK TABLES `income` WRITE;
/*!40000 ALTER TABLE `income` DISABLE KEYS */;
INSERT INTO `income` VALUES (1,'salary',22000,'2022-01-25','monthly',NULL),(2,'salary',26000,'2022-01-25','monthly',NULL),(3,'bonus',3000,'2022-01-27','once-off',NULL),(4,'craftsStore',5600,'2022-02-10','once-off','website design payment'),(5,'salary',22000,'2022-02-25','monthly',NULL),(6,'salary',26000,'2022-02-25','monthly',NULL),(7,'benStores',1000,'2022-02-26','once-off','shelving setup'),(8,'salary',22000,'2022-03-25','monthly',NULL),(9,'salary',26000,'2022-03-25','monthly',NULL);
/*!40000 ALTER TABLE `income` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `savings`
--

DROP TABLE IF EXISTS `savings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `savings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `saved_location` varchar(100) NOT NULL,
  `amount` double NOT NULL,
  `saved_date` date NOT NULL,
  `notes` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `savings`
--

LOCK TABLES `savings` WRITE;
/*!40000 ALTER TABLE `savings` DISABLE KEYS */;
INSERT INTO `savings` VALUES (1,'bank',450,'2022-01-28','general savings'),(2,'savings account',200,'2022-01-28','birthday savings'),(3,'bank',300,'2022-01-31','general savings'),(4,'savings account',150,'2022-02-15','birthday savings'),(5,'bank',450,'2022-02-28','general savings'),(6,'savings account',250,'2022-02-28','birthday savings'),(7,'bank',700,'2022-03-01','general savings'),(8,'bank',560,'2022-03-26','general savings'),(9,'savings account',300,'2022-03-27','birthday savings'),(10,'bank',400,'2022-03-30','general savings');
/*!40000 ALTER TABLE `savings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_accountsummary`
--

DROP TABLE IF EXISTS `user_accountsummary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_accountsummary` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `accountSummary_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  KEY `accountSummary_idx` (`accountSummary_id`),
  CONSTRAINT `accountSummary_id` FOREIGN KEY (`accountSummary_id`) REFERENCES `accountsummary` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id6` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_accountsummary`
--

LOCK TABLES `user_accountsummary` WRITE;
/*!40000 ALTER TABLE `user_accountsummary` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_accountsummary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_custom_goals`
--

DROP TABLE IF EXISTS `user_custom_goals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_custom_goals` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `custom_goals_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  KEY `custom_goals_idx` (`custom_goals_id`),
  CONSTRAINT `custom_goals_id` FOREIGN KEY (`custom_goals_id`) REFERENCES `custom_goals` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id5` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_custom_goals`
--

LOCK TABLES `user_custom_goals` WRITE;
/*!40000 ALTER TABLE `user_custom_goals` DISABLE KEYS */;
INSERT INTO `user_custom_goals` VALUES (1,2,1),(2,1,2),(3,2,3),(4,1,4),(5,1,5),(6,2,6),(7,1,7),(8,2,8),(9,1,9),(10,2,10),(11,2,11),(12,1,12);
/*!40000 ALTER TABLE `user_custom_goals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_debt_payments`
--

DROP TABLE IF EXISTS `user_debt_payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_debt_payments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `debt_payments_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  KEY `debt_payments_id_idx` (`debt_payments_id`),
  CONSTRAINT `debt_payments_id` FOREIGN KEY (`debt_payments_id`) REFERENCES `debt_payments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id3` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_debt_payments`
--

LOCK TABLES `user_debt_payments` WRITE;
/*!40000 ALTER TABLE `user_debt_payments` DISABLE KEYS */;
INSERT INTO `user_debt_payments` VALUES (1,1,1),(2,2,2),(3,1,3),(4,2,4),(5,2,5),(6,1,6),(7,1,7),(8,2,8),(9,2,9),(10,1,10),(11,1,11),(12,2,12),(13,1,13),(14,2,14),(15,2,15),(16,1,16),(17,1,17),(18,2,18),(19,2,19),(20,1,20),(21,1,22),(22,2,23),(23,1,24),(24,2,25),(25,2,27),(26,1,28),(27,1,29),(28,2,30),(29,2,31),(30,1,32);
/*!40000 ALTER TABLE `user_debt_payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_expenses`
--

DROP TABLE IF EXISTS `user_expenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_expenses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `expenses_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  KEY `expenses_id_idx` (`expenses_id`),
  CONSTRAINT `expenses_id` FOREIGN KEY (`expenses_id`) REFERENCES `expenses` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_expenses`
--

LOCK TABLES `user_expenses` WRITE;
/*!40000 ALTER TABLE `user_expenses` DISABLE KEYS */;
INSERT INTO `user_expenses` VALUES (1,1,1),(2,2,2),(3,2,3),(4,1,4),(5,1,5),(6,1,6),(7,2,7),(8,2,8),(9,1,9),(10,2,10),(11,1,11),(12,1,12),(13,1,13),(14,2,14),(15,1,15),(16,2,16),(17,2,17),(18,1,18),(19,2,19),(20,1,20),(21,2,21),(22,2,22),(23,2,23),(24,1,24),(25,1,25),(26,1,26),(27,1,27),(28,2,28),(29,1,29),(30,1,30),(31,2,31),(32,1,32),(33,2,33),(34,2,34),(35,2,35),(36,1,36),(37,1,37),(38,1,38),(39,2,39),(42,1,40),(43,2,41),(44,1,42),(45,2,43),(46,2,44),(47,1,45),(48,2,46),(49,1,47),(50,2,48),(51,1,49),(52,1,50),(53,2,51),(54,2,52),(55,2,53),(56,2,54),(57,1,55),(58,1,56),(64,1,57),(65,2,58),(66,1,59),(67,1,60),(68,2,61),(69,1,62),(70,2,63),(71,1,64),(72,2,65),(76,2,66),(77,2,67),(78,1,68),(79,1,69),(80,1,70),(81,2,71),(82,1,72),(83,2,73),(84,2,74),(85,2,75),(86,1,76),(87,1,77),(88,2,78),(89,1,79),(90,1,80),(91,2,81),(92,2,82),(93,1,83),(94,2,84),(95,1,85),(96,2,86),(97,1,87),(98,2,88),(99,2,89),(100,2,90),(101,1,91),(102,1,92),(103,1,93),(104,1,94),(105,1,95),(106,2,96),(107,2,97),(108,1,98),(109,1,99),(110,1,100),(111,1,101),(112,2,102),(113,2,103),(114,1,104),(115,2,105),(116,2,106),(117,2,107),(118,1,108),(119,1,109),(120,1,110),(121,1,111),(122,2,112),(123,2,113),(124,1,114),(125,2,115),(126,1,116),(127,2,117),(128,1,118),(129,1,119),(130,2,120),(131,2,121);
/*!40000 ALTER TABLE `user_expenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_income`
--

DROP TABLE IF EXISTS `user_income`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_income` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `income_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  KEY `income_id_idx` (`income_id`),
  CONSTRAINT `income_id` FOREIGN KEY (`income_id`) REFERENCES `income` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_income`
--

LOCK TABLES `user_income` WRITE;
/*!40000 ALTER TABLE `user_income` DISABLE KEYS */;
INSERT INTO `user_income` VALUES (1,1,1),(2,2,2),(3,2,3),(4,1,4),(5,1,5),(6,2,6),(7,2,7),(8,1,8),(9,2,9);
/*!40000 ALTER TABLE `user_income` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_savings`
--

DROP TABLE IF EXISTS `user_savings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_savings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `savings_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  KEY `savings_id_idx` (`savings_id`),
  CONSTRAINT `savings_id` FOREIGN KEY (`savings_id`) REFERENCES `savings` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id4` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_savings`
--

LOCK TABLES `user_savings` WRITE;
/*!40000 ALTER TABLE `user_savings` DISABLE KEYS */;
INSERT INTO `user_savings` VALUES (1,1,1),(2,2,2),(3,1,3),(4,2,4),(5,1,5),(6,2,6),(7,1,7),(8,1,8),(9,2,9),(10,2,10);
/*!40000 ALTER TABLE `user_savings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'brian','adams','badams@gmail.com'),(2,'amanda','adams','amandaadams@outlook.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-15 23:43:26
