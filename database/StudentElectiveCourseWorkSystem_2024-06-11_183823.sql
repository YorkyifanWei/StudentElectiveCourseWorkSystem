-- MySQL dump 10.13  Distrib 8.3.0, for macos14.2 (arm64)
--
-- Host: localhost    Database: StudentElectiveCourseWorkSystem
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin_info`
--

DROP TABLE IF EXISTS `admin_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `aid` char(5) NOT NULL,
  `a_name` varchar(20) NOT NULL,
  `a_password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_info`
--

/*!40000 ALTER TABLE `admin_info` DISABLE KEYS */;
INSERT INTO `admin_info` VALUES (1,'a0000','admin','admin123');
/*!40000 ALTER TABLE `admin_info` ENABLE KEYS */;

--
-- Table structure for table `course_info`
--

DROP TABLE IF EXISTS `course_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cid` char(8) NOT NULL,
  `c_name` varchar(30) NOT NULL,
  `c_info` varchar(100) NOT NULL,
  `c_time` varchar(100) NOT NULL,
  `c_init` int NOT NULL,
  `c_end` int NOT NULL,
  `c_location` varchar(50) NOT NULL,
  `c_type` char(1) NOT NULL,
  `c_school` char(2) NOT NULL,
  `c_period` int NOT NULL,
  `c_capacity` int NOT NULL,
  `c_current` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_info`
--

/*!40000 ALTER TABLE `course_info` DISABLE KEYS */;
INSERT INTO `course_info` VALUES (1,'c0200001','数学分析B1','人人都爱的数分','(1,1),(1,3),(3,1),(3,3),(5,1),(5,3)',1,20,'东区第五教学楼5201','0','02',6,90,3),(2,'c0100002','力学','人人都爱的力学','(1,1),(1,3),(3,3),(3,4),(5,3),(5,4)',1,20,'东区第五教学楼5302','0','01',6,90,1),(3,'c0600003','程序设计','想秃头就来，体验帕鲁生活','(2,3),(2,4),(4,3),(4,4)',2,18,'东区第五教学楼5402','0','06',6,90,2);
/*!40000 ALTER TABLE `course_info` ENABLE KEYS */;

--
-- Table structure for table `student_course`
--

DROP TABLE IF EXISTS `student_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cid` char(8) NOT NULL,
  `sid` char(9) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_course`
--

/*!40000 ALTER TABLE `student_course` DISABLE KEYS */;
INSERT INTO `student_course` VALUES (1,'c0200001','s23030001'),(2,'c0600003','s23030001'),(3,'c0200001','s23000002'),(4,'c0600003','s23000002'),(5,'c0200001','s23111155'),(6,'c0100002','s22060522');
/*!40000 ALTER TABLE `student_course` ENABLE KEYS */;

--
-- Table structure for table `student_info`
--

DROP TABLE IF EXISTS `student_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sid` char(9) NOT NULL,
  `s_name` varchar(20) NOT NULL,
  `s_class` char(6) NOT NULL,
  `s_school` char(2) NOT NULL,
  `s_sex` char(1) NOT NULL,
  `s_phone` char(11) NOT NULL,
  `s_email` varchar(50) NOT NULL,
  `s_idcard` char(18) NOT NULL,
  `s_password` varchar(50) NOT NULL,
  `s_entertime` char(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_info`
--

/*!40000 ALTER TABLE `student_info` DISABLE KEYS */;
INSERT INTO `student_info` VALUES (1,'s23000002','少批','230001','00','m','13800138001','13800138001@qq.com','440302200601010010','123456','23'),(2,'s23030001','张三','230301','03','m','13899138001','1s9w2138001@qq.com','440306200502010010','1234567890','23'),(3,'s23111155','李四','231102','11','f','13992329983','82918342@qq.com','440102200409220000','genshinimpact','23'),(4,'s22060522','王五','220604','06','f','16911451419','wendy@139.com','440306200401251980','loveskadi','22');
/*!40000 ALTER TABLE `student_info` ENABLE KEYS */;

--
-- Table structure for table `teacher_course`
--

DROP TABLE IF EXISTS `teacher_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tid` char(7) NOT NULL,
  `cid` char(8) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_course`
--

/*!40000 ALTER TABLE `teacher_course` DISABLE KEYS */;
INSERT INTO `teacher_course` VALUES (1,'t000001','c0200001'),(2,'t000001','c0100002'),(3,'t020088','c0200001'),(4,'t220189','c0600003');
/*!40000 ALTER TABLE `teacher_course` ENABLE KEYS */;

--
-- Table structure for table `teacher_info`
--

DROP TABLE IF EXISTS `teacher_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tid` char(7) NOT NULL,
  `t_name` varchar(20) NOT NULL,
  `t_title` varchar(20) NOT NULL,
  `t_school` char(2) NOT NULL,
  `t_sex` char(1) NOT NULL,
  `t_phone` char(11) NOT NULL,
  `t_email` varchar(50) NOT NULL,
  `t_idcard` char(18) NOT NULL,
  `t_address` varchar(50) NOT NULL,
  `t_password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_info`
--

/*!40000 ALTER TABLE `teacher_info` DISABLE KEYS */;
INSERT INTO `teacher_info` VALUES (1,'t000001','张老师','教授','00','m','17391372971','1145141919810@qq.com','44030619870234','东区老图书馆2f206一座','123456'),(2,'t020088','李老师','副教授','02','f','12792739201','mambaout@ustc.com.cn','44010919770224','西区电三楼5f502一座','whatcanisay'),(3,'t220189','曼老师','研究员','22','m','13729372900','honkai@starrail.com','44030819990081','东区第一教学楼1f102二座','oshenqidong');
/*!40000 ALTER TABLE `teacher_info` ENABLE KEYS */;

--
-- Dumping routines for database 'StudentElectiveCourseWorkSystem'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-11 18:39:12
