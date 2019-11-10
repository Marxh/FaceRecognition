CREATE DATABASE  IF NOT EXISTS `Student` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `Student`;
-- MySQL dump 10.13  Distrib 8.0.18, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: Student
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `Student_info`
--

DROP TABLE IF EXISTS `Student_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Student_info` (
  `Student_ID` varchar(100) NOT NULL,
  `First_NAme` varchar(100) NOT NULL,
  `Last_Name` varchar(100) NOT NULL,
  `Age` int(11) NOT NULL,
  `Program` varchar(100) NOT NULL,
  `Gender` varchar(100) NOT NULL,
  `Country` varchar(100) NOT NULL,
  `Remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Student_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student_info`
--

LOCK TABLES `Student_info` WRITE;
/*!40000 ALTER TABLE `Student_info` DISABLE KEYS */;
INSERT INTO `Student_info` VALUES ('U201901','Xu','Zheng',23,'MISM','Male','China','null'),('U201902','Yufan','Yan',22,'MISM','Male','China','null'),('U201903','Xinrui','Zheng',22,'MISM','Female','China','Remark'),('U201904','Shanyue','Wan',22,'MISM','Male','China','null'),('U201905','Jianping','Deng',21,'MIST','Male','China','null');
/*!40000 ALTER TABLE `Student_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student_Log`
--

DROP TABLE IF EXISTS `Student_Log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Student_Log` (
  `Stu_ID` varchar(100) NOT NULL,
  `Visit_Time` timestamp NOT NULL,
  `Visit_Num` int(11) NOT NULL,
  `Reason` varchar(100) DEFAULT NULL,
  `Emotion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Stu_ID`,`Visit_Time`),
  CONSTRAINT `student_log_ibfk_1` FOREIGN KEY (`Stu_ID`) REFERENCES `student_info` (`Student_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student_Log`
--

LOCK TABLES `Student_Log` WRITE;
/*!40000 ALTER TABLE `Student_Log` DISABLE KEYS */;
INSERT INTO `Student_Log` VALUES ('U201901','2019-08-29 05:45:24',1,'Orientation Register','Happy'),('U201901','2019-09-14 23:52:52',2,'Booking Project Room','Ordinary'),('U201901','2019-10-26 07:08:12',3,'Register for Halloween Party','Happy'),('U201902','2019-08-29 02:34:18',1,'Orientation Register','Happy'),('U201902','2019-09-12 06:53:42',2,'Booking Project Room','Ordinary'),('U201903','2019-08-28 00:51:34',1,'Orientation Register','Happy'),('U201903','2019-09-25 04:48:54',2,'Check Assignments Due','Worried'),('U201903','2019-10-01 05:56:07',3,'Check Assignment Score','Surprised'),('U201903','2019-10-07 08:07:52',4,'Log into Library Website','Worried'),('U201903','2019-10-28 09:53:24',5,'Register for Halloween Party','Happy'),('U201904','2019-08-29 04:17:14',1,'Orientation Register','Happy'),('U201905','2019-08-29 02:15:29',1,'Orientation Register','Happy'),('U201905','2019-10-01 05:22:04',2,'Check Assignments Due','Worried'),('U201905','2019-10-17 08:58:33',3,'Booking Project Room','Ordinary'),('U201905','2019-10-30 11:06:27',4,'Register for Halloween Party','Happy');
/*!40000 ALTER TABLE `Student_Log` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-07 17:24:52
