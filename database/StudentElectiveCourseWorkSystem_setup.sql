-- MySQL dump 10.13  Distribute 8.3.0, for macos14.2 (arm64)
--
-- Host: localhost    Database: StudentElectiveCourseWorkSystem
-- ------------------------------------------------------
-- Server version	8.3.0

-- 设置原始字符集客户端变量为当前字符集客户端变量
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
-- 设置原始字符集结果变量为当前字符集结果变量
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
-- 设置原始排序规则连接变量为当前排序规则连接变量
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
-- 设置数据库字符集为utf8mb4以支持更广泛的语言字符
/*!50503 SET NAMES utf8mb4 */;
-- 设置原始时区变量为当前时区变量
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
-- 将时区设置为UTC以确保全局一致的时间比较
/*!40103 SET TIME_ZONE='+00:00' */;
-- 设置原始唯一检查变量为当前唯一检查变量，禁用唯一性检查
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
-- 设置原始外键检查变量为当前外键检查变量，禁用外键检查
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
-- 设置原始SQL模式变量为当前SQL模式变量，禁用自动插入零
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
-- 设置原始SQL注释变量为当前SQL注释变量，禁用SQL注释
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


--
-- Table structure for table `admin_info`
--

-- 如果表`admin_info`存在，则删除它，为创建新表做准备
DROP TABLE IF EXISTS `admin_info`;

-- 设置客户端字符集为utf8mb4，以确保数据的正确编码和解码
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;

-- 创建`admin_info`表，用于存储管理员信息
-- 表结构包括管理员ID（自增长）、管理员编号、管理员名称和管理员密码
CREATE TABLE `admin_info` (
  `id` int NOT NULL AUTO_INCREMENT, -- 管理员ID，作为主键，自增长
  `aid` char(5) NOT NULL, -- 管理员编号，固定长度为5
  `a_name` varchar(20) NOT NULL, -- 管理员名称，最大长度为20
  `a_password` varchar(50) NOT NULL, -- 管理员密码，最大长度为50
  PRIMARY KEY (`id`) -- 将管理员ID设置为主键
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 恢复之前设置的客户端字符集
/*!40101 SET character_set_client = @saved_cs_client */;

/*
 * 禁用`admin_info`表的索引
 *
 * 该语句用于在数据库操作中暂时禁用`admin_info`表的索引，以提高某些操作的性能。
 * 当需要大量插入或更新数据时，禁用索引可以减少索引维护的开销，但也会降低数据查询的速度。
 * 应在操作完成后通过ENABLE KEYS重新启用索引。
 */
/*!40000 ALTER TABLE `admin_info` DISABLE KEYS */;

/*
 * 启用`admin_info`表的索引
 *
 * 该语句用于在数据库操作完成后重新启用`admin_info`表的索引。
 * 在禁用索引进行大量数据操作后，应重新启用索引以恢复查询性能和确保数据完整性。
 */
/*!40000 ALTER TABLE `admin_info` ENABLE KEYS */;



--
-- Table structure for table `course_info`
--

-- 如果表`course_info`已存在，则删除它
DROP TABLE IF EXISTS `course_info`;

-- 设置字符集为utf8mb4，以支持更广泛的字符集
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;

-- 创建`course_info`表，用于存储课程信息
CREATE TABLE `course_info` (
  `id` int NOT NULL AUTO_INCREMENT, -- 自增的主键，用于唯一标识每门课程
  `cid` char(8) NOT NULL, -- 课程编号，8位字符长度
  `c_name` varchar(30) NOT NULL, -- 课程名称，最长30个字符
  `c_info` varchar(100) NOT NULL, -- 课程简介，最长100个字符
  `c_time` varchar(100) NOT NULL, -- 课程时间，最长100个字符，格式待定
  `c_init` int NOT NULL, -- 课程开始时间，以整数形式存储，具体格式待定
  `c_end` int NOT NULL, -- 课程结束时间，以整数形式存储，具体格式待定
  `c_location` varchar(50) NOT NULL, -- 课程地点，最长50个字符
  `c_type` char(1) NOT NULL, -- 课程类型，用1个字符表示
  `c_school` char(2) NOT NULL, -- 所属学院，用2个字符表示
  `c_period` int NOT NULL, -- 课程周期，以整数形式表示
  `c_capacity` int NOT NULL, -- 课程容量，即最大可选课人数
  `c_current` int NOT NULL, -- 当前选课人数
  PRIMARY KEY (`id`) -- 主键索引
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 恢复之前的字符集设置
/*!40101 SET character_set_client = @saved_cs_client */;

-- 禁用表`course_info`上的索引
/*!40000 ALTER TABLE `course_info` DISABLE KEYS */;

-- 启用表`course_info`上的索引
/*!40000 ALTER TABLE `course_info` ENABLE KEYS */;


--
-- Table structure for table `student_course`
--

-- 如果表`student_course`存在，则删除它，为创建新表做准备
DROP TABLE IF EXISTS `student_course`;

-- 设置字符集为utf8mb4，以支持更广泛的字符集
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;

-- 创建`student_course`表，用于存储学生选课信息
CREATE TABLE `student_course` (
  `id` int NOT NULL AUTO_INCREMENT, -- `id`列是自增的主键，用于唯一标识每条选课记录
  `cid` char(8) NOT NULL, -- `cid`列存储课程编号，长度为8字符，是选课记录的必要组成部分
  `sid` char(9) NOT NULL, -- `sid`列存储学生编号，长度为9字符，是选课记录的必要组成部分
  PRIMARY KEY (`id`) -- 将`id`列设置为主键
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 恢复之前设置的字符集配置
/*!40101 SET character_set_client = @saved_cs_client */;

-- 禁用表`student_course`的索引，以进行后续的批量操作
/*!40000 ALTER TABLE `student_course` DISABLE KEYS */;

-- 启用表`student_course`的索引，完成批量操作后恢复索引功能
/*!40000 ALTER TABLE `student_course` ENABLE KEYS */;


--
-- Table structure for table `student_info`
--

-- 如果表`student_info`已存在，则删除它，为创建新表做准备
DROP TABLE IF EXISTS `student_info`;

-- 设置客户端字符集为utf8mb4，以确保数据的正确编码
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;

-- 创建`student_info`表，用于存储学生信息
CREATE TABLE `student_info` (
  `id` int NOT NULL AUTO_INCREMENT, -- `id`列是主键，自增长，用于唯一标识每个学生
  `sid` char(9) NOT NULL, -- `sid`列存储学生的学号，长度为9字符
  `s_name` varchar(20) NOT NULL, -- `s_name`列存储学生的名字，长度最多为20字符
  `s_class` char(6) NOT NULL, -- `s_class`列存储学生的班级，长度为6字符
  `s_school` char(2) NOT NULL, -- `s_school`列存储学生的学校代码，长度为2字符
  `s_sex` char(1) NOT NULL, -- `s_sex`列存储学生的性别，长度为1字符
  `s_phone` char(11) NOT NULL, -- `s_phone`列存储学生的联系电话，长度为11字符
  `s_email` varchar(50) NOT NULL, -- `s_email`列存储学生的电子邮件地址，长度最多为50字符
  `s_idcard` char(18) NOT NULL, -- `s_idcard`列存储学生的身份证号码，长度为18字符
  `s_password` varchar(50) NOT NULL, -- `s_password`列存储学生的密码，长度最多为50字符
  `s_entertime` char(2) NOT NULL, -- `s_entertime`列存储学生的入学时间，长度为2字符
  PRIMARY KEY (`id`) -- `id`列被设置为主键
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 恢复之前设置的客户端字符集
/*!40101 SET character_set_client = @saved_cs_client */;

-- 禁用`student_info`表上的索引，以进行后续的操作
/*!40000 ALTER TABLE `student_info` DISABLE KEYS */;

-- 启用`student_info`表上的索引，完成操作
/*!40000 ALTER TABLE `student_info` ENABLE KEYS */;


--
-- Table structure for table `teacher_course`
--

-- 如果表`teacher_course`存在，则删除它。这是为了确保在重新创建表时不会出现命名冲突。
DROP TABLE IF EXISTS `teacher_course`;

-- 设置字符集为utf8mb4，以支持更广泛的字符集和更高的性能。
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;

-- 创建`teacher_course`表，用于存储教师和课程的关联信息。
CREATE TABLE `teacher_course` (
  `id` int NOT NULL AUTO_INCREMENT, -- `id`列是主键，自动递增，用于唯一标识每条记录。
  `tid` char(7) NOT NULL, -- `tid`列存储教师的ID，长度为7字符。
  `cid` char(8) NOT NULL, -- `cid`列存储课程的ID，长度为8字符。
  PRIMARY KEY (`id`) -- `id`列被设置为主键
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 恢复之前的字符集设置。
/*!40101 SET character_set_client = @saved_cs_client */;

-- 禁用和启用表的索引，以优化表的操作，例如插入或删除操作。
/*!40000 ALTER TABLE `teacher_course` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacher_course` ENABLE KEYS */;


--
-- Table structure for table `teacher_info`
--

-- 如果表`teacher_info`存在，则删除它，为创建新表做准备
DROP TABLE IF EXISTS `teacher_info`;

-- 设置客户端字符集为utf8mb4，以确保数据的正确编码
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;

-- 创建`teacher_info`表，用于存储教师信息
CREATE TABLE `teacher_info` (
  `id` int NOT NULL AUTO_INCREMENT, -- `id`为教师唯一标识，自增长
  `tid` char(7) NOT NULL, -- `tid`为教师编号，长度为7字符
  `t_name` varchar(20) NOT NULL, -- `t_name`为教师姓名，长度不超过20字符
  `t_title` varchar(20) NOT NULL, -- `t_title`为教师职称，长度不超过20字符
  `t_school` char(2) NOT NULL, -- `t_school`为教师所在学校编码，长度为2字符
  `t_sex` char(1) NOT NULL, -- `t_sex`为教师性别，长度为1字符
  `t_phone` char(11) NOT NULL, -- `t_phone`为教师电话号码，长度为11字符
  `t_email` varchar(50) NOT NULL, -- `t_email`为教师电子邮件地址，长度不超过50字符
  `t_idcard` char(18) NOT NULL, -- `t_idcard`为教师身份证号码，长度为18字符
  `t_address` varchar(50) NOT NULL, -- `t_address`为教师家庭地址，长度不超过50字符
  `t_password` varchar(50) NOT NULL, -- `t_password`为教师密码，长度不超过50字符
  PRIMARY KEY (`id`) -- 将`id`设置为表的主键
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 暂时禁用表`teacher_info`上的索引，以进行后续操作
/*!40000 ALTER TABLE `teacher_info` DISABLE KEYS */;

-- 恢复表`teacher_info`上的索引
/*!40000 ALTER TABLE `teacher_info` ENABLE KEYS */;


--
-- Dumping routines for database 'StudentElectiveCourseWorkSystem'
--
# 设置时区为之前的设置
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
# 设置SQL模式为之前的设置
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
# 设置外键检查为之前的设置
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
# 设置唯一性检查为之前的设置
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
# 设置客户端字符集为之前的设置
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
# 设置结果字符集为之前的设置
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
# 设置连接字符集为之前的设置
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
# 设置SQL注释提示为之前的设置
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-28 17:07:46
