package com.domain;

import com.domain.User;

/**
 * @author Stanislav Kurilin
 */
public interface FinancialService {
    void transfer(User from, User to, int amount);
}
