package com.core.utils.http;


import com.core.utils.LoggerUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * HTTP基础类
 *
 * @author qinpeng
 * @version $Id: HttpClientPool, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2017年06月28日 下午2:27 qinpeng Exp $
 */
public class HttpClientPool {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final int MAX_PER_ROUTE = 20;
    private static final int MAX_TOTAL = 400;


    private PoolingHttpClientConnectionManager connectionManager;

    /**
     * 初始化连接池
     */
    protected HttpClientPool() {
        //实现SSL绕过
        Registry<ConnectionSocketFactory> reg = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", buildSSLConnectionSocketFactory())
                .build();
        connectionManager = new PoolingHttpClientConnectionManager(reg);
        connectionManager.setDefaultMaxPerRoute(MAX_PER_ROUTE);
        connectionManager.setMaxTotal(MAX_TOTAL);
        logger.info(LoggerUtils.getSplitLogger("完成HTTP连接池初始化"));
    }

    /**
     * SSL操作
     * <p>
     * 实现X509TrustManager，绕过SSL
     *
     * @return
     */
    private SSLConnectionSocketFactory buildSSLConnectionSocketFactory() {
        try {
            // 优先绕过安全证书
            return new SSLConnectionSocketFactory(createIgnoreVerifySSL());
        } catch (KeyManagementException e) {
            logger.error("ssl connection fail", e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("ssl connection fail", e);
        }
        return SSLConnectionSocketFactory.getSocketFactory();
    }

    private SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        SSLContext sc = SSLContext.getInstance("SSLv3");
        sc.init(null, new TrustManager[]{trustManager}, null);
        return sc;
    }


    protected CloseableHttpClient getClient() {
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(connectionManager);
        return httpClientBuilder.build();
    }


}
