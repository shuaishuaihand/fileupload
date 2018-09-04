/*
Navicat MySQL Data Transfer

Source Server         : nishuai
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : file_upload

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2018-09-04 10:31:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lastName` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `empStatus` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38252 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('38222', '张三', '2714763867@qq.com', '男', null, null);
INSERT INTO `employee` VALUES ('38223', '李四', '213414', '男', null, null);
INSERT INTO `employee` VALUES ('38224', '丽丽', '213541345', '女', null, null);
INSERT INTO `employee` VALUES ('38225', 'jim', '21341234', '男', null, null);
INSERT INTO `employee` VALUES ('38226', 'Tom', '32441345', '男', null, null);
INSERT INTO `employee` VALUES ('38227', 'jack', '52462476567', 'man', null, null);
INSERT INTO `employee` VALUES ('38228', 'rose', '345315415', 'woman', null, null);
INSERT INTO `employee` VALUES ('38229', '杨明', '24·234', '男', null, null);
INSERT INTO `employee` VALUES ('38230', '张丽', '233434', '女', null, null);
INSERT INTO `employee` VALUES ('38231', '503cd', 'b', '1', null, null);
INSERT INTO `employee` VALUES ('38232', '张三', '2714763867@qq.com', '男', null, null);
INSERT INTO `employee` VALUES ('38233', '李四', '213414', '男', null, null);
INSERT INTO `employee` VALUES ('38234', '丽丽', '213541345', '女', null, null);
INSERT INTO `employee` VALUES ('38235', 'jim', '21341234', '男', null, null);
INSERT INTO `employee` VALUES ('38236', 'Tom', '32441345', '男', null, null);
INSERT INTO `employee` VALUES ('38237', 'jack', '52462476567', 'man', null, null);
INSERT INTO `employee` VALUES ('38238', 'rose', '345315415', 'woman', null, null);
INSERT INTO `employee` VALUES ('38239', '杨明', '24·234', '男', null, null);
INSERT INTO `employee` VALUES ('38240', '张丽', '233434', '女', null, null);
INSERT INTO `employee` VALUES ('38241', '503cd', 'b', '1', null, null);
INSERT INTO `employee` VALUES ('38242', '张三', '2714763867@qq.com', '男', null, null);
INSERT INTO `employee` VALUES ('38243', '李四', '213414', '男', null, null);
INSERT INTO `employee` VALUES ('38244', '丽丽', '213541345', '女', null, null);
INSERT INTO `employee` VALUES ('38245', 'jim', '21341234', '男', null, null);
INSERT INTO `employee` VALUES ('38246', 'Tom', '32441345', '男', null, null);
INSERT INTO `employee` VALUES ('38247', 'jack', '52462476567', 'man', null, null);
INSERT INTO `employee` VALUES ('38248', 'rose', '345315415', 'woman', null, null);
INSERT INTO `employee` VALUES ('38249', '杨明', '24·234', '男', null, null);
INSERT INTO `employee` VALUES ('38250', '张丽', '233434', '女', null, null);
INSERT INTO `employee` VALUES ('38251', '503cd', 'b', '1', null, null);

-- ----------------------------
-- Table structure for f_advertisement
-- ----------------------------
DROP TABLE IF EXISTS `f_advertisement`;
CREATE TABLE `f_advertisement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adv_type` int(11) DEFAULT NULL COMMENT '广告类型：0视频，1图片',
  `adv_size` varchar(255) DEFAULT NULL COMMENT '广告占用空间',
  `adv_time` int(11) DEFAULT '0' COMMENT '广告时长—视频（s）',
  `create_time` datetime DEFAULT NULL COMMENT '上传日期',
  `adv_resolving_power` varchar(255) DEFAULT NULL COMMENT '广告分辨率（备用）(kb/s)',
  `adv_url` varchar(255) DEFAULT NULL COMMENT '广告地址(视频帧地址，图片地址)',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名称',
  `file_type` varchar(255) DEFAULT NULL COMMENT '文件格式',
  `file_url` varchar(255) DEFAULT NULL COMMENT '文件地址（视频地址）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1430 DEFAULT CHARSET=utf8 COMMENT='广告表';

-- ----------------------------
-- Records of f_advertisement
-- ----------------------------
INSERT INTO `f_advertisement` VALUES ('1411', '0', '28.01MB', '561', '2018-09-03 15:14:39', '1280x800', '/screenvideo/videoimage/0f8daacb55664301abae68b7c8a3e4a9.png', '05-spring项目 - 副本', '.flv', '/videofile/0f8daacb55664301abae68b7c8a3e4a9.flv');
INSERT INTO `f_advertisement` VALUES ('1412', '0', '28.01MB', '561', '2018-09-03 15:16:57', '1280x800', '/screenvideo/videoimage/f2c4dcdb4fec4971b9b99eef83362453.png', '05-spring项目 - 副本(1)', '.flv', '/videofile/f2c4dcdb4fec4971b9b99eef83362453.flv');
INSERT INTO `f_advertisement` VALUES ('1413', '0', '28.01MB', '561', '2018-09-03 15:16:57', '1280x800', '/screenvideo/videoimage/50d58869a03745068aca5feab1307ceb.png', '05-spring项目', '.flv', '/videofile/50d58869a03745068aca5feab1307ceb.flv');
INSERT INTO `f_advertisement` VALUES ('1414', '0', '39.19MB', '667', '2018-09-03 15:47:45', '1280x800', '/screenvideo/videoimage/577130545adf458aae5bdd6c772da99c.png', '06-spring项目概念 - 副本', '.flv', '/videofile/577130545adf458aae5bdd6c772da99c.flv');
INSERT INTO `f_advertisement` VALUES ('1415', '0', '39.19MB', '667', '2018-09-03 15:47:45', '1280x800', '/screenvideo/videoimage/7b42578d90d6442eb3b78fd04552988c.png', '06-spring项目概念', '.flv', '/videofile/7b42578d90d6442eb3b78fd04552988c.flv');
INSERT INTO `f_advertisement` VALUES ('1416', '0', '28.01MB', '561', '2018-09-03 16:10:03', '1280x800', '/screenvideo/videoimage/0f7f32aefb8d4d4daecb0ea37e301b99.png', '05-spring项目 - 副本 - 副本', '.flv', '/videofile/0f7f32aefb8d4d4daecb0ea37e301b99.flv');
INSERT INTO `f_advertisement` VALUES ('1417', '0', '28.01MB', '561', '2018-09-03 16:10:04', '1280x800', '/screenvideo/videoimage/f6345f3039324ff493089617e51b716e.png', '05-spring项目 - 副本(2)', '.flv', '/videofile/f6345f3039324ff493089617e51b716e.flv');
INSERT INTO `f_advertisement` VALUES ('1418', '0', '28.01MB', '561', '2018-09-03 16:10:04', '1280x800', '/screenvideo/videoimage/8a4c4a6a6aa74619a05367ba51599787.png', '05-spring项目(1)', '.flv', '/videofile/8a4c4a6a6aa74619a05367ba51599787.flv');
INSERT INTO `f_advertisement` VALUES ('1419', '0', '29.93MB', '369', '2018-09-03 16:10:06', '1280x800', '/screenvideo/videoimage/bcaae13c34a24415b300da3ee7fa3681.png', '07-spring配置详解-Bean元素_', '.flv', '/videofile/bcaae13c34a24415b300da3ee7fa3681.flv');
INSERT INTO `f_advertisement` VALUES ('1420', '0', '39.19MB', '667', '2018-09-03 16:10:06', '1280x800', '/screenvideo/videoimage/7f2b02d7efa74470968f7a2b4a955a25.png', '06-spring项目概念(1)', '.flv', '/videofile/7f2b02d7efa74470968f7a2b4a955a25.flv');
INSERT INTO `f_advertisement` VALUES ('1421', '0', '39.19MB', '667', '2018-09-03 16:10:06', '1280x800', '/screenvideo/videoimage/973b6190ffe44a1594b8798d54e108fa.png', '06-spring项目概念 - 副本(1)', '.flv', '/videofile/973b6190ffe44a1594b8798d54e108fa.flv');
INSERT INTO `f_advertisement` VALUES ('1422', '0', '35.66MB', '547', '2018-09-03 16:10:07', '1280x800', '/screenvideo/videoimage/b62c09fa26ac45e9b932bc0d1894fa42.png', '09-spring配置详解-scope属性_', '.flv', '/videofile/b62c09fa26ac45e9b932bc0d1894fa42.flv');
INSERT INTO `f_advertisement` VALUES ('1423', '0', '64.60MB', '761', '2018-09-03 16:10:10', '1280x800', '/screenvideo/videoimage/4b4ad9bc13674d6e864d9693f00452b2.png', '08-spring配置详解', '.flv', '/videofile/4b4ad9bc13674d6e864d9693f00452b2.flv');
INSERT INTO `f_advertisement` VALUES ('1424', '0', '29.93MB', '369', '2018-09-03 16:10:23', '1280x800', '/screenvideo/videoimage/d0537e6c864b4dab94e66f4bfed2b221.png', '07-spring配置详解-Bean元素_ - 副本', '.flv', '/videofile/d0537e6c864b4dab94e66f4bfed2b221.flv');
INSERT INTO `f_advertisement` VALUES ('1425', '0', '39.19MB', '667', '2018-09-03 16:10:24', '1280x800', '/screenvideo/videoimage/a7599bb90230424e8b0e8deed7c9a2e2.png', '06-spring项目概念 - 副本 - 副本', '.flv', '/videofile/a7599bb90230424e8b0e8deed7c9a2e2.flv');
INSERT INTO `f_advertisement` VALUES ('1426', '0', '39.19MB', '667', '2018-09-03 16:10:24', '1280x800', '/screenvideo/videoimage/2b561cd17abe45fea1e4e7c1243865ae.png', '06-spring项目概念 - 副本 (2)', '.flv', '/videofile/2b561cd17abe45fea1e4e7c1243865ae.flv');
INSERT INTO `f_advertisement` VALUES ('1427', '0', '35.66MB', '547', '2018-09-03 16:10:26', '1280x800', '/screenvideo/videoimage/8dd334914b574ab0afac9803ef5c39d2.png', '09-spring配置详解-scope属性_ - 副本', '.flv', '/videofile/8dd334914b574ab0afac9803ef5c39d2.flv');
INSERT INTO `f_advertisement` VALUES ('1428', '0', '27.20MB', '358', '2018-09-03 16:10:26', '1280x800', '/screenvideo/videoimage/b9c8488c813d44dfb0c091bfe6543162.png', '10-spring配置详解-初始化 - 副本 - 副本', '.flv', '/videofile/b9c8488c813d44dfb0c091bfe6543162.flv');
INSERT INTO `f_advertisement` VALUES ('1429', '0', '28.01MB', '561', '2018-09-03 16:34:11', '1280x800', '/screenvideo/videoimage/a6ee372fd3ef42ea8eda88a199cbcd5d.png', '05-spring', '.flv', '/videofile/a6ee372fd3ef42ea8eda88a199cbcd5d.flv');

-- ----------------------------
-- Table structure for f_adv_plan
-- ----------------------------
DROP TABLE IF EXISTS `f_adv_plan`;
CREATE TABLE `f_adv_plan` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `adv_id` int(11) DEFAULT NULL COMMENT '广告ID',
  `plan_id` int(11) DEFAULT NULL COMMENT '发布计划',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发布计划与广告关联表';

-- ----------------------------
-- Records of f_adv_plan
-- ----------------------------

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL,
  `111` varchar(255) DEFAULT NULL,
  `22` varchar(255) DEFAULT NULL,
  `333` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1', '111', '22', '333');
INSERT INTO `test` VALUES ('2', '222', '22', '333');
INSERT INTO `test` VALUES ('3', '111', '22', '333');
INSERT INTO `test` VALUES ('4', '222', '22', '333');
