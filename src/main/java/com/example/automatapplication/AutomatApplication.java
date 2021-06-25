package com.example.automatapplication;

import com.example.automatapplication.entity.BeverageProps;
import com.example.automatapplication.entity.Product;
import com.example.automatapplication.enums.BeverageType;
import com.example.automatapplication.enums.ProductType;
import com.example.automatapplication.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class AutomatApplication implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(AutomatApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        BeverageProps tea = new BeverageProps(BeverageType.HOT);
        Product productTea = Product.builder().productType(ProductType.BEVERAGE)
                .beverageProps(tea).name("Çay").amount(new BigDecimal("2")).build();
        BeverageProps water = new BeverageProps(BeverageType.COLD);
        Product productWater = Product.builder().productType(ProductType.BEVERAGE)
                .beverageProps(water).name("Su").amount(new BigDecimal("1")).build();

        Product cin = Product.builder().productType(ProductType.FOOD)
                .name("Cin").amount(new BigDecimal("2.5")).build();

        Product cizi = Product.builder().productType(ProductType.FOOD)
                .name("Çizi").amount(new BigDecimal("3")).build();

        Product halley = Product.builder().productType(ProductType.FOOD)
                .name("Halley").amount(new BigDecimal("3")).build();

        List<Product> productList = Arrays.asList(productTea, productWater, cin, cizi, halley);
        productRepository.saveAll(productList);


    }
}
