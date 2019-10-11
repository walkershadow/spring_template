package com.core.aop.controlleraspect;

import com.alibaba.fastjson.JSONObject;
import com.core.aop.controlleraspect.aspect.AbstractControllerAspect;
import com.core.utils.IPUtils;
import com.core.utils.ResponseUtils;
import com.model.exceptions.XinNiuException;
import com.model.to.response.DemoResponse;
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
public class ControllerAction extends AbstractControllerAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void doLog(HttpServletRequest request, Object result, long usedTime) {
        logger.info("\n|请求接口:" + request.getRequestURL().toString()
                + "\n|请求Client-IP:" + IPUtils.getRequestIp(request)
                + "\n|请求参数:" + JSONObject.toJSONString(getParams(request))
                + "\n|返回结果:" + JSONObject.toJSONString(result)
                + "\n|请求耗时:" + usedTime + "ms");
    }


    @Override
    public Object doException(Throwable e) {
        if(e instanceof XinNiuException){
            return xinniuExceptionHandler((XinNiuException)e);
        }else {
            return exceptionHandler((Exception)e);
        }
    }

    /**
     * 处理XinNiuException异常
     * @param e
     * @return
     */
    private DemoResponse xinniuExceptionHandler(XinNiuException e) {
        if (null == e.getException()) {
            logger.error(e.getErrorEnum().getMessage());
        } else {
            logger.error(e.getErrorEnum().getMessage(), e.getException());
        }
        return ResponseUtils.error(e.getErrorEnum().getMessage());
    }

    /**
     * 处理Exception异常
     * @param e
     * @return
     */
    private DemoResponse exceptionHandler(Exception e) {
        logger.error("发生未知异常", e);
        return ResponseUtils.error("发生未知异常");
    }
}
