package com.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.config.SystemConfig;
import com.config.WxPayConfig;
import com.service.SystemConfigService;
import com.service.WxPayConfigService;

@Service
public class TransactionContext implements ApplicationContextAware,InitializingBean{

	private static ApplicationContext applicationContext;
	
	private static SystemConfig systemConfig;
	
	private static WxPayConfig wxPayConfig;
	
	@Autowired
	private SystemConfigService systemConfigService;
	
	@Autowired
	private WxPayConfigService wxPayConfigService;
	
	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		applicationContext = ctx;
	}

	public static <T> T getBean(String name,Class<T> requiredType) {
		return (T)applicationContext.getBean(name, requiredType);
	}
	
	public static SystemConfig getSystemConfig(){
		return systemConfig;
	}

	public static WxPayConfig getWxPayConfig(){
		return wxPayConfig;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		systemConfig = systemConfigService.getSystemConfig();
		wxPayConfig = wxPayConfigService.getWxPayConfig();
	}

}
