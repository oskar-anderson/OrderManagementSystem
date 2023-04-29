package com.example.ordermanagementsystem.dataApiDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class ApiDtoOrderCreate {

    /**
     * It is not necessary to include id field. If id is not provided a random one will be generated.
     */
    @Null
    private UUID id = UUID.randomUUID();

    @Null
    private OffsetDateTime submittedDate;

    @NotNull
    private UUID customerId;

    @NotNull
    private List<ApiDtoOrderLineCreate> orderLines;
}
