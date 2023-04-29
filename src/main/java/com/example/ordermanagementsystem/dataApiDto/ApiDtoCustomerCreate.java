package com.example.ordermanagementsystem.dataApiDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ApiDtoCustomerCreate {

    /**
     * It is not necessary to include id field. If id is not provided a random one will be generated.
     */
    @Null
    private UUID id = UUID.randomUUID();

    @NotNull
    private String registrationCode;

    @NotNull
    private String fullName;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;
}
