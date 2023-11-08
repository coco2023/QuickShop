package com.umistore.imsys.service;

import com.umistore.imsys.entity.Transaction;
import com.umistore.imsys.exception.ResourceNotFoundException;
import com.umistore.imsys.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Integer id) {
        transactionRepository.deleteById(id);
    }

    public Transaction updateTransactionById(Integer id, Transaction transactionDetails) {
        return transactionRepository.findById(id)
                .map(transaction -> {
                    // Update transaction with new details
                    // Assuming you have getters and setters in your Transaction entity

                    // Update only a selection of fields, not all fields should be updated
                    // For example, you might not want to update the TransactionID

                    if (transactionDetails.getQuantity() != null) {
                        transaction.setQuantity(transactionDetails.getQuantity());
                    }
                    if (transactionDetails.getTransactionType() != null) {
                        transaction.setTransactionType(transactionDetails.getTransactionType());
                    }
                    // You can add more fields to be updated as needed

                    // Save the updated transaction back to the database
                    return transactionRepository.save(transaction);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id " + id));
    }

    public List<Transaction> searchTransactions(String transactionType, Integer quantity) {
        if (transactionType != null && quantity != null) {
            return transactionRepository.findByTransactionTypeAndQuantity(transactionType, quantity);
        } else if (transactionType != null) {
            return transactionRepository.findByTransactionType(transactionType);
        } else if (quantity != null) {
            return transactionRepository.findByQuantity(quantity);
        } else {
            return transactionRepository.findAll();
        }
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
