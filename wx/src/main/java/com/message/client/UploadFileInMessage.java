package com.message.client;

import com.message.IMessage;

public class UploadFileInMessage implements IMessage{

	private String filePath;
	
	private String type;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
