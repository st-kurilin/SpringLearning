package com.services;

import com.domain.FinancialService;
import com.domain.commerce.Money;
import com.domain.commerce.Transaction;
import com.domain.User;
import com.domain.commerce.TransactionRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;

/**
 * @author Stanislav Kurilin
 */
@Transactional(readOnly = false)
public class FinancialServiceImpl implements FinancialService {
    private final TransactionRepository repository;

    @Inject
    public FinancialServiceImpl(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void transfer(User from, User to, int amount) {
        final Transaction transaction = new Transaction();
        transaction.setFrom(from);
        transaction.setTo(to);
        transaction.setAmount(new Money(amount));
        transaction.setDate(new Date());
        repository.save(transaction);
    }
}
