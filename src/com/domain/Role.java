package com.domain;

import com.google.common.base.Predicate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import java.util.Collection;

import static com.google.common.collect.Collections2.filter;
import static java.util.Arrays.asList;

/**
 * @author Stanislav Kurilin
 */
public enum Role {
    ADMINISTRATOR(true), USER(true), ANONYMOUS(false);
    /*determinate if it could be assigned throw user editing*/
    private final boolean assignable;

    private Role(boolean assignable) {
        this.assignable = assignable;
    }

    public GrantedAuthority authority() {
        return new GrantedAuthorityImpl("ROLE_" + this.name());
    }

    public static Collection<Role> assignable() {
        return filter(asList(values()), new Predicate<Role>() {
            @Override
            public boolean apply(Role role) {
                return role.assignable;
            }
        });
    }
}
