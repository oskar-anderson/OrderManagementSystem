package com.example.ordermanagementsystem.controller;

import com.example.ordermanagementsystem.dataApiDto.ApiDtoProductCreate;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoProductGet;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoProductGetIncludeOrderLineThenIncludeOrder;
import com.example.ordermanagementsystem.mapper.ProductMapper;
import com.example.ordermanagementsystem.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductApiImpl implements ProductApi {

    private final ProductMapper mapper = new ProductMapper();

    private final ProductService repo;

    @Override
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public ResponseEntity<ApiDtoProductGet> get(@RequestParam UUID id) {
        val domainProduct = repo.get(id);
        if (domainProduct == null) {
            return ResponseEntity.notFound().build();
        }
        val dtoResponse = mapper.domainToApiDto(domainProduct);
        return ResponseEntity.ok(dtoResponse);
    }

    @Override
    @RequestMapping(value = "get-all", method = RequestMethod.GET)
    public ResponseEntity<List<ApiDtoProductGet>> getAll() {
        val domain = repo.getAll();
        val dtoResponse = domain.stream().map(mapper::domainToApiDto).toList();
        return ResponseEntity.ok(dtoResponse);
    }

    @Override
    @RequestMapping(value = "get-include-order-lines-then-include-order", method = RequestMethod.GET)
    public ResponseEntity<ApiDtoProductGetIncludeOrderLineThenIncludeOrder> getIncludeOrderLinesThenIncludeOrder(@RequestParam UUID id) {
        val orderLinesDomain = repo.getIncludeOrderLinesThenIncludeOrder(id);
        val orderLinesResponse = mapper.domainToApiDtoIncludeOrderLineThenIncludeOrder(orderLinesDomain);
        return ResponseEntity.ok(orderLinesResponse);
    }

    @Override
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<ApiDtoProductGet> create(@Valid @RequestBody ApiDtoProductCreate dto) {
        val domain = mapper.apiDtoCreateToDomain(dto);
        val domainSaved = repo.save(domain);
        val dtoResponse = mapper.domainToApiDto(domainSaved);
        return ResponseEntity.ok(dtoResponse);
    }

    @Override
    @RequestMapping(value = "soft-delete", method = RequestMethod.POST)
    public ResponseEntity<ApiDtoProductGet> setExpired(@RequestParam UUID id) {
        val domainSaved = repo.softDelete(id, OffsetDateTime.now());
        val dtoResponse = mapper.domainToApiDto(domainSaved);
        return ResponseEntity.ok(dtoResponse);
    }
}
