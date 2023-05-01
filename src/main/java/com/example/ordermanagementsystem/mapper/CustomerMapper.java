package com.example.ordermanagementsystem.mapper;

import com.example.ordermanagementsystem.dataApiDto.ApiDtoCustomerCreate;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoCustomerGet;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoCustomerGetIncludeOrderThenIncludeOrderLine;
import com.example.ordermanagementsystem.dataDomain.DomainCustomer;
import lombok.val;

import java.util.UUID;

public class CustomerMapper {

    public DomainCustomer createDtoToDomain(ApiDtoCustomerCreate dto) {
        return DomainCustomer.builder()
                .id(UUID.randomUUID())
                .registrationCode(dto.getRegistrationCode())
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public ApiDtoCustomerGet domainToGetDto(DomainCustomer domain) {
        return ApiDtoCustomerGet.builder()
                .id(domain.getId())
                .registrationCode(domain.getRegistrationCode())
                .fullName(domain.getFullName())
                .email(domain.getEmail())
                .phoneNumber(domain.getPhoneNumber())
                .build();
    }

    public ApiDtoCustomerGetIncludeOrderThenIncludeOrderLine domainToGetDtoIncludeOrderThenIncludeOrderLine(DomainCustomer domain) {
        val orderMapper = new OrderMapper();
        return ApiDtoCustomerGetIncludeOrderThenIncludeOrderLine.builder()
                .id(domain.getId())
                .registrationCode(domain.getRegistrationCode())
                .fullName(domain.getFullName())
                .email(domain.getEmail())
                .phoneNumber(domain.getPhoneNumber())
                .customerOrders(domain.getCustomerOrders().stream().map(orderMapper::domainToApiDtoIncludeOrderLines).toList())
                .build();
    }
}
