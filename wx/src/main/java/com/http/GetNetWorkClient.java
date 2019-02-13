package com.http;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.execpt.WxException;
import com.util.Constants;

@Service
public class GetNetWorkClient implements NetWorkClient,InitializingBean {

	private Log log = LogFactory.getLog(GetNetWorkClient.class);
	
	@Autowired
	private CloseableHttpClient closeableHttpClient;
	
	@Autowired
	private NetWorkManager netWorkManager;
	
	@Override
	public String send(String url, String message) {
		try {
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse execute = closeableHttpClient.execute(get);
			return EntityUtils.toString(execute.getEntity());
		} catch (Exception e) {
			log.error("GetHttpNetWorkClient通信异常",e);
			throw new WxException("通信异常");
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		netWorkManager.addClient(Constants.GET, this);
	}
	

}
