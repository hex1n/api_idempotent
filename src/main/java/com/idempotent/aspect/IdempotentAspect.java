package com.idempotent.aspect;

import com.idempotent.annotation.Idempotent;
import com.idempotent.common.R;
import com.idempotent.utils.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author hex1n
 * @Time 2020/5/5 11:46
 */
@Aspect
@Component
public class IdempotentAspect {

    @Resource
    private HttpServletRequest request;

    @Resource
    private RedisUtil redisUtil;

    @Around("@annotation(idempotent)")
    public Object around(ProceedingJoinPoint joinPoint, Idempotent idempotent) throws Throwable {
        System.out.println("检查token是否有效,{}" + joinPoint.getSignature().toString());
        String token = request.getHeader("token");
        String value = idempotent.value();
        Boolean del = redisUtil.del(token);
        if (!del) {
            return R.error().msg("请勿重复提交");
        }
        Object proceed = joinPoint.proceed();
        return proceed;
    }
}
