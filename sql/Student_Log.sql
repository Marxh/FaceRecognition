/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost
 Source Database       : Face

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : utf-8

 Date: 11/16/2019 23:46:27 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `Student_Log`
-- ----------------------------
DROP TABLE IF EXISTS `Student_Log`;
CREATE TABLE `Student_Log` (
  `Stu_ID` varchar(100) NOT NULL,
  `Visit_Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Reason` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Stu_ID`,`Visit_Time`),
  CONSTRAINT `student_log_ibfk_1` FOREIGN KEY (`Stu_ID`) REFERENCES `student_info` (`Student_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `Student_Log`
-- ----------------------------
BEGIN;
INSERT INTO `Student_Log` VALUES ('U201901', '2019-08-29 05:45:24', 'Orientation Register'), ('U201901', '2019-09-14 23:52:52', 'Booking Project Room'), ('U201901', '2019-10-26 07:08:12', 'Register for Halloween Party'), ('U201901', '2019-11-14 00:00:00', 'Java Course'), ('U201901', '2019-11-15 00:00:00', 'Java Course'), ('U201902', '2019-08-29 02:34:18', 'Orientation Register'), ('U201902', '2019-09-12 06:53:42', 'Booking Project Room'), ('U201903', '2019-08-28 00:51:34', 'Orientation Register'), ('U201903', '2019-09-25 04:48:54', 'Check Assignments Due'), ('U201903', '2019-10-01 05:56:07', 'Check Assignment Score'), ('U201903', '2019-10-07 08:07:52', 'Log into Library Website'), ('U201903', '2019-10-28 09:53:24', 'Register for Halloween Party'), ('U201903', '2019-11-15 00:00:00', 'Java Course'), ('U201904', '2019-08-29 04:17:14', 'Orientation Register'), ('U201904', '2019-11-16 00:00:00', 'Java Course'), ('U201905', '2019-08-29 02:15:29', 'Orientation Register'), ('U201905', '2019-10-01 05:22:04', 'Check Assignments Due'), ('U201905', '2019-10-17 08:58:33', 'Booking Project Room'), ('U201905', '2019-10-30 11:06:27', 'Register for Halloween Party');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
