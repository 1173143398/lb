package com.thread;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.HttpClientConnectionManager;

public class IdleConnectionMonitorThread extends Thread {
    
	private static Log log = LogFactory.getLog(IdleConnectionMonitorThread.class);
    private HttpClientConnectionManager connMgr;
    private volatile boolean shutdown;
    
    public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr){
    	this.connMgr = connMgr;
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                synchronized (this) {
                    Thread.sleep(5000);
                    log.info("清理无效http链接");
                    connMgr.closeExpiredConnections();
                    connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
                }
            }
        } catch (InterruptedException ex) {
            // terminate
        }
    }
    
    public void shutdown() {
        shutdown = true;
    }
    
}