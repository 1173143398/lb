package com.execpt;

public class WxException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WxException(String message) {
		super(message);
	}
	
	public WxException(String message, Throwable cause) {
        super(message, cause);
    }
}
