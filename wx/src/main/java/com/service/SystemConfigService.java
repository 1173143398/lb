package com.service;

import com.config.SystemConfig;

public interface SystemConfigService {
	
	SystemConfig getSystemConfig();

	void update(SystemConfig systemConfig);
}
