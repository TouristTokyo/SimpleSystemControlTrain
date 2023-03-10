package com.vsu.cs.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StationDto {
    @NotNull
    private String name;

    @NotNull
    private String town;
}
