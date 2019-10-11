/**
 * @company 杭州信牛网络科技有限公司
 * @copyright Copyright (c) 2015 - 2018
 */
package com.core.aop.mutidatasource;

/**
 * 用途描述
 *
 * @author 刘全权
 * @version $Id: DynamicDataSourceHolder, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2018年01月09日 15:36 Exp $
 */

public class DynamicDataSourceHolder {
    /**
     * 注意：数据源标识保存在线程变量中，避免多线程操作数据源时互相干扰
     */
    private static final ThreadLocal<String> THREAD_DATA_SOURCE = new ThreadLocal<>();

    public static String getDataSource() {
        return THREAD_DATA_SOURCE.get();
    }

    public static void setDataSource(String dataSource) {
        THREAD_DATA_SOURCE.set(dataSource);
    }

    public static void clearDataSource() {
        THREAD_DATA_SOURCE.remove();
    }
}
