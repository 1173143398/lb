package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.ConfigDetail;
import com.dao.ConfigDetailDao;
import com.service.ConfigDetailService;

@Service
public class ConfigDetailServiceImpl implements ConfigDetailService {

	@Autowired
	private ConfigDetailDao configDetailDao;
	
	@Override
	public ConfigDetail getConfigDetail(String configId) {
		return configDetailDao.getConfigDetail(configId);
	}

}
