package com.util;

import com.alibaba.fastjson.JSONObject;
import com.message.IMessage;

public class JsonUtil {

	public static <T> T jsonToBean(String message,Class<? extends IMessage> requiredType) {
		return (T) JSONObject.parseObject(message, requiredType);
	}
	
	public static String beanToJson(IMessage message,Class<? extends IMessage> requiredType) {
		return JSONObject.toJSONString(message);
	}
}
