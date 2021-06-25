package com.example.automatapplication.service;

import com.example.automatapplication.dto.OrderRequest;
import com.example.automatapplication.dto.OrderResponse;
import com.example.automatapplication.entity.BeverageProps;
import com.example.automatapplication.entity.Product;
import com.example.automatapplication.entity.Transaction;
import com.example.automatapplication.enums.BeverageType;
import com.example.automatapplication.enums.PaymentType;
import com.example.automatapplication.enums.ProductType;
import com.example.automatapplication.exception.AutomatException;
import com.example.automatapplication.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

/**
 * @author created by cengizhan on 24.06.2021
 */
@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    private OrderService target;
    @Mock
    private ProductService productService;
    @Mock
    private TransactionService transactionService;

    @BeforeEach
    void init() {
        target = new OrderServiceImpl(productService, transactionService);
    }

    @Test
    public void createOrder_ifOrderRequestIsNull_throwAutomatException(){
        AutomatException exception = Assertions.assertThrows(AutomatException.class, () -> {
            target.createOrder(null);
        });

        Assertions.assertEquals(exception.getMessage(),"Order request cannot be null!");
        Assertions.assertEquals(exception.getHttpStatus(),HttpStatus.NO_CONTENT);
    }

    @Test
    public void createOrder_ifOrderRequestIsNotNull_shouldReturnOrderResponse(){
        OrderRequest orderRequest = OrderRequest.builder().productId(1l).paymentAmount(new BigDecimal(10))
                .num(2).numberOfSugar(3).paymentType(PaymentType.BANKNOTE).beverageType(BeverageType.HOT).build();
        Product product = Product.builder().id(1l).productType(ProductType.BEVERAGE).amount(new BigDecimal(3))
                .name("Çay").beverageProps(new BeverageProps(BeverageType.HOT)).build();

        when(productService.findById(orderRequest.getProductId())).thenReturn(product);
        when(transactionService.save(Mockito.any(Transaction.class))).thenAnswer(i -> i.getArguments()[0]);

        OrderResponse result = target.createOrder(orderRequest);
        Assertions.assertEquals(orderRequest.getNum(),result.getNum());
        Assertions.assertEquals(result.getAmountToBeRefunded(), new BigDecimal(4));
        Assertions.assertEquals(result.getTotalFee(), new BigDecimal(6));
        Assertions.assertEquals(result.getPaymentType(),PaymentType.BANKNOTE);
        Assertions.assertNotNull(result.getTransactionId());
    }

}
