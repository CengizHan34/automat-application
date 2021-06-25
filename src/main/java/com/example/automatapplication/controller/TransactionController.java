package com.example.automatapplication.controller;

import com.example.automatapplication.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author created by cengizhan on 24.06.2021
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{transactionId}")
    private ResponseEntity getTransaction(@PathVariable UUID transactionId) {
        return ResponseEntity.ok().body(transactionService.findByTransactionId(transactionId));
    }
}
