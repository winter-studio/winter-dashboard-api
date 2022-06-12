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

 Date: 12/06/2022 16:26:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
  `deleted` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (20, NULL, 'dashboard', 'Dashboard', 'DashboardOutlined', NULL, NULL, 1, 0, 0, 1, 0);
INSERT INTO `menu` VALUES (21, 20, 'workplace', '工作台', NULL, NULL, 'dashboard/Workplace.vue', 2, 0, 1, 1, 0);
INSERT INTO `menu` VALUES (22, NULL, 'external', '项目文档', 'ApiOutlined', NULL, 'https://github.com/winter-studio/winter-dashboard-ui', 3, 0, 0, 9, 0);
INSERT INTO `menu` VALUES (23, NULL, 'exception', '异常页面', 'ExclamationCircleOutlined', NULL, NULL, 1, 0, 0, 7, 0);
INSERT INTO `menu` VALUES (24, 23, '403', '403', NULL, NULL, 'basic/exception/403.vue', 2, 0, 1, 8, 0);
INSERT INTO `menu` VALUES (25, 23, '404', '404', NULL, NULL, 'basic/exception/404.vue', 2, 0, 1, 9, 0);
INSERT INTO `menu` VALUES (26, 23, '500', '500', NULL, NULL, 'basic/exception/500.vue', 2, 0, 1, 11, 0);
INSERT INTO `menu` VALUES (27, NULL, 'frame', '外部页面', 'LinkOutlined', NULL, NULL, 1, 0, 0, 6, 0);
INSERT INTO `menu` VALUES (28, 27, 'naive-ui', 'NaiveUi', 'BookOutlined', NULL, 'https://www.naiveui.com', 4, 0, 1, 1, 0);
INSERT INTO `menu` VALUES (29, NULL, 'result', '结果页面', 'CheckCircleOutlined', NULL, NULL, 1, 0, 0, 2, 0);
INSERT INTO `menu` VALUES (30, 29, 'success', '成功页', NULL, NULL, 'result/Success.vue', 2, 0, 1, 1, 0);
INSERT INTO `menu` VALUES (31, 29, 'fail', '失败页', NULL, NULL, 'result/Failure.vue', 2, 0, 1, 2, 0);
INSERT INTO `menu` VALUES (32, 29, 'info', '信息页', NULL, NULL, 'result/Info.vue', 2, 0, 1, 3, 0);
INSERT INTO `menu` VALUES (33, NULL, 'setting', '设置页面', 'SettingOutlined', NULL, NULL, 1, 0, 0, 5, 0);
INSERT INTO `menu` VALUES (34, 33, 'account', '个人设置', NULL, NULL, 'setting/account/Account.vue', 2, 0, 1, 1, 0);
INSERT INTO `menu` VALUES (35, 33, 'system', '系统设置', NULL, NULL, 'setting/system/System.vue', 2, 0, 1, 2, 0);
INSERT INTO `menu` VALUES (36, NULL, 'system', '系统设置', 'AppstoreAddOutlined', NULL, NULL, 1, 0, 0, 4, 0);
INSERT INTO `menu` VALUES (37, 36, 'menu', '菜单管理', 'MenuOutlined', NULL, 'system/menu/Menu.vue', 2, 0, 1, 1, 0);
INSERT INTO `menu` VALUES (38, NULL, 'about', '关于', 'ProjectOutlined', 'new', 'About.vue', 2, 0, 1, 3, 0);
INSERT INTO `menu` VALUES (39, NULL, 'blog', '博客', 'BookOutlined', NULL, 'https://www.iamkyun.com', 3, 0, 1, 12, 1);
INSERT INTO `menu` VALUES (40, NULL, 'test', '目录测试', NULL, NULL, NULL, 1, 0, 1, 13, 1);
INSERT INTO `menu` VALUES (41, 40, 'test', '测试1', 'AimOutlined', NULL, 'https://baidu.com', 3, 0, 1, 1, 1);
INSERT INTO `menu` VALUES (42, NULL, 'dddd', 'dddd', 'AccountBookOutlined', NULL, NULL, 1, 0, 1, 14, 1);
INSERT INTO `menu` VALUES (43, NULL, '22', '22', NULL, NULL, NULL, 1, 0, 1, 15, 1);
INSERT INTO `menu` VALUES (44, NULL, '123', '213', NULL, NULL, NULL, 1, 0, 1, 16, 1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色代码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'Administrator', 'admin');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `role_id` int UNSIGNED NOT NULL,
  `menu_id` int NOT NULL,
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (1, 20);
INSERT INTO `role_menu` VALUES (1, 21);
INSERT INTO `role_menu` VALUES (1, 22);
INSERT INTO `role_menu` VALUES (1, 23);
INSERT INTO `role_menu` VALUES (1, 24);
INSERT INTO `role_menu` VALUES (1, 25);
INSERT INTO `role_menu` VALUES (1, 26);
INSERT INTO `role_menu` VALUES (1, 27);
INSERT INTO `role_menu` VALUES (1, 28);
INSERT INTO `role_menu` VALUES (1, 29);
INSERT INTO `role_menu` VALUES (1, 30);
INSERT INTO `role_menu` VALUES (1, 31);
INSERT INTO `role_menu` VALUES (1, 32);
INSERT INTO `role_menu` VALUES (1, 33);
INSERT INTO `role_menu` VALUES (1, 34);
INSERT INTO `role_menu` VALUES (1, 35);
INSERT INTO `role_menu` VALUES (1, 36);
INSERT INTO `role_menu` VALUES (1, 37);
INSERT INTO `role_menu` VALUES (1, 38);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_u_username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$12$iIhN5Q3hUHRYD9k4t5oQWux1YPG5CYl/acjUHXDZNhbUUMLYic0SW', '2022-05-23 22:26:38', 0);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` bigint UNSIGNED NOT NULL COMMENT '用户ID',
  `role_id` int UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1);

SET FOREIGN_KEY_CHECKS = 1;
