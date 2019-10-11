/**
 * @company 杭州信牛网络科技有限公司
 * @copyright Copyright (c) 2015 - 2018
 */
package com.core.aop.cache.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 多数据源注解
 *
 * @author 陈旭
 * @version $Id: CatchException, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2018年01月09日 16:37 Exp $
 */

@Target({ElementType.METHOD})
@Retention(RUNTIME)
@Inherited
@Documented
/**
 * @author 陈旭
 * @version $Id: RedisCache, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2019年04月18日 10:06 陈旭 Exp $
 */
public @interface RedisCache {

    /**
     * 缓存命名空间
     * redis缓存的key将会加上namespace作为前缀,为：#{namespace}_XX
     * 默认将使用当前注解注释的类名+方法名作为前缀,为：#{类名}_#{方法名}_XX
     *
     * @return
     */
    String namespace() ;

    /**
     * 构成缓存key的参数数组 下标从0开始
     * {"KEY","#0","#0.id","#0.getId()"}
     *
     * @return
     */
    String[] key() ;

    /**
     * 过期时间，单位秒
     *
     * @return
     */
    int expire();
}
