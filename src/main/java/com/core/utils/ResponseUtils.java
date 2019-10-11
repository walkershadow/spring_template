package com.core.utils;

import com.model.enums.StatusEnum;
import com.model.to.response.DemoResponse;
import org.apache.commons.lang3.StringUtils;

public class ResponseUtils {

    /**
     * 正常返回
     *
     * @return
     */
    public static DemoResponse success(Object data) {
        return new DemoResponse(StatusEnum.SUCCESS.getMessage(), data, StringUtils.EMPTY);
    }

    /**
     * 异常返回
     *
     * @return
     */
    public static DemoResponse error(String msg) {
        return new DemoResponse(StatusEnum.FAILED.getMessage(), null, msg);
    }


}
