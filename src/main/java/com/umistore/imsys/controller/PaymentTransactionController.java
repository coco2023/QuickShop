package com.umistore.imsys.controller;

import com.umistore.imsys.entity.PaymentTransaction;
import com.umistore.imsys.service.PaymentTransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paymentTransactions")
@Api(value = "PaymentTransactionController", description = "Operations pertaining to payment transactions")
public class PaymentTransactionController {

    @Autowired
    private PaymentTransactionService paymentTransactionService;

    @PostMapping("/")
    @ApiOperation(value = "Create a payment transaction", response = PaymentTransaction.class)
    public ResponseEntity<PaymentTransaction> createPaymentTransaction(@RequestBody PaymentTransaction paymentTransaction) {
        PaymentTransaction savedTransaction = paymentTransactionService.createPaymentTransaction(paymentTransaction);
        return ResponseEntity.ok(savedTransaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaymentTransaction(@PathVariable Integer id) {
        paymentTransactionService.deletePaymentTransaction(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentTransaction> updatePaymentTransaction(@PathVariable Integer id, @RequestBody PaymentTransaction paymentTransaction) {
        return ResponseEntity.ok(paymentTransactionService.updatePaymentTransaction(id, paymentTransaction));
    }

    // Search for transactions by status
    @GetMapping("/search")
    public ResponseEntity<List<PaymentTransaction>> searchTransactionsByStatus(@RequestParam String status) {
        List<PaymentTransaction> transactions = paymentTransactionService.searchByStatus(status);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PaymentTransaction>> getAllPaymentTransactions() {
        List<PaymentTransaction> transactions = paymentTransactionService.getAllPaymentTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

}
