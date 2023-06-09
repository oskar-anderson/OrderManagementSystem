package com.example.ordermanagementsystem.dataApiDto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public class ApiDtoOrderLineGet {

    @NotNull
    public UUID id;

    @NotNull
    public Integer quantity;

    @NotNull
    public float productUnitPrice;

    @NotNull
    public String productSKU;

    @NotNull
    public UUID productId;

    @NotNull
    public UUID orderId;
}
