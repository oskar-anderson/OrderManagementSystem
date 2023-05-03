package com.example.ordermanagementsystem.dataDal;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DalOrderLine {

    @Builder.Default
    private UUID id = UUID.randomUUID();

    @NotNull
    private Integer quantity;

    /**
     * Product unit price at point of sale.
     */
    @NotNull
    private float productUnitPrice;


    /**
     * Product SKU code at point of sale.
     */
    @NotNull
    private String productSKU;


    // ---------------------- reference navigational properties along with physical FK property block ----------------------

    @NotNull
    private UUID orderId;

    @Nullable
    private DalOrder order = null;

    /**
     * This is the id reference to product today, not at point of order.
     */
    @NotNull
    private UUID productId;


    /**
     * This is the product today, not at point of order.
     */
    @Nullable
    private DalProduct product = null;
}
