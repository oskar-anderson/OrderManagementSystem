package com.example.ordermanagementsystem.dataApiDto;

import com.example.ordermanagementsystem.dataDomain.DomainOrder;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
public class ApiDtoCustomerGetIncludeOrderThenIncludeOrderLine {

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

    @NotNull
    public List<ApiDtoOrderGetIncludeOrderLines> customerOrders = new ArrayList<>();
}
