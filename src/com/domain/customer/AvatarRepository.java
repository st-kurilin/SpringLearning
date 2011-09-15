package com.domain.customer;

import com.sun.istack.internal.Nullable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

/**
 * @author Stanislav Kurilin
 */
@Repository
@NoRepositoryBean
public interface AvatarRepository {

    void assign(Long user, @Nullable Avatar avatar);

    Avatar load(Long user);
    boolean exist(Long userId);
}
