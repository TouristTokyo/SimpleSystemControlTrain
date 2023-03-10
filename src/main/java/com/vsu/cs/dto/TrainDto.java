package com.vsu.cs.dto;

import com.vsu.cs.models.Rout;
import com.vsu.cs.models.Station;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TrainDto {
    @NotNull
    private String name;

    @NotNull
    private Double speed;

    @NotNull
    private Boolean isBroken;

    @NotNull
    private Station currentStation;

    @NotNull
    private Rout rout;
}
