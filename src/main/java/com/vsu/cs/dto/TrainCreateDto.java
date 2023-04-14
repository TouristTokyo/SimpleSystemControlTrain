package com.vsu.cs.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigInteger;

@Data
public class TrainCreateDto {
    @NotNull
    private String name;

    private Double speed;

    private Boolean isBroken;

    @NotNull
    private BigInteger currentStationId;

    @NotNull
    private BigInteger routId;
}
