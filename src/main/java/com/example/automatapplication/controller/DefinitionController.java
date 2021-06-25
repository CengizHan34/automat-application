package com.example.automatapplication.controller;

import com.example.automatapplication.enums.BeverageType;
import com.example.automatapplication.enums.PaymentType;
import com.example.automatapplication.enums.ProductType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author created by cengizhan on 21.06.2021
 */
@RestController
@RequestMapping("/definition")
public class DefinitionController {

    @GetMapping("/payment-types")
    private ResponseEntity getPaymentTypes() {
        return ResponseEntity.ok().body(PaymentType.values());
    }

    @GetMapping("/beverage-types")
    private ResponseEntity getBeverageTypes() {
        return ResponseEntity.ok().body(BeverageType.values());
    }

    @GetMapping("/product-types")
    private ResponseEntity getProductTypes() {
        return ResponseEntity.ok().body(ProductType.values());
    }
}
