package com.example.ordermanagementsystem.controller;

import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderLineGet;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface OrderLine {

    ResponseEntity<ApiDtoOrderLineGet> setQuantity(UUID id, int quantity);
}
