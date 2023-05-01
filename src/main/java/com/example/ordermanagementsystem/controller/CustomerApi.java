package com.example.ordermanagementsystem.controller;

import com.example.ordermanagementsystem.dataApiDto.ApiDtoCustomerCreate;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoCustomerGet;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoCustomerGetIncludeOrderThenIncludeOrderLine;
import lombok.val;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CustomerApi {

    public ResponseEntity<List<ApiDtoCustomerGet>> getCustomers();


    public ResponseEntity<ApiDtoCustomerGet> getCustomer(UUID id);

    public ResponseEntity<ApiDtoCustomerGetIncludeOrderThenIncludeOrderLine> getIncludeOrderThenIncludeOrderLine(UUID id);


    public ResponseEntity<ApiDtoCustomerGet> createCustomer(ApiDtoCustomerCreate dto);
}
