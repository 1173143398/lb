package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.ServerConfig;
import com.dao.ServerConfigDao;
import com.service.ServerConfigService;

@Service
public class ServerConfigServiceImpl implements ServerConfigService {

	@Autowired
	private ServerConfigDao serverConfigDao;
	
	@Override
	public ServerConfig getServerConfig(String msgType,String eventType) {
		return serverConfigDao.getServerConfig(msgType, eventType);
	}

}
