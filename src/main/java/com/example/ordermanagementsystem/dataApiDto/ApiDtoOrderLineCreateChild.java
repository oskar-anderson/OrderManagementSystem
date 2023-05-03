package com.example.ordermanagementsystem.dataApiDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ApiDtoOrderLineCreateChild {

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
}
