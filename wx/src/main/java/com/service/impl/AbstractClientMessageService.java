package com.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.config.ClientConfig;
import com.context.TransactionContext;
import com.execpt.WxException;
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
		Pattern compile = Pattern.compile("(\\$\\{\\w+\\})");
		Matcher matcher = compile.matcher(url);
		while(matcher.find()){
			String param = matcher.group();
			String pName = param.replaceAll("[\\$|\\{|\\}]", "");
			String value = null;
			try{
				value = wxExpressionParser.getValue(TransactionContext.getSystemConfig(), pName);
				if(StringUtil.isNull(value)){
					value = wxExpressionParser.getValue(reqParam, pName);
				}
			}catch(Exception e){
				value = wxExpressionParser.getValue(reqParam, pName);
			}
			if(StringUtil.isNull(value)){
				throw new WxException("获取参数值失败" + pName);
			}
			url = url.replace(param, value);
		}
		return url;
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
	
	protected abstract IMessage doService(ClientConfig clientConfig,IMessage message);
	
}
