package com.idempotent.intercepter;

import com.idempotent.annotation.Idempotent;
import com.idempotent.service.TokenService;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author hex1n
 * @Time 2020/5/4 17:38
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Idempotent annotation = method.getAnnotation(Idempotent.class);
        if (annotation != null) {
            check(request);
        }
        return true;
    }

    private void check(HttpServletRequest request) {
        tokenService.checkToken(request);
    }
}
