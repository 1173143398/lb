package com.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.ClientConfig;
import com.context.TransactionContext;
import com.service.ClientMessageService;
import com.service.SystemConfigService;
import com.service.WxPayConfigService;
import com.wxpay.WXPay;

@Service("refreshConfigMessageService")
public class RefreshConfigMessageService implements ClientMessageService {

	private static Log log = LogFactory.getLog(RefreshConfigMessageService.class);
	@Autowired
	private SystemConfigService systemConfigService;
	
	@Autowired
	private WxPayConfigService wxPayConfigService;
	
	@Autowired
	private WXPay wxPay;
	
	@Override
	public String doService(ClientConfig clientConfig, String message) {
		refresh();
		return "SUCCESS";
	}

	private void refresh(){
		log.info("更新配置");
		TransactionContext.setSystemConfig(systemConfigService.getSystemConfig());
		TransactionContext.setWxPayConfig(wxPayConfigService.getWxPayConfig());
	}
}
