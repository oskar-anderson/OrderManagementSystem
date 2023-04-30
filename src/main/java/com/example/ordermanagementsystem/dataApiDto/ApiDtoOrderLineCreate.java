package com.example.ordermanagementsystem.dataApiDto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ApiDtoOrderLineCreate {

    /**
     * It is not necessary to include id field. If id is not provided a random one will be generated.
     */
    @Nullable
    public UUID id = UUID.randomUUID();

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
