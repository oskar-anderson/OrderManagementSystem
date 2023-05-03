package com.example.ordermanagementsystem.controller;

import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderLineCreate;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderLineGet;
import com.example.ordermanagementsystem.mapper.OrderLineMapper;
import com.example.ordermanagementsystem.service.OrderLineService;
import com.example.ordermanagementsystem.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order-line/")
public class OrderLineApiImpl implements OrderLineApi {

    private final OrderLineMapper mapper = new OrderLineMapper();

    private final OrderLineService repo;
    private final OrderService orderRepo;

    @Override
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public ResponseEntity<ApiDtoOrderLineGet> get(UUID id) {
        val orderLineDomain = repo.getOrderLine(id);
        if (orderLineDomain == null) {
            return ResponseEntity.notFound().build();
        }
        val orderLineDto = mapper.domainToApiDto(orderLineDomain);
        return ResponseEntity.ok(orderLineDto);
    }

    @Override
    @RequestMapping(value = "set-quantity", method = RequestMethod.PATCH)
    public ResponseEntity<Void> setQuantity(@RequestParam UUID id, @RequestParam @Min(0) int quantity) {
        if (quantity == 0) {
            repo.delete(id);
            return ResponseEntity.ok().build();
        }
        repo.setQuantity(id, quantity);
        return ResponseEntity.ok().build();
    }

    @Override
    @RequestMapping(value = "addOrderLine", method = RequestMethod.PATCH)
    public ResponseEntity<ApiDtoOrderLineGet> addOrderLine(@Valid  @RequestBody ApiDtoOrderLineCreate orderLine) {
        if (orderRepo.getIncludeOrderLines(orderLine.orderId).getOrderLines().stream().anyMatch(ol -> ol.getProductId().equals(orderLine.productId))) {
            throw new IllegalStateException("Not going to happen! Order.orderLines already contains Product that is being added by API argument.");
        }
        val orderLineDomain = mapper.apiDtoCreateToDomain(orderLine);
        val orderLineSaved = repo.save(orderLineDomain);
        val orderLineResponse = mapper.domainToApiDto(orderLineSaved);
        return ResponseEntity.ok(orderLineResponse);
    }
}
