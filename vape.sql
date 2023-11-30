/*
 Navicat Premium Data Transfer

 Source Server         : 本地MySql
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : vape

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 10/11/2023 18:03:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_news_article
-- ----------------------------
DROP TABLE IF EXISTS `t_news_article`;
CREATE TABLE `t_news_article`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章id',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章作者',
  `release_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `cover` longblob NULL COMMENT '文章封面',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_news_article
-- ----------------------------

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
INSERT INTO `t_sys_menu` VALUES ('0', '运维管理', '101', '/', 0, '1588357381643079681', '', NULL, 'user001', '2022-11-07', 0, 'icon-yunweiguanli');
INSERT INTO `t_sys_menu` VALUES ('1588357381643079681', '菜单管理', '101', '/menu', 0, '1588364975791181826', NULL, NULL, NULL, NULL, 0, 'icon-caidan');
INSERT INTO `t_sys_menu` VALUES ('0', '数据管理', '101', '/', 0, '1588365140849627138', '', NULL, 'user001', '2022-11-07', 0, 'icon-guanli');
INSERT INTO `t_sys_menu` VALUES ('1588357381643079681', '用户管理', '101', '/user', 0, '1588365321745764354', NULL, NULL, NULL, NULL, 0, 'icon-01_yonghuguanli');
INSERT INTO `t_sys_menu` VALUES ('1588357381643079681', '角色管理', '101', '/role', 0, '1588365346307608577', NULL, NULL, NULL, NULL, 0, 'icon-jiaoseguanli');
INSERT INTO `t_sys_menu` VALUES ('1588365140849627138', '商品管理', '101', '/money', 0, '1588365463773286401', NULL, NULL, NULL, NULL, 0, 'icon-shichanghejingjixiaoyi');
INSERT INTO `t_sys_menu` VALUES ('1588365140849627138', '文章管理', '101', '/article', 0, '1588365514721497090', '', NULL, '', NULL, 0, 'icon-dangyuanyidong');
INSERT INTO `t_sys_menu` VALUES ('1588365140849627138', '基础管理', '101', '/plant', 0, '1588365541757980673', NULL, NULL, NULL, NULL, 0, 'icon-zhongzhiguanli');
INSERT INTO `t_sys_menu` VALUES ('1588365140849627138', '订单管理', '101', '/breed', 0, '1588365567355817985', '', NULL, '', NULL, 0, 'icon-4yangzhichang');
INSERT INTO `t_sys_menu` VALUES ('1588365140849627138', '库存管理', '101', '/building', 0, '1588365567355817995', NULL, NULL, NULL, NULL, 0, 'icon-home-full');

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

SET FOREIGN_KEY_CHECKS = 1;
