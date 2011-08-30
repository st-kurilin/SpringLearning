package com.controller;

import com.domain.EmailAddress;
import com.domain.User;
import com.domain.repo.UserRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

/**
 * @author Stanislav Kurilin
 */
@Controller
@RequestMapping("/users")
public class UserController {
    private final UserRepository repository;

    @Inject
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAll(Map<String, Object> model) {
        model.put("users", repository.findAll());
        System.out.println(repository.findAll());
        return "users";
    }

    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public String createPage(Map<String, Object> model) {
        model.put("user", new User());
        return "userNew";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showPage(@PathVariable("id") Long id, Map<String, Object> model) {
        model.put("user", repository.findOne(id));
        System.out.println(repository.findOne(id));
        return "userView";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editPage(@PathVariable("id") Long id, Map<String, Object> model) {
        model.put("user", repository.findOne(id));
        return "usersEdit";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(@Valid User user,  BindingResult bindingResult, Map<String, Object> model) {
        if (bindingResult.hasErrors()) {
            return "userNew";
        }
        final User entity = repository.save(user);
        return "redirect:/users/" + entity.getId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable("id") Long id, User user, BindingResult bindingResult, Map<String, Object> model) {
        if (bindingResult.hasErrors()) {
            return "usersEdit";
        }
        user.setId(id);
        repository.save(user);
        return "redirect:/users/" + id;
    }

    @RequestMapping(value = "/findByEmail", method = RequestMethod.GET, params = "email")
    public String findByEmail(@RequestParam("email") EmailAddress email){
        final User user = repository.findByEmail(email);
        if(user == null){
            return "redirect:/users/";
        }
        return "redirect:/users/" + user.getId();
    }


}
