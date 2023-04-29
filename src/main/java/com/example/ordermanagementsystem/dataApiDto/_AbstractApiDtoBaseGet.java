package com.example.ordermanagementsystem.dataApiDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;

import java.util.UUID;

@Builder
public abstract class _AbstractApiDtoBaseGet {

    @NotNull
    public UUID id;
}
