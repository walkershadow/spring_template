package com.core.aop.authentication.aspect;

import com.core.aop.authentication.annotation.Authentication;
import com.model.enums.ErrorEnum;
import com.model.exceptions.XinNiuException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author 陈旭
 * @version $Id: AbstractRedisCacheAspect, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2019年04月18日 10:06 陈旭 Exp $
 */
@Component
@Aspect
public abstract class AbstractAuthenticationAspect {

    @Pointcut("@annotation(com.core.aop.authentication.annotation.Authentication)")
    public void pointCut() {
    }

    /**
     * 验证用户权限
     *
     * @param permissionName
     * @param request
     */
    public abstract boolean checkPermission(String permissionName, HttpServletRequest request);


    /**
     * 对注解的方法进行权限验证
     *
     * @param joinPoint
     * @return
     */
    @Before(value = "pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        try {

            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();

            //方法代理对象
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            //方法代理对象_带注解信息
            Method methodWithAnnotations = joinPoint.getTarget().getClass().getDeclaredMethod(joinPoint.getSignature().getName(), method.getParameterTypes());
            //注解信息
            Authentication authentication = methodWithAnnotations.getDeclaredAnnotation(Authentication.class);
            //验证用户权限
            if (!checkPermission(authentication.permissionName(), request)) {
                throw new XinNiuException(ErrorEnum.USER_NO_AUTHORITY);
            }
        } catch (XinNiuException e) {
            throw e;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return;
    }

}
