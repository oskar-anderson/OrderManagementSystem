package com.example.ordermanagementsystem.dataApiDto;

import com.example.ordermanagementsystem.dataDomain.DomainCustomer;
import com.example.ordermanagementsystem.dataDomain.DomainOrderLine;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public class ApiDtoOrderGetIncludeOrderLines {

    @NotNull
    public UUID id;

    @Nullable
    public OffsetDateTime submittedDate;


    // ---------------------- reference navigational properties along with physical FK property block ----------------------

    /**
     * Deleted user will be null
     */
    @Nullable
    public UUID customerId;



    // ---------------------- collection navigational (inverse) properties block ----------------------

    @NotNull
    public List<ApiDtoOrderLineGet> orderLines;
}
