package com.domain.customer;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

/**
 * @author Stanislav Kurilin
 */
@NoRepositoryBean
public class UpdateableUserRepositoryImpl extends JpaDaoSupport implements UpdateableUserRepository {
    @Inject
    public UpdateableUserRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        setEntityManagerFactory(entityManagerFactory);
    }

    @Override
    public User updateEntity(Long id, User data) {
        data.setId(id);
        final User merge = getJpaTemplate().merge(data);
        return merge;
    }
}
