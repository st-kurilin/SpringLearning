package com.domain.customer;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Stanislav Kurilin
 */
@NoRepositoryBean
public interface UpdateableUserRepository extends Repository<User, Long> {
    @Modifying
    @Transactional
    User updateEntity(Long id, User data);
}
