package com.core.aop.controlleraspect.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈旭
 * @version $Id: AbstractRedisCacheAspect, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2019年04月18日 10:06 陈旭 Exp $
 */
@Component
@Aspect
public abstract class AbstractControllerAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.ResponseBody)" +
            "&& execution(* com.controller..*.*(..)) ")
    public void pointCut() {
    }

    /**
     * 处理异常
     *
     * @param e
     */
    public abstract Object doException(Throwable e);

    /**
     * 打印返回日志
     *
     * @param request
     * @param result
     */
    public abstract void doLog(HttpServletRequest request, Object result, long usedTime);

    /**
     * 对注解的controller方法进行日志打印
     *
     * @param joinPoint
     * @return
     */
    @Around(value = "pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        //请求接收时间
        long startTime = System.currentTimeMillis();
        //请求返回结果
        Object result = null;
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        try {
            //处理请求
            result = joinPoint.proceed();
        } catch (Throwable e) {
            //处理异常
            result = doException(e);
        } finally {
            //请求结束时间
            long endTime = System.currentTimeMillis();
            //请求处理耗时
            long usedTime = endTime - startTime;
            //打印日志
            doLog(request, result, usedTime);
        }
        return result;
    }

    public Map<String, String> getParams(HttpServletRequest request) {
        Enumeration<String> keys = request.getParameterNames();
        Map<String, String> params = new HashMap<>();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            params.put(key, request.getParameter(key));
        }
        return params;
    }
}
