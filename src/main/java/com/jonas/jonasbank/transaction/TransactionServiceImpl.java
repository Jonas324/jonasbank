package com.jonas.jonasbank.transaction;

import com.jonas.jonasbank.user.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private UserServiceImpl userService;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  UserServiceImpl userService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Transaction> newPayment(Transaction transaction) {

        Transaction newTransaction = new Transaction();

        newTransaction.setSenderId(transaction.getSenderId());
        newTransaction.setReceiverId(transaction.getReceiverId());
        newTransaction.setCredit(transaction.getCredit());
        transactionRepository.save(newTransaction);

        userService.calculateCredit(transaction);

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
