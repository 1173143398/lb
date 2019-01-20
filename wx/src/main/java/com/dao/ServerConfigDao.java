package com.dao;

import com.config.ServerConfig;

public interface ServerConfigDao {

	public ServerConfig getServerConfig(String msgType,String eventType);
}
