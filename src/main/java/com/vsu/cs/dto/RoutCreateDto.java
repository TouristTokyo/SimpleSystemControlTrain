package com.vsu.cs.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigInteger;

@Data
public class RoutCreateDto {
    @NotNull
    private String name;

    @NotNull
    private BigInteger stationStartId;

    @NotNull
    private BigInteger stationEndId;
}
