package com.example.ordermanagementsystem.mapper;

import com.example.ordermanagementsystem.dataApiDto.*;
import com.example.ordermanagementsystem.dataDomain.DomainOrder;
import com.example.ordermanagementsystem.dataDomain.DomainOrderLine;
import jakarta.validation.constraints.NotNull;
import lombok.val;

import java.util.UUID;

public class OrderLineMapper {

    public ApiDtoOrderLineGet domainToApiDto(DomainOrderLine domain) {
        return ApiDtoOrderLineGet.builder()
                .id(domain.getId())
                .quantity(domain.getQuantity())
                .productUnitPrice(domain.getProductUnitPrice())
                .productSKU(domain.getProductSKU())
                .productId(domain.getProductId())
                .orderId(domain.getOrderId())
                .build();
    }

    public ApiDtoOrderLineGetIncludeOrder domainToApiDtoIncludeOrder(DomainOrderLine domain) {
        val domainOrderMapper = new OrderMapper();
        return ApiDtoOrderLineGetIncludeOrder.builder()
                .id(domain.getId())
                .quantity(domain.getQuantity())
                .productUnitPrice(domain.getProductUnitPrice())
                .productSKU(domain.getProductSKU())
                .productId(domain.getProductId())
                .orderId(domain.getOrderId())
                .order(domainOrderMapper.domainToApiDto(domain.getOrder()))
                .build();
    }

    public DomainOrderLine apiDtoCreateToDomain(ApiDtoOrderLineCreate dto) {
        return DomainOrderLine.builder()
                .id(UUID.randomUUID())
                .quantity(dto.getQuantity())
                .productUnitPrice(dto.getProductUnitPrice())
                .productSKU(dto.getProductSKU())
                .productId(dto.getProductId())
                .orderId(dto.getOrderId())
                .build();
    }

    public DomainOrderLine apiDtoCreateChildToDomain(ApiDtoOrderLineCreateChild dto, UUID orderId) {
        return DomainOrderLine.builder()
                .id(UUID.randomUUID())
                .quantity(dto.getQuantity())
                .productUnitPrice(dto.getProductUnitPrice())
                .productSKU(dto.getProductSKU())
                .productId(dto.getProductId())
                .orderId(orderId)
                .build();
    }
}
