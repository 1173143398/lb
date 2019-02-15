package com.service.impl;

import org.springframework.stereotype.Service;

import com.config.ServerConfig;
import com.service.ServerMessageService;

@Service("simpleServerMessageService")
public class SimpleServerMessageService implements ServerMessageService {

	@Override
	public String doService(ServerConfig serverConfig, String message) {
		return "";
	}

}
