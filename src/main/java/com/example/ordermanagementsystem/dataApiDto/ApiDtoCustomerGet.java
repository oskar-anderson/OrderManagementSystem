package com.example.ordermanagementsystem.dataApiDto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public class ApiDtoCustomerGet {

    @NotNull
    public UUID id;

    @NotNull
    public String registrationCode;

    @NotNull
    public String fullName;

    @NotNull
    public String email;

    @NotNull
    public String phoneNumber;
}
