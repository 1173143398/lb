package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.ClientConfig;
import com.dao.ClientConfigDao;
import com.service.ClientConfigService;

@Service
public class ClientConfigServiceImpl implements ClientConfigService {

	@Autowired
	private ClientConfigDao clientConfigDao;
	
	@Override
	public ClientConfig getClientConfig(String funcNo) {
		return clientConfigDao.getClientConfig(funcNo);
	}

}
