package com.example.ordermanagementsystem.dataDomain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Table(name = "order_line")
public class DomainOrderLine {

    @Id
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
    @Column(name = "order_id")
    private UUID orderId;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private DomainOrder order;

    /**
     * This is the product id today, not at point of order.
     */
    @NotNull
    @Column(name = "product_id")
    private UUID productId;


    /**
     * This is the product today, not at point of order.
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private DomainProduct product;
}
