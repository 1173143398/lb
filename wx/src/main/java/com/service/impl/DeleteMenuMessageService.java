package com.service.impl;

import org.springframework.stereotype.Service;

import com.config.ClientConfig;
import com.http.GetRequestExecutor;
import com.message.IMessage;
import com.util.ClassUtil;

@Service("deleteMenuMessageService")
public class DeleteMenuMessageService extends AbstractClientMessageService{

	@Override
	public IMessage doService(ClientConfig clientConfig, IMessage message) throws Exception{
		String formatUrl = this.formatUrl(clientConfig.getUrl(), message);
		Class<? extends IMessage> reqClass = ClassUtil.getClass(clientConfig.getReqClass(), IMessage.class);
		String msg = parserManager.getParser(clientConfig.getReqMsgType()).beanToMessage(message, reqClass);
		
		GetRequestExecutor getRequestExecutor = new GetRequestExecutor();
		String send = getRequestExecutor.execute(httpClient, formatUrl, msg);
		
		Class<? extends IMessage> respClass = ClassUtil.getClass(clientConfig.getRespClass(), IMessage.class);
		IMessage messageToBean = parserManager.getParser(clientConfig.getRespMsgType()).messageToBean(send, respClass);
		return messageToBean;
	}

	

}
