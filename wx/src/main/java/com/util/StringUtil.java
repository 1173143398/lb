package com.util;

public class StringUtil {

	public static boolean isNull(Object o) {
		if(o == null || "".equals(o)) {
			return true;
		}
		return false;
	}
}
