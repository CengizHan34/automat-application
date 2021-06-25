package com.example.automatapplication.service.impl;

import com.example.automatapplication.entity.Transaction;
import com.example.automatapplication.repository.TransactionRepository;
import com.example.automatapplication.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author created by cengizhan on 23.06.2021
 */
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction findByTransactionId(UUID transactionId) {
        return transactionRepository.findByTransactionId(transactionId);
    }
}
