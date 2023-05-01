package com.example.ordermanagementsystem.controller;

import com.example.ordermanagementsystem.dataApiDto.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ProductApi {

    ResponseEntity<ApiDtoProductGet> get(UUID id);

    ResponseEntity<List<ApiDtoProductGet>> getAll();

    ResponseEntity<ApiDtoProductGetIncludeOrderLineThenIncludeOrder> getIncludeOrderLinesThenIncludeOrder(UUID id);

    ResponseEntity<ApiDtoProductGet> create(ApiDtoProductCreate dto);

    ResponseEntity<ApiDtoProductGet> setExpired(UUID id);
}
