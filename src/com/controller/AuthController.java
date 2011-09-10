package com.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.flash.FlashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Implements AuthenticationFailureHandler to handle failures on auth
 * <p/>
 * Implements BeanPostProcessor as workaround  to configure UsernamePasswordAuthenticationFilter.
 * Should be fixed in spring 3.1.0.M1
 * For details consult https://jira.springsource.org/browse/SEC-1445
 *
 * @author Stanislav Kurilin
 */
@Controller
public class AuthController implements AuthenticationFailureHandler, BeanPostProcessor {
    ////TODO: [stas] move to config
    private final String loginParam = "email";
    private final String passwordParam = "password";
    @RequestMapping(value = "signin", method = RequestMethod.GET)
    public String loginPage() {
        return "auth/login";
    }

    @RequestMapping("accessDenied")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String accessDeniedPage() {
        return "auth/accessDenied";
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        final String login = request.getParameter(loginParam);
        FlashMap.getCurrent(request).put("signinError", true);
        FlashMap.getCurrent(request).put("login", login);
        response.sendRedirect("signin");
    }

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
}

