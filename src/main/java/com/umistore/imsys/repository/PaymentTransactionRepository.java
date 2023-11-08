package com.umistore.imsys.repository;

import com.umistore.imsys.entity.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Integer> {
    List<PaymentTransaction> findByPaymentStatus(String status);
}
