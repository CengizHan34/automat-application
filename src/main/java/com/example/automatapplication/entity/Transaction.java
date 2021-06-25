package com.example.automatapplication.entity;

import com.example.automatapplication.enums.PaymentType;
import com.example.automatapplication.enums.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author created by cengizhan on 23.06.2021
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    private UUID transactionId;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
    private BigDecimal amountToBeRefunded;
    private BigDecimal totalAmount;
    private PaymentType paymentType;
    private BigDecimal paymentAmount;
    private String description;
}
