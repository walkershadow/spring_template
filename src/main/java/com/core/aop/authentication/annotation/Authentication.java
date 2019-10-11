package com.core.aop.authentication.annotation;

import java.lang.annotation.*;


/**
 * 权限验证注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface Authentication {

    /**
     * 权限名称
     */
    String permissionName();

}
