package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.ClientConfig;
import com.http.NetWorkManager;
import com.message.IMessage;
import com.parse.IWxExpressionParser;
import com.parse.impl.ParserManager;
import com.util.ClassUtil;

@Service("createMenuMessageService")
public class CreateMenuMessageService extends AbstractClientMessageService {

	@Autowired
	private IWxExpressionParser wxExpressionParser;
	
	@Autowired
	private ParserManager parserManager;
	
	@Autowired
	private NetWorkManager netWorkManager;
	
	@Override
	public IMessage doService(ClientConfig clientConfig, IMessage message) {
		String formatUrl = this.formatUrl(wxExpressionParser, clientConfig.getUrl(), message);
		Class<? extends IMessage> reqClass = ClassUtil.getClass(clientConfig.getReqClass(), IMessage.class);
		String msg = parserManager.getParser(clientConfig.getReqMsgType()).beanToMessage(message, reqClass);
		String send = netWorkManager.getClient(clientConfig.getMethod()).send(formatUrl, msg);
		Class<? extends IMessage> respClass = ClassUtil.getClass(clientConfig.getRespClass(), IMessage.class);
		IMessage messageToBean = parserManager.getParser(clientConfig.getRespMsgType()).messageToBean(send, respClass);
		return messageToBean;
	}

}
