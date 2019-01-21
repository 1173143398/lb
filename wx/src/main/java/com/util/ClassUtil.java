package com.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.execpt.WxException;
import com.message.IMessage;

public class ClassUtil {
	
	private static Log log = LogFactory.getLog(ClassUtil.class);
	
	public static Class<? extends IMessage> getClass(String className, Class<? extends IMessage> requiedType) {
		try {
			Class<?> forName = Class.forName(className);
			if (requiedType.isAssignableFrom(forName)) {
				Class<? extends IMessage> asSubclass = forName.asSubclass(IMessage.class);
				return asSubclass;
			}
		} catch (ClassNotFoundException e) {
			log.error("加载类型异常",e);
			throw new WxException("加载类型异常");
		}
		return null;
	}
}
