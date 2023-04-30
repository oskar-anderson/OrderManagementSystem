package com.example.ordermanagementsystem.controller;

import com.example.ordermanagementsystem.dataApiDto.ApiDtoProductCreate;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoProductGet;
import com.example.ordermanagementsystem.mapper.ProductMapper;
import com.example.ordermanagementsystem.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductImpl implements Product {

    private final ProductMapper mapper = new ProductMapper();

    private final ProductService repo;

    @Override
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public ResponseEntity<ApiDtoProductGet> get(@RequestParam UUID id) {
        val domain = repo.get(id);
        val dtoResponse = mapper.domainToApiDto(domain);
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
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<ApiDtoProductGet> create(@RequestBody ApiDtoProductCreate dto) {
        val domain = mapper.apiDtoCreateToDomain(dto);
        val domainSaved = repo.save(domain);
        val dtoResponse = mapper.domainToApiDto(domainSaved);
        return ResponseEntity.ok(dtoResponse);
    }
}
