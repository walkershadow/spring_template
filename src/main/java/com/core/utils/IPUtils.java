package com.core.utils;

import cn.hutool.core.util.NetUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class IPUtils {

    /**
     * 未知IP
     */
    private final static String UNKNOWN_IP = "unknown";

    /**
     * Ip在请求Header中的位置列表
     */
    private final static String[] IP_LOCATION_ARRAY = {"x-forwarded-for",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR"};


    /**
     * 获取HttpRequest请求 ip地址
     *
     * @param request
     * @return
     */
    public static String getRequestIp(HttpServletRequest request) {
        int index = 0;
        String ip = StringUtils.EMPTY;
        while ((StringUtils.isEmpty(ip) || UNKNOWN_IP.equalsIgnoreCase(ip)) && index < IP_LOCATION_ARRAY.length) {
            ip = request.getHeader(IP_LOCATION_ARRAY[index]);
            index++;
        }
        return (StringUtils.isEmpty(ip) || UNKNOWN_IP.equalsIgnoreCase(ip)) ? request.getRemoteAddr() : ip;
    }

    /**
     * 获取本机 ip地址
     *
     * @return
     */
    public static String getLocalIp() {
        return NetUtil.getLocalhostStr();
    }

}
