package com.example.ordermanagementsystem.api;

import com.example.ordermanagementsystem.controller.ProductApi;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProductApiTest {

    @Autowired
    private ProductApi productApi;

    @Test
    public void getOrdersByProduct() {
        val response = productApi.getIncludeOrderLinesThenIncludeOrder(UUID.fromString("7907ad40-e1d4-4eeb-9b44-1ad92381dc22"));
        assert(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals(response.getBody().orderLines.size(), 5);
    }
}
