package com.example.ordermanagementsystem.dataApiDto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class ApiDtoOrderCreate {

    @Nullable
    public OffsetDateTime submittedDate;

    @NotNull
    public UUID customerId;

    @NotNull
    public List<ApiDtoOrderLineCreateChild> orderLines;
}
