package com.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        ApplicationContextUtils.context = context;
    }

    /**
     * 根据beanName 获取实例
     *
     * @param beanName
     * @return
     */
    public static <T> T getBeanByName(String beanName) {
        if (beanName == null || context == null) {
            return null;
        }
        return (T) context.getBean(beanName);
    }

    /**
     * 根据类型 获取实例
     *
     * @param clazz
     * @return
     */
    public static <T> T getBeanByType(Class clazz) {
        if (clazz == null || context == null) {
            return null;
        }
        return (T) context.getBean(clazz);
    }

}
