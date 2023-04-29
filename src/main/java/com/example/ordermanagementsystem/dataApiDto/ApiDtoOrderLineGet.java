package com.example.ordermanagementsystem.dataApiDto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public class ApiDtoOrderLineGet {

    @NotNull
    public UUID id;

    @NotNull
    private Integer quantity;

    @NotNull
    private float productUnitPrice;

    @NotNull
    private String productSKU;

    @NotNull
    private UUID productId;
}
