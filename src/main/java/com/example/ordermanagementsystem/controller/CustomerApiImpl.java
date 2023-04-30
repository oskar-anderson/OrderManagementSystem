package com.example.ordermanagementsystem.controller;

import com.example.ordermanagementsystem.dataApiDto.ApiDtoCustomerCreate;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoCustomerGet;
import com.example.ordermanagementsystem.mapper.CustomerMapper;
import com.example.ordermanagementsystem.service.CustomerService;
import com.example.ordermanagementsystem.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer/")
public class CustomerApiImpl implements CustomerApi {

    private final CustomerMapper customerMapper = new CustomerMapper();

    private final CustomerService customerService;

    @Override
    @RequestMapping(value = "get-all", method = RequestMethod.GET)
    public ResponseEntity<List<ApiDtoCustomerGet>> getCustomers() {
        val domain = customerService.getAll();
        return ResponseEntity.ok(domain.stream().map(customerMapper::domainToGetDto).toList());
    }


    @Override
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public ResponseEntity<ApiDtoCustomerGet> getCustomer(@RequestParam UUID id) {
        val domain = customerService.get(id);
        return ResponseEntity.ok(customerMapper.domainToGetDto(domain));
    }

    @Override
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<ApiDtoCustomerGet> createCustomer(@RequestBody ApiDtoCustomerCreate dto) {
        val domainIn = customerMapper.createDtoToDomain(dto);
        val domainSaved = customerService.save(domainIn);
        return ResponseEntity.ok(customerMapper.domainToGetDto(domainSaved));
    }
}
