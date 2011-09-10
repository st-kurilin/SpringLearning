package com.security;

import com.domain.customer.CurrentUserProvider;
import com.domain.customer.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Stanislav Kurilin
 */

public class CurrentUserProviderImpl implements CurrentUserProvider {
    @Override
    public User currentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
        } else {
            String username = principal.toString();
        }
        return null;
    }
}
