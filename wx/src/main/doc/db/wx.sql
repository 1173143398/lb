/*
Navicat MySQL Data Transfer

Source Server         : con
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : wx

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

Date: 2019-02-19 18:22:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `clilent_config`
-- ----------------------------
DROP TABLE IF EXISTS `clilent_config`;
CREATE TABLE `clilent_config` (
  `FUNC_NO` char(5) NOT NULL,
  `FUNC_DESC` varchar(256) DEFAULT NULL,
  `SHCEMA` varchar(10) DEFAULT NULL,
  `URL` varchar(512) DEFAULT NULL,
  `METHOD` varchar(10) DEFAULT NULL,
  `REQ_CLASS` varchar(256) DEFAULT NULL,
  `REQ_MSG_TYPE` varchar(10) DEFAULT NULL,
  `RESP_CLASS` varchar(256) DEFAULT NULL,
  `RESP_MSG_TYPE` varchar(10) DEFAULT NULL,
  `SERVICE_BEAN` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`FUNC_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clilent_config
-- ----------------------------
INSERT INTO `clilent_config` VALUES ('00000', '获取access_token', 'HTTPS', 'https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=${appId}&secret=${appSecret}', 'GET', 'com.message.client.DefaultClientMessage', 'JSON', 'com.message.client.TokenMessage', 'JSON', 'accessTokenMessageService');
INSERT INTO `clilent_config` VALUES ('00001', '上传素材', 'HTTPS', 'https://api.weixin.qq.com/cgi-bin/media/upload?access_token=${accessToken}&type=${type}', 'UPLOAD', 'com.message.client.UploadFileInMessage', 'JSON', 'com.message.client.UploadFileOutMessage', 'JSON', 'uploadFileMessageService');
INSERT INTO `clilent_config` VALUES ('00002', '下载素材', 'HTTP', 'https://api.weixin.qq.com/cgi-bin/media/get?access_token=${accessToken}&media_id=${mediaId}', 'DOWNLOAD', 'com.message.client.DownloadFileInMessage', 'JSON', 'com.message.client.DownloadFileOutMessage', 'JSON', 'downloadFileMessageService');
INSERT INTO `clilent_config` VALUES ('00003', '创建菜单', 'HTTPS', 'https://api.weixin.qq.com/cgi-bin/menu/create?access_token=${accessToken}', 'POST', 'com.message.client.CreateMenuInMessage', 'JSON', 'com.message.client.CreateMenuOutMessage', 'JSON', 'createMenuMessageService');
INSERT INTO `clilent_config` VALUES ('00004', '获取jsapi_ticket', 'HTTPS', 'https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=${accessToken}&type=jsapi', 'GET', 'com.message.client.DefaultClientMessage', 'JSON', 'com.message.client.JsapiTicketMessage', 'JSON', 'jsapiTicketMessageService');
INSERT INTO `clilent_config` VALUES ('00005', '删除菜单', 'HTTPS', 'https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=${accessToken}', 'GET', 'com.message.client.DefaultClientMessage', 'JSON', 'com.message.client.DeleteMenuMessage', 'JSON', 'deleteMenuMessageService');
INSERT INTO `clilent_config` VALUES ('00007', '获取微信支付沙箱signkey', '', '', '', 'com.message.client.SandboxSignKeyInMessage', 'JSON', 'com.message.client.SandboxSignKeyOutMessage', 'JSON', 'sandboxSignKeyService');

-- ----------------------------
-- Table structure for `server_config`
-- ----------------------------
DROP TABLE IF EXISTS `server_config`;
CREATE TABLE `server_config` (
  `MSG_TYPE` varchar(10) NOT NULL,
  `EVENT_TYPE` varchar(10) NOT NULL,
  `REQ_CLASS` varchar(256) DEFAULT NULL,
  `REQ_MSG_TYPE` varchar(10) DEFAULT NULL,
  `RESP_CLASS` varchar(256) DEFAULT NULL,
  `RESP_MSG_TYPE` varchar(10) DEFAULT NULL,
  `SERVICE_BEAN` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`MSG_TYPE`,`EVENT_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of server_config
-- ----------------------------
INSERT INTO `server_config` VALUES ('text', 'text', 'com.message.server.TextInMessage', 'XML', 'com.message.server.TextOutMessage', 'XML', 'textServerMessageService');

-- ----------------------------
-- Table structure for `system_config`
-- ----------------------------
DROP TABLE IF EXISTS `system_config`;
CREATE TABLE `system_config` (
  `SERVER_MSG_TYPE` varchar(10) DEFAULT NULL,
  `APP_ID` varchar(256) DEFAULT NULL,
  `APP_SECRET` varchar(256) DEFAULT NULL,
  `TOKEN` varchar(256) DEFAULT NULL,
  `ACCESS_TOKEN` varchar(256) DEFAULT NULL,
  `JSAPI_TICKET` varchar(256) DEFAULT NULL,
  `SECRITY_TYPE` char(1) DEFAULT NULL,
  `ENCODING_AESKEY` varchar(256) DEFAULT NULL,
  `DOMAIN` varchar(256) DEFAULT NULL,
  `EXPIRES_IN` int(11) DEFAULT NULL,
  `TMS` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_config
-- ----------------------------
INSERT INTO `system_config` VALUES ('XML', 'wx91d49736e4bc722d', 'e3f93c71d0aaa303e268b85c7c2c3a4c', 'liangbo', '18_Qzu1RIXaVUdCmc0Sh_99IedXTV7Fy2XixAdxyoun1hoK6y2u1LdeBmR0E7udrV94IPo3yVYWWKAQ7vRWkUuu0z-DAFa6LM6rPYNS5kl6GdvdzEmtcLQOP89MThcwvrv_2bB1vJrtxpbnZv-XMEUbAGAASI', 'LIKLckvwlJT9cWIhEQTwfHFkbt0FVcbNLt8UimDxzsoaAkTAkRLO2gH6mLpZBZc-Tzib-eDQgRzQpjbGzmi1Yw', 'C', 'U48TF38rZ1Mrn1YBmN1iEyWyyQvhZ1oBGX1nbdCbRiS', null, '7200', '2019-02-19 15:14:19');

-- ----------------------------
-- Table structure for `wx_pay_config`
-- ----------------------------
DROP TABLE IF EXISTS `wx_pay_config`;
CREATE TABLE `wx_pay_config` (
  `APP_ID` varchar(256) DEFAULT NULL,
  `APP_SECRET` varchar(256) DEFAULT NULL,
  `MCH_ID` varchar(256) DEFAULT NULL,
  `WX_KEY` varchar(256) DEFAULT NULL,
  `SANDBOX_SIGN_KEY` varchar(256) DEFAULT NULL,
  `CERT_PATH` varchar(256) DEFAULT NULL,
  `WX_DOMAIN` varchar(256) DEFAULT NULL,
  `PRIMARY_DOMAIN` varchar(10) DEFAULT NULL,
  `NOTIFY_URL` varchar(256) DEFAULT NULL,
  `AUTO_REPORT` varchar(10) DEFAULT NULL,
  `USE_SANDBOX` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_pay_config
-- ----------------------------
INSERT INTO `wx_pay_config` VALUES ('wx426b3015555a46be', '7813490da6f1265e4901ffb80afaa36f', '1900009851', '8934e7d15453e97507ef794cf7b0519d', '9fdaddc81970990e040c04fa488349aa', null, 'api.mch.weixin.qq.com', 'true', 'http://u5qn9q.natappfree.cc/pay/receive', 'false', 'true');
