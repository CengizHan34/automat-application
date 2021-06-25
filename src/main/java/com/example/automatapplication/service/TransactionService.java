package com.example.automatapplication.service;

import com.example.automatapplication.entity.Transaction;

import java.util.UUID;

/**
 * @author created by cengizhan on 23.06.2021
 */
public interface TransactionService {
    Transaction save(Transaction transaction);

    Transaction findByTransactionId(UUID transactionId);
}
