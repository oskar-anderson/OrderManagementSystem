package com.example.ordermanagementsystem.dataApiDto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
public class ApiDtoProductGetIncludeOrderLineThenIncludeOrder {

    @NotNull
    public UUID id;

    @NotNull
    public String name;

    @NotNull
    public String skuCode;

    @NotNull
    public String unitPrice;

    @NotNull
    public OffsetDateTime startDate;

    @NotNull
    public OffsetDateTime endDate;

    @NotNull
    public List<ApiDtoOrderLineGetIncludeOrder> orderLines = new ArrayList<>();
}
