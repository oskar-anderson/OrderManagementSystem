package com.example.ordermanagementsystem.controller;

import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderLineGet;
import com.example.ordermanagementsystem.mapper.OrderLineMapper;
import com.example.ordermanagementsystem.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order-line/")
public class OrderLineImpl implements OrderLine {

    private final OrderLineMapper mapper = new OrderLineMapper();

    private final OrderLineService repo;

    @RequestMapping(value = "set-quantity", method = RequestMethod.PATCH)
    public ResponseEntity<ApiDtoOrderLineGet> setQuantity(@RequestParam UUID id, @RequestParam int quantity) {
        val domainOrderLine = repo.setQuantity(id, quantity);
        val apiDtoOrderLine = mapper.domainToApiDto(domainOrderLine);
        return ResponseEntity.ok(apiDtoOrderLine);
    }
}
