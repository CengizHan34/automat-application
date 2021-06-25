package com.example.automatapplication.repository;

import com.example.automatapplication.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author created by cengizhan on 23.06.2021
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByTransactionId(UUID transactionId);
}
