package com.core.utils.http;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.util.CollectionUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    private final static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private final static String CHAR_CODE = "UTF-8";

    private final static String HTTP = "http://";

    private final static String HTTPS = "https://";


    /**
     * 调用HTTP-POST
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String doPost(String url, Map<String, String> params) throws IOException {
        return doPost(url, params, null);
    }

    /**
     * 调用HTTP-POST
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String doPost(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        //从连接池中获取请求client
        CloseableHttpClient httpClient = HttpClientUtils.getHttpClient();
        //组装HttpPost
        HttpPost httpPost = getHttpPost(url);
        //HttpPost设置header
        addHeader(httpPost, url, headers);
        //HttpPost设置请求参数
        List<BasicNameValuePair> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        //整体编码转换
        httpPost.setEntity(new UrlEncodedFormEntity(list, CHAR_CODE));
        //发起HTTP连接
        CloseableHttpResponse httpResp = httpClient.execute(httpPost);
        //字符串类型的返回结果
        String content = EntityUtils.toString(httpResp.getEntity(), CHAR_CODE);
        //关闭HTTP返回
        httpResp.close();
        return content;
    }

    /**
     * 调用HTTP-GET
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String doGet(String url) throws IOException {
        return doGet(url, null, null);
    }


    /**
     * 调用HTTP-GET
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String doGet(String url, Map<String, String> params) throws IOException {
        return doGet(url, params, null);
    }

    /**
     * 调用HTTP-GET
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String doGet(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        //从连接池中获取请求client
        CloseableHttpClient httpClient = HttpClientUtils.getHttpClient();
        //组装HttpGet URL
        url = buildUrl(url, params);
        //组装HttpGet
        HttpGet httpGet = getHttpGet(url);
        //HttpGet设置Header
        addHeader(httpGet, url, headers);
        //发起HTTP连接
        CloseableHttpResponse httpResp = httpClient.execute(httpGet);
        //字符串类型的返回结果
        String content = EntityUtils.toString(httpResp.getEntity(), CHAR_CODE);
        //关闭HTTP返回
        httpResp.close();
        return content;
    }

    /**
     * 获取HttpPost
     *
     * @param url
     * @return
     */
    private static HttpPost getHttpPost(String url) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(RequestConfig.custom()
                .setConnectionRequestTimeout(30000).build());
        return httpPost;
    }

    /**
     * 获取HttpGet
     *
     * @param url
     * @return
     */
    private static HttpGet getHttpGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(RequestConfig.custom()
                .setConnectionRequestTimeout(30000).build());
        return httpGet;
    }

    /**
     * 根据map构建url
     *
     * @param params
     * @return
     */
    private static String buildUrl(String url, Map<String, String> params) {
        StringBuilder builder = new StringBuilder(url);
        if (CollectionUtils.isEmpty(params)) {
            return url;
        }
        builder.append("?");
        List<String> keyList = Lists.newArrayList(params.keySet());
        for (String key:keyList) {
            builder.append(key).append("=").append(urlEncode(params.get(key)));
            if (keyList.indexOf(key)!=keyList.size()-1){
                builder.append("&");
            }
        }
        return builder.toString();
    }

    /**
     * URL地址encode
     * @param str
     * @return
     */
    private static String urlEncode(String str) {
        try {
            str = StringUtils.isEmpty(str) ? str : URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("url encode failed", e);
        }
        return str;
    }

    /**
     * 设置HTTP Header
     *
     * @param httpRequestBase
     * @param url
     * @param headers
     */
    private static void addHeader(HttpRequestBase httpRequestBase, String url, Map<String, String> headers) {
        httpRequestBase.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded;CharSet=gbk"));
        httpRequestBase.setHeader(new BasicHeader("UserAgent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.101 Safari/537.36"));
        httpRequestBase.setHeader(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.101 Safari/537.36"));
        httpRequestBase.setHeader(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
        httpRequestBase.setHeader(new BasicHeader("Host", getHost(url)));
        httpRequestBase.setHeader(new BasicHeader("Origin", "https://" + getHost(url)));
        httpRequestBase.setHeader(new BasicHeader("Accept-Encoding", "gzip, deflate"));
        httpRequestBase.setHeader(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8"));
        httpRequestBase.setHeader(new BasicHeader("Connection", "keep-alive"));
        if (null != headers) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpRequestBase.setHeader(new BasicHeader(entry.getKey(), entry.getValue()));
            }
        }
    }

    /**
     * 获取URL的Host
     *
     * @param url
     * @return
     */
    private static String getHost(String url) {
        if (StringUtils.isEmpty(url)) {
            return StringUtils.EMPTY;
        }
        if (!url.startsWith(HTTP) && !url.startsWith(HTTPS)) {
            return StringUtils.EMPTY;
        }
        url = url.substring(url.indexOf("://") + 3, url.length());
        return url.contains("/") ? url.substring(0, url.indexOf("/")) : url;
    }

    public static void main(String[] args) throws IOException {
//        doPost("http://www.baidu.com", Maps.newHashMap());
        System.out.println(buildUrl("http://www.baidu.com", ImmutableMap.of("a", "1")));
    }

}
