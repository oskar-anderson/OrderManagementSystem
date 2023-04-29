package com.example.ordermanagementsystem.dataDomain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Table(name = "product")
public class DomainProduct {

    @Id
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
    private String unitPrice;

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

    @OneToMany(
            mappedBy = DomainOrderLine.Fields.product
    )
    @Builder.Default
    private List<DomainOrderLine> orderLines = new ArrayList<>();
}
