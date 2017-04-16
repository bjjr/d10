start transaction;

create database `Acme-Chorbies`;

use `Acme-Chorbies`;

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete 
	on `Acme-Chorbies`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `Acme-Chorbies`.* to 'acme-manager'@'%';


-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Chorbies
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_cgls5lrufx91ufsyh467spwa3` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_idt4b4u259p6vs4pyr9lax4eg` (`userAccount_id`),
  CONSTRAINT `FK_idt4b4u259p6vs4pyr9lax4eg` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (994,0,'admin@email.com','adminName','+666 335566','adminSurname',983);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banner` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `path` varchar(255) DEFAULT NULL,
  `service` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
INSERT INTO `banner` VALUES (1010,0,'https://i1.wp.com/www.cocinothai.com/wp-content/uploads/2013/06/Pad-thaiFeat.jpg?resize=940%2C590','Acme-Pad-Thai'),(1011,0,'http://www.ikea.com/PIAimages/0416866_PE577783_S5.JPG','Acme BnB'),(1012,0,'http://www.km77.com/marcas/ford/fiesta_05/tdci90/med/04.jpg','Acme CnG');
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chirp`
--

DROP TABLE IF EXISTS `chirp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chirp` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `copy` bit(1) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `recipient_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_20arsrvo5x8fqqrj7o5tomu6f` (`recipient_id`),
  KEY `FK_7nnun85ngke6yiv2qnerep5uc` (`sender_id`),
  CONSTRAINT `FK_20arsrvo5x8fqqrj7o5tomu6f` FOREIGN KEY (`recipient_id`) REFERENCES `chorbi` (`id`),
  CONSTRAINT `FK_7nnun85ngke6yiv2qnerep5uc` FOREIGN KEY (`sender_id`) REFERENCES `chorbi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chirp`
--

LOCK TABLES `chirp` WRITE;
/*!40000 ALTER TABLE `chirp` DISABLE KEYS */;
INSERT INTO `chirp` VALUES (1035,0,'\0','2016-03-15 21:31:00','Chirp 1 subject','Chirp 1 text',1015,1014),(1036,0,'','2016-03-15 21:31:00','Chirp 1 subject','Chirp 1 text',1015,1014),(1037,0,'\0','2016-04-17 22:15:00','Chirp 2 subject','Chirp 2 text',1014,1015),(1038,0,'','2016-04-17 22:15:00','Chirp 2 subject','Chirp 2 text',1014,1015),(1039,0,'\0','2016-02-21 21:31:00','Chirp 3 subject','Chirp 3 text',1016,1015),(1040,0,'','2016-02-21 21:31:00','Chirp 3 subject','Chirp 3 text',1016,1015),(1041,0,'\0','2016-05-29 17:47:00','Chirp 4 subject','Chirp 4 text',1016,1015),(1042,0,'','2016-05-29 17:47:00','Chirp 4 subject','Chirp 4 text',1016,1015),(1043,0,'\0','2016-06-30 13:45:00','Chirp 5 subject','Chirp 5 text',1016,1015),(1044,0,'','2016-06-30 13:45:00','Chirp 5 subject','Chirp 5 text',1016,1015),(1045,0,'\0','2016-07-07 12:11:00','Chirp 6 subject','Chirp 6 text',1016,1015),(1046,0,'','2016-07-07 12:11:00','Chirp 6 subject','Chirp 6 text',1016,1015),(1047,0,'\0','2016-07-08 20:32:00','Chirp 7 subject','Chirp 7 text',1016,1015),(1048,0,'','2016-07-08 20:32:00','Chirp 7 subject','Chirp 7 text',1016,1015),(1049,0,'\0','2016-07-12 18:33:00','Chirp 8 subject','Chirp 8 text',1016,1015),(1050,0,'','2016-07-12 18:33:00','Chirp 8 subject','Chirp 8 text',1016,1015);
/*!40000 ALTER TABLE `chirp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chirp_attachments`
--

DROP TABLE IF EXISTS `chirp_attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chirp_attachments` (
  `Chirp_id` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  KEY `FK_19xst1rktyonkumt1r20fe0gh` (`Chirp_id`),
  CONSTRAINT `FK_19xst1rktyonkumt1r20fe0gh` FOREIGN KEY (`Chirp_id`) REFERENCES `chirp` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chirp_attachments`
--

LOCK TABLES `chirp_attachments` WRITE;
/*!40000 ALTER TABLE `chirp_attachments` DISABLE KEYS */;
INSERT INTO `chirp_attachments` VALUES (1039,'https://www.attachment1.com'),(1040,'https://www.attachment1.com'),(1041,'https://www.attachment2.com'),(1041,'https://www.attachment3.com'),(1042,'https://www.attachment2.com'),(1042,'https://www.attachment3.com');
/*!40000 ALTER TABLE `chirp_attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chorbi`
--

DROP TABLE IF EXISTS `chorbi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chorbi` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) DEFAULT NULL,
  `banned` bit(1) NOT NULL,
  `birthdate` date DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `relationship` varchar(255) DEFAULT NULL,
  `creditCard_id` int(11) DEFAULT NULL,
  `searchTemplate_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5louq4cq51fl6wldjwd5ivlw2` (`searchTemplate_id`),
  KEY `FK_ooohivehsja6eu3cxe201pe7v` (`creditCard_id`),
  KEY `FK_qrvmwkp25xc5exr6m3jgaxu4x` (`userAccount_id`),
  CONSTRAINT `FK_5louq4cq51fl6wldjwd5ivlw2` FOREIGN KEY (`searchTemplate_id`) REFERENCES `searchtemplate` (`id`),
  CONSTRAINT `FK_ooohivehsja6eu3cxe201pe7v` FOREIGN KEY (`creditCard_id`) REFERENCES `creditcard` (`id`),
  CONSTRAINT `FK_qrvmwkp25xc5exr6m3jgaxu4x` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chorbi`
--

LOCK TABLES `chorbi` WRITE;
/*!40000 ALTER TABLE `chorbi` DISABLE KEYS */;
INSERT INTO `chorbi` VALUES (1013,1,'chorbi1@email.com','chorbiName1','+656 335566','chorbiSurname1',984,'\0','1982-01-01','CITY1','COUNTRY1',NULL,NULL,'Chorbi 1 description','MAN','https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg','LOVE',32768,995),(1014,0,'chorbi2@email.com','chorbiName2','+34666333222','chorbiSurname2',985,'\0','1984-05-25','CITY2','COUNTRY2','PROVINCE1','STATE1','Chorbi 2 description','WOMAN','https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg','LOVE',NULL,996),(1015,0,'chorbi3@email.com','chorbiName3','56478521562','chorbiSurname3',986,'\0','1979-03-17','CITY3','COUNTRY2',NULL,NULL,'Chorbi 3 description','WOMAN','https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg','LOVE',1005,997),(1016,0,'chorbi4@email.com','chorbiName4','+3457 5215-AAA-11','chorbiSurname4',987,'\0','1981-04-25','CITY4','COUNTRY1',NULL,NULL,'Chorbi 4 description','MAN','https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg','ACTIVITIES',1006,998),(1017,0,'chorbi5@email.com','chorbiName5','+345 AA-BB-CC15','chorbiSurname5',988,'\0','1989-11-12','CITY4','COUNTRY1',NULL,NULL,'Chorbi 5 description','WOMAN','https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg','ACTIVITIES',1007,999),(1018,0,'chorbi6@email.com','chorbiName6','+3 1111112222','chorbiSurname6',989,'\0','1971-01-12','CITY3','COUNTRY2',NULL,NULL,'Chorbi 6 description','MAN','https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg','ACTIVITIES',1008,1000),(1019,0,'chorbi7@email.com','chorbiName7','+5 4587225','chorbiSurname7',990,'\0','1975-04-17','CITY2','COUNTRY2','PROVINCE1','STATE1','Chorbi 7 description','WOMAN','https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg','FRIENDSHIP',1009,1001),(1020,0,'chorbi8@email.com','chorbiName8','+18 12345678','chorbiSurname8',991,'\0','1980-06-21','CITY1','COUNTRY1',NULL,NULL,'Chorbi 8 description','MAN','https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg','FRIENDSHIP',NULL,1002),(1021,0,'chorbi9@email.com','chorbiName9','124578624','chorbiSurname9',992,'\0','1978-07-21','CITY1','COUNTRY1',NULL,NULL,'Chorbi 9 description','MAN','https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg','FRIENDSHIP',NULL,1003),(1022,0,'chorbi10@email.com','chorbiName10','+12 5485631474','chorbiSurname10',993,'','1982-02-27','CITY3','COUNTRY2',NULL,NULL,'Chorbi 10 description','MAN','https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg','FRIENDSHIP',NULL,1004);
/*!40000 ALTER TABLE `chorbi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chorbilike`
--

DROP TABLE IF EXISTS `chorbilike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chorbilike` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `liked_id` int(11) NOT NULL,
  `liker_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_skbkxrebny5li9ugo11g55321` (`liked_id`),
  KEY `FK_ss6bixsllm6ptd17uf7ct8sw6` (`liker_id`),
  CONSTRAINT `FK_skbkxrebny5li9ugo11g55321` FOREIGN KEY (`liked_id`) REFERENCES `chorbi` (`id`),
  CONSTRAINT `FK_ss6bixsllm6ptd17uf7ct8sw6` FOREIGN KEY (`liker_id`) REFERENCES `chorbi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chorbilike`
--

LOCK TABLES `chorbilike` WRITE;
/*!40000 ALTER TABLE `chorbilike` DISABLE KEYS */;
INSERT INTO `chorbilike` VALUES (1023,0,NULL,'2016-05-05 17:45:00',1016,1014),(1024,0,NULL,'2016-05-06 17:49:00',1014,1015),(1025,0,'A nice comment','2016-03-05 12:45:00',1016,1015),(1026,0,NULL,'2016-04-12 18:03:00',1017,1015),(1027,0,NULL,'2016-05-05 14:15:00',1018,1015),(1028,0,NULL,'2016-06-05 17:45:00',1019,1015),(1029,0,NULL,'2016-08-25 23:51:00',1020,1015),(1030,0,NULL,'2016-11-11 22:37:00',1021,1015),(1031,0,NULL,'2016-11-10 23:37:00',1016,1017),(1032,0,NULL,'2016-11-27 19:37:00',1016,1018),(1033,0,NULL,'2016-11-30 18:37:00',1016,1019),(1034,0,NULL,'2016-11-01 15:37:00',1016,1020);
/*!40000 ALTER TABLE `chorbilike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditcard`
--

DROP TABLE IF EXISTS `creditcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `creditcard` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `cvv` int(11) NOT NULL,
  `holder` varchar(255) DEFAULT NULL,
  `month` int(11) NOT NULL,
  `number` varchar(255) DEFAULT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditcard`
--

LOCK TABLES `creditcard` WRITE;
/*!40000 ALTER TABLE `creditcard` DISABLE KEYS */;
INSERT INTO `creditcard` VALUES (1005,0,'VISA',301,'holderName1',1,'869964971792152',2020),(1006,0,'MASTERCARD',104,'holderName1',2,'869958262282899',2019),(1007,0,'DISCOVER',602,'holderName1',3,'869910591110382',2019),(1008,0,'DINNERS',496,'holderName1',11,'869930337417727',2015),(1009,0,'AMEX',890,'holderName1',5,'869922049378552',2011),(32768,3,'MASTERCARD',120,'asdf',4,'869964971792152',2017);
/*!40000 ALTER TABLE `creditcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',2);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `searchtemplate`
--

DROP TABLE IF EXISTS `searchtemplate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `searchtemplate` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `relationship` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `searchtemplate`
--

LOCK TABLES `searchtemplate` WRITE;
/*!40000 ALTER TABLE `searchtemplate` DISABLE KEYS */;
INSERT INTO `searchtemplate` VALUES (995,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(996,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(997,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(998,0,30,'CITY4','COUNTRY1',NULL,NULL,'WOMAN','chorbiName','ACTIVITIES'),(999,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'FRIENDSHIP'),(1000,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ACTIVITIES'),(1001,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ACTIVITIES'),(1002,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'LOVE'),(1003,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ACTIVITIES'),(1004,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'LOVE');
/*!40000 ALTER TABLE `searchtemplate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (983,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(984,0,'3daa859a294cdefb20a84840240a76f5','chorbi1'),(985,0,'0c8746de81268518ff83490301db8652','chorbi2'),(986,0,'a2da05a88eead7e64593826cafc6a7a7','chorbi3'),(987,0,'a09dd233386632e297a7f4f461989563','chorbi4'),(988,0,'7e062f6f2a4c0f7ec5abacef2917e033','chorbi5'),(989,0,'0b41c51bd4b755c5b639498f55058fd3','chorbi6'),(990,0,'cd33d975ad65688dc4d54b67ed48fd1a','chorbi7'),(991,0,'cf0216b73314f84c64fd88f5adf4dfb2','chorbi8'),(992,0,'f33e074e9fd8e9289b7f0d790acb917c','chorbi9'),(993,0,'5e446ee1e7cc817c52cc5d464ded75c5','chorbi10');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (983,'ADMIN'),(984,'CHORBI'),(985,'CHORBI'),(986,'CHORBI'),(987,'CHORBI'),(988,'CHORBI'),(989,'CHORBI'),(990,'CHORBI'),(991,'CHORBI'),(992,'CHORBI'),(993,'CHORBI');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-16 18:28:08

commit;
