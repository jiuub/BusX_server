/*
 Navicat Premium Data Transfer

 Source Server         : MySQL_local
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : bus_db

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 03/05/2019 22:59:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `Index_name`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '$2a$10$s.q4m2.lfkLKZaI9RQcp6OiEE6wb8MjAUxCpzCCXniZDQQLgV/KNy', 'ADMIN');
INSERT INTO `admin` VALUES (8, '12345', '$2a$10$Bjk/YLAA4XgR13FK19.2vuRQHmlC4OY8NDodle/5TWqDZe3qUSRci', 'ADMIN');

-- ----------------------------
-- Table structure for bus
-- ----------------------------
DROP TABLE IF EXISTS `bus`;
CREATE TABLE `bus`  (
  `busID` int(11) NOT NULL AUTO_INCREMENT,
  `busName` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '车名',
  `busCity` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公交车所属城市',
  `busPrice` int(11) NOT NULL COMMENT '票价',
  `busStart` time(0) NULL DEFAULT NULL COMMENT '首班车',
  `busEnd` time(0) NULL DEFAULT NULL COMMENT '末班车',
  PRIMARY KEY (`busID`) USING BTREE,
  UNIQUE INDEX `busNameOnly`(`busName`, `busCity`) USING BTREE COMMENT '城市中的公交车名唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus
-- ----------------------------
INSERT INTO `bus` VALUES (1, '611路', '西安', 2, '06:00:00', '00:00:00');
INSERT INTO `bus` VALUES (2, '612路', '西安', 2, '06:00:00', '20:30:00');

-- ----------------------------
-- Table structure for line
-- ----------------------------
DROP TABLE IF EXISTS `line`;
CREATE TABLE `line`  (
  `busID` int(11) NOT NULL COMMENT '线路id',
  `stationID` int(11) NOT NULL COMMENT '站点id',
  `direction` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '方向',
  `sta_No` int(16) NOT NULL COMMENT '第几个站点',
  UNIQUE INDEX `lineID`(`busID`, `stationID`, `direction`) USING BTREE,
  INDEX `fk_line_bus_1`(`busID`) USING BTREE,
  INDEX `fk_line_station_1`(`stationID`) USING BTREE,
  CONSTRAINT `fk_line_bus_1` FOREIGN KEY (`busID`) REFERENCES `bus` (`busID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_line_station_1` FOREIGN KEY (`stationID`) REFERENCES `station` (`stationID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of line
-- ----------------------------
INSERT INTO `line` VALUES (1, 1, 'back', 20);
INSERT INTO `line` VALUES (1, 1, 'go', 1);
INSERT INTO `line` VALUES (1, 2, 'back', 19);
INSERT INTO `line` VALUES (1, 2, 'go', 2);
INSERT INTO `line` VALUES (1, 3, 'back', 18);
INSERT INTO `line` VALUES (1, 3, 'go', 3);
INSERT INTO `line` VALUES (1, 4, 'back', 17);
INSERT INTO `line` VALUES (1, 4, 'go', 4);
INSERT INTO `line` VALUES (1, 5, 'back', 16);
INSERT INTO `line` VALUES (1, 5, 'go', 5);
INSERT INTO `line` VALUES (1, 6, 'go', 6);
INSERT INTO `line` VALUES (1, 7, 'back', 14);
INSERT INTO `line` VALUES (1, 7, 'go', 7);
INSERT INTO `line` VALUES (1, 8, 'back', 13);
INSERT INTO `line` VALUES (1, 8, 'go', 8);
INSERT INTO `line` VALUES (1, 9, 'go', 9);
INSERT INTO `line` VALUES (1, 10, 'back', 11);
INSERT INTO `line` VALUES (1, 10, 'go', 10);
INSERT INTO `line` VALUES (1, 11, 'back', 10);
INSERT INTO `line` VALUES (1, 11, 'go', 11);
INSERT INTO `line` VALUES (1, 12, 'back', 9);
INSERT INTO `line` VALUES (1, 12, 'go', 12);
INSERT INTO `line` VALUES (1, 13, 'back', 8);
INSERT INTO `line` VALUES (1, 13, 'go', 13);
INSERT INTO `line` VALUES (1, 14, 'back', 7);
INSERT INTO `line` VALUES (1, 14, 'go', 14);
INSERT INTO `line` VALUES (1, 15, 'back', 6);
INSERT INTO `line` VALUES (1, 15, 'go', 15);
INSERT INTO `line` VALUES (1, 16, 'back', 5);
INSERT INTO `line` VALUES (1, 16, 'go', 16);
INSERT INTO `line` VALUES (1, 17, 'back', 4);
INSERT INTO `line` VALUES (1, 17, 'go', 17);
INSERT INTO `line` VALUES (1, 18, 'back', 3);
INSERT INTO `line` VALUES (1, 18, 'go', 18);
INSERT INTO `line` VALUES (1, 19, 'back', 2);
INSERT INTO `line` VALUES (1, 19, 'go', 19);
INSERT INTO `line` VALUES (1, 20, 'back', 1);
INSERT INTO `line` VALUES (1, 20, 'go', 20);
INSERT INTO `line` VALUES (1, 30, 'back', 12);
INSERT INTO `line` VALUES (1, 33, 'back', 15);
INSERT INTO `line` VALUES (2, 4, 'back', 17);
INSERT INTO `line` VALUES (2, 4, 'go', 10);
INSERT INTO `line` VALUES (2, 5, 'back', 16);
INSERT INTO `line` VALUES (2, 5, 'go', 11);
INSERT INTO `line` VALUES (2, 7, 'back', 14);
INSERT INTO `line` VALUES (2, 7, 'go', 13);
INSERT INTO `line` VALUES (2, 8, 'back', 13);
INSERT INTO `line` VALUES (2, 8, 'go', 14);
INSERT INTO `line` VALUES (2, 10, 'back', 11);
INSERT INTO `line` VALUES (2, 10, 'go', 16);
INSERT INTO `line` VALUES (2, 11, 'back', 10);
INSERT INTO `line` VALUES (2, 11, 'go', 17);
INSERT INTO `line` VALUES (2, 12, 'back', 9);
INSERT INTO `line` VALUES (2, 12, 'go', 18);
INSERT INTO `line` VALUES (2, 13, 'back', 8);
INSERT INTO `line` VALUES (2, 13, 'go', 19);
INSERT INTO `line` VALUES (2, 14, 'back', 7);
INSERT INTO `line` VALUES (2, 14, 'go', 20);
INSERT INTO `line` VALUES (2, 15, 'back', 6);
INSERT INTO `line` VALUES (2, 15, 'go', 21);
INSERT INTO `line` VALUES (2, 16, 'back', 5);
INSERT INTO `line` VALUES (2, 16, 'go', 22);
INSERT INTO `line` VALUES (2, 17, 'back', 4);
INSERT INTO `line` VALUES (2, 17, 'go', 23);
INSERT INTO `line` VALUES (2, 18, 'back', 3);
INSERT INTO `line` VALUES (2, 18, 'go', 24);
INSERT INTO `line` VALUES (2, 21, 'back', 25);
INSERT INTO `line` VALUES (2, 21, 'go', 1);
INSERT INTO `line` VALUES (2, 22, 'back', 24);
INSERT INTO `line` VALUES (2, 22, 'go', 2);
INSERT INTO `line` VALUES (2, 23, 'back', 23);
INSERT INTO `line` VALUES (2, 23, 'go', 3);
INSERT INTO `line` VALUES (2, 24, 'back', 22);
INSERT INTO `line` VALUES (2, 24, 'go', 4);
INSERT INTO `line` VALUES (2, 25, 'back', 21);
INSERT INTO `line` VALUES (2, 25, 'go', 5);
INSERT INTO `line` VALUES (2, 26, 'go', 6);
INSERT INTO `line` VALUES (2, 27, 'back', 20);
INSERT INTO `line` VALUES (2, 27, 'go', 7);
INSERT INTO `line` VALUES (2, 28, 'back', 19);
INSERT INTO `line` VALUES (2, 28, 'go', 8);
INSERT INTO `line` VALUES (2, 29, 'back', 18);
INSERT INTO `line` VALUES (2, 29, 'go', 9);
INSERT INTO `line` VALUES (2, 30, 'back', 12);
INSERT INTO `line` VALUES (2, 30, 'go', 15);
INSERT INTO `line` VALUES (2, 31, 'back', 2);
INSERT INTO `line` VALUES (2, 31, 'go', 25);
INSERT INTO `line` VALUES (2, 32, 'back', 1);
INSERT INTO `line` VALUES (2, 32, 'go', 26);
INSERT INTO `line` VALUES (2, 33, 'back', 15);
INSERT INTO `line` VALUES (2, 33, 'go', 12);

-- ----------------------------
-- Table structure for state
-- ----------------------------
DROP TABLE IF EXISTS `state`;
CREATE TABLE `state`  (
  `stateID` int(11) NOT NULL AUTO_INCREMENT,
  `busID` int(11) NULL DEFAULT NULL,
  `direction` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '方向',
  `sta_No` int(16) NULL DEFAULT NULL COMMENT '站点',
  `state` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '进站离站',
  PRIMARY KEY (`stateID`) USING BTREE,
  INDEX `busID`(`busID`) USING BTREE,
  CONSTRAINT `fk_state_bus_1` FOREIGN KEY (`busID`) REFERENCES `bus` (`busID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for station
-- ----------------------------
DROP TABLE IF EXISTS `station`;
CREATE TABLE `station`  (
  `stationID` int(11) NOT NULL AUTO_INCREMENT,
  `sta_Name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '巴士站点',
  `sta_City` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '站点所属城市',
  `sta_Lng` decimal(10, 7) NULL DEFAULT NULL COMMENT '经度',
  `sta_Lat` decimal(10, 7) NULL DEFAULT NULL COMMENT '纬度',
  PRIMARY KEY (`stationID`) USING BTREE,
  UNIQUE INDEX `sta_NameOnly`(`sta_Name`, `sta_City`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of station
-- ----------------------------
INSERT INTO `station` VALUES (0, '暂无信息', '暂无信息', NULL, NULL);
INSERT INTO `station` VALUES (1, '火车站', '西安', NULL, NULL);
INSERT INTO `station` VALUES (2, '五路口', '西安', NULL, NULL);
INSERT INTO `station` VALUES (3, '民乐园', '西安', NULL, NULL);
INSERT INTO `station` VALUES (4, '大差市', '西安', NULL, NULL);
INSERT INTO `station` VALUES (5, '端履门', '西安', NULL, NULL);
INSERT INTO `station` VALUES (6, '钟楼西', '西安', NULL, NULL);
INSERT INTO `station` VALUES (7, '广济街', '西安', NULL, NULL);
INSERT INTO `station` VALUES (8, '桥梓口', '西安', NULL, NULL);
INSERT INTO `station` VALUES (9, '西门里', '西安', NULL, NULL);
INSERT INTO `station` VALUES (10, '南小巷', '西安', NULL, NULL);
INSERT INTO `station` VALUES (11, '西稍门', '西安', NULL, NULL);
INSERT INTO `station` VALUES (12, '丰镐东路', '西安', NULL, NULL);
INSERT INTO `station` VALUES (13, '丰登路', '西安', NULL, NULL);
INSERT INTO `station` VALUES (14, '土门商厦', '西安', NULL, NULL);
INSERT INTO `station` VALUES (15, '土门', '西安', NULL, NULL);
INSERT INTO `station` VALUES (16, '团结南路', '西安', NULL, NULL);
INSERT INTO `station` VALUES (17, '土门市场', '西安', NULL, NULL);
INSERT INTO `station` VALUES (18, '制药厂', '西安', NULL, NULL);
INSERT INTO `station` VALUES (19, '西钞广场', '西安', NULL, NULL);
INSERT INTO `station` VALUES (20, '汉城路', '西安', NULL, NULL);
INSERT INTO `station` VALUES (21, '铁一中东校区', '西安', NULL, NULL);
INSERT INTO `station` VALUES (22, '青龙北路东口', '西安', NULL, NULL);
INSERT INTO `station` VALUES (23, '沙坡', '西安', NULL, NULL);
INSERT INTO `station` VALUES (24, '交大南门', '西安', NULL, NULL);
INSERT INTO `station` VALUES (25, '西铁局', '西安', NULL, NULL);
INSERT INTO `station` VALUES (26, '太乙路', '西安', NULL, NULL);
INSERT INTO `station` VALUES (27, '建东街东段', '西安', NULL, NULL);
INSERT INTO `station` VALUES (28, '东南城角', '西安', NULL, NULL);
INSERT INTO `station` VALUES (29, '东门', '西安', NULL, NULL);
INSERT INTO `station` VALUES (30, '西门', '西安', NULL, NULL);
INSERT INTO `station` VALUES (31, '锅炉厂', '西安', NULL, NULL);
INSERT INTO `station` VALUES (32, '起重机厂', '西安', NULL, NULL);
INSERT INTO `station` VALUES (33, '钟楼', '西安', NULL, NULL);
INSERT INTO `station` VALUES (43, 'test1', '北京', NULL, NULL);
INSERT INTO `station` VALUES (51, 'test2', '西安', NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userID` int(36) NOT NULL AUTO_INCREMENT,
  `userName` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`userID`) USING BTREE,
  UNIQUE INDEX `index_userName`(`userName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10025 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (10025, 'z@z.cn', '12345678');

-- ----------------------------
-- View structure for line_view
-- ----------------------------
DROP VIEW IF EXISTS `line_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `line_view` AS select `bus`.`busName` AS `busName`,`station`.`sta_Name` AS `sta_Name`,`line`.`direction` AS `direction`,`line`.`sta_No` AS `sta_No`,`bus`.`busCity` AS `busCity` from ((`bus` join `line` on((`line`.`busID` = `bus`.`busID`))) join `station` on((`line`.`stationID` = `station`.`stationID`))) order by `bus`.`busID`,`line`.`direction` desc,`line`.`sta_No`;

SET FOREIGN_KEY_CHECKS = 1;
