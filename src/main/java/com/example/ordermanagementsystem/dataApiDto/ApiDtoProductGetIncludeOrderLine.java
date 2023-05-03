package com.example.ordermanagementsystem.dataApiDto;

import com.example.ordermanagementsystem.dataDomain.DomainOrderLine;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
public class ApiDtoProductGetIncludeOrderLine {

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

    @NotNull
    public List<ApiDtoOrderLineGet> orderLines = new ArrayList<>();
}
