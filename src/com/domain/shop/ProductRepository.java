package com.domain.shop;

import com.domain.customer.User;

import java.util.List;

/**
 * @author Stanislav Kurilin
 */
public interface ProductRepository  {
    //TODO: [stas] make it pageable
    List<Product> findBySeller(User user);
    //TODO: [stas] all crud operations
    //TODO: [stas] pageable retrieve with custom soring (by price/date)
}
