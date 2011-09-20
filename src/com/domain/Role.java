package com.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

/**
 * @author Stanislav Kurilin
 */
public enum Role {
    ADMINISTRATOR, USER, ANONYMOUS;

    public GrantedAuthority authority() {
        return new GrantedAuthorityImpl("ROLE_" + this.name());
    }
}
