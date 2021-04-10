/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : photography

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 07/01/2021 21:06:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for app_info
-- ----------------------------
DROP TABLE IF EXISTS `app_info`;
CREATE TABLE `app_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `service_content` text COMMENT '服务条例',
  `private_content` text COMMENT '隐私协议',
  `version` varchar(100) DEFAULT NULL COMMENT '小程序版本',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='小程序信息表';

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `photography_id` int(11) DEFAULT NULL COMMENT '作品id',
  `open_id` varchar(100) DEFAULT NULL COMMENT '评论人微信open_id',
  `comment_id` int(11) DEFAULT NULL COMMENT '评论id',
  `comment` text COMMENT '评论内容',
  `votes` int(11) DEFAULT '0' COMMENT '评论点赞量',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

-- ----------------------------
-- Table structure for competition
-- ----------------------------
DROP TABLE IF EXISTS `competition`;
CREATE TABLE `competition` (
  `competition_id` varchar(50) NOT NULL COMMENT '比赛id',
  `name` varchar(255) DEFAULT NULL COMMENT '比赛名称',
  `subject` text COMMENT '比赛主题',
  `condition` text COMMENT '比赛条件',
  `start_date` varchar(10) DEFAULT NULL COMMENT '比赛开始时间',
  `end_date` varchar(10) DEFAULT NULL COMMENT '比赛结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `img_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`competition_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='比赛场次表';

-- ----------------------------
-- Records of competition
-- ----------------------------
BEGIN;
INSERT INTO `competition` VALUES ('54c77905-9791-441e-bf17-6208f3d7170c', '2020年度宠物比赛', '狗中最丑', '如果你是爱宠人士？养宠达人？炫宠狂友？千万不要错过这次“宠物摄影大赛”，用你的镜头记录下它们的萌、憨、亦或搞笑，与广大爱宠影友一起同台比拼，让你的萌宠成为明星大放异彩！\n\n一、大赛组织机构\n\n主办：四川省摄影家协会网   瑞派华茜宠物医院\n协办：成都宠世吉贸易有限公司     成都道行远生物科技有限公司\n官方网站：四川省摄影家协会网（http://www.scssyjxh.cn/）\n官方公众号：四川省摄影家协会（scssyjxhw）\n官方抖音号：scssyjxhw\n\n\n二、大赛主题\n\n本次摄影大赛是由瑞派华茜宠物医院发起，它的前身是华西动物医院。由华西医科大学实验动物中心创办，1992年10月8日在林萌街华西医科大学实验动物中心开业。\n瑞派华茜宠物医院作为中国西部地区的中心医院，每年接诊数以万计来自全国各地的宠物疾病。这里有着被送来治疗的宠物，在医术超群的医生们科学救治和照料下逐渐恢复的小生命们，有着面对宠物的生老病死，心路也不断变化的宠主。在宠物医院发生的千百个治疗故事背后，却有打动人心的治愈力量，爱与温暖，悄然发生。\n减少痛苦、增加快乐一直是瑞派华茜宠物医院奉行的宗旨，因为他们认为每一个生命都是有尊严的，而每一只伴侣动物也同样需要尊重和关爱。瑞派华茜宠物医院不仅拥有丰富的热爱宠物的文化底蕴，而且拥有大量有责任心并且医术精湛的专科医生不断提升伴侣宠物的健康水平。因此瑞派华茜宠物医院愿您同我们一起从现在开始，一同携手来关注那些被忽视的生命，相信你一定能够发现身边每只小猫小狗的独特之处，赶快拿起你的相机或手机抓住那些可爱的瞬间吧！和全国热爱宠物的人们一起参与第三届“华茜杯”全国宠物摄影大赛！\n\n\n三、奖金设置\n\n获奖作品可作为加入四川省摄影家协会条件之一。\n\n\n四、投稿日期、公示日期 \n\n投稿日期:2020年7月9日——2020 年 10月7日(以收件日期为准) \n公示日期：2020年10月10日左右\n\n\n五、参赛人资格\n\n(一)凡达到法律规定的法定年龄、具有独立承担法律责任的国内外自然人均可参加。\n(二)参赛作品画面上不得显示作者的姓名、昵称或 Logo 等，不得加水印、边框及题字、题跋等，凡不符合要求者取消参赛资格。\n(三)每件作品必须有作品的标题，以及与作品相关的文字说明。凡不符合要求者取消参赛资格。\n\n\n六、投稿细则\n\n(一)参赛作品要求立意鲜明、场景真实、视觉突出、内容生动、摄影语言丰富、图像品质优异，单幅、组照(限 4-9 幅)、黑白、彩色不限。\n(二)每人参赛作品不得超过 30 件。每件作品除标题外，需配上 30 字以内的图片说明，介绍图片的内容或意义。\n(三)作者和拍摄时间均不限。\n(四)本次大赛实行网络投稿，数据文件为 JPG 格式。长边1000像素，JPG，1M以下（否则无法上传）。投稿数量每人限投 30件(每幅/组作品为“一件”)。组照作品要求多张+拼图形式上传，每组组照作品限制 在 4-9 幅在【本次摄影赛投稿官网】上传参赛作品。\n\n投稿平台：\n平台1、手机APP投稿：\n通过四川省摄影家协会网手机投稿APP平台投稿（请扫描下方二维码）；\n平台2、网上投稿：\n登录四川省摄影家协会网摄影季赛官网投稿http://scssxwphoto.jhw.sc.cn/index.aspx，登陆方式点击——四川省摄影家协会网首页广告链接（或扫描征稿启事上方二维码）。\n说明：作者不得在1、2项平台重复投稿，作品格式和大小需符合投稿平台需求，主办方保证不公开或泄露投稿人非公开信息。\n(五)以不违背拍摄对象客观真实属性为准，允许一定程度的后期处理，包括对影调和色彩等进行适度的后期调整。彩色可以整体(不可局部)转变为黑白(含单色)。\n(六)大赛拒绝合作作品，投稿者须保证对该作品拥有独立、完整、明确、无争议的著作权。作品涉及的第三人的著作权、肖像权、名誉权、隐私权等法律责 任由作者自负。大赛提醒投稿者审慎注意著作权中其他相关法律规定。\n(七)主办方有权将所有入选作品的图片及文字说明用于公益传播，包括出版图书、画册及推荐相关媒体发表等，并在其公益广告、宣传、展览及文化等活动中拥有使用权，并不再另付稿酬。\n(八)凡投稿作者，均视为同意并遵守以上各条规定。凡不符合参赛要求的作品，一经发现将一律取消参赛资格。\n(九)所有获奖作品有效像素须在2MB以上，不能按要求提供作品文件的获奖者，将被视为放弃获奖资格。\n(十)本征稿启事解释权属于主办单位。\n \n\n                                              摄影大赛组委会\n\n                                              2020年7月5日', '2020-12-12', '2020-12-20', '2020-12-13 09:39:41', '2020-12-19 17:47:44', 'https://images.pexels.com/photos/825949/pexels-photo-825949.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500');
INSERT INTO `competition` VALUES ('54c7905-9791-441e-bf17-6208f3d7170c', '2019年度宠物比赛', '谁最快', '1.\n2.3.4.5', '2020-12-12', '2020-12-20', '2020-12-13 09:40:08', '2020-12-19 17:25:44', 'https://images.pexels.com/photos/422220/pexels-photo-422220.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500');
INSERT INTO `competition` VALUES ('54c795-9791-441e-bf10d7170c', '2020 annual pet beauty', 'who is the most beautiful?', 'everyone can join in', '2020-12-12', '2021-01-01', '2020-12-13 09:41:19', '2020-12-19 17:24:40', 'https://images.pexels.com/photos/164186/pexels-photo-164186.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500');
INSERT INTO `competition` VALUES ('54c795-9791-441e-bf17-6208f3d7170c', '2020??????', '猫中最美', '如果你是爱宠人士？养宠达人？炫宠狂友？千万不要错过这次“宠物摄影大赛”，用你的镜头记录下它们的萌、憨、亦或搞笑，与广大爱宠影友一起同台比拼，让你的萌宠成为明星大放异彩！\n\n一、大赛组织机构\n\n主办：四川省摄影家协会网   瑞派华茜宠物医院\n协办：成都宠世吉贸易有限公司     成都道行远生物科技有限公司\n官方网站：四川省摄影家协会网（http://www.scssyjxh.cn/）\n官方公众号：四川省摄影家协会（scssyjxhw）\n官方抖音号：scssyjxhw\n\n\n二、大赛主题\n\n本次摄影大赛是由瑞派华茜宠物医院发起，它的前身是华西动物医院。由华西医科大学实验动物中心创办，1992年10月8日在林萌街华西医科大学实验动物中心开业。\n瑞派华茜宠物医院作为中国西部地区的中心医院，每年接诊数以万计来自全国各地的宠物疾病。这里有着被送来治疗的宠物，在医术超群的医生们科学救治和照料下逐渐恢复的小生命们，有着面对宠物的生老病死，心路也不断变化的宠主。在宠物医院发生的千百个治疗故事背后，却有打动人心的治愈力量，爱与温暖，悄然发生。\n减少痛苦、增加快乐一直是瑞派华茜宠物医院奉行的宗旨，因为他们认为每一个生命都是有尊严的，而每一只伴侣动物也同样需要尊重和关爱。瑞派华茜宠物医院不仅拥有丰富的热爱宠物的文化底蕴，而且拥有大量有责任心并且医术精湛的专科医生不断提升伴侣宠物的健康水平。因此瑞派华茜宠物医院愿您同我们一起从现在开始，一同携手来关注那些被忽视的生命，相信你一定能够发现身边每只小猫小狗的独特之处，赶快拿起你的相机或手机抓住那些可爱的瞬间吧！和全国热爱宠物的人们一起参与第三届“华茜杯”全国宠物摄影大赛！\n\n\n三、奖金设置\n\n获奖作品可作为加入四川省摄影家协会条件之一。\n\n\n四、投稿日期、公示日期 \n\n投稿日期:2020年7月9日——2020 年 10月7日(以收件日期为准) \n公示日期：2020年10月10日左右\n\n\n五、参赛人资格\n\n(一)凡达到法律规定的法定年龄、具有独立承担法律责任的国内外自然人均可参加。\n(二)参赛作品画面上不得显示作者的姓名、昵称或 Logo 等，不得加水印、边框及题字、题跋等，凡不符合要求者取消参赛资格。\n(三)每件作品必须有作品的标题，以及与作品相关的文字说明。凡不符合要求者取消参赛资格。\n\n\n六、投稿细则\n\n(一)参赛作品要求立意鲜明、场景真实、视觉突出、内容生动、摄影语言丰富、图像品质优异，单幅、组照(限 4-9 幅)、黑白、彩色不限。\n(二)每人参赛作品不得超过 30 件。每件作品除标题外，需配上 30 字以内的图片说明，介绍图片的内容或意义。\n(三)作者和拍摄时间均不限。\n(四)本次大赛实行网络投稿，数据文件为 JPG 格式。长边1000像素，JPG，1M以下（否则无法上传）。投稿数量每人限投 30件(每幅/组作品为“一件”)。组照作品要求多张+拼图形式上传，每组组照作品限制 在 4-9 幅在【本次摄影赛投稿官网】上传参赛作品。\n\n投稿平台：\n平台1、手机APP投稿：\n通过四川省摄影家协会网手机投稿APP平台投稿（请扫描下方二维码）；\n平台2、网上投稿：\n登录四川省摄影家协会网摄影季赛官网投稿http://scssxwphoto.jhw.sc.cn/index.aspx，登陆方式点击——四川省摄影家协会网首页广告链接（或扫描征稿启事上方二维码）。\n说明：作者不得在1、2项平台重复投稿，作品格式和大小需符合投稿平台需求，主办方保证不公开或泄露投稿人非公开信息。\n(五)以不违背拍摄对象客观真实属性为准，允许一定程度的后期处理，包括对影调和色彩等进行适度的后期调整。彩色可以整体(不可局部)转变为黑白(含单色)。\n(六)大赛拒绝合作作品，投稿者须保证对该作品拥有独立、完整、明确、无争议的著作权。作品涉及的第三人的著作权、肖像权、名誉权、隐私权等法律责 任由作者自负。大赛提醒投稿者审慎注意著作权中其他相关法律规定。\n(七)主办方有权将所有入选作品的图片及文字说明用于公益传播，包括出版图书、画册及推荐相关媒体发表等，并在其公益广告、宣传、展览及文化等活动中拥有使用权，并不再另付稿酬。\n(八)凡投稿作者，均视为同意并遵守以上各条规定。凡不符合参赛要求的作品，一经发现将一律取消参赛资格。\n(九)所有获奖作品有效像素须在2MB以上，不能按要求提供作品文件的获奖者，将被视为放弃获奖资格。\n(十)本征稿启事解释权属于主办单位。\n \n\n                                              摄影大赛组委会\n\n                                              2020年7月5日', '2020-12-12', '2020-12-20', '2020-12-13 09:42:43', '2020-12-19 17:47:43', 'https://images.pexels.com/photos/730896/pexels-photo-730896.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500');
COMMIT;

-- ----------------------------
-- Table structure for dim_pet
-- ----------------------------
DROP TABLE IF EXISTS `dim_pet`;
CREATE TABLE `dim_pet` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `pet_type` varchar(100) DEFAULT NULL COMMENT '宠物类型',
  `variety` varchar(100) DEFAULT NULL COMMENT '品种',
  `size` varchar(10) DEFAULT NULL COMMENT '宠物大小（大型、中型、小型）',
  `country` varchar(100) DEFAULT NULL COMMENT '国别',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 COMMENT='宠物字典表';

-- ----------------------------
-- Records of dim_pet
-- ----------------------------
BEGIN;
INSERT INTO `dim_pet` VALUES (1, '狗', '哈士奇', '', NULL, '2021-01-01 10:13:22', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (2, '狗', '萨摩耶', NULL, NULL, '2021-01-01 10:13:45', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (3, '狗', '拉布拉多', NULL, NULL, '2021-01-01 10:13:58', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (4, '狗', '边境牧羊犬', NULL, NULL, '2021-01-01 10:14:08', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (5, '狗', '柯基犬', NULL, NULL, '2021-01-01 10:14:21', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (6, '狗', '泰迪', NULL, NULL, '2021-01-01 10:14:28', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (7, '狗', '阿拉斯加', NULL, NULL, '2021-01-01 10:14:36', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (8, '狗', '柴犬', NULL, NULL, '2021-01-01 10:14:44', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (9, '狗', '比熊犬', NULL, NULL, '2021-01-01 10:14:54', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (10, '狗', '法国斗牛犬', NULL, NULL, '2021-01-01 10:15:04', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (11, '狗', '金毛寻回犬', NULL, NULL, '2021-01-01 10:15:15', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (12, '狗', '德国牧羊犬', NULL, NULL, '2021-01-01 10:15:22', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (13, '狗', '中华田园犬', NULL, NULL, '2021-01-01 10:15:29', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (14, '狗', '博美', NULL, NULL, '2021-01-01 10:15:38', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (15, '狗', '西施', NULL, NULL, '2021-01-01 10:16:01', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (16, '狗', '吉娃娃', NULL, NULL, '2021-01-01 10:16:06', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (17, '狗', '贵宾犬', NULL, NULL, '2021-01-01 10:16:20', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (18, '狗', '秋田犬', NULL, NULL, '2021-01-01 10:16:35', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (19, '狗', '雪纳瑞', NULL, NULL, '2021-01-01 10:16:45', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (20, '狗', '松狮犬', NULL, NULL, '2021-01-01 10:16:53', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (21, '狗', '巴哥犬', NULL, NULL, '2021-01-01 10:17:08', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (22, '狗', '恶霸犬', NULL, NULL, '2021-01-01 10:17:21', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (23, '狗', '杜宾犬', NULL, NULL, '2021-01-01 10:17:27', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (24, '狗', '马尔济斯犬', NULL, NULL, '2021-01-01 10:17:38', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (25, '狗', '英国斗牛犬', NULL, NULL, '2021-01-01 10:17:47', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (26, '狗', '蝴蝶犬', NULL, NULL, '2021-01-01 10:18:05', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (27, '狗', '喜乐蒂犬', NULL, NULL, '2021-01-01 10:18:13', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (28, '狗', '藏獒犬', NULL, NULL, '2021-01-01 10:18:25', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (29, '狗', '高加索犬', NULL, NULL, '2021-01-01 10:18:43', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (30, '狗', '纽芬兰犬', NULL, NULL, '2021-01-01 10:19:01', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (31, '狗', '苏格兰牧羊犬', NULL, NULL, '2021-01-01 10:19:14', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (32, '猫', '英国短毛猫', NULL, NULL, '2021-01-01 10:19:43', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (33, '猫', '布偶猫', NULL, NULL, '2021-01-01 10:19:51', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (34, '猫', '美国短毛猫', NULL, NULL, '2021-01-01 10:19:59', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (35, '猫', '无毛猫', NULL, NULL, '2021-01-01 10:20:10', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (36, '猫', '中华田园猫', NULL, NULL, '2021-01-01 10:20:19', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (37, '猫', '银渐层猫', NULL, NULL, '2021-01-01 10:20:34', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (38, '猫', '串串猫', NULL, NULL, '2021-01-01 10:20:46', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (39, '猫', '加菲猫', NULL, NULL, '2021-01-01 10:20:53', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (40, '猫', '狸花猫', NULL, NULL, '2021-01-01 10:21:04', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (41, '猫', '金渐层', NULL, NULL, '2021-01-01 10:21:33', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (42, '猫', '苏格兰折耳猫', NULL, NULL, '2021-01-01 10:21:44', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (43, '猫', '缅因猫', NULL, NULL, '2021-01-01 10:21:55', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (44, '猫', '金吉拉', NULL, NULL, '2021-01-01 10:22:04', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (45, '猫', '安哥拉', NULL, NULL, '2021-01-01 10:22:14', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (46, '猫', '阿比西尼亚', NULL, NULL, '2021-01-01 10:22:22', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (47, '猫', '英国长毛猫', NULL, NULL, '2021-01-01 10:22:32', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (48, '猫', '曼基康猫', NULL, NULL, '2021-01-01 10:22:49', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (49, '猫', '孟买猫', NULL, NULL, '2021-01-01 10:23:01', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (50, '猫', '俄罗斯蓝猫', NULL, NULL, '2021-01-01 10:23:10', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (51, '猫', '波斯猫', NULL, NULL, '2021-01-01 10:23:16', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (52, '猫', '孟加拉豹猫', NULL, NULL, '2021-01-01 10:23:36', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (53, '猫', '虎斑猫', NULL, NULL, '2021-01-01 10:23:44', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (54, '猫', '土耳其梵猫', NULL, NULL, '2021-01-01 10:23:59', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (55, '猫', '柏曼猫', NULL, NULL, '2021-01-01 10:24:21', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (56, '猫', '德文卷毛猫', NULL, NULL, '2021-01-01 10:24:33', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (57, '猫', '喜马拉雅猫', NULL, NULL, '2021-01-01 10:24:42', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (58, '猫', '橘猫', NULL, NULL, '2021-01-01 10:24:49', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (59, '猫', '高地猫', NULL, NULL, '2021-01-01 10:24:55', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (60, '猫', '埃及猫', NULL, NULL, '2021-01-01 10:25:03', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (61, '猫', '狮子猫', NULL, NULL, '2021-01-01 10:25:12', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (62, '猫', '新加坡猫', NULL, NULL, '2021-01-01 10:25:20', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (63, '猫', '西伯利亚森林猫', NULL, NULL, '2021-01-01 10:25:37', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (64, '猫', '挪威森林猫', NULL, NULL, '2021-01-01 10:25:55', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (65, '猫', '东方猫', NULL, NULL, '2021-01-01 10:26:04', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (66, '猫', '茶杯猫', NULL, NULL, '2021-01-01 10:26:16', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (67, '猫', '日本短尾猫', NULL, NULL, '2021-01-01 10:26:27', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (68, '猫', '柯尼斯卷毛猫', NULL, NULL, '2021-01-01 10:26:53', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (69, '猫', '拿破仑', NULL, NULL, '2021-01-01 10:27:03', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (70, '其他动物', '蜥蜴', NULL, NULL, '2021-01-01 10:27:28', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (71, '其他动物', '鳄鱼', NULL, NULL, '2021-01-01 10:27:41', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (72, '其他动物', '水牛', NULL, NULL, '2021-01-01 10:27:47', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (73, '其他动物', '乌龟', NULL, NULL, '2021-01-01 10:27:55', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (74, '其他动物', '蛇', NULL, NULL, '2021-01-01 10:28:03', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (75, '其他动物', '仓鼠', NULL, NULL, '2021-01-01 10:28:09', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (76, '其他动物', '兔子', NULL, NULL, '2021-01-01 10:28:19', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (77, '其他动物', '老鼠', NULL, NULL, '2021-01-01 10:28:27', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (78, '鱼', '海缸', NULL, NULL, '2021-01-01 10:28:57', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (79, '鱼', '锦鲤', NULL, NULL, '2021-01-01 10:29:06', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (80, '鱼', '斗鱼', NULL, NULL, '2021-01-01 10:29:12', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (81, '鱼', '龙鱼', NULL, NULL, '2021-01-01 10:29:20', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (82, '鱼', '草金鱼', NULL, NULL, '2021-01-01 10:29:28', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (83, '鱼', '草缸', NULL, NULL, '2021-01-01 10:29:39', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (84, '鱼', '金鱼', NULL, NULL, '2021-01-01 10:29:45', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (85, '鱼', '罗汉鱼', NULL, NULL, '2021-01-01 10:30:43', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (86, '鸟', '鹦鹉', NULL, NULL, '2021-01-01 10:30:57', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (87, '鸟', '文鸟', NULL, NULL, '2021-01-01 10:31:03', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (88, '鸟', '科尔鸭', NULL, NULL, '2021-01-01 10:31:18', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (89, '鸟', '珍珠鸟', NULL, NULL, '2021-01-01 10:31:26', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (90, '鸟', '宠物鸡', NULL, NULL, '2021-01-01 10:31:41', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (91, '鸟', '鸽子', NULL, NULL, '2021-01-01 10:31:48', '2021-01-01 07:18:36');
INSERT INTO `dim_pet` VALUES (92, '鸟', '麻雀', NULL, NULL, '2021-01-01 10:31:59', '2021-01-01 07:18:36');
COMMIT;

-- ----------------------------
-- Table structure for photography
-- ----------------------------
DROP TABLE IF EXISTS `photography`;
CREATE TABLE `photography` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `phone` varchar(50) DEFAULT NULL COMMENT '参赛人手机号',
  `open_id` varchar(100) DEFAULT NULL COMMENT '参赛人微信open_id',
  `url` varchar(255) DEFAULT NULL COMMENT '参赛作品',
  `type` varchar(10) DEFAULT NULL COMMENT '作品类型（image/video）',
  `subject` varchar(255) DEFAULT NULL COMMENT '作品主题',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '宠物昵称',
  `photographer` varchar(255) DEFAULT NULL COMMENT '作者昵称',
  `votes` int(11) DEFAULT '0' COMMENT '作品点赞量',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='摄影作品表';

-- ----------------------------
-- Records of photography
-- ----------------------------
BEGIN;
INSERT INTO `photography` VALUES (1, '15313621879', NULL, 'https://images.pexels.com/photos/156934/pexels-photo-156934.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260', 'image', '冬日暖宝宝', 'catty', 'chen', 0, '2020-12-27 11:14:07', '2020-12-27 11:14:07');
INSERT INTO `photography` VALUES (2, '15313621879', NULL, 'https://images.pexels.com/photos/236606/pexels-photo-236606.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260', 'image', '煤球的周末', 'blacky', 'chen', 0, '2020-12-27 11:14:07', '2020-12-27 11:16:09');
INSERT INTO `photography` VALUES (3, '15313621879', NULL, 'https://images.pexels.com/photos/774731/pexels-photo-774731.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260', 'image', 'helo', 'kitty', 'chen', 0, '2020-12-27 11:14:07', '2020-12-27 11:14:42');
INSERT INTO `photography` VALUES (4, NULL, 'oJpCK5BBuDrmVjjpeid5-CWJ2JUY', 'http://cdn.pipilong.pet//mnt/pet/avatar/1609251670233XjPJqa4bsYxWd88054790e244ea282de6a4a186f72b9.png', 'image', 'I am the Best', '', NULL, 0, '2020-12-29 22:21:13', '2020-12-29 22:21:13');
INSERT INTO `photography` VALUES (5, NULL, 'oJpCK5BBuDrmVjjpeid5-CWJ2JUY', 'http://cdn.pipilong.pet//mnt/pet/avatar/1609251952840diH3OwZo3OoC3ee6ffa827427fcf683a9b877ce6b7ca.png', 'image', 'You are my King.', 'Toyota', NULL, 0, '2020-12-29 22:25:58', '2020-12-29 22:25:58');
INSERT INTO `photography` VALUES (6, NULL, 'oJpCK5BBuDrmVjjpeid5-CWJ2JUY', '', 'image', 'My roader', 'Benz', NULL, 0, '2020-12-29 22:28:08', '2020-12-29 22:28:08');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `open_id` varchar(100) DEFAULT NULL COMMENT '微信open_id',
  `union_id` varchar(100) DEFAULT NULL COMMENT '微信union_id',
  `pwd` varchar(255) DEFAULT NULL COMMENT '密码',
  `user_type` varchar(10) DEFAULT NULL COMMENT '用户类型(1普通用户,2摄影师,3摄影机构,4宠物店)',
  `valid_start_date` datetime DEFAULT NULL COMMENT '会员有效开始日期',
  `valid_end_date` datetime DEFAULT NULL COMMENT '会员有效结束日期',
  `token` varchar(255) DEFAULT NULL COMMENT '登录access token',
  `country` varchar(255) DEFAULT NULL COMMENT '国家',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '头像url',
  `gender` varchar(255) DEFAULT NULL COMMENT '性别',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `language` varchar(255) DEFAULT NULL COMMENT '语言',
  `birthday` varchar(100) DEFAULT NULL COMMENT '出生日期',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `balance` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, '15313621879', 'ouHZt5T1IqahjM-W_TNli_FfOjJ8', NULL, '730a3bf0-7eb1-4545-acc3-b73b9a9e5fe6', '1', '2020-12-12 00:00:00', '2020-04-14 11:02:24', 'e21be309-250a-414f-b03f-6a78c476dd17', '7dbcc71a-8d33-43a8-8614-0d64092275b3', '安道尔', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/ZUrkDSFvpKW8nCT7QxpBtfAd2a3RmKOofX8ueg2ictFicaAibLQDrVRLDzw4PXGpLCvBJ5ibYvQ32LyKOvWoMtNmDg/132', '1', '@', 'zh_CN', '2020-12-14 16:00:50', '2020-12-29 22:14:20', '2020-04-19 09:58:27');
COMMIT;

-- ----------------------------
-- Table structure for user_pet
-- ----------------------------
DROP TABLE IF EXISTS `user_pet`;
CREATE TABLE `user_pet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avatar_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `birthday` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `contact` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_time` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `nick_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `open_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `pet_type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `update_time` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `variety` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for vote
-- ----------------------------
DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote` (
  `competition_id` varchar(50) NOT NULL COMMENT '比赛id',
  `phone` varchar(50) DEFAULT NULL COMMENT '参赛人手机号',
  `open_id` varchar(100) NOT NULL COMMENT '参赛人微信open_id',
  `subject` varchar(255) DEFAULT NULL COMMENT '作品主题',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '宠物昵称',
  `votes` int(11) DEFAULT '0' COMMENT '票数',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `type` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增',
  PRIMARY KEY (`competition_id`,`open_id`),
  UNIQUE KEY `new_uk_name` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='作品投票表'

-- ----------------------------
-- Records of vote
-- ----------------------------
BEGIN;
INSERT INTO `vote` VALUES ('54c77905-9791-441e-bf17-6208f3d7170c', '15313621879', 'dfales-ewqj32f-2r23delwlefje', '最美猫', 'micky', 1, '2020-12-13 09:56:54', '2020-12-19 17:26:01', 'image', 'https://images.pexels.com/photos/57416/cat-sweet-kitty-animals-57416.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500');
INSERT INTO `vote` VALUES ('54c77905-9791-441e-bf17-6208f3d7170c', '15313621879', 'dfalses-ewqj32f-2r23delwlefje', '最丑狗', 'mongo', 1, '2020-12-13 09:56:54', '2020-12-19 17:26:02', 'image', 'https://images.pexels.com/photos/58997/pexels-photo-58997.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500');
INSERT INTO `vote` VALUES ('54c77905-9791-441e-bf17-6208f3d7170c', '15313621879', 'dfalses-ewqj32f-2r23dlwlefje', '夏日清风', 'summer', 1, '2020-12-13 09:56:54', '2020-12-19 17:26:03', 'image', 'https://images.pexels.com/photos/2194261/pexels-photo-2194261.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500');
INSERT INTO `vote` VALUES ('54c77905-9791-441e-bf17-6208f3d7170c', '15313621879', 'dfalses-ewqj32f-2r23dlwlfje', '冬日莹雪', 'snower', 1, '2020-12-13 09:56:54', '2020-12-19 17:26:04', 'image', 'https://images.pexels.com/photos/1741205/pexels-photo-1741205.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500');
INSERT INTO `vote` VALUES ('54c795-9791-441e-bf17-6208f3d7170c', '15313621879', 'dfales-ewqj32f-2r23delwlefje', '最美猫', 'micky', 1, '2020-12-13 09:56:54', '2020-12-19 17:26:01', 'image', 'https://images.pexels.com/photos/57416/cat-sweet-kitty-animals-57416.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500');
INSERT INTO `vote` VALUES ('54c795-9791-441e-bf17-6208f3d7170c', '15313621879', 'dfalses-ewqj32f-2r23delwlefje', '最丑狗', 'mongo', 1, '2020-12-13 09:56:54', '2020-12-19 17:26:02', 'image', 'https://images.pexels.com/photos/58997/pexels-photo-58997.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
