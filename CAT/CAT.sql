/*
Navicat MySQL Data Transfer

Source Server         : 192.168.88.130
Source Server Version : 50614
Source Host           : 192.168.88.130:3306
Source Database       : CAT

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2019-12-24 15:56:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_address
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地址id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `name` varchar(25) DEFAULT NULL COMMENT '地址名称',
  `phone` varchar(25) DEFAULT NULL,
  `addressInfo` varchar(50) DEFAULT NULL,
  `infos` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除 (0:否,1:是)',
  `version` int(10) DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `fk_adr_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='地址表';

-- ----------------------------
-- Records of t_address
-- ----------------------------
INSERT INTO `t_address` VALUES ('1', '1', 'admin', '13000000000', '河北省,石家庄市,新华区', '七夜校区', '2019-12-17 13:57:50', '2019-12-17 13:57:50', '0', '6');
INSERT INTO `t_address` VALUES ('2', '2', '王五', '14056595983', '湖南省,岳阳市,岳阳楼区', '七夜校区', '2019-12-24 16:46:20', '2019-12-24 16:46:20', '0', '6');
INSERT INTO `t_address` VALUES ('3', '1', '小李子', '13054098654', '内蒙古自治区,呼和浩特市,赛罕区', '七夜校区', '2019-12-12 23:39:44', '2019-12-12 23:39:44', '0', '3');
INSERT INTO `t_address` VALUES ('11', '2', '欧阳阿', '13222222222', '内蒙古自治区,呼和浩特市,土默特左旗', '七夜校区', '2019-12-24 16:34:46', '2019-12-24 16:34:46', '1', '2');
INSERT INTO `t_address` VALUES ('12', '2', '欧阳阿', '18765355663', '内蒙古自治区,乌海市,海勃湾区', '七夜校区', '2019-12-24 16:34:48', '2019-12-24 16:34:48', '1', '2');
INSERT INTO `t_address` VALUES ('13', '2', '欧阳阿', '13223333222', '河北省,唐山市,路北区', '七夜校区', '2019-12-24 16:34:50', '2019-12-24 16:34:50', '1', '2');
INSERT INTO `t_address` VALUES ('14', '2', '欧阳阿', '13222222222', '山西省,长治市,襄垣县', '七夜校区', '2019-12-24 16:34:54', '2019-12-24 16:34:54', '1', '0');
INSERT INTO `t_address` VALUES ('15', '2', '欧阳阿', '16545895566', '山西省,长治市,长治县', '七夜校区', '2019-12-24 16:34:59', '2019-12-24 16:34:59', '1', '0');
INSERT INTO `t_address` VALUES ('16', '2', '欧阳阿', '13222222222', '河北省,秦皇岛市,山海关区', '七夜校区', '2019-12-24 16:34:57', '2019-12-24 16:34:57', '1', '0');
INSERT INTO `t_address` VALUES ('17', '2', '欧阳阿', '15456896594', '河北省,秦皇岛市,山海关区', '七夜校区', '2019-12-24 16:28:12', '2019-12-24 16:28:12', '0', '0');
INSERT INTO `t_address` VALUES ('18', '2', '欧阳阿', '18459865535', '河北省,秦皇岛市,山海关区', '七夜校区', '2019-12-24 16:35:01', '2019-12-24 16:35:01', '1', '4');
INSERT INTO `t_address` VALUES ('19', '2', '欧阳阿', '14333333333', '河北省,秦皇岛市,山海关区', '七夜校区', '2019-12-24 16:28:15', '2019-12-24 16:28:15', '0', '4');
INSERT INTO `t_address` VALUES ('20', '2', '欧阳阿', '13232332323', '河北省,秦皇岛市,山海关区', '七夜校区', '2019-12-24 16:28:19', '2019-12-24 16:28:19', '0', '4');
INSERT INTO `t_address` VALUES ('21', '14', '222', '13223323233', '辽宁省,锦州市,黑山县', '七夜校区', '2019-12-24 16:46:57', '2019-12-24 16:46:57', '1', '5');
INSERT INTO `t_address` VALUES ('22', '14', '王五', '15648958654', '内蒙古自治区,包头市,石拐区', '七夜校区', '2019-12-10 23:50:00', '2019-12-10 23:50:00', '0', '4');
INSERT INTO `t_address` VALUES ('23', '16', '李总', '16545522655', '内蒙古自治区,鄂尔多斯市,准格尔旗', '七夜校区', '2019-12-12 23:39:44', '2019-12-12 23:39:44', '0', '4');
INSERT INTO `t_address` VALUES ('24', '14', '2222', '13213222222', '天津市,天津城区,河西区', '七夜校区', '2019-12-24 16:46:54', '2019-12-24 16:46:54', '1', '5');
INSERT INTO `t_address` VALUES ('25', '2', '张三', '16545210022', '河北省,石家庄市,长安区', '七夜校区', '2019-12-12 23:39:44', '2019-12-12 23:39:44', '0', '4');
INSERT INTO `t_address` VALUES ('26', '2', '欧阳阿', '16545200166', '山西省,大同市,矿区', '七夜校区', '2019-12-24 16:35:06', '2019-12-24 16:35:06', '1', '4');
INSERT INTO `t_address` VALUES ('27', '2', '欧阳阿', '13212322311', '天津市,天津城区,河东区', '七夜校区', '2019-12-24 16:28:23', '2019-12-24 16:28:23', '0', '6');
INSERT INTO `t_address` VALUES ('28', '26', '李周鹏', '13217400566', '湖南省,岳阳市,岳阳楼区', '七夜校区', '2019-12-24 16:47:09', '2019-12-24 16:47:09', '0', '2');
INSERT INTO `t_address` VALUES ('29', '26', '12312', '13217400566', '山西省,大同市,矿区', '七夜校区', '2019-12-24 16:47:07', '2019-12-24 16:47:07', '1', '1');
INSERT INTO `t_address` VALUES ('30', '1', '李四', '15469565635', '河北省,唐山市,路北区', '公安局', '2019-12-24 16:47:09', '2019-12-24 16:47:09', '0', '4');
INSERT INTO `t_address` VALUES ('31', '2', '元气满满小欧阳', '17673452044', '上海市,上海城区,浦东新区', 'bilibili大厦', '2019-12-24 16:47:10', '2019-12-24 16:47:10', '0', '2');

-- ----------------------------
-- Table structure for t_car
-- ----------------------------
DROP TABLE IF EXISTS `t_car`;
CREATE TABLE `t_car` (
  `car_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `drink_id` int(11) DEFAULT NULL COMMENT '饮品id',
  `picture` varchar(255) DEFAULT NULL,
  `name` varchar(25) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `num` int(11) DEFAULT '1',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(10) DEFAULT NULL COMMENT '版本号',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除(0:否,1:是)',
  PRIMARY KEY (`car_id`),
  KEY `user_id` (`user_id`),
  KEY `fk_car_d` (`drink_id`),
  CONSTRAINT `fk_car_d` FOREIGN KEY (`drink_id`) REFERENCES `t_drink` (`drink_id`),
  CONSTRAINT `fk_car_u` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='购物车表';

-- ----------------------------
-- Records of t_car
-- ----------------------------
INSERT INTO `t_car` VALUES ('1', '1', '2', '', '冰美式', '34.00', '1', '2019-12-12 22:57:33', '2019-12-12 22:57:33', '0', '1');
INSERT INTO `t_car` VALUES ('2', '1', '1', 'badc8ec1-423c-4910-9fa4-d1552802519d20170920041436157.jpg', '经典美式', '41.00', '1', '2019-12-12 22:57:34', '2019-12-12 22:57:34', '0', '1');
INSERT INTO `t_car` VALUES ('3', '1', '1', '908735e3-6964-43c6-b709-085e094060becold-brew.jpg', '冷萃冰咖啡', '21.00', '1', '2019-12-12 23:13:35', '2019-12-12 23:13:35', '0', '1');
INSERT INTO `t_car` VALUES ('4', '1', '2', 'fc9be876-5061-4914-8541-bc2886f5e402vanilla-flavor-sweet-cream-cold-brew.jpg', '轻甜奶油冷萃', '37.00', '1', '2019-12-12 23:13:35', '2019-12-12 23:13:35', '0', '1');
INSERT INTO `t_car` VALUES ('5', '1', '16', '6184deb6-59e5-4ee7-9068-6ac37da6b58dZNl4kVUGWxYbz8ybmZaCOB_xSwb7b1FLTGAxYSPnoOmD4NY92lDmpUAXabGVUF74uzFvxlbkWx5uwqY2qcjixFEuLYk00OmSS1IdNpm8K8sG4JN9RIm2mTKcbLtc2o2vfCF2ubeXzk49OsGrXt_KYDCngOyCwZK-s3fqawWswzk.jpg', '多肉菩提', '17.00', '1', '2019-12-12 23:13:35', '2019-12-12 23:13:35', '0', '1');
INSERT INTO `t_car` VALUES ('6', '1', '20', 'ec619dc5-8619-40bf-9c28-bcf864d0f48fU3vUjJnVtk4hsG3a1vOaNOOX0GcjKn24s36IEhshUWfVSww_0fEcxyUL9qzUCoximXKqvF8xz-Pgbz9r8ffpSA.jpg', '幽兰拿铁', '16.00', '1', '2019-12-12 23:00:50', '2019-12-12 23:00:50', '0', '1');
INSERT INTO `t_car` VALUES ('7', '1', '1', '908735e3-6964-43c6-b709-085e094060becold-brew.jpg', '冷萃冰咖啡', '21.00', '1', '2019-12-12 23:13:35', '2019-12-12 23:13:35', '0', '1');
INSERT INTO `t_car` VALUES ('8', '1', '2', 'fc9be876-5061-4914-8541-bc2886f5e402vanilla-flavor-sweet-cream-cold-brew.jpg', '轻甜奶油冷萃', '37.00', '1', '2019-12-12 23:13:54', '2019-12-12 23:13:54', '0', '1');
INSERT INTO `t_car` VALUES ('9', '1', '16', '6184deb6-59e5-4ee7-9068-6ac37da6b58dZNl4kVUGWxYbz8ybmZaCOB_xSwb7b1FLTGAxYSPnoOmD4NY92lDmpUAXabGVUF74uzFvxlbkWx5uwqY2qcjixFEuLYk00OmSS1IdNpm8K8sG4JN9RIm2mTKcbLtc2o2vfCF2ubeXzk49OsGrXt_KYDCngOyCwZK-s3fqawWswzk.jpg', '多肉菩提', '17.00', '1', '2019-12-12 23:13:54', '2019-12-12 23:13:54', '0', '1');
INSERT INTO `t_car` VALUES ('10', '1', '9', 'c4910673-ff04-4d11-9b5d-8bcf8c02ad95flat-white.jpg', '馥芮白', '29.00', '1', '2019-12-12 23:13:54', '2019-12-12 23:13:54', '0', '1');
INSERT INTO `t_car` VALUES ('11', '1', '30', 'facd72e1-95d9-4ebe-ada1-fd0d82c2eb07b9eb6e374f438be4.jpg', '小罐茶', '479.00', '1', '2019-12-12 23:15:47', '2019-12-12 23:15:47', '0', '1');
INSERT INTO `t_car` VALUES ('12', '1', '28', '283ddc85-9332-451e-adab-6c1e567836025b62dc2dN791e05e1.jpg', '老寿眉', '98.00', '1', '2019-12-12 23:15:47', '2019-12-12 23:15:47', '0', '1');
INSERT INTO `t_car` VALUES ('13', '1', '25', '9a337348-f62e-4db8-88d8-6de8e692ca731d6585e0a4a6d5ed.jpg', '碧螺春', '28.00', '1', '2019-12-12 23:15:47', '2019-12-12 23:15:47', '0', '1');
INSERT INTO `t_car` VALUES ('14', '1', '10', '23c30c3c-ef6b-4807-b91c-d358234504c5hazelnut-flavored-latte.jpg', '榛果风味拿铁', '19.00', '1', '2019-12-12 23:16:12', '2019-12-12 23:16:12', '0', '1');
INSERT INTO `t_car` VALUES ('15', '1', '8', '98bb4805-df8c-4239-8a9f-4f7fb359f4efcaramel-macchiato.jpg', '焦糖玛奇朵', '32.00', '1', '2019-12-12 23:16:12', '2019-12-12 23:16:12', '0', '1');
INSERT INTO `t_car` VALUES ('16', '1', '5', 'baea898b-ca9c-4b33-aff0-4c659d406deccaffe-latte.jpg', '拿铁', '24.00', '1', '2019-12-12 23:16:12', '2019-12-12 23:16:12', '0', '1');
INSERT INTO `t_car` VALUES ('17', '1', '17', '84037db0-e4a4-4fb8-8d01-1d2142e10e7ce139b7aa8b0b092cbc89c7b74d67159b863595.jpg', '波波茶', '19.00', '1', '2019-12-12 23:16:30', '2019-12-12 23:16:30', '0', '1');
INSERT INTO `t_car` VALUES ('18', '1', '18', 'ea4bd20e-0988-46c0-8609-c2e8782064c2974879adf9cef93750a0572739f6e3a034284.png', '焦糖奶茶', '25.00', '1', '2019-12-12 23:16:30', '2019-12-12 23:16:30', '0', '1');
INSERT INTO `t_car` VALUES ('19', '1', '19', '23f03329-6a63-4166-9aac-57fee66236006d0645830a1c4ffaa2cbc7da8cdf024436706.png', '冰激凌红茶', '18.00', '1', '2019-12-12 23:16:30', '2019-12-12 23:16:30', '0', '1');
INSERT INTO `t_car` VALUES ('20', '1', '2', 'fc9be876-5061-4914-8541-bc2886f5e402vanilla-flavor-sweet-cream-cold-brew.jpg', '轻甜奶油冷萃', '37.00', '1', '2019-12-12 23:16:52', '2019-12-12 23:16:52', '0', '1');
INSERT INTO `t_car` VALUES ('21', '1', '9', 'c4910673-ff04-4d11-9b5d-8bcf8c02ad95flat-white.jpg', '馥芮白', '29.00', '1', '2019-12-12 23:16:52', '2019-12-12 23:16:52', '0', '1');
INSERT INTO `t_car` VALUES ('22', '1', '12', '22f7920c-1deb-4dbf-9448-60289c7e94e33-20170406101608.png', '气质冷萃咖啡', '36.00', '1', '2019-12-12 23:16:52', '2019-12-12 23:16:52', '0', '1');
INSERT INTO `t_car` VALUES ('23', '1', '15', 'c57e9da8-0896-4045-9a66-59b49d1f268cinstore-nitro-cold-brew-float.jpg', '气致冷萃浮乐朵', '45.00', '1', '2019-12-12 23:16:52', '2019-12-12 23:16:52', '0', '1');
INSERT INTO `t_car` VALUES ('24', '1', '1', '908735e3-6964-43c6-b709-085e094060becold-brew.jpg', '冷萃冰咖啡', '21.00', '1', '2019-12-17 13:52:14', '2019-12-17 13:52:14', '0', '1');
INSERT INTO `t_car` VALUES ('25', '1', '2', 'fc9be876-5061-4914-8541-bc2886f5e402vanilla-flavor-sweet-cream-cold-brew.jpg', '轻甜奶油冷萃', '37.00', '1', '2019-12-17 13:52:14', '2019-12-17 13:52:14', '0', '1');
INSERT INTO `t_car` VALUES ('26', '1', '10', '23c30c3c-ef6b-4807-b91c-d358234504c5hazelnut-flavored-latte.jpg', '榛果风味拿铁', '19.00', '1', '2019-12-17 13:52:14', '2019-12-17 13:52:14', '0', '1');
INSERT INTO `t_car` VALUES ('27', '1', '12', '22f7920c-1deb-4dbf-9448-60289c7e94e33-20170406101608.png', '气质冷萃咖啡', '36.00', '1', '2019-12-13 01:21:50', '2019-12-13 01:21:50', '0', '1');
INSERT INTO `t_car` VALUES ('28', '1', '2', 'fc9be876-5061-4914-8541-bc2886f5e402vanilla-flavor-sweet-cream-cold-brew.jpg', '轻甜奶油冷萃', '37.00', '1', '2019-12-17 13:52:14', '2019-12-17 13:52:14', '0', '1');
INSERT INTO `t_car` VALUES ('29', '2', '15', 'c57e9da8-0896-4045-9a66-59b49d1f268cinstore-nitro-cold-brew-float.jpg', '气致冷萃浮乐朵', '45.00', '1', '2019-12-24 16:33:41', '2019-12-24 16:33:41', '0', '1');
INSERT INTO `t_car` VALUES ('30', '2', '20', 'ec619dc5-8619-40bf-9c28-bcf864d0f48fU3vUjJnVtk4hsG3a1vOaNOOX0GcjKn24s36IEhshUWfVSww_0fEcxyUL9qzUCoximXKqvF8xz-Pgbz9r8ffpSA.jpg', '幽兰拿铁', '16.00', '1', '2019-12-24 16:33:41', '2019-12-24 16:33:41', '0', '1');
INSERT INTO `t_car` VALUES ('31', '2', '27', '394933d1-8e3b-49ec-b5bd-0f837e9bd90df9e188eabf6c0e87.png', '金骏眉', '126.00', '1', '2019-12-24 16:31:54', '2019-12-24 16:31:54', '0', '1');

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `category_name` varchar(25) DEFAULT NULL COMMENT '分类名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级id',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除(0:否,1:是)',
  `version` int(10) DEFAULT NULL COMMENT '版本号',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人id',
  PRIMARY KEY (`category_id`),
  KEY `parent_id` (`parent_id`),
  KEY `create_user_id` (`create_user_id`),
  CONSTRAINT `fk_c_user` FOREIGN KEY (`create_user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='类别表';

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES ('1', '咖啡', '1', '2019-12-05 12:01:30', null, '0', '0', '1');
INSERT INTO `t_category` VALUES ('2', '茶', '2', '2019-12-05 13:31:03', '2019-12-05 13:31:03', '0', '0', '1');

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除(0:否,1:是)',
  `score` int(1) DEFAULT NULL COMMENT '评分(0:一星，1:二星，2:三星，3:四星，4:五星)',
  `version` int(10) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  CONSTRAINT `fk_com_drink` FOREIGN KEY (`comment_id`) REFERENCES `t_drink` (`drink_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='评论表';

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES ('1', '2019-12-12 00:37:30', '2019-12-12 00:37:30', '0', '4', '6');
INSERT INTO `t_comment` VALUES ('2', '2019-12-12 00:37:34', '2019-12-12 00:37:34', '0', '5', '1');
INSERT INTO `t_comment` VALUES ('3', '2019-12-12 00:37:23', '2019-12-12 00:37:23', '0', '3', '2');
INSERT INTO `t_comment` VALUES ('4', '2019-12-12 00:37:14', '2019-12-12 00:37:14', '0', '5', '1');
INSERT INTO `t_comment` VALUES ('5', '2019-12-06 11:03:16', '2019-12-06 11:03:16', '0', '1', '0');
INSERT INTO `t_comment` VALUES ('6', '2019-12-06 11:03:16', '2019-12-06 11:03:16', '0', '5', '0');
INSERT INTO `t_comment` VALUES ('7', '2019-12-06 11:03:16', '2019-12-06 11:03:16', '0', '5', '0');
INSERT INTO `t_comment` VALUES ('8', '2019-12-06 11:03:16', '2019-12-06 11:03:16', '0', '4', '0');
INSERT INTO `t_comment` VALUES ('9', '2019-12-06 11:03:16', '2019-12-06 11:03:16', '0', '2', '0');
INSERT INTO `t_comment` VALUES ('10', '2019-12-07 00:35:43', '2019-12-07 00:35:43', '0', '5', '0');
INSERT INTO `t_comment` VALUES ('11', '2019-12-07 00:35:43', '2019-12-07 00:35:43', '0', '4', '0');
INSERT INTO `t_comment` VALUES ('12', '2019-12-07 00:35:43', '2019-12-07 00:35:43', '0', '3', '0');
INSERT INTO `t_comment` VALUES ('13', '2019-12-07 00:35:43', '2019-12-07 00:35:43', '0', '2', '0');
INSERT INTO `t_comment` VALUES ('14', '2019-12-07 00:35:43', '2019-12-07 00:35:43', '0', '1', '0');
INSERT INTO `t_comment` VALUES ('15', '2019-12-07 00:35:43', '2019-12-07 00:35:43', '0', '5', '0');
INSERT INTO `t_comment` VALUES ('16', '2019-12-07 00:35:43', '2019-12-07 00:35:43', '0', '5', '0');
INSERT INTO `t_comment` VALUES ('17', '2019-12-07 00:35:43', '2019-12-07 00:35:43', '0', '4', '0');
INSERT INTO `t_comment` VALUES ('18', '2019-12-07 00:35:43', '2019-12-07 00:35:43', '0', '2', '0');
INSERT INTO `t_comment` VALUES ('19', '2019-12-07 00:35:47', '2019-12-07 00:35:47', '0', '5', '0');
INSERT INTO `t_comment` VALUES ('20', '2019-12-07 00:35:47', '2019-12-07 00:35:47', '0', '4', '0');
INSERT INTO `t_comment` VALUES ('21', '2019-12-07 00:35:47', '2019-12-07 00:35:47', '0', '3', '0');
INSERT INTO `t_comment` VALUES ('22', '2019-12-07 00:35:47', '2019-12-07 00:35:47', '0', '2', '0');
INSERT INTO `t_comment` VALUES ('23', null, null, '0', '5', '0');
INSERT INTO `t_comment` VALUES ('24', null, null, '0', '4', '0');
INSERT INTO `t_comment` VALUES ('25', '2019-12-11 14:50:17', null, '0', '5', '0');
INSERT INTO `t_comment` VALUES ('26', '2019-12-11 15:56:04', '2019-12-11 15:56:04', '0', '5', '0');
INSERT INTO `t_comment` VALUES ('27', '2019-12-11 15:56:05', '2019-12-11 15:56:05', '0', '5', '0');
INSERT INTO `t_comment` VALUES ('28', '2019-12-11 15:55:11', null, '0', '5', '0');
INSERT INTO `t_comment` VALUES ('29', '2019-12-11 14:27:16', null, '0', '5', '0');
INSERT INTO `t_comment` VALUES ('30', '2019-12-11 19:34:48', '2019-12-11 19:34:48', '0', '5', '3');
INSERT INTO `t_comment` VALUES ('31', '2019-12-24 16:44:22', '2019-12-24 16:44:22', '0', '2', '1');

-- ----------------------------
-- Table structure for t_drink
-- ----------------------------
DROP TABLE IF EXISTS `t_drink`;
CREATE TABLE `t_drink` (
  `drink_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '饮品id',
  `drink_name` varchar(25) DEFAULT NULL COMMENT '饮品名称',
  `drink_picture` varchar(255) DEFAULT NULL COMMENT '饮品照片',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `explain` varchar(255) DEFAULT NULL COMMENT '说明',
  `parent_id` int(11) DEFAULT NULL COMMENT '子id',
  `create_timesd` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除(0:否,1:是)',
  `version` int(10) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`drink_id`),
  KEY `child_id` (`parent_id`),
  CONSTRAINT `fk_dr_cate` FOREIGN KEY (`parent_id`) REFERENCES `t_category` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of t_drink
-- ----------------------------
INSERT INTO `t_drink` VALUES ('1', '冷萃冰咖啡', '908735e3-6964-43c6-b709-085e094060becold-brew.jpg', '21.00', '10多个小时的慢速萃取，带出微甜的巧克力般风味，与特别的柑橘般风味，为你奉上沁甘顺滑的味觉感受。', '1', '2019-12-12 00:29:54', '2019-12-11 23:01:22', '0', '13');
INSERT INTO `t_drink` VALUES ('2', '轻甜奶油冷萃', 'fc9be876-5061-4914-8541-bc2886f5e402vanilla-flavor-sweet-cream-cold-brew.jpg', '37.00', '在冷萃冰咖啡的基础上，添加香草风味轻奶油。每份轻奶油都是使用脱脂奶也更符合现代人的健康生活理念。', '1', '2019-12-12 00:32:57', '2019-12-11 23:04:26', '0', '4');
INSERT INTO `t_drink` VALUES ('3', '美式咖啡', 'cfb608fb-b466-428b-80c7-c9a32a0a128acaffe-americano.jpg', '24.00', '简单即是美味，萃取经典浓缩咖啡，以水调和，香气浓郁蔓溢。', '1', '2019-12-12 00:35:23', '2019-12-11 23:06:51', '0', '3');
INSERT INTO `t_drink` VALUES ('4', '浓缩咖啡', '5037038b-64ff-4381-917e-92272ec9fe7aespresso.jpg', '35.00', '萃取浓缩烘焙咖啡豆精华，每一口都馥郁满溢，伴随浓郁的焦糖香及多层次的口感。', '1', '2019-12-12 00:37:04', '2019-12-11 23:08:32', '0', '3');
INSERT INTO `t_drink` VALUES ('5', '拿铁', 'baea898b-ca9c-4b33-aff0-4c659d406deccaffe-latte.jpg', '24.00', '本色的经典咖啡。新鲜萃取的浓缩咖啡中缓缓倒入蒸煮牛奶，覆上轻柔奶泡，简单的香与纯', '1', '2019-12-12 00:46:33', '2019-12-11 23:18:02', '0', '3');
INSERT INTO `t_drink` VALUES ('6', '摩卡', 'ab6d1562-9f45-45d4-af36-913e52269faecaffe-mocha.jpg', '27.00', '在浓缩咖啡中加入摩卡酱，与蒸煮牛奶一起交织出丝滑与醇厚。', '1', '2019-12-12 00:47:31', '2019-12-11 23:18:59', '0', '3');
INSERT INTO `t_drink` VALUES ('7', '卡布奇诺', 'c968e990-83e7-4cae-8b09-596218ea5c05cappuccino.jpg', '29.00', '蒸煮牛奶与浓缩咖啡相融合，用丰厚绵密的奶泡覆顶，再撒上肉桂粉或可可粉。', '1', '2019-12-12 00:48:19', '2019-12-11 23:19:48', '0', '3');
INSERT INTO `t_drink` VALUES ('8', '焦糖玛奇朵', '98bb4805-df8c-4239-8a9f-4f7fb359f4efcaramel-macchiato.jpg', '32.00', '玛奇朵在意大利语中的意思是“印记”,从蒸煮牛奶和添加风味糖浆开始,再倒入醇厚的浓缩咖啡,留下属于玛奇朵的独有印记。', '1', '2019-12-12 00:50:16', '2019-12-11 23:21:44', '0', '4');
INSERT INTO `t_drink` VALUES ('9', '馥芮白', 'c4910673-ff04-4d11-9b5d-8bcf8c02ad95flat-white.jpg', '29.00', '选用星巴克精萃浓缩咖啡制成，融合绵密奶泡，风味更浓郁和甘甜。', '1', '2019-12-12 00:51:21', '2019-12-11 23:22:49', '0', '3');
INSERT INTO `t_drink` VALUES ('10', '榛果风味拿铁', '23c30c3c-ef6b-4807-b91c-d358234504c5hazelnut-flavored-latte.jpg', '19.00', '榛果风味与醇厚的浓缩咖啡相得益彰，融合在蒸煮牛奶中，带给你特别温暖的醇香。', '1', '2019-12-12 00:53:01', '2019-12-11 23:24:30', '0', '3');
INSERT INTO `t_drink` VALUES ('11', '香草风味拿铁', 'f504f910-9432-4dee-8c1a-f28924630e00vanilla-flavored-latte.jpg', '30.00', '清雅香甜的香草风味糖浆让浓缩咖啡变得浪漫起来，开启拿铁的香草浪漫之旅。', '1', '2019-12-12 00:54:30', '2019-12-11 23:25:58', '0', '1');
INSERT INTO `t_drink` VALUES ('12', '气质冷萃咖啡', '22f7920c-1deb-4dbf-9448-60289c7e94e33-20170406101608.png', '36.00', '用特别的加工方式，赋予冷萃咖啡绵密的泡沫，口感如天鹅绒般顺滑', '1', '2019-12-12 00:56:56', '2019-12-11 23:28:24', '0', '1');
INSERT INTO `t_drink` VALUES ('13', '阿馥奇朵', '2e6d8066-b646-418e-b2a1-eea94b0f1ce0affogato.jpg', '22.00', '双份精萃浓缩咖啡，新西兰进口香草冰淇淋', '1', '2019-12-12 01:02:25', '2019-12-11 23:33:53', '0', '1');
INSERT INTO `t_drink` VALUES ('14', '麦芽雪冷萃', '9989ee71-cde5-470b-b47b-08183673a3f0cold-brew-malt.jpg', '34.00', '冷萃咖啡，双份新西兰进口香草冰淇淋\n巧克力味苦味酒，麦芽成分', '1', '2019-12-12 01:03:15', '2019-12-11 23:34:43', '0', '1');
INSERT INTO `t_drink` VALUES ('15', '气致冷萃浮乐朵', 'c57e9da8-0896-4045-9a66-59b49d1f268cinstore-nitro-cold-brew-float.jpg', '45.00', '气致™冷萃咖啡，新西兰进口香草冰淇淋', '1', '2019-12-12 01:04:28', '2019-12-11 23:35:56', '0', '2');
INSERT INTO `t_drink` VALUES ('16', '多肉菩提', '6184deb6-59e5-4ee7-9068-6ac37da6b58dZNl4kVUGWxYbz8ybmZaCOB_xSwb7b1FLTGAxYSPnoOmD4NY92lDmpUAXabGVUF74uzFvxlbkWx5uwqY2qcjixFEuLYk00OmSS1IdNpm8K8sG4JN9RIm2mTKcbLtc2o2vfCF2ubeXzk49OsGrXt_KYDCngOyCwZK-s3fqawWswzk.jpg', '17.00', '喜茶同款', '2', '2019-12-12 01:17:46', '2019-12-11 23:49:14', '0', '1');
INSERT INTO `t_drink` VALUES ('17', '波波茶', '84037db0-e4a4-4fb8-8d01-1d2142e10e7ce139b7aa8b0b092cbc89c7b74d67159b863595.jpg', '19.00', '喜茶同款', '2', '2019-12-12 01:19:39', '2019-12-11 23:51:07', '0', '1');
INSERT INTO `t_drink` VALUES ('18', '焦糖奶茶', 'ea4bd20e-0988-46c0-8609-c2e8782064c2974879adf9cef93750a0572739f6e3a034284.png', '25.00', '1点点同款', '2', '2019-12-12 01:20:55', '2019-12-11 23:52:23', '0', '1');
INSERT INTO `t_drink` VALUES ('19', '冰激凌红茶', '23f03329-6a63-4166-9aac-57fee66236006d0645830a1c4ffaa2cbc7da8cdf024436706.png', '18.00', '1点点同款', '2', '2019-12-12 01:21:45', '2019-12-11 23:53:13', '0', '1');
INSERT INTO `t_drink` VALUES ('20', '幽兰拿铁', 'ec619dc5-8619-40bf-9c28-bcf864d0f48fU3vUjJnVtk4hsG3a1vOaNOOX0GcjKn24s36IEhshUWfVSww_0fEcxyUL9qzUCoximXKqvF8xz-Pgbz9r8ffpSA.jpg', '16.00', '茶颜悦色同款', '2', '2019-12-12 01:24:31', '2019-12-11 23:55:59', '0', '1');
INSERT INTO `t_drink` VALUES ('21', '声声乌龙', '0ef7ba9e-1813-4c75-b905-a4ed79fef4f4XXmID1jb8v5z_zUD7eIAYX5s8Rldsq2WlImzKqwCkamwK8Lj0colHBWeX6r06i-YmXKqvF8xz-Pgbz9r8ffpSA.jpg', '15.00', '茶颜悦色同款', '2', '2019-12-24 16:44:48', '2019-12-24 16:44:48', '0', '6');
INSERT INTO `t_drink` VALUES ('22', '抹茶菩提', 'ccaabdea-78c7-4e44-8eaa-a1ce66d42010F8pU3rYaTQzbyj8Onl2TVX75TL1y1IBZi6QgO14lTQZx3Aw923RiQBRqM7GBEBSYmXKqvF8xz-Pgbz9r8ffpSA.jpg', '16.00', '茶颜悦色同款', '2', '2019-12-24 16:44:49', '2019-12-24 16:44:49', '0', '6');
INSERT INTO `t_drink` VALUES ('23', '苦荞茶', '155aa9b3-c55b-40df-ab88-6a2dca6042da5aaf2227N5825fbb7.jpg', '56.90', '养颜，祛湿', '2', '2019-12-24 16:44:50', '2019-12-24 16:44:50', '0', '7');
INSERT INTO `t_drink` VALUES ('24', '祁门红茶', 'b7ca93d2-0f1c-4977-bf56-564dd46d7e1ca111a072ee390090.jpg', '53.90', '艺福堂 茶叶 特级祁门红茶类祁红 祁门工夫红茶可做奶茶配料', '2', '2019-12-24 16:44:52', '2019-12-24 16:44:52', '0', '7');
INSERT INTO `t_drink` VALUES ('25', '碧螺春', '9a337348-f62e-4db8-88d8-6de8e692ca731d6585e0a4a6d5ed.jpg', '28.00', '碧螺春', '2', '2019-12-24 16:44:53', '2019-12-24 16:44:53', '0', '7');
INSERT INTO `t_drink` VALUES ('26', '君山银针', '368321a6-bdd6-47e2-8d66-921e7560f0fa2d686721b24c2436.jpg', '119.00', '湖南岳阳君山当地的银针', '2', '2019-12-24 16:44:55', '2019-12-24 16:44:55', '0', '7');
INSERT INTO `t_drink` VALUES ('27', '金骏眉', '394933d1-8e3b-49ec-b5bd-0f837e9bd90df9e188eabf6c0e87.png', '126.00', '木桶实木礼盒装 武夷正山小种新茶金俊眉', '2', '2019-12-24 16:44:57', '2019-12-24 16:44:57', '0', '7');
INSERT INTO `t_drink` VALUES ('28', '老寿眉', '283ddc85-9332-451e-adab-6c1e567836025b62dc2dN791e05e1.jpg', '98.00', '八享时福建白茶老寿眉', '2', '2019-12-24 16:44:58', '2019-12-24 16:44:58', '0', '5');
INSERT INTO `t_drink` VALUES ('29', '平阳黄汤', '3344db7e-c02c-4aca-8a42-dff21b390f533f51ab4ef1db4c3f.jpg', '82.00', '子久 平阳黄汤 黄芽 黄茶', '2', '2019-12-24 16:44:59', '2019-12-24 16:44:59', '0', '5');
INSERT INTO `t_drink` VALUES ('30', '小罐茶', 'facd72e1-95d9-4ebe-ada1-fd0d82c2eb07b9eb6e374f438be4.jpg', '479.00', '小罐茶，大师作', '2', '2019-12-24 16:45:00', '2019-12-24 16:45:00', '0', '5');
INSERT INTO `t_drink` VALUES ('31', '冷萃冰咖啡3', '4ebeb86f-9486-46aa-8ec4-1a7a76da12b2ZNl4kVUGWxYbz8ybmZaCOB_xSwb7b1FLTGAxYSPnoOmD4NY92lDmpUAXabGVUF74uzFvxlbkWx5uwqY2qcjixFEuLYk00OmSS1IdNpm8K8sG4JN9RIm2mTKcbLtc2o2vfCF2ubeXzk49OsGrXt_KYDCngOyCwZK-s3fqawWswzk.jpg', '77.00', '使用冷液萃取盗版的冰咖啡', '1', '2019-12-24 16:45:04', '2019-12-24 16:45:04', '1', '6');

-- ----------------------------
-- Table structure for t_drink_order
-- ----------------------------
DROP TABLE IF EXISTS `t_drink_order`;
CREATE TABLE `t_drink_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `drink_id` int(11) DEFAULT NULL COMMENT '饮品id',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  `drink_num` int(25) DEFAULT NULL COMMENT '订单下饮品数量',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `fk_z_order` (`order_id`),
  KEY `fk_z_drink` (`drink_id`),
  CONSTRAINT `fk_z_drink` FOREIGN KEY (`drink_id`) REFERENCES `t_drink` (`drink_id`),
  CONSTRAINT `fk_z_order` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品和订单中间表';

-- ----------------------------
-- Records of t_drink_order
-- ----------------------------

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(25) DEFAULT NULL COMMENT '用户名',
  `address_id` int(11) DEFAULT NULL COMMENT '收货地址',
  `drink_name` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `price` decimal(10,2) DEFAULT NULL,
  `state` int(1) DEFAULT NULL COMMENT '订单状态（0为未通过，1已通过）',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(10) DEFAULT NULL COMMENT '版本号',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除(0:否,1:是)',
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', '2', '张三', '19', '经典美式,冰美式,枫叶飘飘,illy 意式浓缩,Machiatto', '139.00', '0', '2019-12-12 23:37:34', '2019-12-12 23:37:34', '8', '0');
INSERT INTO `t_order` VALUES ('2', '2', '张三', '19', '经典美式,冰美式,枫叶飘飘,illy 意式浓缩,Machiatto', '139.00', '0', '2019-12-24 16:34:33', '2019-12-24 16:34:33', '9', '1');
INSERT INTO `t_order` VALUES ('3', '2', '张三', '1', '经典美式,冰美式,枫叶飘飘,illy 意式浓缩,Machiatto', '139.00', '0', '2019-12-12 23:46:14', '2019-12-12 23:46:14', '8', '0');
INSERT INTO `t_order` VALUES ('4', '2', '张三', '1', '经典美式,冰美式,枫叶飘飘,illy 意式浓缩,Machiatto', '139.00', '0', '2019-12-24 16:34:32', '2019-12-24 16:34:32', '6', '1');
INSERT INTO `t_order` VALUES ('5', '2', '张三', '1', '经典美式,冰美式,枫叶飘飘,illy 意式浓缩,Machiatto', '139.00', '0', '2019-12-12 23:39:15', '2019-12-12 23:39:15', '10', '0');
INSERT INTO `t_order` VALUES ('6', '2', '张三', '20', '经典美式,冰美式,枫叶飘飘,illy 意式浓缩,Machiatto', '139.00', '0', '2019-12-24 16:34:35', '2019-12-24 16:34:35', '9', '1');
INSERT INTO `t_order` VALUES ('7', '2', '张三', '1', '经典美式,冰美式,枫叶飘飘,illy 意式浓缩,Machiatto', '139.00', '0', '2019-12-24 16:34:30', '2019-12-24 16:34:30', '6', '1');
INSERT INTO `t_order` VALUES ('8', '2', '张三', '19', '经典美式,冰美式,枫叶飘飘,illy 意式浓缩,Machiatto', '139.00', '0', '2019-12-12 23:37:46', '2019-12-12 23:37:46', '4', '0');
INSERT INTO `t_order` VALUES ('9', '16', '2222', '23', '经典美式', '23.00', '0', '2019-12-10 21:16:35', '2019-12-10 21:16:35', '4', '0');
INSERT INTO `t_order` VALUES ('10', '14', '王五', '21', '经典美式,espresso macchiato,枫叶飘飘,经典美式,冰美式,Costa 美式,冰美式,经典美式', '213.00', '0', '2019-12-10 21:16:37', '2019-12-10 21:16:37', '4', '0');
INSERT INTO `t_order` VALUES ('11', '14', '王五', '22', '经典美式,经典意式浓缩,Costa 美式,冰美式', '150.00', '1', '2019-12-17 13:57:43', '2019-12-17 13:57:43', '10', '0');
INSERT INTO `t_order` VALUES ('12', '2', '张三', '22', '经典美式,经典意式浓缩,Costa 美式,冰美', '150.00', '1', '2019-12-17 13:57:43', '2019-12-17 13:57:43', '11', '0');
INSERT INTO `t_order` VALUES ('13', '2', '张三', '22', '经典美式,经典意式浓缩,Costa 美式,冰美', '150.00', '1', '2019-12-17 13:57:43', '2019-12-17 13:57:43', '11', '0');
INSERT INTO `t_order` VALUES ('14', '2', '张三', '22', '经典美式,经典意式浓缩,Costa 美式,冰美', '150.00', '1', '2019-12-17 13:57:43', '2019-12-17 13:57:43', '10', '0');
INSERT INTO `t_order` VALUES ('15', '26', '测试用户', '28', '经典意式浓缩,祁门红茶', '107.00', '1', '2019-12-17 13:57:43', '2019-12-17 13:57:43', '6', '0');
INSERT INTO `t_order` VALUES ('16', '1', 'admin', '3', '冷萃冰咖啡,轻甜奶油冷萃,多肉菩提,冷萃冰咖啡', '96.00', '1', '2019-12-17 13:57:43', '2019-12-17 13:57:43', '2', '0');
INSERT INTO `t_order` VALUES ('17', '1', 'admin', '1', '轻甜奶油冷萃,多肉菩提,馥芮白', '83.00', '1', '2019-12-17 13:57:43', '2019-12-17 13:57:43', '5', '0');
INSERT INTO `t_order` VALUES ('18', '1', 'admin', '3', '小罐茶,老寿眉,碧螺春', '605.00', '1', '2019-12-17 13:57:43', '2019-12-17 13:57:43', '2', '0');
INSERT INTO `t_order` VALUES ('19', '1', 'admin', '3', '榛果风味拿铁,焦糖玛奇朵,拿铁', '75.00', '1', '2019-12-17 13:57:43', '2019-12-17 13:57:43', '2', '0');
INSERT INTO `t_order` VALUES ('20', '1', 'admin', '1', '波波茶,焦糖奶茶,冰激凌红茶', '62.00', '1', '2019-12-17 13:57:43', '2019-12-17 13:57:43', '2', '0');
INSERT INTO `t_order` VALUES ('21', '1', 'admin', '3', '轻甜奶油冷萃,馥芮白,气质冷萃咖啡,气致冷萃浮乐朵', '147.00', '1', '2019-12-24 16:45:29', '2019-12-24 16:45:29', '5', '0');
INSERT INTO `t_order` VALUES ('22', '1', 'admin', '1', '冷萃冰咖啡,轻甜奶油冷萃,榛果风味拿铁,轻甜奶油冷萃', '114.00', '1', '2019-12-24 16:45:26', '2019-12-24 16:45:26', '4', '0');
INSERT INTO `t_order` VALUES ('23', '2', '张三', '31', '气致冷萃浮乐朵,幽兰拿铁', '61.00', '1', '2019-12-24 16:45:27', '2019-12-24 16:45:27', '3', '0');
INSERT INTO `t_order` VALUES ('24', '2', '张三', '27', null, '61.00', '0', '2019-12-24 16:45:32', '2019-12-24 16:45:32', '3', '1');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(25) DEFAULT NULL COMMENT '角色名',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` int(10) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '管理员', '2019-12-05 11:55:30', null, '0');
INSERT INTO `t_role` VALUES ('2', '用户', '2019-12-06 17:19:13', '2019-12-06 17:19:13', '0');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `account` varchar(25) DEFAULT NULL COMMENT '用户账号',
  `sex` int(1) DEFAULT NULL COMMENT '性别(0:男，1:女)',
  `password` varchar(25) DEFAULT NULL COMMENT '用户密码',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `version` int(10) DEFAULT NULL COMMENT '版本号',
  `is_deleted` int(1) DEFAULT NULL COMMENT '是否删除(0:否,1:是)',
  `user_role_id` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`),
  KEY `user_role_id` (`user_role_id`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`user_role_id`) REFERENCES `t_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '0', '111111', '13217400566', '2019-12-24 16:39:58', '2019-12-24 16:39:58', '5', '0', '1');
INSERT INTO `t_user` VALUES ('2', '张三', '1', '998877', '17673452044', '2019-12-24 16:39:23', '2019-12-24 16:39:23', '10', '0', '2');
INSERT INTO `t_user` VALUES ('14', '尹俊', '0', '123321', '17674044314', '2019-12-12 00:26:21', '2019-12-12 00:26:21', '8', '0', '2');
INSERT INTO `t_user` VALUES ('15', '马天放', '0', '998877', '18613962804', '2019-12-12 00:26:21', '2019-12-12 00:26:21', '3', '0', '2');
INSERT INTO `t_user` VALUES ('16', '贺新雨', '0', '111111', '13545622655', '2019-12-12 00:26:22', '2019-12-12 00:26:22', '3', '0', '2');
INSERT INTO `t_user` VALUES ('17', '束千秋', '1', '123456', '13545622655', '2019-12-12 00:26:22', '2019-12-12 00:26:22', '3', '0', '2');
INSERT INTO `t_user` VALUES ('18', '翁芳华', '1', '123456', '13545622655', '2019-12-12 00:26:23', '2019-12-12 00:26:23', '3', '0', '2');
INSERT INTO `t_user` VALUES ('19', '史乃', '1', '123456', '13545645294', '2019-12-12 00:26:23', '2019-12-12 00:26:23', '5', '0', '2');
INSERT INTO `t_user` VALUES ('20', '良歆', '1', '123456', '13545845261', '2019-12-12 00:26:25', '2019-12-12 00:26:25', '3', '0', '2');
INSERT INTO `t_user` VALUES ('21', '毛鸿煊', '0', '123456', '13545156123', '2019-12-12 00:26:26', '2019-12-12 00:26:26', '5', '0', '2');
INSERT INTO `t_user` VALUES ('22', '傅亦云', '0', '123456', '15894668954', '2019-12-24 16:40:24', '2019-12-24 16:40:24', '9', '0', '2');
INSERT INTO `t_user` VALUES ('23', '梅庄静', '1', '123456', '18975348979', '2019-12-24 16:40:25', '2019-12-24 16:40:25', '14', '0', '2');
INSERT INTO `t_user` VALUES ('24', '马语风', '1', '123456', '19846498425', '2019-12-24 16:40:25', '2019-12-24 16:40:25', '16', '0', '2');
INSERT INTO `t_user` VALUES ('25', '韦德辉', '0', '123456', '15564663242', '2019-12-24 16:40:26', '2019-12-24 16:40:26', '9', '0', '2');
INSERT INTO `t_user` VALUES ('26', '田同化', '0', '123456', '15499865746', '2019-12-24 16:40:27', '2019-12-24 16:40:27', '6', '0', '2');
INSERT INTO `t_user` VALUES ('27', '邴言', '0', '123456', '15561365465', '2019-12-24 16:40:27', '2019-12-24 16:40:27', '6', '0', '2');
INSERT INTO `t_user` VALUES ('28', '蒲小星', '0', '123456', '17451613216', '2019-12-24 16:40:28', '2019-12-24 16:40:28', '3', '0', '2');
INSERT INTO `t_user` VALUES ('29', '欧阳共和', '1', '123123', '18455629615', '2019-12-24 16:40:50', '2019-12-24 15:37:53', '4', '0', '2');
INSERT INTO `t_user` VALUES ('30', 'RNG_XDD', '0', '159357', '15649354952', '2019-12-24 16:41:46', null, '0', '0', '2');
