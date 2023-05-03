package com.example.ordermanagementsystem.api;

import com.example.ordermanagementsystem.Startup;
import com.example.ordermanagementsystem.controller.ProductApi;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoProductCreate;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProductApiTest {

    @Autowired
    private ProductApi productApi;

    @Autowired
    private Startup startup;

    @Test
    public void getOrdersByProduct() throws IOException {
        startup.seedData(false);
        val response = productApi.getIncludeOrderLinesThenIncludeOrder(UUID.fromString("7907ad40-e1d4-4eeb-9b44-1ad92381dc22"));
        assert(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals(5, response.getBody().orderLines.size());
    }

    @Test
    public void createProduct() throws IOException {
        startup.seedData(false);
        val draftProduct = ApiDtoProductCreate.builder()
                .name("Northwoods Cranberry Sauce")
                .skuCode("b48a-3945")
                .unitPrice(40f)
                .startDate(OffsetDateTime.now())
                .endDate(OffsetDateTime.now().plusDays(7))
                .build();
        val response = productApi.create(draftProduct);
        assert(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().id);
    }
}
