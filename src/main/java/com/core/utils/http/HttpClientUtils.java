package com.core.utils.http;

import org.apache.http.impl.client.CloseableHttpClient;

/**
 * 用途描述
 *
 * @author qinpeng
 * @version $Id: HttpClientDownloader, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2017年06月29日 上午11:42 qinpeng Exp $
 */
public class HttpClientUtils {

    private final static HttpClientPool HTTP_CLIENT_POOL;

    static {
        HTTP_CLIENT_POOL = new HttpClientPool();
    }

    protected static CloseableHttpClient getHttpClient() {
        CloseableHttpClient httpClient = null;
        synchronized (HTTP_CLIENT_POOL) {
            httpClient = HTTP_CLIENT_POOL.getClient();
        }
        return httpClient;
    }

}
