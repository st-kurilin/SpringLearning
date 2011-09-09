package com.sample;

import com.domain.commerce.Money;
import com.domain.customer.EmailAddress;
import com.domain.customer.Gender;
import com.domain.customer.User;
import com.domain.customer.UserRepository;
import com.domain.shop.Product;
import com.domain.shop.ProductRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Date;

/**
 * @author Stanislav Kurilin
 */
@Component
public class SampleDataCreator {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Inject
    public SampleDataCreator(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public void create() {
        //TODO: [stas] we shouldn't use broken data
        User user = new User();
        user.setName("Vasyaa");
        user.setEmail(new EmailAddress("alsd@asd.as"));
        user.setBirthday(new Date());
        user.setGender(Gender.MALE);

        User userId = userRepository.save(user);
        Product product;
        for (int i = 0; i < 50; i++) {
            product = new Product();
            product.setTitle("generated".intern());
            product.setPrice(new Money(50 - i));
            product.setUser(userId);
            productRepository.save(product);
        }
    }
}
