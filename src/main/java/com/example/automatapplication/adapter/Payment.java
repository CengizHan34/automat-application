package com.example.automatapplication.adapter;

import com.example.automatapplication.dto.OrderRequest;
import com.example.automatapplication.dto.PaymentInfo;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author created by cengizhan on 22.06.2021
 */
public interface Payment {
    PaymentInfo pay(OrderRequest orderRequest, BigDecimal totalFee, UUID transactionId);
}
