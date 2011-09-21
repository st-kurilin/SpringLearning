package com.controller;

import com.domain.customer.*;
import com.domain.shop.ProductRepository;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Stanislav Kurilin
 */
@Controller
@RequestMapping("/users")
public class UserController extends AbstractController {
    private UserRepository repository;
    private AvatarRepository avatarRepository;
    private ProductRepository productRepository;
    private CurrentUserProvider currentUserProvider;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        // true passed to CustomDateEditor constructor means convert empty String to null
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * This empty constructor is needed in order for the Spring Security annotations to work as expected
     */
    public UserController() {
    }

    @Inject
    public UserController(UserRepository repository,
                          AvatarRepository avatarRepository,
                          ProductRepository productRepository,
                          CurrentUserProvider currentUserProvider) {
        this.repository = repository;
        this.avatarRepository = avatarRepository;
        this.productRepository = productRepository;
        this.currentUserProvider = currentUserProvider;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAll(Map<String, Object> model) {
        model.put("users", repository.findAll());
        model.put("currentUser", currentUserProvider.currentUser());
        return "user/users";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String createPage(Map<String, Object> model) {
        model.put("userForm", new UserForm(new User(), null));
        return "user/userNew";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showPage(@PathVariable("id") Long id, Map<String, Object> model) {
        final User user = repository.findOne(id);
        if (user == null) {
            return "redirect:/error404";
        }
        model.put("user", user);
        model.put("currentUser", currentUserProvider.currentUser());
        model.put("products", productRepository.findBySeller(user));
        model.put("existAvatar", avatarRepository.exist(id));
        return "user/userView";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    @PreAuthorize("hasPermission(#id, 'user-edit')")
    public String editPage(@PathVariable("id") Long id, Map<String, Object> model) {
        model.put("userForm", new UserForm(repository.findOne(id), null));
        model.put("currentUser", currentUserProvider.currentUser());
        return "user/usersEdit";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)

    public String create(@ModelAttribute @Valid UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/userNew";
        }
        final User user = userForm.getUser();
        final Avatar avatar = userForm.getAvatar();
        final User entity = repository.save(user);
        avatarRepository.assign(entity.getId(), avatar);
        return "redirect:/users/" + entity.getId();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasPermission(#id, 'user-edit')")
    public String edit(@PathVariable("id") Long id, @Valid UserForm userForm, BindingResult bindingResult, Map<String, Object> model) {
        if (bindingResult.hasErrors()) {
            model.put("id", id);
            return "user/usersEdit";
        }
        repository.updateEntity(id, userForm.getUser());
        avatarRepository.assign(id, userForm.getAvatar());
        return "redirect:/users/" + id;
    }


    @RequestMapping(value = "/findByEmail", method = RequestMethod.GET, params = "email")
    public String findByEmail(@RequestParam("email") EmailAddress email) {
        final User user = repository.findByEmail(email);
        if (user == null) {
            return "redirect:/users/";
        }
        return "redirect:/users/" + user.getId();
    }

    @RequestMapping(value = "/{id}/avatar", method = RequestMethod.GET)
    public ResponseEntity<byte[]> avatar(@PathVariable("id") Long id) {
        Avatar avatar = avatarRepository.load(id);
        if (avatar == null) {
            return new ResponseEntity<byte[]>(HttpStatus.NO_CONTENT);
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", avatar.getContentType());
        return new ResponseEntity<byte[]>(avatar.getContent(), responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/emails/available", method = RequestMethod.GET)
    @ResponseBody
    public String isEmailAvailable(@RequestParam("email") String email) {
        User user = repository.findByEmail(new EmailAddress(email));
        return Boolean.toString(user == null);
    }

}
