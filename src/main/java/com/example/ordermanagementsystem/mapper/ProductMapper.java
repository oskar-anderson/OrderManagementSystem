package com.example.ordermanagementsystem.mapper;

import com.example.ordermanagementsystem.dataApiDto.ApiDtoCustomerCreate;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoProductCreate;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoProductGet;
import com.example.ordermanagementsystem.dataDomain.DomainProduct;

public class ProductMapper {

    public ApiDtoProductGet domainToApiDto(DomainProduct domain) {
        return ApiDtoProductGet.builder()
                .id(domain.getId())
                .name(domain.getName())
                .skuCode(domain.getSkuCode())
                .unitPrice(domain.getUnitPrice())
                .startDate(domain.getStartDate())
                .endDate(domain.getEndDate())
                .build();
    }

    public DomainProduct apiDtoCreateToDomain(ApiDtoProductCreate dto) {
        return DomainProduct.builder()
                .id(dto.getId())
                .name(dto.getName())
                .skuCode(dto.getSkuCode())
                .unitPrice(dto.getUnitPrice())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
    }
}
