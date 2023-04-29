package com.example.ordermanagementsystem.dataApiDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ApiDtoOrderLineCreate {

    /**
     * It is not necessary to include id field. If id is not provided a random one will be generated.
     */
    @Null
    private UUID id = UUID.randomUUID();

    @NotNull
    private Integer quantity;

    @NotNull
    private float productUnitPrice;

    @NotNull
    private String productSKU;

    @NotNull
    private UUID productId;
}
