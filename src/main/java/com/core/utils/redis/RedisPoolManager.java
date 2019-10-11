package com.core.utils.redis;

import com.model.config.RedisConfig;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;

/**
 * 类 名 称   ：
 * 业务描述   ：
 * 作 者 名   ： @Author 简单
 * 开发日期   ： 2018/06/21
 * Created   ： Intellij IDEA
 * ***********************************
 * 修改日期   ：
 * 修 改 者   ：
 * 修改内容   ：
 */
public class RedisPoolManager {
    private static HashMap<Integer, JedisPool> poolMap = new HashMap<>();

    /**
     * 初始 pool
     *
     * @param database
     * @return
     */
    private synchronized static JedisPool initPool(int database) {
        JedisPool pool = poolMap.get(database);
        if (pool != null) {
            return pool;
        }
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(RedisConfig.MAX_TOTAL);
        config.setMaxIdle(RedisConfig.MAX_IDLE);
        config.setMinIdle(RedisConfig.MIN_IDLE);
        config.setTestOnBorrow(RedisConfig.TEST_ON_BORROW);
        config.setTestOnReturn(RedisConfig.TEST_ON_RETURN);
        pool = new JedisPool(config, RedisConfig.HOST,
                RedisConfig.PORT,
                RedisConfig.TIMEOUT,
                RedisConfig.PASSWORD, database);
        poolMap.put(database, pool);
        return pool;
    }

    /**
     * 得到 JedisPool
     *
     * @param database
     * @return
     */
    public static JedisPool getPool(int database) {
        return poolMap.get(database) != null ? poolMap.get(database) : RedisPoolManager.initPool(database);
    }
}
