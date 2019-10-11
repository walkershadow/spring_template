package com.core.utils.redis;

import com.model.config.RedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.util.Pool;

import java.util.Collections;

/**
 * 兼容哨兵和集群redisUtils  在配置文件中需要配置 redis.sential.active 哨兵为true  集群为false
 * 若出现redis 异常则推送报警  KafkaPushUtil版本为1.3
 *
 * @author 吴孔珍(吴斯文)
 * @version $Id: RedisUtil, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2018年11月12日 16:14
 */
public class RedisUtil {
    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    private static Pool<Jedis> pool;

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;

    static {
        if (RedisConfig.SENTINEL_ACTIVE) {
            pool = JedisSentialPoolManager.getPool(RedisConfig.DATABASE);
        } else {
            pool = RedisPoolManager.getPool(RedisConfig.DATABASE);
        }
    }

    /**
     * 设置数据
     *
     * @param key
     * @param value
     */
    public static void set(String key, String value) {
        set(key, value, 0);
    }

    /**
     * 设置数据
     *
     * @param key
     * @param value
     * @param timeOut
     */
    public static void set(String key, String value, Integer timeOut) {
        try (Jedis jedis = pool.getResource()) {
            jedis.set(key, value);
            if (timeOut > 0) {
                jedis.expire(key, timeOut);
            }
        }
    }

    /**
     * 读取数据
     *
     * @param key
     */
    public static String get(String key) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.get(key);
        }
    }

    /**
     * 删除数据
     *
     * @param key
     */
    public static void del(String key) {
        try (Jedis jedis = pool.getResource()) {
            jedis.del(key);
        }
    }

    /**
     * 右推入
     *
     * @param key
     */
    public static void rpush(String key, String... string) {
        try (Jedis jedis = pool.getResource()) {
            jedis.rpush(key);
        }
    }

    /**
     * 右推出
     *
     * @param key
     */
    public static String rpop(String key) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.rpop(key);
        }
    }

    /**
     * 左推入
     *
     * @param key
     */
    public static void lpush(String key, String... string) {
        try (Jedis jedis = pool.getResource()) {
            jedis.lpush(key);
        }
    }

    /**
     * 左推出
     *
     * @param key
     */
    public static String leftPop(String key) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.lpop(key);
        }
    }


    /**
     * 判断redis中是否存在key
     * @param key
     * @return
     */
    public static Boolean exists(String key) {
        try (Jedis jedis = pool.getResource()) {
            return jedis.exists(key);
        }
    }

    /**
     * 尝试获取分布式锁
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public static boolean getLock(String key, String value, Integer expireTime) {
        try (Jedis jedis = pool.getResource()) {
            String result = jedis.set(key, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
            return LOCK_SUCCESS.equals(result);
        }
    }

    /**
     * 释放分布式锁
     *
     * @param key 锁
     * @param key 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseLock(String key, String value) {
        try (Jedis jedis = pool.getResource()) {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = jedis.eval(script, Collections.singletonList(key), Collections.singletonList(value));
            return RELEASE_SUCCESS.equals(result);
        }
    }


}
