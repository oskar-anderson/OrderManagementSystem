package com.example.ordermanagementsystem.api;

import com.example.ordermanagementsystem.Startup;
import com.example.ordermanagementsystem.controller.CustomerApi;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoCustomerCreate;
import com.example.ordermanagementsystem.service.CustomerService;
import com.example.ordermanagementsystem.util.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@AutoConfigureMockMvc
@SpringBootTest
public class CustomerApiTest {

    @Autowired
    private CustomerApi customerApi;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Startup startup;
    @Test
    public void createCustomer_willCreateId() throws IOException {
        startup.seedData(false);

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
    public void getOrdersByCustomer() throws IOException {
        startup.seedData(false);

        val response = customerApi.getIncludeOrderThenIncludeOrderLine(UUID.fromString("8d1bc873-f2cb-4914-be2d-fade5fbab0db"));
        assert(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertEquals(4, response.getBody().customerOrders.size());
    }


    @Test
    public void createCustomer_willFailWhenEmailIsInvalid() throws Exception {
        val draftCustomer = ApiDtoCustomerCreate.builder()
                .registrationCode("a40c-52e2")
                .fullName("John Smith")
                .email("john.smith")
                .phoneNumber("526-523-5204")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer/create")
                        .content(Json.Serialize(draftCustomer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createCustomer_willFailWhenRegistrationCodeIsInvalid() throws Exception {
        val draftCustomer = ApiDtoCustomerCreate.builder()
                .registrationCode("a40c-mmmm")
                .fullName("John Smith")
                .email("john.smith")
                .phoneNumber("526-523-5204")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer/create")
                        .content(Json.Serialize(draftCustomer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createCustomer_willFailWhenFullNameIsLargerThan64Chars() throws Exception {
        val draftCustomer = ApiDtoCustomerCreate.builder()
                .registrationCode("a40c-mmmm")
                .fullName("John Smith-very-long-invalid-name-" + "a".repeat(64))
                .email("john.smith")
                .phoneNumber("526-523-5204")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer/create")
                        .content(Json.Serialize(draftCustomer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
