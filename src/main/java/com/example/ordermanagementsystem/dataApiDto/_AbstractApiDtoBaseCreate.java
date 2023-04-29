package com.example.ordermanagementsystem.dataApiDto;

import jakarta.validation.constraints.Null;
import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class _AbstractApiDtoBaseCreate {
    /**
     * It is not necessary to include id field. If id is not provided a random one will be generated.
     */
    @Null
    private UUID id = UUID.randomUUID();
}
