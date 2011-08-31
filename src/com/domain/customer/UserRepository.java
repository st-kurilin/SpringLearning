package com.domain.customer;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Stanislav Kurilin
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(EmailAddress email);
}
