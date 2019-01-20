/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-01-19 21:54:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sh_power`
-- ----------------------------
DROP TABLE IF EXISTS `sh_power`;
CREATE TABLE `sh_power` (
  `u_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '鏉冮檺id',
  `power_name` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限名称',
  `power_url` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限url',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sh_power
-- ----------------------------
INSERT INTO `sh_power` VALUES ('12345', '添加管理員', '/usermag');

-- ----------------------------
-- Table structure for `sh_role`
-- ----------------------------
DROP TABLE IF EXISTS `sh_role`;
CREATE TABLE `sh_role` (
  `u_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '瑙掕壊id',
  `role_name` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名称',
  `role_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色类型',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sh_role
-- ----------------------------
INSERT INTO `sh_role` VALUES ('12345', '管理員', 'admin');

-- ----------------------------
-- Table structure for `sh_role_power`
-- ----------------------------
DROP TABLE IF EXISTS `sh_role_power`;
CREATE TABLE `sh_role_power` (
  `u_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '瑙掕壊鏉冮檺id',
  `role_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  `power_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限id',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sh_role_power
-- ----------------------------
INSERT INTO `sh_role_power` VALUES ('12345', '12345', '12345');

-- ----------------------------
-- Table structure for `sh_user`
-- ----------------------------
DROP TABLE IF EXISTS `sh_user`;
CREATE TABLE `sh_user` (
  `u_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `user_name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名称',
  `nick_name` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
  `phone_no` varchar(13) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号码',
  `email` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `pass_word` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录日期',
  `allow_status` int(1) NOT NULL DEFAULT '1' COMMENT '鐧诲綍鐘舵€侊紝1-鏈夋晥锛?-绂佹',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sh_user
-- ----------------------------
INSERT INTO `sh_user` VALUES ('12345', 'admin', '管理员', null, null, 'admin', '2019-01-19 19:45:02', null, '1');

-- ----------------------------
-- Table structure for `sh_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sh_user_role`;
CREATE TABLE `sh_user_role` (
  `u_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '鐢ㄦ埛瑙掕壊id',
  `user_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `role_id` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sh_user_role
-- ----------------------------
INSERT INTO `sh_user_role` VALUES ('12345', '12345', '12345');
