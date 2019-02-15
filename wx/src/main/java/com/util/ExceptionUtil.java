package com.util;

import com.execpt.WxException;

public class ExceptionUtil {

	public static void alert(Object o,String message){
		if(StringUtil.isNull(o)){
			throw new WxException(message);
		}
	}
}
