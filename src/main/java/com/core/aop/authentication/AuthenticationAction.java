package com.core.aop.authentication;

import cn.hutool.core.util.NetUtil;
import com.core.aop.authentication.aspect.AbstractAuthenticationAspect;
import com.core.utils.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 陈旭
 * @version $Id: RedisCacheAction, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2019年04月18日 10:06 陈旭 Exp $
 */
@Component
public class AuthenticationAction extends AbstractAuthenticationAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean checkPermission(String permissionName, HttpServletRequest request) {
        System.out.println("权限验证:" + IPUtils.getRequestIp(request));
        return true;
    }
}
