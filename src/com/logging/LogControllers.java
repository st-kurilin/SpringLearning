package com.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Andrey Loboda
 * @date : 05.09.11
 */
public class LogControllers extends HandlerInterceptorAdapter {
    private static Logger log = LoggerFactory.getLogger(LogControllers.class);

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        log.debug("request : " + request.getRequestURI());
        return super.preHandle(request, response, handler);
    }
}
