package com.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.config.SystemConfig;
import com.service.SystemConfigService;

@Service
public class TransactionContext implements ApplicationContextAware,InitializingBean{

	private static ApplicationContext applicationContext;
	
	private static SystemContext systemContext = new SystemContext();
	
	@Autowired
	private SystemConfigService systemConfigService;
	
	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		applicationContext = ctx;
		
	}

	public static <T> T getBean(String name,Class<T> requiredType) {
		return (T)applicationContext.getBean(name, requiredType);
	}
	
	public static SystemContext getSystemContext(){
		return systemContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		SystemConfig systemConfig = systemConfigService.getSystemConfig();
		systemContext.setAccessToken(systemConfig.getAccessToken());
		systemContext.setAppId(systemConfig.getAppId());
		systemContext.setAppSecret(systemConfig.getAppSecret());
		systemContext.setServerMsgType(systemConfig.getServerMsgType());
		systemContext.setTimerUpdateTokenUrl(systemConfig.getTimerUpdateTokenUrl());
		systemContext.setToken(systemConfig.getToken());
		systemContext.setJsapiTicket(systemConfig.getJsapiTicket());
	}

}
