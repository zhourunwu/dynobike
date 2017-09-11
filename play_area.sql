/*
Navicat MySQL Data Transfer

Source Server         : localhost-mysql-connection
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : adcloud

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-06-23 22:22:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for play_area
-- ----------------------------
DROP TABLE IF EXISTS `play_area`;
CREATE TABLE `play_area` (
  `area_id` int(11) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(50) NOT NULL,
  `startX` int(11) NOT NULL,
  `startY` int(11) NOT NULL,
  `width` int(11) NOT NULL,
  `height` int(11) NOT NULL,
  `layer` int(11) NOT NULL,
  `area_type` varchar(50) NOT NULL,
  `task_id` int(11) NOT NULL,
  `tasktpl_id` int(11) NOT NULL,
  PRIMARY KEY (`area_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of play_area
-- ----------------------------
INSERT INTO `play_area` VALUES ('1', '视频区', '10', '10', '200', '200', '1', 'video', '29', '2');
INSERT INTO `play_area` VALUES ('2', '图片区1', '10', '10', '200', '200', '1', 'image', '29', '2');
INSERT INTO `play_area` VALUES ('3', '流动文字区', '10', '10', '200', '200', '1', 'text', '29', '2');
INSERT INTO `play_area` VALUES ('4', '流动图片区2', '10', '10', '200', '200', '1', 'image', '29', '2');
INSERT INTO `play_area` VALUES ('5', '政务之窗（在线链接区）', '10', '10', '200', '200', '1', 'dynamic_html', '29', '2');
