package com.example.automatapplication.dto;

import com.example.automatapplication.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author created by cengizhan on 23.06.2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInfo {
    private TransactionStatus status;
    private BigDecimal amountToBeRefunded = new BigDecimal(0);
    private String description;
}
