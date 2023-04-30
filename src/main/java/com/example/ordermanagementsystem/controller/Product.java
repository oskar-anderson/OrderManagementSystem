package com.example.ordermanagementsystem.controller;

import com.example.ordermanagementsystem.dataApiDto.ApiDtoCustomerCreate;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoProductCreate;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoProductGet;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface Product {

    ResponseEntity<ApiDtoProductGet> get(UUID id);

    ResponseEntity<List<ApiDtoProductGet>> getAll();

    ResponseEntity<ApiDtoProductGet> create(ApiDtoProductCreate dto);
}
