package com.controller;

import com.domain.customer.CurrentUserProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.flash.FlashMap;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author Stanislav Kurilin
 */
@Controller
public class AuthController implements AuthenticationFailureHandler {
    @Value("#{security.parameterLogin}")
    private String loginParam;
    @Inject
    private CurrentUserProvider currentUserProvider;

    @RequestMapping(value = "signin", method = RequestMethod.GET)
    public String loginPage() {
        return "auth/login";
    }

    @RequestMapping("accessDenied")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String accessDeniedPage(Map<String, Object> model) {
        model.put("currentUser", currentUserProvider.currentUser());
        return "auth/accessDenied";
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        final String login = request.getParameter(loginParam);
        FlashMap.getCurrent(request).put("signinError", true);
        FlashMap.getCurrent(request).put(loginParam, login);
        response.sendRedirect("signin");
    }
}

