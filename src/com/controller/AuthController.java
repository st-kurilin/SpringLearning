package com.controller;

import com.domain.customer.CurrentUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

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
public class AuthController {
    private final CurrentUserProvider currentUserProvider;

    @Autowired
    public AuthController(CurrentUserProvider currentUserProvider) {
        this.currentUserProvider = currentUserProvider;
    }

    @RequestMapping(value = "signin", method = RequestMethod.GET)
    public String loginPage() {
        if (currentUserProvider.currentUser() != null) {
            return "redirect:/users/";
        }
        return "auth/login";
    }

    @RequestMapping("accessDenied")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String accessDeniedPage() {
        return "auth/accessDenied";
    }


}

