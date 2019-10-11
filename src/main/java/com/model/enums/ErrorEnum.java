/**
 * @company 杭州信牛网络科技有限公司
 * @copyright Copyright (c) 2015 - 2018
 */
package com.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 尹佳鹏(Sec)
 * @version $Id: ErrorEnum, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2018年01月10日 17:33 尹佳鹏(Sec) Exp $
 */
@AllArgsConstructor
public enum ErrorEnum {

    /*
     *参数格式不正确
     */
    ILLEGAL_FORMAT("参数格式不正确"),

    /*
     *当前用户无权限
     */
    USER_NO_AUTHORITY("当前用户无权限");

    @Getter
    private String message;

}
