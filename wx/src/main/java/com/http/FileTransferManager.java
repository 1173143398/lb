package com.http;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class FileTransferManager {

	public Map<String,FileTransfer>  fileTransfers = new HashMap<String,FileTransfer>();
	
	public void addFileTransfer(String key,FileTransfer fileTransfer){
		fileTransfers.put(key, fileTransfer);
	}
	
	public FileTransfer getFileTransfer(String key){
		return fileTransfers.get(key);
	}
}
