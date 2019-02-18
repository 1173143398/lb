package com.service;

import com.config.ClientConfig;
import com.message.IMessage;

public interface ClientMessageService {

	String doService(ClientConfig clientConfig,String message);
	
	IMessage doService(ClientConfig clientConfig,IMessage message);
}
