package com.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class TransactionContext implements ApplicationContextAware{

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		applicationContext = ctx;
		
	}

	public static <T> T getBean(String name,Class<T> requiredType) {
		return (T)applicationContext.getBean(name, requiredType);
	}
}
