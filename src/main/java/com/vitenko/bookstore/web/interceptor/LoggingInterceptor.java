package com.vitenko.bookstore.web.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class LoggingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        StringBuilder stringBuilder = new StringBuilder("Interceptor | PRE | URI: ");
        stringBuilder.append(request.getRequestURI()).append(" | method: ").append(request.getMethod());
        log.info(stringBuilder.toString());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        StringBuilder stringBuilder = new StringBuilder("Interceptor | POST | URI: ");
        stringBuilder.append(request.getRequestURI()).append(" | method: ").append(request.getMethod());
        log.info(stringBuilder.toString());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        StringBuilder stringBuilder = new StringBuilder("Interceptor | AFTER | URI: ");
        stringBuilder.append(request.getRequestURI()).append(" | method: ").append(request.getMethod());
        if (ex != null) {
            stringBuilder.append(" | exception: ").append(ex.getMessage());
        }
        log.info(stringBuilder.toString());
    }
}
