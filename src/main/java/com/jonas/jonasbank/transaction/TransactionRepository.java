package com.jonas.jonasbank.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT s FROM Transaction s WHERE s.senderId = ?1")
    Transaction findAllBySenderId(Long senderId);
}
