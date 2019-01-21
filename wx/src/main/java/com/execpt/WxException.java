package com.execpt;

public class WxException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorCode = "";
	
	public WxException(String message){
		this("",message);
	}
	
	public WxException(String errorCode,String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public WxException(String errorCode,String message, Throwable cause) {
        super(message, cause);
    }
	
	@Override
	public String toString() {
		return "["+errorCode+"]" + this.getMessage();
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
