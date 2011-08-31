package com.domain.commerce;

import com.domain.customer.User;

/**
 * @author Stanislav Kurilin
 */
public interface FinancialService {
    void transfer(User from, User to, int amount);
}
