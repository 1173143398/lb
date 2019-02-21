package com.http;

import org.apache.http.impl.client.CloseableHttpClient;

public interface RequestExecutor<R, D> {

	R execute(CloseableHttpClient httpClient,String url,D data) throws Exception;
}
