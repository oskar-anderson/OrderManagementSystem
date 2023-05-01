package com.example.ordermanagementsystem.mapper;

import com.example.ordermanagementsystem.dataApiDto.*;
import com.example.ordermanagementsystem.dataDomain.DomainProduct;
import lombok.val;

import java.util.UUID;

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
                .id(UUID.randomUUID())
                .name(dto.getName())
                .skuCode(dto.getSkuCode())
                .unitPrice(dto.getUnitPrice())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
    }

    public ApiDtoProductGetIncludeOrderLine domainToApiDtoIncludeOrderLine(DomainProduct domain) {
        val orderLineMapper = new OrderLineMapper();
        return ApiDtoProductGetIncludeOrderLine.builder()
                .id(domain.getId())
                .name(domain.getName())
                .skuCode(domain.getSkuCode())
                .unitPrice(domain.getUnitPrice())
                .startDate(domain.getStartDate())
                .endDate(domain.getEndDate())
                .orderLines(domain.getOrderLines().stream().map(orderLineMapper::domainToApiDto).toList())
                .build();
    }

    public ApiDtoProductGetIncludeOrderLineThenIncludeOrder domainToApiDtoIncludeOrderLineThenIncludeOrder(DomainProduct domain) {
        val orderLineMapper = new OrderLineMapper();
        return ApiDtoProductGetIncludeOrderLineThenIncludeOrder.builder()
                .id(domain.getId())
                .name(domain.getName())
                .skuCode(domain.getSkuCode())
                .unitPrice(domain.getUnitPrice())
                .startDate(domain.getStartDate())
                .endDate(domain.getEndDate())
                .orderLines(domain.getOrderLines().stream().map(orderLineMapper::domainToApiDtoIncludeOrder).toList())
                .build();
    }
}
