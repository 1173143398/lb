package com.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.config.ClientConfig;
import com.config.ConfigDetail;
import com.config.ServerConfig;
import com.context.TransactionContext;
import com.execpt.WxException;
import com.message.EventMessage;
import com.message.IMessage;
import com.message.Message;
import com.parse.ReqParser;
import com.parse.RespParser;
import com.security.SecurityService;
import com.service.ClientConfigService;
import com.service.ConfigDetailService;
import com.service.MessageService;
import com.service.ServerConfigService;
import com.util.ClassUtil;
import com.util.StringUtil;
import com.util.XmlUtil;
import com.validator.ReqValidator;
import com.validator.RespValidator;

@Controller
public class WxController {

	private static Log log = LogFactory.getLog(WxController.class);
	@Autowired
	private SecurityService securityService;
	@Autowired
	private ServerConfigService serverConfigService;
	@Autowired
	private ConfigDetailService configDetailService;
	
	@Autowired
	private ClientConfigService clientConfigService;
	
	@RequestMapping("receive")
	@ResponseBody
	public String receive(@RequestBody String messsage) {
		log.info("接收报文:" + messsage);
		//解密报文
		String dMesssage = securityService.decrypt(messsage);
		log.info("解密后报文:" + dMesssage);
		//找出消息类型
		Message bean = getMessage(dMesssage);
		if(bean == null) {
			throw new WxException("转换消息失败");
		}
		if(StringUtil.isNull(bean.getMsgType())) {
			throw new WxException("获取消息类型失败");
		}
		String msgType = bean.getMsgType();
		String eventType = msgType;
		log.info("消息类型:" + msgType);
		if("event".contentEquals(msgType)) {
			//如果是event，再找事件类型
			log.info("事件类型:" + eventType);
			eventType = getEventType(dMesssage);
			if(StringUtil.isNull(eventType)) {
				throw new WxException("获取事件类型失败");
			}
		}
		//匹配数据库配置
		ServerConfig serverConfig = serverConfigService.getServerConfig(msgType, eventType);
		if(serverConfig == null) {
			throw new WxException("没有消息处理配置,消息类型:"+msgType+" 事件类型:"+eventType);
		}
		//获取配置信息
		ConfigDetail configDetail = configDetailService.getConfigDetail(serverConfig.getConfigId());
		if(configDetail == null) {
			throw new WxException("没有消息详细处理配置,消息类型:"+msgType+" 事件类型:"+eventType);
		}
		String rIMesssage = doService(dMesssage, configDetail);
		//加密报文
		return securityService.encrypt(rIMesssage);
	}

	private String doService(String message, ConfigDetail configDetail) {
		//解析请求报文
		ReqParser reqParser = TransactionContext.getBean(configDetail.getReqMsgParseBean(), ReqParser.class);
		if(reqParser == null) {
			throw new WxException("没有请求报文解析类,CONFIG_ID:" + configDetail.getConfigId());
		}
		Class<? extends IMessage> reqClass = ClassUtil.getClass(configDetail.getReqClass(), IMessage.class);
		if(reqClass == null) {
			throw new WxException("没有请求报文类,应为:IMesssage.class,CONFIG_ID:" + configDetail.getConfigId());
		}
		IMessage parseMsg = reqParser.parse(message,reqClass,configDetail.getReqMsgType());
		//校验请求报文
		ReqValidator reqValidator = TransactionContext.getBean(configDetail.getReqMsgValidBean(), ReqValidator.class);
		if(reqValidator == null) {
			throw new WxException("没有请求报文校验类,CONFIG_ID:" + configDetail.getConfigId());
		}
		boolean validate = reqValidator.validate(parseMsg,reqClass);
		if(validate == false) {
			throw new WxException("请求报文校验失败,CONFIG_ID:" + configDetail.getConfigId());
		}
		//处理请求报文
		MessageService messsageService = TransactionContext.getBean(configDetail.getServiceBean(), MessageService.class);
		if(messsageService == null) {
			throw new WxException("没有请求报文处理类,CONFIG_ID:" + configDetail.getConfigId());
		}
		IMessage rsMsg = messsageService.doService(parseMsg,reqClass);
		//响应报文校验
		RespValidator respValidator = TransactionContext.getBean(configDetail.getRespMsgValidBean(), RespValidator.class);
		if(respValidator == null) {
			throw new WxException("没有响应报文校验类,CONFIG_ID:" + configDetail.getConfigId());
		}
		Class<? extends IMessage> respClass = ClassUtil.getClass(configDetail.getRespClass(), IMessage.class);
		if(respClass == null) {
			throw new WxException("没有响应报文类,应为:IMesssage.class,CONFIG_ID:" + configDetail.getConfigId());
		}
		validate = respValidator.validate(rsMsg,respClass);
		if(validate == false) {
			throw new WxException("响应报文校验失败,CONFIG_ID:" + configDetail.getConfigId());
		}
		//返回响应报文 
		RespParser respParser = TransactionContext.getBean(configDetail.getRespMsgParseBean(), RespParser.class);
		if(respParser == null) {
			throw new WxException("没有响应报文解析类,CONFIG_ID:" + configDetail.getConfigId());
		}
		String rIMesssage = respParser.parse(rsMsg,respClass,configDetail.getRespMsgType());
		return rIMesssage;
	}
	
	@RequestMapping("/send")
	@ResponseBody
	public String send(@RequestBody String message,@RequestParam("funcno") String funcNo) {
		if(StringUtil.isNull(funcNo)) {
			throw new WxException("功能号不能为空");
		}
		//获取配置
		ClientConfig clientConfig = clientConfigService.getClientConfig(funcNo);
		if(clientConfig == null) {
			throw new WxException("获取配置失败,功能号:" + funcNo);
		}
		//获取信息处理配置
		ConfigDetail configDetail = configDetailService.getConfigDetail(clientConfig.getConfigId());
		if(configDetail == null) {
			throw new WxException("获取配置失败,功能号:" + funcNo);
		}
		return this.doService(message, configDetail);
	}
	

	private String getEventType(String msg) {
		if(StringUtil.isNull(msg) == false) {
			EventMessage xmlToBean = XmlUtil.xmlToBean(msg, EventMessage.class);
			return xmlToBean.getEventType();
		}
		return null;
	}

	private Message getMessage(String msg) {
		if(StringUtil.isNull(msg) == false) {
			Message xmlToBean = XmlUtil.xmlToBean(msg, Message.class);
			return xmlToBean;
		}
		return null;
	}
}
