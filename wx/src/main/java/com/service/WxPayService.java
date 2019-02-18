package com.service;

import java.util.Map;

public interface WxPayService {

	String sign(Map<String,String> data);
}
