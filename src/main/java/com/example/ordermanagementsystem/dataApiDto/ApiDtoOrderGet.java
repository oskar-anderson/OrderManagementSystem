package com.example.ordermanagementsystem.dataApiDto;

import com.example.ordermanagementsystem.dataDomain.DomainCustomer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;


import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
public class ApiDtoOrderGet {

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
}
