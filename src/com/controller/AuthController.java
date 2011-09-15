package com.controller;

import com.domain.customer.CurrentUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

/**
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
        return "auth/login";
    }

    @RequestMapping("accessDenied")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String accessDeniedPage(Map<String, Object> model) {
        model.put("currentUser", currentUserProvider.currentUser());
        return "auth/accessDenied";
    }

}

