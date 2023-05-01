package com.example.ordermanagementsystem.mapper;

import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderCreate;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderGet;
import com.example.ordermanagementsystem.dataApiDto.ApiDtoOrderGetIncludeOrderLines;
import com.example.ordermanagementsystem.dataDomain.DomainOrder;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.val;

import java.time.OffsetDateTime;
import java.util.UUID;

public class OrderMapper {

    public ApiDtoOrderGet domainToApiDto(DomainOrder domain) {
        return ApiDtoOrderGet.builder()
                .id(domain.getId())
                .submittedDate(domain.getSubmittedDate())
                .customerId(domain.getCustomerId())
                .build();
    }

    public ApiDtoOrderGetIncludeOrderLines domainToApiDtoIncludeOrderLines(DomainOrder domain) {
        val orderLineMapper = new OrderLineMapper();
        return ApiDtoOrderGetIncludeOrderLines.builder()
                .id(domain.getId())
                .submittedDate(domain.getSubmittedDate())
                .customerId(domain.getCustomerId())
                .orderLines(domain.getOrderLines().stream().map(orderLineMapper::domainToApiDto).toList())
                .build();
    }

    public DomainOrder apiDtoCreateToDomain(ApiDtoOrderCreate dto) {
        return DomainOrder.builder()
                .id(UUID.randomUUID())
                .submittedDate(dto.getSubmittedDate())
                .customerId(dto.getCustomerId())
                .build();
    }
}
