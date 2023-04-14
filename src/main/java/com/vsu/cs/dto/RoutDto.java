package com.vsu.cs.dto;

import com.vsu.cs.models.Station;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoutDto {
    @NotNull
    private String name;

    @NotNull
    private Station stationStart;

    @NotNull
    private Station stationEnd;
}