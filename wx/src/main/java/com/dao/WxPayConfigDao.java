package com.dao;

import com.config.WxPayConfig;

public interface WxPayConfigDao {

	WxPayConfig getWxPayConfig();
	
	int update(WxPayConfig wxPayConfig);
}
