package com.security;

import com.domain.customer.CurrentUserProvider;
import com.domain.customer.EmailAddress;
import com.domain.customer.User;
import com.domain.customer.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * @author Stanislav Kurilin
 */
@Component
public class CurrentUserProviderImpl implements CurrentUserProvider {
    private final UserRepository userRepository;

    @Inject
    public CurrentUserProviderImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User currentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String emailName = ((UserDetails) principal).getUsername();
            User user = userRepository.findByEmail(new EmailAddress(emailName));
            return user;
        }
        return null;
    }
}
