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
    public String name;

    @NotNull
    public String skuCode;

    @NotNull
    public Float unitPrice;

    @NotNull
    public OffsetDateTime startDate;

    @NotNull
    public OffsetDateTime endDate;
}
