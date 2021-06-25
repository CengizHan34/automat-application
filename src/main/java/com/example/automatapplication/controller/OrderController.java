package com.example.automatapplication.controller;

import com.example.automatapplication.dto.OrderRequest;
import com.example.automatapplication.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author created by cengizhan on 22.06.2021
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    private ResponseEntity createOrder(@RequestBody final OrderRequest orderRequest) {
        return ResponseEntity.ok().body(orderService.createOrder(orderRequest));
    }
}
