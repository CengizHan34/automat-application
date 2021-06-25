package com.example.automatapplication.dto;

import com.example.automatapplication.enums.PaymentType;
import com.example.automatapplication.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author created by cengizhan on 21.06.2021
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private UUID transactionId;
    private PaymentType paymentType;
    private TransactionStatus status;
    private String name;
    private int num;
    private BigDecimal amountToBeRefunded;
    private BigDecimal totalFee;
}
