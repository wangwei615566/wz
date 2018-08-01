/*
 Navicat Premium Data Transfer

 Source Server         : localhost 2
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : pos

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 29/06/2018 19:47:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app_info
-- ----------------------------
DROP TABLE IF EXISTS `app_info`;
CREATE TABLE `app_info` (
  `id` int(11) NOT NULL,
  `version_name` varchar(10) DEFAULT NULL COMMENT '当前app版本号',
  `remark` varchar(256) DEFAULT NULL COMMENT 'app更新内容',
  `mobile_type` char(1) DEFAULT NULL COMMENT '手机系统1ios 2 android',
  `update_url` varchar(255) DEFAULT NULL COMMENT '更新文件地址',
  `force_update` varchar(2) NOT NULL DEFAULT '0' COMMENT '强制更新 1 强制更新 0 不强制更新',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入库时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='App版本信息表';

-- ----------------------------
-- Records of app_info
-- ----------------------------
BEGIN;
INSERT INTO `app_info` VALUES (0, '1.0.3', '新版本~~~', '2', 'http://www.czwxian.com/apk/1.0.3/czqb_1.0.3.apk', '0', '2017-09-29 14:27:16');
INSERT INTO `app_info` VALUES (1, '1.0.4', '新版本1.0.4', '2', 'http://www.czwxian.com/apk/1.0.4/czqb_1.0.4.apk', '0', '2017-09-29 14:32:10');
COMMIT;

-- ----------------------------
-- Table structure for bank_card
-- ----------------------------
DROP TABLE IF EXISTS `bank_card`;
CREATE TABLE `bank_card` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `bank_name` varchar(32) DEFAULT NULL COMMENT '开户行',
  `card_no` varchar(32) DEFAULT NULL COMMENT '银行卡号',
  `phone` varchar(20) DEFAULT NULL COMMENT '预留手机号',
  `bank_code` varchar(20) DEFAULT NULL COMMENT '银行编码',
  `bank_province` varchar(20) DEFAULT NULL COMMENT '开户行所在省编码',
  `bank_city` varchar(20) DEFAULT NULL COMMENT '开户行所在城市编码',
  `use_state` tinyint(4) DEFAULT '0' COMMENT '使用状态 0-非默认使用 1-默认使用',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银行卡信息表';

-- ----------------------------
-- Table structure for bank_list
-- ----------------------------
DROP TABLE IF EXISTS `bank_list`;
CREATE TABLE `bank_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bank_name` varchar(32) DEFAULT NULL COMMENT '银行名称',
  `bank_code` varchar(10) DEFAULT NULL COMMENT '银行编码',
  `state` tinyint(4) DEFAULT '1' COMMENT '状态 1正常 0禁用',
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银行列表';

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL COMMENT ' 商品名称',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `spec` varchar(64) DEFAULT NULL COMMENT '规格',
  `type` tinyint(4) DEFAULT NULL COMMENT '商品类型：1普通商品 2虚拟商品',
  `state` tinyint(4) DEFAULT NULL COMMENT '状态 1正常 0下架',
  `profit_amount` decimal(10,2) DEFAULT NULL COMMENT '推广费用',
  `udpate_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Table structure for goods_detail
-- ----------------------------
DROP TABLE IF EXISTS `goods_detail`;
CREATE TABLE `goods_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(20) NOT NULL COMMENT '商品id',
  `detail_type` tinyint(4) DEFAULT NULL COMMENT '详情类型 1商品介绍 2商品参数 3购买须知',
  `description` text COMMENT '描述信息',
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品详情表';

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(32) DEFAULT NULL COMMENT '订单号',
  `shipping_addr_id` bigint(20) DEFAULT NULL COMMENT '收货地址id',
  `goods_num` int(11) DEFAULT NULL COMMENT '商品数量',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '订单金额',
  `coupon_amount` decimal(10,2) DEFAULT NULL COMMENT '优惠券金额',
  `state` tinyint(4) DEFAULT NULL COMMENT '订单状态 1待付款 2.待发货 3.待收货 4.已完成 5.商品已激活 0.支付失败',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注信息',
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `goods_num` int(11) DEFAULT NULL COMMENT '商品数量',
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情表';

-- ----------------------------
-- Table structure for profit_agent
-- ----------------------------
DROP TABLE IF EXISTS `profit_agent`;
CREATE TABLE `profit_agent` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `level_id` bigint(20) DEFAULT NULL COMMENT '分润等级id',
  `leader_id` bigint(20) DEFAULT NULL COMMENT '上级代理id',
  `use_state` tinyint(4) DEFAULT NULL COMMENT '使用状态 1-启用 0-禁用',
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户会员信息表';

-- ----------------------------
-- Table structure for profit_amount
-- ----------------------------
DROP TABLE IF EXISTS `profit_amount`;
CREATE TABLE `profit_amount` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `total` decimal(10,2) DEFAULT NULL COMMENT '总金额',
  `can_cashed` decimal(10,2) DEFAULT NULL COMMENT '可提现',
  `cashed` decimal(10,2) DEFAULT NULL COMMENT '已提现',
  `frozen` decimal(10,2) DEFAULT NULL COMMENT '已冻结',
  `state` tinyint(4) DEFAULT '1' COMMENT '账户状态 1启用 0冻结',
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户分润资金表';

-- ----------------------------
-- Table structure for profit_cash_log
-- ----------------------------
DROP TABLE IF EXISTS `profit_cash_log`;
CREATE TABLE `profit_cash_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `account_id` bigint(20) NOT NULL COMMENT '提现账号id',
  `cash_way` tinyint(4) DEFAULT NULL COMMENT '提现类型 1银行卡 2支付宝 3微信',
  `account_no` varchar(32) DEFAULT NULL COMMENT '提现账号',
  `account_name` varchar(32) DEFAULT NULL COMMENT '提现账号名称',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '提现金额',
  `fee` decimal(10,2) DEFAULT NULL COMMENT '提现手续费',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分润提现记录表';

-- ----------------------------
-- Table structure for profit_level
-- ----------------------------
DROP TABLE IF EXISTS `profit_level`;
CREATE TABLE `profit_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '会员名称',
  `level` tinyint(4) DEFAULT '1' COMMENT '分润等级',
  `rate` decimal(10,2) DEFAULT NULL COMMENT '推广费用分润率',
  `second_rate` decimal(10,2) DEFAULT NULL COMMENT '推广费用二级分润率',
  `reg_amount` decimal(10,2) DEFAULT NULL COMMENT '注册分润金额',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='分润会员等级表';

-- ----------------------------
-- Records of profit_level
-- ----------------------------
BEGIN;
INSERT INTO `profit_level` VALUES (1, '普通会员', 1, 0.30, 0.01, 20.00, NULL, '2018-06-28 15:04:45', '2018-06-28 15:04:50');
INSERT INTO `profit_level` VALUES (2, '钻石vip', 2, 0.80, 0.10, 20.00, NULL, '2018-06-28 15:18:05', '2018-06-28 15:18:10');
INSERT INTO `profit_level` VALUES (3, '至尊vip', 3, 1.00, 0.20, 20.00, NULL, '2018-06-28 15:18:35', '2018-06-28 15:18:38');
COMMIT;

-- ----------------------------
-- Table structure for profit_log
-- ----------------------------
DROP TABLE IF EXISTS `profit_log`;
CREATE TABLE `profit_log` (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `agent_id` bigint(20) NOT NULL COMMENT '分润人id',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '分润金额',
  `rate` decimal(10,2) DEFAULT NULL COMMENT '分润率',
  `remark` varchar(256) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分润记录表';

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '配置名称',
  `code` varchar(32) DEFAULT NULL COMMENT '参数名称',
  `value` varchar(64) DEFAULT NULL COMMENT '参数对应的值',
  `state` tinyint(4) DEFAULT NULL COMMENT '状态 1启用 0禁用',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注说明',
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
INSERT INTO `sys_config` VALUES (1, '版本控制', 'check_version', '{\"state\": \"10\",\"version\": \"1.0.1\"}', 1, 'state是否启用10启用20禁用  version版本号', '2018-06-29 17:23:38', '2018-06-29 17:23:42');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type_code` varchar(64) DEFAULT NULL COMMENT '类型编码',
  `type_name` varchar(32) DEFAULT NULL COMMENT '类型名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(30) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `type_code` (`type_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='数据字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES (2, 'PRODUCT_TYPE', '借款类型', 20, '借款类型');
INSERT INTO `sys_dict` VALUES (6, 'RANK_TYPE', '授信等级', 10, '授信等级');
INSERT INTO `sys_dict` VALUES (7, 'CREDIT_TYPE', '额度类型', 30, '额度类型');
INSERT INTO `sys_dict` VALUES (8, 'CONTACT_RELATION', '联系人关系类型', 40, '联系人关系类型');
INSERT INTO `sys_dict` VALUES (9, 'EDUCATIONAL_STATE', '教育程度', 36, '教育程度');
INSERT INTO `sys_dict` VALUES (10, 'MARITAL_STATE', '婚姻状况', 28, '婚姻状况');
INSERT INTO `sys_dict` VALUES (11, 'LIVE_TIME', '居住时长', 27, '居住时长');
INSERT INTO `sys_dict` VALUES (12, 'WORK_TIME', '工作时长', 26, '工作时长');
INSERT INTO `sys_dict` VALUES (13, 'SALARY_RANGE', '月薪范围', 25, '月薪范围');
INSERT INTO `sys_dict` VALUES (14, 'BANK_TYPE', '银行代码', 24, '银行代码');
INSERT INTO `sys_dict` VALUES (15, 'SYSTEM_TYPE', '系统参数类别', 41, '系统参数类别');
INSERT INTO `sys_dict` VALUES (16, 'URGE_WAY', '催收方式', 42, '催收方式');
INSERT INTO `sys_dict` VALUES (18, 'URGE_STATE', '催收状态', 43, '催收状态');
INSERT INTO `sys_dict` VALUES (19, 'ACCESS_CODE', '访问码时限', 44, '访问码时限');
INSERT INTO `sys_dict` VALUES (20, 'THIRD_DATA_SCENE', '借款场景', 45, '借款场景');
INSERT INTO `sys_dict` VALUES (21, 'KINSFOLK_RELATION', '直系联系人', 46, '直系联系人');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `item_code` varchar(64) DEFAULT NULL COMMENT '参数编码',
  `item_value` varchar(32) DEFAULT NULL COMMENT '参数值',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级ID',
  `state` varchar(2) NOT NULL DEFAULT '10' COMMENT '状态  10 有效 20 无效',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `item_code` (`item_code`) USING BTREE,
  KEY `parent_id_index` (`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8 COMMENT='字典表数据明细';

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_detail` VALUES (1, '01', '现金贷', 2, '10');
INSERT INTO `sys_dict_detail` VALUES (2, '01', 'A', 6, '10');
INSERT INTO `sys_dict_detail` VALUES (3, '02', 'B', 6, '10');
INSERT INTO `sys_dict_detail` VALUES (4, '03', 'C', 6, '10');
INSERT INTO `sys_dict_detail` VALUES (5, '01', '房贷额度', 7, '10');
INSERT INTO `sys_dict_detail` VALUES (6, '02', '车贷额度', 7, '10');
INSERT INTO `sys_dict_detail` VALUES (10, '01', '配偶', 21, '10');
INSERT INTO `sys_dict_detail` VALUES (11, '02', '子女', 21, '10');
INSERT INTO `sys_dict_detail` VALUES (12, '03', '父亲', 21, '10');
INSERT INTO `sys_dict_detail` VALUES (13, '04', '母亲', 21, '10');
INSERT INTO `sys_dict_detail` VALUES (14, '05', '同事', 8, '10');
INSERT INTO `sys_dict_detail` VALUES (15, '01', '初中及小学以下', 9, '10');
INSERT INTO `sys_dict_detail` VALUES (16, '02', '中专/职高/高中', 9, '10');
INSERT INTO `sys_dict_detail` VALUES (17, '03', '高职/大专', 9, '10');
INSERT INTO `sys_dict_detail` VALUES (18, '04', '本科', 9, '10');
INSERT INTO `sys_dict_detail` VALUES (19, '05', '硕士', 9, '10');
INSERT INTO `sys_dict_detail` VALUES (20, '06', '博士', 9, '10');
INSERT INTO `sys_dict_detail` VALUES (21, '07', '博士后', 9, '10');
INSERT INTO `sys_dict_detail` VALUES (22, '01', '未婚', 10, '10');
INSERT INTO `sys_dict_detail` VALUES (23, '02', '已婚', 10, '10');
INSERT INTO `sys_dict_detail` VALUES (24, '03', '离异', 10, '10');
INSERT INTO `sys_dict_detail` VALUES (25, '04', '丧偶', 10, '10');
INSERT INTO `sys_dict_detail` VALUES (26, '01', '半年以内', 11, '10');
INSERT INTO `sys_dict_detail` VALUES (27, '02', '半年到一年', 11, '10');
INSERT INTO `sys_dict_detail` VALUES (28, '03', '一年以上', 11, '10');
INSERT INTO `sys_dict_detail` VALUES (29, '01', '一年以下', 12, '10');
INSERT INTO `sys_dict_detail` VALUES (30, '02', '一年至三年', 12, '10');
INSERT INTO `sys_dict_detail` VALUES (31, '03', '三年至五年', 12, '10');
INSERT INTO `sys_dict_detail` VALUES (32, '04', '五年以上', 12, '10');
INSERT INTO `sys_dict_detail` VALUES (33, '01', '5千以下', 13, '10');
INSERT INTO `sys_dict_detail` VALUES (34, '02', '5千至1万', 13, '10');
INSERT INTO `sys_dict_detail` VALUES (35, '03', '1万至2万', 13, '10');
INSERT INTO `sys_dict_detail` VALUES (36, '04', '2万以上', 13, '10');
INSERT INTO `sys_dict_detail` VALUES (37, '01', '中国工商银行', 14, '10');
INSERT INTO `sys_dict_detail` VALUES (38, '02', '中国农业银行', 14, '10');
INSERT INTO `sys_dict_detail` VALUES (39, '03', '中国银行', 14, '10');
INSERT INTO `sys_dict_detail` VALUES (40, '04', '中国建设银行', 14, '10');
INSERT INTO `sys_dict_detail` VALUES (41, '05', '交通银行', 14, '10');
INSERT INTO `sys_dict_detail` VALUES (42, '06', '中信银行', 14, '10');
INSERT INTO `sys_dict_detail` VALUES (43, '07', '光大银行', 14, '10');
INSERT INTO `sys_dict_detail` VALUES (44, '08', '华夏银行', 14, '10');
INSERT INTO `sys_dict_detail` VALUES (45, '09', '民生银行', 14, '10');
INSERT INTO `sys_dict_detail` VALUES (46, '10', '广发银行', 14, '10');
INSERT INTO `sys_dict_detail` VALUES (47, '11', '平安银行', 14, '10');
INSERT INTO `sys_dict_detail` VALUES (48, '12', '招商银行', 14, '10');
INSERT INTO `sys_dict_detail` VALUES (49, '30', '协议参数', 15, '10');
INSERT INTO `sys_dict_detail` VALUES (50, '20', '业务参数', 15, '10');
INSERT INTO `sys_dict_detail` VALUES (51, '10', '系统关键参数', 15, '10');
INSERT INTO `sys_dict_detail` VALUES (52, '06', '朋友', 8, '10');
INSERT INTO `sys_dict_detail` VALUES (53, '40', 'H5参数', 15, '10');
INSERT INTO `sys_dict_detail` VALUES (54, '70', '大圣', 15, '10');
INSERT INTO `sys_dict_detail` VALUES (55, '80', '第三方', 15, '10');
INSERT INTO `sys_dict_detail` VALUES (56, '50', '备注参数', 15, '10');
INSERT INTO `sys_dict_detail` VALUES (57, '60', '短信参数', 15, '10');
INSERT INTO `sys_dict_detail` VALUES (58, '10', '电话', 16, '10');
INSERT INTO `sys_dict_detail` VALUES (59, '20', '邮件', 16, '10');
INSERT INTO `sys_dict_detail` VALUES (60, '30', '短信', 16, '10');
INSERT INTO `sys_dict_detail` VALUES (61, '40', '现场沟通', 16, '10');
INSERT INTO `sys_dict_detail` VALUES (62, '50', '其他', 16, '10');
INSERT INTO `sys_dict_detail` VALUES (66, '20', '催收中', 18, '10');
INSERT INTO `sys_dict_detail` VALUES (67, '30', '承诺还款', 18, '10');
INSERT INTO `sys_dict_detail` VALUES (68, '40', '催收成功', 18, '10');
INSERT INTO `sys_dict_detail` VALUES (69, '50', '坏账', 18, '10');
INSERT INTO `sys_dict_detail` VALUES (70, '01', '2小时', 19, '10');
INSERT INTO `sys_dict_detail` VALUES (71, '02', '12小时', 19, '10');
INSERT INTO `sys_dict_detail` VALUES (72, '03', '一天', 19, '10');
INSERT INTO `sys_dict_detail` VALUES (73, '04', '二天', 19, '10');
INSERT INTO `sys_dict_detail` VALUES (74, '05', '七天', 19, '10');
INSERT INTO `sys_dict_detail` VALUES (75, '06', '一个月', 19, '10');
INSERT INTO `sys_dict_detail` VALUES (76, '07', '三个月', 19, '10');
INSERT INTO `sys_dict_detail` VALUES (77, '08', '六个月', 19, '10');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sys_type` tinyint(3) DEFAULT '0' COMMENT '类型  0公用',
  `name` varchar(128) DEFAULT '' COMMENT '菜单名称',
  `parent_id` int(11) unsigned DEFAULT NULL COMMENT '父级ID',
  `href` varchar(512) DEFAULT '' COMMENT '链接地址',
  `icon_cls` varchar(512) DEFAULT '' COMMENT '图标',
  `sort` int(11) unsigned zerofill DEFAULT '00000000000' COMMENT '排序',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user` varchar(128) DEFAULT '' COMMENT '添加者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(128) DEFAULT '' COMMENT '修改者',
  `remark` varchar(256) DEFAULT '' COMMENT '备注',
  `is_delete` tinyint(2) DEFAULT NULL COMMENT '是否删除 ：0不删除，1删除',
  `is_menu` tinyint(2) DEFAULT '1' COMMENT '是否菜单 0否，1是',
  `scriptid` varchar(255) DEFAULT NULL COMMENT '脚本名称',
  `controller_name` varchar(255) DEFAULT NULL COMMENT '控制器名字',
  `leaf` tinyint(1) DEFAULT NULL COMMENT '是否为子节点  1 true 0 false',
  `level` tinyint(2) unsigned zerofill DEFAULT NULL COMMENT '树状层数据',
  `scriptid_old` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11303 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', 0, NULL, 'icon-xitongguanli', 00000000099, '2016-05-31 00:00:00', NULL, '2016-12-15 17:11:18', NULL, '1', 0, 1, 'sysManage', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (2, 0, '菜单管理', 1, NULL, 'icon-caidanguanli', 00000000030, NULL, NULL, '2016-08-02 09:36:12', NULL, '1', 0, 1, 'sysMenuManage', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (3, 0, '用户管理', 1, NULL, 'icon-yonghuguanli', 00000000010, NULL, NULL, '2016-08-02 09:36:27', NULL, '1', 0, 1, 'sysUserManage', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (4, 0, '角色管理', 1, NULL, 'icon-jiaoseguanli', 00000000020, NULL, NULL, '2016-08-02 09:36:53', NULL, '1', 0, 1, 'sysRoleManage', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (7, 0, '字典管理', 1, NULL, 'icon-zidianguanli', 00000000040, NULL, NULL, '2016-08-02 09:44:48', NULL, '1', 0, 1, 'sysDicManage', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11050, 0, '系统参数设置', 1, '', NULL, 00000000100, NULL, '', NULL, '', NULL, 0, 1, 'SystemParameterSettings', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11138, 0, '借款订单', 11159, '', NULL, 00000000050, NULL, '', '2017-03-31 14:19:47', '', ' 用户借款管理', 0, 1, 'LoanInformation', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11140, 0, '用户信息', 11161, '', NULL, 00000000010, NULL, '', '2017-03-07 19:59:12', '', '用户基本信息', 0, 1, 'UserBasicInformation', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11141, 0, '规则配置', 11153, '', 'icon', 00000000020, NULL, '', '2016-12-26 14:45:24', '', 'ds', 0, 1, 'ruleEngine', NULL, 1, 02, NULL);
INSERT INTO `sys_menu` VALUES (11146, 0, '规则类型绑定', 11153, '', NULL, 00000000030, NULL, '', '2017-04-25 15:43:06', '', '规则类型绑定', 0, 1, 'BorrowingRulesManagement', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11149, 0, '贷后管理', 0, '', NULL, 00000000052, NULL, '', '2017-03-04 16:12:53', '', NULL, 1, 1, 'LoanBackManege', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11151, 0, '表字段维护', 11153, '', NULL, 00000000010, NULL, '', '2017-01-11 14:48:42', '', NULL, 0, 1, 'FormFieldsToAdd', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11153, 0, '规则引擎', 0, '', 'icon-guizeshuoming', 00000000020, NULL, '', '2017-03-08 13:40:39', '', NULL, 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11154, 0, '规则匹配记录', 11153, '', NULL, 00000000040, NULL, '', '2017-04-25 17:35:13', '', NULL, 1, 1, 'RulesMatchResults', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11157, 0, '发布借款', 11159, '', NULL, 00000000002, NULL, '', '2017-03-21 09:30:59', '', NULL, 1, 1, 'TargetRelease', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11159, 0, '借款管理', 0, '', 'icon-jiekuanjilu', 00000000040, NULL, '', '2017-03-08 13:43:08', '', '借款管理', 0, 1, '', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11161, 0, '用户管理', 0, '', 'icon-yonghuxinxi', 00000000010, NULL, '', '2017-09-11 12:00:32', '', '用户信息', 0, 1, 'UserInformation', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11164, 0, '还款管理', 11149, '', '', 00000000010, NULL, '', '2017-03-04 14:24:10', '', NULL, 1, 1, 'PaymentManagt', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11165, 0, '催收管理', 11149, '', 'icon-cuishouguanli', 00000000020, NULL, '', '2017-03-04 11:37:01', '', NULL, 1, 1, 'CollectionManagt', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11168, 0, '用户认证信息', 11161, '', NULL, 00000000020, NULL, '', '2017-03-07 19:59:01', '', '用户认证信息', 0, 1, 'UserAuthenticationList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11171, 0, '还款计划', 11149, '', NULL, 00000000010, NULL, '', '2017-03-04 16:12:48', '', '还款计划', 1, 1, 'RepaymentPlanList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11172, 0, '还款记录', 11149, '', NULL, 00000000020, NULL, '', '2017-03-04 16:12:43', '', '还款记录', 1, 1, 'PaymentHistory', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11173, 0, '代理商管理', 1, '', NULL, 00000000060, NULL, '', '2017-03-03 13:59:24', '', '代理商管理', 1, 1, 'AgentManage', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11174, 0, '代理商管理', 0, '', 'icon-dailishang', 00000000011, NULL, '', '2017-03-08 13:44:18', '', '代理商管理-管理员', 0, 1, 'AgentManage', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11175, 0, '代理商管理', 11174, '', NULL, 00000000010, NULL, '', NULL, '', '代理商管理', 1, 1, 'AgentModuleAdmin', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11176, 0, '奖励获得记录', 11174, '', NULL, 00000000040, NULL, '', '2017-03-24 11:38:52', '', '奖励获得记录', 0, 1, 'ShareDetail', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11177, 0, '奖励资金账户', 11174, '', NULL, 00000000030, NULL, '', '2017-03-24 11:37:13', '', '奖励资金账户', 0, 1, 'CashCheck', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11178, 0, '奖励打款记录', 11174, '', NULL, 00000000050, NULL, '', '2017-03-24 11:38:43', '', '奖励打款记录', 0, 1, 'CashRecord', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11179, 0, '代理商清单', 11174, '', NULL, 00000000070, NULL, '', '2017-03-03 14:22:24', '', '代理商管理-代理商', 1, 1, 'AgentBranchManage', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11180, 0, '一级代理商管理', 0, '', 'icon-dailishang', 00000000012, NULL, '', '2017-03-08 13:44:32', '', '代理商管理-代理商', 0, 1, 'AgentBranchManage', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11181, 0, '代理商管理', 11180, '', NULL, 00000000010, NULL, '', NULL, '', '代理商管理', 0, 1, 'AgentModuleAdminBranch', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11182, 0, '奖励获得记录', 11180, '', NULL, 00000000030, NULL, '', '2017-04-05 19:18:19', '', '奖励获得记录', 0, 1, 'ShareDetailBranch', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11183, 0, '奖励资金账户', 11180, '', NULL, 00000000020, NULL, '', '2017-04-05 19:18:13', '', '奖励资金账户', 0, 1, 'CashCheckBranch', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11184, 0, '奖励打款记录', 11180, '', NULL, 00000000040, NULL, '', '2017-04-05 19:08:37', '', '奖励打款记录', 0, 1, 'CashRecordBranch', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11185, 0, '催收管理', 0, '', NULL, 00000000060, NULL, '', '2017-03-04 16:38:20', '', '催收管理', 1, 1, 'CollectionManage', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11186, 0, '催收人员管理', 11185, '', NULL, 00000000010, NULL, '', '2017-03-04 16:38:55', '', '催收人员管理', 1, 1, 'CollectionPersonnelManage', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11187, 0, '催收反馈意见', 11185, '', NULL, 00000000020, NULL, '', '2017-03-04 16:39:00', '', '催收反馈意见', 1, 1, 'CollectionFeedback', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11188, 0, '催收总订单列表', 11185, '', NULL, 00000000030, NULL, '', '2017-03-04 16:39:05', '', '催收总订单列表', 1, 1, 'CollectionTotalOrderList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11189, 0, '待催收记录列表', 11185, '', NULL, 00000000040, NULL, '', '2017-03-04 16:39:10', '', '待催收记录列表', 1, 1, 'CollectionRecordList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11190, 0, '我的订单', 11185, '', NULL, 00000000050, NULL, '', '2017-03-04 16:39:14', '', '我的订单', 1, 1, 'MyOrder', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11191, 0, '待催收列表', 11185, '', NULL, 00000000060, NULL, '', '2017-03-04 16:39:19', '', '待催收列表', 1, 1, 'WaitCollectionList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11192, 0, '催收中列表', 11185, '', NULL, 00000000070, NULL, '', '2017-03-04 16:39:24', '', '催收中列表', 1, 1, 'CollectionInList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11193, 0, '承诺还款列表', 11185, '', NULL, 00000000080, NULL, '', '2017-03-04 16:39:28', '', '承诺还款列表', 1, 1, 'CommitmentRepaymentList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11194, 0, '催收成功列表', 11185, '', NULL, 00000000090, NULL, '', '2017-03-04 16:39:33', '', '催收成功列表', 1, 1, 'CollectionSuccessList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11195, 0, '风控管理', 0, '', 'icon-zhuanyefengkong', 00000000021, NULL, '', '2017-03-08 13:42:47', '', '风控管理', 0, 1, 'RiskControlManage', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11196, 0, '待机审订单', 11195, '', NULL, 00000000010, NULL, '', '2017-03-04 15:49:22', '', '待机审订单列表', 0, 1, 'StandbyReviewList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11197, 0, '机审拒绝订单', 11195, '', NULL, 00000000020, NULL, '', NULL, '', '机审拒绝订单列表', 0, 1, 'RejectedOrderList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11198, 0, '人工复审', 11195, '', NULL, 00000000030, NULL, '', NULL, '', '人工复审', 0, 1, 'LoanApplicationManage', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11199, 0, '还款管理', 0, '', 'icon-qian', 00000000051, NULL, '', '2017-03-08 13:44:55', '', '还款管理', 0, 1, 'RepaymentManage', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11200, 0, '支付宝还款记录', 11199, '', NULL, 00000000050, NULL, '', '2017-03-22 11:56:40', '', '支付宝还款记录列表', 0, 1, 'AlipayPaymentList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11201, 0, '银行卡还款记录', 11199, '', NULL, 00000000060, NULL, '', '2017-03-22 11:56:49', '', '银行卡还款记录列表', 0, 1, 'BankCardPaymentList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11202, 0, '还款计划', 11199, '', NULL, 00000000010, NULL, '', '2017-03-21 11:54:46', '', '还款计划列表', 0, 1, 'RepaymentPlanList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11203, 0, '还款记录', 11199, '', NULL, 00000000020, NULL, '', '2017-03-22 16:05:46', '', '还款记录', 0, 1, 'PaymentHistory', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11204, 0, '催收人员管理', 0, '', 'icon-renyuan', 00000000060, NULL, '', '2017-03-08 13:47:30', '', '催收人员管理', 0, 1, 'CollectionPersonnelManage', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11205, 0, '催收员', 11204, '', NULL, 00000000010, NULL, '', '2017-03-21 15:23:42', '', '催收员列表', 0, 1, 'CollectionPersonnelList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11206, 0, '催收反馈', 11207, '', NULL, 00000000030, NULL, '', '2017-03-22 18:02:19', '', '催收反馈', 0, 1, 'CollectionFeedback', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11207, 0, '催收订单管理', 0, '', 'icon-dingdan', 00000000061, NULL, '', '2017-03-13 19:19:52', '', '催收订单管理', 0, 1, 'CollectionOrderListManage', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11208, 0, '催收总订单', 11207, '', NULL, 00000000010, NULL, '', NULL, '', '催收总订单列表', 0, 1, 'CollectionTotalOrderList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11209, 0, '待催收订单', 11207, '', NULL, 00000000020, NULL, '', NULL, '', '待催收记录列表', 0, 1, 'CollectionRecordList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11210, 0, '我的催收订单', 0, '', 'icon-wodedingdan', 00000000062, NULL, '', '2017-03-08 13:47:09', '', '我的催收订单管理', 0, 1, 'MyCollectionOrderManage', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11211, 0, '我的订单', 11210, '', NULL, 00000000010, NULL, '', NULL, '', '我的订单', 0, 1, 'MyOrder', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11212, 0, '待催收订单', 11210, '', NULL, 00000000020, NULL, '', NULL, '', '待催收列表', 0, 1, 'WaitCollectionList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11213, 0, '催收中订单', 11210, '', NULL, 00000000030, NULL, '', NULL, '', '催收中列表', 0, 1, 'CollectionInList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11214, 0, '承诺还款订单', 11210, '', NULL, 00000000040, NULL, '', NULL, '', '承诺还款列表', 0, 1, 'CommitmentRepaymentList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11215, 0, '催收成功订单', 11210, '', NULL, 00000000050, NULL, '', NULL, '', '催收成功列表', 0, 1, 'CollectionSuccessList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11216, 0, '放款订单', 11159, '', NULL, 00000000060, NULL, '', NULL, '', '放款列表', 0, 1, 'LoanList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11217, 0, '已逾期订单', 11232, '', NULL, 00000000072, NULL, '', '2017-03-21 11:57:26', '', '已逾期列表', 0, 1, 'OverdueList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11218, 0, '催收预警', 0, '', 'icon-yujingxiaoxi', 00000000063, NULL, '', '2017-03-08 13:47:52', '', '催收预警', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11219, 0, '已逾期未入催', 11218, '', NULL, 00000000010, NULL, '', NULL, '', '已逾期未入催', 0, 1, 'OverdueNoAdmission', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11220, 0, '代理商列表', 11174, '', NULL, 00000000005, NULL, '', '2017-04-25 17:21:00', '', '代理商列表', 0, 1, 'AgentList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11221, 0, '用户代理等级', 11174, '', NULL, 00000000004, NULL, '', '2017-04-25 17:21:18', '', '用户代理等级', 0, 1, 'OrdinaryUserList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11222, 0, '未还款已出催', 11218, '', NULL, 00000000020, NULL, '', NULL, '', '未还款已出催', 0, 1, 'NoRepaymentPutForward', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11223, 0, '未分配催收员', 11218, '', NULL, 00000000030, NULL, '', NULL, '', '未分配催收员', 0, 1, 'UnallocatedCollection', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11224, 0, '催收统计', 0, '', 'icon-tongji', 00000000064, NULL, '', '2017-03-08 13:47:41', '', '催收统计管理', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11225, 0, '催回率统计', 11224, '', NULL, 00000000010, NULL, '', NULL, '', '催回率统计', 0, 1, 'RecoveryRateStatistics', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11226, 0, '催收订单统计', 11224, '', NULL, 00000000020, NULL, '', NULL, '', '催收订单统计', 0, 1, 'CollectionOrderStatistics', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11227, 0, '催收员每日统计', 11224, '', NULL, 00000000030, NULL, '', NULL, '', '催收员每日统计', 0, 1, 'CollectionDailyStatistics', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11228, 0, '借款进度', 11159, '', NULL, 00000000055, NULL, '', '2017-03-07 15:02:29', '', '借款进度', 0, 1, 'LoanSchedule', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11232, 0, '贷后管理', 0, '', 'icon-qian', 00000000052, NULL, '', '2017-03-08 13:48:29', '', '贷后管理', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11233, 0, '已还款订单', 11232, '', NULL, 00000000071, NULL, '', '2017-03-21 11:57:09', '', '已还款列表', 0, 1, 'RepaymentList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11235, 0, '财务管理', 0, '', NULL, 00000000046, NULL, '', '2017-03-07 15:24:17', '', '财务管理', 1, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11236, 0, '支付管理', 0, '', 'icon-qian', 00000000050, NULL, '', '2017-04-19 11:47:37', '', '打款管理', 0, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11237, 0, '还款管理', 11235, '', NULL, 00000000034, NULL, '', '2017-03-07 15:24:38', '', '还款管理', 1, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11238, 0, '代扣支付记录', 11199, '', NULL, 00000000036, NULL, '', '2017-03-22 16:02:37', '', '代扣支付记录', 0, 1, 'DeductionsList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11239, 0, '待扣款订单', 11199, '', NULL, 00000000037, NULL, '', '2017-03-22 16:02:48', '', '待扣款列表', 1, 1, 'StayDeductionsList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11240, 0, '支付记录', 11236, '', NULL, 00000000043, NULL, '', '2017-04-19 11:47:52', '', '支付记录', 0, 1, 'remitList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11241, 0, '支付审核', 11236, '', NULL, 00000000044, NULL, '', '2017-04-19 11:47:59', '', '放款支付审核', 0, 1, 'remitCheck', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11242, 0, '系统放款订单', 11236, '', NULL, 00000000045, NULL, '', '2017-03-22 16:04:20', '', '系统打款列表', 1, 1, 'systemCallList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11244, 0, '渠道管理', 0, '', 'icon-tongji', 00000000122, NULL, '', '2017-04-24 14:02:59', '', '渠道管理', 0, 1, 'PipelineManage', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11245, 0, '用户通讯录', 11161, '', NULL, 00000000026, NULL, '', NULL, '', NULL, NULL, 1, 'UserAddressBook', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11246, 0, '用户通讯录', 11161, '', NULL, 00000000026, NULL, '', '2017-03-17 15:35:48', '', '用户通讯录', 1, 1, 'UserBook', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11248, 0, '审核员列表', 11195, '', NULL, 00000000033, NULL, '', '2017-03-20 15:32:57', '', '审核员列表', 1, 1, 'AuditorList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11249, 0, '审核订单数量', 11195, '', NULL, 00000000035, NULL, '', '2017-03-20 15:33:06', '', '审核订单数量', 1, 1, 'AuditOrderQuantity', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11250, 0, '风控数据', 0, '', 'icon-zhuanyefengkong', 00000000080, NULL, '', '2017-03-08 13:42:38', '', '风控数据', 0, 0, 'WindControlData', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11251, 0, '每日通过率', 11250, '', NULL, 00000000061, NULL, '', NULL, '', '每日通过率', 0, 1, 'DailyPassRate', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11252, 0, '平台数据日报', 11250, '', NULL, 00000000062, NULL, '', NULL, '', '平台数据日报', 0, 1, 'PlatformDataDaily', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11254, 0, '运营数据', 0, '', 'icon-tongji', 00000000083, NULL, '', '2017-03-08 13:46:35', '', ' 运营数据', 0, 0, 'OperationalData', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11255, 0, '整体运营数据', 11254, '', NULL, 00000000071, NULL, '', '2017-03-20 11:08:53', '', ' 整体运营数据', 1, 1, 'OverallOperationalData', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11256, 0, '渠道数据汇总列表', 11254, '', NULL, 00000000072, NULL, '', '2017-03-20 11:08:45', '', '渠道数据汇总列表', 1, 1, 'ChannelDataList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11257, 0, '每日还款分析', 11254, '', NULL, 00000000073, NULL, '', NULL, '', '每日还款分析', 0, 1, 'DailyRepaymentAnalysis', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11258, 0, '每月还款分析', 11254, '', NULL, 00000000074, NULL, '', NULL, '', ' 每月还款分析', 0, 1, 'MonthlyRepaymentAnalysis', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11259, 0, '每月逾期分析', 11254, '', NULL, 00000000076, NULL, '', '2017-03-20 14:59:16', '', ' 每月逾期分析', 1, 1, ' Monthly1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11260, 0, '财务数据', 0, '', 'icon-tongji', 00000000081, NULL, '', '2017-03-20 11:09:35', '', '  财务数据', 1, 0, 'FinancialData', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11261, 0, '每日放款数据', 11260, '', NULL, 00000000079, NULL, '', '2017-03-20 11:09:44', '', '每日放款数据', 1, 1, 'DailyLoanData', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11264, 0, '统计管理', 0, '', 'icon-tongji', 00000000085, NULL, '', '2017-03-08 13:43:42', '', ' 统计管理', 0, 0, 'StatisticalManagement', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11267, 0, '对账列表', 11264, '', NULL, 00000000110, NULL, '', '2017-03-20 11:10:03', '', '对账列表', 1, 1, 'CheckList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11268, 0, '每日未还本金', 11264, '', NULL, 00000000111, NULL, '', NULL, '', '每日未还本金', 0, 1, 'DailyPrincipal', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11269, 0, '每日放款收支数据', 11264, '', NULL, 00000000112, NULL, '', NULL, '', ' 每日放款收支数据', 0, 1, 'DailylLoanData', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11270, 0, '定时任务', 0, '', 'icon-tongji', 00000000108, NULL, '', '2017-03-20 15:26:40', '', '定时任务', 0, 1, 'TimedTask', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11271, 0, '任务列表', 11270, '', NULL, 00000000110, NULL, '', '2017-03-21 18:14:57', '', '定时任务列表', 0, 1, 'TimedTaskList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11272, 0, '每月逾期分析', 11254, '', NULL, 00000000077, NULL, '', '2017-03-20 14:59:45', '', '每月逾期分析', 0, 1, 'Monthly', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11273, 0, '已坏账订单', 11232, '', NULL, 00000000073, NULL, '', NULL, '', '已坏账订单', 0, 1, 'BadDebtsList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11274, 0, '执行记录', 11270, '', NULL, 00000000123, NULL, '', '2017-03-21 18:16:14', '', '定时任务执行记录', 0, 1, 'TimedTaskLog', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11275, 0, '催收反馈', 11210, '', NULL, 00000000060, NULL, '', NULL, '', '催收反馈', 0, 1, 'CollectionFeedbackManage', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11276, 0, '浅橙贷前审核记录', 11195, '', NULL, 00000000025, NULL, '', NULL, '', '浅橙贷前审核记录', 0, 1, 'MachineRequestRecord', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11277, 0, 'Druid监控', 1, '', NULL, 00000000111, NULL, '', '2017-03-27 09:38:32', '', 'Druid监控', 0, 1, 'Druid', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11278, 0, '访问码管理', 1, '', NULL, 00000000001, NULL, '', NULL, '', '访问码管理', 0, 1, 'AccessCode', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11279, 0, '工作台', 0, '', 'icon-yonghuxinxi', 00000000001, NULL, '', '2017-04-06 10:52:13', '', '工作台', 1, 1, 'Workbench', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11280, 0, '用户反馈列表', 11161, '', NULL, 00000000033, NULL, '', NULL, '', '用户反馈列表', 0, 1, 'UserFeedback', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11281, 0, '征信数据接口', 0, '', 'icon-tongji', 00000000070, NULL, '', '2017-04-10 10:14:41', '', '第三方征信', 0, 1, 'ThirdPartyInquiry', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11282, 0, '第三方征信接口', 11281, '', NULL, 00000000010, NULL, '', '2017-04-20 11:38:57', '', '第三方征信', 0, 1, 'ThirdPartyInquiry', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11283, 0, '场景与接口关系维护', 11281, '', NULL, 00000000030, NULL, '', '2017-04-20 11:38:38', '', '场景与接口关系维护', 0, 1, 'ScenePortManage', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11285, 0, '支付对账记录', 11236, '', NULL, 00000000045, NULL, '', '2017-04-20 11:23:12', '', '支付对账记录', 0, 1, 'CheckManagement', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11287, 0, '渠道app管理', 11244, '', NULL, 00000000010, NULL, '', '2017-03-20 11:08:45', '', '用户渠道管理', 0, 1, 'ChannelManage', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11288, 0, '渠道信息统计', 11244, '', NULL, 00000000020, NULL, '', NULL, '', '渠道信息统计', 0, 1, 'ChannelInformationStatistics', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11289, 0, '同盾贷前审核记录', 11195, '', NULL, 00000000028, NULL, '', NULL, '', '同盾贷前审核记录', 0, 1, 'ShildCreditAuditRecords', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11290, 0, '机审通过订单', 11195, '', NULL, 00000000015, NULL, '', NULL, '', '机审通过订单', 0, 1, 'MachinePassList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11291, 0, '放款审核', 11195, '', NULL, 00000000040, NULL, '', NULL, '', '放款审核', 0, 1, 'LoanAuditList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11292, 0, '报表', 0, '', 'icon-yonghuxinxi', 00000000150, NULL, '', '2017-09-21 16:15:49', '', '报表', 0, 1, 'ReportForm', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11293, 0, '财务结算', 11292, '', 'icon-yonghuxinxi', 00000000151, NULL, '', '2017-09-21 16:15:59', '', '财务结算', 0, 1, 'FinanceSettlement', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11294, 0, '逾期15天报表', 11292, '', 'icon-yonghuxinxi', 00000000152, NULL, '', '2017-09-26 14:19:05', '', '逾期15天报表', 0, 1, 'OverdueOrderExport', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11295, 0, '应还统计', 11292, '', 'icon-yonghuxinxi', 00000000153, NULL, '', '2017-09-28 11:31:35', '', '应还统计', 0, 1, 'RepaymentStatistics', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11296, 0, '风控数据动态统计', 11292, '', 'icon-yonghuxinxi', 00000000154, NULL, '', '2017-09-28 11:31:54', '', '风控数据动态统计', 0, 1, 'RiskControlDataDynamicStatistics', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11297, 0, '风控数据统计', 11292, '', 'icon-yonghuxinxi', 00000000155, NULL, '', NULL, '', '风控数据统计', 0, 1, 'RiskControllerStatistics', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11298, 0, '合作平台管理', 0, '', 'icon-yonghuxinxi', 00000000160, NULL, '', NULL, '', '合作平台管理', 0, 1, 'SurplusOrder', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11299, 0, '尾单合作平台管理', 11298, '', NULL, 00000000161, NULL, '', NULL, '', '尾单合作平台管理', 0, 1, 'SurplusOrderPlatform', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11300, 0, '黑名单用户', 11161, '', NULL, 00000000034, NULL, '', NULL, '', '黑名单用户', 0, 1, 'BlackCustomerList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11301, 0, '引流合作平台管理', 11298, '', NULL, 00000000162, NULL, '', NULL, '', '引流合作平台管理', 0, 1, 'DrainagePlatformList', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (11302, 0, '测试', 11298, '', NULL, 00000000003, NULL, '', NULL, '', 'test ', 0, 1, 'asedfwef', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_perm
-- ----------------------------
DROP TABLE IF EXISTS `sys_perm`;
CREATE TABLE `sys_perm` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(128) NOT NULL COMMENT '编码',
  `name` varchar(512) DEFAULT '' COMMENT '权限名称',
  `perm_level` int(2) NOT NULL DEFAULT '1' COMMENT '权限级别 1-系统级别 2-普通级别',
  `remark` varchar(128) DEFAULT '' COMMENT '权限备注',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `add_user` varchar(11) NOT NULL DEFAULT '' COMMENT '添加人',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of sys_perm
-- ----------------------------
BEGIN;
INSERT INTO `sys_perm` VALUES (65, 'modules:manage:system:user:save', '新增用户', 1, '用户管理', '2016-12-05 14:25:30', 'system', 3);
INSERT INTO `sys_perm` VALUES (66, 'modules:manage:system:user:update', '用户修改', 1, '用户管理', '2016-12-05 14:49:03', 'system', 3);
INSERT INTO `sys_perm` VALUES (67, 'modules:manage:system:roleMenu:fetch', '分配修改权限', 1, '分配修改权限', '2016-12-05 14:59:51', 'system', 4);
INSERT INTO `sys_perm` VALUES (68, 'modules:manage:system:role:save', '新增修改角色', 1, '角色管理', '2016-12-05 15:02:26', 'system', 4);
INSERT INTO `sys_perm` VALUES (69, 'modules:manage:system:role:delete', '删除角色', 1, '角色管理', '2016-12-05 15:03:21', 'system', 4);
INSERT INTO `sys_perm` VALUES (76, 'modules:manage:system:menu:update', '新增修改菜单', 1, '菜单管理', '2016-12-05 15:41:18', 'system', 2);
INSERT INTO `sys_perm` VALUES (77, 'modules:manage:system:user:lis', '用户管理', 1, '用户管理', '2016-12-05 15:47:46', 'system', 3);
INSERT INTO `sys_perm` VALUES (78, 'modules:manage:system:dict:page', '字典查询', 1, '字典管理', '2016-12-05 15:49:17', 'system', 7);
INSERT INTO `sys_perm` VALUES (79, 'modules:manage:system:dict:detail:find', '字典详情', 1, '字典管理', '2016-12-05 15:50:25', 'system', 7);
INSERT INTO `sys_perm` VALUES (80, 'modules:manage:system:dict:save', '新增修改字典', 1, '字典管理', '2016-12-05 15:51:31', 'system', 7);
INSERT INTO `sys_perm` VALUES (81, 'modules:manage:system:dict:detail:save', '新增修改字典详情', 1, '字典管理', '2016-12-05 15:52:20', 'system', 7);
INSERT INTO `sys_perm` VALUES (82, 'modules:manage:system:dict:delete', '删除字典', 1, '字典管理', '2016-12-05 15:52:57', 'system', 7);
INSERT INTO `sys_perm` VALUES (83, 'modules:manage:system:dict:detail:delete', '删除字典详情', 1, '字典管理', '2016-12-05 15:53:34', 'system', 7);
INSERT INTO `sys_perm` VALUES (90, 'modules:manage:system:config:save', '新增修改系统参数', 1, '系统参数设置', '2016-12-05 17:10:25', 'system', 11050);
INSERT INTO `sys_perm` VALUES (91, 'modules:manage:system:config:list', '查询系统参数', 1, '系统参数设置', '2016-12-05 17:11:04', 'system', 11050);
INSERT INTO `sys_perm` VALUES (92, 'modules:manage:system:config:delete', '删除系统参数', 1, '系统参数设置', '2016-12-05 17:12:00', 'system', 11050);
INSERT INTO `sys_perm` VALUES (97, 'modules:manage:system:menu:find', '加载出原有菜单数据', 1, '加载出原有菜单数据', '2016-12-05 21:39:37', 'system', 4);
INSERT INTO `sys_perm` VALUES (98, 'modules:manage:system:menu:save', '修改用户权限', 1, '修改用户权限', '2016-12-05 21:41:36', 'system', 4);
INSERT INTO `sys_perm` VALUES (99, 'modules:manage:system:perm:find', '查询所有菜单', 1, '查询所有菜单', '2016-12-05 21:41:36', 'system', 4);
INSERT INTO `sys_perm` VALUES (100, 'modules:system:sconfig:westone', '获取用户某个菜单面板项集合', 1, '获取用户某个菜单面板项集合', '2016-12-05 21:41:36', 'system', 4);
INSERT INTO `sys_perm` VALUES (101, 'modules:system:sconfig:tree', '获取菜单树状图', 1, '获取菜单树状图', '2016-12-05 21:47:36', 'system', 4);
INSERT INTO `sys_perm` VALUES (102, 'modules:manage:system:menu:findMenuTrees', '配置菜单', 1, '配置菜单', '2016-12-05 21:50:16', 'system', 4);
INSERT INTO `sys_perm` VALUES (103, 'menu:delete', '删除菜单', 1, '删除菜单', '2016-12-05 21:50:16', 'system', 4);
INSERT INTO `sys_perm` VALUES (104, 'modules:system:sconfig:rolemenu', '分配菜单', 1, '分配菜单', '2016-12-05 21:50:16', 'system', 4);
INSERT INTO `sys_perm` VALUES (105, 'modules:manage:system:menu:combo:find', '菜单下拉框', 1, '菜单下拉框', '2016-12-05 21:50:16', 'system', 4);
INSERT INTO `sys_perm` VALUES (106, 'modules:system:modifyPassword', '修改密码', 1, '修改密码', '2016-12-05 14:25:30', 'system', 3);
INSERT INTO `sys_perm` VALUES (107, 'modules:system:getcustomerservicestafflist', '获取客服专员', 1, '获取客服专员', '2016-12-05 15:47:46', 'system', 3);
INSERT INTO `sys_perm` VALUES (108, 'modules:manage:system:user:info:find', '查询用户', 1, '查询用户', '2016-12-05 15:47:46', 'system', 3);
INSERT INTO `sys_perm` VALUES (109, 'modules:manage:system:dict:cache:list', '缓存字典使用    ', 1, '缓存字典使用    ', '2016-12-05 15:53:34', 'system', 7);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(128) DEFAULT '' COMMENT '角色名',
  `nid` varchar(64) DEFAULT '' COMMENT '角色唯一标示',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user` varchar(128) DEFAULT '' COMMENT '添加者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(128) DEFAULT '' COMMENT '修改者',
  `remark` varchar(256) DEFAULT '' COMMENT '备注',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '是否删除：0不删除，1删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `nid_index` (`nid`) USING BTREE,
  KEY `primary_key` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '系统管理员', 'system', '2016-05-06 00:59:00', 'system', '2016-05-06 12:00:00', 'system', '超级管理员', 0);
INSERT INTO `sys_role` VALUES (2, '财务人员', 'financialStaff', '2016-08-04 10:08:35', 'system', '2017-03-05 16:02:02', 'system', '财务人员', 0);
INSERT INTO `sys_role` VALUES (3, '代理商', 'agent', '2017-03-03 15:19:17', 'system', '2017-03-03 16:01:57', 'system', '代理商', 0);
INSERT INTO `sys_role` VALUES (4, '运营人员', 'operator', '2017-03-08 12:07:55', 'system', '2017-03-08 12:07:55', 'system', '运营人员', 0);
INSERT INTO `sys_role` VALUES (5, '催收管理员', 'collector', '2017-03-08 12:08:40', 'system', '2017-03-08 12:08:40', 'system', '催收管理员', 0);
INSERT INTO `sys_role` VALUES (6, '催收专员', 'collectionSpecialist', '2017-03-08 12:09:10', 'system', '2017-03-08 12:09:10', 'system', '催收专员', 0);
INSERT INTO `sys_role` VALUES (17, '客服人员', 'customerServiceOfficer', '2017-03-08 12:11:17', 'system', '2017-03-08 12:11:17', 'system', '客服人员', 0);
INSERT INTO `sys_role` VALUES (18, '风控人员', 'riskControlPersonnel', '2017-03-08 12:11:46', 'system', '2017-03-08 12:11:46', 'system', '风控人员', 0);
INSERT INTO `sys_role` VALUES (19, '超级管理员', 'superManager', '2017-08-19 18:22:36', 'zhaojr', '2017-08-19 18:22:36', 'zhaojr', '系统管理员', 0);
INSERT INTO `sys_role` VALUES (20, '风控专员', 'riskControlService', '2017-08-25 10:21:53', 'yuran1990', '2017-08-25 10:21:53', 'yuran1990', '风控专员', 0);
INSERT INTO `sys_role` VALUES (21, '委外催收组', 'lctc', '2017-09-30 17:14:42', 'system', '2017-09-30 17:14:42', 'system', '', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` int(11) NOT NULL COMMENT '角色主键',
  `menu_id` int(11) NOT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `role_id` (`role_id`,`menu_id`) USING BTREE,
  KEY `role_id_index` (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6781 DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (6684, 1, 1);
INSERT INTO `sys_role_menu` VALUES (6677, 1, 2);
INSERT INTO `sys_role_menu` VALUES (6678, 1, 3);
INSERT INTO `sys_role_menu` VALUES (6679, 1, 4);
INSERT INTO `sys_role_menu` VALUES (6680, 1, 7);
INSERT INTO `sys_role_menu` VALUES (6681, 1, 11050);
INSERT INTO `sys_role_menu` VALUES (6689, 1, 11138);
INSERT INTO `sys_role_menu` VALUES (6693, 1, 11140);
INSERT INTO `sys_role_menu` VALUES (6685, 1, 11141);
INSERT INTO `sys_role_menu` VALUES (6686, 1, 11146);
INSERT INTO `sys_role_menu` VALUES (6687, 1, 11151);
INSERT INTO `sys_role_menu` VALUES (6688, 1, 11153);
INSERT INTO `sys_role_menu` VALUES (6692, 1, 11159);
INSERT INTO `sys_role_menu` VALUES (6697, 1, 11161);
INSERT INTO `sys_role_menu` VALUES (6694, 1, 11168);
INSERT INTO `sys_role_menu` VALUES (6703, 1, 11174);
INSERT INTO `sys_role_menu` VALUES (6698, 1, 11176);
INSERT INTO `sys_role_menu` VALUES (6699, 1, 11177);
INSERT INTO `sys_role_menu` VALUES (6700, 1, 11178);
INSERT INTO `sys_role_menu` VALUES (6708, 1, 11180);
INSERT INTO `sys_role_menu` VALUES (6704, 1, 11181);
INSERT INTO `sys_role_menu` VALUES (6705, 1, 11182);
INSERT INTO `sys_role_menu` VALUES (6706, 1, 11183);
INSERT INTO `sys_role_menu` VALUES (6707, 1, 11184);
INSERT INTO `sys_role_menu` VALUES (6716, 1, 11195);
INSERT INTO `sys_role_menu` VALUES (6709, 1, 11196);
INSERT INTO `sys_role_menu` VALUES (6710, 1, 11197);
INSERT INTO `sys_role_menu` VALUES (6711, 1, 11198);
INSERT INTO `sys_role_menu` VALUES (6722, 1, 11199);
INSERT INTO `sys_role_menu` VALUES (6717, 1, 11200);
INSERT INTO `sys_role_menu` VALUES (6718, 1, 11201);
INSERT INTO `sys_role_menu` VALUES (6719, 1, 11202);
INSERT INTO `sys_role_menu` VALUES (6720, 1, 11203);
INSERT INTO `sys_role_menu` VALUES (6724, 1, 11204);
INSERT INTO `sys_role_menu` VALUES (6723, 1, 11205);
INSERT INTO `sys_role_menu` VALUES (6725, 1, 11206);
INSERT INTO `sys_role_menu` VALUES (6728, 1, 11207);
INSERT INTO `sys_role_menu` VALUES (6726, 1, 11208);
INSERT INTO `sys_role_menu` VALUES (6727, 1, 11209);
INSERT INTO `sys_role_menu` VALUES (6735, 1, 11210);
INSERT INTO `sys_role_menu` VALUES (6729, 1, 11211);
INSERT INTO `sys_role_menu` VALUES (6730, 1, 11212);
INSERT INTO `sys_role_menu` VALUES (6731, 1, 11213);
INSERT INTO `sys_role_menu` VALUES (6732, 1, 11214);
INSERT INTO `sys_role_menu` VALUES (6733, 1, 11215);
INSERT INTO `sys_role_menu` VALUES (6690, 1, 11216);
INSERT INTO `sys_role_menu` VALUES (6744, 1, 11217);
INSERT INTO `sys_role_menu` VALUES (6739, 1, 11218);
INSERT INTO `sys_role_menu` VALUES (6736, 1, 11219);
INSERT INTO `sys_role_menu` VALUES (6701, 1, 11220);
INSERT INTO `sys_role_menu` VALUES (6702, 1, 11221);
INSERT INTO `sys_role_menu` VALUES (6737, 1, 11222);
INSERT INTO `sys_role_menu` VALUES (6738, 1, 11223);
INSERT INTO `sys_role_menu` VALUES (6743, 1, 11224);
INSERT INTO `sys_role_menu` VALUES (6740, 1, 11225);
INSERT INTO `sys_role_menu` VALUES (6741, 1, 11226);
INSERT INTO `sys_role_menu` VALUES (6742, 1, 11227);
INSERT INTO `sys_role_menu` VALUES (6691, 1, 11228);
INSERT INTO `sys_role_menu` VALUES (6747, 1, 11232);
INSERT INTO `sys_role_menu` VALUES (6745, 1, 11233);
INSERT INTO `sys_role_menu` VALUES (6751, 1, 11236);
INSERT INTO `sys_role_menu` VALUES (6721, 1, 11238);
INSERT INTO `sys_role_menu` VALUES (6748, 1, 11240);
INSERT INTO `sys_role_menu` VALUES (6749, 1, 11241);
INSERT INTO `sys_role_menu` VALUES (6754, 1, 11244);
INSERT INTO `sys_role_menu` VALUES (6757, 1, 11250);
INSERT INTO `sys_role_menu` VALUES (6755, 1, 11251);
INSERT INTO `sys_role_menu` VALUES (6756, 1, 11252);
INSERT INTO `sys_role_menu` VALUES (6761, 1, 11254);
INSERT INTO `sys_role_menu` VALUES (6758, 1, 11257);
INSERT INTO `sys_role_menu` VALUES (6759, 1, 11258);
INSERT INTO `sys_role_menu` VALUES (6764, 1, 11264);
INSERT INTO `sys_role_menu` VALUES (6762, 1, 11268);
INSERT INTO `sys_role_menu` VALUES (6763, 1, 11269);
INSERT INTO `sys_role_menu` VALUES (6767, 1, 11270);
INSERT INTO `sys_role_menu` VALUES (6765, 1, 11271);
INSERT INTO `sys_role_menu` VALUES (6760, 1, 11272);
INSERT INTO `sys_role_menu` VALUES (6746, 1, 11273);
INSERT INTO `sys_role_menu` VALUES (6766, 1, 11274);
INSERT INTO `sys_role_menu` VALUES (6734, 1, 11275);
INSERT INTO `sys_role_menu` VALUES (6712, 1, 11276);
INSERT INTO `sys_role_menu` VALUES (6682, 1, 11277);
INSERT INTO `sys_role_menu` VALUES (6683, 1, 11278);
INSERT INTO `sys_role_menu` VALUES (6695, 1, 11280);
INSERT INTO `sys_role_menu` VALUES (6770, 1, 11281);
INSERT INTO `sys_role_menu` VALUES (6768, 1, 11282);
INSERT INTO `sys_role_menu` VALUES (6769, 1, 11283);
INSERT INTO `sys_role_menu` VALUES (6750, 1, 11285);
INSERT INTO `sys_role_menu` VALUES (6752, 1, 11287);
INSERT INTO `sys_role_menu` VALUES (6753, 1, 11288);
INSERT INTO `sys_role_menu` VALUES (6713, 1, 11289);
INSERT INTO `sys_role_menu` VALUES (6714, 1, 11290);
INSERT INTO `sys_role_menu` VALUES (6715, 1, 11291);
INSERT INTO `sys_role_menu` VALUES (6776, 1, 11292);
INSERT INTO `sys_role_menu` VALUES (6771, 1, 11293);
INSERT INTO `sys_role_menu` VALUES (6772, 1, 11294);
INSERT INTO `sys_role_menu` VALUES (6773, 1, 11295);
INSERT INTO `sys_role_menu` VALUES (6774, 1, 11296);
INSERT INTO `sys_role_menu` VALUES (6775, 1, 11297);
INSERT INTO `sys_role_menu` VALUES (6780, 1, 11298);
INSERT INTO `sys_role_menu` VALUES (6777, 1, 11299);
INSERT INTO `sys_role_menu` VALUES (6696, 1, 11300);
INSERT INTO `sys_role_menu` VALUES (6778, 1, 11301);
INSERT INTO `sys_role_menu` VALUES (6779, 1, 11302);
INSERT INTO `sys_role_menu` VALUES (5745, 2, 11138);
INSERT INTO `sys_role_menu` VALUES (5749, 2, 11140);
INSERT INTO `sys_role_menu` VALUES (5741, 2, 11141);
INSERT INTO `sys_role_menu` VALUES (5742, 2, 11146);
INSERT INTO `sys_role_menu` VALUES (5743, 2, 11151);
INSERT INTO `sys_role_menu` VALUES (5744, 2, 11153);
INSERT INTO `sys_role_menu` VALUES (5748, 2, 11159);
INSERT INTO `sys_role_menu` VALUES (5752, 2, 11161);
INSERT INTO `sys_role_menu` VALUES (5750, 2, 11168);
INSERT INTO `sys_role_menu` VALUES (5758, 2, 11174);
INSERT INTO `sys_role_menu` VALUES (5753, 2, 11176);
INSERT INTO `sys_role_menu` VALUES (5754, 2, 11177);
INSERT INTO `sys_role_menu` VALUES (5755, 2, 11178);
INSERT INTO `sys_role_menu` VALUES (5766, 2, 11195);
INSERT INTO `sys_role_menu` VALUES (5759, 2, 11196);
INSERT INTO `sys_role_menu` VALUES (5760, 2, 11197);
INSERT INTO `sys_role_menu` VALUES (5761, 2, 11198);
INSERT INTO `sys_role_menu` VALUES (5772, 2, 11199);
INSERT INTO `sys_role_menu` VALUES (5767, 2, 11200);
INSERT INTO `sys_role_menu` VALUES (5768, 2, 11201);
INSERT INTO `sys_role_menu` VALUES (5769, 2, 11202);
INSERT INTO `sys_role_menu` VALUES (5770, 2, 11203);
INSERT INTO `sys_role_menu` VALUES (5774, 2, 11204);
INSERT INTO `sys_role_menu` VALUES (5773, 2, 11205);
INSERT INTO `sys_role_menu` VALUES (5775, 2, 11206);
INSERT INTO `sys_role_menu` VALUES (5778, 2, 11207);
INSERT INTO `sys_role_menu` VALUES (5776, 2, 11208);
INSERT INTO `sys_role_menu` VALUES (5777, 2, 11209);
INSERT INTO `sys_role_menu` VALUES (5785, 2, 11210);
INSERT INTO `sys_role_menu` VALUES (5779, 2, 11211);
INSERT INTO `sys_role_menu` VALUES (5780, 2, 11212);
INSERT INTO `sys_role_menu` VALUES (5781, 2, 11213);
INSERT INTO `sys_role_menu` VALUES (5782, 2, 11214);
INSERT INTO `sys_role_menu` VALUES (5783, 2, 11215);
INSERT INTO `sys_role_menu` VALUES (5746, 2, 11216);
INSERT INTO `sys_role_menu` VALUES (5794, 2, 11217);
INSERT INTO `sys_role_menu` VALUES (5789, 2, 11218);
INSERT INTO `sys_role_menu` VALUES (5786, 2, 11219);
INSERT INTO `sys_role_menu` VALUES (5756, 2, 11220);
INSERT INTO `sys_role_menu` VALUES (5757, 2, 11221);
INSERT INTO `sys_role_menu` VALUES (5787, 2, 11222);
INSERT INTO `sys_role_menu` VALUES (5788, 2, 11223);
INSERT INTO `sys_role_menu` VALUES (5793, 2, 11224);
INSERT INTO `sys_role_menu` VALUES (5790, 2, 11225);
INSERT INTO `sys_role_menu` VALUES (5791, 2, 11226);
INSERT INTO `sys_role_menu` VALUES (5792, 2, 11227);
INSERT INTO `sys_role_menu` VALUES (5747, 2, 11228);
INSERT INTO `sys_role_menu` VALUES (5797, 2, 11232);
INSERT INTO `sys_role_menu` VALUES (5795, 2, 11233);
INSERT INTO `sys_role_menu` VALUES (5801, 2, 11236);
INSERT INTO `sys_role_menu` VALUES (5771, 2, 11238);
INSERT INTO `sys_role_menu` VALUES (5798, 2, 11240);
INSERT INTO `sys_role_menu` VALUES (5799, 2, 11241);
INSERT INTO `sys_role_menu` VALUES (5804, 2, 11244);
INSERT INTO `sys_role_menu` VALUES (5807, 2, 11250);
INSERT INTO `sys_role_menu` VALUES (5805, 2, 11251);
INSERT INTO `sys_role_menu` VALUES (5806, 2, 11252);
INSERT INTO `sys_role_menu` VALUES (5811, 2, 11254);
INSERT INTO `sys_role_menu` VALUES (5808, 2, 11257);
INSERT INTO `sys_role_menu` VALUES (5809, 2, 11258);
INSERT INTO `sys_role_menu` VALUES (5814, 2, 11264);
INSERT INTO `sys_role_menu` VALUES (5812, 2, 11268);
INSERT INTO `sys_role_menu` VALUES (5813, 2, 11269);
INSERT INTO `sys_role_menu` VALUES (5817, 2, 11270);
INSERT INTO `sys_role_menu` VALUES (5815, 2, 11271);
INSERT INTO `sys_role_menu` VALUES (5810, 2, 11272);
INSERT INTO `sys_role_menu` VALUES (5796, 2, 11273);
INSERT INTO `sys_role_menu` VALUES (5816, 2, 11274);
INSERT INTO `sys_role_menu` VALUES (5784, 2, 11275);
INSERT INTO `sys_role_menu` VALUES (5762, 2, 11276);
INSERT INTO `sys_role_menu` VALUES (5751, 2, 11280);
INSERT INTO `sys_role_menu` VALUES (5820, 2, 11281);
INSERT INTO `sys_role_menu` VALUES (5818, 2, 11282);
INSERT INTO `sys_role_menu` VALUES (5819, 2, 11283);
INSERT INTO `sys_role_menu` VALUES (5800, 2, 11285);
INSERT INTO `sys_role_menu` VALUES (5802, 2, 11287);
INSERT INTO `sys_role_menu` VALUES (5803, 2, 11288);
INSERT INTO `sys_role_menu` VALUES (5763, 2, 11289);
INSERT INTO `sys_role_menu` VALUES (5764, 2, 11290);
INSERT INTO `sys_role_menu` VALUES (5765, 2, 11291);
INSERT INTO `sys_role_menu` VALUES (5822, 2, 11292);
INSERT INTO `sys_role_menu` VALUES (5821, 2, 11293);
INSERT INTO `sys_role_menu` VALUES (3392, 3, 11180);
INSERT INTO `sys_role_menu` VALUES (3388, 3, 11181);
INSERT INTO `sys_role_menu` VALUES (3389, 3, 11182);
INSERT INTO `sys_role_menu` VALUES (3390, 3, 11183);
INSERT INTO `sys_role_menu` VALUES (3391, 3, 11184);
INSERT INTO `sys_role_menu` VALUES (5263, 4, 11138);
INSERT INTO `sys_role_menu` VALUES (5267, 4, 11140);
INSERT INTO `sys_role_menu` VALUES (5266, 4, 11159);
INSERT INTO `sys_role_menu` VALUES (5270, 4, 11161);
INSERT INTO `sys_role_menu` VALUES (5268, 4, 11168);
INSERT INTO `sys_role_menu` VALUES (5276, 4, 11199);
INSERT INTO `sys_role_menu` VALUES (5271, 4, 11200);
INSERT INTO `sys_role_menu` VALUES (5272, 4, 11201);
INSERT INTO `sys_role_menu` VALUES (5273, 4, 11202);
INSERT INTO `sys_role_menu` VALUES (5274, 4, 11203);
INSERT INTO `sys_role_menu` VALUES (5264, 4, 11216);
INSERT INTO `sys_role_menu` VALUES (5277, 4, 11217);
INSERT INTO `sys_role_menu` VALUES (5265, 4, 11228);
INSERT INTO `sys_role_menu` VALUES (5280, 4, 11232);
INSERT INTO `sys_role_menu` VALUES (5278, 4, 11233);
INSERT INTO `sys_role_menu` VALUES (5275, 4, 11238);
INSERT INTO `sys_role_menu` VALUES (5283, 4, 11244);
INSERT INTO `sys_role_menu` VALUES (5286, 4, 11250);
INSERT INTO `sys_role_menu` VALUES (5284, 4, 11251);
INSERT INTO `sys_role_menu` VALUES (5285, 4, 11252);
INSERT INTO `sys_role_menu` VALUES (5290, 4, 11254);
INSERT INTO `sys_role_menu` VALUES (5287, 4, 11257);
INSERT INTO `sys_role_menu` VALUES (5288, 4, 11258);
INSERT INTO `sys_role_menu` VALUES (5293, 4, 11264);
INSERT INTO `sys_role_menu` VALUES (5291, 4, 11268);
INSERT INTO `sys_role_menu` VALUES (5292, 4, 11269);
INSERT INTO `sys_role_menu` VALUES (5289, 4, 11272);
INSERT INTO `sys_role_menu` VALUES (5279, 4, 11273);
INSERT INTO `sys_role_menu` VALUES (5269, 4, 11280);
INSERT INTO `sys_role_menu` VALUES (5281, 4, 11287);
INSERT INTO `sys_role_menu` VALUES (5282, 4, 11288);
INSERT INTO `sys_role_menu` VALUES (5169, 5, 11140);
INSERT INTO `sys_role_menu` VALUES (5171, 5, 11204);
INSERT INTO `sys_role_menu` VALUES (5170, 5, 11205);
INSERT INTO `sys_role_menu` VALUES (5172, 5, 11206);
INSERT INTO `sys_role_menu` VALUES (5175, 5, 11207);
INSERT INTO `sys_role_menu` VALUES (5173, 5, 11208);
INSERT INTO `sys_role_menu` VALUES (5174, 5, 11209);
INSERT INTO `sys_role_menu` VALUES (5182, 5, 11210);
INSERT INTO `sys_role_menu` VALUES (5176, 5, 11211);
INSERT INTO `sys_role_menu` VALUES (5177, 5, 11212);
INSERT INTO `sys_role_menu` VALUES (5178, 5, 11213);
INSERT INTO `sys_role_menu` VALUES (5179, 5, 11214);
INSERT INTO `sys_role_menu` VALUES (5180, 5, 11215);
INSERT INTO `sys_role_menu` VALUES (5191, 5, 11217);
INSERT INTO `sys_role_menu` VALUES (5186, 5, 11218);
INSERT INTO `sys_role_menu` VALUES (5183, 5, 11219);
INSERT INTO `sys_role_menu` VALUES (5184, 5, 11222);
INSERT INTO `sys_role_menu` VALUES (5185, 5, 11223);
INSERT INTO `sys_role_menu` VALUES (5190, 5, 11224);
INSERT INTO `sys_role_menu` VALUES (5187, 5, 11225);
INSERT INTO `sys_role_menu` VALUES (5188, 5, 11226);
INSERT INTO `sys_role_menu` VALUES (5189, 5, 11227);
INSERT INTO `sys_role_menu` VALUES (5194, 5, 11232);
INSERT INTO `sys_role_menu` VALUES (5192, 5, 11233);
INSERT INTO `sys_role_menu` VALUES (5193, 5, 11273);
INSERT INTO `sys_role_menu` VALUES (5181, 5, 11275);
INSERT INTO `sys_role_menu` VALUES (5454, 6, 11208);
INSERT INTO `sys_role_menu` VALUES (5461, 6, 11210);
INSERT INTO `sys_role_menu` VALUES (5455, 6, 11211);
INSERT INTO `sys_role_menu` VALUES (5456, 6, 11212);
INSERT INTO `sys_role_menu` VALUES (5457, 6, 11213);
INSERT INTO `sys_role_menu` VALUES (5458, 6, 11214);
INSERT INTO `sys_role_menu` VALUES (5459, 6, 11215);
INSERT INTO `sys_role_menu` VALUES (5465, 6, 11224);
INSERT INTO `sys_role_menu` VALUES (5462, 6, 11225);
INSERT INTO `sys_role_menu` VALUES (5463, 6, 11226);
INSERT INTO `sys_role_menu` VALUES (5464, 6, 11227);
INSERT INTO `sys_role_menu` VALUES (5460, 6, 11275);
INSERT INTO `sys_role_menu` VALUES (6263, 17, 11138);
INSERT INTO `sys_role_menu` VALUES (6267, 17, 11140);
INSERT INTO `sys_role_menu` VALUES (6266, 17, 11159);
INSERT INTO `sys_role_menu` VALUES (6270, 17, 11161);
INSERT INTO `sys_role_menu` VALUES (6268, 17, 11168);
INSERT INTO `sys_role_menu` VALUES (6271, 17, 11200);
INSERT INTO `sys_role_menu` VALUES (6272, 17, 11201);
INSERT INTO `sys_role_menu` VALUES (6264, 17, 11216);
INSERT INTO `sys_role_menu` VALUES (6265, 17, 11228);
INSERT INTO `sys_role_menu` VALUES (6269, 17, 11280);
INSERT INTO `sys_role_menu` VALUES (5195, 18, 11138);
INSERT INTO `sys_role_menu` VALUES (5199, 18, 11140);
INSERT INTO `sys_role_menu` VALUES (5198, 18, 11159);
INSERT INTO `sys_role_menu` VALUES (5202, 18, 11161);
INSERT INTO `sys_role_menu` VALUES (5200, 18, 11168);
INSERT INTO `sys_role_menu` VALUES (5203, 18, 11197);
INSERT INTO `sys_role_menu` VALUES (5204, 18, 11198);
INSERT INTO `sys_role_menu` VALUES (5205, 18, 11200);
INSERT INTO `sys_role_menu` VALUES (5206, 18, 11201);
INSERT INTO `sys_role_menu` VALUES (5207, 18, 11203);
INSERT INTO `sys_role_menu` VALUES (5210, 18, 11204);
INSERT INTO `sys_role_menu` VALUES (5209, 18, 11205);
INSERT INTO `sys_role_menu` VALUES (5211, 18, 11206);
INSERT INTO `sys_role_menu` VALUES (5214, 18, 11207);
INSERT INTO `sys_role_menu` VALUES (5212, 18, 11208);
INSERT INTO `sys_role_menu` VALUES (5213, 18, 11209);
INSERT INTO `sys_role_menu` VALUES (5221, 18, 11210);
INSERT INTO `sys_role_menu` VALUES (5215, 18, 11211);
INSERT INTO `sys_role_menu` VALUES (5216, 18, 11212);
INSERT INTO `sys_role_menu` VALUES (5217, 18, 11213);
INSERT INTO `sys_role_menu` VALUES (5218, 18, 11214);
INSERT INTO `sys_role_menu` VALUES (5219, 18, 11215);
INSERT INTO `sys_role_menu` VALUES (5196, 18, 11216);
INSERT INTO `sys_role_menu` VALUES (5230, 18, 11217);
INSERT INTO `sys_role_menu` VALUES (5225, 18, 11218);
INSERT INTO `sys_role_menu` VALUES (5222, 18, 11219);
INSERT INTO `sys_role_menu` VALUES (5223, 18, 11222);
INSERT INTO `sys_role_menu` VALUES (5224, 18, 11223);
INSERT INTO `sys_role_menu` VALUES (5229, 18, 11224);
INSERT INTO `sys_role_menu` VALUES (5226, 18, 11225);
INSERT INTO `sys_role_menu` VALUES (5227, 18, 11226);
INSERT INTO `sys_role_menu` VALUES (5228, 18, 11227);
INSERT INTO `sys_role_menu` VALUES (5197, 18, 11228);
INSERT INTO `sys_role_menu` VALUES (5233, 18, 11232);
INSERT INTO `sys_role_menu` VALUES (5231, 18, 11233);
INSERT INTO `sys_role_menu` VALUES (5208, 18, 11238);
INSERT INTO `sys_role_menu` VALUES (5232, 18, 11273);
INSERT INTO `sys_role_menu` VALUES (5220, 18, 11275);
INSERT INTO `sys_role_menu` VALUES (5201, 18, 11280);
INSERT INTO `sys_role_menu` VALUES (4747, 19, 1);
INSERT INTO `sys_role_menu` VALUES (4740, 19, 2);
INSERT INTO `sys_role_menu` VALUES (4741, 19, 3);
INSERT INTO `sys_role_menu` VALUES (4742, 19, 4);
INSERT INTO `sys_role_menu` VALUES (4743, 19, 7);
INSERT INTO `sys_role_menu` VALUES (4744, 19, 11050);
INSERT INTO `sys_role_menu` VALUES (4745, 19, 11277);
INSERT INTO `sys_role_menu` VALUES (4746, 19, 11278);
INSERT INTO `sys_role_menu` VALUES (5130, 20, 11197);
INSERT INTO `sys_role_menu` VALUES (5131, 20, 11198);
INSERT INTO `sys_role_menu` VALUES (5133, 20, 11206);
INSERT INTO `sys_role_menu` VALUES (5136, 20, 11207);
INSERT INTO `sys_role_menu` VALUES (5134, 20, 11208);
INSERT INTO `sys_role_menu` VALUES (5135, 20, 11209);
INSERT INTO `sys_role_menu` VALUES (5143, 20, 11210);
INSERT INTO `sys_role_menu` VALUES (5137, 20, 11211);
INSERT INTO `sys_role_menu` VALUES (5138, 20, 11212);
INSERT INTO `sys_role_menu` VALUES (5139, 20, 11213);
INSERT INTO `sys_role_menu` VALUES (5140, 20, 11214);
INSERT INTO `sys_role_menu` VALUES (5141, 20, 11215);
INSERT INTO `sys_role_menu` VALUES (5142, 20, 11275);
INSERT INTO `sys_role_menu` VALUES (5132, 20, 11290);
INSERT INTO `sys_role_menu` VALUES (6279, 21, 11210);
INSERT INTO `sys_role_menu` VALUES (6273, 21, 11211);
INSERT INTO `sys_role_menu` VALUES (6274, 21, 11212);
INSERT INTO `sys_role_menu` VALUES (6275, 21, 11213);
INSERT INTO `sys_role_menu` VALUES (6276, 21, 11214);
INSERT INTO `sys_role_menu` VALUES (6277, 21, 11215);
INSERT INTO `sys_role_menu` VALUES (6278, 21, 11275);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_perm
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_perm`;
CREATE TABLE `sys_role_perm` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `perm_id` int(11) NOT NULL COMMENT '权限ID',
  `add_user` varchar(11) NOT NULL DEFAULT '' COMMENT '添加人',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4730 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_role_perm
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_perm` VALUES (4585, 19, 76, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4586, 19, 65, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4587, 19, 66, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4588, 19, 77, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4589, 19, 106, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4590, 19, 107, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4591, 19, 108, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4592, 19, 67, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4593, 19, 68, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4594, 19, 69, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4595, 19, 97, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4596, 19, 98, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4597, 19, 99, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4598, 19, 100, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4599, 19, 101, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4600, 19, 102, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4601, 19, 103, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4602, 19, 104, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4603, 19, 105, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4604, 19, 78, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4605, 19, 79, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4606, 19, 80, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4607, 19, 81, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4608, 19, 82, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4609, 19, 83, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4610, 19, 109, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4611, 19, 90, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4612, 19, 91, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4613, 19, 92, 'zhaojr', '2017-08-19 18:22:53');
INSERT INTO `sys_role_perm` VALUES (4701, 1, 76, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4702, 1, 65, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4703, 1, 66, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4704, 1, 77, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4705, 1, 106, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4706, 1, 107, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4707, 1, 108, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4708, 1, 67, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4709, 1, 68, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4710, 1, 69, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4711, 1, 97, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4712, 1, 98, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4713, 1, 99, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4714, 1, 100, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4715, 1, 101, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4716, 1, 102, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4717, 1, 103, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4718, 1, 104, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4719, 1, 105, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4720, 1, 78, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4721, 1, 79, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4722, 1, 80, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4723, 1, 81, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4724, 1, 82, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4725, 1, 83, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4726, 1, 109, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4727, 1, 90, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4728, 1, 91, 'system', '2017-10-31 15:30:25');
INSERT INTO `sys_role_perm` VALUES (4729, 1, 92, 'system', '2017-10-31 15:30:25');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `name` varchar(128) DEFAULT '' COMMENT '姓名',
  `user_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '登陆名',
  `password` varchar(128) DEFAULT '' COMMENT '密码',
  `job_num` varchar(128) DEFAULT '' COMMENT '工号',
  `company_id` char(64) DEFAULT NULL COMMENT '公司',
  `office_id` char(64) DEFAULT NULL COMMENT '部门',
  `office_over` varchar(1024) DEFAULT NULL COMMENT '管辖机构',
  `position` int(3) unsigned zerofill DEFAULT '001' COMMENT '职位 0普通职员 1主管  2部门经理',
  `email` varchar(256) DEFAULT '' COMMENT '邮箱',
  `phone` varchar(128) DEFAULT '' COMMENT '电话',
  `mobile` varchar(128) DEFAULT '' COMMENT '手机',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态：0正常 1禁用',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登陆IP',
  `login_time` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user` varchar(128) DEFAULT '' COMMENT '添加者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(128) DEFAULT '' COMMENT '修改者',
  `remark` varchar(256) DEFAULT '' COMMENT '备注',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '是否删除:0不删除，1删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `userNameIndex` (`user_name`) USING BTREE,
  KEY `officeIdIndex` (`office_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, '管理员', 'system', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '1', '11', '11', '1101,1102,11030101,11030102,110301,11030201,11030202,110302,11030501,11030502,110305,11030701,11030702,110307,1103,11040101,11040102,110401,1104,11050101,11050102,110501,11050201,11050202,110502,1105,11', 001, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, '2017-08-22 09:36:26', 'system', NULL, 0);
INSERT INTO `sys_user` VALUES (2, '财务人员', 'cwry', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', 'ly123456', '11', '11030502', NULL, 000, NULL, NULL, '13436444855', 1, NULL, NULL, '2016-08-19 14:05:04', '吴彦祖', '2017-08-19 18:13:31', '吴彦祖', '演示', 0);
INSERT INTO `sys_user` VALUES (4, '运营人员', 'yyry', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '20170001', NULL, NULL, NULL, 001, '', '', '', 1, '', NULL, '2017-03-03 15:15:07', '', '2017-08-19 18:13:31', '', '', 0);
INSERT INTO `sys_user` VALUES (5, '催收管理员', 'csry', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '201700012', NULL, NULL, NULL, 001, '', '', '', 1, '', NULL, '2017-03-03 15:15:07', '', '2017-08-19 18:13:31', '', '', 0);
INSERT INTO `sys_user` VALUES (6, '催收专员', 'cszy', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '20170003', NULL, NULL, NULL, 001, '', '18258224675', '', 1, '', NULL, '2017-03-03 15:15:07', '', '2017-08-19 18:13:31', '', '', 0);
INSERT INTO `sys_user` VALUES (7, '客服人员', 'kfry', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '20170004', NULL, NULL, NULL, 001, '', '', '', 1, '', NULL, '2017-03-03 15:15:07', '', '2017-08-19 18:13:31', '', '', 0);
INSERT INTO `sys_user` VALUES (8, '风控人员', 'fkry', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '20170005', NULL, NULL, NULL, 001, '', '', '', 1, '', NULL, '2017-03-03 15:15:07', '', '2017-08-19 18:13:31', '', '', 0);
INSERT INTO `sys_user` VALUES (13, '余然', '余然', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '001', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, '2017-08-03 11:45:20', '管理员', '2017-08-03 21:49:22', '管理员', NULL, 0);
INSERT INTO `sys_user` VALUES (14, 'yuran', 'yuran', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '002', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, '2017-08-03 21:52:19', '管理员', '2017-08-03 22:05:40', '管理员', NULL, 0);
INSERT INTO `sys_user` VALUES (15, '赵井如', 'zhaojr', '5hdkhy4lpzxmhekudj5mb74sllliyfkbqqadbdy', '20170006', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-08-03 22:07:25', 'yuran', '2017-08-03 22:07:25', 'yuran', NULL, 0);
INSERT INTO `sys_user` VALUES (16, '郭琼', 'guoq', 'a32a3kdeuqwfu5znim3ih4iycqr5mtzqifs25ha', '20170007', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-08-03 22:08:08', 'yuran', '2017-08-03 22:08:08', 'yuran', NULL, 0);
INSERT INTO `sys_user` VALUES (17, '吴涛', 'wut', 'q5b5fmpe7syzfekudj5mb74sllliyfkbqqadbdy', '20170008', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-08-03 22:09:08', 'yuran', '2017-08-07 16:20:57', 'system', NULL, 0);
INSERT INTO `sys_user` VALUES (18, '丁晨曦', 'dingcx', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '20170009', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-08-03 22:10:21', 'yuran', '2017-08-03 22:10:21', 'yuran', NULL, 0);
INSERT INTO `sys_user` VALUES (19, '王蕾', 'wangl', 'tovhdmiziure25znim3ih4iycqr5mtzqifs25ha', '20170010', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-08-03 22:11:43', 'yuran', '2017-08-03 22:11:43', 'yuran', NULL, 0);
INSERT INTO `sys_user` VALUES (20, '彭艳', 'pengy', 'mxazriif2v6fdo3nsd7gvm4uuaa2sxi7auiea7a', '20170011', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-08-03 22:12:03', 'yuran', '2017-08-03 22:12:03', 'yuran', NULL, 0);
INSERT INTO `sys_user` VALUES (21, '熊思琪', 'xiongsq', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '20170012', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, '2017-08-03 22:12:43', 'yuran', '2017-08-04 09:46:02', 'yuran', NULL, 0);
INSERT INTO `sys_user` VALUES (22, '李倩', 'liq', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '20170013', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-08-03 22:13:04', 'yuran', '2017-08-03 22:13:04', 'yuran', NULL, 0);
INSERT INTO `sys_user` VALUES (23, '熊思瑞', 'xiongsr', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '20170014', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-08-04 09:45:48', '管理员', '2017-08-04 09:45:48', '管理员', NULL, 0);
INSERT INTO `sys_user` VALUES (24, '余然', 'yuran1990', 'yycqjbcqes77zhlt3l3noxxuddh5ca2vvnsmqti', '20170015', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-08-04 14:33:15', '管理员', '2017-08-04 14:33:15', '管理员', NULL, 0);
INSERT INTO `sys_user` VALUES (25, '张敏', 'zhangm', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '20170016', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-08-07 18:01:40', '管理员', '2017-08-07 18:01:40', '管理员', NULL, 0);
INSERT INTO `sys_user` VALUES (26, '苏丫琴', 'suyq', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '20170017', NULL, 'null', NULL, NULL, NULL, NULL, '13971205762', 0, NULL, NULL, '2017-08-11 11:21:47', '管理员', '2017-08-11 11:21:47', '管理员', NULL, 0);
INSERT INTO `sys_user` VALUES (27, '王小虎', 'wangxh', 'fnhfuubg5w32e5znim3ih4iycqr5mtzqifs25ha', '20170018', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-08-11 19:17:41', '管理员', '2017-08-11 19:17:41', '管理员', NULL, 0);
INSERT INTO `sys_user` VALUES (28, '鲁小晖', 'luxh', '37vze673s6gaka6jz4laj3nfflh5ca2vvnsmqti', '20170019', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, '2017-08-16 17:18:15', '余然', '2017-08-25 17:42:38', '余然', NULL, 0);
INSERT INTO `sys_user` VALUES (29, '陶冬冬', 'taodd', '3aqyiys2xjplxspd2xvi2pm525k6fonxzp2idsa', '20170020', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-08-17 16:03:44', '余然', '2017-08-19 18:13:31', '余然', NULL, 0);
INSERT INTO `sys_user` VALUES (30, '王玮', 'wwei', 'tlf5ernfbpjlpekudj5mb74sllliyfkbqqadbdy', '20170020', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-08-24 16:06:05', '余然', '2017-08-24 16:06:05', '余然', NULL, 0);
INSERT INTO `sys_user` VALUES (31, '邹念', 'znian', 'tuif6vcrtqkcl4exzxkpj2layua2sxi7auiea7a', '20170021', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, '2017-08-25 09:59:06', '余然', '2017-08-25 10:26:18', '余然', NULL, 0);
INSERT INTO `sys_user` VALUES (32, '陈锋', 'cfeng', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '20170022', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, '2017-08-25 09:59:39', '余然', '2017-08-25 10:26:18', '余然', NULL, 0);
INSERT INTO `sys_user` VALUES (33, '邹念', 'zounian', 'tuif6vcrtqkcl4exzxkpj2layua2sxi7auiea7a', '20170023', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-08-25 10:25:38', '余然', '2017-08-25 10:25:38', '余然', NULL, 0);
INSERT INTO `sys_user` VALUES (34, '陈锋', 'chenfeng', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '20170024', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-08-25 10:25:57', '余然', '2017-08-25 10:25:57', '余然', NULL, 0);
INSERT INTO `sys_user` VALUES (35, '钟久荣', 'zhongjr', 'unjises62wgkvzgltk5kztudiaa2sxi7auiea7a', '20170025', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-08-29 10:16:52', '余然', '2017-08-29 10:16:52', '余然', NULL, 0);
INSERT INTO `sys_user` VALUES (36, '肖梦云', 'xiaomy', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '20170026', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-09-04 10:49:32', '余然', '2017-09-04 10:49:32', 'system', NULL, 0);
INSERT INTO `sys_user` VALUES (37, '张帆', '13554400481', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', NULL, NULL, NULL, NULL, NULL, NULL, '13554400481', NULL, 0, NULL, NULL, '2017-09-14 21:06:26', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (38, 'tester', 'tester', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', 'test_1', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-09-20 16:31:54', '管理员', '2017-09-20 16:31:54', 'system', NULL, 0);
INSERT INTO `sys_user` VALUES (39, 'tester_2', 'tester_2', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', 'test_2', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-09-20 16:32:15', '管理员', '2017-09-20 16:32:15', '管理员', NULL, 0);
INSERT INTO `sys_user` VALUES (40, '刘远', 'liuyuan', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '2070027', NULL, NULL, NULL, NULL, NULL, '13720267736', NULL, 0, NULL, NULL, '2017-09-22 11:11:33', NULL, NULL, 'system', NULL, 0);
INSERT INTO `sys_user` VALUES (41, 'liuy', 'liuy', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '1001', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-09-29 09:47:06', '管理员', '2017-09-29 09:56:57', 'system', NULL, 0);
INSERT INTO `sys_user` VALUES (42, '良晨同创', 'lctc', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '1234rfds', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-09-30 17:15:58', '管理员', '2017-09-30 17:15:58', '管理员', NULL, 0);
INSERT INTO `sys_user` VALUES (43, '康晓雯', 'kangxiaowen', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '123456', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-10-11 14:24:30', '管理员', '2017-10-11 14:24:30', '管理员', NULL, 0);
INSERT INTO `sys_user` VALUES (44, '方国超', 'fang', 'fpdfjj4dle2bs5znim3ih4iycqr5mtzqifs25ha', '47', NULL, 'null', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, '2017-10-30 11:04:34', '管理员', '2017-10-30 11:04:34', '管理员', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NOT NULL COMMENT '角色主键',
  `user_id` int(11) NOT NULL COMMENT '用户主键',
  `level` tinyint(1) unsigned zerofill DEFAULT NULL COMMENT '级别',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `role_id_index` (`role_id`) USING BTREE,
  KEY `user_id_index` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1, 1, NULL);
INSERT INTO `sys_user_role` VALUES (2, 2, 2, NULL);
INSERT INTO `sys_user_role` VALUES (3, 3, 3, NULL);
INSERT INTO `sys_user_role` VALUES (4, 4, 4, NULL);
INSERT INTO `sys_user_role` VALUES (5, 5, 5, NULL);
INSERT INTO `sys_user_role` VALUES (6, 6, 6, NULL);
INSERT INTO `sys_user_role` VALUES (7, 17, 7, NULL);
INSERT INTO `sys_user_role` VALUES (8, 18, 8, NULL);
INSERT INTO `sys_user_role` VALUES (9, 2, 1, NULL);
INSERT INTO `sys_user_role` VALUES (10, 4, 1, NULL);
INSERT INTO `sys_user_role` VALUES (11, 5, 1, NULL);
INSERT INTO `sys_user_role` VALUES (12, 6, 1, NULL);
INSERT INTO `sys_user_role` VALUES (13, 17, 1, NULL);
INSERT INTO `sys_user_role` VALUES (14, 18, 1, NULL);
INSERT INTO `sys_user_role` VALUES (15, 6, 9, NULL);
INSERT INTO `sys_user_role` VALUES (16, 3, 10, NULL);
INSERT INTO `sys_user_role` VALUES (17, 3, 11, NULL);
INSERT INTO `sys_user_role` VALUES (18, 1, 12, NULL);
INSERT INTO `sys_user_role` VALUES (19, 4, 13, NULL);
INSERT INTO `sys_user_role` VALUES (20, 2, 14, NULL);
INSERT INTO `sys_user_role` VALUES (21, 1, 15, NULL);
INSERT INTO `sys_user_role` VALUES (22, 2, 15, NULL);
INSERT INTO `sys_user_role` VALUES (23, 3, 15, NULL);
INSERT INTO `sys_user_role` VALUES (24, 4, 15, NULL);
INSERT INTO `sys_user_role` VALUES (25, 5, 15, NULL);
INSERT INTO `sys_user_role` VALUES (26, 6, 15, NULL);
INSERT INTO `sys_user_role` VALUES (27, 17, 15, NULL);
INSERT INTO `sys_user_role` VALUES (28, 18, 15, NULL);
INSERT INTO `sys_user_role` VALUES (29, 18, 16, NULL);
INSERT INTO `sys_user_role` VALUES (31, 4, 18, NULL);
INSERT INTO `sys_user_role` VALUES (32, 17, 18, NULL);
INSERT INTO `sys_user_role` VALUES (33, 6, 18, NULL);
INSERT INTO `sys_user_role` VALUES (34, 5, 18, NULL);
INSERT INTO `sys_user_role` VALUES (35, 4, 19, NULL);
INSERT INTO `sys_user_role` VALUES (36, 4, 20, NULL);
INSERT INTO `sys_user_role` VALUES (37, 17, 21, NULL);
INSERT INTO `sys_user_role` VALUES (38, 4, 21, NULL);
INSERT INTO `sys_user_role` VALUES (39, 2, 22, NULL);
INSERT INTO `sys_user_role` VALUES (40, 4, 23, NULL);
INSERT INTO `sys_user_role` VALUES (41, 17, 23, NULL);
INSERT INTO `sys_user_role` VALUES (42, 1, 24, NULL);
INSERT INTO `sys_user_role` VALUES (43, 2, 24, NULL);
INSERT INTO `sys_user_role` VALUES (44, 3, 24, NULL);
INSERT INTO `sys_user_role` VALUES (45, 4, 24, NULL);
INSERT INTO `sys_user_role` VALUES (46, 5, 24, NULL);
INSERT INTO `sys_user_role` VALUES (47, 6, 24, NULL);
INSERT INTO `sys_user_role` VALUES (48, 17, 24, NULL);
INSERT INTO `sys_user_role` VALUES (49, 18, 24, NULL);
INSERT INTO `sys_user_role` VALUES (56, 2, 25, NULL);
INSERT INTO `sys_user_role` VALUES (57, 4, 26, NULL);
INSERT INTO `sys_user_role` VALUES (58, 17, 26, NULL);
INSERT INTO `sys_user_role` VALUES (59, 5, 26, NULL);
INSERT INTO `sys_user_role` VALUES (60, 6, 26, NULL);
INSERT INTO `sys_user_role` VALUES (61, 1, 27, NULL);
INSERT INTO `sys_user_role` VALUES (62, 2, 27, NULL);
INSERT INTO `sys_user_role` VALUES (63, 3, 27, NULL);
INSERT INTO `sys_user_role` VALUES (64, 4, 27, NULL);
INSERT INTO `sys_user_role` VALUES (65, 5, 27, NULL);
INSERT INTO `sys_user_role` VALUES (66, 6, 27, NULL);
INSERT INTO `sys_user_role` VALUES (67, 17, 27, NULL);
INSERT INTO `sys_user_role` VALUES (68, 18, 27, NULL);
INSERT INTO `sys_user_role` VALUES (69, 4, 28, NULL);
INSERT INTO `sys_user_role` VALUES (70, 17, 28, NULL);
INSERT INTO `sys_user_role` VALUES (71, 4, 29, NULL);
INSERT INTO `sys_user_role` VALUES (72, 18, 29, NULL);
INSERT INTO `sys_user_role` VALUES (73, 19, 15, NULL);
INSERT INTO `sys_user_role` VALUES (74, 19, 24, NULL);
INSERT INTO `sys_user_role` VALUES (75, 1, 30, NULL);
INSERT INTO `sys_user_role` VALUES (76, 18, 31, NULL);
INSERT INTO `sys_user_role` VALUES (77, 18, 32, NULL);
INSERT INTO `sys_user_role` VALUES (78, 20, 33, NULL);
INSERT INTO `sys_user_role` VALUES (79, 20, 34, NULL);
INSERT INTO `sys_user_role` VALUES (80, 18, 35, NULL);
INSERT INTO `sys_user_role` VALUES (83, 19, 1, NULL);
INSERT INTO `sys_user_role` VALUES (84, 6, 36, NULL);
INSERT INTO `sys_user_role` VALUES (85, 3, 37, NULL);
INSERT INTO `sys_user_role` VALUES (86, 5, 17, NULL);
INSERT INTO `sys_user_role` VALUES (88, 6, 39, NULL);
INSERT INTO `sys_user_role` VALUES (89, 2, 38, NULL);
INSERT INTO `sys_user_role` VALUES (94, 6, 41, NULL);
INSERT INTO `sys_user_role` VALUES (95, 21, 42, NULL);
INSERT INTO `sys_user_role` VALUES (96, 1, 43, NULL);
INSERT INTO `sys_user_role` VALUES (100, 1, 40, NULL);
INSERT INTO `sys_user_role` VALUES (101, 2, 40, NULL);
INSERT INTO `sys_user_role` VALUES (102, 5, 44, NULL);
INSERT INTO `sys_user_role` VALUES (103, 6, 44, NULL);
COMMIT;

-- ----------------------------
-- Table structure for third_pay
-- ----------------------------
DROP TABLE IF EXISTS `third_pay`;
CREATE TABLE `third_pay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `account` varchar(32) DEFAULT NULL COMMENT '第三方移动支付账号',
  `name` varchar(32) DEFAULT NULL COMMENT '第三方支付姓名',
  `operator` tinyint(4) DEFAULT NULL COMMENT '运营商 1-支付宝 2-微信',
  `state` tinyint(4) DEFAULT '0' COMMENT '状态 1默认使用 0-非默认使用',
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方支付信息表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(32) DEFAULT NULL COMMENT '登录名',
  `login_pwd` varchar(64) DEFAULT NULL COMMENT '登录密码',
  `loginpwd_modify_time` datetime DEFAULT NULL COMMENT '上次登录密码修改时间',
  `register_client` varchar(10) DEFAULT NULL COMMENT '注册客户端',
  `invitation_code` varchar(10) DEFAULT NULL COMMENT '邀请码',
  `channel_id` int(11) DEFAULT NULL COMMENT '渠道id',
  `level_id` bigint(20) DEFAULT NULL COMMENT '会员等级id',
  `register_ip` varchar(32) DEFAULT NULL COMMENT '注册ip',
  `login_ip` varchar(32) DEFAULT NULL COMMENT '登录ip',
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for user_base_info
-- ----------------------------
DROP TABLE IF EXISTS `user_base_info`;
CREATE TABLE `user_base_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `age` tinyint(4) DEFAULT NULL COMMENT '年龄',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别 0-未知 1-男 2-女',
  `national` varchar(32) DEFAULT NULL COMMENT '民族',
  `id_no` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `id_addr` varchar(256) DEFAULT NULL COMMENT '身份证地址',
  `living_img` varchar(256) DEFAULT NULL COMMENT '人脸识别地址',
  `front_img` varchar(256) DEFAULT NULL COMMENT '身份证正面',
  `back_img` varchar(256) DEFAULT NULL COMMENT '身份证背面',
  `education` varchar(30) DEFAULT NULL COMMENT '学历',
  `register_addr` varchar(256) DEFAULT NULL COMMENT '注册地址',
  `marry_state` tinyint(4) DEFAULT NULL COMMENT '婚姻状况 0-未知 1-已婚 2-未婚',
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';

-- ----------------------------
-- Table structure for user_invite
-- ----------------------------
DROP TABLE IF EXISTS `user_invite`;
CREATE TABLE `user_invite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `invite_time` datetime DEFAULT NULL COMMENT '邀请时间',
  `invite_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '被邀请人id',
  `invite_name` varchar(32) NOT NULL DEFAULT '' COMMENT '被邀请人名称',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '邀请人id',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '邀请人名称',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邀请记录表';

-- ----------------------------
-- Table structure for user_shipping_addr
-- ----------------------------
DROP TABLE IF EXISTS `user_shipping_addr`;
CREATE TABLE `user_shipping_addr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `area` varchar(32) DEFAULT NULL COMMENT '区',
  `detail_addr` varchar(1000) DEFAULT NULL COMMENT '详细地址',
  `state` tinyint(4) DEFAULT '0' COMMENT '是否默认收货地址 1是 0否',
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户收货地址表';

SET FOREIGN_KEY_CHECKS = 1;
