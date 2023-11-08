package com.umistore.imsys.service;

import com.umistore.imsys.entity.PaymentTransaction;
import com.umistore.imsys.repository.PaymentTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PaymentTransactionService  {
    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;

    @Transactional
    public PaymentTransaction createPaymentTransaction(PaymentTransaction paymentTransaction) {
        return paymentTransactionRepository.save(paymentTransaction);
    }

    public void deletePaymentTransaction(Integer id) {
        paymentTransactionRepository.deleteById(id);
    }

    public PaymentTransaction updatePaymentTransaction(Integer transactionId, PaymentTransaction newData) {
        return paymentTransactionRepository.findById(transactionId)
                .map(transaction -> {
                    transaction.setStripeChargeId(newData.getStripeChargeId());
                    transaction.setAmount(newData.getAmount());
                    transaction.setCurrency(newData.getCurrency());
                    transaction.setPaymentStatus(newData.getPaymentStatus());
                    // Add more fields to update as necessary
                    return paymentTransactionRepository.save(transaction);
                }).orElseThrow(() -> new EntityNotFoundException("PaymentTransaction not found with id " + transactionId));
    }

    public List<PaymentTransaction> searchByStatus(String status) {
        return paymentTransactionRepository.findByPaymentStatus(status);
    }

    public List<PaymentTransaction> getAllPaymentTransactions() {
        return paymentTransactionRepository.findAll();
    }

}
