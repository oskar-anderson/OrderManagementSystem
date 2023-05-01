package com.example.ordermanagementsystem.api;

import com.example.ordermanagementsystem.Startup;
import com.example.ordermanagementsystem.controller.CustomerApi;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoCustomerCreate;
import com.example.ordermanagementsystem.service.CustomerService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerApiTest {

    @Autowired
    private CustomerApi customerApi;

    @Autowired
    private CustomerService customerService;

    private static final Logger log = LoggerFactory.getLogger(CustomerApiTest.class);

    @Test
    public void createCustomer_willCreateId() {
        val draftCustomer = ApiDtoCustomerCreate.builder()
                .registrationCode("a40c-52e2")
                .fullName("John Smith")
                .email("john.smith@example.com")
                .phoneNumber("526-523-5204")
                .build();

        val response = customerApi.createCustomer(draftCustomer);
        assert(response.getStatusCode().is2xxSuccessful());
        val createdCustomer = response.getBody();
        assertNotNull(createdCustomer);
        assertNotNull(createdCustomer.id);
    }

    @Test
    public void getOrdersByCustomer() {
        val response = customerApi.getIncludeOrderThenIncludeOrderLine(UUID.fromString("8d1bc873-f2cb-4914-be2d-fade5fbab0db"));
        assert(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals(response.getBody().customerOrders.size(), 4);
    }

    @Test
    public void getOrdersByCustomer2() {
        customerService.getIncludeOrderThenIncludeOrderLine2(UUID.fromString("8d1bc873-f2cb-4914-be2d-fade5fbab0db"));
    }


    @Test
    public void createCustomer_willFailWhenEmailIsInvalid() {
        val draftCustomer = ApiDtoCustomerCreate.builder()
                .registrationCode("a40c-52e2")
                .fullName("John Smith")
                .email("john.smith")
                .phoneNumber("526-523-5204")
                .build();

        val response = customerApi.createCustomer(draftCustomer);
        assert(response.getStatusCode().is4xxClientError());
    }

    @Test
    public void createCustomer_willFailWhenRegistrationCodeIsInvalid() {
        val draftCustomer = ApiDtoCustomerCreate.builder()
                .registrationCode("a40c-mmmm")
                .fullName("John Smith")
                .email("john.smith")
                .phoneNumber("526-523-5204")
                .build();

        val response = customerApi.createCustomer(draftCustomer);
        assert(response.getStatusCode().is4xxClientError());
    }

    @Test
    public void createCustomer_willFailWhenFullNameIsLargerThan64Chars() {
        val draftCustomer = ApiDtoCustomerCreate.builder()
                .registrationCode("a40c-mmmm")
                .fullName("John Smith-very-long-invalid-name-" + "a".repeat(64))
                .email("john.smith")
                .phoneNumber("526-523-5204")
                .build();

        val response = customerApi.createCustomer(draftCustomer);
        assert(response.getStatusCode().is4xxClientError());
    }
}
