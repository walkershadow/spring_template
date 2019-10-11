package com.controller;


import com.core.aop.authentication.annotation.Authentication;
import com.core.utils.ResponseUtils;
import com.model.to.response.DemoResponse;
import com.service.biz.DemoBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author 陈旭
 * @version $Id: DemoMapper, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2018年04月18日 10:06 陈旭 Exp $
 */

@Controller
@RequestMapping("/")
public class ApiController {
    @Autowired
    private DemoBizService demoBizService;

    /**
     * 查询
     *
     * @return
     */
    @Authentication(permissionName = "TEST")
    @RequestMapping(value = "/hello")
    @ResponseBody
    public String query() {
        return "hello";
    }

    /**
     * 查询
     *
     * @return
     */
    @Authentication(permissionName = "TEST")
    @RequestMapping(value = "/page/query")
    @ResponseBody
    public DemoResponse getPage(@RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return ResponseUtils.success(demoBizService.getPage(pageNumber, pageSize));
    }
}
