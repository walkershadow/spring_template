package com.model.to.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class DemoResponse {

    /**
     * 状态
     */
    @Getter
    @Setter
    private String status;

    /**
     * 数据
     */
    @Getter
    @Setter
    private Object data;

    /**
     * 错误描述
     */
    @Getter
    @Setter
    private String msg;

}
