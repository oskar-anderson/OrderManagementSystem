package com.example.ordermanagementsystem.controller;

import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderLineGet;
import com.example.ordermanagementsystem.mapper.OrderLineMapper;
import com.example.ordermanagementsystem.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderLineImpl implements OrderLine {

    private final OrderLineMapper mapper = new OrderLineMapper();

    private final OrderLineService repo;

    public ResponseEntity<ApiDtoOrderLineGet> setQuantity(UUID id, int quantity) {
        val domainOrderLine = repo.setQuantity(id, quantity);
        val apiDtoOrderLine = mapper.domainToApiDto(domainOrderLine);
        return ResponseEntity.ok(apiDtoOrderLine);
    }
}
