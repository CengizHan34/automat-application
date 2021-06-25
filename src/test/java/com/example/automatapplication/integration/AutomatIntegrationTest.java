package com.example.automatapplication.integration;

import com.example.automatapplication.dto.OrderRequest;
import com.example.automatapplication.dto.OrderResponse;
import com.example.automatapplication.enums.PaymentType;
import com.example.automatapplication.enums.TransactionStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

/**
 * @author created by cengizhan on 24.06.2021
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AutomatIntegrationTest {
    @LocalServerPort
    private int port;
    private String url;
    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void init(){
        url = String.format("http://localhost:%d",port);
    }

    @Test
    public void createOrder(){
        StringBuilder stringBuilder = new StringBuilder(url).append("/order");
        HttpHeaders headers = new HttpHeaders();
        OrderRequest orderRequest = OrderRequest.builder().productId(1l).paymentType(PaymentType.BANKNOTE)
                .paymentAmount(new BigDecimal(10)).num(2).numberOfSugar(2)
                .build();
        HttpEntity<OrderRequest> request = new HttpEntity<>(orderRequest, headers);
        ResponseEntity<OrderResponse> response = this.restTemplate.postForEntity(stringBuilder.toString(), request, OrderResponse.class);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getStatus(), TransactionStatus.SUCCESS);
    }
}
