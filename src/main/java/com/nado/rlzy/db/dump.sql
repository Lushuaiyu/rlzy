-- MySQL dump 10.13  Distrib 5.7.20, for Win64 (x86_64)
--
-- Host: 47.96.126.70    Database: rlzy
-- ------------------------------------------------------
-- Server version	5.7.24-log

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
-- Table structure for table `attention`
--

DROP TABLE IF EXISTS `attention`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attention` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `useringId` int(11) DEFAULT NULL COMMENT '关注者id',
  `useredId` int(11) DEFAULT NULL COMMENT '被关注者id',
  `objectId` int(11) DEFAULT NULL COMMENT '关注的表id',
  `type` int(11) DEFAULT NULL COMMENT '1为报名表 0为招聘表',
  `status` int(11) DEFAULT '0' COMMENT '关注状态 0已关注 1取消关注',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attention`
--

LOCK TABLES `attention` WRITE;
/*!40000 ALTER TABLE `attention` DISABLE KEYS */;
INSERT INTO `attention` VALUES (1,2,999,1,1,0,'2018-11-20 16:26:54'),(2,4,3,1,1,0,'2018-11-20 16:27:18'),(3,3,999,2,1,0,'2018-11-20 16:52:37'),(4,4,999,3,1,0,'2018-11-20 16:52:49');
/*!40000 ALTER TABLE `attention` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collect`
--

DROP TABLE IF EXISTS `collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `briefchapter_id` int(11) NOT NULL COMMENT '简章id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `create_time` date DEFAULT NULL COMMENT '添加时间',
  `delete_flag` int(11) NOT NULL DEFAULT '0' COMMENT '是否取消收藏 0 收藏 1: 取消收藏',
  `sign_up_id` int(11) DEFAULT NULL COMMENT '报名表id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='收藏表\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collect`
--

LOCK TABLES `collect` WRITE;
/*!40000 ALTER TABLE `collect` DISABLE KEYS */;
INSERT INTO `collect` VALUES (1,1,4,'2019-07-02',0,1),(9,3,3,'2019-07-30',0,1),(10,2,3,'2019-07-07',1,1),(11,3,3,'2019-07-08',0,1);
/*!40000 ALTER TABLE `collect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL COMMENT '遇到的问题',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `name` varchar(255) NOT NULL COMMENT '反馈者姓名',
  `phone` varchar(255) NOT NULL COMMENT '反馈者手机号',
  `delete_flag` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除, 默认 0 没删除 1  已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,'fjsaidjijas iasj',4,'2018-11-20 03:54:26','yc','13801577536',0),(2,'一堆问题',5,'2019-07-25 00:41:42','aa','10236547896',0),(3,'这个问题比较严重',3,'2019-07-25 08:42:42','喵喵','01236547895',0),(4,'miao',3,'2019-07-25 12:09:39','zs','13864578452',0),(5,'miao',3,'2019-07-26 03:43:05','zs','13864578452',0);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `helpcenter`
--

DROP TABLE IF EXISTS `helpcenter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `helpcenter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `helpcenter`
--

LOCK TABLES `helpcenter` WRITE;
/*!40000 ALTER TABLE `helpcenter` DISABLE KEYS */;
INSERT INTO `helpcenter` VALUES (1,'测试标题','dasfaesfdasfds','2018-11-20 12:02:07',0),(2,'测试标题2','dasfaesfdasfsdafds','2018-11-20 12:02:19',0);
/*!40000 ALTER TABLE `helpcenter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_acct`
--

DROP TABLE IF EXISTS `hr_acct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_acct` (
  `Id` int(32) NOT NULL AUTO_INCREMENT COMMENT '账户表',
  `UserId` int(32) DEFAULT NULL COMMENT '用户Id',
  `AcctBalance` decimal(10,0) DEFAULT '0' COMMENT '账户金额',
  `IceBalance` decimal(10,0) DEFAULT '0' COMMENT '冻结金额',
  `DeleteFlag` tinyint(4) DEFAULT '0' COMMENT '0:未删除  1:已删除',
  `modifyTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `CreateTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_acct`
--

LOCK TABLES `hr_acct` WRITE;
/*!40000 ALTER TABLE `hr_acct` DISABLE KEYS */;
INSERT INTO `hr_acct` VALUES (1,2,1000,100,0,'2019-07-24 08:39:56','2019-10-29 09:44:03'),(2,3,0,200,0,'2019-07-19 07:49:34','2018-10-29 09:44:05'),(3,4,0,100,0,'2019-07-24 07:23:46','2018-10-30 09:10:33'),(4,7,0,700,0,'2019-07-20 06:11:53','2019-01-10 02:27:55'),(5,8,0,2000,0,'2019-07-19 09:27:18','2019-01-10 02:27:56');
/*!40000 ALTER TABLE `hr_acct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_acct_detail`
--

DROP TABLE IF EXISTS `hr_acct_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_acct_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '账户明细表',
  `acctId` int(11) DEFAULT NULL COMMENT '账户id',
  `amount` decimal(10,0) DEFAULT NULL COMMENT '交易金额',
  `beforeAmount` decimal(10,0) DEFAULT NULL COMMENT '交易前金额',
  `afterAmount` decimal(10,0) DEFAULT NULL COMMENT '交易后金额',
  `createTime` datetime DEFAULT NULL,
  `withdrawWay` int(11) DEFAULT NULL COMMENT '提现方式 0支付宝 1银行卡',
  `payWay` int(11) DEFAULT NULL COMMENT '付款方式 0支付宝 1微信 2线下',
  `status` int(11) DEFAULT '0' COMMENT '账户状态 0未付款 1已付款 2审核失败 ',
  `type` int(255) DEFAULT NULL COMMENT '0 充值 1被返佣扣款 2返佣获得 3提现',
  `alipayNum` varchar(255) DEFAULT NULL COMMENT '支付宝账号',
  `withdrawAmount` decimal(10,0) DEFAULT NULL COMMENT '提现金额',
  `accountName` varchar(255) DEFAULT NULL COMMENT '开户人姓名',
  `bankName` varchar(255) DEFAULT NULL COMMENT '开户行',
  `bankcardNum` varchar(255) DEFAULT NULL COMMENT '银行卡号',
  `signUpId` int(11) DEFAULT NULL COMMENT '返佣的报名表id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_acct_detail`
--

LOCK TABLES `hr_acct_detail` WRITE;
/*!40000 ALTER TABLE `hr_acct_detail` DISABLE KEYS */;
INSERT INTO `hr_acct_detail` VALUES (1,1,200,1100,900,'2018-10-31 00:00:00',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,2,300,2200,1900,'2019-07-30 10:32:25',NULL,NULL,0,2,NULL,NULL,NULL,NULL,NULL,NULL),(3,3,200,200,200,'2018-10-14 00:00:00',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,2),(4,3,300,200,200,'2018-11-14 00:00:00',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,2),(5,3,400,200,200,'2018-11-14 00:00:00',NULL,NULL,1,2,NULL,NULL,NULL,NULL,NULL,2),(6,3,400,200,200,'2018-11-14 00:00:00',NULL,1,1,0,NULL,NULL,NULL,NULL,NULL,NULL),(7,3,400,200,200,'2018-11-14 00:00:00',NULL,NULL,1,2,NULL,NULL,NULL,NULL,NULL,NULL),(8,3,100,1000,1100,'2018-11-15 00:00:00',NULL,0,1,0,NULL,NULL,NULL,NULL,NULL,NULL),(9,3,25,NULL,NULL,'2018-11-15 00:00:00',NULL,2,0,0,NULL,NULL,NULL,NULL,NULL,NULL),(10,3,400,200,200,'2018-11-15 00:00:00',NULL,2,1,0,NULL,NULL,NULL,NULL,NULL,NULL),(11,3,400,200,200,'2018-11-15 00:00:00',NULL,2,2,0,NULL,NULL,NULL,NULL,NULL,NULL),(14,3,1000,NULL,NULL,'2018-11-16 00:00:00',0,NULL,0,3,'15750852005',1,NULL,NULL,NULL,NULL),(15,3,1500,NULL,NULL,'2018-11-19 00:00:00',1,NULL,0,3,NULL,2,NULL,NULL,NULL,NULL),(16,3,1500,NULL,NULL,'2018-11-19 00:00:00',1,NULL,0,3,NULL,2,NULL,NULL,'11111',NULL),(17,1,100,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,2),(18,1,100,NULL,NULL,'2019-07-17 00:00:00',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,2),(19,2,100,NULL,NULL,'2019-07-17 00:00:00',NULL,NULL,0,2,NULL,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `hr_acct_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_briefchapter`
--

DROP TABLE IF EXISTS `hr_briefchapter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_briefchapter` (
  `Id` int(32) NOT NULL AUTO_INCREMENT COMMENT '简章表',
  `RecruitedCompanyId` int(32) DEFAULT NULL COMMENT '被招聘企业ID',
  `PostId` int(32) DEFAULT NULL COMMENT '招聘岗位ID',
  `jobNatureId` varchar(32) DEFAULT NULL COMMENT '工作性质',
  `RecruitingNo` int(32) DEFAULT NULL COMMENT '招聘人数',
  `avgSalary` int(11) DEFAULT '0' COMMENT '综合工资',
  `detailSalary` varchar(255) DEFAULT NULL COMMENT '结算工资',
  `detailSalaryWay` varchar(255) DEFAULT NULL COMMENT '结算工资方式',
  `educationId` varchar(64) DEFAULT NULL COMMENT '学历',
  `experienceId` varchar(64) DEFAULT NULL COMMENT '经验',
  `welfareId` varchar(64) DEFAULT NULL COMMENT '福利',
  `PostDetail` varchar(200) DEFAULT NULL COMMENT '职位描述',
  `interviewAddress` int(11) DEFAULT NULL COMMENT '0用人单位 1非用人单位',
  `registerTime` datetime DEFAULT NULL COMMENT '报道时间',
  `contractWay` int(11) DEFAULT NULL COMMENT '合同签订方 0招聘单位 1用工单位 2厂方指定劳务公司',
  `contract_way_detail_id` varchar(255) DEFAULT NULL COMMENT '合同签订方详情id(用工方式\n)',
  `contractTime` varchar(255) DEFAULT NULL COMMENT '合同到期时间',
  `hireWay` int(11) DEFAULT NULL COMMENT '录取方式 0完全直录 1可以直录 2不可直录',
  `Rebate` int(11) DEFAULT NULL COMMENT '0:无返佣 1:有返佣',
  `userId` int(11) DEFAULT NULL COMMENT '提交人',
  `Status` int(32) DEFAULT '0' COMMENT '状态 0待审核 1通过(正在招)  2未通过 3已结束 -1接口暂时保存，后台不需要取',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注，不通过是用到',
  `CheckedTime` timestamp NULL DEFAULT NULL COMMENT '审核通过时间',
  `readNum` int(11) DEFAULT '0' COMMENT '浏览次数',
  `CreateTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `DeleteFlag` int(11) DEFAULT '0' COMMENT '0:未删除  1:已删除',
  `man_age_id` int(11) DEFAULT NULL COMMENT '男员工年龄段',
  `women_age_id` int(11) DEFAULT NULL COMMENT '女员工年龄段',
  `work_way_id` varchar(255) DEFAULT NULL COMMENT '工作方式',
  `work_time_arrange_id` varchar(255) DEFAULT NULL COMMENT '工作时间安排',
  `clothing_requirement_id` varchar(255) DEFAULT NULL COMMENT '服装要求',
  `hobby_id` varchar(255) DEFAULT NULL COMMENT '兴趣爱好',
  `overtime_times_id` int(11) DEFAULT NULL COMMENT '加班时长 id',
  `description_job_photo_url` varchar(255) DEFAULT NULL COMMENT '职位描述图片url',
  `accept_recommended_resume` int(11) DEFAULT NULL COMMENT '是否接受平台推荐简历 0: 是 1: 否',
  `employer_certificate_photo_url` varchar(255) DEFAULT NULL COMMENT '用人单位证明图片 url',
  `man_num` int(11) DEFAULT NULL COMMENT '男人招聘数量段',
  `women_num` int(11) DEFAULT NULL COMMENT '女人招聘数量',
  `no_employer_address` varchar(255) DEFAULT NULL COMMENT '非用人单位面试地址',
  `profession_id` varchar(255) DEFAULT NULL COMMENT '专业id',
  `work_address` varchar(255) DEFAULT NULL COMMENT '工作地点',
  `contract_start_time` date DEFAULT NULL COMMENT '合同开始时间',
  `interviewTime` datetime DEFAULT NULL COMMENT '面试时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_briefchapter`
--

LOCK TABLES `hr_briefchapter` WRITE;
/*!40000 ALTER TABLE `hr_briefchapter` DISABLE KEYS */;
INSERT INTO `hr_briefchapter` VALUES (1,2,1,'3',3,21000,'300','天','11','56','3','bbb',1,'2019-07-19 15:48:00',0,'41,43,44','2019-11-17 16:48',0,1,8,0,NULL,NULL,20,'2018-12-19 05:40:24',0,11,12,'27','30','19','22',34,NULL,0,NULL,11,10,'你好街','49,50','aaaa','2019-05-07','2019-07-09 10:00:00'),(2,3,2,'3',3,12000,'20','小时','12','65','3,4','cccc',0,'2019-07-22 16:48:00',0,'41,42','-12-17 16:482019',0,1,8,1,NULL,NULL,21,'2018-12-19 05:45:57',0,11,11,'27','33','20','26',35,NULL,1,NULL,24,12,'llfasdf街','51,52','eeeee','2019-07-22','2019-05-15 14:00:00'),(3,5,3,'3',3,13000,'300','天','10','58','4','dddd',1,'2019-07-25 16:48:00',1,'41,43,44','2020-12-17 16:48',2,1,7,2,NULL,NULL,56,'2018-12-19 05:47:13',0,13,14,'28','31','18','24',36,NULL,1,NULL,20,30,'按说大道','52,53','iiiiii','2019-07-14','2019-07-26 10:00:00'),(4,5,1,'3',3,8000,'20','小时','64','65','3,4','miaomiao',0,'2019-07-21 16:48:00',0,'41,42,43,44','2019-12-08 16:48',1,0,3,3,NULL,NULL,56,'2018-12-19 06:04:38',0,13,12,'29','33','18','25',66,NULL,0,NULL,12,8,'皈依路','50,51','安静路','2019-07-07','2019-07-17 09:00:00'),(5,2,1,'3',3,2000,'300','天','9','56','3','aaa',0,'2019-09-21 16:48:00',1,'43,44','09',1,1,4,2,NULL,NULL,54,'2018-12-19 06:05:31',0,12,13,'27','31','21','23',34,NULL,0,NULL,16,5,'安创路','53,54','静安寺','2019-07-07','2017-08-10 13:00:00'),(6,3,4,'5',2,15000,'20','小时','64','65','3,4','eeeee',0,'2019-08-28 16:48:00',0,'41,43','2019-07-17 16:48',0,0,6,2,NULL,NULL,67,'2018-12-19 06:40:05',0,11,14,'28','33','20','24',34,NULL,1,NULL,10,23,'仁和路','49,50,51','永乐路','2019-07-21','2019-08-14 15:00:00'),(7,5,5,'5',3,0,'6000','月','7','56','3','ffffff',1,'2019-08-19 16:48:00',1,'44,67,68','2021-12-17 16:48',1,1,5,2,NULL,NULL,76,'2018-12-19 06:44:01',0,14,11,'28','30','19','25',66,NULL,1,NULL,2,5,'康庄路','50,51,52','常州街','2019-07-20','2019-08-13 11:00:00');
/*!40000 ALTER TABLE `hr_briefchapter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_complaint`
--

DROP TABLE IF EXISTS `hr_complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_complaint` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '投诉记录表',
  `brief_chapter_id` int(32) DEFAULT NULL COMMENT '被投诉企业简章Id',
  `complaint_type_id` varchar(32) DEFAULT NULL COMMENT '投诉类型Id',
  `description` varchar(32) DEFAULT NULL COMMENT '问题描述',
  `evidence` varchar(256) DEFAULT NULL COMMENT '凭证',
  `name` varchar(255) DEFAULT NULL COMMENT '投诉人',
  `phone` varchar(255) DEFAULT NULL COMMENT '投诉人手机',
  `status` int(32) DEFAULT '0' COMMENT '状态 1待解决 2已撤销 3已处理 4 企业7天之后未处理, 平台后台处理',
  `delete_flag` int(11) DEFAULT '0' COMMENT '0:未删除  1:已删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `sign_up_id` int(11) NOT NULL COMMENT '求职表主键id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_complaint`
--

LOCK TABLES `hr_complaint` WRITE;
/*!40000 ALTER TABLE `hr_complaint` DISABLE KEYS */;
INSERT INTO `hr_complaint` VALUES (1,3,'1','该企业随意散布虚假消息','dddddd','xiaoming','13877777',3,0,'2018-10-30 00:00:00',1,1),(2,2,'2','fasasasasasasasasasasas','rereeer','yc','111112',1,0,'2018-11-19 00:00:00',2,2),(3,4,'2','ffsfffsdfsdf','ddddd','aa','18629871278',2,0,'2019-07-10 00:00:00',3,2),(4,5,'3','1111','fdfd','miao',NULL,1,0,'2019-07-25 00:00:00',3,1),(5,6,'4','1111','fdfd','miao',NULL,0,0,'2019-07-25 00:00:00',2,1),(6,1,'1','1111','fdfd','miao',NULL,2,0,'2019-07-10 00:00:00',5,1);
/*!40000 ALTER TABLE `hr_complaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_complaintstatus`
--

DROP TABLE IF EXISTS `hr_complaintstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_complaintstatus` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '投诉状态表',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `delete_flag` tinyint(1) DEFAULT '0' COMMENT '0:未删除  1:已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_complaintstatus`
--

LOCK TABLES `hr_complaintstatus` WRITE;
/*!40000 ALTER TABLE `hr_complaintstatus` DISABLE KEYS */;
INSERT INTO `hr_complaintstatus` VALUES (1,'待解决',0),(2,'已撤销',0),(3,'已生效',0);
/*!40000 ALTER TABLE `hr_complaintstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_complainttype`
--

DROP TABLE IF EXISTS `hr_complainttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_complainttype` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '投诉类型表',
  `complaint_type` varchar(32) DEFAULT NULL COMMENT '投诉类型',
  `delete_flag` tinyint(1) DEFAULT '0' COMMENT '0:未删除  1:已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_complainttype`
--

LOCK TABLES `hr_complainttype` WRITE;
/*!40000 ALTER TABLE `hr_complainttype` DISABLE KEYS */;
INSERT INTO `hr_complainttype` VALUES (1,'虚假消息1',0),(2,'返费问题',0),(3,'客服态度恶劣2',0),(4,'你猜的问题',0);
/*!40000 ALTER TABLE `hr_complainttype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_configuration`
--

DROP TABLE IF EXISTS `hr_configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_configuration` (
  `Id` int(32) NOT NULL AUTO_INCREMENT COMMENT '配置表--废弃',
  `Name` varchar(64) DEFAULT NULL COMMENT '配置项名称',
  `DeleteFlag` tinyint(4) DEFAULT '0' COMMENT '0:未删除  1:已删除',
  `CreateTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_configuration`
--

LOCK TABLES `hr_configuration` WRITE;
/*!40000 ALTER TABLE `hr_configuration` DISABLE KEYS */;
INSERT INTO `hr_configuration` VALUES (1,'工作岗位',0,NULL),(2,'投诉类型',0,NULL),(3,'推荐人身份',0,NULL),(4,'招聘者身份',0,NULL);
/*!40000 ALTER TABLE `hr_configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_configuration_child`
--

DROP TABLE IF EXISTS `hr_configuration_child`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_configuration_child` (
  `Id` int(32) NOT NULL AUTO_INCREMENT COMMENT '配置表子项--废弃',
  `Name` varchar(64) DEFAULT NULL COMMENT '子项名称',
  `Pid` int(32) DEFAULT NULL COMMENT '父ID',
  `DeleteFlag` tinyint(4) DEFAULT '0' COMMENT '0:未删除  1:已删除',
  `CreateTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_configuration_child`
--

LOCK TABLES `hr_configuration_child` WRITE;
/*!40000 ALTER TABLE `hr_configuration_child` DISABLE KEYS */;
INSERT INTO `hr_configuration_child` VALUES (1,NULL,1,0,NULL),(2,'普工',1,0,NULL),(3,'操作工',1,0,NULL),(4,NULL,2,0,NULL),(5,'虚假信息',2,0,NULL),(6,'返费问题',2,0,NULL),(7,NULL,3,0,NULL),(8,'本人',3,0,NULL),(9,'老师',3,0,NULL),(10,'同学',3,0,NULL),(11,'中介',3,0,NULL),(12,NULL,4,0,NULL),(13,'招聘单位',4,0,NULL),(14,'代招单位',4,0,NULL),(15,NULL,0,0,NULL),(16,'熟练工',1,0,NULL);
/*!40000 ALTER TABLE `hr_configuration_child` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_group`
--

DROP TABLE IF EXISTS `hr_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_group` (
  `Id` int(32) NOT NULL AUTO_INCREMENT COMMENT '组织表',
  `GroupName` varchar(64) DEFAULT NULL COMMENT '组织名称',
  `GroupInfo` varchar(255) DEFAULT NULL COMMENT '公司简介',
  `legalPerson` varchar(255) DEFAULT NULL COMMENT '法人',
  `socialCreditCode` varchar(255) DEFAULT NULL COMMENT '统一社会信用代码',
  `registrationPlace` varchar(255) DEFAULT NULL COMMENT '注册地',
  `interview_address` varchar(255) DEFAULT NULL COMMENT '面试地址',
  `GroupAddress` varchar(64) DEFAULT NULL COMMENT '公司地址',
  `IdentifyTime` timestamp NULL DEFAULT NULL COMMENT '认证时间',
  `CertificationUnitId` int(32) DEFAULT NULL COMMENT '认证单位Id(代招单位会有多家认证单位)',
  `BusinessLicense` varchar(256) DEFAULT NULL COMMENT '营业执照',
  `HelpProve` varchar(256) DEFAULT NULL COMMENT '代招证明',
  `remark` varchar(255) DEFAULT NULL COMMENT '审核未通过备注',
  `Status` int(32) DEFAULT '0' COMMENT '状态 1待审核 2审核通过 3审核未通过',
  `GroupTypeId` int(32) DEFAULT NULL COMMENT '组织身份Id',
  `CertifierId` int(32) DEFAULT NULL COMMENT '提交认证人Id',
  `foulNum` int(11) DEFAULT '0' COMMENT '违规次数',
  `CreateTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `DeleteFlag` int(11) DEFAULT '0' COMMENT '0:未删除  1:已删除',
  `Pid` int(32) DEFAULT NULL COMMENT '父Id',
  `person_num` varchar(255) DEFAULT NULL COMMENT '公司人数',
  `co_policy` varchar(255) DEFAULT NULL COMMENT '公司制度',
  `enterprise_license` varchar(255) DEFAULT NULL COMMENT '企业许可证',
  `registrant_certificate` varchar(255) DEFAULT NULL COMMENT '注册人委托证明',
  PRIMARY KEY (`Id`),
  KEY `Pid` (`Pid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_group`
--

LOCK TABLES `hr_group` WRITE;
/*!40000 ALTER TABLE `hr_group` DISABLE KEYS */;
INSERT INTO `hr_group` VALUES (1,'苏州永拓人力资源有限公司',NULL,NULL,NULL,NULL,'上海宝山喵喵路','苏州市吴中区星湖街328号','2018-10-23 08:56:55',1,'营业执照a',NULL,NULL,0,1,7,1,'2019-01-10 07:46:03',0,NULL,'100-500','股份制',NULL,NULL),(2,'佳能苏州电子厂',NULL,NULL,NULL,NULL,'无锡','苏州市吴中区星湖街328号','2018-10-17 10:03:14',1,'营业执照b',NULL,NULL,1,2,NULL,0,'2019-01-10 07:46:06',0,1,'20-99','股份制',NULL,NULL),(3,'华硕苏州电子厂','公司简介','法人','3333','天堂','南京','苏州市吴中区星湖街328号','2018-10-29 06:31:02',1,'营业执照c',NULL,'信息不全',2,2,NULL,2,'2019-01-10 07:46:07',0,1,'20-99','股份制',NULL,NULL),(4,'测试企业','企业简介','法人','123','问津路','吴中区','龙泉路',NULL,NULL,'营业执照d','',NULL,3,NULL,8,0,'2019-01-10 07:46:22',0,NULL,'100-500','股份制',NULL,NULL),(5,'喵喵厂','公司简介','喵星人','123','漕宝路','相城区','龙泉大道','2019-06-25 20:00:23',1,'营业执照e',NULL,NULL,2,2,NULL,0,'2019-06-25 20:00:37',0,1,'200-300','股份制',NULL,NULL),(6,'喵喵公司','这个公司很厉害的',NULL,NULL,NULL,'大于街道','aa街道',NULL,NULL,'urlddd',NULL,NULL,0,NULL,NULL,0,NULL,0,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `hr_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_groupstatus`
--

DROP TABLE IF EXISTS `hr_groupstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_groupstatus` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '企业状态表',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `deleteFlag` tinyint(1) DEFAULT '0' COMMENT '0:未删除  1:已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_groupstatus`
--

LOCK TABLES `hr_groupstatus` WRITE;
/*!40000 ALTER TABLE `hr_groupstatus` DISABLE KEYS */;
INSERT INTO `hr_groupstatus` VALUES (1,'待审核',0),(2,'审核通过',0),(3,'审核未通过',0),(4,'已打款',0);
/*!40000 ALTER TABLE `hr_groupstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_grouptype`
--

DROP TABLE IF EXISTS `hr_grouptype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_grouptype` (
  `Id` int(32) NOT NULL AUTO_INCREMENT COMMENT '组织身份表',
  `GroupType` varchar(32) DEFAULT NULL COMMENT '组织身份',
  `CreateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `DeleteFlag` tinyint(4) DEFAULT '0' COMMENT '0:未删除  1:已删除',
  `Pid` int(32) DEFAULT NULL COMMENT '父Id',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_grouptype`
--

LOCK TABLES `hr_grouptype` WRITE;
/*!40000 ALTER TABLE `hr_grouptype` DISABLE KEYS */;
INSERT INTO `hr_grouptype` VALUES (1,'代招单位','2018-10-23 08:51:52',0,NULL),(2,'招聘单位','2018-10-23 08:35:59',0,NULL);
/*!40000 ALTER TABLE `hr_grouptype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_identity`
--

DROP TABLE IF EXISTS `hr_identity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_identity` (
  `Id` int(32) NOT NULL AUTO_INCREMENT COMMENT '身份表',
  `Name` varchar(32) DEFAULT NULL COMMENT '名称',
  `Type` int(1) DEFAULT NULL COMMENT '0推荐人身份 1招聘者身份',
  `DeleteFlag` tinyint(1) DEFAULT '0' COMMENT '0:未删除  1:已删除',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_identity`
--

LOCK TABLES `hr_identity` WRITE;
/*!40000 ALTER TABLE `hr_identity` DISABLE KEYS */;
INSERT INTO `hr_identity` VALUES (1,'本人',0,0),(2,'中介',0,0),(3,'老师',0,0),(4,'同学',0,0),(5,'招聘单位',1,0),(6,'代招单位',1,0),(7,'1234',1,1);
/*!40000 ALTER TABLE `hr_identity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_menu`
--

DROP TABLE IF EXISTS `hr_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_menu` (
  `id` varchar(11) NOT NULL COMMENT '后台菜单表主键',
  `pId` int(11) DEFAULT NULL COMMENT '父级栏目',
  `name` varchar(32) DEFAULT NULL COMMENT '栏目名称',
  `type` int(2) DEFAULT NULL COMMENT '栏目级别',
  `url` varchar(255) DEFAULT NULL COMMENT '配置地址',
  `status` int(1) DEFAULT NULL COMMENT '栏目状态（0关闭、1开启）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_menu`
--

LOCK TABLES `hr_menu` WRITE;
/*!40000 ALTER TABLE `hr_menu` DISABLE KEYS */;
INSERT INTO `hr_menu` VALUES ('1',0,'用户管理',1,'',1),('101',1,'推荐人列表',2,'user/getAllUser.do',1),('102',1,'招聘者列表',2,'user/getAllRecruiter.do',1),('2',0,'简章管理',1,NULL,1),('201',2,'简章列表',2,'briefChapter/getAllBriefChapter.do',1),('3',0,'企业管理',1,NULL,1),('301',3,'企业列表',2,'group/getGroupList.do',1),('4',0,'财务管理',1,NULL,1),('402',4,'充值列表',2,'recharge/getAllRechargeList.do',1),('403',4,'提现列表',2,'withdraw/getAllWithdrawRecord.do',1),('5',0,'投诉管理',1,NULL,1),('501',5,'投诉列表',2,'complaint/getAllComplaintRecord.do',1),('6',0,'配置管理',1,NULL,1),('601',6,'岗位配置',2,'post/getAllPost.do',1),('602',6,'投诉类型配置',2,'complaintType/getAllComplaintType.do',1),('603',6,'推荐人身份配置',2,'identity/getAllIdentity.do',1),('604',6,'招聘者身份配置',2,'identity/getAllIdentity1.do',1),('7',0,'账号管理',1,NULL,1),('701',7,'管理员列表',2,'user/getAllAdmin.do',1),('702',7,'角色列表',2,'role/getAllRole.do',1);
/*!40000 ALTER TABLE `hr_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_post`
--

DROP TABLE IF EXISTS `hr_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_post` (
  `Id` int(32) NOT NULL AUTO_INCREMENT COMMENT '岗位表',
  `Name` varchar(64) DEFAULT NULL COMMENT '岗位名称',
  `DeleteFlag` tinyint(4) DEFAULT '0' COMMENT '0:未删除  1:已删除',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_post`
--

LOCK TABLES `hr_post` WRITE;
/*!40000 ALTER TABLE `hr_post` DISABLE KEYS */;
INSERT INTO `hr_post` VALUES (1,'维修工',0,'2019-05-09 11:19:34'),(2,'普工',0,'2019-04-28 11:19:37'),(3,'工程师',0,'2019-05-23 11:19:41'),(4,'开发',0,'2019-05-14 11:19:45'),(5,'高级开发工程师',0,'2019-06-28 13:49:20');
/*!40000 ALTER TABLE `hr_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_rebaterecord`
--

DROP TABLE IF EXISTS `hr_rebaterecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_rebaterecord` (
  `Id` int(32) NOT NULL AUTO_INCREMENT COMMENT '返佣记录表',
  `BriefChapterId` int(32) DEFAULT NULL COMMENT '简章表Id',
  `RebateType` int(11) DEFAULT NULL COMMENT '返佣类型 0:面试 1:报到 2:入职',
  `RebateMale` decimal(10,0) DEFAULT NULL COMMENT '返佣金额男',
  `RebateFemale` decimal(10,0) DEFAULT NULL COMMENT '返佣金额女',
  `RebateTime` date DEFAULT NULL COMMENT '返佣时间',
  `status` int(11) DEFAULT '0' COMMENT '0待返佣 1已返佣 2不返佣 3已确认 41 给付已结束 42 给付失败 43 给付中',
  `DeleteFlag` int(11) DEFAULT '0' COMMENT '0:未删除  1:已删除',
  `CreateTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `rebate_variable` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '女返佣金额变量',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_rebaterecord`
--

LOCK TABLES `hr_rebaterecord` WRITE;
/*!40000 ALTER TABLE `hr_rebaterecord` DISABLE KEYS */;
INSERT INTO `hr_rebaterecord` VALUES (1,6,0,200,100,'2019-07-04',0,0,'2019-01-19 05:45:57',0),(2,1,0,679,6768,'2019-07-22',2,0,'2019-01-19 05:47:13',7900),(3,2,1,33,54,'2018-07-13',42,0,'2019-07-23 04:47:13',0),(4,2,2,200,150,'2018-07-27',2,0,'2019-08-19 05:47:13',0),(5,3,0,200,100,'2018-05-09',41,0,'2019-02-19 06:04:39',155),(6,3,1,200,200,'2019-07-07',2,0,'2019-07-24 06:04:39',0),(7,3,2,200,150,'2019-07-01',0,0,'2019-08-26 06:04:39',0),(8,4,0,200,100,'2019-07-21',2,0,'2019-01-23 06:05:31',0),(9,4,2,200,200,'2019-07-28',0,0,'2019-08-25 06:05:31',0),(10,4,1,200,150,'2019-07-24',41,0,'2019-07-20 06:05:32',0),(11,5,2,200,400,'2018-10-11',1,0,'2019-08-25 08:33:53',0),(12,5,1,200,150,'2019-06-06',42,0,'2019-07-24 08:33:54',0),(13,6,1,200,300,'2019-07-22',1,0,'2019-07-26 01:40:04',0),(14,6,2,200,300,'2019-07-15',43,0,'2019-07-25 03:22:32',0);
/*!40000 ALTER TABLE `hr_rebaterecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_recharge`
--

DROP TABLE IF EXISTS `hr_recharge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_recharge` (
  `Id` int(32) NOT NULL AUTO_INCREMENT COMMENT '充值记录表',
  `UserId` int(32) DEFAULT NULL COMMENT '被充值者Id',
  `RechargeTime` timestamp NULL DEFAULT NULL COMMENT '充值时间',
  `RechargeType` varchar(32) DEFAULT NULL COMMENT '充值方式',
  `RechargeAmount` decimal(10,0) DEFAULT NULL COMMENT '充值金额',
  `OperatorId` int(32) DEFAULT NULL COMMENT '操作人',
  `Status` int(11) DEFAULT NULL COMMENT '线下支付需审核--状态:0待审核 1审核通过 2审核未通过',
  `DeleteFlag` tinyint(4) DEFAULT '0' COMMENT '0:未删除  1:已删除',
  `CreateTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_recharge`
--

LOCK TABLES `hr_recharge` WRITE;
/*!40000 ALTER TABLE `hr_recharge` DISABLE KEYS */;
INSERT INTO `hr_recharge` VALUES (1,2,'2019-01-01 09:41:24','支付宝',10000,3,NULL,0,'2018-10-29 09:43:43'),(2,2,'2019-02-19 09:54:29','线下',10000,3,0,0,'2018-10-29 09:54:46');
/*!40000 ALTER TABLE `hr_recharge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_role`
--

DROP TABLE IF EXISTS `hr_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_role` (
  `Id` int(32) NOT NULL AUTO_INCREMENT COMMENT '角色表',
  `Name` varchar(64) NOT NULL COMMENT '名称',
  `Description` varchar(200) DEFAULT NULL COMMENT '描述',
  `Status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态（已启用1、已禁用0）',
  `CreateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `DeleteFlag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0:未删除  1:已删除',
  `Power` longtext COMMENT '权限',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_role`
--

LOCK TABLES `hr_role` WRITE;
/*!40000 ALTER TABLE `hr_role` DISABLE KEYS */;
INSERT INTO `hr_role` VALUES (1,'超级管理员','拥有所有权限',1,'2019-02-25 07:20:50',0,'101R,102R,201R,301R,402R,403R,501R,601R,701R,702R,101U,102U,201U,301U,402U,403U,501U,601U,701U,702U,101C,102C,201C,301C,402C,403C,501C,601C,701C,702C,101D,102D,201D,301D,402D,403D,501D,601D,701D,702D',0),(2,'财务人员','负责财务审计',1,'2019-05-28 08:17:21',0,'601R,601C',0),(4,'简章审核人员','审核简章',1,'2019-02-26 08:45:48',0,NULL,0),(6,'user','登录权限',1,'2019-07-05 00:56:25',0,NULL,0);
/*!40000 ALTER TABLE `hr_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_signup`
--

DROP TABLE IF EXISTS `hr_signup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_signup` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '报名表',
  `user_id` int(32) DEFAULT NULL COMMENT '用户表Id',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex` int(11) DEFAULT NULL COMMENT '0:女  1:男',
  `id_card` varchar(32) DEFAULT NULL COMMENT '身份证',
  `education` varchar(32) DEFAULT NULL COMMENT '学历',
  `graduation_time` date DEFAULT NULL COMMENT '毕业时间',
  `profession` varchar(32) DEFAULT NULL COMMENT '专业',
  `registration_position_id` varchar(255) DEFAULT NULL COMMENT '意向岗位Id(可多选)',
  `arrival_time` date DEFAULT NULL COMMENT '到岗时间',
  `expected_salary_upper` decimal(10,0) DEFAULT NULL COMMENT '期望薪资上限',
  `expected_salary_lower` decimal(10,0) DEFAULT NULL COMMENT '期望薪资下限',
  `relation` int(11) DEFAULT NULL COMMENT '与推荐人关系',
  `it_is_public` int(11) DEFAULT NULL COMMENT '是否公开(0: 公开 1: 不公开)',
  `agree_platform_help` int(11) DEFAULT NULL COMMENT '是否获取平台帮助(0: 获取 1: 不获取)',
  `create_time` datetime DEFAULT NULL COMMENT '报名时间',
  `delete_flag` int(11) NOT NULL DEFAULT '0' COMMENT '0 未删除 1 已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_signup`
--

LOCK TABLES `hr_signup` WRITE;
/*!40000 ALTER TABLE `hr_signup` DISABLE KEYS */;
INSERT INTO `hr_signup` VALUES (1,1,'王小二',18,1,'320172199611155218','7','2017-10-22','汽修','1,2','2019-07-06',8000,5000,61,0,0,'2019-07-29 03:47:09',0),(2,1,'柳烟',18,1,'320761100187212432','8','2016-10-23','机械','1,2','2019-07-08',9000,6000,62,0,0,'2019-06-29 03:47:12',0),(3,2,'赵霞',16,0,'110882092839282146','64','2018-10-23','学前','1,2','2019-07-19',10000,8000,63,1,1,'2019-05-29 03:47:14',0),(4,4,'张柳烟',18,0,'320761199612212381','70','2019-07-02','信息学','1,2','2019-07-20',5000,4000,62,1,1,'2019-07-26 03:47:17',0),(5,6,'zs',16,0,'320611199712125634','71','2017-06-30','cs','1,2','2019-08-31',2000,1000,62,0,0,'2019-07-21 03:47:19',0),(6,1,'王小二',18,0,'320172199611155218','71','2019-07-11','cs','1,2','2019-07-11',5000,3000,63,0,0,'2019-07-14 03:47:21',0),(7,1,'王小二',24,1,'320172199611155218','71','2019-07-11','cs','1,2','2019-07-11',7000,6000,62,0,0,'2019-07-07 03:47:24',0),(8,3,'赵霞',25,0,'','64','2019-07-11','学前','1,2','2019-07-11',5000,4000,62,0,0,'2019-07-13 03:47:26',1),(9,2,'王五',25,0,'213820197302153263','64','2019-07-17','喵喵','1,2','2019-07-31',10000,8000,62,0,0,'2019-07-07 03:47:28',0),(10,1,'赵六',23,1,'123092199802123789','71','2019-07-18','旺旺','1,2','2019-07-31',12000,10000,62,0,0,'2019-07-01 03:47:32',0),(11,3,'刘博',26,1,'32091219971112273X','71','2019-07-24','学前','1,2','2019-07-25',13000,8000,62,0,0,'2019-07-02 03:47:35',0),(12,2,'hello',27,0,'456789145678902567','71','2019-07-24','mall','1,2','2019-07-24',11111,8000,62,0,0,'2019-07-03 03:47:37',0);
/*!40000 ALTER TABLE `hr_signup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_signup_deliveryrecord`
--

DROP TABLE IF EXISTS `hr_signup_deliveryrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_signup_deliveryrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '报名表投递记录表',
  `signup_id` int(11) DEFAULT NULL COMMENT '报名表Id',
  `brief_chapter_id` int(11) DEFAULT NULL COMMENT '简章Id',
  `job_status` int(11) DEFAULT NULL COMMENT '求职状态 0已报名 1待面试 2待报到 3待返佣 4不合适 5邀请报名 6已完成 7邀请面试 8 未面试 9 未报到 10已面试 11 已报道',
  `no_pass_reason` int(11) DEFAULT NULL COMMENT '未通过原因 0不合适 1未面试 2未报道 3面试未通过 4返佣中断',
  `no_report_reason` int(11) DEFAULT NULL COMMENT '未报道原因 0 社保原因(无责) 1 其他原因(无责) 2 其他原因(有责)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `delete_flag` int(11) NOT NULL DEFAULT '0' COMMENT '0 未删除 1 已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_signup_deliveryrecord`
--

LOCK TABLES `hr_signup_deliveryrecord` WRITE;
/*!40000 ALTER TABLE `hr_signup_deliveryrecord` DISABLE KEYS */;
INSERT INTO `hr_signup_deliveryrecord` VALUES (1,1,2,8,1,1,'2019-07-29 05:57:17',0),(2,2,2,10,2,2,'2019-07-28 05:57:19',0),(3,3,3,0,2,1,'2019-07-29 03:58:16',0),(4,3,4,2,1,1,'2019-07-17 05:58:46',0),(5,3,5,3,2,2,'2019-07-26 05:59:16',0),(6,4,4,1,1,2,'2019-07-15 05:59:47',0),(7,5,2,2,1,1,'2019-07-12 06:00:17',0),(8,5,5,0,1,2,'2019-07-29 07:37:39',0);
/*!40000 ALTER TABLE `hr_signup_deliveryrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_status`
--

DROP TABLE IF EXISTS `hr_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_status` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '简章状态表',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `deleteFlag` tinyint(1) DEFAULT '0' COMMENT '0:未删除  1:已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_status`
--

LOCK TABLES `hr_status` WRITE;
/*!40000 ALTER TABLE `hr_status` DISABLE KEYS */;
INSERT INTO `hr_status` VALUES (1,'待审核',0),(2,'审核不通过',0),(3,'正在招',0),(4,'已招满无返佣',0),(5,'已招满返佣未结束',0),(6,'已招满返佣结束',0);
/*!40000 ALTER TABLE `hr_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_user`
--

DROP TABLE IF EXISTS `hr_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_user` (
  `Id` int(32) NOT NULL AUTO_INCREMENT COMMENT '用户表',
  `UserName` varchar(32) DEFAULT NULL COMMENT '用户名',
  `sex` int(11) DEFAULT NULL COMMENT '性别 0男 1女',
  `RoleId` int(11) DEFAULT NULL COMMENT '所属角色ID',
  `Password` varchar(32) DEFAULT NULL COMMENT '密码',
  `HeadImage` varchar(256) DEFAULT NULL COMMENT '头像',
  `PostIdStr` varchar(32) DEFAULT NULL COMMENT '推荐人 意向岗位 可以为多个岗位id',
  `Mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `IdCard` varchar(32) DEFAULT NULL COMMENT '身份证',
  `RegisterTime` datetime DEFAULT NULL COMMENT '注册时间',
  `RecommendNoUpper` int(11) DEFAULT NULL COMMENT '推荐人数上限',
  `RecommendNoLower` int(11) DEFAULT NULL COMMENT '推荐人数下限',
  `RecommendInfo` varchar(200) DEFAULT NULL COMMENT '推荐说明',
  `RecommenderId` int(32) DEFAULT NULL COMMENT '推荐人ID',
  `LoginIp` varchar(32) DEFAULT NULL COMMENT '登陆Ip',
  `LoginTime` datetime DEFAULT NULL COMMENT '登陆时间',
  `DeleteFlag` int(11) DEFAULT '0' COMMENT '0:未删除  1:已删除',
  `type` int(1) DEFAULT NULL COMMENT '0管理员 1本人 2推介者 5招聘单位 6代招单位',
  `isPublic` int(11) DEFAULT NULL COMMENT '是否公开 0公开 1不公开',
  `agreeHelp` int(11) DEFAULT NULL COMMENT '是否同意平台帮助 0是 1否',
  `status` int(11) DEFAULT '0' COMMENT '0已启用 1已禁用',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_user`
--

LOCK TABLES `hr_user` WRITE;
/*!40000 ALTER TABLE `hr_user` DISABLE KEYS */;
INSERT INTO `hr_user` VALUES (1,'超级管理员X',NULL,1,'e10adc3949ba59abbe56e057f20f883e','erer','3,4','17326122358',NULL,NULL,10,5,'推荐说明',1,'192.168.1.45','2019-06-18 20:13:54',0,0,NULL,NULL,NULL),(2,'张三',0,0,'910899ea7106c8e68e9c00c156a47ee4','weqjm','1,3','13024438185','11088209283928213','2018-10-19 14:59:04',34,20,'推荐说明',NULL,NULL,NULL,0,2,0,0,0),(3,'李四',1,0,'910899ea7106c8e68e9c00c156a47ee4','ere','2,4','12345623456','11088209283928212','2018-10-22 09:39:43',14,11,'推荐说明',NULL,NULL,NULL,0,2,0,0,0),(4,'杨晨',NULL,0,'910899ea7106c8e68e9c00c156a47ee4','http://tongzhe-oss.oss-cn-hangzhou.aliyuncs.com/blog/1563096041838.png','1,2','15750852005','340827','2019-07-24 09:58:30',20,10,'推荐说明',4,NULL,NULL,0,1,0,NULL,NULL),(6,'财务人员Y',NULL,2,'910899ea7106c8e68e9c00c156a47ee4','erer','1,3','15152899877','547633339867543589','2019-07-24 09:58:31',20,15,'推荐说明',5,'192.168.1.45','2019-02-26 11:33:39',0,0,NULL,NULL,NULL),(7,'招聘者A',NULL,0,'910899ea7106c8e68e9c00c156a47ee4','erer','1,2','13000000000','320684111111111111','2019-01-10 10:23:56',32,12,'推荐说明',6,NULL,NULL,0,5,NULL,NULL,NULL),(8,'代招者A',NULL,0,'910899ea7106c8e68e9c00c156a47ee4','fdsfd','1,2','13100000000','320684111111111112','2019-01-10 10:23:59',45,12,NULL,7,NULL,NULL,0,6,NULL,NULL,NULL),(9,'招聘者B',NULL,0,'910899ea7106c8e68e9c00c156a47ee4','gggg','1,4','13200000000','320684111111111113','2019-01-12 11:19:58',34,11,'推荐说明',8,NULL,NULL,0,6,NULL,NULL,NULL),(10,'王五',1,0,'e10adc3949ba59abbe56e057f20f883e',NULL,'1,2','13300000000','320684111111111114','2019-07-29 10:21:42',8000,6000,'推荐说明',3,NULL,NULL,0,1,0,0,0),(16,'aaaaa',0,NULL,'123456','ss',NULL,'18620925694','32068119961115521X','2019-07-29 14:29:44',NULL,NULL,NULL,NULL,NULL,NULL,0,1,NULL,NULL,0);
/*!40000 ALTER TABLE `hr_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hr_withdraw`
--

DROP TABLE IF EXISTS `hr_withdraw`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hr_withdraw` (
  `Id` int(32) NOT NULL AUTO_INCREMENT COMMENT '提现记录表',
  `CasherId` int(32) DEFAULT NULL COMMENT '提现人ID',
  `WithdrawAmount` decimal(10,2) DEFAULT NULL COMMENT '提现金额',
  `HandlingFee` decimal(10,2) DEFAULT NULL COMMENT '手续费',
  `WithdrawType` varchar(32) DEFAULT NULL COMMENT '提现方式 0银行卡 1支付宝',
  `Status` varchar(32) DEFAULT NULL COMMENT '处理状态:1待审核 2审核通过 3审核未通过 4已打款',
  `DeleteFlag` tinyint(4) DEFAULT '0' COMMENT '0:未删除  1:已删除',
  `CreateTime` timestamp NULL DEFAULT NULL COMMENT '创建时间 打款完成需修改为到账时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hr_withdraw`
--

LOCK TABLES `hr_withdraw` WRITE;
/*!40000 ALTER TABLE `hr_withdraw` DISABLE KEYS */;
INSERT INTO `hr_withdraw` VALUES (1,3,1100.00,100.00,'0','1',0,'2018-10-30 02:45:23'),(2,3,2200.00,200.00,'1','2',0,'2018-10-30 02:45:58'),(3,3,3300.00,300.00,'1','3',0,'2018-10-30 02:47:12'),(4,3,4400.00,400.00,'1','4',0,'2018-10-30 02:47:15');
/*!40000 ALTER TABLE `hr_withdraw` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_sign_up_table`
--

DROP TABLE IF EXISTS `my_sign_up_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `my_sign_up_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sign_id` int(11) NOT NULL COMMENT '报名表主键id',
  `group_name` varchar(255) DEFAULT NULL COMMENT '分组名称',
  `status` int(11) NOT NULL COMMENT '0 公开 1 不公开 2 平台帮助 3 自定义名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `delete_flag` int(11) NOT NULL DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='我的求职表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_sign_up_table`
--

LOCK TABLES `my_sign_up_table` WRITE;
/*!40000 ALTER TABLE `my_sign_up_table` DISABLE KEYS */;
INSERT INTO `my_sign_up_table` VALUES (1,1,'公开',0,'2019-07-08 00:00:00',0),(2,2,'公开',0,'2019-07-08 00:00:00',0),(3,3,'就是不公开',3,'2019-07-08 00:00:00',0),(4,4,'不公开',1,'2019-07-22 08:17:18',0),(5,5,'平台帮助',2,'2019-07-22 08:20:03',0),(6,6,'商家协助',3,'2019-07-22 08:23:52',0),(7,7,'公开',0,'2019-07-22 09:03:58',0),(8,8,'不公开',1,'2019-07-22 09:04:40',0),(9,9,'平台帮助',2,'2019-07-22 09:04:42',0),(10,10,'你的名字',3,'2019-07-22 09:05:09',0),(11,11,'天下无双',3,'2019-07-22 09:05:42',0);
/*!40000 ALTER TABLE `my_sign_up_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `options`
--

DROP TABLE IF EXISTS `options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `options` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '前端选项内容表',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `deleteFlag` int(11) DEFAULT '0' COMMENT '0未删除 1已删除',
  `type` int(11) DEFAULT NULL COMMENT '1 福利 2工作性质 3学历要求 4经验要求 5用工形式 6.年龄段 7.服装要求 8.兴趣爱好 9.工作方式 10. 工作时间安排 11.加班时长 12 用人单位地址 13 非用人单位地址  15. 男人的招聘数量 16 女人的招聘数量 17 专业 18. 确认与被推荐人的关系',
  `createTime` timestamp NULL DEFAULT NULL,
  `age_min` int(11) DEFAULT NULL COMMENT '年龄下限',
  `age_max` int(11) DEFAULT NULL COMMENT '年龄上限',
  `overtime_time_min` int(11) DEFAULT NULL COMMENT '加班时长min',
  `overtime_time_max` int(11) DEFAULT NULL COMMENT '加班时长max',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES (3,'五险一金',0,1,'2018-10-29 09:10:07',NULL,NULL,NULL,NULL),(4,'包吃包住',0,1,'2018-10-29 09:10:11',NULL,NULL,NULL,NULL),(5,'服务',0,2,'2018-10-29 09:11:00',NULL,NULL,NULL,NULL),(6,'非服务',0,2,'2018-10-29 09:11:10',NULL,NULL,NULL,NULL),(7,'本科',0,3,'2018-10-29 09:11:39',NULL,NULL,NULL,NULL),(8,'专科',0,3,'2018-10-29 09:11:44',NULL,NULL,NULL,NULL),(11,NULL,0,6,'2019-06-27 02:22:19',16,22,NULL,NULL),(12,NULL,0,6,'2019-06-27 02:22:30',23,30,NULL,NULL),(13,NULL,0,6,'2019-06-27 02:27:42',31,40,NULL,NULL),(14,NULL,0,6,'2019-06-27 02:28:00',40,200,NULL,NULL),(18,'便装',0,7,'2019-06-27 17:23:51',NULL,NULL,NULL,NULL),(19,'常规厂服',0,7,'2019-06-27 17:24:19',NULL,NULL,NULL,NULL),(20,'分离式无尘服',0,7,'2019-06-27 17:24:41',NULL,NULL,NULL,NULL),(21,'连体式无尘服',0,7,'2019-06-27 17:24:59',NULL,NULL,NULL,NULL),(22,'阅读',0,8,'2019-06-27 17:25:52',NULL,NULL,NULL,NULL),(23,'跳舞',0,8,'2019-06-27 17:26:12',NULL,NULL,NULL,NULL),(24,'音乐',0,8,'2019-06-27 17:26:30',NULL,NULL,NULL,NULL),(25,'运动',0,8,'2019-06-27 17:26:42',NULL,NULL,NULL,NULL),(26,'烹饪',0,8,'2019-06-27 17:27:03',NULL,NULL,NULL,NULL),(27,'坐',0,9,'2019-06-27 17:29:41',NULL,NULL,NULL,NULL),(28,'站立',0,9,'2019-06-27 17:29:54',NULL,NULL,NULL,NULL),(29,'走动',0,9,'2019-06-27 17:30:57',NULL,NULL,NULL,NULL),(30,'白班',0,10,'2019-06-27 17:30:58',NULL,NULL,NULL,NULL),(31,'夜班',0,10,'2019-06-27 17:31:51',NULL,NULL,NULL,NULL),(32,'三班',0,10,'2019-06-27 17:32:06',NULL,NULL,NULL,NULL),(33,'两班',0,10,'2019-06-27 17:32:22',NULL,NULL,NULL,NULL),(34,NULL,0,11,NULL,NULL,NULL,0,40),(35,NULL,0,11,NULL,NULL,NULL,40,100),(36,NULL,0,11,NULL,NULL,NULL,100,NULL),(37,'星湖街到',0,12,'2019-06-27 17:58:51',NULL,NULL,NULL,NULL),(38,'创业大厦',0,13,'2019-06-27 17:59:45',NULL,NULL,NULL,NULL),(39,'创业路',0,12,'2019-06-27 18:00:19',NULL,NULL,NULL,NULL),(40,'陕西南路',0,12,'2019-06-27 18:01:49',NULL,NULL,NULL,NULL),(41,'正式工',0,5,'2019-06-28 02:27:40',NULL,NULL,NULL,NULL),(42,'小时工',0,5,'2019-06-27 18:28:16',NULL,NULL,NULL,NULL),(43,'派遣工',0,5,'2019-06-27 18:29:12',NULL,NULL,NULL,NULL),(44,'实习生',0,5,'2019-06-27 18:29:25',NULL,NULL,NULL,NULL),(45,'20',0,15,'2019-06-27 20:54:45',NULL,NULL,NULL,NULL),(46,'40',0,16,'2019-06-27 21:00:35',NULL,NULL,NULL,NULL),(47,'10',0,15,'2019-06-27 21:00:54',NULL,NULL,NULL,NULL),(48,'23',0,NULL,'2019-06-28 05:07:55',NULL,NULL,NULL,NULL),(49,'汽修',0,17,'2019-06-27 22:40:23',NULL,NULL,NULL,NULL),(50,'机械',0,17,NULL,NULL,NULL,NULL,NULL),(51,'管理',0,17,NULL,NULL,NULL,NULL,NULL),(52,'电子信息',0,17,NULL,NULL,NULL,NULL,NULL),(53,'电子工程',0,17,NULL,NULL,NULL,NULL,NULL),(54,'计算机科学',0,17,NULL,NULL,NULL,NULL,NULL),(55,'1年内',0,4,'2019-06-28 06:59:20',NULL,NULL,NULL,NULL),(56,'1-3年',0,4,'2019-06-28 06:59:42',NULL,NULL,NULL,NULL),(57,'3-5年',0,4,'2019-06-28 06:00:08',NULL,NULL,NULL,NULL),(58,'5-10年',0,4,'2019-06-28 06:00:25',NULL,NULL,NULL,NULL),(59,'10年以上',0,4,'2019-06-28 06:00:43',NULL,NULL,NULL,NULL),(60,'职业推荐人',0,18,'2019-07-01 17:55:01',NULL,NULL,NULL,NULL),(61,'亲朋',0,18,'2019-07-01 17:55:52',NULL,NULL,NULL,NULL),(62,'同学',0,18,'2019-07-01 17:55:53',NULL,NULL,NULL,NULL),(63,'师生',0,18,'2019-07-01 17:55:54',NULL,NULL,NULL,NULL),(64,'不限',0,3,NULL,NULL,NULL,NULL,NULL),(65,'不限',0,4,NULL,NULL,NULL,NULL,NULL),(66,'',0,11,NULL,NULL,NULL,0,NULL),(67,'寒假工',0,5,NULL,NULL,NULL,NULL,NULL),(68,'暑假工',0,5,NULL,NULL,NULL,NULL,NULL),(69,'硕士',0,3,NULL,NULL,NULL,NULL,NULL),(70,'高中',0,3,NULL,NULL,NULL,NULL,NULL),(71,'初中及以下',0,3,NULL,NULL,NULL,NULL,NULL),(72,'中专/中技',0,3,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `readrecord`
--

DROP TABLE IF EXISTS `readrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `readrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0获取招聘端的关注 1获取求职端的关注',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `readrecord`
--

LOCK TABLES `readrecord` WRITE;
/*!40000 ALTER TABLE `readrecord` DISABLE KEYS */;
/*!40000 ALTER TABLE `readrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sp_hat_area`
--

DROP TABLE IF EXISTS `sp_hat_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sp_hat_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `areaID` varchar(50) DEFAULT NULL,
  `area` varchar(60) DEFAULT NULL,
  `father` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3181 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sp_hat_area`
--

LOCK TABLES `sp_hat_area` WRITE;
/*!40000 ALTER TABLE `sp_hat_area` DISABLE KEYS */;
INSERT INTO `sp_hat_area` VALUES (1,'110101','东城区','110100'),(2,'110102','西城区','110100'),(3,'110103','崇文区','110100'),(4,'110104','宣武区','110100'),(5,'110105','朝阳区','110100'),(6,'110106','丰台区','110100'),(7,'110107','石景山区','110100'),(8,'110108','海淀区','110100'),(9,'110109','门头沟区','110100'),(10,'110111','房山区','110100'),(11,'110112','通州区','110100'),(12,'110113','顺义区','110100'),(13,'110114','昌平区','110100'),(14,'110115','大兴区','110100'),(15,'110116','怀柔区','110100'),(16,'110117','平谷区','110100'),(17,'110228','密云县','110200'),(18,'110229','延庆县','110200'),(19,'120101','和平区','120100'),(20,'120102','河东区','120100'),(21,'120103','河西区','120100'),(22,'120104','南开区','120100'),(23,'120105','河北区','120100'),(24,'120106','红桥区','120100'),(25,'120107','塘沽区','120100'),(26,'120108','汉沽区','120100'),(27,'120109','大港区','120100'),(28,'120110','东丽区','120100'),(29,'120111','西青区','120100'),(30,'120112','津南区','120100'),(31,'120113','北辰区','120100'),(32,'120114','武清区','120100'),(33,'120115','宝坻区','120100'),(34,'120221','宁河县','120200'),(35,'120223','静海县','120200'),(36,'120225','蓟　县','120200'),(38,'130102','长安区','130100'),(39,'130103','桥东区','130100'),(40,'130104','桥西区','130100'),(41,'130105','新华区','130100'),(42,'130107','井陉矿区','130100'),(43,'130108','裕华区','130100'),(44,'130121','井陉县','130100'),(45,'130123','正定县','130100'),(46,'130124','栾城县','130100'),(47,'130125','行唐县','130100'),(48,'130126','灵寿县','130100'),(49,'130127','高邑县','130100'),(50,'130128','深泽县','130100'),(51,'130129','赞皇县','130100'),(52,'130130','无极县','130100'),(53,'130131','平山县','130100'),(54,'130132','元氏县','130100'),(55,'130133','赵　县','130100'),(56,'130181','辛集市','130100'),(57,'130182','藁城市','130100'),(58,'130183','晋州市','130100'),(59,'130184','新乐市','130100'),(60,'130185','鹿泉市','130100'),(62,'130202','路南区','130200'),(63,'130203','路北区','130200'),(64,'130204','古冶区','130200'),(65,'130205','开平区','130200'),(66,'130207','丰南区','130200'),(67,'130208','丰润区','130200'),(68,'130223','滦　县','130200'),(69,'130224','滦南县','130200'),(70,'130225','乐亭县','130200'),(71,'130227','迁西县','130200'),(72,'130229','玉田县','130200'),(73,'130230','唐海县','130200'),(74,'130281','遵化市','130200'),(75,'130283','迁安市','130200'),(77,'130302','海港区','130300'),(78,'130303','山海关区','130300'),(79,'130304','北戴河区','130300'),(80,'130321','青龙满族自治县','130300'),(81,'130322','昌黎县','130300'),(82,'130323','抚宁县','130300'),(83,'130324','卢龙县','130300'),(85,'130402','邯山区','130400'),(86,'130403','丛台区','130400'),(87,'130404','复兴区','130400'),(88,'130406','峰峰矿区','130400'),(89,'130421','邯郸县','130400'),(90,'130423','临漳县','130400'),(91,'130424','成安县','130400'),(92,'130425','大名县','130400'),(93,'130426','涉　县','130400'),(94,'130427','磁　县','130400'),(95,'130428','肥乡县','130400'),(96,'130429','永年县','130400'),(97,'130430','邱　县','130400'),(98,'130431','鸡泽县','130400'),(99,'130432','广平县','130400'),(100,'130433','馆陶县','130400'),(101,'130434','魏　县','130400'),(102,'130435','曲周县','130400'),(103,'130481','武安市','130400'),(105,'130502','桥东区','130500'),(106,'130503','桥西区','130500'),(107,'130521','邢台县','130500'),(108,'130522','临城县','130500'),(109,'130523','内丘县','130500'),(110,'130524','柏乡县','130500'),(111,'130525','隆尧县','130500'),(112,'130526','任　县','130500'),(113,'130527','南和县','130500'),(114,'130528','宁晋县','130500'),(115,'130529','巨鹿县','130500'),(116,'130530','新河县','130500'),(117,'130531','广宗县','130500'),(118,'130532','平乡县','130500'),(119,'130533','威　县','130500'),(120,'130534','清河县','130500'),(121,'130535','临西县','130500'),(122,'130581','南宫市','130500'),(123,'130582','沙河市','130500'),(125,'130602','新市区','130600'),(126,'130603','北市区','130600'),(127,'130604','南市区','130600'),(128,'130621','满城县','130600'),(129,'130622','清苑县','130600'),(130,'130623','涞水县','130600'),(131,'130624','阜平县','130600'),(132,'130625','徐水县','130600'),(133,'130626','定兴县','130600'),(134,'130627','唐　县','130600'),(135,'130628','高阳县','130600'),(136,'130629','容城县','130600'),(137,'130630','涞源县','130600'),(138,'130631','望都县','130600'),(139,'130632','安新县','130600'),(140,'130633','易　县','130600'),(141,'130634','曲阳县','130600'),(142,'130635','蠡　县','130600'),(143,'130636','顺平县','130600'),(144,'130637','博野县','130600'),(145,'130638','雄　县','130600'),(146,'130681','涿州市','130600'),(147,'130682','定州市','130600'),(148,'130683','安国市','130600'),(149,'130684','高碑店市','130600'),(151,'130702','桥东区','130700'),(152,'130703','桥西区','130700'),(153,'130705','宣化区','130700'),(154,'130706','下花园区','130700'),(155,'130721','宣化县','130700'),(156,'130722','张北县','130700'),(157,'130723','康保县','130700'),(158,'130724','沽源县','130700'),(159,'130725','尚义县','130700'),(160,'130726','蔚　县','130700'),(161,'130727','阳原县','130700'),(162,'130728','怀安县','130700'),(163,'130729','万全县','130700'),(164,'130730','怀来县','130700'),(165,'130731','涿鹿县','130700'),(166,'130732','赤城县','130700'),(167,'130733','崇礼县','130700'),(169,'130802','双桥区','130800'),(170,'130803','双滦区','130800'),(171,'130804','鹰手营子矿区','130800'),(172,'130821','承德县','130800'),(173,'130822','兴隆县','130800'),(174,'130823','平泉县','130800'),(175,'130824','滦平县','130800'),(176,'130825','隆化县','130800'),(177,'130826','丰宁满族自治县','130800'),(178,'130827','宽城满族自治县','130800'),(179,'130828','围场满族蒙古族自治县','130800'),(181,'130902','新华区','130900'),(182,'130903','运河区','130900'),(183,'130921','沧　县','130900'),(184,'130922','青　县','130900'),(185,'130923','东光县','130900'),(186,'130924','海兴县','130900'),(187,'130925','盐山县','130900'),(188,'130926','肃宁县','130900'),(189,'130927','南皮县','130900'),(190,'130928','吴桥县','130900'),(191,'130929','献　县','130900'),(192,'130930','孟村回族自治县','130900'),(193,'130981','泊头市','130900'),(194,'130982','任丘市','130900'),(195,'130983','黄骅市','130900'),(196,'130984','河间市','130900'),(198,'131002','安次区','131000'),(199,'131003','广阳区','131000'),(200,'131022','固安县','131000'),(201,'131023','永清县','131000'),(202,'131024','香河县','131000'),(203,'131025','大城县','131000'),(204,'131026','文安县','131000'),(205,'131028','大厂回族自治县','131000'),(206,'131081','霸州市','131000'),(207,'131082','三河市','131000'),(209,'131102','桃城区','131100'),(210,'131121','枣强县','131100'),(211,'131122','武邑县','131100'),(212,'131123','武强县','131100'),(213,'131124','饶阳县','131100'),(214,'131125','安平县','131100'),(215,'131126','故城县','131100'),(216,'131127','景　县','131100'),(217,'131128','阜城县','131100'),(218,'131181','冀州市','131100'),(219,'131182','深州市','131100'),(221,'140105','小店区','140100'),(222,'140106','迎泽区','140100'),(223,'140107','杏花岭区','140100'),(224,'140108','尖草坪区','140100'),(225,'140109','万柏林区','140100'),(226,'140110','晋源区','140100'),(227,'140121','清徐县','140100'),(228,'140122','阳曲县','140100'),(229,'140123','娄烦县','140100'),(230,'140181','古交市','140100'),(232,'140202','城　区','140200'),(233,'140203','矿　区','140200'),(234,'140211','南郊区','140200'),(235,'140212','新荣区','140200'),(236,'140221','阳高县','140200'),(237,'140222','天镇县','140200'),(238,'140223','广灵县','140200'),(239,'140224','灵丘县','140200'),(240,'140225','浑源县','140200'),(241,'140226','左云县','140200'),(242,'140227','大同县','140200'),(244,'140302','城　区','140300'),(245,'140303','矿　区','140300'),(246,'140311','郊　区','140300'),(247,'140321','平定县','140300'),(248,'140322','盂　县','140300'),(250,'140402','城　区','140400'),(251,'140411','郊　区','140400'),(252,'140421','长治县','140400'),(253,'140423','襄垣县','140400'),(254,'140424','屯留县','140400'),(255,'140425','平顺县','140400'),(256,'140426','黎城县','140400'),(257,'140427','壶关县','140400'),(258,'140428','长子县','140400'),(259,'140429','武乡县','140400'),(260,'140430','沁　县','140400'),(261,'140431','沁源县','140400'),(262,'140481','潞城市','140400'),(264,'140502','城　区','140500'),(265,'140521','沁水县','140500'),(266,'140522','阳城县','140500'),(267,'140524','陵川县','140500'),(268,'140525','泽州县','140500'),(269,'140581','高平市','140500'),(271,'140602','朔城区','140600'),(272,'140603','平鲁区','140600'),(273,'140621','山阴县','140600'),(274,'140622','应　县','140600'),(275,'140623','右玉县','140600'),(276,'140624','怀仁县','140600'),(278,'140702','榆次区','140700'),(279,'140721','榆社县','140700'),(280,'140722','左权县','140700'),(281,'140723','和顺县','140700'),(282,'140724','昔阳县','140700'),(283,'140725','寿阳县','140700'),(284,'140726','太谷县','140700'),(285,'140727','祁　县','140700'),(286,'140728','平遥县','140700'),(287,'140729','灵石县','140700'),(288,'140781','介休市','140700'),(290,'140802','盐湖区','140800'),(291,'140821','临猗县','140800'),(292,'140822','万荣县','140800'),(293,'140823','闻喜县','140800'),(294,'140824','稷山县','140800'),(295,'140825','新绛县','140800'),(296,'140826','绛　县','140800'),(297,'140827','垣曲县','140800'),(298,'140828','夏　县','140800'),(299,'140829','平陆县','140800'),(300,'140830','芮城县','140800'),(301,'140881','永济市','140800'),(302,'140882','河津市','140800'),(304,'140902','忻府区','140900'),(305,'140921','定襄县','140900'),(306,'140922','五台县','140900'),(307,'140923','代　县','140900'),(308,'140924','繁峙县','140900'),(309,'140925','宁武县','140900'),(310,'140926','静乐县','140900'),(311,'140927','神池县','140900'),(312,'140928','五寨县','140900'),(313,'140929','岢岚县','140900'),(314,'140930','河曲县','140900'),(315,'140931','保德县','140900'),(316,'140932','偏关县','140900'),(317,'140981','原平市','140900'),(319,'141002','尧都区','141000'),(320,'141021','曲沃县','141000'),(321,'141022','翼城县','141000'),(322,'141023','襄汾县','141000'),(323,'141024','洪洞县','141000'),(324,'141025','古　县','141000'),(325,'141026','安泽县','141000'),(326,'141027','浮山县','141000'),(327,'141028','吉　县','141000'),(328,'141029','乡宁县','141000'),(329,'141030','大宁县','141000'),(330,'141031','隰　县','141000'),(331,'141032','永和县','141000'),(332,'141033','蒲　县','141000'),(333,'141034','汾西县','141000'),(334,'141081','侯马市','141000'),(335,'141082','霍州市','141000'),(337,'141102','离石区','141100'),(338,'141121','文水县','141100'),(339,'141122','交城县','141100'),(340,'141123','兴　县','141100'),(341,'141124','临　县','141100'),(342,'141125','柳林县','141100'),(343,'141126','石楼县','141100'),(344,'141127','岚　县','141100'),(345,'141128','方山县','141100'),(346,'141129','中阳县','141100'),(347,'141130','交口县','141100'),(348,'141181','孝义市','141100'),(349,'141182','汾阳市','141100'),(351,'150102','新城区','150100'),(352,'150103','回民区','150100'),(353,'150104','玉泉区','150100'),(354,'150105','赛罕区','150100'),(355,'150121','土默特左旗','150100'),(356,'150122','托克托县','150100'),(357,'150123','和林格尔县','150100'),(358,'150124','清水河县','150100'),(359,'150125','武川县','150100'),(361,'150202','东河区','150200'),(362,'150203','昆都仑区','150200'),(363,'150204','青山区','150200'),(364,'150205','石拐区','150200'),(365,'150206','白云矿区','150200'),(366,'150207','九原区','150200'),(367,'150221','土默特右旗','150200'),(368,'150222','固阳县','150200'),(369,'150223','达尔罕茂明安联合旗','150200'),(371,'150302','海勃湾区','150300'),(372,'150303','海南区','150300'),(373,'150304','乌达区','150300'),(375,'150402','红山区','150400'),(376,'150403','元宝山区','150400'),(377,'150404','松山区','150400'),(378,'150421','阿鲁科尔沁旗','150400'),(379,'150422','巴林左旗','150400'),(380,'150423','巴林右旗','150400'),(381,'150424','林西县','150400'),(382,'150425','克什克腾旗','150400'),(383,'150426','翁牛特旗','150400'),(384,'150428','喀喇沁旗','150400'),(385,'150429','宁城县','150400'),(386,'150430','敖汉旗','150400'),(388,'150502','科尔沁区','150500'),(389,'150521','科尔沁左翼中旗','150500'),(390,'150522','科尔沁左翼后旗','150500'),(391,'150523','开鲁县','150500'),(392,'150524','库伦旗','150500'),(393,'150525','奈曼旗','150500'),(394,'150526','扎鲁特旗','150500'),(395,'150581','霍林郭勒市','150500'),(396,'150602','东胜区','150600'),(397,'150621','达拉特旗','150600'),(398,'150622','准格尔旗','150600'),(399,'150623','鄂托克前旗','150600'),(400,'150624','鄂托克旗','150600'),(401,'150625','杭锦旗','150600'),(402,'150626','乌审旗','150600'),(403,'150627','伊金霍洛旗','150600'),(405,'150702','海拉尔区','150700'),(406,'150721','阿荣旗','150700'),(407,'150722','莫力达瓦达斡尔族自治旗','150700'),(408,'150723','鄂伦春自治旗','150700'),(409,'150724','鄂温克族自治旗','150700'),(410,'150725','陈巴尔虎旗','150700'),(411,'150726','新巴尔虎左旗','150700'),(412,'150727','新巴尔虎右旗','150700'),(413,'150781','满洲里市','150700'),(414,'150782','牙克石市','150700'),(415,'150783','扎兰屯市','150700'),(416,'150784','额尔古纳市','150700'),(417,'150785','根河市','150700'),(419,'150802','临河区','150800'),(420,'150821','五原县','150800'),(421,'150822','磴口县','150800'),(422,'150823','乌拉特前旗','150800'),(423,'150824','乌拉特中旗','150800'),(424,'150825','乌拉特后旗','150800'),(425,'150826','杭锦后旗','150800'),(427,'150902','集宁区','150900'),(428,'150921','卓资县','150900'),(429,'150922','化德县','150900'),(430,'150923','商都县','150900'),(431,'150924','兴和县','150900'),(432,'150925','凉城县','150900'),(433,'150926','察哈尔右翼前旗','150900'),(434,'150927','察哈尔右翼中旗','150900'),(435,'150928','察哈尔右翼后旗','150900'),(436,'150929','四子王旗','150900'),(437,'150981','丰镇市','150900'),(438,'152201','乌兰浩特市','152200'),(439,'152202','阿尔山市','152200'),(440,'152221','科尔沁右翼前旗','152200'),(441,'152222','科尔沁右翼中旗','152200'),(442,'152223','扎赉特旗','152200'),(443,'152224','突泉县','152200'),(444,'152501','二连浩特市','152500'),(445,'152502','锡林浩特市','152500'),(446,'152522','阿巴嘎旗','152500'),(447,'152523','苏尼特左旗','152500'),(448,'152524','苏尼特右旗','152500'),(449,'152525','东乌珠穆沁旗','152500'),(450,'152526','西乌珠穆沁旗','152500'),(451,'152527','太仆寺旗','152500'),(452,'152528','镶黄旗','152500'),(453,'152529','正镶白旗','152500'),(454,'152530','正蓝旗','152500'),(455,'152531','多伦县','152500'),(456,'152921','阿拉善左旗','152900'),(457,'152922','阿拉善右旗','152900'),(458,'152923','额济纳旗','152900'),(460,'210102','和平区','210100'),(461,'210103','沈河区','210100'),(462,'210104','大东区','210100'),(463,'210105','皇姑区','210100'),(464,'210106','铁西区','210100'),(465,'210111','苏家屯区','210100'),(466,'210112','东陵区','210100'),(467,'210113','新城子区','210100'),(468,'210114','于洪区','210100'),(469,'210122','辽中县','210100'),(470,'210123','康平县','210100'),(471,'210124','法库县','210100'),(472,'210181','新民市','210100'),(474,'210202','中山区','210200'),(475,'210203','西岗区','210200'),(476,'210204','沙河口区','210200'),(477,'210211','甘井子区','210200'),(478,'210212','旅顺口区','210200'),(479,'210213','金州区','210200'),(480,'210224','长海县','210200'),(481,'210281','瓦房店市','210200'),(482,'210282','普兰店市','210200'),(483,'210283','庄河市','210200'),(485,'210302','铁东区','210300'),(486,'210303','铁西区','210300'),(487,'210304','立山区','210300'),(488,'210311','千山区','210300'),(489,'210321','台安县','210300'),(490,'210323','岫岩满族自治县','210300'),(491,'210381','海城市','210300'),(493,'210402','新抚区','210400'),(494,'210403','东洲区','210400'),(495,'210404','望花区','210400'),(496,'210411','顺城区','210400'),(497,'210421','抚顺县','210400'),(498,'210422','新宾满族自治县','210400'),(499,'210423','清原满族自治县','210400'),(501,'210502','平山区','210500'),(502,'210503','溪湖区','210500'),(503,'210504','明山区','210500'),(504,'210505','南芬区','210500'),(505,'210521','本溪满族自治县','210500'),(506,'210522','桓仁满族自治县','210500'),(508,'210602','元宝区','210600'),(509,'210603','振兴区','210600'),(510,'210604','振安区','210600'),(511,'210624','宽甸满族自治县','210600'),(512,'210681','东港市','210600'),(513,'210682','凤城市','210600'),(515,'210702','古塔区','210700'),(516,'210703','凌河区','210700'),(517,'210711','太和区','210700'),(518,'210726','黑山县','210700'),(519,'210727','义　县','210700'),(520,'210781','凌海市','210700'),(521,'210782','北宁市','210700'),(523,'210802','站前区','210800'),(524,'210803','西市区','210800'),(525,'210804','鲅鱼圈区','210800'),(526,'210811','老边区','210800'),(527,'210881','盖州市','210800'),(528,'210882','大石桥市','210800'),(530,'210902','海州区','210900'),(531,'210903','新邱区','210900'),(532,'210904','太平区','210900'),(533,'210905','清河门区','210900'),(534,'210911','细河区','210900'),(535,'210921','阜新蒙古族自治县','210900'),(536,'210922','彰武县','210900'),(538,'211002','白塔区','211000'),(539,'211003','文圣区','211000'),(540,'211004','宏伟区','211000'),(541,'211005','弓长岭区','211000'),(542,'211011','太子河区','211000'),(543,'211021','辽阳县','211000'),(544,'211081','灯塔市','211000'),(546,'211102','双台子区','211100'),(547,'211103','兴隆台区','211100'),(548,'211121','大洼县','211100'),(549,'211122','盘山县','211100'),(551,'211202','银州区','211200'),(552,'211204','清河区','211200'),(553,'211221','铁岭县','211200'),(554,'211223','西丰县','211200'),(555,'211224','昌图县','211200'),(556,'211281','调兵山市','211200'),(557,'211282','开原市','211200'),(559,'211302','双塔区','211300'),(560,'211303','龙城区','211300'),(561,'211321','朝阳县','211300'),(562,'211322','建平县','211300'),(563,'211324','喀喇沁左翼蒙古族自治县','211300'),(564,'211381','北票市','211300'),(565,'211382','凌源市','211300'),(567,'211402','连山区','211400'),(568,'211403','龙港区','211400'),(569,'211404','南票区','211400'),(570,'211421','绥中县','211400'),(571,'211422','建昌县','211400'),(572,'211481','兴城市','211400'),(574,'220102','南关区','220100'),(575,'220103','宽城区','220100'),(576,'220104','朝阳区','220100'),(577,'220105','二道区','220100'),(578,'220106','绿园区','220100'),(579,'220112','双阳区','220100'),(580,'220122','农安县','220100'),(581,'220181','九台市','220100'),(582,'220182','榆树市','220100'),(583,'220183','德惠市','220100'),(585,'220202','昌邑区','220200'),(586,'220203','龙潭区','220200'),(587,'220204','船营区','220200'),(588,'220211','丰满区','220200'),(589,'220221','永吉县','220200'),(590,'220281','蛟河市','220200'),(591,'220282','桦甸市','220200'),(592,'220283','舒兰市','220200'),(593,'220284','磐石市','220200'),(595,'220302','铁西区','220300'),(596,'220303','铁东区','220300'),(597,'220322','梨树县','220300'),(598,'220323','伊通满族自治县','220300'),(599,'220381','公主岭市','220300'),(600,'220382','双辽市','220300'),(602,'220402','龙山区','220400'),(603,'220403','西安区','220400'),(604,'220421','东丰县','220400'),(605,'220422','东辽县','220400'),(607,'220502','东昌区','220500'),(608,'220503','二道江区','220500'),(609,'220521','通化县','220500'),(610,'220523','辉南县','220500'),(611,'220524','柳河县','220500'),(612,'220581','梅河口市','220500'),(613,'220582','集安市','220500'),(615,'220602','八道江区','220600'),(616,'220621','抚松县','220600'),(617,'220622','靖宇县','220600'),(618,'220623','长白朝鲜族自治县','220600'),(619,'220625','江源县','220600'),(620,'220681','临江市','220600'),(622,'220702','宁江区','220700'),(623,'220721','前郭尔罗斯蒙古族自治县','220700'),(624,'220722','长岭县','220700'),(625,'220723','乾安县','220700'),(626,'220724','扶余县','220700'),(628,'220802','洮北区','220800'),(629,'220821','镇赉县','220800'),(630,'220822','通榆县','220800'),(631,'220881','洮南市','220800'),(632,'220882','大安市','220800'),(633,'222401','延吉市','222400'),(634,'222402','图们市','222400'),(635,'222403','敦化市','222400'),(636,'222404','珲春市','222400'),(637,'222405','龙井市','222400'),(638,'222406','和龙市','222400'),(639,'222424','汪清县','222400'),(640,'222426','安图县','222400'),(642,'230102','道里区','230100'),(643,'230103','南岗区','230100'),(644,'230104','道外区','230100'),(645,'230106','香坊区','230100'),(646,'230107','动力区','230100'),(647,'230108','平房区','230100'),(648,'230109','松北区','230100'),(649,'230111','呼兰区','230100'),(650,'230123','依兰县','230100'),(651,'230124','方正县','230100'),(652,'230125','宾　县','230100'),(653,'230126','巴彦县','230100'),(654,'230127','木兰县','230100'),(655,'230128','通河县','230100'),(656,'230129','延寿县','230100'),(657,'230181','阿城市','230100'),(658,'230182','双城市','230100'),(659,'230183','尚志市','230100'),(660,'230184','五常市','230100'),(662,'230202','龙沙区','230200'),(663,'230203','建华区','230200'),(664,'230204','铁锋区','230200'),(665,'230205','昂昂溪区','230200'),(666,'230206','富拉尔基区','230200'),(667,'230207','碾子山区','230200'),(668,'230208','梅里斯达斡尔族区','230200'),(669,'230221','龙江县','230200'),(670,'230223','依安县','230200'),(671,'230224','泰来县','230200'),(672,'230225','甘南县','230200'),(673,'230227','富裕县','230200'),(674,'230229','克山县','230200'),(675,'230230','克东县','230200'),(676,'230231','拜泉县','230200'),(677,'230281','讷河市','230200'),(679,'230302','鸡冠区','230300'),(680,'230303','恒山区','230300'),(681,'230304','滴道区','230300'),(682,'230305','梨树区','230300'),(683,'230306','城子河区','230300'),(684,'230307','麻山区','230300'),(685,'230321','鸡东县','230300'),(686,'230381','虎林市','230300'),(687,'230382','密山市','230300'),(689,'230402','向阳区','230400'),(690,'230403','工农区','230400'),(691,'230404','南山区','230400'),(692,'230405','兴安区','230400'),(693,'230406','东山区','230400'),(694,'230407','兴山区','230400'),(695,'230421','萝北县','230400'),(696,'230422','绥滨县','230400'),(698,'230502','尖山区','230500'),(699,'230503','岭东区','230500'),(700,'230505','四方台区','230500'),(701,'230506','宝山区','230500'),(702,'230521','集贤县','230500'),(703,'230522','友谊县','230500'),(704,'230523','宝清县','230500'),(705,'230524','饶河县','230500'),(707,'230602','萨尔图区','230600'),(708,'230603','龙凤区','230600'),(709,'230604','让胡路区','230600'),(710,'230605','红岗区','230600'),(711,'230606','大同区','230600'),(712,'230621','肇州县','230600'),(713,'230622','肇源县','230600'),(714,'230623','林甸县','230600'),(715,'230624','杜尔伯特蒙古族自治县','230600'),(717,'230702','伊春区','230700'),(718,'230703','南岔区','230700'),(719,'230704','友好区','230700'),(720,'230705','西林区','230700'),(721,'230706','翠峦区','230700'),(722,'230707','新青区','230700'),(723,'230708','美溪区','230700'),(724,'230709','金山屯区','230700'),(725,'230710','五营区','230700'),(726,'230711','乌马河区','230700'),(727,'230712','汤旺河区','230700'),(728,'230713','带岭区','230700'),(729,'230714','乌伊岭区','230700'),(730,'230715','红星区','230700'),(731,'230716','上甘岭区','230700'),(732,'230722','嘉荫县','230700'),(733,'230781','铁力市','230700'),(735,'230802','永红区','230800'),(736,'230803','向阳区','230800'),(737,'230804','前进区','230800'),(738,'230805','东风区','230800'),(739,'230811','郊　区','230800'),(740,'230822','桦南县','230800'),(741,'230826','桦川县','230800'),(742,'230828','汤原县','230800'),(743,'230833','抚远县','230800'),(744,'230881','同江市','230800'),(745,'230882','富锦市','230800'),(747,'230902','新兴区','230900'),(748,'230903','桃山区','230900'),(749,'230904','茄子河区','230900'),(750,'230921','勃利县','230900'),(752,'231002','东安区','231000'),(753,'231003','阳明区','231000'),(754,'231004','爱民区','231000'),(755,'231005','西安区','231000'),(756,'231024','东宁县','231000'),(757,'231025','林口县','231000'),(758,'231081','绥芬河市','231000'),(759,'231083','海林市','231000'),(760,'231084','宁安市','231000'),(761,'231085','穆棱市','231000'),(763,'231102','爱辉区','231100'),(764,'231121','嫩江县','231100'),(765,'231123','逊克县','231100'),(766,'231124','孙吴县','231100'),(767,'231181','北安市','231100'),(768,'231182','五大连池市','231100'),(770,'231202','北林区','231200'),(771,'231221','望奎县','231200'),(772,'231222','兰西县','231200'),(773,'231223','青冈县','231200'),(774,'231224','庆安县','231200'),(775,'231225','明水县','231200'),(776,'231226','绥棱县','231200'),(777,'231281','安达市','231200'),(778,'231282','肇东市','231200'),(779,'231283','海伦市','231200'),(780,'232721','呼玛县','232700'),(781,'232722','塔河县','232700'),(782,'232723','漠河县','232700'),(783,'310101','黄浦区','310100'),(784,'310103','卢湾区','310100'),(785,'310104','徐汇区','310100'),(786,'310105','长宁区','310100'),(787,'310106','静安区','310100'),(788,'310107','普陀区','310100'),(789,'310108','闸北区','310100'),(790,'310109','虹口区','310100'),(791,'310110','杨浦区','310100'),(792,'310112','闵行区','310100'),(793,'310113','宝山区','310100'),(794,'310114','嘉定区','310100'),(795,'310115','浦东新区','310100'),(796,'310116','金山区','310100'),(797,'310117','松江区','310100'),(798,'310118','青浦区','310100'),(799,'310119','南汇区','310100'),(800,'310120','奉贤区','310100'),(801,'310230','崇明县','310200'),(803,'320102','玄武区','320100'),(804,'320103','白下区','320100'),(805,'320104','秦淮区','320100'),(806,'320105','建邺区','320100'),(807,'320106','鼓楼区','320100'),(808,'320107','下关区','320100'),(809,'320111','浦口区','320100'),(810,'320113','栖霞区','320100'),(811,'320114','雨花台区','320100'),(812,'320115','江宁区','320100'),(813,'320116','六合区','320100'),(814,'320124','溧水县','320100'),(815,'320125','高淳县','320100'),(817,'320202','崇安区','320200'),(818,'320203','南长区','320200'),(819,'320204','北塘区','320200'),(820,'320205','锡山区','320200'),(821,'320206','惠山区','320200'),(822,'320211','滨湖区','320200'),(823,'320281','江阴市','320200'),(824,'320282','宜兴市','320200'),(826,'320302','鼓楼区','320300'),(827,'320303','云龙区','320300'),(828,'320304','九里区','320300'),(829,'320305','贾汪区','320300'),(830,'320311','泉山区','320300'),(831,'320321','丰　县','320300'),(832,'320322','沛　县','320300'),(833,'320323','铜山县','320300'),(834,'320324','睢宁县','320300'),(835,'320381','新沂市','320300'),(836,'320382','邳州市','320300'),(838,'320402','天宁区','320400'),(839,'320404','钟楼区','320400'),(840,'320405','戚墅堰区','320400'),(841,'320411','新北区','320400'),(842,'320412','武进区','320400'),(843,'320481','溧阳市','320400'),(844,'320482','金坛市','320400'),(846,'320502','沧浪区','320500'),(847,'320503','平江区','320500'),(848,'320504','金阊区','320500'),(849,'320505','虎丘区','320500'),(850,'320506','吴中区','320500'),(851,'320507','相城区','320500'),(852,'320581','常熟市','320500'),(853,'320582','张家港市','320500'),(854,'320583','昆山市','320500'),(855,'320584','吴江市','320500'),(856,'320585','太仓市','320500'),(858,'320602','崇川区','320600'),(859,'320611','港闸区','320600'),(860,'320621','海安县','320600'),(861,'320623','如东县','320600'),(862,'320681','启东市','320600'),(863,'320682','如皋市','320600'),(864,'320683','通州市','320600'),(865,'320684','海门市','320600'),(867,'320703','连云区','320700'),(868,'320705','新浦区','320700'),(869,'320706','海州区','320700'),(870,'320721','赣榆县','320700'),(871,'320722','东海县','320700'),(872,'320723','灌云县','320700'),(873,'320724','灌南县','320700'),(875,'320802','清河区','320800'),(876,'320803','楚州区','320800'),(877,'320804','淮阴区','320800'),(878,'320811','清浦区','320800'),(879,'320826','涟水县','320800'),(880,'320829','洪泽县','320800'),(881,'320830','盱眙县','320800'),(882,'320831','金湖县','320800'),(884,'320902','亭湖区','320900'),(885,'320903','盐都区','320900'),(886,'320921','响水县','320900'),(887,'320922','滨海县','320900'),(888,'320923','阜宁县','320900'),(889,'320924','射阳县','320900'),(890,'320925','建湖县','320900'),(891,'320981','东台市','320900'),(892,'320982','大丰市','320900'),(894,'321002','广陵区','321000'),(895,'321003','邗江区','321000'),(896,'321011','郊　区','321000'),(897,'321023','宝应县','321000'),(898,'321081','仪征市','321000'),(899,'321084','高邮市','321000'),(900,'321088','江都市','321000'),(902,'321102','京口区','321100'),(903,'321111','润州区','321100'),(904,'321112','丹徒区','321100'),(905,'321181','丹阳市','321100'),(906,'321182','扬中市','321100'),(907,'321183','句容市','321100'),(909,'321202','海陵区','321200'),(910,'321203','高港区','321200'),(911,'321281','兴化市','321200'),(912,'321282','靖江市','321200'),(913,'321283','泰兴市','321200'),(914,'321284','姜堰市','321200'),(916,'321302','宿城区','321300'),(917,'321311','宿豫区','321300'),(918,'321322','沭阳县','321300'),(919,'321323','泗阳县','321300'),(920,'321324','泗洪县','321300'),(922,'330102','上城区','330100'),(923,'330103','下城区','330100'),(924,'330104','江干区','330100'),(925,'330105','拱墅区','330100'),(926,'330106','西湖区','330100'),(927,'330108','滨江区','330100'),(928,'330109','萧山区','330100'),(929,'330110','余杭区','330100'),(930,'330122','桐庐县','330100'),(931,'330127','淳安县','330100'),(932,'330182','建德市','330100'),(933,'330183','富阳市','330100'),(934,'330185','临安市','330100'),(936,'330203','海曙区','330200'),(937,'330204','江东区','330200'),(938,'330205','江北区','330200'),(939,'330206','北仑区','330200'),(940,'330211','镇海区','330200'),(941,'330212','鄞州区','330200'),(942,'330225','象山县','330200'),(943,'330226','宁海县','330200'),(944,'330281','余姚市','330200'),(945,'330282','慈溪市','330200'),(946,'330283','奉化市','330200'),(948,'330302','鹿城区','330300'),(949,'330303','龙湾区','330300'),(950,'330304','瓯海区','330300'),(951,'330322','洞头县','330300'),(952,'330324','永嘉县','330300'),(953,'330326','平阳县','330300'),(954,'330327','苍南县','330300'),(955,'330328','文成县','330300'),(956,'330329','泰顺县','330300'),(957,'330381','瑞安市','330300'),(958,'330382','乐清市','330300'),(960,'330402','秀城区','330400'),(961,'330411','秀洲区','330400'),(962,'330421','嘉善县','330400'),(963,'330424','海盐县','330400'),(964,'330481','海宁市','330400'),(965,'330482','平湖市','330400'),(966,'330483','桐乡市','330400'),(968,'330502','吴兴区','330500'),(969,'330503','南浔区','330500'),(970,'330521','德清县','330500'),(971,'330522','长兴县','330500'),(972,'330523','安吉县','330500'),(974,'330602','越城区','330600'),(975,'330621','绍兴县','330600'),(976,'330624','新昌县','330600'),(977,'330681','诸暨市','330600'),(978,'330682','上虞市','330600'),(979,'330683','嵊州市','330600'),(981,'330702','婺城区','330700'),(982,'330703','金东区','330700'),(983,'330723','武义县','330700'),(984,'330726','浦江县','330700'),(985,'330727','磐安县','330700'),(986,'330781','兰溪市','330700'),(987,'330782','义乌市','330700'),(988,'330783','东阳市','330700'),(989,'330784','永康市','330700'),(991,'330802','柯城区','330800'),(992,'330803','衢江区','330800'),(993,'330822','常山县','330800'),(994,'330824','开化县','330800'),(995,'330825','龙游县','330800'),(996,'330881','江山市','330800'),(998,'330902','定海区','330900'),(999,'330903','普陀区','330900'),(1000,'330921','岱山县','330900'),(1001,'330922','嵊泗县','330900'),(1003,'331002','椒江区','331000'),(1004,'331003','黄岩区','331000'),(1005,'331004','路桥区','331000'),(1006,'331021','玉环县','331000'),(1007,'331022','三门县','331000'),(1008,'331023','天台县','331000'),(1009,'331024','仙居县','331000'),(1010,'331081','温岭市','331000'),(1011,'331082','临海市','331000'),(1013,'331102','莲都区','331100'),(1014,'331121','青田县','331100'),(1015,'331122','缙云县','331100'),(1016,'331123','遂昌县','331100'),(1017,'331124','松阳县','331100'),(1018,'331125','云和县','331100'),(1019,'331126','庆元县','331100'),(1020,'331127','景宁畲族自治县','331100'),(1021,'331181','龙泉市','331100'),(1023,'340102','瑶海区','340100'),(1024,'340103','庐阳区','340100'),(1025,'340104','蜀山区','340100'),(1026,'340111','包河区','340100'),(1027,'340121','长丰县','340100'),(1028,'340122','肥东县','340100'),(1029,'340123','肥西县','340100'),(1031,'340202','镜湖区','340200'),(1032,'340203','马塘区','340200'),(1033,'340204','新芜区','340200'),(1034,'340207','鸠江区','340200'),(1035,'340221','芜湖县','340200'),(1036,'340222','繁昌县','340200'),(1037,'340223','南陵县','340200'),(1039,'340302','龙子湖区','340300'),(1040,'340303','蚌山区','340300'),(1041,'340304','禹会区','340300'),(1042,'340311','淮上区','340300'),(1043,'340321','怀远县','340300'),(1044,'340322','五河县','340300'),(1045,'340323','固镇县','340300'),(1047,'340402','大通区','340400'),(1048,'340403','田家庵区','340400'),(1049,'340404','谢家集区','340400'),(1050,'340405','八公山区','340400'),(1051,'340406','潘集区','340400'),(1052,'340421','凤台县','340400'),(1054,'340502','金家庄区','340500'),(1055,'340503','花山区','340500'),(1056,'340504','雨山区','340500'),(1057,'340521','当涂县','340500'),(1059,'340602','杜集区','340600'),(1060,'340603','相山区','340600'),(1061,'340604','烈山区','340600'),(1062,'340621','濉溪县','340600'),(1064,'340702','铜官山区','340700'),(1065,'340703','狮子山区','340700'),(1066,'340711','郊　区','340700'),(1067,'340721','铜陵县','340700'),(1069,'340802','迎江区','340800'),(1070,'340803','大观区','340800'),(1071,'340811','郊　区','340800'),(1072,'340822','怀宁县','340800'),(1073,'340823','枞阳县','340800'),(1074,'340824','潜山县','340800'),(1075,'340825','太湖县','340800'),(1076,'340826','宿松县','340800'),(1077,'340827','望江县','340800'),(1078,'340828','岳西县','340800'),(1079,'340881','桐城市','340800'),(1081,'341002','屯溪区','341000'),(1082,'341003','黄山区','341000'),(1083,'341004','徽州区','341000'),(1084,'341021','歙　县','341000'),(1085,'341022','休宁县','341000'),(1086,'341023','黟　县','341000'),(1087,'341024','祁门县','341000'),(1089,'341102','琅琊区','341100'),(1090,'341103','南谯区','341100'),(1091,'341122','来安县','341100'),(1092,'341124','全椒县','341100'),(1093,'341125','定远县','341100'),(1094,'341126','凤阳县','341100'),(1095,'341181','天长市','341100'),(1096,'341182','明光市','341100'),(1098,'341202','颍州区','341200'),(1099,'341203','颍东区','341200'),(1100,'341204','颍泉区','341200'),(1101,'341221','临泉县','341200'),(1102,'341222','太和县','341200'),(1103,'341225','阜南县','341200'),(1104,'341226','颍上县','341200'),(1105,'341282','界首市','341200'),(1107,'341302','墉桥区','341300'),(1108,'341321','砀山县','341300'),(1109,'341322','萧　县','341300'),(1110,'341323','灵璧县','341300'),(1111,'341324','泗　县','341300'),(1113,'341402','居巢区','341400'),(1114,'341421','庐江县','341400'),(1115,'341422','无为县','341400'),(1116,'341423','含山县','341400'),(1117,'341424','和　县','341400'),(1119,'341502','金安区','341500'),(1120,'341503','裕安区','341500'),(1121,'341521','寿　县','341500'),(1122,'341522','霍邱县','341500'),(1123,'341523','舒城县','341500'),(1124,'341524','金寨县','341500'),(1125,'341525','霍山县','341500'),(1127,'341602','谯城区','341600'),(1128,'341621','涡阳县','341600'),(1129,'341622','蒙城县','341600'),(1130,'341623','利辛县','341600'),(1132,'341702','贵池区','341700'),(1133,'341721','东至县','341700'),(1134,'341722','石台县','341700'),(1135,'341723','青阳县','341700'),(1137,'341802','宣州区','341800'),(1138,'341821','郎溪县','341800'),(1139,'341822','广德县','341800'),(1140,'341823','泾　县','341800'),(1141,'341824','绩溪县','341800'),(1142,'341825','旌德县','341800'),(1143,'341881','宁国市','341800'),(1145,'350102','鼓楼区','350100'),(1146,'350103','台江区','350100'),(1147,'350104','仓山区','350100'),(1148,'350105','马尾区','350100'),(1149,'350111','晋安区','350100'),(1150,'350121','闽侯县','350100'),(1151,'350122','连江县','350100'),(1152,'350123','罗源县','350100'),(1153,'350124','闽清县','350100'),(1154,'350125','永泰县','350100'),(1155,'350128','平潭县','350100'),(1156,'350181','福清市','350100'),(1157,'350182','长乐市','350100'),(1159,'350203','思明区','350200'),(1160,'350205','海沧区','350200'),(1161,'350206','湖里区','350200'),(1162,'350211','集美区','350200'),(1163,'350212','同安区','350200'),(1164,'350213','翔安区','350200'),(1166,'350302','城厢区','350300'),(1167,'350303','涵江区','350300'),(1168,'350304','荔城区','350300'),(1169,'350305','秀屿区','350300'),(1170,'350322','仙游县','350300'),(1172,'350402','梅列区','350400'),(1173,'350403','三元区','350400'),(1174,'350421','明溪县','350400'),(1175,'350423','清流县','350400'),(1176,'350424','宁化县','350400'),(1177,'350425','大田县','350400'),(1178,'350426','尤溪县','350400'),(1179,'350427','沙　县','350400'),(1180,'350428','将乐县','350400'),(1181,'350429','泰宁县','350400'),(1182,'350430','建宁县','350400'),(1183,'350481','永安市','350400'),(1185,'350502','鲤城区','350500'),(1186,'350503','丰泽区','350500'),(1187,'350504','洛江区','350500'),(1188,'350505','泉港区','350500'),(1189,'350521','惠安县','350500'),(1190,'350524','安溪县','350500'),(1191,'350525','永春县','350500'),(1192,'350526','德化县','350500'),(1193,'350527','金门县','350500'),(1194,'350581','石狮市','350500'),(1195,'350582','晋江市','350500'),(1196,'350583','南安市','350500'),(1198,'350602','芗城区','350600'),(1199,'350603','龙文区','350600'),(1200,'350622','云霄县','350600'),(1201,'350623','漳浦县','350600'),(1202,'350624','诏安县','350600'),(1203,'350625','长泰县','350600'),(1204,'350626','东山县','350600'),(1205,'350627','南靖县','350600'),(1206,'350628','平和县','350600'),(1207,'350629','华安县','350600'),(1208,'350681','龙海市','350600'),(1210,'350702','延平区','350700'),(1211,'350721','顺昌县','350700'),(1212,'350722','浦城县','350700'),(1213,'350723','光泽县','350700'),(1214,'350724','松溪县','350700'),(1215,'350725','政和县','350700'),(1216,'350781','邵武市','350700'),(1217,'350782','武夷山市','350700'),(1218,'350783','建瓯市','350700'),(1219,'350784','建阳市','350700'),(1221,'350802','新罗区','350800'),(1222,'350821','长汀县','350800'),(1223,'350822','永定县','350800'),(1224,'350823','上杭县','350800'),(1225,'350824','武平县','350800'),(1226,'350825','连城县','350800'),(1227,'350881','漳平市','350800'),(1229,'350902','蕉城区','350900'),(1230,'350921','霞浦县','350900'),(1231,'350922','古田县','350900'),(1232,'350923','屏南县','350900'),(1233,'350924','寿宁县','350900'),(1234,'350925','周宁县','350900'),(1235,'350926','柘荣县','350900'),(1236,'350981','福安市','350900'),(1237,'350982','福鼎市','350900'),(1239,'360102','东湖区','360100'),(1240,'360103','西湖区','360100'),(1241,'360104','青云谱区','360100'),(1242,'360105','湾里区','360100'),(1243,'360111','青山湖区','360100'),(1244,'360121','南昌县','360100'),(1245,'360122','新建县','360100'),(1246,'360123','安义县','360100'),(1247,'360124','进贤县','360100'),(1249,'360202','昌江区','360200'),(1250,'360203','珠山区','360200'),(1251,'360222','浮梁县','360200'),(1252,'360281','乐平市','360200'),(1254,'360302','安源区','360300'),(1255,'360313','湘东区','360300'),(1256,'360321','莲花县','360300'),(1257,'360322','上栗县','360300'),(1258,'360323','芦溪县','360300'),(1260,'360402','庐山区','360400'),(1261,'360403','浔阳区','360400'),(1262,'360421','九江县','360400'),(1263,'360423','武宁县','360400'),(1264,'360424','修水县','360400'),(1265,'360425','永修县','360400'),(1266,'360426','德安县','360400'),(1267,'360427','星子县','360400'),(1268,'360428','都昌县','360400'),(1269,'360429','湖口县','360400'),(1270,'360430','彭泽县','360400'),(1271,'360481','瑞昌市','360400'),(1273,'360502','渝水区','360500'),(1274,'360521','分宜县','360500'),(1276,'360602','月湖区','360600'),(1277,'360622','余江县','360600'),(1278,'360681','贵溪市','360600'),(1280,'360702','章贡区','360700'),(1281,'360721','赣　县','360700'),(1282,'360722','信丰县','360700'),(1283,'360723','大余县','360700'),(1284,'360724','上犹县','360700'),(1285,'360725','崇义县','360700'),(1286,'360726','安远县','360700'),(1287,'360727','龙南县','360700'),(1288,'360728','定南县','360700'),(1289,'360729','全南县','360700'),(1290,'360730','宁都县','360700'),(1291,'360731','于都县','360700'),(1292,'360732','兴国县','360700'),(1293,'360733','会昌县','360700'),(1294,'360734','寻乌县','360700'),(1295,'360735','石城县','360700'),(1296,'360781','瑞金市','360700'),(1297,'360782','南康市','360700'),(1299,'360802','吉州区','360800'),(1300,'360803','青原区','360800'),(1301,'360821','吉安县','360800'),(1302,'360822','吉水县','360800'),(1303,'360823','峡江县','360800'),(1304,'360824','新干县','360800'),(1305,'360825','永丰县','360800'),(1306,'360826','泰和县','360800'),(1307,'360827','遂川县','360800'),(1308,'360828','万安县','360800'),(1309,'360829','安福县','360800'),(1310,'360830','永新县','360800'),(1311,'360881','井冈山市','360800'),(1313,'360902','袁州区','360900'),(1314,'360921','奉新县','360900'),(1315,'360922','万载县','360900'),(1316,'360923','上高县','360900'),(1317,'360924','宜丰县','360900'),(1318,'360925','靖安县','360900'),(1319,'360926','铜鼓县','360900'),(1320,'360981','丰城市','360900'),(1321,'360982','樟树市','360900'),(1322,'360983','高安市','360900'),(1324,'361002','临川区','361000'),(1325,'361021','南城县','361000'),(1326,'361022','黎川县','361000'),(1327,'361023','南丰县','361000'),(1328,'361024','崇仁县','361000'),(1329,'361025','乐安县','361000'),(1330,'361026','宜黄县','361000'),(1331,'361027','金溪县','361000'),(1332,'361028','资溪县','361000'),(1333,'361029','东乡县','361000'),(1334,'361030','广昌县','361000'),(1336,'361102','信州区','361100'),(1337,'361121','上饶县','361100'),(1338,'361122','广丰县','361100'),(1339,'361123','玉山县','361100'),(1340,'361124','铅山县','361100'),(1341,'361125','横峰县','361100'),(1342,'361126','弋阳县','361100'),(1343,'361127','余干县','361100'),(1344,'361128','鄱阳县','361100'),(1345,'361129','万年县','361100'),(1346,'361130','婺源县','361100'),(1347,'361181','德兴市','361100'),(1349,'370102','历下区','370100'),(1350,'370103','市中区','370100'),(1351,'370104','槐荫区','370100'),(1352,'370105','天桥区','370100'),(1353,'370112','历城区','370100'),(1354,'370113','长清区','370100'),(1355,'370124','平阴县','370100'),(1356,'370125','济阳县','370100'),(1357,'370126','商河县','370100'),(1358,'370181','章丘市','370100'),(1360,'370202','市南区','370200'),(1361,'370203','市北区','370200'),(1362,'370205','四方区','370200'),(1363,'370211','黄岛区','370200'),(1364,'370212','崂山区','370200'),(1365,'370213','李沧区','370200'),(1366,'370214','城阳区','370200'),(1367,'370281','胶州市','370200'),(1368,'370282','即墨市','370200'),(1369,'370283','平度市','370200'),(1370,'370284','胶南市','370200'),(1371,'370285','莱西市','370200'),(1373,'370302','淄川区','370300'),(1374,'370303','张店区','370300'),(1375,'370304','博山区','370300'),(1376,'370305','临淄区','370300'),(1377,'370306','周村区','370300'),(1378,'370321','桓台县','370300'),(1379,'370322','高青县','370300'),(1380,'370323','沂源县','370300'),(1382,'370402','市中区','370400'),(1383,'370403','薛城区','370400'),(1384,'370404','峄城区','370400'),(1385,'370405','台儿庄区','370400'),(1386,'370406','山亭区','370400'),(1387,'370481','滕州市','370400'),(1389,'370502','东营区','370500'),(1390,'370503','河口区','370500'),(1391,'370521','垦利县','370500'),(1392,'370522','利津县','370500'),(1393,'370523','广饶县','370500'),(1395,'370602','芝罘区','370600'),(1396,'370611','福山区','370600'),(1397,'370612','牟平区','370600'),(1398,'370613','莱山区','370600'),(1399,'370634','长岛县','370600'),(1400,'370681','龙口市','370600'),(1401,'370682','莱阳市','370600'),(1402,'370683','莱州市','370600'),(1403,'370684','蓬莱市','370600'),(1404,'370685','招远市','370600'),(1405,'370686','栖霞市','370600'),(1406,'370687','海阳市','370600'),(1408,'370702','潍城区','370700'),(1409,'370703','寒亭区','370700'),(1410,'370704','坊子区','370700'),(1411,'370705','奎文区','370700'),(1412,'370724','临朐县','370700'),(1413,'370725','昌乐县','370700'),(1414,'370781','青州市','370700'),(1415,'370782','诸城市','370700'),(1416,'370783','寿光市','370700'),(1417,'370784','安丘市','370700'),(1418,'370785','高密市','370700'),(1419,'370786','昌邑市','370700'),(1421,'370802','市中区','370800'),(1422,'370811','任城区','370800'),(1423,'370826','微山县','370800'),(1424,'370827','鱼台县','370800'),(1425,'370828','金乡县','370800'),(1426,'370829','嘉祥县','370800'),(1427,'370830','汶上县','370800'),(1428,'370831','泗水县','370800'),(1429,'370832','梁山县','370800'),(1430,'370881','曲阜市','370800'),(1431,'370882','兖州市','370800'),(1432,'370883','邹城市','370800'),(1434,'370902','泰山区','370900'),(1435,'370903','岱岳区','370900'),(1436,'370921','宁阳县','370900'),(1437,'370923','东平县','370900'),(1438,'370982','新泰市','370900'),(1439,'370983','肥城市','370900'),(1441,'371002','环翠区','371000'),(1442,'371081','文登市','371000'),(1443,'371082','荣成市','371000'),(1444,'371083','乳山市','371000'),(1446,'371102','东港区','371100'),(1447,'371103','岚山区','371100'),(1448,'371121','五莲县','371100'),(1449,'371122','莒　县','371100'),(1451,'371202','莱城区','371200'),(1452,'371203','钢城区','371200'),(1454,'371302','兰山区','371300'),(1455,'371311','罗庄区','371300'),(1456,'371312','河东区','371300'),(1457,'371321','沂南县','371300'),(1458,'371322','郯城县','371300'),(1459,'371323','沂水县','371300'),(1460,'371324','苍山县','371300'),(1461,'371325','费　县','371300'),(1462,'371326','平邑县','371300'),(1463,'371327','莒南县','371300'),(1464,'371328','蒙阴县','371300'),(1465,'371329','临沭县','371300'),(1467,'371402','德城区','371400'),(1468,'371421','陵　县','371400'),(1469,'371422','宁津县','371400'),(1470,'371423','庆云县','371400'),(1471,'371424','临邑县','371400'),(1472,'371425','齐河县','371400'),(1473,'371426','平原县','371400'),(1474,'371427','夏津县','371400'),(1475,'371428','武城县','371400'),(1476,'371481','乐陵市','371400'),(1477,'371482','禹城市','371400'),(1479,'371502','东昌府区','371500'),(1480,'371521','阳谷县','371500'),(1481,'371522','莘　县','371500'),(1482,'371523','茌平县','371500'),(1483,'371524','东阿县','371500'),(1484,'371525','冠　县','371500'),(1485,'371526','高唐县','371500'),(1486,'371581','临清市','371500'),(1488,'371602','滨城区','371600'),(1489,'371621','惠民县','371600'),(1490,'371622','阳信县','371600'),(1491,'371623','无棣县','371600'),(1492,'371624','沾化县','371600'),(1493,'371625','博兴县','371600'),(1494,'371626','邹平县','371600'),(1496,'371702','牡丹区','371700'),(1497,'371721','曹　县','371700'),(1498,'371722','单　县','371700'),(1499,'371723','成武县','371700'),(1500,'371724','巨野县','371700'),(1501,'371725','郓城县','371700'),(1502,'371726','鄄城县','371700'),(1503,'371727','定陶县','371700'),(1504,'371728','东明县','371700'),(1506,'410102','中原区','410100'),(1507,'410103','二七区','410100'),(1508,'410104','管城回族区','410100'),(1509,'410105','金水区','410100'),(1510,'410106','上街区','410100'),(1511,'410108','邙山区','410100'),(1512,'410122','中牟县','410100'),(1513,'410181','巩义市','410100'),(1514,'410182','荥阳市','410100'),(1515,'410183','新密市','410100'),(1516,'410184','新郑市','410100'),(1517,'410185','登封市','410100'),(1519,'410202','龙亭区','410200'),(1520,'410203','顺河回族区','410200'),(1521,'410204','鼓楼区','410200'),(1522,'410205','南关区','410200'),(1523,'410211','郊　区','410200'),(1524,'410221','杞　县','410200'),(1525,'410222','通许县','410200'),(1526,'410223','尉氏县','410200'),(1527,'410224','开封县','410200'),(1528,'410225','兰考县','410200'),(1530,'410302','老城区','410300'),(1531,'410303','西工区','410300'),(1532,'410304','廛河回族区','410300'),(1533,'410305','涧西区','410300'),(1534,'410306','吉利区','410300'),(1535,'410307','洛龙区','410300'),(1536,'410322','孟津县','410300'),(1537,'410323','新安县','410300'),(1538,'410324','栾川县','410300'),(1539,'410325','嵩　县','410300'),(1540,'410326','汝阳县','410300'),(1541,'410327','宜阳县','410300'),(1542,'410328','洛宁县','410300'),(1543,'410329','伊川县','410300'),(1544,'410381','偃师市','410300'),(1546,'410402','新华区','410400'),(1547,'410403','卫东区','410400'),(1548,'410404','石龙区','410400'),(1549,'410411','湛河区','410400'),(1550,'410421','宝丰县','410400'),(1551,'410422','叶　县','410400'),(1552,'410423','鲁山县','410400'),(1553,'410425','郏　县','410400'),(1554,'410481','舞钢市','410400'),(1555,'410482','汝州市','410400'),(1557,'410502','文峰区','410500'),(1558,'410503','北关区','410500'),(1559,'410505','殷都区','410500'),(1560,'410506','龙安区','410500'),(1561,'410522','安阳县','410500'),(1562,'410523','汤阴县','410500'),(1563,'410526','滑　县','410500'),(1564,'410527','内黄县','410500'),(1565,'410581','林州市','410500'),(1567,'410602','鹤山区','410600'),(1568,'410603','山城区','410600'),(1569,'410611','淇滨区','410600'),(1570,'410621','浚　县','410600'),(1571,'410622','淇　县','410600'),(1573,'410702','红旗区','410700'),(1574,'410703','卫滨区','410700'),(1575,'410704','凤泉区','410700'),(1576,'410711','牧野区','410700'),(1577,'410721','新乡县','410700'),(1578,'410724','获嘉县','410700'),(1579,'410725','原阳县','410700'),(1580,'410726','延津县','410700'),(1581,'410727','封丘县','410700'),(1582,'410728','长垣县','410700'),(1583,'410781','卫辉市','410700'),(1584,'410782','辉县市','410700'),(1586,'410802','解放区','410800'),(1587,'410803','中站区','410800'),(1588,'410804','马村区','410800'),(1589,'410811','山阳区','410800'),(1590,'410821','修武县','410800'),(1591,'410822','博爱县','410800'),(1592,'410823','武陟县','410800'),(1593,'410825','温　县','410800'),(1594,'410881','济源市','410800'),(1595,'410882','沁阳市','410800'),(1596,'410883','孟州市','410800'),(1598,'410902','华龙区','410900'),(1599,'410922','清丰县','410900'),(1600,'410923','南乐县','410900'),(1601,'410926','范　县','410900'),(1602,'410927','台前县','410900'),(1603,'410928','濮阳县','410900'),(1605,'411002','魏都区','411000'),(1606,'411023','许昌县','411000'),(1607,'411024','鄢陵县','411000'),(1608,'411025','襄城县','411000'),(1609,'411081','禹州市','411000'),(1610,'411082','长葛市','411000'),(1612,'411102','源汇区','411100'),(1613,'411103','郾城区','411100'),(1614,'411104','召陵区','411100'),(1615,'411121','舞阳县','411100'),(1616,'411122','临颍县','411100'),(1618,'411202','湖滨区','411200'),(1619,'411221','渑池县','411200'),(1620,'411222','陕　县','411200'),(1621,'411224','卢氏县','411200'),(1622,'411281','义马市','411200'),(1623,'411282','灵宝市','411200'),(1625,'411302','宛城区','411300'),(1626,'411303','卧龙区','411300'),(1627,'411321','南召县','411300'),(1628,'411322','方城县','411300'),(1629,'411323','西峡县','411300'),(1630,'411324','镇平县','411300'),(1631,'411325','内乡县','411300'),(1632,'411326','淅川县','411300'),(1633,'411327','社旗县','411300'),(1634,'411328','唐河县','411300'),(1635,'411329','新野县','411300'),(1636,'411330','桐柏县','411300'),(1637,'411381','邓州市','411300'),(1639,'411402','梁园区','411400'),(1640,'411403','睢阳区','411400'),(1641,'411421','民权县','411400'),(1642,'411422','睢　县','411400'),(1643,'411423','宁陵县','411400'),(1644,'411424','柘城县','411400'),(1645,'411425','虞城县','411400'),(1646,'411426','夏邑县','411400'),(1647,'411481','永城市','411400'),(1649,'411502','师河区','411500'),(1650,'411503','平桥区','411500'),(1651,'411521','罗山县','411500'),(1652,'411522','光山县','411500'),(1653,'411523','新　县','411500'),(1654,'411524','商城县','411500'),(1655,'411525','固始县','411500'),(1656,'411526','潢川县','411500'),(1657,'411527','淮滨县','411500'),(1658,'411528','息　县','411500'),(1660,'411602','川汇区','411600'),(1661,'411621','扶沟县','411600'),(1662,'411622','西华县','411600'),(1663,'411623','商水县','411600'),(1664,'411624','沈丘县','411600'),(1665,'411625','郸城县','411600'),(1666,'411626','淮阳县','411600'),(1667,'411627','太康县','411600'),(1668,'411628','鹿邑县','411600'),(1669,'411681','项城市','411600'),(1671,'411702','驿城区','411700'),(1672,'411721','西平县','411700'),(1673,'411722','上蔡县','411700'),(1674,'411723','平舆县','411700'),(1675,'411724','正阳县','411700'),(1676,'411725','确山县','411700'),(1677,'411726','泌阳县','411700'),(1678,'411727','汝南县','411700'),(1679,'411728','遂平县','411700'),(1680,'411729','新蔡县','411700'),(1682,'420102','江岸区','420100'),(1683,'420103','江汉区','420100'),(1684,'420104','乔口区','420100'),(1685,'420105','汉阳区','420100'),(1686,'420106','武昌区','420100'),(1687,'420107','青山区','420100'),(1688,'420111','洪山区','420100'),(1689,'420112','东西湖区','420100'),(1690,'420113','汉南区','420100'),(1691,'420114','蔡甸区','420100'),(1692,'420115','江夏区','420100'),(1693,'420116','黄陂区','420100'),(1694,'420117','新洲区','420100'),(1696,'420202','黄石港区','420200'),(1697,'420203','西塞山区','420200'),(1698,'420204','下陆区','420200'),(1699,'420205','铁山区','420200'),(1700,'420222','阳新县','420200'),(1701,'420281','大冶市','420200'),(1703,'420302','茅箭区','420300'),(1704,'420303','张湾区','420300'),(1705,'420321','郧　县','420300'),(1706,'420322','郧西县','420300'),(1707,'420323','竹山县','420300'),(1708,'420324','竹溪县','420300'),(1709,'420325','房　县','420300'),(1710,'420381','丹江口市','420300'),(1712,'420502','西陵区','420500'),(1713,'420503','伍家岗区','420500'),(1714,'420504','点军区','420500'),(1715,'420505','猇亭区','420500'),(1716,'420506','夷陵区','420500'),(1717,'420525','远安县','420500'),(1718,'420526','兴山县','420500'),(1719,'420527','秭归县','420500'),(1720,'420528','长阳土家族自治县','420500'),(1721,'420529','五峰土家族自治县','420500'),(1722,'420581','宜都市','420500'),(1723,'420582','当阳市','420500'),(1724,'420583','枝江市','420500'),(1726,'420602','襄城区','420600'),(1727,'420606','樊城区','420600'),(1728,'420607','襄阳区','420600'),(1729,'420624','南漳县','420600'),(1730,'420625','谷城县','420600'),(1731,'420626','保康县','420600'),(1732,'420682','老河口市','420600'),(1733,'420683','枣阳市','420600'),(1734,'420684','宜城市','420600'),(1736,'420702','梁子湖区','420700'),(1737,'420703','华容区','420700'),(1738,'420704','鄂城区','420700'),(1740,'420802','东宝区','420800'),(1741,'420804','掇刀区','420800'),(1742,'420821','京山县','420800'),(1743,'420822','沙洋县','420800'),(1744,'420881','钟祥市','420800'),(1746,'420902','孝南区','420900'),(1747,'420921','孝昌县','420900'),(1748,'420922','大悟县','420900'),(1749,'420923','云梦县','420900'),(1750,'420981','应城市','420900'),(1751,'420982','安陆市','420900'),(1752,'420984','汉川市','420900'),(1754,'421002','沙市区','421000'),(1755,'421003','荆州区','421000'),(1756,'421022','公安县','421000'),(1757,'421023','监利县','421000'),(1758,'421024','江陵县','421000'),(1759,'421081','石首市','421000'),(1760,'421083','洪湖市','421000'),(1761,'421087','松滋市','421000'),(1763,'421102','黄州区','421100'),(1764,'421121','团风县','421100'),(1765,'421122','红安县','421100'),(1766,'421123','罗田县','421100'),(1767,'421124','英山县','421100'),(1768,'421125','浠水县','421100'),(1769,'421126','蕲春县','421100'),(1770,'421127','黄梅县','421100'),(1771,'421181','麻城市','421100'),(1772,'421182','武穴市','421100'),(1774,'421202','咸安区','421200'),(1775,'421221','嘉鱼县','421200'),(1776,'421222','通城县','421200'),(1777,'421223','崇阳县','421200'),(1778,'421224','通山县','421200'),(1779,'421281','赤壁市','421200'),(1781,'421302','曾都区','421300'),(1782,'421381','广水市','421300'),(1783,'422801','恩施市','422800'),(1784,'422802','利川市','422800'),(1785,'422822','建始县','422800'),(1786,'422823','巴东县','422800'),(1787,'422825','宣恩县','422800'),(1788,'422826','咸丰县','422800'),(1789,'422827','来凤县','422800'),(1790,'422828','鹤峰县','422800'),(1791,'429004','仙桃市','429000'),(1792,'429005','潜江市','429000'),(1793,'429006','天门市','429000'),(1794,'429021','神农架林区','429000'),(1796,'430102','芙蓉区','430100'),(1797,'430103','天心区','430100'),(1798,'430104','岳麓区','430100'),(1799,'430105','开福区','430100'),(1800,'430111','雨花区','430100'),(1801,'430121','长沙县','430100'),(1802,'430122','望城县','430100'),(1803,'430124','宁乡县','430100'),(1804,'430181','浏阳市','430100'),(1806,'430202','荷塘区','430200'),(1807,'430203','芦淞区','430200'),(1808,'430204','石峰区','430200'),(1809,'430211','天元区','430200'),(1810,'430221','株洲县','430200'),(1811,'430223','攸　县','430200'),(1812,'430224','茶陵县','430200'),(1813,'430225','炎陵县','430200'),(1814,'430281','醴陵市','430200'),(1816,'430302','雨湖区','430300'),(1817,'430304','岳塘区','430300'),(1818,'430321','湘潭县','430300'),(1819,'430381','湘乡市','430300'),(1820,'430382','韶山市','430300'),(1822,'430405','珠晖区','430400'),(1823,'430406','雁峰区','430400'),(1824,'430407','石鼓区','430400'),(1825,'430408','蒸湘区','430400'),(1826,'430412','南岳区','430400'),(1827,'430421','衡阳县','430400'),(1828,'430422','衡南县','430400'),(1829,'430423','衡山县','430400'),(1830,'430424','衡东县','430400'),(1831,'430426','祁东县','430400'),(1832,'430481','耒阳市','430400'),(1833,'430482','常宁市','430400'),(1835,'430502','双清区','430500'),(1836,'430503','大祥区','430500'),(1837,'430511','北塔区','430500'),(1838,'430521','邵东县','430500'),(1839,'430522','新邵县','430500'),(1840,'430523','邵阳县','430500'),(1841,'430524','隆回县','430500'),(1842,'430525','洞口县','430500'),(1843,'430527','绥宁县','430500'),(1844,'430528','新宁县','430500'),(1845,'430529','城步苗族自治县','430500'),(1846,'430581','武冈市','430500'),(1848,'430602','岳阳楼区','430600'),(1849,'430603','云溪区','430600'),(1850,'430611','君山区','430600'),(1851,'430621','岳阳县','430600'),(1852,'430623','华容县','430600'),(1853,'430624','湘阴县','430600'),(1854,'430626','平江县','430600'),(1855,'430681','汨罗市','430600'),(1856,'430682','临湘市','430600'),(1858,'430702','武陵区','430700'),(1859,'430703','鼎城区','430700'),(1860,'430721','安乡县','430700'),(1861,'430722','汉寿县','430700'),(1862,'430723','澧　县','430700'),(1863,'430724','临澧县','430700'),(1864,'430725','桃源县','430700'),(1865,'430726','石门县','430700'),(1866,'430781','津市市','430700'),(1868,'430802','永定区','430800'),(1869,'430811','武陵源区','430800'),(1870,'430821','慈利县','430800'),(1871,'430822','桑植县','430800'),(1873,'430902','资阳区','430900'),(1874,'430903','赫山区','430900'),(1875,'430921','南　县','430900'),(1876,'430922','桃江县','430900'),(1877,'430923','安化县','430900'),(1878,'430981','沅江市','430900'),(1880,'431002','北湖区','431000'),(1881,'431003','苏仙区','431000'),(1882,'431021','桂阳县','431000'),(1883,'431022','宜章县','431000'),(1884,'431023','永兴县','431000'),(1885,'431024','嘉禾县','431000'),(1886,'431025','临武县','431000'),(1887,'431026','汝城县','431000'),(1888,'431027','桂东县','431000'),(1889,'431028','安仁县','431000'),(1890,'431081','资兴市','431000'),(1892,'431102','芝山区','431100'),(1893,'431103','冷水滩区','431100'),(1894,'431121','祁阳县','431100'),(1895,'431122','东安县','431100'),(1896,'431123','双牌县','431100'),(1897,'431124','道　县','431100'),(1898,'431125','江永县','431100'),(1899,'431126','宁远县','431100'),(1900,'431127','蓝山县','431100'),(1901,'431128','新田县','431100'),(1902,'431129','江华瑶族自治县','431100'),(1904,'431202','鹤城区','431200'),(1905,'431221','中方县','431200'),(1906,'431222','沅陵县','431200'),(1907,'431223','辰溪县','431200'),(1908,'431224','溆浦县','431200'),(1909,'431225','会同县','431200'),(1910,'431226','麻阳苗族自治县','431200'),(1911,'431227','新晃侗族自治县','431200'),(1912,'431228','芷江侗族自治县','431200'),(1913,'431229','靖州苗族侗族自治县','431200'),(1914,'431230','通道侗族自治县','431200'),(1915,'431281','洪江市','431200'),(1917,'431302','娄星区','431300'),(1918,'431321','双峰县','431300'),(1919,'431322','新化县','431300'),(1920,'431381','冷水江市','431300'),(1921,'431382','涟源市','431300'),(1922,'433101','吉首市','433100'),(1923,'433122','泸溪县','433100'),(1924,'433123','凤凰县','433100'),(1925,'433124','花垣县','433100'),(1926,'433125','保靖县','433100'),(1927,'433126','古丈县','433100'),(1928,'433127','永顺县','433100'),(1929,'433130','龙山县','433100'),(1931,'440102','东山区','440100'),(1932,'440103','荔湾区','440100'),(1933,'440104','越秀区','440100'),(1934,'440105','海珠区','440100'),(1935,'440106','天河区','440100'),(1936,'440107','芳村区','440100'),(1937,'440111','白云区','440100'),(1938,'440112','黄埔区','440100'),(1939,'440113','番禺区','440100'),(1940,'440114','花都区','440100'),(1941,'440183','增城市','440100'),(1942,'440184','从化市','440100'),(1944,'440203','武江区','440200'),(1945,'440204','浈江区','440200'),(1946,'440205','曲江区','440200'),(1947,'440222','始兴县','440200'),(1948,'440224','仁化县','440200'),(1949,'440229','翁源县','440200'),(1950,'440232','乳源瑶族自治县','440200'),(1951,'440233','新丰县','440200'),(1952,'440281','乐昌市','440200'),(1953,'440282','南雄市','440200'),(1955,'440303','罗湖区','440300'),(1956,'440304','福田区','440300'),(1957,'440305','南山区','440300'),(1958,'440306','宝安区','440300'),(1959,'440307','龙岗区','440300'),(1960,'440308','盐田区','440300'),(1962,'440402','香洲区','440400'),(1963,'440403','斗门区','440400'),(1964,'440404','金湾区','440400'),(1966,'440507','龙湖区','440500'),(1967,'440511','金平区','440500'),(1968,'440512','濠江区','440500'),(1969,'440513','潮阳区','440500'),(1970,'440514','潮南区','440500'),(1971,'440515','澄海区','440500'),(1972,'440523','南澳县','440500'),(1974,'440604','禅城区','440600'),(1975,'440605','南海区','440600'),(1976,'440606','顺德区','440600'),(1977,'440607','三水区','440600'),(1978,'440608','高明区','440600'),(1980,'440703','蓬江区','440700'),(1981,'440704','江海区','440700'),(1982,'440705','新会区','440700'),(1983,'440781','台山市','440700'),(1984,'440783','开平市','440700'),(1985,'440784','鹤山市','440700'),(1986,'440785','恩平市','440700'),(1988,'440802','赤坎区','440800'),(1989,'440803','霞山区','440800'),(1990,'440804','坡头区','440800'),(1991,'440811','麻章区','440800'),(1992,'440823','遂溪县','440800'),(1993,'440825','徐闻县','440800'),(1994,'440881','廉江市','440800'),(1995,'440882','雷州市','440800'),(1996,'440883','吴川市','440800'),(1998,'440902','茂南区','440900'),(1999,'440903','茂港区','440900'),(2000,'440923','电白县','440900'),(2001,'440981','高州市','440900'),(2002,'440982','化州市','440900'),(2003,'440983','信宜市','440900'),(2005,'441202','端州区','441200'),(2006,'441203','鼎湖区','441200'),(2007,'441223','广宁县','441200'),(2008,'441224','怀集县','441200'),(2009,'441225','封开县','441200'),(2010,'441226','德庆县','441200'),(2011,'441283','高要市','441200'),(2012,'441284','四会市','441200'),(2014,'441302','惠城区','441300'),(2015,'441303','惠阳区','441300'),(2016,'441322','博罗县','441300'),(2017,'441323','惠东县','441300'),(2018,'441324','龙门县','441300'),(2020,'441402','梅江区','441400'),(2021,'441421','梅　县','441400'),(2022,'441422','大埔县','441400'),(2023,'441423','丰顺县','441400'),(2024,'441424','五华县','441400'),(2025,'441426','平远县','441400'),(2026,'441427','蕉岭县','441400'),(2027,'441481','兴宁市','441400'),(2029,'441502','城　区','441500'),(2030,'441521','海丰县','441500'),(2031,'441523','陆河县','441500'),(2032,'441581','陆丰市','441500'),(2034,'441602','源城区','441600'),(2035,'441621','紫金县','441600'),(2036,'441622','龙川县','441600'),(2037,'441623','连平县','441600'),(2038,'441624','和平县','441600'),(2039,'441625','东源县','441600'),(2041,'441702','江城区','441700'),(2042,'441721','阳西县','441700'),(2043,'441723','阳东县','441700'),(2044,'441781','阳春市','441700'),(2046,'441802','清城区','441800'),(2047,'441821','佛冈县','441800'),(2048,'441823','阳山县','441800'),(2049,'441825','连山壮族瑶族自治县','441800'),(2050,'441826','连南瑶族自治县','441800'),(2051,'441827','清新县','441800'),(2052,'441881','英德市','441800'),(2053,'441882','连州市','441800'),(2055,'445102','湘桥区','445100'),(2056,'445121','潮安县','445100'),(2057,'445122','饶平县','445100'),(2059,'445202','榕城区','445200'),(2060,'445221','揭东县','445200'),(2061,'445222','揭西县','445200'),(2062,'445224','惠来县','445200'),(2063,'445281','普宁市','445200'),(2065,'445302','云城区','445300'),(2066,'445321','新兴县','445300'),(2067,'445322','郁南县','445300'),(2068,'445323','云安县','445300'),(2069,'445381','罗定市','445300'),(2071,'450102','兴宁区','450100'),(2072,'450103','青秀区','450100'),(2073,'450105','江南区','450100'),(2074,'450107','西乡塘区','450100'),(2075,'450108','良庆区','450100'),(2076,'450109','邕宁区','450100'),(2077,'450122','武鸣县','450100'),(2078,'450123','隆安县','450100'),(2079,'450124','马山县','450100'),(2080,'450125','上林县','450100'),(2081,'450126','宾阳县','450100'),(2082,'450127','横　县','450100'),(2084,'450202','城中区','450200'),(2085,'450203','鱼峰区','450200'),(2086,'450204','柳南区','450200'),(2087,'450205','柳北区','450200'),(2088,'450221','柳江县','450200'),(2089,'450222','柳城县','450200'),(2090,'450223','鹿寨县','450200'),(2091,'450224','融安县','450200'),(2092,'450225','融水苗族自治县','450200'),(2093,'450226','三江侗族自治县','450200'),(2095,'450302','秀峰区','450300'),(2096,'450303','叠彩区','450300'),(2097,'450304','象山区','450300'),(2098,'450305','七星区','450300'),(2099,'450311','雁山区','450300'),(2100,'450321','阳朔县','450300'),(2101,'450322','临桂县','450300'),(2102,'450323','灵川县','450300'),(2103,'450324','全州县','450300'),(2104,'450325','兴安县','450300'),(2105,'450326','永福县','450300'),(2106,'450327','灌阳县','450300'),(2107,'450328','龙胜各族自治县','450300'),(2108,'450329','资源县','450300'),(2109,'450330','平乐县','450300'),(2110,'450331','荔蒲县','450300'),(2111,'450332','恭城瑶族自治县','450300'),(2113,'450403','万秀区','450400'),(2114,'450404','蝶山区','450400'),(2115,'450405','长洲区','450400'),(2116,'450421','苍梧县','450400'),(2117,'450422','藤　县','450400'),(2118,'450423','蒙山县','450400'),(2119,'450481','岑溪市','450400'),(2121,'450502','海城区','450500'),(2122,'450503','银海区','450500'),(2123,'450512','铁山港区','450500'),(2124,'450521','合浦县','450500'),(2126,'450602','港口区','450600'),(2127,'450603','防城区','450600'),(2128,'450621','上思县','450600'),(2129,'450681','东兴市','450600'),(2131,'450702','钦南区','450700'),(2132,'450703','钦北区','450700'),(2133,'450721','灵山县','450700'),(2134,'450722','浦北县','450700'),(2136,'450802','港北区','450800'),(2137,'450803','港南区','450800'),(2138,'450804','覃塘区','450800'),(2139,'450821','平南县','450800'),(2140,'450881','桂平市','450800'),(2142,'450902','玉州区','450900'),(2143,'450921','容　县','450900'),(2144,'450922','陆川县','450900'),(2145,'450923','博白县','450900'),(2146,'450924','兴业县','450900'),(2147,'450981','北流市','450900'),(2149,'451002','右江区','451000'),(2150,'451021','田阳县','451000'),(2151,'451022','田东县','451000'),(2152,'451023','平果县','451000'),(2153,'451024','德保县','451000'),(2154,'451025','靖西县','451000'),(2155,'451026','那坡县','451000'),(2156,'451027','凌云县','451000'),(2157,'451028','乐业县','451000'),(2158,'451029','田林县','451000'),(2159,'451030','西林县','451000'),(2160,'451031','隆林各族自治县','451000'),(2162,'451102','八步区','451100'),(2163,'451121','昭平县','451100'),(2164,'451122','钟山县','451100'),(2165,'451123','富川瑶族自治县','451100'),(2167,'451202','金城江区','451200'),(2168,'451221','南丹县','451200'),(2169,'451222','天峨县','451200'),(2170,'451223','凤山县','451200'),(2171,'451224','东兰县','451200'),(2172,'451225','罗城仫佬族自治县','451200'),(2173,'451226','环江毛南族自治县','451200'),(2174,'451227','巴马瑶族自治县','451200'),(2175,'451228','都安瑶族自治县','451200'),(2176,'451229','大化瑶族自治县','451200'),(2177,'451281','宜州市','451200'),(2179,'451302','兴宾区','451300'),(2180,'451321','忻城县','451300'),(2181,'451322','象州县','451300'),(2182,'451323','武宣县','451300'),(2183,'451324','金秀瑶族自治县','451300'),(2184,'451381','合山市','451300'),(2186,'451402','江洲区','451400'),(2187,'451421','扶绥县','451400'),(2188,'451422','宁明县','451400'),(2189,'451423','龙州县','451400'),(2190,'451424','大新县','451400'),(2191,'451425','天等县','451400'),(2192,'451481','凭祥市','451400'),(2194,'460105','秀英区','460100'),(2195,'460106','龙华区','460100'),(2196,'460107','琼山区','460100'),(2197,'460108','美兰区','460100'),(2199,'469001','五指山市','469000'),(2200,'469002','琼海市','469000'),(2201,'469003','儋州市','469000'),(2202,'469005','文昌市','469000'),(2203,'469006','万宁市','469000'),(2204,'469007','东方市','469000'),(2205,'469025','定安县','469000'),(2206,'469026','屯昌县','469000'),(2207,'469027','澄迈县','469000'),(2208,'469028','临高县','469000'),(2209,'469030','白沙黎族自治县','469000'),(2210,'469031','昌江黎族自治县','469000'),(2211,'469033','乐东黎族自治县','469000'),(2212,'469034','陵水黎族自治县','469000'),(2213,'469035','保亭黎族苗族自治县','469000'),(2214,'469036','琼中黎族苗族自治县','469000'),(2215,'469037','西沙群岛','469000'),(2216,'469038','南沙群岛','469000'),(2217,'469039','中沙群岛的岛礁及其海域','469000'),(2218,'500101','万州区','500100'),(2219,'500102','涪陵区','500100'),(2220,'500103','渝中区','500100'),(2221,'500104','大渡口区','500100'),(2222,'500105','江北区','500100'),(2223,'500106','沙坪坝区','500100'),(2224,'500107','九龙坡区','500100'),(2225,'500108','南岸区','500100'),(2226,'500109','北碚区','500100'),(2227,'500110','万盛区','500100'),(2228,'500111','双桥区','500100'),(2229,'500112','渝北区','500100'),(2230,'500113','巴南区','500100'),(2231,'500114','黔江区','500100'),(2232,'500115','长寿区','500100'),(2233,'500222','綦江县','500200'),(2234,'500223','潼南县','500200'),(2235,'500224','铜梁县','500200'),(2236,'500225','大足县','500200'),(2237,'500226','荣昌县','500200'),(2238,'500227','璧山县','500200'),(2239,'500228','梁平县','500200'),(2240,'500229','城口县','500200'),(2241,'500230','丰都县','500200'),(2242,'500231','垫江县','500200'),(2243,'500232','武隆县','500200'),(2244,'500233','忠　县','500200'),(2245,'500234','开　县','500200'),(2246,'500235','云阳县','500200'),(2247,'500236','奉节县','500200'),(2248,'500237','巫山县','500200'),(2249,'500238','巫溪县','500200'),(2250,'500240','石柱土家族自治县','500200'),(2251,'500241','秀山土家族苗族自治县','500200'),(2252,'500242','酉阳土家族苗族自治县','500200'),(2253,'500243','彭水苗族土家族自治县','500200'),(2254,'500381','江津市','500300'),(2255,'500382','合川市','500300'),(2256,'500383','永川市','500300'),(2257,'500384','南川市','500300'),(2259,'510104','锦江区','510100'),(2260,'510105','青羊区','510100'),(2261,'510106','金牛区','510100'),(2262,'510107','武侯区','510100'),(2263,'510108','成华区','510100'),(2264,'510112','龙泉驿区','510100'),(2265,'510113','青白江区','510100'),(2266,'510114','新都区','510100'),(2267,'510115','温江区','510100'),(2268,'510121','金堂县','510100'),(2269,'510122','双流县','510100'),(2270,'510124','郫　县','510100'),(2271,'510129','大邑县','510100'),(2272,'510131','蒲江县','510100'),(2273,'510132','新津县','510100'),(2274,'510181','都江堰市','510100'),(2275,'510182','彭州市','510100'),(2276,'510183','邛崃市','510100'),(2277,'510184','崇州市','510100'),(2279,'510302','自流井区','510300'),(2280,'510303','贡井区','510300'),(2281,'510304','大安区','510300'),(2282,'510311','沿滩区','510300'),(2283,'510321','荣　县','510300'),(2284,'510322','富顺县','510300'),(2286,'510402','东　区','510400'),(2287,'510403','西　区','510400'),(2288,'510411','仁和区','510400'),(2289,'510421','米易县','510400'),(2290,'510422','盐边县','510400'),(2292,'510502','江阳区','510500'),(2293,'510503','纳溪区','510500'),(2294,'510504','龙马潭区','510500'),(2295,'510521','泸　县','510500'),(2296,'510522','合江县','510500'),(2297,'510524','叙永县','510500'),(2298,'510525','古蔺县','510500'),(2300,'510603','旌阳区','510600'),(2301,'510623','中江县','510600'),(2302,'510626','罗江县','510600'),(2303,'510681','广汉市','510600'),(2304,'510682','什邡市','510600'),(2305,'510683','绵竹市','510600'),(2307,'510703','涪城区','510700'),(2308,'510704','游仙区','510700'),(2309,'510722','三台县','510700'),(2310,'510723','盐亭县','510700'),(2311,'510724','安　县','510700'),(2312,'510725','梓潼县','510700'),(2313,'510726','北川羌族自治县','510700'),(2314,'510727','平武县','510700'),(2315,'510781','江油市','510700'),(2317,'510802','市中区','510800'),(2318,'510811','元坝区','510800'),(2319,'510812','朝天区','510800'),(2320,'510821','旺苍县','510800'),(2321,'510822','青川县','510800'),(2322,'510823','剑阁县','510800'),(2323,'510824','苍溪县','510800'),(2325,'510903','船山区','510900'),(2326,'510904','安居区','510900'),(2327,'510921','蓬溪县','510900'),(2328,'510922','射洪县','510900'),(2329,'510923','大英县','510900'),(2331,'511002','市中区','511000'),(2332,'511011','东兴区','511000'),(2333,'511024','威远县','511000'),(2334,'511025','资中县','511000'),(2335,'511028','隆昌县','511000'),(2337,'511102','市中区','511100'),(2338,'511111','沙湾区','511100'),(2339,'511112','五通桥区','511100'),(2340,'511113','金口河区','511100'),(2341,'511123','犍为县','511100'),(2342,'511124','井研县','511100'),(2343,'511126','夹江县','511100'),(2344,'511129','沐川县','511100'),(2345,'511132','峨边彝族自治县','511100'),(2346,'511133','马边彝族自治县','511100'),(2347,'511181','峨眉山市','511100'),(2349,'511302','顺庆区','511300'),(2350,'511303','高坪区','511300'),(2351,'511304','嘉陵区','511300'),(2352,'511321','南部县','511300'),(2353,'511322','营山县','511300'),(2354,'511323','蓬安县','511300'),(2355,'511324','仪陇县','511300'),(2356,'511325','西充县','511300'),(2357,'511381','阆中市','511300'),(2359,'511402','东坡区','511400'),(2360,'511421','仁寿县','511400'),(2361,'511422','彭山县','511400'),(2362,'511423','洪雅县','511400'),(2363,'511424','丹棱县','511400'),(2364,'511425','青神县','511400'),(2366,'511502','翠屏区','511500'),(2367,'511521','宜宾县','511500'),(2368,'511522','南溪县','511500'),(2369,'511523','江安县','511500'),(2370,'511524','长宁县','511500'),(2371,'511525','高　县','511500'),(2372,'511526','珙　县','511500'),(2373,'511527','筠连县','511500'),(2374,'511528','兴文县','511500'),(2375,'511529','屏山县','511500'),(2377,'511602','广安区','511600'),(2378,'511621','岳池县','511600'),(2379,'511622','武胜县','511600'),(2380,'511623','邻水县','511600'),(2381,'511681','华莹市','511600'),(2383,'511702','通川区','511700'),(2384,'511721','达　县','511700'),(2385,'511722','宣汉县','511700'),(2386,'511723','开江县','511700'),(2387,'511724','大竹县','511700'),(2388,'511725','渠　县','511700'),(2389,'511781','万源市','511700'),(2391,'511802','雨城区','511800'),(2392,'511821','名山县','511800'),(2393,'511822','荥经县','511800'),(2394,'511823','汉源县','511800'),(2395,'511824','石棉县','511800'),(2396,'511825','天全县','511800'),(2397,'511826','芦山县','511800'),(2398,'511827','宝兴县','511800'),(2400,'511902','巴州区','511900'),(2401,'511921','通江县','511900'),(2402,'511922','南江县','511900'),(2403,'511923','平昌县','511900'),(2405,'512002','雁江区','512000'),(2406,'512021','安岳县','512000'),(2407,'512022','乐至县','512000'),(2408,'512081','简阳市','512000'),(2409,'513221','汶川县','513200'),(2410,'513222','理　县','513200'),(2411,'513223','茂　县','513200'),(2412,'513224','松潘县','513200'),(2413,'513225','九寨沟县','513200'),(2414,'513226','金川县','513200'),(2415,'513227','小金县','513200'),(2416,'513228','黑水县','513200'),(2417,'513229','马尔康县','513200'),(2418,'513230','壤塘县','513200'),(2419,'513231','阿坝县','513200'),(2420,'513232','若尔盖县','513200'),(2421,'513233','红原县','513200'),(2422,'513321','康定县','513300'),(2423,'513322','泸定县','513300'),(2424,'513323','丹巴县','513300'),(2425,'513324','九龙县','513300'),(2426,'513325','雅江县','513300'),(2427,'513326','道孚县','513300'),(2428,'513327','炉霍县','513300'),(2429,'513328','甘孜县','513300'),(2430,'513329','新龙县','513300'),(2431,'513330','德格县','513300'),(2432,'513331','白玉县','513300'),(2433,'513332','石渠县','513300'),(2434,'513333','色达县','513300'),(2435,'513334','理塘县','513300'),(2436,'513335','巴塘县','513300'),(2437,'513336','乡城县','513300'),(2438,'513337','稻城县','513300'),(2439,'513338','得荣县','513300'),(2440,'513401','西昌市','513400'),(2441,'513422','木里藏族自治县','513400'),(2442,'513423','盐源县','513400'),(2443,'513424','德昌县','513400'),(2444,'513425','会理县','513400'),(2445,'513426','会东县','513400'),(2446,'513427','宁南县','513400'),(2447,'513428','普格县','513400'),(2448,'513429','布拖县','513400'),(2449,'513430','金阳县','513400'),(2450,'513431','昭觉县','513400'),(2451,'513432','喜德县','513400'),(2452,'513433','冕宁县','513400'),(2453,'513434','越西县','513400'),(2454,'513435','甘洛县','513400'),(2455,'513436','美姑县','513400'),(2456,'513437','雷波县','513400'),(2458,'520102','南明区','520100'),(2459,'520103','云岩区','520100'),(2460,'520111','花溪区','520100'),(2461,'520112','乌当区','520100'),(2462,'520113','白云区','520100'),(2463,'520114','小河区','520100'),(2464,'520121','开阳县','520100'),(2465,'520122','息烽县','520100'),(2466,'520123','修文县','520100'),(2467,'520181','清镇市','520100'),(2468,'520201','钟山区','520200'),(2469,'520203','六枝特区','520200'),(2470,'520221','水城县','520200'),(2471,'520222','盘　县','520200'),(2473,'520302','红花岗区','520300'),(2474,'520303','汇川区','520300'),(2475,'520321','遵义县','520300'),(2476,'520322','桐梓县','520300'),(2477,'520323','绥阳县','520300'),(2478,'520324','正安县','520300'),(2479,'520325','道真仡佬族苗族自治县','520300'),(2480,'520326','务川仡佬族苗族自治县','520300'),(2481,'520327','凤冈县','520300'),(2482,'520328','湄潭县','520300'),(2483,'520329','余庆县','520300'),(2484,'520330','习水县','520300'),(2485,'520381','赤水市','520300'),(2486,'520382','仁怀市','520300'),(2488,'520402','西秀区','520400'),(2489,'520421','平坝县','520400'),(2490,'520422','普定县','520400'),(2491,'520423','镇宁布依族苗族自治县','520400'),(2492,'520424','关岭布依族苗族自治县','520400'),(2493,'520425','紫云苗族布依族自治县','520400'),(2494,'522201','铜仁市','522200'),(2495,'522222','江口县','522200'),(2496,'522223','玉屏侗族自治县','522200'),(2497,'522224','石阡县','522200'),(2498,'522225','思南县','522200'),(2499,'522226','印江土家族苗族自治县','522200'),(2500,'522227','德江县','522200'),(2501,'522228','沿河土家族自治县','522200'),(2502,'522229','松桃苗族自治县','522200'),(2503,'522230','万山特区','522200'),(2504,'522301','兴义市','522300'),(2505,'522322','兴仁县','522300'),(2506,'522323','普安县','522300'),(2507,'522324','晴隆县','522300'),(2508,'522325','贞丰县','522300'),(2509,'522326','望谟县','522300'),(2510,'522327','册亨县','522300'),(2511,'522328','安龙县','522300'),(2512,'522401','毕节市','522400'),(2513,'522422','大方县','522400'),(2514,'522423','黔西县','522400'),(2515,'522424','金沙县','522400'),(2516,'522425','织金县','522400'),(2517,'522426','纳雍县','522400'),(2518,'522427','威宁彝族回族苗族自治县','522400'),(2519,'522428','赫章县','522400'),(2520,'522601','凯里市','522600'),(2521,'522622','黄平县','522600'),(2522,'522623','施秉县','522600'),(2523,'522624','三穗县','522600'),(2524,'522625','镇远县','522600'),(2525,'522626','岑巩县','522600'),(2526,'522627','天柱县','522600'),(2527,'522628','锦屏县','522600'),(2528,'522629','剑河县','522600'),(2529,'522630','台江县','522600'),(2530,'522631','黎平县','522600'),(2531,'522632','榕江县','522600'),(2532,'522633','从江县','522600'),(2533,'522634','雷山县','522600'),(2534,'522635','麻江县','522600'),(2535,'522636','丹寨县','522600'),(2536,'522701','都匀市','522700'),(2537,'522702','福泉市','522700'),(2538,'522722','荔波县','522700'),(2539,'522723','贵定县','522700'),(2540,'522725','瓮安县','522700'),(2541,'522726','独山县','522700'),(2542,'522727','平塘县','522700'),(2543,'522728','罗甸县','522700'),(2544,'522729','长顺县','522700'),(2545,'522730','龙里县','522700'),(2546,'522731','惠水县','522700'),(2547,'522732','三都水族自治县','522700'),(2549,'530102','五华区','530100'),(2550,'530103','盘龙区','530100'),(2551,'530111','官渡区','530100'),(2552,'530112','西山区','530100'),(2553,'530113','东川区','530100'),(2554,'530121','呈贡县','530100'),(2555,'530122','晋宁县','530100'),(2556,'530124','富民县','530100'),(2557,'530125','宜良县','530100'),(2558,'530126','石林彝族自治县','530100'),(2559,'530127','嵩明县','530100'),(2560,'530128','禄劝彝族苗族自治县','530100'),(2561,'530129','寻甸回族彝族自治县','530100'),(2562,'530181','安宁市','530100'),(2564,'530302','麒麟区','530300'),(2565,'530321','马龙县','530300'),(2566,'530322','陆良县','530300'),(2567,'530323','师宗县','530300'),(2568,'530324','罗平县','530300'),(2569,'530325','富源县','530300'),(2570,'530326','会泽县','530300'),(2571,'530328','沾益县','530300'),(2572,'530381','宣威市','530300'),(2574,'530402','红塔区','530400'),(2575,'530421','江川县','530400'),(2576,'530422','澄江县','530400'),(2577,'530423','通海县','530400'),(2578,'530424','华宁县','530400'),(2579,'530425','易门县','530400'),(2580,'530426','峨山彝族自治县','530400'),(2581,'530427','新平彝族傣族自治县','530400'),(2582,'530428','元江哈尼族彝族傣族自治县','530400'),(2584,'530502','隆阳区','530500'),(2585,'530521','施甸县','530500'),(2586,'530522','腾冲县','530500'),(2587,'530523','龙陵县','530500'),(2588,'530524','昌宁县','530500'),(2590,'530602','昭阳区','530600'),(2591,'530621','鲁甸县','530600'),(2592,'530622','巧家县','530600'),(2593,'530623','盐津县','530600'),(2594,'530624','大关县','530600'),(2595,'530625','永善县','530600'),(2596,'530626','绥江县','530600'),(2597,'530627','镇雄县','530600'),(2598,'530628','彝良县','530600'),(2599,'530629','威信县','530600'),(2600,'530630','水富县','530600'),(2602,'530702','古城区','530700'),(2603,'530721','玉龙纳西族自治县','530700'),(2604,'530722','永胜县','530700'),(2605,'530723','华坪县','530700'),(2606,'530724','宁蒗彝族自治县','530700'),(2608,'530802','翠云区','530800'),(2609,'530821','普洱哈尼族彝族自治县','530800'),(2610,'530822','墨江哈尼族自治县','530800'),(2611,'530823','景东彝族自治县','530800'),(2612,'530824','景谷傣族彝族自治县','530800'),(2613,'530825','镇沅彝族哈尼族拉祜族自治县','530800'),(2614,'530826','江城哈尼族彝族自治县','530800'),(2615,'530827','孟连傣族拉祜族佤族自治县','530800'),(2616,'530828','澜沧拉祜族自治县','530800'),(2617,'530829','西盟佤族自治县','530800'),(2619,'530902','临翔区','530900'),(2620,'530921','凤庆县','530900'),(2621,'530922','云　县','530900'),(2622,'530923','永德县','530900'),(2623,'530924','镇康县','530900'),(2624,'530925','双江拉祜族佤族布朗族傣族自治县','530900'),(2625,'530926','耿马傣族佤族自治县','530900'),(2626,'530927','沧源佤族自治县','530900'),(2627,'532301','楚雄市','532300'),(2628,'532322','双柏县','532300'),(2629,'532323','牟定县','532300'),(2630,'532324','南华县','532300'),(2631,'532325','姚安县','532300'),(2632,'532326','大姚县','532300'),(2633,'532327','永仁县','532300'),(2634,'532328','元谋县','532300'),(2635,'532329','武定县','532300'),(2636,'532331','禄丰县','532300'),(2637,'532501','个旧市','532500'),(2638,'532502','开远市','532500'),(2639,'532522','蒙自县','532500'),(2640,'532523','屏边苗族自治县','532500'),(2641,'532524','建水县','532500'),(2642,'532525','石屏县','532500'),(2643,'532526','弥勒县','532500'),(2644,'532527','泸西县','532500'),(2645,'532528','元阳县','532500'),(2646,'532529','红河县','532500'),(2647,'532530','金平苗族瑶族傣族自治县','532500'),(2648,'532531','绿春县','532500'),(2649,'532532','河口瑶族自治县','532500'),(2650,'532621','文山县','532600'),(2651,'532622','砚山县','532600'),(2652,'532623','西畴县','532600'),(2653,'532624','麻栗坡县','532600'),(2654,'532625','马关县','532600'),(2655,'532626','丘北县','532600'),(2656,'532627','广南县','532600'),(2657,'532628','富宁县','532600'),(2658,'532801','景洪市','532800'),(2659,'532822','勐海县','532800'),(2660,'532823','勐腊县','532800'),(2661,'532901','大理市','532900'),(2662,'532922','漾濞彝族自治县','532900'),(2663,'532923','祥云县','532900'),(2664,'532924','宾川县','532900'),(2665,'532925','弥渡县','532900'),(2666,'532926','南涧彝族自治县','532900'),(2667,'532927','巍山彝族回族自治县','532900'),(2668,'532928','永平县','532900'),(2669,'532929','云龙县','532900'),(2670,'532930','洱源县','532900'),(2671,'532931','剑川县','532900'),(2672,'532932','鹤庆县','532900'),(2673,'533102','瑞丽市','533100'),(2674,'533103','潞西市','533100'),(2675,'533122','梁河县','533100'),(2676,'533123','盈江县','533100'),(2677,'533124','陇川县','533100'),(2678,'533321','泸水县','533300'),(2679,'533323','福贡县','533300'),(2680,'533324','贡山独龙族怒族自治县','533300'),(2681,'533325','兰坪白族普米族自治县','533300'),(2682,'533421','香格里拉县','533400'),(2683,'533422','德钦县','533400'),(2684,'533423','维西傈僳族自治县','533400'),(2686,'540102','城关区','540100'),(2687,'540121','林周县','540100'),(2688,'540122','当雄县','540100'),(2689,'540123','尼木县','540100'),(2690,'540124','曲水县','540100'),(2691,'540125','堆龙德庆县','540100'),(2692,'540126','达孜县','540100'),(2693,'540127','墨竹工卡县','540100'),(2694,'542121','昌都县','542100'),(2695,'542122','江达县','542100'),(2696,'542123','贡觉县','542100'),(2697,'542124','类乌齐县','542100'),(2698,'542125','丁青县','542100'),(2699,'542126','察雅县','542100'),(2700,'542127','八宿县','542100'),(2701,'542128','左贡县','542100'),(2702,'542129','芒康县','542100'),(2703,'542132','洛隆县','542100'),(2704,'542133','边坝县','542100'),(2705,'542221','乃东县','542200'),(2706,'542222','扎囊县','542200'),(2707,'542223','贡嘎县','542200'),(2708,'542224','桑日县','542200'),(2709,'542225','琼结县','542200'),(2710,'542226','曲松县','542200'),(2711,'542227','措美县','542200'),(2712,'542228','洛扎县','542200'),(2713,'542229','加查县','542200'),(2714,'542231','隆子县','542200'),(2715,'542232','错那县','542200'),(2716,'542233','浪卡子县','542200'),(2717,'542301','日喀则市','542300'),(2718,'542322','南木林县','542300'),(2719,'542323','江孜县','542300'),(2720,'542324','定日县','542300'),(2721,'542325','萨迦县','542300'),(2722,'542326','拉孜县','542300'),(2723,'542327','昂仁县','542300'),(2724,'542328','谢通门县','542300'),(2725,'542329','白朗县','542300'),(2726,'542330','仁布县','542300'),(2727,'542331','康马县','542300'),(2728,'542332','定结县','542300'),(2729,'542333','仲巴县','542300'),(2730,'542334','亚东县','542300'),(2731,'542335','吉隆县','542300'),(2732,'542336','聂拉木县','542300'),(2733,'542337','萨嘎县','542300'),(2734,'542338','岗巴县','542300'),(2735,'542421','那曲县','542400'),(2736,'542422','嘉黎县','542400'),(2737,'542423','比如县','542400'),(2738,'542424','聂荣县','542400'),(2739,'542425','安多县','542400'),(2740,'542426','申扎县','542400'),(2741,'542427','索　县','542400'),(2742,'542428','班戈县','542400'),(2743,'542429','巴青县','542400'),(2744,'542430','尼玛县','542400'),(2745,'542521','普兰县','542500'),(2746,'542522','札达县','542500'),(2747,'542523','噶尔县','542500'),(2748,'542524','日土县','542500'),(2749,'542525','革吉县','542500'),(2750,'542526','改则县','542500'),(2751,'542527','措勤县','542500'),(2752,'542621','林芝县','542600'),(2753,'542622','工布江达县','542600'),(2754,'542623','米林县','542600'),(2755,'542624','墨脱县','542600'),(2756,'542625','波密县','542600'),(2757,'542626','察隅县','542600'),(2758,'542627','朗　县','542600'),(2760,'610102','新城区','610100'),(2761,'610103','碑林区','610100'),(2762,'610104','莲湖区','610100'),(2763,'610111','灞桥区','610100'),(2764,'610112','未央区','610100'),(2765,'610113','雁塔区','610100'),(2766,'610114','阎良区','610100'),(2767,'610115','临潼区','610100'),(2768,'610116','长安区','610100'),(2769,'610122','蓝田县','610100'),(2770,'610124','周至县','610100'),(2771,'610125','户　县','610100'),(2772,'610126','高陵县','610100'),(2774,'610202','王益区','610200'),(2775,'610203','印台区','610200'),(2776,'610204','耀州区','610200'),(2777,'610222','宜君县','610200'),(2779,'610302','渭滨区','610300'),(2780,'610303','金台区','610300'),(2781,'610304','陈仓区','610300'),(2782,'610322','凤翔县','610300'),(2783,'610323','岐山县','610300'),(2784,'610324','扶风县','610300'),(2785,'610326','眉　县','610300'),(2786,'610327','陇　县','610300'),(2787,'610328','千阳县','610300'),(2788,'610329','麟游县','610300'),(2789,'610330','凤　县','610300'),(2790,'610331','太白县','610300'),(2792,'610402','秦都区','610400'),(2793,'610403','杨凌区','610400'),(2794,'610404','渭城区','610400'),(2795,'610422','三原县','610400'),(2796,'610423','泾阳县','610400'),(2797,'610424','乾　县','610400'),(2798,'610425','礼泉县','610400'),(2799,'610426','永寿县','610400'),(2800,'610427','彬　县','610400'),(2801,'610428','长武县','610400'),(2802,'610429','旬邑县','610400'),(2803,'610430','淳化县','610400'),(2804,'610431','武功县','610400'),(2805,'610481','兴平市','610400'),(2807,'610502','临渭区','610500'),(2808,'610521','华　县','610500'),(2809,'610522','潼关县','610500'),(2810,'610523','大荔县','610500'),(2811,'610524','合阳县','610500'),(2812,'610525','澄城县','610500'),(2813,'610526','蒲城县','610500'),(2814,'610527','白水县','610500'),(2815,'610528','富平县','610500'),(2816,'610581','韩城市','610500'),(2817,'610582','华阴市','610500'),(2819,'610602','宝塔区','610600'),(2820,'610621','延长县','610600'),(2821,'610622','延川县','610600'),(2822,'610623','子长县','610600'),(2823,'610624','安塞县','610600'),(2824,'610625','志丹县','610600'),(2825,'610626','吴旗县','610600'),(2826,'610627','甘泉县','610600'),(2827,'610628','富　县','610600'),(2828,'610629','洛川县','610600'),(2829,'610630','宜川县','610600'),(2830,'610631','黄龙县','610600'),(2831,'610632','黄陵县','610600'),(2833,'610702','汉台区','610700'),(2834,'610721','南郑县','610700'),(2835,'610722','城固县','610700'),(2836,'610723','洋　县','610700'),(2837,'610724','西乡县','610700'),(2838,'610725','勉　县','610700'),(2839,'610726','宁强县','610700'),(2840,'610727','略阳县','610700'),(2841,'610728','镇巴县','610700'),(2842,'610729','留坝县','610700'),(2843,'610730','佛坪县','610700'),(2845,'610802','榆阳区','610800'),(2846,'610821','神木县','610800'),(2847,'610822','府谷县','610800'),(2848,'610823','横山县','610800'),(2849,'610824','靖边县','610800'),(2850,'610825','定边县','610800'),(2851,'610826','绥德县','610800'),(2852,'610827','米脂县','610800'),(2853,'610828','佳　县','610800'),(2854,'610829','吴堡县','610800'),(2855,'610830','清涧县','610800'),(2856,'610831','子洲县','610800'),(2858,'610902','汉滨区','610900'),(2859,'610921','汉阴县','610900'),(2860,'610922','石泉县','610900'),(2861,'610923','宁陕县','610900'),(2862,'610924','紫阳县','610900'),(2863,'610925','岚皋县','610900'),(2864,'610926','平利县','610900'),(2865,'610927','镇坪县','610900'),(2866,'610928','旬阳县','610900'),(2867,'610929','白河县','610900'),(2869,'611002','商州区','611000'),(2870,'611021','洛南县','611000'),(2871,'611022','丹凤县','611000'),(2872,'611023','商南县','611000'),(2873,'611024','山阳县','611000'),(2874,'611025','镇安县','611000'),(2875,'611026','柞水县','611000'),(2877,'620102','城关区','620100'),(2878,'620103','七里河区','620100'),(2879,'620104','西固区','620100'),(2880,'620105','安宁区','620100'),(2881,'620111','红古区','620100'),(2882,'620121','永登县','620100'),(2883,'620122','皋兰县','620100'),(2884,'620123','榆中县','620100'),(2887,'620302','金川区','620300'),(2888,'620321','永昌县','620300'),(2890,'620402','白银区','620400'),(2891,'620403','平川区','620400'),(2892,'620421','靖远县','620400'),(2893,'620422','会宁县','620400'),(2894,'620423','景泰县','620400'),(2896,'620502','秦城区','620500'),(2897,'620503','北道区','620500'),(2898,'620521','清水县','620500'),(2899,'620522','秦安县','620500'),(2900,'620523','甘谷县','620500'),(2901,'620524','武山县','620500'),(2902,'620525','张家川回族自治县','620500'),(2904,'620602','凉州区','620600'),(2905,'620621','民勤县','620600'),(2906,'620622','古浪县','620600'),(2907,'620623','天祝藏族自治县','620600'),(2909,'620702','甘州区','620700'),(2910,'620721','肃南裕固族自治县','620700'),(2911,'620722','民乐县','620700'),(2912,'620723','临泽县','620700'),(2913,'620724','高台县','620700'),(2914,'620725','山丹县','620700'),(2916,'620802','崆峒区','620800'),(2917,'620821','泾川县','620800'),(2918,'620822','灵台县','620800'),(2919,'620823','崇信县','620800'),(2920,'620824','华亭县','620800'),(2921,'620825','庄浪县','620800'),(2922,'620826','静宁县','620800'),(2924,'620902','肃州区','620900'),(2925,'620921','金塔县','620900'),(2926,'620922','安西县','620900'),(2927,'620923','肃北蒙古族自治县','620900'),(2928,'620924','阿克塞哈萨克族自治县','620900'),(2929,'620981','玉门市','620900'),(2930,'620982','敦煌市','620900'),(2932,'621002','西峰区','621000'),(2933,'621021','庆城县','621000'),(2934,'621022','环　县','621000'),(2935,'621023','华池县','621000'),(2936,'621024','合水县','621000'),(2937,'621025','正宁县','621000'),(2938,'621026','宁　县','621000'),(2939,'621027','镇原县','621000'),(2941,'621102','安定区','621100'),(2942,'621121','通渭县','621100'),(2943,'621122','陇西县','621100'),(2944,'621123','渭源县','621100'),(2945,'621124','临洮县','621100'),(2946,'621125','漳　县','621100'),(2947,'621126','岷　县','621100'),(2949,'621202','武都区','621200'),(2950,'621221','成　县','621200'),(2951,'621222','文　县','621200'),(2952,'621223','宕昌县','621200'),(2953,'621224','康　县','621200'),(2954,'621225','西和县','621200'),(2955,'621226','礼　县','621200'),(2956,'621227','徽　县','621200'),(2957,'621228','两当县','621200'),(2958,'622901','临夏市','622900'),(2959,'622921','临夏县','622900'),(2960,'622922','康乐县','622900'),(2961,'622923','永靖县','622900'),(2962,'622924','广河县','622900'),(2963,'622925','和政县','622900'),(2964,'622926','东乡族自治县','622900'),(2965,'622927','积石山保安族东乡族撒拉族自治县','622900'),(2966,'623001','合作市','623000'),(2967,'623021','临潭县','623000'),(2968,'623022','卓尼县','623000'),(2969,'623023','舟曲县','623000'),(2970,'623024','迭部县','623000'),(2971,'623025','玛曲县','623000'),(2972,'623026','碌曲县','623000'),(2973,'623027','夏河县','623000'),(2975,'630102','城东区','630100'),(2976,'630103','城中区','630100'),(2977,'630104','城西区','630100'),(2978,'630105','城北区','630100'),(2979,'630121','大通回族土族自治县','630100'),(2980,'630122','湟中县','630100'),(2981,'630123','湟源县','630100'),(2982,'632121','平安县','632100'),(2983,'632122','民和回族土族自治县','632100'),(2984,'632123','乐都县','632100'),(2985,'632126','互助土族自治县','632100'),(2986,'632127','化隆回族自治县','632100'),(2987,'632128','循化撒拉族自治县','632100'),(2988,'632221','门源回族自治县','632200'),(2989,'632222','祁连县','632200'),(2990,'632223','海晏县','632200'),(2991,'632224','刚察县','632200'),(2992,'632321','同仁县','632300'),(2993,'632322','尖扎县','632300'),(2994,'632323','泽库县','632300'),(2995,'632324','河南蒙古族自治县','632300'),(2996,'632521','共和县','632500'),(2997,'632522','同德县','632500'),(2998,'632523','贵德县','632500'),(2999,'632524','兴海县','632500'),(3000,'632525','贵南县','632500'),(3001,'632621','玛沁县','632600'),(3002,'632622','班玛县','632600'),(3003,'632623','甘德县','632600'),(3004,'632624','达日县','632600'),(3005,'632625','久治县','632600'),(3006,'632626','玛多县','632600'),(3007,'632721','玉树县','632700'),(3008,'632722','杂多县','632700'),(3009,'632723','称多县','632700'),(3010,'632724','治多县','632700'),(3011,'632725','囊谦县','632700'),(3012,'632726','曲麻莱县','632700'),(3013,'632801','格尔木市','632800'),(3014,'632802','德令哈市','632800'),(3015,'632821','乌兰县','632800'),(3016,'632822','都兰县','632800'),(3017,'632823','天峻县','632800'),(3019,'640104','兴庆区','640100'),(3020,'640105','西夏区','640100'),(3021,'640106','金凤区','640100'),(3022,'640121','永宁县','640100'),(3023,'640122','贺兰县','640100'),(3024,'640181','灵武市','640100'),(3026,'640202','大武口区','640200'),(3027,'640205','惠农区','640200'),(3028,'640221','平罗县','640200'),(3030,'640302','利通区','640300'),(3031,'640323','盐池县','640300'),(3032,'640324','同心县','640300'),(3033,'640381','青铜峡市','640300'),(3035,'640402','原州区','640400'),(3036,'640422','西吉县','640400'),(3037,'640423','隆德县','640400'),(3038,'640424','泾源县','640400'),(3039,'640425','彭阳县','640400'),(3041,'640502','沙坡头区','640500'),(3042,'640521','中宁县','640500'),(3043,'640522','海原县','640500'),(3045,'650102','天山区','650100'),(3046,'650103','沙依巴克区','650100'),(3047,'650104','新市区','650100'),(3048,'650105','水磨沟区','650100'),(3049,'650106','头屯河区','650100'),(3050,'650107','达坂城区','650100'),(3051,'650108','东山区','650100'),(3052,'650121','乌鲁木齐县','650100'),(3054,'650202','独山子区','650200'),(3055,'650203','克拉玛依区','650200'),(3056,'650204','白碱滩区','650200'),(3057,'650205','乌尔禾区','650200'),(3058,'652101','吐鲁番市','652100'),(3059,'652122','鄯善县','652100'),(3060,'652123','托克逊县','652100'),(3061,'652201','哈密市','652200'),(3062,'652222','巴里坤哈萨克自治县','652200'),(3063,'652223','伊吾县','652200'),(3064,'652301','昌吉市','652300'),(3065,'652302','阜康市','652300'),(3066,'652303','米泉市','652300'),(3067,'652323','呼图壁县','652300'),(3068,'652324','玛纳斯县','652300'),(3069,'652325','奇台县','652300'),(3070,'652327','吉木萨尔县','652300'),(3071,'652328','木垒哈萨克自治县','652300'),(3072,'652701','博乐市','652700'),(3073,'652722','精河县','652700'),(3074,'652723','温泉县','652700'),(3075,'652801','库尔勒市','652800'),(3076,'652822','轮台县','652800'),(3077,'652823','尉犁县','652800'),(3078,'652824','若羌县','652800'),(3079,'652825','且末县','652800'),(3080,'652826','焉耆回族自治县','652800'),(3081,'652827','和静县','652800'),(3082,'652828','和硕县','652800'),(3083,'652829','博湖县','652800'),(3084,'652901','阿克苏市','652900'),(3085,'652922','温宿县','652900'),(3086,'652923','库车县','652900'),(3087,'652924','沙雅县','652900'),(3088,'652925','新和县','652900'),(3089,'652926','拜城县','652900'),(3090,'652927','乌什县','652900'),(3091,'652928','阿瓦提县','652900'),(3092,'652929','柯坪县','652900'),(3093,'653001','阿图什市','653000'),(3094,'653022','阿克陶县','653000'),(3095,'653023','阿合奇县','653000'),(3096,'653024','乌恰县','653000'),(3097,'653101','喀什市','653100'),(3098,'653121','疏附县','653100'),(3099,'653122','疏勒县','653100'),(3100,'653123','英吉沙县','653100'),(3101,'653124','泽普县','653100'),(3102,'653125','莎车县','653100'),(3103,'653126','叶城县','653100'),(3104,'653127','麦盖提县','653100'),(3105,'653128','岳普湖县','653100'),(3106,'653129','伽师县','653100'),(3107,'653130','巴楚县','653100'),(3108,'653131','塔什库尔干塔吉克自治县','653100'),(3109,'653201','和田市','653200'),(3110,'653221','和田县','653200'),(3111,'653222','墨玉县','653200'),(3112,'653223','皮山县','653200'),(3113,'653224','洛浦县','653200'),(3114,'653225','策勒县','653200'),(3115,'653226','于田县','653200'),(3116,'653227','民丰县','653200'),(3117,'654002','伊宁市','654000'),(3118,'654003','奎屯市','654000'),(3119,'654021','伊宁县','654000'),(3120,'654022','察布查尔锡伯自治县','654000'),(3121,'654023','霍城县','654000'),(3122,'654024','巩留县','654000'),(3123,'654025','新源县','654000'),(3124,'654026','昭苏县','654000'),(3125,'654027','特克斯县','654000'),(3126,'654028','尼勒克县','654000'),(3127,'654201','塔城市','654200'),(3128,'654202','乌苏市','654200'),(3129,'654221','额敏县','654200'),(3130,'654223','沙湾县','654200'),(3131,'654224','托里县','654200'),(3132,'654225','裕民县','654200'),(3133,'654226','和布克赛尔蒙古自治县','654200'),(3134,'654301','阿勒泰市','654300'),(3135,'654321','布尔津县','654300'),(3136,'654322','富蕴县','654300'),(3137,'654323','福海县','654300'),(3138,'654324','哈巴河县','654300'),(3139,'654325','青河县','654300'),(3140,'654326','吉木乃县','654300'),(3141,'659001','石河子市','659000'),(3142,'659002','阿拉尔市','659000'),(3143,'659003','图木舒克市','659000'),(3144,'659004','五家渠市','659000'),(3145,'441901','莞城区','441900'),(3146,'441902','东城区','441900'),(3147,'441903','南城区','441900'),(3148,'441904','万江区','441900'),(3149,'442001','石歧区','442000'),(3150,'442002','东区','442000'),(3151,'442003','西区','442000'),(3152,'442004','南区','442000'),(3153,'442005','五桂山区','442000'),(3154,'442006','火炬开发区','442000'),(3155,'710101','内湖区','710100'),(3156,'710102','南港区','710100'),(3157,'710103','中正区','710100'),(3158,'710104','万华区','710100'),(3159,'710105','大同区','710100'),(3160,'710106','中山区','710100'),(3161,'710107','松山区','710100'),(3162,'710108','大安区','710100'),(3163,'710109','信义区','710100'),(3164,'710110','文山区','710100'),(3165,'710111','士林区','710100'),(3166,'710112','北投区','710100'),(3167,'710201','板桥区','710200'),(3168,'710202','汐止区','710200'),(3169,'710203','新店区','710200'),(3170,'710204','永和区','710200'),(3171,'710205','中和区','710200'),(3172,'710206','土城区','710200'),(3173,'710301','中区','710300'),(3174,'710302','东区','710300'),(3175,'710401','中西区','710400'),(3176,'710402','东区','710400'),(3177,'710501','旧高雄市区','710500'),(3178,'710502','凤山地区','710500'),(3179,'710503','冈山地区','710500'),(3180,'710504','旗山地区','710500');
/*!40000 ALTER TABLE `sp_hat_area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sp_hat_city`
--

DROP TABLE IF EXISTS `sp_hat_city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sp_hat_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cityID` varchar(6) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `father` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=351 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sp_hat_city`
--

LOCK TABLES `sp_hat_city` WRITE;
/*!40000 ALTER TABLE `sp_hat_city` DISABLE KEYS */;
INSERT INTO `sp_hat_city` VALUES (1,'110100','北京市','110000'),(2,'110200','县','110000'),(3,'120100','天津市','120000'),(4,'120200','县','120000'),(5,'130100','石家庄市','130000'),(6,'130200','唐山市','130000'),(7,'130300','秦皇岛市','130000'),(8,'130400','邯郸市','130000'),(9,'130500','邢台市','130000'),(10,'130600','保定市','130000'),(11,'130700','张家口市','130000'),(12,'130800','承德市','130000'),(13,'130900','沧州市','130000'),(14,'131000','廊坊市','130000'),(15,'131100','衡水市','130000'),(16,'140100','太原市','140000'),(17,'140200','大同市','140000'),(18,'140300','阳泉市','140000'),(19,'140400','长治市','140000'),(20,'140500','晋城市','140000'),(21,'140600','朔州市','140000'),(22,'140700','晋中市','140000'),(23,'140800','运城市','140000'),(24,'140900','忻州市','140000'),(25,'141000','临汾市','140000'),(26,'141100','吕梁市','140000'),(27,'150100','呼和浩特市','150000'),(28,'150200','包头市','150000'),(29,'150300','乌海市','150000'),(30,'150400','赤峰市','150000'),(31,'150500','通辽市','150000'),(32,'150600','鄂尔多斯市','150000'),(33,'150700','呼伦贝尔市','150000'),(34,'150800','巴彦淖尔市','150000'),(35,'150900','乌兰察布市','150000'),(36,'152200','兴安盟','150000'),(37,'152500','锡林郭勒盟','150000'),(38,'152900','阿拉善盟','150000'),(39,'210100','沈阳市','210000'),(40,'210200','大连市','210000'),(41,'210300','鞍山市','210000'),(42,'210400','抚顺市','210000'),(43,'210500','本溪市','210000'),(44,'210600','丹东市','210000'),(45,'210700','锦州市','210000'),(46,'210800','营口市','210000'),(47,'210900','阜新市','210000'),(48,'211000','辽阳市','210000'),(49,'211100','盘锦市','210000'),(50,'211200','铁岭市','210000'),(51,'211300','朝阳市','210000'),(52,'211400','葫芦岛市','210000'),(53,'220100','长春市','220000'),(54,'220200','吉林市','220000'),(55,'220300','四平市','220000'),(56,'220400','辽源市','220000'),(57,'220500','通化市','220000'),(58,'220600','白山市','220000'),(59,'220700','松原市','220000'),(60,'220800','白城市','220000'),(61,'222400','延边朝鲜族自治州','220000'),(62,'230100','哈尔滨市','230000'),(63,'230200','齐齐哈尔市','230000'),(64,'230300','鸡西市','230000'),(65,'230400','鹤岗市','230000'),(66,'230500','双鸭山市','230000'),(67,'230600','大庆市','230000'),(68,'230700','伊春市','230000'),(69,'230800','佳木斯市','230000'),(70,'230900','七台河市','230000'),(71,'231000','牡丹江市','230000'),(72,'231100','黑河市','230000'),(73,'231200','绥化市','230000'),(74,'232700','大兴安岭地区','230000'),(75,'310100','上海市','310000'),(76,'310200','县','310000'),(77,'320100','南京市','320000'),(78,'320200','无锡市','320000'),(79,'320300','徐州市','320000'),(80,'320400','常州市','320000'),(81,'320500','苏州市','320000'),(82,'320600','南通市','320000'),(83,'320700','连云港市','320000'),(84,'320800','淮安市','320000'),(85,'320900','盐城市','320000'),(86,'321000','扬州市','320000'),(87,'321100','镇江市','320000'),(88,'321200','泰州市','320000'),(89,'321300','宿迁市','320000'),(90,'330100','杭州市','330000'),(91,'330200','宁波市','330000'),(92,'330300','温州市','330000'),(93,'330400','嘉兴市','330000'),(94,'330500','湖州市','330000'),(95,'330600','绍兴市','330000'),(96,'330700','金华市','330000'),(97,'330800','衢州市','330000'),(98,'330900','舟山市','330000'),(99,'331000','台州市','330000'),(100,'331100','丽水市','330000'),(101,'340100','合肥市','340000'),(102,'340200','芜湖市','340000'),(103,'340300','蚌埠市','340000'),(104,'340400','淮南市','340000'),(105,'340500','马鞍山市','340000'),(106,'340600','淮北市','340000'),(107,'340700','铜陵市','340000'),(108,'340800','安庆市','340000'),(109,'341000','黄山市','340000'),(110,'341100','滁州市','340000'),(111,'341200','阜阳市','340000'),(112,'341300','宿州市','340000'),(113,'341400','巢湖市','340000'),(114,'341500','六安市','340000'),(115,'341600','亳州市','340000'),(116,'341700','池州市','340000'),(117,'341800','宣城市','340000'),(118,'350100','福州市','350000'),(119,'350200','厦门市','350000'),(120,'350300','莆田市','350000'),(121,'350400','三明市','350000'),(122,'350500','泉州市','350000'),(123,'350600','漳州市','350000'),(124,'350700','南平市','350000'),(125,'350800','龙岩市','350000'),(126,'350900','宁德市','350000'),(127,'360100','南昌市','360000'),(128,'360200','景德镇市','360000'),(129,'360300','萍乡市','360000'),(130,'360400','九江市','360000'),(131,'360500','新余市','360000'),(132,'360600','鹰潭市','360000'),(133,'360700','赣州市','360000'),(134,'360800','吉安市','360000'),(135,'360900','宜春市','360000'),(136,'361000','抚州市','360000'),(137,'361100','上饶市','360000'),(138,'370100','济南市','370000'),(139,'370200','青岛市','370000'),(140,'370300','淄博市','370000'),(141,'370400','枣庄市','370000'),(142,'370500','东营市','370000'),(143,'370600','烟台市','370000'),(144,'370700','潍坊市','370000'),(145,'370800','济宁市','370000'),(146,'370900','泰安市','370000'),(147,'371000','威海市','370000'),(148,'371100','日照市','370000'),(149,'371200','莱芜市','370000'),(150,'371300','临沂市','370000'),(151,'371400','德州市','370000'),(152,'371500','聊城市','370000'),(153,'371600','滨州市','370000'),(154,'371700','荷泽市','370000'),(155,'410100','郑州市','410000'),(156,'410200','开封市','410000'),(157,'410300','洛阳市','410000'),(158,'410400','平顶山市','410000'),(159,'410500','安阳市','410000'),(160,'410600','鹤壁市','410000'),(161,'410700','新乡市','410000'),(162,'410800','焦作市','410000'),(163,'410900','濮阳市','410000'),(164,'411000','许昌市','410000'),(165,'411100','漯河市','410000'),(166,'411200','三门峡市','410000'),(167,'411300','南阳市','410000'),(168,'411400','商丘市','410000'),(169,'411500','信阳市','410000'),(170,'411600','周口市','410000'),(171,'411700','驻马店市','410000'),(172,'420100','武汉市','420000'),(173,'420200','黄石市','420000'),(174,'420300','十堰市','420000'),(175,'420500','宜昌市','420000'),(176,'420600','襄樊市','420000'),(177,'420700','鄂州市','420000'),(178,'420800','荆门市','420000'),(179,'420900','孝感市','420000'),(180,'421000','荆州市','420000'),(181,'421100','黄冈市','420000'),(182,'421200','咸宁市','420000'),(183,'421300','随州市','420000'),(184,'422800','恩施土家族苗族自治州','420000'),(185,'429000','省直辖行政单位','420000'),(186,'430100','长沙市','430000'),(187,'430200','株洲市','430000'),(188,'430300','湘潭市','430000'),(189,'430400','衡阳市','430000'),(190,'430500','邵阳市','430000'),(191,'430600','岳阳市','430000'),(192,'430700','常德市','430000'),(193,'430800','张家界市','430000'),(194,'430900','益阳市','430000'),(195,'431000','郴州市','430000'),(196,'431100','永州市','430000'),(197,'431200','怀化市','430000'),(198,'431300','娄底市','430000'),(199,'433100','湘西土家族苗族自治州','430000'),(200,'440100','广州市','440000'),(201,'440200','韶关市','440000'),(202,'440300','深圳市','440000'),(203,'440400','珠海市','440000'),(204,'440500','汕头市','440000'),(205,'440600','佛山市','440000'),(206,'440700','江门市','440000'),(207,'440800','湛江市','440000'),(208,'440900','茂名市','440000'),(209,'441200','肇庆市','440000'),(210,'441300','惠州市','440000'),(211,'441400','梅州市','440000'),(212,'441500','汕尾市','440000'),(213,'441600','河源市','440000'),(214,'441700','阳江市','440000'),(215,'441800','清远市','440000'),(216,'441900','东莞市','440000'),(217,'442000','中山市','440000'),(218,'445100','潮州市','440000'),(219,'445200','揭阳市','440000'),(220,'445300','云浮市','440000'),(221,'450100','南宁市','450000'),(222,'450200','柳州市','450000'),(223,'450300','桂林市','450000'),(224,'450400','梧州市','450000'),(225,'450500','北海市','450000'),(226,'450600','防城港市','450000'),(227,'450700','钦州市','450000'),(228,'450800','贵港市','450000'),(229,'450900','玉林市','450000'),(230,'451000','百色市','450000'),(231,'451100','贺州市','450000'),(232,'451200','河池市','450000'),(233,'451300','来宾市','450000'),(234,'451400','崇左市','450000'),(235,'460100','海口市','460000'),(236,'460200','三亚市','460000'),(237,'469000','省直辖县级行政单位','460000'),(238,'500100','市辖区','500000'),(239,'500200','县','500000'),(240,'500300','市','500000'),(241,'510100','成都市','510000'),(242,'510300','自贡市','510000'),(243,'510400','攀枝花市','510000'),(244,'510500','泸州市','510000'),(245,'510600','德阳市','510000'),(246,'510700','绵阳市','510000'),(247,'510800','广元市','510000'),(248,'510900','遂宁市','510000'),(249,'511000','内江市','510000'),(250,'511100','乐山市','510000'),(251,'511300','南充市','510000'),(252,'511400','眉山市','510000'),(253,'511500','宜宾市','510000'),(254,'511600','广安市','510000'),(255,'511700','达州市','510000'),(256,'511800','雅安市','510000'),(257,'511900','巴中市','510000'),(258,'512000','资阳市','510000'),(259,'513200','阿坝藏族羌族自治州','510000'),(260,'513300','甘孜藏族自治州','510000'),(261,'513400','凉山彝族自治州','510000'),(262,'520100','贵阳市','520000'),(263,'520200','六盘水市','520000'),(264,'520300','遵义市','520000'),(265,'520400','安顺市','520000'),(266,'522200','铜仁地区','520000'),(267,'522300','黔西南布依族苗族自治州','520000'),(268,'522400','毕节地区','520000'),(269,'522600','黔东南苗族侗族自治州','520000'),(270,'522700','黔南布依族苗族自治州','520000'),(271,'530100','昆明市','530000'),(272,'530300','曲靖市','530000'),(273,'530400','玉溪市','530000'),(274,'530500','保山市','530000'),(275,'530600','昭通市','530000'),(276,'530700','丽江市','530000'),(277,'530800','思茅市','530000'),(278,'530900','临沧市','530000'),(279,'532300','楚雄彝族自治州','530000'),(280,'532500','红河哈尼族彝族自治州','530000'),(281,'532600','文山壮族苗族自治州','530000'),(282,'532800','西双版纳傣族自治州','530000'),(283,'532900','大理白族自治州','530000'),(284,'533100','德宏傣族景颇族自治州','530000'),(285,'533300','怒江傈僳族自治州','530000'),(286,'533400','迪庆藏族自治州','530000'),(287,'540100','拉萨市','540000'),(288,'542100','昌都地区','540000'),(289,'542200','山南地区','540000'),(290,'542300','日喀则地区','540000'),(291,'542400','那曲地区','540000'),(292,'542500','阿里地区','540000'),(293,'542600','林芝地区','540000'),(294,'610100','西安市','610000'),(295,'610200','铜川市','610000'),(296,'610300','宝鸡市','610000'),(297,'610400','咸阳市','610000'),(298,'610500','渭南市','610000'),(299,'610600','延安市','610000'),(300,'610700','汉中市','610000'),(301,'610800','榆林市','610000'),(302,'610900','安康市','610000'),(303,'611000','商洛市','610000'),(304,'620100','兰州市','620000'),(305,'620200','嘉峪关市','620000'),(306,'620300','金昌市','620000'),(307,'620400','白银市','620000'),(308,'620500','天水市','620000'),(309,'620600','武威市','620000'),(310,'620700','张掖市','620000'),(311,'620800','平凉市','620000'),(312,'620900','酒泉市','620000'),(313,'621000','庆阳市','620000'),(314,'621100','定西市','620000'),(315,'621200','陇南市','620000'),(316,'622900','临夏回族自治州','620000'),(317,'623000','甘南藏族自治州','620000'),(318,'630100','西宁市','630000'),(319,'632100','海东地区','630000'),(320,'632200','海北藏族自治州','630000'),(321,'632300','黄南藏族自治州','630000'),(322,'632500','海南藏族自治州','630000'),(323,'632600','果洛藏族自治州','630000'),(324,'632700','玉树藏族自治州','630000'),(325,'632800','海西蒙古族藏族自治州','630000'),(326,'640100','银川市','640000'),(327,'640200','石嘴山市','640000'),(328,'640300','吴忠市','640000'),(329,'640400','固原市','640000'),(330,'640500','中卫市','640000'),(331,'650100','乌鲁木齐市','650000'),(332,'650200','克拉玛依市','650000'),(333,'652100','吐鲁番地区','650000'),(334,'652200','哈密地区','650000'),(335,'652300','昌吉回族自治州','650000'),(336,'652700','博尔塔拉蒙古自治州','650000'),(337,'652800','巴音郭楞蒙古自治州','650000'),(338,'652900','阿克苏地区','650000'),(339,'653000','克孜勒苏柯尔克孜自治州','650000'),(340,'653100','喀什地区','650000'),(341,'653200','和田地区','650000'),(342,'654000','伊犁哈萨克自治州','650000'),(343,'654200','塔城地区','650000'),(344,'654300','阿勒泰地区','650000'),(345,'659000','省直辖行政单位','650000'),(346,'710100','台北市','710000'),(347,'710200','新北市','710000'),(348,'710300','台中市','710000'),(349,'710400','台南市','710000'),(350,'710500','高雄市','710000');
/*!40000 ALTER TABLE `sp_hat_city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sp_hat_province`
--

DROP TABLE IF EXISTS `sp_hat_province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sp_hat_province` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `provinceID` varchar(6) DEFAULT NULL,
  `province` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sp_hat_province`
--

LOCK TABLES `sp_hat_province` WRITE;
/*!40000 ALTER TABLE `sp_hat_province` DISABLE KEYS */;
INSERT INTO `sp_hat_province` VALUES (1,'110000','北京市'),(2,'120000','天津市'),(3,'130000','河北省'),(4,'140000','山西省'),(5,'150000','内蒙古自治区'),(6,'210000','辽宁省'),(7,'220000','吉林省'),(8,'230000','黑龙江省'),(9,'310000','上海市'),(10,'320000','江苏省'),(11,'330000','浙江省'),(12,'340000','安徽省'),(13,'350000','福建省'),(14,'360000','江西省'),(15,'370000','山东省'),(16,'410000','河南省'),(17,'420000','湖北省'),(18,'430000','湖南省'),(19,'440000','广东省'),(20,'450000','广西壮族自治区'),(21,'460000','海南省'),(22,'500000','重庆市'),(23,'510000','四川省'),(24,'520000','贵州省'),(25,'530000','云南省'),(26,'540000','西藏自治区'),(27,'610000','陕西省'),(28,'620000','甘肃省'),(29,'630000','青海省'),(30,'640000','宁夏回族自治区'),(31,'650000','新疆维吾尔自治区'),(32,'710000','台湾省');
/*!40000 ALTER TABLE `sp_hat_province` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_account`
--

DROP TABLE IF EXISTS `test_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键\n',
  `name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `money` decimal(10,0) DEFAULT NULL COMMENT '金钱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='账户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_account`
--

LOCK TABLES `test_account` WRITE;
/*!40000 ALTER TABLE `test_account` DISABLE KEYS */;
INSERT INTO `test_account` VALUES (1,'aaa',100),(2,'bbb',500);
/*!40000 ALTER TABLE `test_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `violation_record`
--

DROP TABLE IF EXISTS `violation_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `violation_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '违规记录表主键id',
  `content` varchar(255) DEFAULT NULL COMMENT '违规的内容',
  `sign_up_id` int(11) NOT NULL COMMENT '报名id',
  `creat_time` date NOT NULL COMMENT '创建时间',
  `delete_flag` int(11) NOT NULL DEFAULT '0' COMMENT '0 未删除 1 已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='违规记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `violation_record`
--

LOCK TABLES `violation_record` WRITE;
/*!40000 ALTER TABLE `violation_record` DISABLE KEYS */;
INSERT INTO `violation_record` VALUES (1,'有责未报到3次',1,'2019-07-24',0),(2,'有责未报道2次',9,'2019-08-07',0),(3,'有责未报道4次',4,'2019-07-24',0),(4,'有责未报道5次',12,'2019-07-13',0),(5,'有责未报道6次',6,'2019-07-22',0),(6,'有责未报道7次',8,'2019-07-30',0);
/*!40000 ALTER TABLE `violation_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `withdrawrules`
--

DROP TABLE IF EXISTS `withdrawrules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `withdrawrules` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `procedureFee` decimal(7,5) DEFAULT NULL COMMENT '手续费',
  `withdrawNum` int(11) DEFAULT NULL COMMENT '提现次数',
  `type` int(11) DEFAULT NULL COMMENT '0 提现手续费 1每月提现次数',
  `createTime` datetime DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `withdrawrules`
--

LOCK TABLES `withdrawrules` WRITE;
/*!40000 ALTER TABLE `withdrawrules` DISABLE KEYS */;
INSERT INTO `withdrawrules` VALUES (1,0.00100,NULL,0,'2018-11-16 15:10:44',0),(2,NULL,2,1,'2018-11-16 15:24:45',0);
/*!40000 ALTER TABLE `withdrawrules` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-30 14:26:23
