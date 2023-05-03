package com.example.ordermanagementsystem.api;

import com.example.ordermanagementsystem.Startup;
import com.example.ordermanagementsystem.controller.OrderApi;
import com.example.ordermanagementsystem.controller.OrderLineApi;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderCreate;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderLineCreate;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderLineCreateChild;
import com.example.ordermanagementsystem.util.Json;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class OrderApiTest {

    @Autowired
    private OrderApi orderApi;

    @Autowired
    private OrderLineApi orderLineApi;

    @Autowired
    private Startup startup;

    @Test
    public void creatEmptyOrder() throws IOException {
        startup.seedData(false);

        val draftOrder = ApiDtoOrderCreate.builder()
                .customerId(UUID.fromString("1c03d14d-1141-4bca-93ba-d5850362f646"))
                .orderLines(new ArrayList<>())
                .submittedDate(null)
                .build();

        val response = orderApi.createOrder(draftOrder);
        assert(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().id);
    }

    @Test
    public void createFullOrder() throws IOException {
        startup.seedData(false);

        val orderLines = new ArrayList<ApiDtoOrderLineCreateChild>();
        orderLines.add(ApiDtoOrderLineCreateChild.builder()
                .quantity(1)
                .productUnitPrice(10f)
                .productSKU("ec26-496c")
                .productId(UUID.fromString("5425941d-bbde-4e10-8ff9-0b83324f3258"))
                .build());
        orderLines.add(ApiDtoOrderLineCreateChild.builder()
                .quantity(2)
                .productUnitPrice(22f)
                .productSKU("526a-4f6b")
                .productId(UUID.fromString("4e271cb7-8c4a-4eee-8be4-0f8c1cdd79f1"))
                .build());

        val draftOrder = ApiDtoOrderCreate.builder()
                .customerId(UUID.fromString("1c03d14d-1141-4bca-93ba-d5850362f646"))
                .orderLines(orderLines)
                .submittedDate(null)
                .build();

        val response = orderApi.createOrder(draftOrder);
        assert(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        val orderWithOrderLines = orderApi.getOrderIncludeOrderLine(response.getBody().id).getBody();
        assertNotNull(orderWithOrderLines);
        assertEquals(2, orderWithOrderLines.orderLines.size());
    }

    @Test
    public void creatOrderAddOrderLinesAndSubmitOrder() throws IOException {
        startup.seedData(false);

        val draftOrder = ApiDtoOrderCreate.builder()
                .customerId(UUID.fromString("1c03d14d-1141-4bca-93ba-d5850362f646"))
                .orderLines(new ArrayList<>())
                .submittedDate(null)
                .build();

        val response = orderApi.createOrder(draftOrder);
        assert(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        val orderWithOrderLinesBefore = orderApi.getOrderIncludeOrderLine(response.getBody().id).getBody();
        assertNotNull(orderWithOrderLinesBefore);
        assertEquals(0, orderWithOrderLinesBefore.orderLines.size());

        val orderLines = new ArrayList<ApiDtoOrderLineCreate>();
        orderLines.add(ApiDtoOrderLineCreate.builder()
                .quantity(1)
                .productUnitPrice(10f)
                .productSKU("ec26-496c")
                .productId(UUID.fromString("5425941d-bbde-4e10-8ff9-0b83324f3258"))
                .orderId(response.getBody().id)
                .build());
        orderLines.add(ApiDtoOrderLineCreate.builder()
                .quantity(2)
                .productUnitPrice(22f)
                .productSKU("526a-4f6b")
                .productId(UUID.fromString("4e271cb7-8c4a-4eee-8be4-0f8c1cdd79f1"))
                .orderId(response.getBody().id)
                .build());
        orderLineApi.addOrderLine(orderLines.get(0));
        orderLineApi.addOrderLine(orderLines.get(1));
        val orderWithOrderLines = orderApi.getOrderIncludeOrderLine(response.getBody().id).getBody();
        assertNotNull(orderWithOrderLines);
        assertEquals(2, orderWithOrderLines.orderLines.size());
        assertNull(orderWithOrderLines.submittedDate);

        val submittedOrder = orderApi.markSubmitted(response.getBody().id, OffsetDateTime.now()).getBody();
        assertNotNull(submittedOrder);
        assertNotNull(submittedOrder.submittedDate);
    }

    @Test
    public void ordersAreSearchableByDate() throws IOException {
        startup.seedData(false);

        val response = orderApi.getOrdersBetweenDates(OffsetDateTime.parse("2022-07-06T20:10:13Z"), OffsetDateTime.parse("2022-08-23T07:06:56Z"));
        assert(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assert(response.getBody().stream().map(x -> x.id).toList().contains(UUID.fromString("51e7852d-ec25-4c9e-81f2-83847fd05095")));
        assert(response.getBody().stream().map(x -> x.id).toList().contains(UUID.fromString("1734f7f9-9827-4a40-89c0-2e1a65b472bf")));
    }
}
