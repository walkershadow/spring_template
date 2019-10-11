/**
 * @company 杭州信牛网络科技有限公司
 * @copyright Copyright (c) 2015 - 2018
 */
package com.core.aop.mutidatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 用途描述
 *
 * @author 刘全权
 * @version $Id: DynamicDataSource, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2018年01月09日 15:35 Exp $
 */

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSource();
    }
}
