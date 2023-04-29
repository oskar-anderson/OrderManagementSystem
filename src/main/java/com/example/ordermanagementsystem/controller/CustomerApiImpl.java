package com.example.ordermanagementsystem.controller;

import com.example.ordermanagementsystem.dataApiDto.ApiDtoCustomerCreate;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoCustomerGet;
import com.example.ordermanagementsystem.mapper.CustomerMapper;
import com.example.ordermanagementsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerApiImpl implements CustomerApi {

    private final CustomerMapper customerMapper = new CustomerMapper();

    private final CustomerService customerService;
    
    @Override
    public ResponseEntity<List<ApiDtoCustomerGet>> getCustomers() {
        val domain = customerService.getAll();
        return ResponseEntity.ok(domain.stream().map(customerMapper::domainToGetDto).toList());
    }


    @Override
    public ResponseEntity<ApiDtoCustomerGet> getCustomer(UUID id) {
        val domain = customerService.get(id);
        return ResponseEntity.ok(customerMapper.domainToGetDto(domain));
    }

    @Override
    public ResponseEntity<ApiDtoCustomerGet> createCustomer(ApiDtoCustomerCreate dto) {
        val domainIn = customerMapper.createDtoToDomain(dto);
        val domainSaved = customerService.save(domainIn);
        return ResponseEntity.ok(customerMapper.domainToGetDto(domainSaved));
    }
}
