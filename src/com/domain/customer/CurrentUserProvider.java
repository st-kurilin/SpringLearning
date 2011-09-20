package com.domain.customer;

/**
 * Used for providing current user
 *
 * @author Stanislav Kurilin
 */
public interface CurrentUserProvider {
    /**
     * @return current authenticated user or null if user didn't sign up
     */
    User currentUser();
}
