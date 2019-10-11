/**
 * @company 杭州信牛网络科技有限公司
 * @copyright Copyright (c) 2015 - 2017
 */

package com.core.utils;

/**
 * @author 陈旭
 * @version $Id: LoggerUtils, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2017年10月16日 20:45 陈旭 Exp $
 */

public class LoggerUtils {


    /**
     * 获取有分隔线的日志字符串
     *
     * @return java.lang.String
     * @author 陈旭
     * @version 1.0
     * @date 2017/10/16
     */
    public static String getSplitLogger(String text) {
        String result = "--------------------" + text + "--------------------";
        return result;
    }


    /**
     * 获取有任务id的日志字符串
     * @param taskId
     * @param msg
     * @return
     */
    public static String getTaskLogger(String taskId, String msg) {
        return "[" + taskId + "]:" + msg;
    }
}
