package com.example.ordermanagementsystem.dataApiDto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
public class ApiDtoProductGet {

    @NotNull
    public UUID id;

    @NotNull
    private String name;

    @NotNull
    private String skuCode;

    @NotNull
    private String unitPrice;

    @NotNull
    private OffsetDateTime startDate;

    @NotNull
    private OffsetDateTime endDate;
}
