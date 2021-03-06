/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : winter-dashboard

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 23/07/2022 11:11:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict`  (
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典代码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '字典名称',
  PRIMARY KEY (`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict
-- ----------------------------

-- ----------------------------
-- Table structure for dict_item
-- ----------------------------
DROP TABLE IF EXISTS `dict_item`;
CREATE TABLE `dict_item`  (
  `dict_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'dict.code',
  `key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'key',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'value',
  PRIMARY KEY (`dict_code`, `key`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_item
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `parent_id` int UNSIGNED NULL DEFAULT NULL COMMENT '父节点',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '路径',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `tags` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '额外标识',
  `data` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '页面组件/链接',
  `type` tinyint(1) NOT NULL COMMENT '组件类型(1:dir/2:view/3:link/4:iframe)',
  `hidden` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否隐藏',
  `keep_alive` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否缓存',
  `sort` smallint NOT NULL COMMENT '排序(基于同级)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (20, NULL, 'dashboard', 'Dashboard', 'DashboardOutlined', NULL, NULL, 1, 0, 0, 1);
INSERT INTO `menu` VALUES (21, 20, 'workplace', '工作台', NULL, NULL, 'dashboard/Workplace.vue', 2, 0, 1, 1);
INSERT INTO `menu` VALUES (36, NULL, 'system', '系统设置', 'AppstoreAddOutlined', NULL, NULL, 1, 0, 0, 4);
INSERT INTO `menu` VALUES (37, 36, 'menu', '菜单管理', 'MenuOutlined', NULL, 'system/menu/Menu.vue', 2, 0, 1, 1);
INSERT INTO `menu` VALUES (38, NULL, 'about', '关于', 'ProjectOutlined', 'new', 'About.vue', 2, 0, 1, 6);
INSERT INTO `menu` VALUES (45, 36, 'users', '用户管理', 'UsergroupAddOutlined', NULL, 'system/user/UserList.vue', 2, 0, 1, 2);
INSERT INTO `menu` VALUES (46, 36, 'role', '角色管理', 'UserSwitchOutlined', NULL, 'system/role/Role.vue', 2, 0, 1, 3);
INSERT INTO `menu` VALUES (47, NULL, 'dev', '开发管理', 'CodeOutlined', NULL, NULL, 1, 0, 1, 5);
INSERT INTO `menu` VALUES (48, 47, 'design', '页面设计', 'FormOutlined', NULL, 'system/dev/design/Designer.vue', 2, 0, 1, 1);
INSERT INTO `menu` VALUES (49, 36, 'dict', '字典管理', 'ReadOutlined', NULL, 'system/dict/Dict.vue', 2, 0, 1, 4);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色代码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', 'admin');
INSERT INTO `role` VALUES (2, '维修员', 'repairer');
INSERT INTO `role` VALUES (3, '顾客', 'customer');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `role_id` int UNSIGNED NOT NULL,
  `menu_id` int NOT NULL,
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 20);
INSERT INTO `role_menu` VALUES (1, 21);
INSERT INTO `role_menu` VALUES (1, 36);
INSERT INTO `role_menu` VALUES (1, 37);
INSERT INTO `role_menu` VALUES (1, 38);
INSERT INTO `role_menu` VALUES (1, 45);
INSERT INTO `role_menu` VALUES (1, 46);
INSERT INTO `role_menu` VALUES (1, 47);
INSERT INTO `role_menu` VALUES (1, 48);
INSERT INTO `role_menu` VALUES (1, 49);

-- ----------------------------
-- Table structure for upload_file
-- ----------------------------
DROP TABLE IF EXISTS `upload_file`;
CREATE TABLE `upload_file`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `related_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '关联ID',
  `related_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '关联类型(Minio Bucket)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件存储路径',
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件原始名称',
  `suffix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '扩展名',
  `content_length` bigint NOT NULL DEFAULT 0 COMMENT '文件大小',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `deleted` tinyint NOT NULL DEFAULT 0 COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文件上传' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of upload_file
-- ----------------------------
INSERT INTO `upload_file` VALUES (3, 1, 'maintenance', '/image/1541286766131216384png', 'null.png', 'png', 0, NULL, '2022-06-27 13:06:15', 0);
INSERT INTO `upload_file` VALUES (4, 1, 'maintenance', '/image/1541289809987960832.png', 'null.png', 'png', 0, NULL, '2022-06-27 13:18:21', 0);
INSERT INTO `upload_file` VALUES (5, 1, 'maintenance', '/image/1541290274108669952.png', 'null.png', 'png', 0, NULL, '2022-06-27 13:20:42', 0);
INSERT INTO `upload_file` VALUES (6, 1, 'maintenance', '/image/1541306506480386048.png', 'null.png', 'png', 20192, NULL, '2022-06-27 14:24:42', 0);
INSERT INTO `upload_file` VALUES (7, 100001, 'maintenance', '/image/1541967883352080384.jpg', 'XJHBCFpvE6J36bilbekTDIdF55lkrv_PZyt6tm7n8iXVwGTVDXmS1wpnqoZic1Y9.jpg', 'jpg', 204948, 2, '2022-06-29 10:12:46', 0);
INSERT INTO `upload_file` VALUES (8, 100003, 'maintenance', '/image/1542164121083084800.jpg', 'eVwWaUTTqUbuGvaDLbOQifVyuudtVRK1jEmnO1p6Trh1oPbHtocdqRC6m-NI2dgU.jpg', 'jpg', 204948, 2, '2022-06-29 23:12:33', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0,
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '微信openId',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像url',
  `mobile` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话号码',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '状态(0:正常)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_u_username`(`username` ASC) USING BTREE,
  UNIQUE INDEX `idx_u_mobile`(`mobile` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$12$iIhN5Q3hUHRYD9k4t5oQWux1YPG5CYl/acjUHXDZNhbUUMLYic0SW', '2022-07-18 09:43:17', 0, NULL, '管理员', 'http://dashboard.iamkyun.com:9000/userprofile/avatar/1548845829928976384.jpg', '18675802286', '0');
INSERT INTO `user` VALUES (2, 'admin2', '$2a$12$iIhN5Q3hUHRYD9k4t5oQWux1YPG5CYl/acjUHXDZNhbUUMLYic0SW', '2022-07-15 10:23:24', 0, NULL, '管理员2', 'https://awss3.iamkyun.com:8443/profile/default_user_profile.svg', '18675802236', '0');
INSERT INTO `user` VALUES (10, 'test', '$2a$12$WcXy5MNxLUR1hRS99eKRxuGq4FWOCDV56D9QpYcjvS54cn0qWE6Fi', '2022-07-14 17:57:19', 1, NULL, '测试', 'http://dashboard.iamkyun.com:9000/userprofile/avatar/1547508762485653504.jpg', '12312312312', '0');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID',
  `role_id` int UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (2, 1);
INSERT INTO `user_role` VALUES (2, 2);
INSERT INTO `user_role` VALUES (10, 1);

SET FOREIGN_KEY_CHECKS = 1;
