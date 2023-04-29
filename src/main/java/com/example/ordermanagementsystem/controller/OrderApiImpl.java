package com.example.ordermanagementsystem.controller;

import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderCreate;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderGet;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderGetIncludeOrderLines;
import com.example.ordermanagementsystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.ordermanagementsystem.mapper.OrderMapper;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderApiImpl {

    private final OrderMapper orderMapper = new OrderMapper();

    private final OrderService orderService;

    public ResponseEntity<List<ApiDtoOrderGet>> getOrders() {
        val domainOrders = orderService.getAll();
        val dtoOrders = domainOrders.stream().map(orderMapper::domainToApiDto).toList();
        return ResponseEntity.ok(dtoOrders);
    }


    public ResponseEntity<ApiDtoOrderGet> getOrder(UUID id) {
        val domainOrder = orderService.get(id);
        val dtoOrder = orderMapper.domainToApiDto(domainOrder);
        return ResponseEntity.ok(dtoOrder);
    }

    public ResponseEntity<ApiDtoOrderGet> createOrder(ApiDtoOrderCreate dto) {
        val domainOrder = orderMapper.apiDtoCreateToDomain(dto);
        val saveOrder = orderService.save(domainOrder);
        val saveOrderDto = orderMapper.domainToApiDto(saveOrder);
        return ResponseEntity.ok(saveOrderDto);
    }

    public ResponseEntity<List<ApiDtoOrderGetIncludeOrderLines>> getOrdersBetweenDates(OffsetDateTime date1Inclusive, OffsetDateTime date2NonInclusive) {
        val domainOrders = orderService.getAllBetweenIncludeOrderLines(date1Inclusive, date2NonInclusive);
        val dtoOrders = domainOrders.stream().map(orderMapper::domainToApiDtoIncludeOrderLines).toList();
        return ResponseEntity.ok(dtoOrders);
    }

    public ResponseEntity<ApiDtoOrderGet> markSubmitted(UUID id, OffsetDateTime submittedDate) {
        val domainOrder = orderService.get(id);
        domainOrder.setSubmittedDate(submittedDate);
        val domainOrderAfterSaving = orderService.save(domainOrder);
        val apiDtoOrderAfterSaving = orderMapper.domainToApiDto(domainOrderAfterSaving);
        return ResponseEntity.ok(apiDtoOrderAfterSaving);
    }
}
