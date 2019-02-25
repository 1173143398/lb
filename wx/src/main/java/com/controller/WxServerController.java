package com.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.config.ServerConfig;
import com.context.TransactionContext;
import com.execpt.WxException;
import com.message.server.EventMessage;
import com.message.server.Message;
import com.parse.IContentParser;
import com.parse.impl.ParserManager;
import com.security.SecurityService;
import com.service.ServerConfigService;
import com.service.ServerMessageService;
import com.util.ExceptionUtil;
import com.util.StringUtil;

@Controller
public class WxServerController {

	private static Log log = LogFactory.getLog(WxServerController.class);
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private ServerConfigService serverConfigService;
	
	@Autowired
	private ParserManager parserManager;
	
	@Autowired
	@Qualifier("simpleServerMessageService")
	private ServerMessageService simpleServerMessageService;
	
	@RequestMapping(value = "/receive",method = RequestMethod.GET)
	@ResponseBody
	public String sign(@RequestParam(value = "signature",required = false) String signature,
			@RequestParam(value = "timestamp",required = false) String timestamp,
			@RequestParam(value = "nonce",required = false) String nonce,
			@RequestParam(value = "echostr",required = false) String echostr){
		log.info("signature:"+signature+" timestamp:"+timestamp +" nonce:"+nonce+" echostr:"+echostr);
		if(securityService.checkSign(signature, timestamp, nonce)){
			return echostr;
		}
		return "";
	}
	
	@RequestMapping(value = "/receive",method = RequestMethod.POST)
	@ResponseBody
	public String receive(@RequestBody String message,
			@RequestParam(value = "signature",required = false) String signature,
			@RequestParam(value = "timestamp",required = false) String timestamp,
			@RequestParam(value = "nonce",required = false) String nonce,
			@RequestParam(value = "encrypt_type",required = false) String encryptType,
			@RequestParam(value = "msg_signature",required = false) String msgSignature) {
		log.info("接收报文:" + message);
		if(securityService.checkSign(signature, timestamp, nonce) == false){
			throw new WxException("非法请求");
		}
		//解密报文
		String reqMsg = securityService.decrypt(message,timestamp,nonce,encryptType,msgSignature);
		log.info("解密后报文:" + reqMsg);
		//找出消息类型
		String msgType = getMsgType(reqMsg);
		String eventType = getEventType(reqMsg, msgType);
		//匹配数据库配置
		ServerConfig serverConfig = serverConfigService.getServerConfig(msgType, eventType);
		String respMsg = null;
		if(serverConfig == null){
			respMsg = simpleServerMessageService.doService(serverConfig, message);
		}else{
			ServerMessageService messageService = TransactionContext.getBean(serverConfig.getServiceBean(), ServerMessageService.class);
			ExceptionUtil.alert(messageService,"请求报文处理类缺失");
			respMsg = messageService.doService(serverConfig,reqMsg);
		}
		//加密报文
		String sMessage = securityService.encrypt(respMsg,timestamp,nonce,encryptType,msgSignature);
		log.info("加密报文:"+sMessage);
		return sMessage;
	}

	private String getEventType(String reqMsg, String msgType) {
		String eventType = msgType;
		log.info("消息类型:" + msgType);
		//如果是event，再找事件类型
		if("event".contentEquals(msgType)) {
			eventType = getEventType(reqMsg);
			log.info("事件类型:" + eventType);
			ExceptionUtil.alert(eventType,"获取事件类型失败");
		}
		return eventType;
	}
	
	

	private String getEventType(String msg) {
		if(StringUtil.isNull(msg) == false) {
			String type = TransactionContext.getSystemConfig().getServerMsgType();
			IContentParser parser = parserManager.getParser(type);
			if(parser == null) {
				throw new WxException("没有请求报文解析类");
			}
			EventMessage xmlToBean = (EventMessage)parser.messageToBean(msg, EventMessage.class);
			return xmlToBean.getEventType();
		}
		return null;
	}

	private String getMsgType(String msg) {
		if(StringUtil.isNull(msg) == false) {
			String type = TransactionContext.getSystemConfig().getServerMsgType();
			IContentParser parser = parserManager.getParser(type);
			if(parser == null) {
				throw new WxException("没有请求报文解析类");
			}
			Message xmlToBean = (Message)parser.messageToBean(msg, Message.class);
			return xmlToBean.getMsgType();
		}
		return null;
	}
	
}
