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

@Service
public class TransactionContext implements ApplicationContextAware,InitializingBean{

	private static ApplicationContext applicationContext;
	
	private static SystemConfig systemConfig;
	
	private static WxPayConfig wxPayConfig;
	
	@Autowired
	private SystemConfigService systemConfigService;
	
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
	
	public static void setSystemConfig(SystemConfig sysCfg){
		systemConfig = sysCfg;
	}
	public static WxPayConfig getWxPayConfig(){
		return wxPayConfig;
	}
	
	public static void setWxPayConfig(WxPayConfig wxPayCfg){
		wxPayConfig = wxPayCfg;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		TransactionContext.setSystemConfig(systemConfigService.getSystemConfig());
	}
	

}
