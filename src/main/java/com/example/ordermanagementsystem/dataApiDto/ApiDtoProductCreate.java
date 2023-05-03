package com.example.ordermanagementsystem.dataApiDto;

import com.example.ordermanagementsystem.dataDomain.DomainOrderLine;
import jakarta.annotation.Nullable;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class ApiDtoProductCreate {
    @NotNull
    @Size(min = 2, max = 255)
    public String name;

    @NotNull
    @Pattern(regexp = "\\b[0-9a-f]{4}-[0-9a-f]{4}\\b", message = "Invalid product SKU code pattern!")
    public String skuCode;

    @NotNull
    @Positive
    public Float unitPrice;

    @NotNull
    public OffsetDateTime startDate = OffsetDateTime.now();

    @NotNull
    public OffsetDateTime endDate = OffsetDateTime.MAX;
}
