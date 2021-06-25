package com.example.automatapplication.service.impl;

import com.example.automatapplication.entity.Product;
import com.example.automatapplication.exception.AutomatException;
import com.example.automatapplication.repository.ProductRepository;
import com.example.automatapplication.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author created by cengizhan on 21.06.2021
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (!product.isPresent()) {
            throw new AutomatException(HttpStatus.NOT_FOUND, "The product you selected was not found!");
        }
        return product.get();
    }
}
