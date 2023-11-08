package com.umistore.imsys.repository;

import com.umistore.imsys.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByTransactionType(String transactionType);

    List<Transaction> findByQuantity(Integer quantity);

    List<Transaction> findByTransactionTypeAndQuantity(String transactionType, Integer quantity);

}
