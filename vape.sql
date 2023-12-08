/*
 Navicat Premium Data Transfer

 Source Server         : 本地服务器mysql
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 192.168.0.160:3306
 Source Schema         : vape

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 07/12/2023 16:11:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_commodity
-- ----------------------------
DROP TABLE IF EXISTS `t_commodity`;
CREATE TABLE `t_commodity`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品id',
  `type_id` int NULL DEFAULT NULL COMMENT '商品类别id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `information` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品信息描述',
  `image` mediumblob NULL COMMENT '商品图片',
  `is_del` int NULL DEFAULT NULL COMMENT '删除标识 0：否，1：是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_commodity
-- ----------------------------
INSERT INTO `t_commodity` VALUES ('123456', 1, 'TH2-SE', 'Borosilicate Glass Cartridge', NULL, NULL);
INSERT INTO `t_commodity` VALUES ('1732659441536446466', 2, '商品名称', '商品信息描述', NULL, 0);
INSERT INTO `t_commodity` VALUES ('1732659897746591746', 2, '商品名称', '商品信息描述', NULL, 0);

-- ----------------------------
-- Table structure for t_commodity_type
-- ----------------------------
DROP TABLE IF EXISTS `t_commodity_type`;
CREATE TABLE `t_commodity_type`  (
  `type_id` int NOT NULL COMMENT '商品类型id',
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品类型名称',
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_commodity_type
-- ----------------------------
INSERT INTO `t_commodity_type` VALUES (1, 'Cartridge');
INSERT INTO `t_commodity_type` VALUES (2, '510 Battery');
INSERT INTO `t_commodity_type` VALUES (3, 'Pod System');

-- ----------------------------
-- Table structure for t_news_article
-- ----------------------------
DROP TABLE IF EXISTS `t_news_article`;
CREATE TABLE `t_news_article`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章id',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章作者',
  `release_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `article_data` varchar(20000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章内容',
  `is_del` int NULL DEFAULT NULL COMMENT '删除标识 0：否，1：是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_news_article
-- ----------------------------
INSERT INTO `t_news_article` VALUES ('1731501169710309378', '测试保存', 'admin', '2023-12-04 10:30:23', '<p>模拟 Ajax 异步设置内容sasas</p>', 0);
INSERT INTO `t_news_article` VALUES ('1731592154234613761', '测试是', 'admin', '2023-12-04 16:31:55', '<p>模拟 Ajax 异步设置内容ces</p>', 0);
INSERT INTO `t_news_article` VALUES ('1731850487059206145', 'a', 'admin', '2023-12-05 09:38:27', '<p>cs</p>', 1);
INSERT INTO `t_news_article` VALUES ('1731850516213813250', 'v', 'admin', '2023-12-05 09:38:34', '<p>cs</p>', 0);
INSERT INTO `t_news_article` VALUES ('1731850539186016258', 'vs', 'admin', '2023-12-05 09:38:39', '<p>sss</p>', 0);
INSERT INTO `t_news_article` VALUES ('1731850556772732930', 'ss', 'admin', '2023-12-05 09:38:44', '<p>hellosss</p>', 0);
INSERT INTO `t_news_article` VALUES ('1731850577014444033', 'asasas', 'admin', '2023-12-05 09:38:48', '<p>helloasssss</p>', 0);
INSERT INTO `t_news_article` VALUES ('1731850613160955906', 'ssss', 'admin', '2023-12-05 09:38:57', '<p>sa</p>', 0);
INSERT INTO `t_news_article` VALUES ('1731850634367356930', 'cc', 'admin', '2023-12-05 09:39:02', '<p>helloa</p>', 0);
INSERT INTO `t_news_article` VALUES ('1731850655309516802', 'aaaa', 'admin', '2023-12-05 09:39:07', '<p>ccccc</p>', 1);
INSERT INTO `t_news_article` VALUES ('1731862818958958594', '图片保存', 'admin', '2023-12-05 16:10:06', '<p style=\"text-align: center;\"> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<img src=\"http://192.168.0.160:62001/api/file/image/ef8e7309-fb0f-4bc3-bb8f-3478e5f30cda.jpg\" alt=\"ef8e7309-fb0f-4bc3-bb8f-3478e5f30cda.jpg\" data-href=\"http://192.168.0.160:62001/api/file/image/ef8e7309-fb0f-4bc3-bb8f-3478e5f30cda.jpg\" style=\"width: 956.00px;height: 612.78px;\"></p>', 0);
INSERT INTO `t_news_article` VALUES ('1731878053300252673', '水水水水水水', 'admin', '2023-12-05 11:27:59', '<p style=\"text-align: center;\">sssssssssss</p>', 0);
INSERT INTO `t_news_article` VALUES ('1731985606042529794', NULL, 'admin', '2023-12-05 18:35:22', '<p>hello</p>', 1);

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu`  (
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '许可',
  `route` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由',
  `is_show` int NULL DEFAULT NULL COMMENT '是否显示 0否，1是',
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` date NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` date NULL DEFAULT NULL COMMENT '修改时间',
  `is_del` int NULL DEFAULT NULL COMMENT '删除标识 0：否，1：是',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统菜单表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES ('0', '运维管理', '101', '/', 0, '1588357381643079681', '', NULL, 'user001', '2022-11-07', 0, 'icon-wxbzhuye');
INSERT INTO `t_sys_menu` VALUES ('0', '数据管理', '101', '/', 0, '1588365140849627138', '', NULL, 'user001', '2022-11-07', 0, 'icon-all');
INSERT INTO `t_sys_menu` VALUES ('1588357381643079681', '用户管理', '101', '/user', 0, '1588365321745764354', NULL, NULL, NULL, NULL, 0, 'icon-account');
INSERT INTO `t_sys_menu` VALUES ('1588357381643079681', '角色管理', '101', '/role', 0, '1588365346307608577', NULL, NULL, NULL, NULL, 0, 'icon-account');
INSERT INTO `t_sys_menu` VALUES ('1588365140849627138', '商品管理', '101', '/commodity', 0, '1588365463773286401', NULL, NULL, NULL, NULL, 0, 'icon-shangpin');
INSERT INTO `t_sys_menu` VALUES ('1588365140849627138', '文章管理', '101', '/article', 0, '1588365514721497090', '', NULL, '', NULL, 0, 'icon-text');
INSERT INTO `t_sys_menu` VALUES ('1588365140849627138', '基础管理', '101', '/plant', 0, '1588365541757980673', NULL, NULL, NULL, NULL, 0, 'icon-wxbbaobiao');
INSERT INTO `t_sys_menu` VALUES ('1588365140849627138', '订单管理', '101', '/breed', 0, '1588365567355817985', '', NULL, '', NULL, 0, 'icon-dingdanjihe');
INSERT INTO `t_sys_menu` VALUES ('1588365140849627138', '库存管理', '101', '/building', 0, '1588365567355817995', NULL, NULL, NULL, NULL, 0, 'icon-cangkukucun');

-- ----------------------------
-- Table structure for t_sys_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu_role`;
CREATE TABLE `t_sys_menu_role`  (
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单id',
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` date NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` date NULL DEFAULT NULL COMMENT '修改时间',
  `is_del` int NULL DEFAULT NULL COMMENT '删除标识 0：否，1：是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单角色关联表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_sys_menu_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `desc_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述信息',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` date NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` date NULL DEFAULT NULL COMMENT '修改时间',
  `is_del` int NULL DEFAULT NULL COMMENT '删除标识 0：否，1：是',
  `menu_list` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单id列表',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('1590553982544007188', '种植管理员', 1, '管理种植模块角色', 0, NULL, '2023-01-12', NULL, '2023-01-10', 0, '1588365140849627138,1588365567355817985,1588365541757980673');
INSERT INTO `t_sys_role` VALUES ('1613001299247157249', '用户管理员', 3, '用户管理', NULL, NULL, '2023-01-12', NULL, '2023-01-11', 0, '1588357381643079681,1588365140849627138');
INSERT INTO `t_sys_role` VALUES ('1613721920914522113', '超级管理员', NULL, '所有模块权限', NULL, NULL, '2023-01-15', NULL, NULL, 0, '1588357381643079681,1588365567355817985,1588365567355817995,1588365514721497090,1588365321745764354,1588365541757980673,1588365346307608577,1588365140849627138,1588365463773286401');
INSERT INTO `t_sys_role` VALUES ('1613739234832359426', '党员管理', NULL, '党员管理', NULL, NULL, '2023-01-13', NULL, NULL, 0, '1588357381643079681,1588365514721497090,1588365140849627138');

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别 0其他，1男，2女',
  `birth_day` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生日',
  `depart_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` date NULL DEFAULT NULL COMMENT '修改时间',
  `is_del` int NULL DEFAULT NULL COMMENT '删除标识 0：否，1：是',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1590593913016602626', '4', '$2a$10$tGk2MRPQoU94X6jw8NxWRe9nUQXfu.kFpLUwSApEQq7oazuO4RTq2', '4', '4', '4', '4', '4', '4', NULL, NULL, '2022-11-22 10:55:52', NULL, NULL, 1, '1590553982544007188');
INSERT INTO `t_sys_user` VALUES ('1592408720489345025', 'user001', '$2a$10$krmdoOJqylkeJLWH.A5H1uRpXKzlJvH.07Fcz7GBmXM0dB.7bOObu', '宝安区', '17829837382', '', '', '', '小米', NULL, '13576154061xjx@gmail.com', '2023-02-07 15:45:46', NULL, NULL, 0, '1590553982544007188');
INSERT INTO `t_sys_user` VALUES ('1613374131223384065', 'user002', '$2a$10$/yb4X9EjORBcas5pYezPeuG39Fqs6hC5q/rrPvzYL58fA839i03V.', '江西', '1565541068', '', '', '', '玉溪', NULL, '1749512834@qq.com', '2023-01-13 11:14:03', NULL, '2023-01-12', 0, '1613721920914522113');
INSERT INTO `t_sys_user` VALUES ('1613721920914522113', 'admin', '$2a$10$nzEmrpGUXyPG5NBDOzmyaOJKunxMvzLG5GtdcNGnuzsY7Fcl8t7TC', '雾泽乡', '13576154000', '', '', '', '萧然', NULL, '1749512834@qq.com', '2022-11-22 10:55:56', NULL, NULL, 0, '1613721920914522113');
INSERT INTO `t_sys_user` VALUES ('1613739346195324930', 'user003', '$2a$10$qIlghp8naUH9E9jUofUlo.aoRaLlIfnb0ULx3TxL3Xw2ZB1y1a2rO', NULL, NULL, NULL, NULL, NULL, '小李', NULL, NULL, '2023-01-13 11:26:57', NULL, NULL, 0, '1613739234832359426');

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` date NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` date NULL DEFAULT NULL COMMENT '修改时间',
  `is_del` int NULL DEFAULT NULL COMMENT '删除标识 0：否，1：是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO `t_sys_user_role` VALUES ('1590256555679289346', '1592408720489345025', '1590553982544007188', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_village_grid
-- ----------------------------
DROP TABLE IF EXISTS `t_village_grid`;
CREATE TABLE `t_village_grid`  (
  `grid_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网格id',
  `village_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '村id',
  `village_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '村名称',
  `grid_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网格名称',
  `area` float NULL DEFAULT NULL COMMENT '面积',
  PRIMARY KEY (`grid_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_village_grid
-- ----------------------------
INSERT INTO `t_village_grid` VALUES ('1', '0', '湾坡村', NULL, 5.88);
INSERT INTO `t_village_grid` VALUES ('2', '1', '湾坡村', '第一网格', 1.97);
INSERT INTO `t_village_grid` VALUES ('3', '1', '湾坡村', '第二网格', 1.96);
INSERT INTO `t_village_grid` VALUES ('4', '1', '湾坡村', '第三网格', 1.96);
INSERT INTO `t_village_grid` VALUES ('5', '0', 'xx村', NULL, 10);
INSERT INTO `t_village_grid` VALUES ('6', '5', 'xx村', '第一网格', 5);
INSERT INTO `t_village_grid` VALUES ('7', '5', 'xx村', '第二网格', 5);

SET FOREIGN_KEY_CHECKS = 1;
