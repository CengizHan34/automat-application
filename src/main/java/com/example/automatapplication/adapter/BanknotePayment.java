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
public class BanknotePayment implements Payment {

    @Override
    public PaymentInfo pay(OrderRequest orderRequest, BigDecimal totalFee, UUID transactionId) {
        PaymentInfo paymentInfo = new PaymentInfo();
        BigDecimal result = orderRequest.getPaymentAmount().subtract(totalFee);
        if (result.compareTo(new BigDecimal(0)) >= 0) {
            paymentInfo.setStatus(TransactionStatus.SUCCESS);
            paymentInfo.setAmountToBeRefunded(result);
            log.info("Banknote payment successful. transactionId: {}", transactionId.toString());
            return paymentInfo;
        }
        paymentInfo.setStatus(TransactionStatus.FAILED);
        paymentInfo.setDescription("The transaction could not be completed due to insufficient fees.");
        paymentInfo.setAmountToBeRefunded(orderRequest.getPaymentAmount());
        log.info("Banknote payment failed! transactionId: {}", transactionId.toString());
        return paymentInfo;
    }
}
