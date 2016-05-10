/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50626
 Source Host           : localhost
 Source Database       : lionxxw_activiti

 Target Server Type    : MySQL
 Target Server Version : 50626
 File Encoding         : utf-8

 Date: 05/10/2016 10:45:41 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '部门名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `state` int(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `emp_dep_temp`
-- ----------------------------
DROP TABLE IF EXISTS `emp_dep_temp`;
CREATE TABLE `emp_dep_temp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dep_id` bigint(20) DEFAULT NULL COMMENT '部门id',
  `emp_id` bigint(20) DEFAULT NULL COMMENT '员工id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `employee`
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) NOT NULL COMMENT '登录账号',
  `pwd` varchar(32) NOT NULL COMMENT '密码(md5)',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `sex` int(1) DEFAULT '0' COMMENT '性别(0-男,1-女)',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `manager_id` bigint(20) DEFAULT NULL COMMENT '上级经理id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `state` int(1) DEFAULT '1' COMMENT '数据状态(0-删除,1-正常)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `employee`
-- ----------------------------
BEGIN;
INSERT INTO `employee` VALUES ('1', 'admin', '123456', '超管', '0', null, null, null, '2016-05-06 13:21:41', '1'), ('3', 'test', '123456', '测试', '0', null, null, null, '2016-05-06 13:22:17', '1'), ('4', 'wangxiang', '123456', '王翔', '0', '18721472363', 'vonshine15@163.com', '1', '2016-05-06 20:50:00', '1'), ('5', 'gmh', '123456', '耿梦涵', '1', '18317059960', 'amber_hh@163.com', '4', '2016-05-06 20:52:28', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
