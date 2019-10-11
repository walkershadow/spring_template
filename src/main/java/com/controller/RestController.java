package com.controller;


import com.core.aop.authentication.annotation.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author 陈旭
 * @version $Id: DemoMapper, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2018年04月18日 10:06 陈旭 Exp $
 */

@Controller
@RequestMapping("/")
public class RestController {


    /**
     * 查询
     * @return
     */
    @RequestMapping(value = "/demo",method =RequestMethod.GET)
    @ResponseBody
    @Authentication(permissionName = "TEST")
    public String query(){
        return "hello";
    }

    /**
     * 新增
     * @return
     */
    @RequestMapping(value = "/demo",method =RequestMethod.POST)
    @ResponseBody
    @Authentication(permissionName = "TEST")
    public String add(){
        return "hello";
    }

    /**
     * 新增
     * @return
     */
    @RequestMapping(value = "/demo",method =RequestMethod.PUT)
    @ResponseBody
    @Authentication(permissionName = "TEST")
    public String update(){
        return "hello";
    }

    /**
     * 修改
     * @return
     */
    @RequestMapping(value = "/demo",method =RequestMethod.DELETE)
    @ResponseBody
    @Authentication(permissionName = "TEST")
    public String delete(){
        return "hello";
    }
}
