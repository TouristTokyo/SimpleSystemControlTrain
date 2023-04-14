package com.vsu.cs.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventDto {
    @NotNull
    private String description;

    private LocalDate date;
}
