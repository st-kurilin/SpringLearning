package com.domain.customer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Stanislav Kurilin
 */
public interface UserRepository extends CrudRepository<User, Long>, UpdateableUserRepository {
    User findByEmail(EmailAddress email);
}
