package com.http;

public interface FileTransfer {

	String upload(FilePo file,FileTransferParam param);
	
	String download(FilePo file,FileTransferParam param);
}
