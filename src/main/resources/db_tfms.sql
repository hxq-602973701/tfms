/*
Navicat MySQL Data Transfer

Source Server         : mine
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : db_tfms

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2019-03-18 18:42:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_activitys
-- ----------------------------
DROP TABLE IF EXISTS `t_activitys`;
CREATE TABLE `t_activitys` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `createTime` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_activitys
-- ----------------------------
INSERT INTO `t_activitys` VALUES ('2', '<p>测试</p>', '2019-03-14 05:03:20', '系统上线', '8b31cf16-75b6-4201-9a72-f06ea2ab43d0_201210070320020.jpg', '1');
INSERT INTO `t_activitys` VALUES ('3', '<p>测试</p>', '2019-03-14 05:03:55', '天福美食订餐网站系统正式发布', '242ff06f-12b6-4ec5-98ba-a2115080c8c4_201210070319530.jpg', '1');
INSERT INTO `t_activitys` VALUES ('4', '<p>大家拿好纸和比&nbsp; 等签名&nbsp; 今天放假一天</p>', '2019-03-14 05:03:20', 'NBA著名球星参观天府美食城', 'c145c6ea-bdc8-49ad-a795-b9bd82511844_17494796_980x1200_0.jpg', '1');
INSERT INTO `t_activitys` VALUES ('5', '<p>测试1</p>', '2019-03-14 05:03:55', '一篇很好的NBa秀', '9b0b4d8d-84ce-4655-ac20-6e952e2a5280_u=3924519027,241439683&fm=32&img.jpg', '1');
INSERT INTO `t_activitys` VALUES ('6', '<p>发布一下啊</p>', '2019-03-14 05:03:37', '鲜花店开业', 'f468a997-7662-4ccb-ad3e-6c1f5642386c_timg (2).jpg', '1');

-- ----------------------------
-- Table structure for t_admins
-- ----------------------------
DROP TABLE IF EXISTS `t_admins`;
CREATE TABLE `t_admins` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admins
-- ----------------------------
INSERT INTO `t_admins` VALUES ('2', 'admin', '33112284ad9fe54fa7ad70fdfdacde9c', '1', 'admin');

-- ----------------------------
-- Table structure for t_adminshops
-- ----------------------------
DROP TABLE IF EXISTS `t_adminshops`;
CREATE TABLE `t_adminshops` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adminId` bigint(20) DEFAULT NULL,
  `shopId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_arcyn0gtl61j9pko1pf9i3iqq` (`adminId`),
  KEY `FK_qjf7jf7qckb59tc1507cgmf9` (`shopId`),
  CONSTRAINT `FK_arcyn0gtl61j9pko1pf9i3iqq` FOREIGN KEY (`adminId`) REFERENCES `t_admins` (`id`),
  CONSTRAINT `FK_qjf7jf7qckb59tc1507cgmf9` FOREIGN KEY (`shopId`) REFERENCES `t_shops` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_adminshops
-- ----------------------------

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `createTime` varchar(255) DEFAULT NULL,
  `channelId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g1vrcrri2q3cq028ivp8rx6d8` (`channelId`),
  CONSTRAINT `FK_g1vrcrri2q3cq028ivp8rx6d8` FOREIGN KEY (`channelId`) REFERENCES `t_channels` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES ('2', '<p>测试</p>', '2019-03-14 05:03:20', '1');
INSERT INTO `t_article` VALUES ('3', '<p>联系我们</p>', '2019-03-14 05:03:55', '2');
INSERT INTO `t_article` VALUES ('4', '<p>关于支付</p>', '2019-03-14 05:03:37', '3');
INSERT INTO `t_article` VALUES ('5', '<p>帮助中心</p>', '2019-03-14 05:03:55', '4');
INSERT INTO `t_article` VALUES ('6', '<p>法律声明</p>', '2019-03-14 05:03:37', '5');
INSERT INTO `t_article` VALUES ('7', '<p>招贤纳士</p>', '2015-08-24 01:08:58', '6');

-- ----------------------------
-- Table structure for t_channels
-- ----------------------------
DROP TABLE IF EXISTS `t_channels`;
CREATE TABLE `t_channels` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_channels
-- ----------------------------
INSERT INTO `t_channels` VALUES ('1', '关于我们', '1');
INSERT INTO `t_channels` VALUES ('2', '联系我们', '1');
INSERT INTO `t_channels` VALUES ('3', '关于支付', '1');
INSERT INTO `t_channels` VALUES ('4', '帮助中心', '1');
INSERT INTO `t_channels` VALUES ('5', '法律申明', '1');
INSERT INTO `t_channels` VALUES ('6', '招贤纳士', '1');

-- ----------------------------
-- Table structure for t_comments
-- ----------------------------
DROP TABLE IF EXISTS `t_comments`;
CREATE TABLE `t_comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `replyTime` varchar(255) DEFAULT NULL,
  `shopId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_75jqe68etjp01vfknhkjfwr6h` (`shopId`),
  KEY `FK_bh19ds51esxs6xiu77y6jnvgc` (`userId`),
  CONSTRAINT `FK_75jqe68etjp01vfknhkjfwr6h` FOREIGN KEY (`shopId`) REFERENCES `t_shops` (`id`),
  CONSTRAINT `FK_bh19ds51esxs6xiu77y6jnvgc` FOREIGN KEY (`userId`) REFERENCES `t_users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comments
-- ----------------------------
INSERT INTO `t_comments` VALUES ('1', '味道好1', '2019-03-14 05:03:55', '2', '1');
INSERT INTO `t_comments` VALUES ('2', '味道好', '2019-03-14 05:03:37', '2', '2');
INSERT INTO `t_comments` VALUES ('3', '卫生干净', '2019-03-14 05:03:55', '2', '1');
INSERT INTO `t_comments` VALUES ('4', '啊啊啊啊', '2019-03-14 05:03:37', '2', '1');
INSERT INTO `t_comments` VALUES ('5', '卫生，干净，好吃', '2019-03-14 05:03:55', '3', '1');
INSERT INTO `t_comments` VALUES ('6', '顶顶顶', '2019-03-14 05:03:37', '3', '1');

-- ----------------------------
-- Table structure for t_fooditems
-- ----------------------------
DROP TABLE IF EXISTS `t_fooditems`;
CREATE TABLE `t_fooditems` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `number` int(11) NOT NULL,
  `price` float NOT NULL,
  `foodId` bigint(20) DEFAULT NULL,
  `orderId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1vvxw70p0kk18ky5nlyerlhnl` (`foodId`),
  KEY `FK_jhjllt7fkvfa0vny2i74w0jq6` (`orderId`),
  CONSTRAINT `FK_1vvxw70p0kk18ky5nlyerlhnl` FOREIGN KEY (`foodId`) REFERENCES `t_foods` (`id`),
  CONSTRAINT `FK_jhjllt7fkvfa0vny2i74w0jq6` FOREIGN KEY (`orderId`) REFERENCES `t_orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_fooditems
-- ----------------------------
INSERT INTO `t_fooditems` VALUES ('3', '贵州老干妈炒饭', '1', '6', '1', '2');
INSERT INTO `t_fooditems` VALUES ('4', '老干妈蛋炒饭', '1', '8.5', '5', '2');
INSERT INTO `t_fooditems` VALUES ('5', '贵州老干妈炒饭', '2', '6', '1', '3');

-- ----------------------------
-- Table structure for t_foods
-- ----------------------------
DROP TABLE IF EXISTS `t_foods`;
CREATE TABLE `t_foods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isdown` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `oldprice` float NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `foodType_id` bigint(20) DEFAULT NULL,
  `shop_id` bigint(20) DEFAULT NULL,
  `isrecommend` varchar(255) DEFAULT NULL,
  `desription` varchar(255) DEFAULT NULL,
  `newprice` float NOT NULL,
  `recommendTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_k83nfwa1s9aa8ya0h6eciab3b` (`foodType_id`),
  KEY `FK_fuiggvdmik3jb8rjcglnqt8uw` (`shop_id`),
  CONSTRAINT `FK_fuiggvdmik3jb8rjcglnqt8uw` FOREIGN KEY (`shop_id`) REFERENCES `t_shops` (`id`),
  CONSTRAINT `FK_k83nfwa1s9aa8ya0h6eciab3b` FOREIGN KEY (`foodType_id`) REFERENCES `t_foodtypes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_foods
-- ----------------------------
INSERT INTO `t_foods` VALUES ('1', '1', '贵州老干妈炒饭', '8', 'cb63bdde-58c5-4eca-ab94-4684bec4b29d_3.jpg', '3', '2', '1', '测试', '6', '2019-03-14 05:03:55');
INSERT INTO `t_foods` VALUES ('3', '1', '鸡蛋灌饼', '3.5', 'e84c20b6-9f7b-4ca5-9be1-e36c442a8b2a_4.jpg', '4', '3', '1', '测试', '3', '2019-03-14 05:03:37');
INSERT INTO `t_foods` VALUES ('4', '1', '鸡蛋炒饭', '8', 'fcc61866-4a8b-4504-a9b9-f760c1a31d82_1 (4).jpg', '3', '3', '1', '味道鲜美，选材干净。', '6.5', '2019-03-14 05:03:55');
INSERT INTO `t_foods` VALUES ('5', '1', '老干妈蛋炒饭', '10', 'be17f6e0-76e4-477f-bd80-408ed7c35e79_1 (2).jpg', '3', '2', '1', '选材干净，味道可口。', '8.5', '2019-03-14 05:03:37');
INSERT INTO `t_foods` VALUES ('6', '1', '拌饭', '5', '1521d72e-cc3f-4cc2-85f5-1fda5472f0b2_12.jpg', '1', '6', '0', '12121', '2.1', '2019-03-14 05:03:55');
INSERT INTO `t_foods` VALUES ('7', '1', '肥羊6两', '50', 'c098ef13-3935-42ae-bca3-dae0466ec179_u=2322618428,2293609004&fm=26&gp=0.jpg', '2', '7', '1', '肥羊一份', '35', '2019-03-14');
INSERT INTO `t_foods` VALUES ('8', '1', '万花齐放', '10', '5d82eb33-bab2-4c2c-bb07-f18ed3d03bf6_timg (3).jpg', '4', '8', '0', '花', '8', null);

-- ----------------------------
-- Table structure for t_foodtypes
-- ----------------------------
DROP TABLE IF EXISTS `t_foodtypes`;
CREATE TABLE `t_foodtypes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `state` varchar(32) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_foodtypes
-- ----------------------------
INSERT INTO `t_foodtypes` VALUES ('1', '盖浇饭', '1', '盖浇饭');
INSERT INTO `t_foodtypes` VALUES ('2', '煲仔饭', '1', '煲仔饭');
INSERT INTO `t_foodtypes` VALUES ('3', '炒饭', '1', '炒饭');
INSERT INTO `t_foodtypes` VALUES ('4', '面食', '1', '面食');

-- ----------------------------
-- Table structure for t_notices
-- ----------------------------
DROP TABLE IF EXISTS `t_notices`;
CREATE TABLE `t_notices` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `createTime` varchar(255) DEFAULT NULL,
  `state` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_notices
-- ----------------------------
INSERT INTO `t_notices` VALUES ('1', '<p>测试</p>', '2019-03-14 05:03:55', '1', '新增加菜谱模型、文章模型更有利');
INSERT INTO `t_notices` VALUES ('4', '<p>食订餐网站系统正式发布</p>', '2019-03-14 05:03:37', '1', '天福美食订餐网站系统正式发布');
INSERT INTO `t_notices` VALUES ('5', '<p>买好衣服准备让人家给你签名</p>', '2019-03-14 06:03:31', '1', 'NBA著名球星参观天府美食城');

-- ----------------------------
-- Table structure for t_orders
-- ----------------------------
DROP TABLE IF EXISTS `t_orders`;
CREATE TABLE `t_orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `orderDate` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `ordernum` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `total` float NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `shopId` bigint(20) DEFAULT NULL,
  `payType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4h2v1ods67jy2nlggo3ia96ns` (`userId`),
  KEY `FK_cn8hy5nc5483yxuptnk6dxpt1` (`shopId`),
  CONSTRAINT `FK_4h2v1ods67jy2nlggo3ia96ns` FOREIGN KEY (`userId`) REFERENCES `t_users` (`id`),
  CONSTRAINT `FK_cn8hy5nc5483yxuptnk6dxpt1` FOREIGN KEY (`shopId`) REFERENCES `t_shops` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_orders
-- ----------------------------
INSERT INTO `t_orders` VALUES ('2', '2019-03-12 05:09:57', '2019-03-12', '刘颖', 'ee6b654e8d664abf969e006830924251', '15538452831', '辣一点', '3', '14.5', '1', '天福美食广场', '2', '0');
INSERT INTO `t_orders` VALUES ('3', '2019-03-12 11:09:46', '2019-03-12', '刘颖', '242d044bad9e4f4aafc625f3145e38ca', '15538452831', '饭少一点，不要太辣', '2', '12', '1', '天福美食广场', '2', '0');

-- ----------------------------
-- Table structure for t_shoplabels
-- ----------------------------
DROP TABLE IF EXISTS `t_shoplabels`;
CREATE TABLE `t_shoplabels` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_shoplabels
-- ----------------------------
INSERT INTO `t_shoplabels` VALUES ('1', '馋嘴小吃', '馋嘴小吃', '1');
INSERT INTO `t_shoplabels` VALUES ('2', '品牌快餐', '品牌快餐', '1');
INSERT INTO `t_shoplabels` VALUES ('3', '甜点饮品', '甜点饮品', '1');
INSERT INTO `t_shoplabels` VALUES ('4', '季节水果', '季节水果', '1');
INSERT INTO `t_shoplabels` VALUES ('5', '营养正餐', '营养正餐', '1');
INSERT INTO `t_shoplabels` VALUES ('6', '鲜花蛋糕', '鲜花蛋糕', '1');

-- ----------------------------
-- Table structure for t_shoplabel_shops
-- ----------------------------
DROP TABLE IF EXISTS `t_shoplabel_shops`;
CREATE TABLE `t_shoplabel_shops` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `shop_id` bigint(20) DEFAULT NULL,
  `shopLabel_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_r06hc2gx3289m0ucxualpjd4j` (`shop_id`),
  KEY `FK_3r04umf4pryv7aimwwpb2gork` (`shopLabel_id`),
  CONSTRAINT `FK_3r04umf4pryv7aimwwpb2gork` FOREIGN KEY (`shopLabel_id`) REFERENCES `t_shoplabels` (`id`),
  CONSTRAINT `FK_r06hc2gx3289m0ucxualpjd4j` FOREIGN KEY (`shop_id`) REFERENCES `t_shops` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_shoplabel_shops
-- ----------------------------
INSERT INTO `t_shoplabel_shops` VALUES ('11', '2', '1');
INSERT INTO `t_shoplabel_shops` VALUES ('13', '2', '5');
INSERT INTO `t_shoplabel_shops` VALUES ('15', '2', '4');
INSERT INTO `t_shoplabel_shops` VALUES ('16', '3', '1');
INSERT INTO `t_shoplabel_shops` VALUES ('17', '3', '2');
INSERT INTO `t_shoplabel_shops` VALUES ('18', '3', '5');
INSERT INTO `t_shoplabel_shops` VALUES ('19', '4', '1');
INSERT INTO `t_shoplabel_shops` VALUES ('20', '4', '2');
INSERT INTO `t_shoplabel_shops` VALUES ('21', '4', '5');
INSERT INTO `t_shoplabel_shops` VALUES ('22', '5', '2');
INSERT INTO `t_shoplabel_shops` VALUES ('23', '5', '3');
INSERT INTO `t_shoplabel_shops` VALUES ('24', '5', '5');
INSERT INTO `t_shoplabel_shops` VALUES ('25', '6', '1');
INSERT INTO `t_shoplabel_shops` VALUES ('26', '7', '1');
INSERT INTO `t_shoplabel_shops` VALUES ('27', '8', '6');

-- ----------------------------
-- Table structure for t_shops
-- ----------------------------
DROP TABLE IF EXISTS `t_shops`;
CREATE TABLE `t_shops` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `bossname` varchar(255) DEFAULT NULL,
  `bosstel` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `isrecommend` varchar(255) NOT NULL,
  `state` varchar(255) DEFAULT NULL,
  `isrest` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `recommendTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_shops
-- ----------------------------
INSERT INTO `t_shops` VALUES ('2', '美食街3号', '张三', '13262043250', '<p><img src=\"http://localhost:8888/tfms/resources/umeditor1.2.2/jsp/upload/20150830/99471440874011084.gif\" _src=\"http://localhost:8888/tfms/resources/umeditor1.2.2/jsp/upload/20150830/99471440874011084.gif\"/></p>', '胡和记面馆', '1', '1', '1', '8fbb1d4a-64b2-451f-8343-a01cdee60443_2.jpg', '2019-03-16');
INSERT INTO `t_shops` VALUES ('3', '天府街1号', '李四', '13262043250', '测试', '炊事班鸡蛋灌饼', '1', '1', '1', '8f7a1d40-c5ee-4adf-b042-4f70e621d18f_1.jpg', '2019-03-16');
INSERT INTO `t_shops` VALUES ('4', '天府街11号', '李四', '13262043250', '测试', '贵州老干妈炒饭', '1', '1', '1', '1f063554-5b5c-494b-a937-1fb0a8377542_4.jpg', '2019-03-16');
INSERT INTO `t_shops` VALUES ('5', '天府街12号', '李四', '13262043250', '测试', '金阳光快餐', '1', '1', '1', '75bf20cd-62bc-411a-9c4b-4d9a74f39053_8.jpg', '2019-03-14');
INSERT INTO `t_shops` VALUES ('6', '美食街三号', '辣条', '15669947917', '<p>模板重新生成啊</p>', '测试商铺', '1', '1', '1', '8294c9a4-87be-4513-9c71-30c2addc891f_u=3924519027,241439683&fm=32&img.jpg', '2019-03-14');
INSERT INTO `t_shops` VALUES ('7', '新世纪大道', '刘颖', '15669947917', '<p>火锅很辣&nbsp; 请慎用</p>', '新世纪火锅', '0', '1', '1', '8218369b-5917-4211-8834-1c95d9944a66_timg.jpg', null);
INSERT INTO `t_shops` VALUES ('8', '鸟语花香街', '刘颖', '15669947917', '<p>鲜花太好看 请谨慎购买</p>', '鸟语花香', '0', '1', '1', '10479fc0-0ca4-4d4a-8a49-86424e887204_timg (2).jpg', null);

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `regDate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO `t_users` VALUES ('1', '天府街38号', '602943701@qq.com', '刘颖1', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying', '2015-08-18');
INSERT INTO `t_users` VALUES ('2', '天府街38号', '602943701@qq.com', '刘颖2', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying1', '2015-08-18');
INSERT INTO `t_users` VALUES ('3', '天府街38号', '602943701@qq.com', '刘颖3', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying2', '2015-08-18');
INSERT INTO `t_users` VALUES ('4', '天府街38号', '602943701@qq.com', '刘颖4', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying3', '2015-08-18');
INSERT INTO `t_users` VALUES ('5', '天府街38号', '602943701@qq.com', '刘颖5', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying4', '2015-08-18');
INSERT INTO `t_users` VALUES ('6', '天府街38号', '602943701@qq.com', '刘颖6', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying5', '2015-08-18');
INSERT INTO `t_users` VALUES ('7', '天府街38号', '602943701@qq.com', '刘颖7', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying6', '2015-08-18');
INSERT INTO `t_users` VALUES ('8', '天府街38号', '602943701@qq.com', '刘颖8', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying7', '2015-08-18');
INSERT INTO `t_users` VALUES ('9', '天府街38号', '602943701@qq.com', '刘颖9', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying8', '2015-08-18');
INSERT INTO `t_users` VALUES ('10', '天府街38号', '602943701@qq.com', '刘颖10', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying9', '2015-08-18');
INSERT INTO `t_users` VALUES ('11', '天府街38号', '602943701@qq.com', '刘颖', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying10', '2015-08-18');
INSERT INTO `t_users` VALUES ('12', '天府街38号', '602943701@qq.com', '刘颖', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying11', '2015-08-18');
INSERT INTO `t_users` VALUES ('13', '天府街38号', '602943701@qq.com', '刘颖', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying12', '2015-08-18');
INSERT INTO `t_users` VALUES ('14', '天府街38号', '602943701@qq.com', '刘颖', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying13', '2015-08-18');
INSERT INTO `t_users` VALUES ('15', '天府街38号', '602943701@qq.com', '刘颖', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying14', '2015-08-18');
INSERT INTO `t_users` VALUES ('16', '天府街38号', '602943701@qq.com', '刘颖', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying15', '2015-08-18');
INSERT INTO `t_users` VALUES ('17', '天府街38号', '602943701@qq.com', '刘颖', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying16', '2015-08-18');
INSERT INTO `t_users` VALUES ('18', '天府街38号', '602943701@qq.com', '刘颖', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying17', '2015-08-18');
INSERT INTO `t_users` VALUES ('19', '天府街38号', '602943701@qq.com', '刘颖', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying18', '2015-08-18');
INSERT INTO `t_users` VALUES ('20', '天府街38号', '602943701@qq.com', '刘颖', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying19', '2015-08-18');
INSERT INTO `t_users` VALUES ('21', '天府街38号', '602943701@qq.com', '刘颖', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying20', '2015-08-18');
INSERT INTO `t_users` VALUES ('22', '天府街38号', '602943701@qq.com', '刘颖', '33112284ad9fe54fa7ad70fdfdacde9c', '15669947917', '女', 'liuying21', '2015-08-18');
