package com.core.utils.redis;

import com.model.config.RedisConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class JedisSentialPoolManager {

    private static HashMap<Integer, JedisSentinelPool> poolMap = new HashMap<>();

    /**
     * 初始 pool
     *
     * @param database
     * @return
     */
    private synchronized static JedisSentinelPool initPool(int database) {
        JedisSentinelPool pool = poolMap.get(database);
        if (pool != null) {
            return pool;
        }

        Set<String> sentinels = new HashSet<>();
        sentinels.add(new HostAndPort(RedisConfig.SENTINEL_HOST_1, RedisConfig.PORT).toString());
        sentinels.add(new HostAndPort(RedisConfig.SENTINEL_HOST_2, RedisConfig.PORT).toString());
        sentinels.add(new HostAndPort(RedisConfig.SENTINEL_HOST_3, RedisConfig.PORT).toString());
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(RedisConfig.MAX_TOTAL);
        poolConfig.setMaxIdle(RedisConfig.MAX_IDLE);
        poolConfig.setMinIdle(RedisConfig.MIN_IDLE);
        poolConfig.setTestOnBorrow(RedisConfig.TEST_ON_BORROW);
        poolConfig.setTestOnReturn(RedisConfig.TEST_ON_RETURN);
        pool = new JedisSentinelPool(RedisConfig.SENTINEL_MASTER_NAME,
                sentinels, poolConfig,
                RedisConfig.TIMEOUT,
                RedisConfig.PASSWORD,
                database);
        poolMap.put(database, pool);
        return pool;
    }

    public static JedisSentinelPool getPool(int database) {
        return poolMap.get(database)!=null?poolMap.get(database):JedisSentialPoolManager.initPool(database);
    }
}
