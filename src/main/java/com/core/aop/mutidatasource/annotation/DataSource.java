/**
 * @company 杭州信牛网络科技有限公司
 * @copyright Copyright (c) 2015 - 2018
 */
package com.core.aop.mutidatasource.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 多数据源注解
 *
 * @author 陈旭
 * @version $Id: CatchException, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2018年01月09日 16:37 Exp $
 */

@Target({ElementType.TYPE, ElementType.METHOD })
@Retention(RUNTIME)
public @interface DataSource {
     String value();
}
