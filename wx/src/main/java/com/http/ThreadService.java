package com.http;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.InitializingBean;

public class ThreadService implements InitializingBean{

	private static Log log = LogFactory.getLog(ThreadService.class);
	private PoolingHttpClientConnectionManager connManager;
	
	private IdleConnectionMonitorThread t;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("启动清理http pool无效链接线程");
		t = new IdleConnectionMonitorThread(connManager);
		t.start();
	}

	public void close() {
		if(t != null) {
			t.shutdown();
		}
	}

	public PoolingHttpClientConnectionManager getConnManager() {
		return connManager;
	}

	public void setConnManager(PoolingHttpClientConnectionManager connManager) {
		this.connManager = connManager;
	}
	
	
	
}
