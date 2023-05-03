package com.example.ordermanagementsystem.controller;


import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderCreate;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderGet;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderGetIncludeOrderLines;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderApi {
    ResponseEntity<List<ApiDtoOrderGet>> getOrders();


    ResponseEntity<ApiDtoOrderGet> getOrder(UUID id);

    ResponseEntity<ApiDtoOrderGetIncludeOrderLines> getOrderIncludeOrderLine(UUID id);

    ResponseEntity<ApiDtoOrderGet> createOrder(ApiDtoOrderCreate dto);

    ResponseEntity<List<ApiDtoOrderGetIncludeOrderLines>> getOrdersBetweenDates(OffsetDateTime date1Inclusive, OffsetDateTime date2NonInclusive);
    ResponseEntity<ApiDtoOrderGet> markSubmitted(UUID id, OffsetDateTime submittedDate);
}
