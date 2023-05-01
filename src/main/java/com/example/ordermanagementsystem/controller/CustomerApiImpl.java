package com.example.ordermanagementsystem.controller;

import com.example.ordermanagementsystem.dataApiDto.ApiDtoCustomerCreate;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoCustomerGet;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoCustomerGetIncludeOrderThenIncludeOrderLine;
import com.example.ordermanagementsystem.mapper.CustomerMapper;
import com.example.ordermanagementsystem.service.CustomerService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

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
    @RequestMapping(value = "get-include-order-then-include-order-line", method = RequestMethod.GET)
    public ResponseEntity<ApiDtoCustomerGetIncludeOrderThenIncludeOrderLine> getIncludeOrderThenIncludeOrderLine(UUID id) {
        val customerDomain = customerService.getIncludeOrderThenIncludeOrderLine(id);
        val customerDto = customerMapper.domainToGetDtoIncludeOrderThenIncludeOrderLine(customerDomain);
        return ResponseEntity.ok(customerDto);
    }


    @Override
    @Operation(
            description = """
            <p>Create Customer</p>
            """
    )
    @ApiResponse(
            responseCode = "200",
            description = "Returns the saved customer object",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(example = """
                            {
                                "id": "1111c873-f2cb-4914-be2d-fade5fbab0db",
                                "registrationCode": "5707-4f1c",
                                "fullName": "Sofia Ulma",
                                "email": "sofia.ulma@example.com",
                                "phoneNumber": "517-282-9481"
                            }
                            """)
            )
    )
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<ApiDtoCustomerGet> createCustomer(@Valid @RequestBody ApiDtoCustomerCreate dto) {
        val domainIn = customerMapper.createDtoToDomain(dto);
        val domainSaved = customerService.save(domainIn);
        return ResponseEntity.ok(customerMapper.domainToGetDto(domainSaved));
    }
}
