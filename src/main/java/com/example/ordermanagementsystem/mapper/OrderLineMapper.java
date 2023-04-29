package com.example.ordermanagementsystem.mapper;

import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderGet;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderLineGet;
import com.example.ordermanagementsystem.dataDomain.DomainOrder;
import com.example.ordermanagementsystem.dataDomain.DomainOrderLine;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class OrderLineMapper {

    public ApiDtoOrderLineGet domainToApiDto(DomainOrderLine domain) {
        return ApiDtoOrderLineGet.builder()
                .id(domain.getId())
                .quantity(domain.getQuantity())
                .productUnitPrice(domain.getProductUnitPrice())
                .productSKU(domain.getProductSKU())
                .productId(domain.getProductId())
                .build();
    }
}
