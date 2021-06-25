package com.example.automatapplication.adapter;

import com.example.automatapplication.dto.OrderRequest;
import com.example.automatapplication.dto.PaymentInfo;
import com.example.automatapplication.enums.TransactionStatus;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author created by cengizhan on 22.06.2021
 */
@Log4j2
public class ContactlessPayment implements Payment {
    @Override
    public PaymentInfo pay(OrderRequest orderRequest, BigDecimal totalFee, UUID transactionId) {
        PaymentInfo paymentInfo = new PaymentInfo();
        if (orderRequest.getCreditCardPassword().equals("1234")) {
            paymentInfo.setStatus(TransactionStatus.SUCCESS);
            log.info("Contactless payment successful. transactionId: {}", transactionId.toString());
        }
        paymentInfo.setStatus(TransactionStatus.FAILED);
        log.info("Contactless payment failed! transactionId: {}", transactionId.toString());
        return paymentInfo;
    }
}
