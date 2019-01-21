package com.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

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
import com.http.NetWorkClient;
import com.http.NetWorkManager;
import com.message.IMessage;
import com.message.server.EventMessage;
import com.message.server.Message;
import com.parse.IContentParser;
import com.parse.IWxExpressionParser;
import com.parse.impl.ParserManager;
import com.security.SecurityService;
import com.service.ClientConfigService;
import com.service.ClientMessageService;
import com.service.ServerConfigService;
import com.service.ServerMessageService;
import com.util.ClassUtil;
import com.util.Constants;
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
	
	@Value("${SERVER_DEFALUT_MSG_TYPE}")
	private String serverDefaultMsgType;
	
	@Autowired
	private ParserManager parserManager;
	
	@Autowired
	private NetWorkManager netWorkManager;
	@Autowired
	private IWxExpressionParser wxExpressionParser;
	@RequestMapping("receive")
	@ResponseBody
	public String receive(@RequestBody String message) {
		log.info("接收报文:" + message);
		//解密报文
		String dMesssage = securityService.decrypt(message);
		log.info("解密后报文:" + dMesssage);
		//找出消息类型
		String msgType = getMessage(dMesssage);
		alert(msgType,"获取消息类型失败");
		String eventType = msgType;
		log.info("消息类型:" + msgType);
		//如果是event，再找事件类型
		if("event".contentEquals(msgType)) {
			log.info("事件类型:" + eventType);
			eventType = getEventType(dMesssage);
			alert(eventType,"获取事件类型失败");
		}
		//匹配数据库配置
		ServerConfig serverConfig = serverConfigService.getServerConfig(msgType, eventType);
		alert(serverConfig,"消息处理配置缺失");
		//解析请求报文
		IContentParser reqParser = parserManager.getParser(serverConfig.getReqMsgType());
		alert(reqParser,"请求报文解析类缺失");
		
		Class<? extends IMessage> reqClass = ClassUtil.getClass(serverConfig.getReqClass(), IMessage.class);
		alert(reqClass,"请求报文类型应为:IMesssage.class");
		
		ServerMessageService messageService = TransactionContext.getBean(serverConfig.getServiceBean(), ServerMessageService.class);
		alert(messageService,"请求报文处理类缺失");
		
		IContentParser respParser = parserManager.getParser(serverConfig.getRespMsgType());
		alert(respParser,"响应报文解析类缺失");
		
		Class<? extends IMessage> respClass = ClassUtil.getClass(serverConfig.getRespClass(), IMessage.class);
		alert(respClass,"响应报文类型应为:IMesssage.class");
		//解析请求报文
		IMessage parseMsg = reqParser.messageToBean(message, reqClass);
		//处理
		IMessage rsMsg = messageService.doService(serverConfig,parseMsg,reqClass);
		//返回响应报文
		String rMesssage = respParser.beanToMessage(rsMsg, respClass);
		//加密报文
		String sMessage = securityService.encrypt(rMesssage);
		return sMessage;
	}
	
	@RequestMapping("/send")
	@ResponseBody
	public String send(@RequestBody(required = false) String message,@RequestParam("funcno") String funcNo) {
		alert(funcNo,"功能号不能为空");
		//获取配置
		ClientConfig clientConfig = clientConfigService.getClientConfig(funcNo);
		alert(clientConfig,"获取配置失败,功能号:" + funcNo);
		
		NetWorkClient client = netWorkManager.getClient(clientConfig.getShcema(), clientConfig.getMethod());
		alert(client,"网络访问类缺失");
		
		ClientMessageService messageService = TransactionContext.getBean(clientConfig.getServiceBean(), ClientMessageService.class);
		alert(messageService,"请求报文处理类缺失");
		
		IContentParser respParser = parserManager.getParser(clientConfig.getRespMsgType());
		alert(respParser,"响应报文解析类缺失");
		
		Class<? extends IMessage> respClass = ClassUtil.getClass(clientConfig.getRespClass(), IMessage.class);
		alert(respClass,"响应报文类应为:IMesssage.class");
		//GET方式处理 
		if(Constants.GET.equals(clientConfig.getMethod())) {
			String url = formatUrl(clientConfig.getUrl());
			log.info("发送URL:" + url);
			String rs = client.send(url, "");
			IMessage rsMsg = respParser.messageToBean(rs, respClass);
			IMessage afterSend = messageService.afterSend(clientConfig, rsMsg, respClass);
			String rMesssage = respParser.beanToMessage(afterSend, respClass);
			return rMesssage;
		}
		//解析请求报文
		IContentParser reqParser = parserManager.getParser(clientConfig.getReqMsgType());
		alert(reqParser,"请求报文解析类缺失");
		
		Class<? extends IMessage> reqClass = ClassUtil.getClass(clientConfig.getReqClass(), IMessage.class);
		alert(reqClass,"请求报文类型应为:IMesssage.class");
		
		
		
		//解析请求报文
		IMessage parseMsg = reqParser.messageToBean(message, reqClass);
		//访问wx前
		IMessage sendMsg = messageService.beforeSend(clientConfig, parseMsg, reqClass);
		//访问wx
		String url = formatUrl(clientConfig.getUrl());
		log.info("发送URL:" + url);
		String msg = reqParser.beanToMessage(sendMsg, reqClass);
		String rs = client.send(url, msg);
		//返回响应报文 
		IMessage rsMsg = respParser.messageToBean(rs, respClass);
		//访问wx后
		IMessage afterSend = messageService.afterSend(clientConfig, rsMsg, respClass);
		String rMesssage = respParser.beanToMessage(afterSend, respClass);
		return rMesssage;
	}
	

	private String formatUrl(String url) {
		Pattern compile = Pattern.compile("(\\$\\{\\w+\\})");
		Matcher matcher = compile.matcher(url);
		while(matcher.find()){
			String param = matcher.group();
			String pName = param.replace("$", "").replace("{", "").replace("}", "");
			String value = wxExpressionParser.getValue(TransactionContext.getSystemContext(), pName);
			url.replace(param, value);
		}
		return url;
	}

	private String getEventType(String msg) {
		if(StringUtil.isNull(msg) == false) {
			IContentParser parser = parserManager.getParser(serverDefaultMsgType);
			if(parser == null) {
				throw new WxException("没有请求报文解析类");
			}
			EventMessage xmlToBean = (EventMessage)parser.messageToBean(msg, EventMessage.class);
			return xmlToBean.getEventType();
		}
		return null;
	}

	private String getMessage(String msg) {
		if(StringUtil.isNull(msg) == false) {
			IContentParser parser = parserManager.getParser(serverDefaultMsgType);
			if(parser == null) {
				throw new WxException("没有请求报文解析类");
			}
			Message xmlToBean = (Message)parser.messageToBean(msg, Message.class);
			return xmlToBean.getMsgType();
		}
		return null;
	}
	
	private void alert(Object o,String message){
		if(StringUtil.isNull(o)){
			throw new WxException(message);
		}
	}
}
