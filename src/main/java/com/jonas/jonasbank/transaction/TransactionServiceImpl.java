package com.jonas.jonasbank.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public ResponseEntity<Transaction> newPayment(Transaction transaction) {

        Transaction newTransaction = new Transaction();

        newTransaction.setSenderId(transaction.getSenderId());
        newTransaction.setReceiverId(transaction.getReceiverId());
        newTransaction.setCredit(transaction.getCredit());
        transactionRepository.save(newTransaction);

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Transaction>> allPayments() {
        List<Transaction> transactions = new ArrayList<>(transactionRepository.findAll());
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @Override
    public List<Transaction> userPayments(Long senderId){
        List<Transaction> userPayments = new ArrayList<>();
        userPayments.add(transactionRepository.findAllBySenderId(senderId));
        return userPayments;
    }

}
