package com.controller;

import com.domain.customer.EmailAddress;
import com.domain.customer.Sex;
import com.domain.customer.User;
import com.domain.customer.UserRepository;
import com.domain.shop.Product;
import com.domain.shop.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author Stanislav Kurilin
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;
    private final UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final String DEFAULT_PAGE_SIZE = "10";
    private final String DEFAULT_SORT_BY = "title";
    private final String DEFAULT_DIRECTION = "ASC";

    @Inject
    public ProductController(ProductRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                          @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                          @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY) String sortBy,
                          @RequestParam(value = "direction", defaultValue = DEFAULT_DIRECTION) String direction,
                          Map<String, Object> model) {
        final Sort.Direction dir = Sort.Direction.fromString(direction);
        model.put("page", repository.findAll(new PageRequest(page, size, new Sort(dir, sortBy))));
        return "products";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String createPage(Map<String, Object> model) {
        model.put("product", new Product());
        return "productNew";
    }

    @RequestMapping(value = "/{title}", method = RequestMethod.GET)
    public String showPage(@PathVariable("title") String titleOfProduct, Map<String, Object> model) {
        model.put("product", repository.findByTitle(titleOfProduct));
        return "productView";
    }

    /*
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editPage(@PathVariable("id") Long id, Map<String, Object> model) {
        model.put("userForm", new UserForm(repository.findOne(id), null));
        model.put("id", id);
        return "usersEdit";
    }
    */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(@ModelAttribute @Valid Product product, BindingResult bindingResult) {
        product.setUser(userRepository.findOne(1l));// get user from id
        if (bindingResult.hasErrors()) {
            return "productNew";
        }
        final Product entity = repository.save(product);
        return "redirect:/products/" + entity.getTitle();
    }

    /*
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable("id") Long id, @Valid UserForm userForm, BindingResult bindingResult, Map<String, Object> model) {
        if (bindingResult.hasErrors()) {
            model.put("id", id);
            return "usersEdit";
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

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", avatar.getContentType());

        return new ResponseEntity<byte[]>(avatar.getContent(), responseHeaders, HttpStatus.OK);
    }
    */
    @RequestMapping(value = "/generateContent", method = RequestMethod.GET)
    public String generateContent(Map<String, Object> model) {
        User user = new User();
        user.setName("Vasyaa");
        user.setEmail(new EmailAddress("alsd@asd.as"));
        user.setBirthday(new Date());
        user.setSex(Sex.MALE);

        User userId = userRepository.save(user);
        Product product;
        for (int i = 0; i < 50; i++) {
            product = new Product();
            product.setTitle("generated".intern());
            product.setPrice(new BigDecimal(50 - i));
            product.setUser(userId);
            repository.save(product);
        }
        model.put("page", repository.findAll());
        return "redirect:/products/";
    }

    @RequestMapping(value = "/isTitleAvailable", method = RequestMethod.GET)
    @ResponseBody
    public String isTitleAvailable(@RequestParam String title) {
        Product product = repository.findByTitle(title);
        if (product == null) {
            return Boolean.TRUE.toString();
        } else {
            return Boolean.FALSE.toString();
        }
    }
}
