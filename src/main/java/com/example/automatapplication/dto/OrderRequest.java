package com.example.automatapplication.dto;

import com.example.automatapplication.enums.BeverageType;
import com.example.automatapplication.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author created by cengizhan on 21.06.2021
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @NotNull(message = "")
    private Long productId;
    @NotNull(message = "")
    private PaymentType paymentType;
    private BigDecimal paymentAmount;
    private String creditCardPassword;
    private int num = 1;
    private BeverageType beverageType;
    private int numberOfSugar;
}
