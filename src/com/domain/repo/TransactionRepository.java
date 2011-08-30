package com.domain.repo;

import com.domain.Transaction;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Stanislav Kurilin
 */
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
