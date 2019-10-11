/**
 * @company 杭州信牛网络科技有限公司
 * @copyright Copyright (c) 2015 - 2018
 */
package com.model.config;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

/**
 * oss配置
 * @author 陈旭
 * @version $Id: AccessConfig, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2018年02月06日 12:21 尹佳鹏(Sec) Exp $
 */

public class OssConfig {
    private static Logger logger = LoggerFactory.getLogger(OssConfig.class);

    public static String ENDPOINT;
    public static String DOMAIN;
    public static String BUCKET_NAME;
    public static String FILE_NAME;
    public static String PATH;
    public static String KEY_ID;
    public static String KEY_SECRET;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("config/oss");
        ENDPOINT = bundle.getString("oss.endpoint");
        DOMAIN = bundle.getString("oss.domain");
        BUCKET_NAME = bundle.getString("oss.bucket.name");
        FILE_NAME = bundle.getString("oss.file.name");
        PATH = bundle.getString("oss.path");
        KEY_ID = bundle.getString("oss.access.key.id");
        KEY_SECRET = bundle.getString("oss.access.key.secret");
    }


}
