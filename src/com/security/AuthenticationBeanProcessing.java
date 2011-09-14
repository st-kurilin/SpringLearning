package com.security;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.flash.FlashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Andrey Loboda
 * @date : 14.09.11
 */
@Component
public class AuthenticationBeanProcessing implements BeanPostProcessor, AuthenticationFailureHandler {
    ////TODO: [stas] move to config
    private final String loginParam = "email";
    private final String passwordParam = "password";

    @Override
    //Configure UsernamePasswordAuthenticationFilter
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof UsernamePasswordAuthenticationFilter) {
            final UsernamePasswordAuthenticationFilter filter = (UsernamePasswordAuthenticationFilter) bean;
            filter.setUsernameParameter(loginParam);
            filter.setPasswordParameter(passwordParam);
        }
        return bean;
    }

    @Override
    //Do nothing
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        final String login = request.getParameter(loginParam);
        FlashMap.getCurrent(request).put("signinError", true);
        FlashMap.getCurrent(request).put("login", login);
        response.sendRedirect("signin");
    }
}
