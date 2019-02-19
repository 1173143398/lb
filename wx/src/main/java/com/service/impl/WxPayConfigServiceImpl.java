package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.WxPayConfig;
import com.dao.WxPayConfigDao;
import com.execpt.WxException;
import com.service.WxPayConfigService;

@Service
public class WxPayConfigServiceImpl implements WxPayConfigService {

	@Autowired
	private WxPayConfigDao wxPayConfigDao;
	@Override
	public WxPayConfig getWxPayConfig() {
		return wxPayConfigDao.getWxPayConfig();
	}

	@Override
	public void update(WxPayConfig wxPayConfig) {
		int i = wxPayConfigDao.update(wxPayConfig);
		if(i != 1){
			throw new WxException("修改支付配置失败");
		}
	}

}
