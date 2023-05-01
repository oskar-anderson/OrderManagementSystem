package com.example.ordermanagementsystem.dataDomain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.time.OffsetDateTime;
import java.util.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Table(name = "orderr") // order is keyword
public class DomainOrder {
    @Id
    @Builder.Default
    private UUID id = UUID.randomUUID();

    /**
     * If this is null then order is mutable (like changing OrderLine.quantity).
     * TODO! possible to send emails to customers asking them to finish their order when submittedDate is null.
     */
    @Nullable
    private OffsetDateTime submittedDate;


    // ---------------------- reference navigational properties along with physical FK property block ----------------------

    /**
     * Deleted user will be null
     */
    @Nullable
    @Column(name = "customer_id")
    private UUID customerId;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private DomainCustomer customer;


    // ---------------------- collection navigational (inverse) properties block ----------------------

    @OneToMany(
            mappedBy = DomainOrderLine.Fields.order
    )
    @Builder.Default
    private Set<DomainOrderLine> orderLines = new HashSet<>();
}
