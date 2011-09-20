package com.controller;

import com.domain.customer.CurrentUserProvider;
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
import java.util.Map;

/**
 * @author Stanislav Kurilin
 */
@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository repository;
    private final UserRepository userRepository;
    private final CurrentUserProvider currentUserProvider;

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Inject
    public ProductController(ProductRepository repository, UserRepository userRepository, CurrentUserProvider currentUserProvider) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.currentUserProvider = currentUserProvider;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                          @RequestParam(value = "pageSize", defaultValue = "4") Integer size,
                          @RequestParam(value = "sortBy", defaultValue = "title") String orderBy,
                          @RequestParam(value = "direction", defaultValue = "ASC") Sort.Direction direction,
                          Map<String, Object> model) {
        final Sort.Order order = new Sort.Order(direction, orderBy);
        final PageRequest pageRequest = new PageRequest(page, size, new Sort(order));
        model.put("page", repository.findAll(pageRequest));
        model.put("currentUser", currentUserProvider.currentUser());
        return "shop/products";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String createPage(Map<String, Object> model) {
        model.put("product", new Product());
        model.put("currentUser", currentUserProvider.currentUser());
        return "shop/productNew";
    }

    @RequestMapping(value = "/{title}", method = RequestMethod.GET)
    public String showPage(@PathVariable("title") String titleOfProduct, Map<String, Object> model) {
        final Product product = repository.findByTitle(titleOfProduct);
        if (product == null) {
            return "redirect:/error404";
        }
        model.put("product", product);
        model.put("currentUser", currentUserProvider.currentUser());
        return "shop/productView";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(@ModelAttribute @Valid Product product, BindingResult bindingResult) {
        product.setSeller(currentUserProvider.currentUser());
        if (bindingResult.hasErrors()) {
            return "shop/productNew";
        }
        final Product entity = repository.save(product);
        return "redirect:/products/" + entity.getTitle();
    }


    @RequestMapping(value = "/titles/available", method = RequestMethod.GET)
    @ResponseBody
    public String isTitleAvailable(@RequestParam String title) {
        Product product = repository.findByTitle(title);
        return Boolean.toString(product == null);
    }
}
