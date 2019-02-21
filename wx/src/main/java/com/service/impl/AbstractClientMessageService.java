package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.config.ClientConfig;
import com.message.IMessage;
import com.parse.IContentParser;
import com.parse.IWxExpressionParser;
import com.parse.impl.ParserManager;
import com.service.ClientMessageService;
import com.util.ClassUtil;
import com.util.ExceptionUtil;
import com.util.StringUtil;

public abstract class AbstractClientMessageService implements
		ClientMessageService {

	@Autowired
	protected ParserManager parserManager;
	
	@Autowired
	protected IWxExpressionParser wxExpressionParser;
	
	protected String formatUrl(String url,IMessage reqParam) {
		return StringUtil.formatUrl(wxExpressionParser, url, reqParam);
	}

	@Override
	public String doService(ClientConfig clientConfig, String message) {
		IContentParser respParser = parserManager.getParser(clientConfig.getRespMsgType());
		ExceptionUtil.alert(respParser,"响应报文解析类缺失");
		
		Class<? extends IMessage> respClass = ClassUtil.getClass(clientConfig.getRespClass(), IMessage.class);
		ExceptionUtil.alert(respClass,"响应报文类应为:IMesssage.class");
		
		//解析请求报文
		IContentParser reqParser = parserManager.getParser(clientConfig.getReqMsgType());
		ExceptionUtil.alert(reqParser,"请求报文解析类缺失");
		
		Class<? extends IMessage> reqClass = ClassUtil.getClass(clientConfig.getReqClass(), IMessage.class);
		ExceptionUtil.alert(reqClass,"请求报文类型应为:IMesssage.class");
		
		//解析请求报文
		IMessage reqMsg = null;
		if(StringUtil.isNull(message) == false){
			reqMsg = reqParser.messageToBean(message, reqClass);
		}
		IMessage respMsg = this.doService(clientConfig, reqMsg);
		return respParser.beanToMessage(respMsg, respClass);
	}
}
