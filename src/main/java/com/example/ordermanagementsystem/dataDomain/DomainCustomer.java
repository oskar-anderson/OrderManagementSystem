package com.example.ordermanagementsystem.dataDomain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Table(name = "customer")
public class DomainCustomer {

    @Id
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @NotNull
    private String registrationCode;

    @NotNull
    private String fullName;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;

    @OneToMany(
            mappedBy = DomainOrder.Fields.customer
    )
    @Builder.Default
    private List<DomainOrder> customerOrders = new ArrayList<>();
}
