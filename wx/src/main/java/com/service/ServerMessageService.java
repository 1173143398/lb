package com.service;

import com.config.ServerConfig;
import com.message.IMessage;

public interface ServerMessageService {

	IMessage doService(ServerConfig serverConfig,IMessage message);
}
