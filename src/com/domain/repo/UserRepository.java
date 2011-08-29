package com.domain.repo;

import com.domain.EmailAddress;
import com.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Stanislav Kurilin
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(EmailAddress email);
}
