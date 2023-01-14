package com.jonas.jonasbank.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private TransactionServiceImpl transactionService;

    @Autowired
    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> newPayment(@RequestBody Transaction transaction){

        return transactionService.newPayment(transaction);

    }

    @GetMapping
    public ResponseEntity<List<Transaction>> allPayments(Transaction transaction){

        return transactionService.allPayments();
    }

    @GetMapping("/{senderId}")
    public List<Transaction> userPayments(@PathVariable("senderId") Long senderId){
        return transactionService.userPayments(senderId);
    }

}
