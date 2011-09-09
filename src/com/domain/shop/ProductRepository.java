package com.domain.shop;

import com.domain.customer.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author Stanislav Kurilin
 */
public interface ProductRepository extends CrudRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    List<Product> findBySeller(User user);

    Product findByTitle(String title);
    //TODO: [stas] pageable retrieve with custom soring (by price/date)
}
