package com.security;

import com.domain.Role;
import com.domain.customer.User;
import com.google.common.collect.ImmutableSet;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static java.lang.String.format;

/**
 * Represents Person in security system.
 * Authenticated (not anonymously) principal always associated with user.
 *
 * @author Stanislav Kurilin
 */
abstract class Principal implements UserDetails {
    public static Principal retrieve(Authentication authentication) {
        if (isAnonymous(authentication)) {
            return ANONYMOUS_USER;
        }
        final Object principal = authentication.getPrincipal();
        if (!(principal instanceof Principal)) {
            throw new UnsupportedOperationException(
                    format("Unsupported UserDetails implementation class %s on %s",
                            authentication.getDetails(),
                            authentication.getClass()));
        }
        return (Principal) authentication.getPrincipal();
    }

    public static Principal createFromPerson(final User user) {
        return new Principal() {
            @Override
            public Collection<GrantedAuthority> getAuthorities() {
                return ImmutableSet.<GrantedAuthority>of(Role.USER.authority());
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getEmail().toString();
            }

            @Override
            public User getPerson() {
                return user;
            }
        };
    }

    public abstract User getPerson();

    public boolean hasRole(Role role) {
        return getAuthorities().contains(role.authority());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //Just use retrieve() factory method
    private Principal() {
    }

    /*
    * From spring security spec http://static.springsource.org/spring-security/site/docs/3.0.x/reference/anonymous.html
    * Note that there is no real conceptual difference between
    * a user who is “anonymously authenticated” and an unauthenticated user.
    *
    */
    private static boolean isAnonymous(Authentication authentication) {
        return (!authentication.isAuthenticated()) || authentication.getAuthorities().contains(Role.ANONYMOUS.authority());
    }

    private static final Principal ANONYMOUS_USER = new Principal() {
        @Override
        public Collection<GrantedAuthority> getAuthorities() {
            return ImmutableSet.<GrantedAuthority>of(Role.ANONYMOUS.authority());
        }

        @Override
        public String getPassword() {
            throw new UnsupportedOperationException("Should be never called for anonymous user");
        }

        @Override
        public User getPerson() {
            throw new UnsupportedOperationException("Should be never called for anonymous user");
        }

        @Override
        public String getUsername() {
            return "[Anonymous]";
        }
    };
}
