package com.controller.error;

import com.domain.customer.CurrentUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author: Andrey Loboda
 * @date : 15.09.11
 */
@Controller
public class ErrorHandlingController {

    private final CurrentUserProvider currentUserProvider;

    @Autowired
    public ErrorHandlingController(CurrentUserProvider currentUserProvider) {
        this.currentUserProvider = currentUserProvider;
    }
    @RequestMapping(value = "error404", method = RequestMethod.GET)
    public String pageNotFound(Map<String, Object> model) {
        model.put("currentUser", currentUserProvider.currentUser());
        return "errors/error404";
    }
}
