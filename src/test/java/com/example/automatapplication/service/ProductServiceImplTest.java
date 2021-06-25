package com.example.automatapplication.service;

import com.example.automatapplication.entity.BeverageProps;
import com.example.automatapplication.entity.Product;
import com.example.automatapplication.enums.BeverageType;
import com.example.automatapplication.enums.ProductType;
import com.example.automatapplication.exception.AutomatException;
import com.example.automatapplication.repository.ProductRepository;
import com.example.automatapplication.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;


/**
 * @author created by cengizhan on 24.06.2021
 */
@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    private ProductService target;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void init(){
        target = new ProductServiceImpl(productRepository);
    }

    @Test
    public void findAll_ifMethodIsCalled_allProductsShouldBeReturned(){
        Product product1 = Product.builder().id(1l).productType(ProductType.BEVERAGE).amount(new BigDecimal(3))
                .name("Çay").beverageProps(new BeverageProps(BeverageType.HOT)).build();
        Product product2 = Product.builder().id(2l).productType(ProductType.FOOD).amount(new BigDecimal(5))
                .name("Çizi").build();

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1,product2));
        List<Product> result = target.findAll();
        Assertions.assertEquals(result.size(),2);
    }

    @Test
    public void findById_ifMethodIsCalled_onlyOneProductShouldBeReturned(){
        Product product = Product.builder().id(2l).productType(ProductType.FOOD).amount(new BigDecimal(5))
                .name("Çizi").build();

        when(productRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(product));
        Product result = target.findById(2l);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getName(), "Çizi");
    }

    @Test
    public void findById_ifProductIsNotFound_throwException(){
        when(productRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.ofNullable(null));

        AutomatException exception = Assertions.assertThrows(AutomatException.class, () -> {
            target.findById(10l);
        });

        Assertions.assertEquals(exception.getMessage(),"The product you selected was not found!");
        Assertions.assertEquals(exception.getHttpStatus(), HttpStatus.NOT_FOUND);
    }
}
