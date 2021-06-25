package com.example.automatapplication.service.impl;

import com.example.automatapplication.adapter.Payment;
import com.example.automatapplication.adapter.PaymentFactory;
import com.example.automatapplication.dto.OrderRequest;
import com.example.automatapplication.dto.OrderResponse;
import com.example.automatapplication.dto.PaymentInfo;
import com.example.automatapplication.entity.Order;
import com.example.automatapplication.entity.Product;
import com.example.automatapplication.entity.Transaction;
import com.example.automatapplication.exception.AutomatException;
import com.example.automatapplication.repository.OrderRepository;
import com.example.automatapplication.service.OrderService;
import com.example.automatapplication.service.ProductService;
import com.example.automatapplication.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author created by cengizhan on 22.06.2021
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductService productService;
    private final TransactionService transactionService;

    @Override
    public OrderResponse createOrder(final OrderRequest orderRequest) {
        if (ObjectUtils.isEmpty(orderRequest)) {
            throw new AutomatException(HttpStatus.NO_CONTENT, "Order request cannot be null!");
        }
        UUID transactionId = UUID.randomUUID();
        Product product = productService.findById(orderRequest.getProductId());
        Order order = Order.builder().product(product).num(orderRequest.getNum())
                .numberOfSugar(orderRequest.getNumberOfSugar()).build();

        BigDecimal totalFee = product.getAmount().multiply(new BigDecimal(orderRequest.getNum()));

        Payment payment = PaymentFactory.intialisePayment(orderRequest.getPaymentType());

        PaymentInfo paymentInfo = payment.pay(orderRequest, totalFee, transactionId);

        Transaction transaction = getTransaction(orderRequest, product, order, paymentInfo, transactionId);

        Transaction savedTransaction = transactionService.save(transaction);
        log.info("Transaction completed successfully. transactionId: {}",
                savedTransaction.getTransactionId().toString());
        return getOrderResponseBuild(savedTransaction);
    }

    private OrderResponse getOrderResponseBuild(Transaction savedTransaction) {
        return OrderResponse.builder().transactionId(savedTransaction.getTransactionId())
                .paymentType(savedTransaction.getPaymentType())
                .name(savedTransaction.getOrder().getProduct().getName())
                .totalFee(savedTransaction.getTotalAmount())
                .num(savedTransaction.getOrder().getNum())
                .amountToBeRefunded(savedTransaction.getAmountToBeRefunded())
                .status(savedTransaction.getTransactionStatus())
                .build();
    }

    private Transaction getTransaction(OrderRequest orderRequest, Product product, Order order, PaymentInfo paymentInfo,
                                       UUID transactionId) {
        return Transaction.builder()
                .transactionId(transactionId)
                .transactionStatus(paymentInfo.getStatus())
                .paymentType(orderRequest.getPaymentType())
                .order(order)
                .totalAmount(product.getAmount().multiply(new BigDecimal(order.getNum())))
                .paymentAmount(orderRequest.getPaymentAmount())
                .amountToBeRefunded(paymentInfo.getAmountToBeRefunded())
                .description(paymentInfo.getDescription())
                .build();
    }
}
