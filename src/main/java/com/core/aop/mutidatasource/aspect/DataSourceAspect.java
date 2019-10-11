/**
 * @company 杭州信牛网络科技有限公司
 * @copyright Copyright (c) 2015 - 2018
 */
package com.core.aop.mutidatasource.aspect;

import com.core.aop.mutidatasource.DynamicDataSourceHolder;
import com.core.aop.mutidatasource.annotation.DataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 多数据源切面
 *
 * @author 陈旭
 * @version $Id: DataSourceAspect, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2018年01月09日 16:43 Exp $
 */
@Component
@Aspect
@Order(1)
public class DataSourceAspect {

    @Pointcut("execution(* com.dao..*.*(..))")
    public void pointCut() {
    }

    /**
     * 拦截目标方法，获取由@DataSource指定的数据源标识，设置到线程存储中以便切换数据源
     *
     * @param point
     */
    @Before("pointCut()")
    public void intercept(JoinPoint point) {
        Class<?> target = point.getTarget().getClass();
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 默认使用目标类型的注解，如果没有则使用其实现接口的注解
        for (Class<?> clazz : target.getInterfaces()) {
            resolveDataSource(clazz, signature.getMethod());
        }
        resolveDataSource(target, signature.getMethod());
    }

    /**
     * 提取目标对象方法注解和类型注解中的数据源标识
     *
     * @param clazz
     * @param method
     */
    private void resolveDataSource(Class<?> clazz, Method method) {
        try {
            Class<?>[] types = method.getParameterTypes();
            // 默认使用类型注解
            if (clazz.isAnnotationPresent(DataSource.class)) {
                DataSource source = clazz.getAnnotation(DataSource.class);
                DynamicDataSourceHolder.setDataSource(source.value());
            }
            // 方法注解可以覆盖类型注解
            Method m = clazz.getMethod(method.getName(), types);
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource source = m.getAnnotation(DataSource.class);
                DynamicDataSourceHolder.setDataSource(source.value());
            }
        } catch (Exception e) {
            System.out.println("提取目标对象方法注解和类型注解中的数据源标识 : " + e);
        }
    }

}
