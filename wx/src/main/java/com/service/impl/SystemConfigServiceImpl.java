package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.SystemConfig;
import com.dao.SystemConfigDao;
import com.execpt.WxException;
import com.service.SystemConfigService;

@Service
public class SystemConfigServiceImpl implements SystemConfigService {

	@Autowired
	private SystemConfigDao systemConfigDao;
	
	@Override
	public SystemConfig getSystemConfig() {
		SystemConfig systemConfig = systemConfigDao.getSystemConfig();
		if(systemConfig == null){
			throw new WxException("系统参数不存在");
		}
		return systemConfig;
	}

	@Override
	public void update(SystemConfig systemConfig) {
		// TODO Auto-generated method stub
		int i = systemConfigDao.update(systemConfig);
		if(i != 1) {
			throw new WxException("更新系统参数失败");
		}
	}

}
