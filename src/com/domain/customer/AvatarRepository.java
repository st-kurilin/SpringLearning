package com.domain.customer;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.net.URL;

/**
 * @author Stanislav Kurilin
 */
@Repository
@NoRepositoryBean
public interface AvatarRepository {
    //TODO:  Long -> UserId
    void assign(Long user, Avatar avatar);

    Avatar load(Long user);
}
