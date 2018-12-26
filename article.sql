/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2018-08-17 12:02:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `uid` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `date` datetime NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`aid`),
  KEY `aid` (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('3', 'aaaaaaaaaaa', '1', 'test', '2018-08-17 00:00:00', '<p>adsfasdf<span style=\"font-weight: bold;\">dfasdfasdfafd</span><span style=\"font-family: Roboto, sans-serif;\">asdfasdf</span></p>');
INSERT INTO `article` VALUES ('5', 'testtest', '1', 'test', '2018-08-17 00:00:00', '<h1>Hi</h1><p><span style=\"font-weight: bold;\">asdfasdf</span><br></p><p><span style=\"font-style: italic;\">asdfs</span></p><p><span style=\"font-style: italic;\"><br></span></p><p><span style=\"font-style: italic;\"><br></span></p>');
INSERT INTO `article` VALUES ('6', 'test long', '1', 'test', '2018-08-17 00:00:00', '<h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1>COOL</h1><h1><br></h1>');
