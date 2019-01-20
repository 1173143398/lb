package com.util;

import com.message.IMessage;

public class ClassUtil {
	public static Class<? extends IMessage> getClass(String className, Class<? extends IMessage> requiedType) {
		try {
			Class<?> forName = Class.forName(className);
			if (requiedType.isAssignableFrom(forName)) {
				Class<? extends IMessage> asSubclass = forName.asSubclass(IMessage.class);
				return asSubclass;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
