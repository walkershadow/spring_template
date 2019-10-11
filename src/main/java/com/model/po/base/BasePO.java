/**
 * @company 杭州信牛网络科技有限公司
 * @copyright Copyright (c) 2015 - 2018
 */

package com.model.po.base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author 陈旭
 * @version $Id: BasePO, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2018年04月18日 10:03 陈旭 Exp $
 */

public class BasePO {

    /**
     * 主键id
     */
    @Getter
    @Setter
    private Integer id;

    /**
     * 创建时间
     */
    @Getter
    @Setter
    private Date createTime;

    /**
     * 更新时间
     */
    @Getter
    @Setter
    private Date updateTime;


}
