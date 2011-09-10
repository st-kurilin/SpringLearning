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
import java.util.GregorianCalendar;

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
        User user = new User();
        user.setName("Elena");
        user.setPassword("pass");
        user.setEmail(new EmailAddress("elena.fabina@mail.ru"));
        user.setBirthday(new Date(new GregorianCalendar(1991, 10, 10).getTimeInMillis()));
        user.setGender(Gender.FEMALE);
        user = userRepository.save(user);

        Product product = new Product();
        product.setTitle("chair");
        product.setPrice(new Money(300.25));
        product.setUser(user);
        productRepository.save(product);

        product = new Product();
        product.setTitle("bedroom");
        product.setPrice(new Money(1230.20));
        product.setUser(user);
        productRepository.save(product);

        product = new Product();
        product.setTitle("towel");
        product.setPrice(new Money(5.5));
        product.setUser(user);
        productRepository.save(product);

        user = new User();
        user.setName("Vasiliy");
        user.setPassword("pass");
        user.setEmail(new EmailAddress("vasil.luchhkevich@gmail.com"));
        user.setBirthday(new Date(new GregorianCalendar(1968, 3, 14).getTimeInMillis()));
        user.setGender(Gender.MALE);
        user = userRepository.save(user);

        product = new Product();
        product.setTitle("monitor");
        product.setPrice(new Money(1700.00));
        product.setUser(user);
        productRepository.save(product);

        product = new Product();
        product.setTitle("keyboard");
        product.setPrice(new Money(12.525));
        product.setUser(user);
        productRepository.save(product);

        user = new User();
        user.setName("Petr");
        user.setPassword("pass");
        user.setEmail(new EmailAddress("petrAndVasik@yahoo.ru"));
        user.setBirthday(new Date(new GregorianCalendar(1956, 11, 25).getTimeInMillis()));
        user.setGender(Gender.MALE);
        user = userRepository.save(user);

        product = new Product();
        product.setTitle("book of Robinson Crusoe");
        product.setPrice(new Money(35.755));
        product.setUser(user);
        productRepository.save(product);

        product = new Product();
        product.setTitle("table");
        product.setPrice(new Money(350.454));
        product.setUser(user);
        productRepository.save(product);


        user = new User();
        user.setName("Klara");
        user.setPassword("pass");
        user.setEmail(new EmailAddress("klara.petrova@mail.ru"));
        user.setBirthday(new Date(new GregorianCalendar(1958, 1, 4).getTimeInMillis()));
        user.setGender(Gender.FEMALE);
        user = userRepository.save(user);

        product = new Product();
        product.setTitle("T-Shirt with Harry Potter");
        product.setPrice(new Money(50));
        product.setUser(user);
        productRepository.save(product);

        product = new Product();
        product.setTitle("Lamp with Jennie");
        product.setPrice(new Money(5000.00));
        product.setUser(user);
        productRepository.save(product);

        user = new User();
        user.setName("John");
        user.setPassword("pass");
        user.setEmail(new EmailAddress("john.smith@gmail.com"));
        user.setBirthday(new Date(new GregorianCalendar(1986, 5, 6).getTimeInMillis()));
        user.setGender(Gender.MALE);
        user = userRepository.save(user);

        product = new Product();
        product.setTitle("Banana with tomato");
        product.setPrice(new Money(1230.20));
        product.setUser(user);
        productRepository.save(product);

        product = new Product();
        product.setTitle("Headphones with microphone");
        product.setPrice(new Money(15.40));
        product.setUser(user);
        productRepository.save(product);

        user = new User();
        user.setName("Karl");
        user.setPassword("pass");
        user.setEmail(new EmailAddress("karl.marks@mail.ru"));
        user.setBirthday(new Date(new GregorianCalendar(1965, 23, 12).getTimeInMillis()));
        user.setGender(Gender.FEMALE);
        user = userRepository.save(user);
    }
}
