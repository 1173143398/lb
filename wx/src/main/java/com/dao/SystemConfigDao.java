package com.dao;

import com.config.SystemConfig;

public interface SystemConfigDao {

	SystemConfig getSystemConfig();
	
	int update(SystemConfig systemConfig);
}
