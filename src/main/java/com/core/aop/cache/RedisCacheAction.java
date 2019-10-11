package com.core.aop.cache;

import com.core.aop.cache.aspect.AbstractRedisCacheAspect;
import com.core.utils.redis.RedisUtil;
import org.springframework.stereotype.Component;

/**
 * @author 陈旭
 * @version $Id: RedisCacheAction, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2019年04月18日 10:06 陈旭 Exp $
 */
@Component
public class RedisCacheAction extends AbstractRedisCacheAspect {

    @Override
    public void setCache(String key, String value, Integer timeOut) {
        RedisUtil.set(key,value,timeOut);
    }

    @Override
    public String getCache(String key) {
        return RedisUtil.get(key);
    }
}
