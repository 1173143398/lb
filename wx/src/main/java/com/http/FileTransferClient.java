package com.http;

import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.execpt.WxException;
import com.util.Constants;

@Service
public class FileTransferClient implements FileTransfer ,InitializingBean{

	private static Log log = LogFactory.getLog(PostHttpsNetWorkClient.class);
	
	@Autowired
	private FileTransferManager fileTransferManager;
	
	@Autowired
	private CloseableHttpClient closeableHttpClient;

	@Override
	public void afterPropertiesSet() throws Exception {
		fileTransferManager.addFileTransfer(Constants.UPLOAD, this);
		fileTransferManager.addFileTransfer(Constants.DOWNLOAD, this);
	}

	@Override
	public String upload(FilePo file, FileTransferParam param) {
		 try {
			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder
					.create();
			multipartEntityBuilder.addBinaryBody(file.getName(), file.getFile());
			if(param != null){
				for (Map.Entry<String, String> e : param.entrySet()) {
					multipartEntityBuilder.addTextBody(e.getKey(), e.getValue());
				}
			}
			HttpEntity httpEntity = multipartEntityBuilder.build();
			HttpPost post = new HttpPost(file.getUrl());
			post.setEntity(httpEntity);
			CloseableHttpResponse execute = closeableHttpClient.execute(post);
			return EntityUtils.toString(execute.getEntity());
		} catch (Exception e) {
			log.error("FileTransferClient通信异常",e);
			throw new WxException("通信异常");
		}
	}



	@Override
	public String download(FilePo file, FileTransferParam param){
		try{
			HttpGet get = new HttpGet(file.getUrl());
			CloseableHttpResponse execute = closeableHttpClient.execute(get);
			InputStream content = execute.getEntity().getContent();
			FileUtils.copyInputStreamToFile(content, file.getFile());
			EntityUtils.consume(execute.getEntity());
			return "";
		}catch(Exception e){
			log.error("FileTransferClient通信异常",e);
			throw new WxException("通信异常");
		}
	}

}
