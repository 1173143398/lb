package com.service;

import com.config.ClientConfig;
import com.message.IMessage;

public interface ClientMessageService {

	IMessage doService(ClientConfig clientConfig,IMessage message,Class<? extends IMessage> requiredType);
}
