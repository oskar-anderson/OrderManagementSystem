package com.example.ordermanagementsystem.controller;

import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderCreate;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderGet;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderGetIncludeOrderLines;
import com.example.ordermanagementsystem.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ordermanagementsystem.mapper.OrderMapper;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order/")
public class OrderApiImpl implements OrderApi {

    private final OrderMapper orderMapper = new OrderMapper();

    private final OrderService orderService;

    @Override
    @RequestMapping(value = "get-all", method = RequestMethod.GET)
    public ResponseEntity<List<ApiDtoOrderGet>> getOrders() {
        val domainOrders = orderService.getAll();
        val dtoOrders = domainOrders.stream().map(orderMapper::domainToApiDto).toList();
        return ResponseEntity.ok(dtoOrders);
    }

    @Override
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public ResponseEntity<ApiDtoOrderGet> getOrder(@RequestParam UUID id) {
        val domainOrder = orderService.get(id);
        if (domainOrder == null) {
            return ResponseEntity.notFound().build();
        }
        val dtoOrder = orderMapper.domainToApiDto(domainOrder);
        return ResponseEntity.ok(dtoOrder);
    }

    @Override
    @RequestMapping(value = "get-include-order-line", method = RequestMethod.GET)
    public ResponseEntity<ApiDtoOrderGetIncludeOrderLines> getOrderIncludeOrderLine(@RequestParam UUID id) {
        val domainOrder = orderService.getIncludeOrderLines(id);
        val dtoOrder = orderMapper.domainToApiDtoIncludeOrderLines(domainOrder);
        return ResponseEntity.ok(dtoOrder);
    }


    @Override
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<ApiDtoOrderGet> createOrder(@Valid  @RequestBody ApiDtoOrderCreate dto) {
        val domainOrder = orderMapper.apiDtoCreateToDomain(dto);
        val saveOrder = orderService.save(domainOrder);
        val saveOrderDto = orderMapper.domainToApiDto(saveOrder);
        return ResponseEntity.ok(saveOrderDto);
    }


    @Override
    @RequestMapping(value = "get-orders-between-dates", method = RequestMethod.GET)
    public ResponseEntity<List<ApiDtoOrderGetIncludeOrderLines>> getOrdersBetweenDates(@RequestParam OffsetDateTime date1Inclusive, @RequestParam OffsetDateTime date2NonInclusive) {
        val domainOrders = orderService.getAllBetweenIncludeOrderLines(date1Inclusive, date2NonInclusive);
        val dtoOrders = domainOrders.stream().map(orderMapper::domainToApiDtoIncludeOrderLines).toList();
        return ResponseEntity.ok(dtoOrders);
    }


    @Override
    @RequestMapping(value = "mark-submitted", method = RequestMethod.PATCH)
    public ResponseEntity<ApiDtoOrderGet> markSubmitted(@RequestParam UUID id, @RequestParam OffsetDateTime submittedDate) {
        val domainOrderAfterSaving = orderService.markSubmitted(id, submittedDate);
        val apiDtoOrderAfterSaving = orderMapper.domainToApiDto(domainOrderAfterSaving);
        return ResponseEntity.ok(apiDtoOrderAfterSaving);
    }
}
