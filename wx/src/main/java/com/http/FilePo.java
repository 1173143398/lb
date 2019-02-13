package com.http;

import java.io.File;

public class FilePo {

	private String name;
	
	private String filePath;
	
	private String url;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public File getFile(){
		return new File(filePath);
	}
}
