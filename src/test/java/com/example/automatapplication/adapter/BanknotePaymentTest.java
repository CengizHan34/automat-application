package com.example.automatapplication.adapter;

import com.example.automatapplication.dto.OrderRequest;
import com.example.automatapplication.dto.PaymentInfo;
import com.example.automatapplication.enums.BeverageType;
import com.example.automatapplication.enums.PaymentType;
import com.example.automatapplication.enums.TransactionStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author created by cengizhan on 25.06.2021
 */
@ExtendWith(MockitoExtension.class)
public class BanknotePaymentTest {
    private BanknotePayment target;

    private OrderRequest orderRequest = OrderRequest.builder().productId(1l).paymentAmount(new BigDecimal(10))
            .num(2).numberOfSugar(3).paymentType(PaymentType.BANKNOTE).beverageType(BeverageType.HOT).build();
    private UUID transactionId = UUID.randomUUID();


    @BeforeEach
    void init(){
        target = new BanknotePayment();
    }

    @Test
    public void pay_ifPaymentIsSuccessful_shouldBeTransactionStatusTrue(){
        BigDecimal totalFee = new BigDecimal(8);
        PaymentInfo result = target.pay(orderRequest,totalFee, transactionId);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatus(), TransactionStatus.SUCCESS);
    }

    @Test
    public void pay_ifPaymentIsFailed_shouldBeTransactionStatusFalse(){
        BigDecimal totalFee = new BigDecimal(15);
        PaymentInfo result = target.pay(orderRequest,totalFee, transactionId);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getStatus(), TransactionStatus.FAILED);
    }
}
