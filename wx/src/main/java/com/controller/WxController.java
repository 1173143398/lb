package com.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.config.ClientConfig;
import com.config.ServerConfig;
import com.context.TransactionContext;
import com.execpt.WxException;
import com.http.NetWorkManager;
import com.message.EventMessage;
import com.message.IMessage;
import com.message.Message;
import com.parse.ContentParser;
import com.parse.impl.ParserManager;
import com.security.SecurityService;
import com.service.ClientConfigService;
import com.service.ServerConfigService;
import com.service.ServerMessageService;
import com.util.ClassUtil;
import com.util.StringUtil;

@Controller
public class WxController {

	private static Log log = LogFactory.getLog(WxController.class);
	@Autowired
	private SecurityService securityService;
	@Autowired
	private ServerConfigService serverConfigService;
	
	@Autowired
	private ClientConfigService clientConfigService;
	
	@Value("SERVER_DEFALUT_MSG_TYPE")
	private String serverDefaultMsgType;
	
	@Autowired
	private ParserManager parserManager;
	
	@Autowired
	private NetWorkManager netWorkManager;
	
	@RequestMapping("receive")
	@ResponseBody
	public String receive(@RequestBody String message) {
		log.info("接收报文:" + message);
		//解密报文
		String dMesssage = securityService.decrypt(message);
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
		//解析请求报文
		ContentParser reqParser = parserManager.getParser(serverConfig.getReqMsgType());
		if(reqParser == null) {
			throw new WxException("没有请求报文解析类");
		}
		Class<? extends IMessage> reqClass = ClassUtil.getClass(serverConfig.getReqClass(), IMessage.class);
		if(reqClass == null) {
			throw new WxException("没有请求报文类,应为:IMesssage.class");
		}
		IMessage parseMsg = reqParser.messageToBean(message, reqClass);
		//处理请求报文
		ServerMessageService messageService = TransactionContext.getBean(serverConfig.getServiceBean(), ServerMessageService.class);
		if(messageService == null) {
			throw new WxException("没有请求报文处理类");
		}
		
		IMessage rsMsg = messageService.doService(serverConfig,parseMsg,reqClass);
		ContentParser respParser = parserManager.getParser(serverConfig.getRespMsgType());
		//返回响应报文 
		if(respParser == null) {
			throw new WxException("没有响应报文解析类");
		}
		Class<? extends IMessage> respClass = ClassUtil.getClass(serverConfig.getRespClass(), IMessage.class);
		if(respClass == null) {
			throw new WxException("没有响应报文类,应为:IMesssage.class");
		}
		String rIMesssage = respParser.beanToMessage(rsMsg, respClass);
		//加密报文
		return securityService.encrypt(rIMesssage);
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
		
		return message;
	}
	

	private String getEventType(String msg) {
		if(StringUtil.isNull(msg) == false) {
			ContentParser parser = parserManager.getParser(serverDefaultMsgType);
			if(parser == null) {
				throw new WxException("没有请求报文解析类");
			}
			EventMessage xmlToBean = (EventMessage)parser.messageToBean(msg, EventMessage.class);
			return xmlToBean.getEventType();
		}
		return null;
	}

	private Message getMessage(String msg) {
		if(StringUtil.isNull(msg) == false) {
			ContentParser parser = parserManager.getParser(serverDefaultMsgType);
			if(parser == null) {
				throw new WxException("没有请求报文解析类");
			}
			Message xmlToBean = (Message)parser.messageToBean(msg, Message.class);
			return xmlToBean;
		}
		return null;
	}
}
