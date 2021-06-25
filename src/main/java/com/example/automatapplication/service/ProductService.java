package com.example.automatapplication.service;

import com.example.automatapplication.entity.Product;

import java.util.List;

/**
 * @author created by cengizhan on 21.06.2021
 */
public interface ProductService {
    List<Product> findAll();

    Product findById(Long id);
}
