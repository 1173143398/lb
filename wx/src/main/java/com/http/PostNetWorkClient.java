package com.http;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.execpt.WxException;
import com.util.Constants;

@Service
public class PostNetWorkClient implements NetWorkClient ,InitializingBean{

	private static Log log = LogFactory.getLog(PostNetWorkClient.class);
	
	@Autowired
	private CloseableHttpClient closeableHttpClient;
	
	@Autowired
	private NetWorkManager netWorkManager;
	
	@Override
	public String send(String url, String message) {
		try {
			HttpPost post = new HttpPost(url);
			post.setHeader("Content-Type", "application/json; charset=utf-8");
			StringEntity entity = new StringEntity(message,"UTF-8");
			post.setEntity(entity);
			CloseableHttpResponse execute = closeableHttpClient.execute(post);
			return EntityUtils.toString(execute.getEntity());
		} catch (Exception e) {
			log.error("PostHttpNetWorkClient通信异常",e);
			throw new WxException("通信异常");
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		netWorkManager.addClient(Constants.POST, this);
	}

}
