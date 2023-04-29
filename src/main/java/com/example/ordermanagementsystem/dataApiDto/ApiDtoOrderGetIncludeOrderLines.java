package com.example.ordermanagementsystem.dataApiDto;

import com.example.ordermanagementsystem.dataDomain.DomainCustomer;
import com.example.ordermanagementsystem.dataDomain.DomainOrderLine;
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

    @Null
    private OffsetDateTime submittedDate;


    // ---------------------- reference navigational properties along with physical FK property block ----------------------

    /**
     * Deleted user will be null
     */
    @Null
    private UUID customerId;



    // ---------------------- collection navigational (inverse) properties block ----------------------

    @NotNull
    private List<ApiDtoOrderLineGet> orderLines;
}
