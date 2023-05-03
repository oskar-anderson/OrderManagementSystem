package com.example.ordermanagementsystem.controller;

import com.example.ordermanagementsystem.dataApiDto.ApiDtoCustomerCreate;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoCustomerGet;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoCustomerGetIncludeOrderThenIncludeOrderLine;
import lombok.val;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CustomerApi {

    ResponseEntity<List<ApiDtoCustomerGet>> getCustomers();


    ResponseEntity<ApiDtoCustomerGet> getCustomer(UUID id);

    ResponseEntity<ApiDtoCustomerGetIncludeOrderThenIncludeOrderLine> getIncludeOrderThenIncludeOrderLine(UUID id);


    ResponseEntity<ApiDtoCustomerGet> createCustomer(ApiDtoCustomerCreate dto);
}
