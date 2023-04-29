package com.example.ordermanagementsystem.dataApiDto;

import com.example.ordermanagementsystem.dataDomain.DomainOrderLine;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class ApiDtoProductCreate {

    /**
     * It is not necessary to include id field. If id is not provided a random one will be generated.
     */
    @Null
    private UUID id = UUID.randomUUID();

    @NotNull
    private String name;

    @NotNull
    private String skuCode;

    @NotNull
    private String unitPrice;

    @NotNull
    private OffsetDateTime startDate = OffsetDateTime.now();

    @NotNull
    private OffsetDateTime endDate = OffsetDateTime.MAX;
}
