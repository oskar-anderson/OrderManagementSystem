package com.example.ordermanagementsystem.dataApiDto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ApiDtoOrderLineCreate {

    @NotNull
    @Min(0)
    public Integer quantity;

    @NotNull
    @Positive
    public float productUnitPrice;

    @NotNull
    @Pattern(regexp = "\\b[0-9a-f]{4}-[0-9a-f]{4}\\b", message = "Invalid product SKU code pattern!")
    public String productSKU;

    @NotNull
    public UUID productId;

    @NotNull
    public UUID orderId;
}
