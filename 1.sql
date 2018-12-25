/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.6.41 : Database - library
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`library` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `library`;

/*Table structure for table `bespeak` */

DROP TABLE IF EXISTS `bespeak`;

CREATE TABLE `bespeak` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reader_card_id` varchar(30) DEFAULT NULL,
  `book_id` varchar(30) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `bespeak` */

insert  into `bespeak`(`id`,`reader_card_id`,`book_id`,`date`) values (1,'16251104120','123','2018-10-17 00:00:00');

/*Table structure for table `bookinfo` */

DROP TABLE IF EXISTS `bookinfo`;

CREATE TABLE `bookinfo` (
  `book_id` varchar(13) NOT NULL,
  `typeId` int(11) DEFAULT NULL,
  `bookname` varchar(40) DEFAULT NULL,
  `writer` varchar(21) DEFAULT NULL,
  `translator` varchar(30) DEFAULT NULL,
  `publisher` varchar(50) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `numbers` int(11) DEFAULT NULL,
  `can_lend` int(11) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bookinfo` */

insert  into `bookinfo`(`book_id`,`typeId`,`bookname`,`writer`,`translator`,`publisher`,`date`,`price`,`numbers`,`can_lend`) values ('123',1,'金瓶梅','张三',NULL,'人民出版社','2018-09-10 00:00:00','100',5,0);

/*Table structure for table `booktype` */

DROP TABLE IF EXISTS `booktype`;

CREATE TABLE `booktype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(20) DEFAULT NULL,
  `days` int(11) DEFAULT '90',
  `fk` double DEFAULT '0.01',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `booktype` */

insert  into `booktype`(`id`,`typeName`,`days`,`fk`) values (1,'科学类',90,0.01),(2,'文学类',90,0.01),(3,'历史类',90,0.01),(4,'农业类',90,0.01),(5,'地理类',90,0.01);

/*Table structure for table `borrow` */

DROP TABLE IF EXISTS `borrow`;

CREATE TABLE `borrow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` varchar(13) DEFAULT NULL,
  `operatorId` int(11) DEFAULT NULL,
  `reader_id` varchar(13) DEFAULT NULL,
  `isback` varchar(11) DEFAULT '否',
  `borrowDate` datetime DEFAULT NULL,
  `bakeDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `borrow` */

insert  into `borrow`(`id`,`book_id`,`operatorId`,`reader_id`,`isback`,`borrowDate`,`bakeDate`) values (1,'123',1,'16251104120','0','2018-10-21 00:00:00','2018-11-21 00:00:00');

/*Table structure for table `fine` */

DROP TABLE IF EXISTS `fine`;

CREATE TABLE `fine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `card_id` varchar(30) DEFAULT NULL,
  `book_name` varchar(30) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `ispay` varchar(4) DEFAULT 'NO',
  `reason` varchar(100) DEFAULT '逾时还书',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `fine` */

insert  into `fine`(`id`,`name`,`card_id`,`book_name`,`price`,`ispay`,`reason`) values (1,'傻峰','16251104120','金瓶梅',50,'是','逾时还书');

/*Table structure for table `operator` */

DROP TABLE IF EXISTS `operator`;

CREATE TABLE `operator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `identityCard` varchar(30) DEFAULT NULL,
  `workdate` datetime DEFAULT NULL,
  `tel` varchar(50) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `type` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `operator` */

insert  into `operator`(`id`,`name`,`sex`,`age`,`identityCard`,`workdate`,`tel`,`password`,`type`) values (1,'admin','男',22,'12345678901','2018-10-01 00:00:00','12345678746','12345','1');

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `book_id` varchar(13) NOT NULL,
  `date` datetime DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `operator` varchar(6) DEFAULT NULL,
  `checkAndAccept` int(11) DEFAULT NULL,
  `zk` double DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order` */

/*Table structure for table `outage` */

DROP TABLE IF EXISTS `outage`;

CREATE TABLE `outage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` varchar(11) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `reason` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `outage` */

/*Table structure for table `reader` */

DROP TABLE IF EXISTS `reader`;

CREATE TABLE `reader` (
  `id` int(13) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `age` int(10) DEFAULT NULL,
  `indentityCard` varchar(30) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `maxNum` int(11) DEFAULT NULL,
  `hasLend` int(11) DEFAULT '0',
  `tel` varchar(50) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `zj` varchar(10) DEFAULT NULL,
  `bztime` datetime DEFAULT NULL,
  `cardType` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `reader` */

insert  into `reader`(`id`,`name`,`sex`,`age`,`indentityCard`,`date`,`maxNum`,`hasLend`,`tel`,`password`,`zj`,`bztime`,`cardType`) values (1,'傻峰','女',30,'16251104120','2020-06-01 00:00:00',200,0,'13416517235','sangfeng','student','2016-09-01 00:00:00','暂停'),(4,'asdfa','男',21,'12345','2022-10-11 00:00:00',200,0,'1234','12345','学生','2018-10-11 00:00:00','正常'),(7,'12','男',12,'123456','2022-10-11 00:00:00',200,0,'121','121','学生','2018-10-11 00:00:00','正常');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
