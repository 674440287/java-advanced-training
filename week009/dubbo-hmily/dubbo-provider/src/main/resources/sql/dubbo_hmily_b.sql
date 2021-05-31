/*
 Navicat Premium Data Transfer

 Source Server         : LocalMySql
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : dubbo_hmily_b

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 28/05/2021 15:52:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键ID',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户ID',
  `total_money` decimal(12, 0) DEFAULT NULL COMMENT '用户余额',
  `money_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '金钱类型(人民币/美元)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of account
-- ----------------------------
BEGIN;
INSERT INTO `account` VALUES ('1', 'b', 20, '人民币'), ('2', 'b', 10, '美元');
COMMIT;

-- ----------------------------
-- Table structure for frozen_assets
-- ----------------------------
DROP TABLE IF EXISTS `frozen_assets`;
CREATE TABLE `frozen_assets`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键ID',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户ID',
  `frozen_money` decimal(12, 0) DEFAULT NULL COMMENT '冻结金额',
  `money_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '金钱类型(人民币/美元)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of frozen_assets
-- ----------------------------
BEGIN;
INSERT INTO `frozen_assets` VALUES ('1', 'b', 50, '人名币'), ('2', 'b', 20, '美元');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
