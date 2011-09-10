package com.security;

import com.domain.customer.EmailAddress;
import com.domain.customer.User;
import com.domain.customer.UserRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.inject.Inject;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Stanislav Kurilin
 */
class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Inject
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, DataAccessException {
        final User user = userRepository.findByEmail(new EmailAddress(email));
        if (user == null) {
            throw new UsernameNotFoundException("Can't find by email = " + email);
        }
        return new UserDetails() {
            @Override
            public Collection<GrantedAuthority> getAuthorities() {
                final LinkedList<GrantedAuthority> results = new LinkedList<GrantedAuthority>();
                if (user.getName().startsWith("Bob")) {
                    results.add(new GrantedAuthorityImpl("ROLE_GOOD_MAN"));
                }
                return results;
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
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;  //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public boolean isEnabled() {
                return true;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
    }
}
