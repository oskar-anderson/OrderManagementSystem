package com.example.ordermanagementsystem.dataDal;

import jakarta.annotation.Nullable;
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
public class DalOrder {
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @Nullable
    private OffsetDateTime submittedDate;


    // ---------------------- reference navigational properties along with physical FK property block ----------------------

    /**
     * Deleted user will be null
     */
    @Nullable
    private UUID customerId;

    @Nullable
    private DalCustomer customer = null;


    // ---------------------- collection navigational (inverse) properties block ----------------------

    @Builder.Default
    private Set<DalOrderLine> orderLines = new HashSet<>();
}
