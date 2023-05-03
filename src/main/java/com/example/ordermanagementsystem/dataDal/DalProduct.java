package com.example.ordermanagementsystem.dataDal;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DalProduct {


    @Builder.Default
    private UUID id = UUID.randomUUID();

    @NotNull
    private String name;

    /**
     * This cannot be changed
     */
    @NotNull
    private String skuCode;

    @NotNull
    private Float unitPrice;

    /**
     * Order cannot be deleted because OrderLine depends on Product. Order can only be retired by setting the endDate.
     */
    @NotNull
    @Builder.Default
    private OffsetDateTime startDate = OffsetDateTime.now();

    /**
     * Order cannot be deleted because OrderLine depends on Product. Order can only be retired by setting the endDate.
     */
    @NotNull
    @Builder.Default
    private OffsetDateTime endDate = OffsetDateTime.MAX;


    // ---------------------- collection navigational (inverse) properties block ----------------------

    @Builder.Default
    private Set<DalOrderLine> orderLines = new HashSet<>();

}
