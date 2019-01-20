package com.service;

import com.config.ServerConfig;

public interface ServerConfigService {

	public ServerConfig getServerConfig(String msgType,String eventType);
}
