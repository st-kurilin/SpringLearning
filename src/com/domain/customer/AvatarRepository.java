package com.domain.customer;

import com.sun.istack.internal.Nullable;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Stanislav Kurilin
 */
@NoRepositoryBean
public interface AvatarRepository {

    void assign(Long user, @Nullable Avatar avatar);

    Avatar load(Long user);

    boolean exist(Long userId);
}
