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

 Date: 11/16/2019 23:46:20 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `Student_Info`
-- ----------------------------
DROP TABLE IF EXISTS `Student_Info`;
CREATE TABLE `Student_Info` (
  `Student_ID` varchar(100) NOT NULL,
  `Student_Name` varchar(100) NOT NULL,
  `Age` int(11) NOT NULL,
  `Program` varchar(100) NOT NULL,
  `Gender` varchar(100) NOT NULL,
  `Country` varchar(100) NOT NULL,
  `Remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Student_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `Student_Info`
-- ----------------------------
BEGIN;
INSERT INTO `Student_Info` VALUES ('U201901', 'Xu Zheng', '23', 'MISM', 'Male', 'China', 'null'), ('U201902', 'Yufan Yan', '22', 'MISM', 'Male', 'China', 'null'), ('U201903', 'Xinrui Zheng', '22', 'MISM', 'Female', 'China', 'Remark'), ('U201904', 'Shanyue Wan', '22', 'MISM', 'Male', 'China', 'null'), ('U201905', 'Jianping Deng', '21', 'MSIT', 'Male', 'China', 'null');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
