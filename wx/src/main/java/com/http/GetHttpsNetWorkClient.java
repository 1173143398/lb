package com.http;

import java.util.HashMap;
import java.util.Map;

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
public class GetHttpsNetWorkClient implements NetWorkClient,InitializingBean {

	private static Log log = LogFactory.getLog(GetHttpsNetWorkClient.class);
			
	@Autowired
	private NetWorkManager netWorkManager;
	
	@Autowired
	private CloseableHttpClient closeableHttpClient;
	
	@Override
	public String send(String url, String message) {
		try {
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse execute = closeableHttpClient.execute(get);
			return EntityUtils.toString(execute.getEntity());
		} catch (Exception e) {
			log.error("GetHttpsNetWorkClient通信异常",e);
			throw new WxException("通信异常");
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String, NetWorkClient> client = netWorkManager.getClient(Constants.HTTPS);
		if(client == null){
			client = new HashMap<String, NetWorkClient>();
			netWorkManager.addClient(Constants.HTTPS, client);
		}
		client.put(Constants.GET, this);
	}

}
