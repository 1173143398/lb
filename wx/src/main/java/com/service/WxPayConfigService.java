package com.service;

import com.config.WxPayConfig;

public interface WxPayConfigService {

	WxPayConfig getWxPayConfig();
	
	void update(WxPayConfig wxPayConfig);
}
