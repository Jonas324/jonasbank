package com.jonas.jonasbank.transaction;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TransactionService {

    ResponseEntity<Transaction> newPayment(Transaction transaction);

    ResponseEntity<List<Transaction>> allPayments();

    List<Transaction> userPayments(Long senderId);
}
