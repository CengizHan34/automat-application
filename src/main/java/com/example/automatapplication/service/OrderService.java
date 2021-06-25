package com.example.automatapplication.service;

import com.example.automatapplication.dto.OrderRequest;
import com.example.automatapplication.dto.OrderResponse;

/**
 * @author created by cengizhan on 22.06.2021
 */
public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
}
