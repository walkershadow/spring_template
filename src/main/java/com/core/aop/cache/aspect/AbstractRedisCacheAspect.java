package com.core.aop.cache.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.core.aop.cache.annotation.RedisCache;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.lang.reflect.*;
import java.util.*;

/**
 * @author 陈旭
 * @version $Id: AbstractRedisCacheAspect, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2019年04月18日 10:06 陈旭 Exp $
 */
@Component
@Aspect
public abstract class AbstractRedisCacheAspect {

    @Pointcut("@annotation(com.core.aop.cache.annotation.RedisCache)")
    public void pointCut() {
    }

    /**
     * 设置redis缓存
     *
     * @param key
     * @param value
     * @param timeOut
     */
    public abstract void setCache(String key, String value, Integer timeOut);

    /**
     * 获取redis缓存
     *
     * @param key
     * @return
     */
    public abstract String getCache(String key);

    /**
     * 对注解的方法结果进行缓存处理
     *
     * @param joinPoint
     * @return
     */
    @Around(value = "pointCut()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        //返回结果
        Object result = null;
        try {
            //方法代理对象
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            //方法代理对象_带注解信息
            Method methodWithAnnotations = joinPoint.getTarget().getClass().getDeclaredMethod(joinPoint.getSignature().getName(), method.getParameterTypes());
            //注解信息
            RedisCache redisCache = methodWithAnnotations.getDeclaredAnnotation(RedisCache.class);

            //获取缓存KEY
            String cacheKey = getCacheKey(redisCache, joinPoint, method);
            //通过缓存KEY从redis中获取缓存结果
            String cacheValue = getCache(cacheKey);
            if (StringUtils.isNotEmpty(cacheValue)) {
                //Redis存在对应缓存
                //Redis中获得结果,不再调用具体方法
                if (method.getReturnType().equals(List.class)) {
                    Type type = method.getGenericReturnType();
                    Class entityClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
                    result = JSONArray.parseArray(cacheValue, entityClass);
                } else {
                    result = JSONObject.parseObject(cacheValue, method.getReturnType());
                }
            } else {
                //Redis不存在对应缓存
                //执行具体方法获得返回结果
                result = joinPoint.proceed();
                setCache(cacheKey, JSON.toJSONString(result), redisCache.expire());
            }
        } catch (Throwable e) {
            throw e;
        }
        return result;
    }


    /**
     * 获取缓存KEY
     *
     * @param redisCache
     * @param joinPoint
     * @param method
     * @return
     */
    private String getCacheKey(RedisCache redisCache, ProceedingJoinPoint joinPoint, Method method) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        //组装缓存key
        StringBuilder stringBuilder = new StringBuilder();
        if (StringUtils.isEmpty(redisCache.namespace())) {
            //没有设置命名空间则使用全限定类名_方法名作为前缀
            stringBuilder.append(joinPoint.getTarget().getClass().getName())
                    .append("_")
                    .append(method.getName());
        } else {
            //设置了命名空间则使用其作为前缀
            stringBuilder.append(redisCache.namespace());
        }

        //方法参数
        Object[] args = joinPoint.getArgs();
        //已设置组成key的参数列表,则使用设置的参数组装key
        for (String paramKey : redisCache.key()) {
            stringBuilder.append("_");
            //组合key的静态字符串
            if (!paramKey.contains("#")) {
                stringBuilder.append(paramKey);
                continue;
            }
            if (paramKey.contains(".")) {
                //包含.的字符串需要调用参数对象的属性或者方法
                int paramIndex = Integer.valueOf(paramKey.substring(paramKey.indexOf("#") + 1, paramKey.indexOf(".")));
                Class clazz = args[paramIndex].getClass();
                //通过是否含有()判断是属性还是方法
                if (paramKey.contains("()")) {
                    //方法名称
                    String methodName = paramKey.substring(paramKey.indexOf(".") + 1).replaceAll("\\(\\)", "");
                    stringBuilder.append(clazz.getMethod(methodName).invoke(args[paramIndex]).toString());
                } else {
                    //当前对象的类的所有属性
                    List<Field> fields = Lists.newArrayList(clazz.getDeclaredFields());
                    //当前类的父类
                    Class superClazz = clazz.getSuperclass();
                    //获取父类的所有属性，直到不存在父类
                    while (superClazz != null) {
                        fields.addAll(Lists.newArrayList(superClazz.getDeclaredFields()));
                        superClazz = superClazz.getSuperclass();
                    }
                    for (Field field : fields) {
                        //属性名称
                        String fieldName = paramKey.substring(paramKey.indexOf(".") + 1);
                        if (field.getName().equals(fieldName)) {
                            field.setAccessible(true);
                            //属性值
                            Object value = field.get(args[paramIndex]);
                            //属性值不为空时 才进行key值拼接
                            if (null != value) {
                                stringBuilder.append(value.toString());
                            }
                        }
                    }
                }
            } else {
                //不包含.的字符串没有二级调用，直接将对应参数组合为key
                int paramIndex = Integer.valueOf(paramKey.substring(paramKey.indexOf("#") + 1));
                stringBuilder.append(args[paramIndex].toString());
            }
        }
        return stringBuilder.toString();
    }

}
