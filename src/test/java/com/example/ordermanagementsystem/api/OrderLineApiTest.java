package com.example.ordermanagementsystem.api;

import com.example.ordermanagementsystem.Startup;
import com.example.ordermanagementsystem.controller.OrderApi;
import com.example.ordermanagementsystem.controller.OrderLineApi;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest
public class OrderLineApiTest {

    @Autowired
    private OrderLineApi orderLineApi;

    @Autowired
    private Startup startup;

    @Test
    public void submittedOrderCannotBeChanged() throws IOException {
        startup.seedData(false);

        assertThrows(IllegalStateException.class, () -> {
            orderLineApi.setQuantity(UUID.fromString("8875b473-5ebd-495b-a79e-84dbddf00bc8"), 5);
        });
    }


    @Test
    public void settingOrderLineQuantityToZeroDeletesOrderLine() throws IOException {
        startup.seedData(false);

        val targetOrderLineId = UUID.fromString("37ceebc8-f205-4083-8bd0-080f545151b2");

        val orderLineBefore = orderLineApi.get(targetOrderLineId).getBody();
        Assertions.assertNotNull(orderLineBefore);
        assertEquals(6, orderLineBefore.quantity);
        orderLineApi.setQuantity(targetOrderLineId, 0);
        val orderAfter = orderLineApi.get(targetOrderLineId).getBody();
        Assertions.assertNull(orderAfter);
    }

    @Test
    public void orderLineQuantityCanBeChanged() throws IOException {
        startup.seedData(false);

        val targetOrderLineId = UUID.fromString("37ceebc8-f205-4083-8bd0-080f545151b2");

        val orderLineBefore = orderLineApi.get(targetOrderLineId).getBody();
        Assertions.assertNotNull(orderLineBefore);
        assertEquals(6, orderLineBefore.quantity);
        orderLineApi.setQuantity(targetOrderLineId, 5);
        val orderLineAfter = orderLineApi.get(targetOrderLineId).getBody();
        Assertions.assertNotNull(orderLineAfter);
        assertEquals(5, orderLineAfter.quantity);
    }
}
