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

public class RedisConfig {
    private static Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    /**
     * redis地址-普通模式
     */
    public static String HOST;

    /**
     * redis地址-哨兵模式
     */
    public static String SENTINEL_HOST_1;
    public static String SENTINEL_HOST_2;
    public static String SENTINEL_HOST_3;

    /**
     * redis哨兵主节点名称-哨兵模式
     */
    public static String SENTINEL_MASTER_NAME;

    /**
     * redis端口号
     */
    public static Integer PORT;

    /**
     * redis密码
     */
    public static String PASSWORD;
    /**
     * redis超时时间 ms
     */
    public static Integer TIMEOUT;
    /**
     * redis使用DB编号
     */
    public static Integer DATABASE;
    /**
     * redis对象池最大活跃数
     */
    public static Integer MAX_ACTIVE;
    /**
     * redis对象池最大数
     */
    public static Integer MAX_TOTAL;
    /**
     * redis对象池最小空闲数
     */
    public static Integer MAX_IDLE;
    /**
     * redis对象池最小空闲数
     */
    public static Integer MIN_IDLE;
    /**
     * redis对象池最大等待时间
     */
    public static Integer MAX_WAIT;
    /**
     * 当调用borrow Object方法时，是否进行有效性检查
     */
    public static Boolean TEST_ON_BORROW;
    /**
     * 当调用return Object方法时，是否进行有效性检查
     */
    public static Boolean TEST_ON_RETURN;
    /**
     * 项目使用redis是否为哨兵模式
     */
    public static Boolean SENTINEL_ACTIVE;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("redis");
        if (bundle == null) {
            throw new IllegalArgumentException(
                    "[redis.properties] is not found!");
        }
        HOST = bundle.getString("redis.ip");
        PASSWORD = bundle.getString("redis.password");
        PORT = Integer.valueOf(bundle.getString("redis.port"));
        TIMEOUT = Integer.valueOf(bundle.getString("redis.timeout"));
        DATABASE = Integer.valueOf(bundle.getString("redis.data.database"));
        MAX_TOTAL = Integer.valueOf(bundle.getString("redis.maxTotal"));
        MAX_ACTIVE = Integer.valueOf(bundle.getString("redis.maxActive"));
        MAX_IDLE = Integer.valueOf(bundle.getString("redis.maxIdle"));
        MIN_IDLE = Integer.valueOf(bundle.getString("redis.minIdle"));
        MAX_WAIT = Integer.valueOf(bundle.getString("redis.maxWait"));
        TEST_ON_BORROW = Boolean.valueOf(bundle.getString("redis.testOnBorrow"));
        TEST_ON_RETURN = Boolean.valueOf(bundle.getString("redis.testOnReturn"));
        SENTINEL_ACTIVE = Boolean.valueOf(bundle.getString("redis.sential.active"));
        SENTINEL_HOST_1 = bundle.getString("redis.sentinel1");
        SENTINEL_HOST_2 = bundle.getString("redis.sentinel2");
        SENTINEL_HOST_3 = bundle.getString("redis.sentinel3");
        SENTINEL_MASTER_NAME = bundle.getString("redis.master.name");
        if (null == SENTINEL_ACTIVE) {
            throw new IllegalArgumentException(
                    "[redis.properties redis.sential.active] is not found!");
        }
    }


}
