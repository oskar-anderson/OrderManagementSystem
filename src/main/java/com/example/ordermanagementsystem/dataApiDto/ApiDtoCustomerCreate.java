package com.example.ordermanagementsystem.dataApiDto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ApiDtoCustomerCreate {

    /**
     * It is not necessary to include id field. If id is not provided a random one will be generated.
     */
    @Nullable
    public UUID id = UUID.randomUUID();

    @NotNull
    @Pattern(regexp = "\\b[0-9a-f]{4}-[0-9a-f]{4}\\b", message = "Invalid registration code pattern!")
    public String registrationCode;

    @NotNull
    @Size(min = 1, max = 64, message = "Invalid person name!")
    public String fullName;

    @NotNull
    @Email(message = "Invalid person email!")
    public String email;

    @NotNull
    @Size(min = 7, max = 10, message = "Invalid phone number!")
    public String phoneNumber;
}
