package com.domain.commerce;

import com.domain.commerce.Transaction;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Stanislav Kurilin
 */
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
