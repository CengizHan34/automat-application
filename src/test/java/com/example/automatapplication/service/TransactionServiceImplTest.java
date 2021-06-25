package com.example.automatapplication.service;

import com.example.automatapplication.entity.Order;
import com.example.automatapplication.entity.Product;
import com.example.automatapplication.entity.Transaction;
import com.example.automatapplication.enums.TransactionStatus;
import com.example.automatapplication.repository.TransactionRepository;
import com.example.automatapplication.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.when;

/**
 * @author created by cengizhan on 24.06.2021
 */
@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {
    private TransactionService target;
    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    void init(){
        target = new TransactionServiceImpl(transactionRepository);
    }

    @Test
    public void save_usedToSaveTransactionObject(){
        when(transactionRepository.save(Mockito.any(Transaction.class))).thenAnswer(i -> i.getArguments()[0]);
        Transaction transaction = target.save(new Transaction());
        Assertions.assertNotNull(transaction);
    }

    @Test
    public void findByTransactionId_ifTransactionIdIsNotNull_shouldReturnTransactionObj(){
        Transaction transaction = Transaction.builder().id(1l).transactionStatus(TransactionStatus.SUCCESS)
                .order(new Order()).amountToBeRefunded(new BigDecimal(10)).paymentAmount(new BigDecimal(10))
                .build();

        when(transactionRepository.findByTransactionId(Mockito.any(UUID.class))).thenReturn(transaction);
        Transaction result = target.findByTransactionId(UUID.randomUUID());
        Assertions.assertNotNull(result);
    }
}
