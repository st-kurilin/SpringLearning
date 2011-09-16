package com.security;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Component;

/**
 * Implements BeanPostProcessor as workaround  to configure UsernamePasswordAuthenticationFilter.
 * Should be fixed in spring 3.1.0.M1
 * For details consult https://jira.springsource.org/browse/SEC-1445
 *
 * @author Stanislav Kurilin
 */
@Component
public class AuthFormCustomizer implements BeanPostProcessor {
    @Value("#{security.parameterLogin}")
    private String loginParam;
    @Value("#{security.parameterPassword}")
    private String passwordParam;
    @Value("#{security.parameterRememberMe}")
    private String rememberMeParam;

    @Override
    //Configure UsernamePasswordAuthenticationFilter
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof UsernamePasswordAuthenticationFilter) {
            final UsernamePasswordAuthenticationFilter filter = (UsernamePasswordAuthenticationFilter) bean;
            filter.setUsernameParameter(loginParam);
            filter.setPasswordParameter(passwordParam);
        }
        if (bean instanceof AbstractRememberMeServices) {
            final AbstractRememberMeServices service = (AbstractRememberMeServices) bean;
            service.setParameter(rememberMeParam);
        }
        return bean;
    }

    @Override
    //Do nothing
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
