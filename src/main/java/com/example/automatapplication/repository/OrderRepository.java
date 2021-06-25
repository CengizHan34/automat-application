package com.example.automatapplication.repository;

import com.example.automatapplication.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author created by cengizhan on 22.06.2021
 */
public interface OrderRepository extends JpaRepository<Product, Long> {
}
