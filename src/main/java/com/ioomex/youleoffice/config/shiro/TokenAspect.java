package com.ioomex.youleoffice.config.shiro;

import cn.hutool.core.util.StrUtil;
import com.ioomex.youleoffice.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TokenAspect
 *
 * @author sutton
 * @since 2024-06-12 20:42
 */
@Component
@Aspect
@Slf4j
public class TokenAspect {

    @Autowired
    private ThreadLocalToken threadLocalToken;

    @Pointcut("within(com.ioomex.youleoffice..*) && within(*..*controller*)")
    public void controllerPointcut() {
    }

    @Around("controllerPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("TokenAspect around");
        R r = (R) joinPoint.proceed();
        String token = threadLocalToken.getToken();
        if (StrUtil.isNotBlank(token)) {
            r.put("token", token);
            threadLocalToken.clear();
        }
        return r;
    }

}