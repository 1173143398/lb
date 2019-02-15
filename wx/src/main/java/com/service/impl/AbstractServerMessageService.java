package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.config.ServerConfig;
import com.context.TransactionContext;
import com.message.IMessage;
import com.parse.IContentParser;
import com.parse.impl.ParserManager;
import com.service.ServerMessageService;
import com.util.ClassUtil;
import com.util.ExceptionUtil;

public abstract class AbstractServerMessageService implements ServerMessageService {

	@Autowired
	protected ParserManager parserManager;
	
	@Override
	public String doService(ServerConfig serverConfig, String message) {
		// 解析请求报文
		IContentParser reqParser = parserManager.getParser(serverConfig
				.getReqMsgType());
		ExceptionUtil.alert(reqParser, "请求报文解析类缺失");

		Class<? extends IMessage> reqClass = ClassUtil.getClass(
				serverConfig.getReqClass(), IMessage.class);
		ExceptionUtil.alert(reqClass, "请求报文类型应为:IMesssage.class");

		ServerMessageService messageService = TransactionContext.getBean(
				serverConfig.getServiceBean(), ServerMessageService.class);
		ExceptionUtil.alert(messageService, "请求报文处理类缺失");

		IContentParser respParser = parserManager.getParser(serverConfig
				.getRespMsgType());
		ExceptionUtil.alert(respParser, "响应报文解析类缺失");

		Class<? extends IMessage> respClass = ClassUtil.getClass(
				serverConfig.getRespClass(), IMessage.class);
		ExceptionUtil.alert(respClass, "响应报文类型应为:IMesssage.class");
		// 解析请求报文
		IMessage reqMessage = reqParser.messageToBean(message, reqClass);
		IMessage respMessage = this.service(serverConfig, reqMessage);
		//返回响应报文
		String rMesssage = respParser.beanToMessage(respMessage, respClass);
		return rMesssage;
	}

	protected abstract IMessage service(ServerConfig serverConfig, IMessage message);
}
