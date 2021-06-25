package com.example.automatapplication.adapter;

import com.example.automatapplication.enums.PaymentType;

/**
 * @author created by cengizhan on 23.06.2021
 */
public class PaymentFactory {
    public static Payment intialisePayment(PaymentType paymentType) {
        if (paymentType.equals(PaymentType.COIN)) {
            return new CoinPayment();
        } else if (paymentType.equals(PaymentType.BANKNOTE)) {
            return new BanknotePayment();
        } else if (paymentType.equals(PaymentType.CONTACT)) {
            return new ContactPayment();
        } else if (paymentType.equals(PaymentType.CONTACTLESS)) {
            return new ContactlessPayment();
        }
        throw new RuntimeException("Unsupported payment method");
    }
}
