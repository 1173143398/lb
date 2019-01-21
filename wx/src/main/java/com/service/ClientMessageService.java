package com.service;

import com.config.ClientConfig;
import com.message.IMessage;

public interface ClientMessageService {

	IMessage beforeSend(ClientConfig clientConfig,IMessage message,Class<? extends IMessage> requiredType);
	
	IMessage afterSend(ClientConfig clientConfig,IMessage message,Class<? extends IMessage> requiredType);
}
