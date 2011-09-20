package com.security;

import com.domain.customer.EmailAddress;
import com.domain.customer.User;
import com.domain.customer.UserRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Used for associating login with Principal
 *
 * @author Stanislav Kurilin
 */
@Component("userService")
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
            //should be thrown by design
            throw new UsernameNotFoundException("Can't find by email = " + email);
        }
        return Principal.createFromPerson(user);
    }
}
