package com.jonas.jonasbank.transaction;

import jakarta.persistence.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@Entity
@Table(name = "Transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long senderId;

    private Long receiverId;

    private int credit;

    public Transaction(Long senderId, Long receiverId, int credit) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.credit = credit;
    }

    public Transaction() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
