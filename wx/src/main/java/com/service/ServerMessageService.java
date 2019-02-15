package com.service;

import com.config.ServerConfig;

public interface ServerMessageService {

	String doService(ServerConfig serverConfig,String message);
}
