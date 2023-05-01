package com.example.ordermanagementsystem.controller;

import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderLineCreate;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderLineGet;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface OrderLineApi {

    ResponseEntity<Void> setQuantity(UUID id, int quantity);

    ResponseEntity<ApiDtoOrderLineGet> addOrderLine(ApiDtoOrderLineCreate orderLine);
}
