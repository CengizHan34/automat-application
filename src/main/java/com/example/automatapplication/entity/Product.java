package com.example.automatapplication.entity;

import com.example.automatapplication.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author created by cengizhan on 21.06.2021
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    @Embedded
    private BeverageProps beverageProps;
    private String name;
    private BigDecimal amount;
}
