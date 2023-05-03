package com.example.ordermanagementsystem.dataDal;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DalCustomer {

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

    @Builder.Default
    private Set<DalOrder> customerOrders = new HashSet<>();
}
