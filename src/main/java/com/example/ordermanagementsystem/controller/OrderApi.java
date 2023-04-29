package com.example.ordermanagementsystem.controller;


import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderCreate;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderGet;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderGetIncludeOrderLines;
import lombok.val;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderApi {
    public ResponseEntity<List<ApiDtoOrderGet>> getOrders();


    public ResponseEntity<ApiDtoOrderGet> getOrder(UUID id);

    public ResponseEntity<ApiDtoOrderGet> createOrder(ApiDtoOrderCreate dto);

    public ResponseEntity<List<ApiDtoOrderGetIncludeOrderLines>> getOrdersBetweenDates(OffsetDateTime date1Inclusive, OffsetDateTime date2NonInclusive);
    public ResponseEntity<ApiDtoOrderGet> markSubmitted(UUID id, OffsetDateTime submittedDate);
}
