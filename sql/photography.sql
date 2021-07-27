/*
 Navicat Premium Data Transfer

 Source Server         : aliyun-petcage
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 39.100.118.8:9834
 Source Schema         : photography

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 09/05/2021 12:49:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app_info
-- ----------------------------
DROP TABLE IF EXISTS `app_info`;
CREATE TABLE `app_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `service_content` text COMMENT '服务条例',
  `private_content` text COMMENT '隐私协议',
  `version` varchar(100) DEFAULT NULL COMMENT '小程序版本',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='小程序信息表';

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `photography_id` int(11) DEFAULT NULL COMMENT '作品id',
  `open_id` varchar(100) DEFAULT NULL COMMENT '评论人微信open_id',
  `comment_id` bigint(20) DEFAULT '0' COMMENT '评论id',
  `comment` text COMMENT '评论内容',
  `is_vote` tinyint(1) DEFAULT '0' COMMENT '是否点赞了',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `competition_id` varchar(50) DEFAULT NULL COMMENT '参赛作品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8 COMMENT='评论表';

-- ----------------------------
-- Table structure for competition
-- ----------------------------
DROP TABLE IF EXISTS `competition`;
CREATE TABLE `competition` (
  `competition_id` varchar(50) NOT NULL COMMENT '比赛id',
  `name` varchar(255) DEFAULT NULL COMMENT '比赛名称',
  `subject` text COMMENT '比赛主题',
  `condition` text COMMENT '比赛条件',
  `start_date` varchar(10) DEFAULT NULL COMMENT '比赛开始时间',
  `end_date` varchar(10) DEFAULT NULL COMMENT '比赛结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `img_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`competition_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='比赛场次表';

-- ----------------------------
-- Table structure for dim_pet
-- ----------------------------
DROP TABLE IF EXISTS `dim_pet`;
CREATE TABLE `dim_pet` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `pet_type` varchar(100) DEFAULT NULL COMMENT '宠物类型',
  `variety` varchar(100) DEFAULT NULL COMMENT '品种',
  `size` varchar(10) DEFAULT NULL COMMENT '宠物大小（大型、中型、小型）',
  `country` varchar(100) DEFAULT NULL COMMENT '国别',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 COMMENT='宠物字典表';

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `open_id` varchar(100) DEFAULT NULL COMMENT '反馈人open_id',
  `content` text COMMENT '反馈内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='反馈表';

-- ----------------------------
-- Table structure for photography
-- ----------------------------
DROP TABLE IF EXISTS `photography`;
CREATE TABLE `photography` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `phone` varchar(50) DEFAULT NULL COMMENT '参赛人手机号',
  `open_id` varchar(100) DEFAULT NULL COMMENT '参赛人微信open_id',
  `url` varchar(255) DEFAULT NULL COMMENT '参赛作品',
  `type` varchar(10) DEFAULT NULL COMMENT '作品类型（image/video）',
  `subject` varchar(255) DEFAULT NULL COMMENT '作品主题',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '宠物昵称',
  `photographer` varchar(255) DEFAULT NULL COMMENT '作者昵称',
  `votes` int(11) DEFAULT '0' COMMENT '作品点赞量',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='摄影作品表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `open_id` varchar(100) DEFAULT NULL COMMENT '微信open_id',
  `union_id` varchar(100) DEFAULT NULL COMMENT '微信union_id',
  `pwd` varchar(255) DEFAULT NULL COMMENT '密码',
  `user_type` varchar(10) DEFAULT NULL COMMENT '用户类型(1普通用户,2摄影师,3摄影机构,4宠物店)',
  `valid_start_date` datetime DEFAULT NULL COMMENT '会员有效开始日期',
  `valid_end_date` datetime DEFAULT NULL COMMENT '会员有效结束日期',
  `token` varchar(255) DEFAULT NULL COMMENT '登录access token',
  `country` varchar(255) DEFAULT NULL COMMENT '国家',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '头像url',
  `gender` varchar(255) DEFAULT NULL COMMENT '性别',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `language` varchar(255) DEFAULT NULL COMMENT '语言',
  `birthday` varchar(100) DEFAULT NULL COMMENT '出生日期',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `balance` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for user_pet
-- ----------------------------
DROP TABLE IF EXISTS `user_pet`;
CREATE TABLE `user_pet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avatar_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `birthday` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `contact` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_time` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `nick_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `open_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `pet_type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `update_time` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `variety` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for vote
-- ----------------------------
DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote` (
  `competition_id` varchar(50) NOT NULL COMMENT '比赛id',
  `phone` varchar(50) DEFAULT NULL COMMENT '参赛人手机号',
  `open_id` varchar(100) NOT NULL COMMENT '参赛人微信open_id',
  `subject` varchar(255) DEFAULT NULL COMMENT '作品主题',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '宠物昵称',
  `votes` int(11) DEFAULT '0' COMMENT '票数',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `type` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增',
  PRIMARY KEY (`competition_id`,`open_id`),
  UNIQUE KEY `new_uk_name` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='作品投票表';

SET FOREIGN_KEY_CHECKS = 1;
